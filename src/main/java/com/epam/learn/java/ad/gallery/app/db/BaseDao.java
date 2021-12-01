package com.epam.learn.java.ad.gallery.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epam.learn.java.ad.gallery.api.db.BaseDaoI;
import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;

/**
 * Boundary of database layer
 * 
 * @author Administrator
 *
 * @param <T>
 */
abstract public class BaseDao<T> implements BaseDaoI<T> {

	protected Connection con;
	protected PreparedStatement insertStatement;

	public BaseDao(Connection con) {
		this.con = con;
	}

	public void insert(T object) throws DBProblemException {
		try (PreparedStatement ps = con.prepareStatement(insertSQL(), Statement.RETURN_GENERATED_KEYS)) {
			putObject(ps, object);
			ps.executeUpdate();

			try (ResultSet rs = ps.getGeneratedKeys()) {
				rs.next();
				updateKey(object, rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new DBProblemException(e);
		}
	}

	protected abstract void updateKey(T object, int i);

	protected abstract void putObject(PreparedStatement ps, T o) throws SQLException;

	public void insert(List<T> l) throws DBProblemException {
		try (PreparedStatement ps = con.prepareStatement(insertSQL())) {
			for (T o : l) {
				putObject(ps, o);
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException e) {
			throw new DBProblemException(e);
		}
	}

	protected abstract String insertSQL();

	protected abstract String updateSQL();

	public void update(T o) throws DBProblemException {
		try (PreparedStatement ps = con.prepareStatement(updateSQL())) {
			putObject(ps, o);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DBProblemException(e);
		}
	}

	protected abstract String selectByIdSQL(int id);

	/**
	 * 
	 * @param rs Already checked for next!!!
	 * @return
	 * @throws SQLException
	 */
	protected abstract T getObject(ResultSet rs) throws SQLException;
	
	protected List<T> query(String sql) throws DBProblemException {
		List<T> res = new ArrayList<>();
		try (Statement st = con.createStatement()) {
			st.execute(sql);
			try (ResultSet rs = st.getResultSet()) {

				while(rs.next()) {
					res.add(getObject(rs));
				}
				
//				T var;
//				while((var = getObject(rs)) != null) {
//					res.add(var);
//				}
			}
		} catch (SQLException e) {
			throw new DBProblemException(e);
		}
		return res;
	}
	
	public Optional<T> get(int id) throws DBProblemException {
		String sql = selectByIdSQL(id);
		try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
			if (rs.next()) {
				return Optional.of(getObject(rs));
			} 
			return Optional.empty();
		} catch (SQLException e) {
			throw new DBProblemException(e);
		}
	}

	public List<T> getAll() throws DBProblemException {
		return null;
	}

	@Override
	public void close() throws DBProblemException {
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				throw new DBProblemException(e);
			}
	}
	
	@Override
	public void delete(int id) throws DBProblemException {
		String sql = "DELETE FROM " + getTambleName() + " WHERE id = " +  id; 
		try (Statement st = con.createStatement()){
			st.execute(sql);
		} catch (SQLException e) {
			throw new DBProblemException(e);
		} 
	}

	protected String getTambleName() {
		return "not_existing_table";
	}

	protected int count(String sql) throws DBProblemException {
		try(Statement st = con.createStatement()) {
			try(ResultSet rs = st.executeQuery(sql)){
				rs.next();
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DBProblemException(e);
		}

	}

}
