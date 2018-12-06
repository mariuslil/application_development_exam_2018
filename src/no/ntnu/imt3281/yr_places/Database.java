package no.ntnu.imt3281.yr_places;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private static Database database;
    private static String DBURL;

    private Database(){
        connectDB();
    }

    public static Database getDB() {
        DBURL = "jdbc:derby:yrDB";
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    public static Database getDBTest() {
        DBURL = "jdbc:derby:memory:yrDB";
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    private Connection connectDB(){
        try(Connection connect = DriverManager.getConnection(DBURL)) {
            System.out.println("DATABASE: Connected");
            return connect;
        } catch (SQLException e) {
            System.out.println("DATABASE: Cannot connect, creating..");
            return setupDB();
        }
    }

    private Connection setupDB(){
        try(Connection connect = DriverManager.getConnection(DBURL + ";create=true")) {
            System.out.println("DATABASE: Created");
            String sql = "CREATE TABLE PLACES\n" +
                    "(Kommunenr INTEGER NOT NULL, \n" +
                    "StedsNavn VARCHAR(64) NOT NULL, \n" +
                    "Prioritet INTEGER NOT NULL, \n" +
                    "StedsType VARCHAR(64) NOT NULL, \n" +
                    "Kommune VARCHAR(64) NOT NULL, \n" +
                    "Fylke VARCHAR(64) NOT NULL, \n" +
                    "Latitude FLOAT NOT NULL, \n" +
                    "Longitude FLOAT NOT NULL, \n" +
                    "URL VARCHAR(128) NOT NULL)";
            Statement stmnt = connect.createStatement();
            stmnt.execute(sql);
            stmnt.close();
            System.out.println("DATABASE: Table created");
            return connect;
        }catch (SQLException e) {
            System.out.println("DATABASE: ERROR Creating DB");
            e.printStackTrace();
            return null;
        }
    }

    public int addPlace(Place p) {
        try(Connection connect = DriverManager.getConnection(DBURL)) {
            String sql = "INSERT INTO PLACES (Kommunenr, StedsNavn, StedsType, Kommune, Fylke, Latitude, Longitude, URL) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement stmnt = connect.prepareStatement(sql);
            stmnt.setInt(1, p.getKommunenr());
            stmnt.setString(2, p.getStedsnavn());
            stmnt.setString(3, p.getStedstype());
            stmnt.setString(4, p.getKommune());
            stmnt.setString(5, p.getFylke());
            stmnt.setDouble(6, p.getLat());
            stmnt.setDouble(7, p.getLng());
            stmnt.setString(8, p.getVarselURL());

            int rows = stmnt.executeUpdate();
            return rows;

        } catch (SQLException e) {
            System.out.println("DATABASE: ERROR no connection");
            return 0;
        }
    }

    public Place findPlace (double lng, double lat) {
        try(Connection connect = DriverManager.getConnection(DBURL)) {
            ArrayList<String> p = new ArrayList<>();
            Statement stmnt = connect.createStatement();
            String sql = "";
            ResultSet rs = stmnt.executeQuery(sql);
            stmnt.close();
            int i = 0;
            while(rs.next()) {
                p.add(rs.getString(i));
                i++;
            }

            Place place = new Place(p);

            return place;

        } catch (SQLException e) {
            System.out.println("DATABASE: ERROR");
        }

        return null;
    }

}
