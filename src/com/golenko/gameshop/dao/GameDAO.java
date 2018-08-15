package com.golenko.gameshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.golenko.gameshop.dao.MySqlConnection;
import com.golenko.gameshop.model.Game;
import com.golenko.gameshop.dao.GameDAO;
import com.golenko.gameshop.model.Game;

public class GameDAO {

	private static String SQL_INSERT = "INSERT INTO games (name, gamedev, date) VALUE (?, ?, ?); ";
	private static String SQL_UPDATE = "UPDATE games set name=?, gamedev=?, date=? where id=?";
	private static String SQL_SELECT = "SELECT * FROM games;";
	private static String SQL_DELETE = "delete from games;";
	private static String SQL_SELECT_BY_ID = "SELECT * FROM games where id = ?;";
	private static String SQL_DELETE_BY_ID = "delete from games where id = ?;";
	private static final String FIELD_ID = "id";
	private static final String FIELD_NAME = "name";
	private static final String FIELD_GAMEDEV = "gamedev";
	private static final String FIELD_DATE = "date";
	private static GameDAO GAME_DAO = null;

	private GameDAO() {

	}

	public static GameDAO getInstance() {
		if (GAME_DAO == null) {
			GAME_DAO = new GameDAO();
		}
		return GAME_DAO;
	}

	public boolean delete(Game item) {
		return delete(item.getId());
	}

	public boolean delete(long id) {
		Connection con = null;
		try {
			con = MySqlConnection.getConnection();
			PreparedStatement st = con.prepareStatement(SQL_DELETE_BY_ID);
			st.setLong(1, id);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			close(con);
		}
	}

	public boolean deleteAll() {
		Connection con = null;
		try {
			con = MySqlConnection.getConnection();
			PreparedStatement st = con.prepareStatement(SQL_DELETE);
			int n = st.executeUpdate();
			if (n == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			close(con);
		}
	}

	public List<Game> selectAll() {
		Connection con = null;
		List<Game> list = new ArrayList<Game>();
		try {
			con = MySqlConnection.getConnection();
			Statement st = con.createStatement();
			ResultSet result = st.executeQuery(SQL_SELECT);
			while (result.next()) {
				list.add(extractGame(result));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		} finally {
			close(con);
		}
		return list;
	}

	public Game select(int id) {
		Connection con = null;
		Game item = null;
		try {
			con = MySqlConnection.getConnection();
			PreparedStatement st = con.prepareStatement(SQL_SELECT_BY_ID);
			st.setInt(1, id);
			ResultSet result = st.executeQuery();
			if (result.next()) {
				item = extractGame(result);
			}
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			return item;
		} finally {
			close(con);
		}
	}

	/**
	 * Method insert onject into database
	 * 
	 * @param item
	 *            - the game will be inserted
	 * @return true if successfully inserted
	 */
	
	public boolean insert(Game item) {
		return insert(item.getName(), item.getGamedev(), item.getDate());
	}

	public boolean insert(String name, String gamedev, Date date) {
		Connection con = null;
		try {
			con = MySqlConnection.getConnection();
			PreparedStatement st = con.prepareStatement(SQL_INSERT);
			st.setString(1, name);
			st.setString(2, gamedev);
			st.setLong(3, date.getTime());
			int n = st.executeUpdate();
			if (n == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			close(con);
		}
	}

	private void close(Connection connection) {
		try {
			if (connection != null)
			connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void update(Game item) {
		Connection con = null;
		try {
			con = MySqlConnection.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(SQL_UPDATE);
			preparedStatement.setString(1, item.getName());
			preparedStatement.setString(2, item.getGamedev());
			preparedStatement.setLong(3, item.getDate().getTime());
			preparedStatement.setLong(4, item.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con);
		}
	}

	private Game extractGame(ResultSet rs) throws SQLException {
		Game item = new Game();
		item.setId(rs.getLong(FIELD_ID));
		item.setName(rs.getString(FIELD_NAME));
		item.setGamedev(rs.getString(FIELD_GAMEDEV));
		item.setDate(new Date(rs.getLong(FIELD_DATE)));
		return item;
	}
}
