package no.ntnu.imt3281.yr_places;

import java.sql.*;

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
                    "URL VARCHAR(128) NOT NULL, \n" +
                    "CONSTRAINT PK_Place PRIMARY KEY(StedsNavn, Prioritet)";
            Statement stmnt = connect.createStatement();
            stmnt.execute(sql);
            stmnt.close();
            System.out.println("DATABASE: Table created");

            return connect;
        }catch (SQLException e) {
            System.out.println("DATABASE: ERROR Creating DB");
            return null;
        }
    }

    public void addPlace(Place p) {
        try(Connection connect = DriverManager.getConnection(DBURL)) {
            String sql = "INSERT INTO PLACES (Kommunenr, StedsNavn, StedsType, Kommune, Fylke, Latitude, Longitude, URL) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement stmnt = connect.prepareStatement(sql);
            stmnt.setInt(1, p.getKommunenr());
            stmnt.setString(2, p.getStedsnavn());
            stmnt.setString(3, p.getStedstype());
            stmnt.setString(4, p.getKommune());
            stmnt.setString(5, p.getFylke());
            stmnt.setFloat(6, p.getLat());
            stmnt.setFloat(7, p.getLng());
            stmnt.setString(8, p.getVarselURL());

        } catch (SQLException e) {
            System.out.println("DATABASE: ERROR no connection");
        }
    }

    public Place findPlace (float lng, float lat) {
        try(Connection connect = DriverManager.getConnection(DBURL)) {

            Statement stmnt = connect.createStatement();
            String sql = "SELECT TOP 1 * FROM PLACES "+
                         "WHERE Latitude";
            ResultSet rs = stmnt.executeQuery(sql);
            stmnt.close();



        } catch (SQLException e) {
            System.out.println("DATABASE: ERROR");
        }

        return null;
    }

}
