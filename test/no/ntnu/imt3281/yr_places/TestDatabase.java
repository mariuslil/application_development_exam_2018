package no.ntnu.imt3281.yr_places;

import org.junit.Test;

import java.io.IOException;
import java.sql.*;

import static org.junit.Assert.assertEquals;

public class TestDatabase {

    @Test
    public void testAddPlace(){
        Database db = Database.getDBTest();
        Place p = null;
        try {
           p = new Place(AllThePlaces.getAllPlaces().get(8197));
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.addPlace(p);

        try (Connection connect = DriverManager.getConnection("jdbc:derby:memory:yrDB")) {
            Statement stmnt = connect.createStatement();
            String sql = "SELECT StedsNavn FROM PLACES " +
                    "WHERE StedsNavn LIKE Ankenes AND Prioritet LIKE 55";
            ResultSet rs = stmnt.executeQuery(sql);
            stmnt.close();

            assertEquals(1805,rs.getString("Kommunenr"));
            assertEquals("Ankenes", rs.getString("StedsNavn"));
            assertEquals("55", rs.getString("Prioritet"));
            assertEquals("Kirke", rs.getString("StedsType"));
            assertEquals("Narvik", rs.getString("Kommune"));
            assertEquals("Nordland", rs.getString("Fylke"));
            assertEquals(68.42101, rs.getString("Latitude"));
            assertEquals(17.37877, rs.getString("Longitude"));
            assertEquals("http://www.yr.no/stad/Noreg/Nordland/Narvik/Ankenes~283165/varsel.xml", rs.getString("URL"));

        } catch (SQLException e) {
            System.out.println("DATABASE: ERROR");
        }
    }

    @Test
    public void testFindPlace(){

    }

}
