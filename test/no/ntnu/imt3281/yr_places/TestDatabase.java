package no.ntnu.imt3281.yr_places;

import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TestDatabase {

    @Test
    public void testAddPlace(){
        Database db = new Database("T");
        Place p = null;
        try {
           p = new Place(AllThePlaces.getAllPlaces().get(8197));
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.addPlace(p);

        try (Connection connect = DriverManager.getConnection("jdbc:derby:memory:yrDB")) {
            Statement stmnt = connect.createStatement();
            String sql = "FROM PLACES SELECT StedsNavn" +
                    "WHERE StedsNavn LIKE Ankenes AND Prioritet LIKE 55";
            String place = stmnt.execute(sql);
            stmnt.close();

        } catch (SQLException e) {

        }
    }
}
