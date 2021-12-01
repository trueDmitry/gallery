package com.epam.learn.java.ad.gallery.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import com.epam.learn.java.ad.gallery.api.db.ExpositionRoomDaoI;
import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;
import com.epam.learn.java.ad.gallery.app.model.ExpositionRoom;

public class ExpositionRoomDao extends BaseDao<ExpositionRoom> implements ExpositionRoomDaoI{

	public ExpositionRoomDao(Connection con) {
		super(con);
	}

	@Override
	protected void putObject(PreparedStatement ps, ExpositionRoom o) throws SQLException {
		ps.setInt(1, o.getExpositionId());
		ps.setInt(2, o.getRoomId());
	}

	@Override
	protected String insertSQL() {
		return "INSERT INTO exposition_room (exposition_id, room_id) VALUES (?, ?)";
	}

	@Override
	protected String updateSQL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void updateKey(ExpositionRoom object, int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String selectByIdSQL(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ExpositionRoom getObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByExpositionId(int id) throws DBProblemException {
		try (Statement s = con.createStatement()) {
			String sqlRoomsClean = "DELETE FROM exposition_room WHERE exposition_id = " + id;
			s.execute(sqlRoomsClean);
		} catch (SQLException e) {
			throw new DBProblemException(e);
		}
	}

}
