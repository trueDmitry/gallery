package com.epam.learn.java.ad.gallery.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import com.epam.learn.java.ad.gallery.api.db.ExpositionDaoI;
import com.epam.learn.java.ad.gallery.api.db.FilterI;
import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;
import com.epam.learn.java.ad.gallery.app.model.Exposition;
import com.epam.learn.java.ad.gallery.app.model.Room;

public class ExpositionDao extends BaseDao<Exposition> implements ExpositionDaoI {

	public ExpositionDao(Connection con) {
		super(con);
	}

	@Override
	protected String getTambleName() {
		return "exposition";
	}

	@Override
	protected void putObject(PreparedStatement ps, Exposition o) throws SQLException {
		ps.setString(1, o.getTheme());
		ps.setInt(2, o.getPrice());
		ps.setDate(3, new java.sql.Date(o.getStart().getTime()));
		ps.setDate(4, new java.sql.Date(o.getEnd().getTime()));
		ps.setInt(5, o.getOpen());
		ps.setInt(6, o.getClose());
		ps.setInt(7, o.getPublished() ? 1 : 0);
		if (o.getId() != 0) {
			ps.setInt(8, o.getId());
		}
	}

	protected String insertSQL() {
		return "INSERT INTO exposition (theme, price, start, end, from_time, till_time, published) VALUES (?, ?, ?, ?, ?, ?, ?)";
	}

	@Override
	protected String updateSQL() {
		return "UPDATE exposition SET theme = ?, price = ?, start = ?, end = ?, from_time = ?, till_time = ?, published= ?  WHERE id = ?";
	}

	@Override
	protected void updateKey(Exposition e, int id) {
		e.setId(id);
	}

	@Override
	protected String selectByIdSQL(int id) {
		return "SELECT " + FIELDS + "FROM exposition WHERE id =" + id;
	}

	@Override
	protected Exposition getObject(ResultSet rs) throws SQLException {
		Exposition ex = new Exposition();
		ex.setId(rs.getInt(1));
		ex.setTheme(rs.getString(2));
		ex.setPrice(rs.getInt(3));
		ex.setStart(rs.getDate(4));
		ex.setEnd(rs.getDate(5));
		ex.setOpen(rs.getInt(6));
		ex.setClose(rs.getInt(7));
		ex.setPublished(rs.getInt(8) == 1);
		setOcupations(con, ex);
		return ex;
	}

	// " id, theme, price, start, end, from_time, till_time, published ";
	private static void readExposition(Exposition ex, ResultSet rs) throws SQLException {
		ex.setId(rs.getInt(1));
		ex.setTheme(rs.getString(2));
		ex.setPrice(rs.getInt(3));
		ex.setStart(rs.getDate(4));
		ex.setEnd(rs.getDate(5));
		ex.setOpen(rs.getInt(6));
		ex.setClose(rs.getInt(7));
		ex.setPublished(rs.getInt(8) == 1);
	}

	private static final String FIELDS = " id, theme, price, start, end, from_time, till_time, published ";

	public static List<Exposition> getExpositions(boolean published) throws SQLException {
		ArrayList<Exposition> res = new ArrayList<>();
		int p = published ? 1 : 0;

		String sql = "SELECT " + FIELDS + "FROM exposition WHERE published =" + p;
		try (Connection con = ConnectionPool.getConnection(); Statement st = con.createStatement()) {
			st.execute(sql);
			try (ResultSet rs = st.getResultSet()) {
				while (rs.next()) {
					Exposition ex = new Exposition();
					readExposition(ex, rs);
					setOcupations(con, ex);
					res.add(ex);
				}
			}
		}
		return res;
	}

	public static List<Exposition> getPublishedExpositions() throws SQLException {
		return getExpositions(true);
	}

	public static List<Exposition> getExpositions() throws SQLException {
		ArrayList<Exposition> res = new ArrayList<>();

		String sql = "SELECT " + FIELDS + "FROM exposition";
		try (Connection con = ConnectionPool.getConnection(); Statement st = con.createStatement()) {
			st.execute(sql);
			try (ResultSet rs = st.getResultSet()) {
				while (rs.next()) {
					Exposition ex = new Exposition();
					readExposition(ex, rs);
					setOcupations(con, ex);
					res.add(ex);
				}
			}
		}
		return res;
	}

	private static void setOcupations(Connection con, Exposition ex) throws SQLException {
		ArrayList<Room> rooms = new ArrayList<>();

		String sql = "SELECT `id`, `name` FROM exposition_room JOIN room ON room_id = id WHERE exposition_id = "
				+ ex.getId();
		try (Statement st = con.createStatement()) {
			st.execute(sql);
			try (ResultSet rs = st.getResultSet()) {
				while (rs.next()) {
					Room r = new Room();
					readRoom(r, rs);
					rooms.add(r);
				}
			}
		}
		ex.setRooms(rooms);
	}

	private static void readRoom(Room r, ResultSet rs) throws SQLException {
		r.setId(rs.getInt(1));
		r.setName(rs.getString(2));
	}

	@Override
	public List<Exposition> get(int startIndex, int quantity, FilterI filter) throws DBProblemException {

		StringBuilder sql = new StringBuilder().append("SELECT ").append(FIELDS).append(" FROM exposition ")
				.append(" LIMIT ").append(startIndex).append(",").append(quantity);

		return query(sql.toString());
	}

	@Override
	public int count(FilterI filter) throws DBProblemException {
		String sql = "SELECT count(id) FROM exposition";
		return super.count(sql);
	}

}
