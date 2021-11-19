package com.epam.learn.java.ad.gallery.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epam.learn.java.ad.gallery.api.db.RoomDaoI;
import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;
import com.epam.learn.java.ad.gallery.model.Room;

public class RoomDao extends BaseDao<Room> implements RoomDaoI{

	public RoomDao(Connection con) {
		super(con);
	}

	@Override
	public void insert(Room order) throws DBProblemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String insertSQL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String updateSQL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String selectByIdSQL(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void updateKey(Room object, int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void putObject(PreparedStatement ps, Room o) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Optional<Room> getObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override 
	public List<Room> getAll() throws DBProblemException {
		ArrayList<Room> res = new ArrayList<>();

		String sql = "SELECT id, name FROM room";
		try (Connection con = ConnectionPool.getConnection(); Statement st = con.createStatement()) {
			st.execute(sql);
			try (ResultSet rs = st.getResultSet()) {
				while (rs.next()) {
					Room room = new Room();
					room.setId(rs.getInt(1));
					room.setName(rs.getString(2));
					res.add(room);
				}
			} 
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DBProblemException();
		}
		return res;	
	}

	
}
