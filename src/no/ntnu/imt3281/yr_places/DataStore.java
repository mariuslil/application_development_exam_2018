package no.ntnu.imt3281.yr_places;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * All communication with the database goes through this class, the application should not
 * have to worry about the database specifics.
 *
 * @author oeivindk
 *
 */
public class DataStore {
	static Connection db = null;
	static DataStore store = null;
	static PreparedStatement insertStatement;

	/**
	 *
	 * @return an instance of this class containing a persistent (disc based) database.
	 * @throws SQLException
	 */
	public static DataStore getDataStore() throws SQLException {
		if (store==null) {
			store = new DataStore(true);
		}
		return store;
	}

	/**
	 *
	 * @return an instance of this class containing a non persistent (memory based) database.
	 * @throws SQLException
	 */
	public static DataStore getTemporaryStore() throws SQLException {
		if (store==null) {
			store = new DataStore(false);
		} else {
			db.close();
			db = null;
			store = new DataStore(false);
		}
		return store;
	}

	/**
	 * Adds a place to the store.
	 *
	 * @param place contains all information about the place to add to the store
	 */
	public int addPlace (Place place) {
		try {
			insertStatement.setInt(1, place.getKommunenr());
			insertStatement.setString(2, place.getStedsnavn());
			insertStatement.setString(3, place.getStedstype());
			insertStatement.setString(4, place.getKommune());
			insertStatement.setString(5, place.getFylke());
			insertStatement.setDouble(6, place.getLat());
			insertStatement.setDouble(7, place.getLng());
			insertStatement.setString(8, place.getVarselURL());
			return insertStatement.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return 0;
		}
	}

	/**
	 * Get number of places in database.
	 *
	 * @return the number of places that exists in tne database.
	 * @throws SQLException
	 */
	public Object placesInStore() throws SQLException {
		String sql = "SELECT COUNT(*) FROM places";
		Statement stmnt = db.createStatement();
		ResultSet res = stmnt.executeQuery(sql);
		if (res.next()) {
			return res.getInt(1);
		}
		return 0;
	}

	/**
	 * Gets a position and returns the place closest to this position.
	 *
	 * @param lat
	 * @param lng
	 * @return the place in the database that is closest to the given lat/lng
	 * @throws SQLException
	 */
	public Place getClosestPlace(double lat, double lng) throws SQLException {
		String sql = "SELECT kommunenr, stedsnavn, stedstype, kommune, fylke, lat, lng, varsel, ((?-lat)*(?-lat))+((?-lng)*(?-lng)) AS dist FROM places ORDER BY dist ASC FETCH FIRST ROW ONLY";
		PreparedStatement stmnt = db.prepareStatement(sql);
		stmnt.setDouble(1, lat);
		stmnt.setDouble(2, lat);
		stmnt.setDouble(3, lng);
		stmnt.setDouble(4, lng);
		ResultSet res = stmnt.executeQuery();
		Place p = null;
		if (res.next()) {
			p = new Place(res);
			/*
			Add this method to Place class :
				public Place(ResultSet res) throws SQLException {
					kommunenr = res.getInt(1);
					stedsnavn = res.getString(2);
					stedstype = res.getString(3);
					kommune = res.getString(4);
					fylke = res.getString(5);
					lat = res.getDouble(6);
					lng = res.getDouble(7);
					url = res.getString(8);
				}
			 */
		}
		return p;
	}

	private DataStore(boolean permanent) throws SQLException {
		db = DriverManager.getConnection("jdbc:derby"+(!permanent?":memory":"")+":placesDB;create=true");
		createTable();	// Create table if not exists
		insertStatement = db.prepareStatement("INSERT INTO places (kommunenr, stedsnavn, stedstype, kommune, fylke, lat, lng, varsel) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
	}

	private void createTable() throws SQLException {
		String sql = "CREATE TABLE places ("
				+ "kommunenr int not null,"
				+ "stedsnavn varchar(128) not null,"
				+ "stedstype varchar(128) not null,"
				+ "kommune varchar(128) not null,"
				+ "fylke varchar(128) not null,"
				+ "lat double not null,"
				+ "lng double not null,"
				+ "varsel varchar(255) not null)";
		Statement stmnt = db.createStatement();
		try {
			stmnt.execute(sql);
		} catch (SQLException sqle) {
			if (!sqle.getSQLState().equals("X0Y32")) {	/// X0Y32 means table exists
				throw (sqle);
			}
		}
	}
}
