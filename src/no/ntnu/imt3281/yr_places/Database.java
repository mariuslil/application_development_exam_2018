package no.ntnu.imt3281.yr_places;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private Connection dbCon;

    public Database(){
        this.dbCon = memDB();
    }

    private Connection memDB() {
        try (Connection connect = DriverManager.getConnection("jdbc:derby:memory:yrDB;create=true")) {
            System.out.println("DATABASE: Created");
            String sql = "CREATE TABLE PLACES\n" +
                    "(Kommunenr INTEGER NOT NULL, \n" +
                    "StedsNavn VARCHAR(64) NOT NULL PRIMARY KEY, \n" +
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
        } catch (SQLException e) {
            System.out.println("DATABASE: ERROR Creating DB");
            return null;
        }
    }

    private Connection connectDB(){
        try(Connection connect = DriverManager.getConnection("jdbc:derby:yrDB")) {
            System.out.println("DATABASE: Connected");
            return connect;
        } catch (SQLException e) {
            System.out.println("DATABASE: Cannot connect, creating..");
            return setupDB();
        }
    }

    private Connection setupDB(){
        try(Connection connect = DriverManager.getConnection("jdbc:derby:yrDB;create=true")) {
            System.out.println("DATABASE: Created");
            String sql = "CREATE TABLE PLACES\n" +
                    "(Kommunenr INTEGER NOT NULL, \n" +
                    "StedsNavn VARCHAR(64) NOT NULL PRIMARY KEY, \n" +
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
            return null;
        }
    }

    public void addPlace(Place p) {

    }

}
