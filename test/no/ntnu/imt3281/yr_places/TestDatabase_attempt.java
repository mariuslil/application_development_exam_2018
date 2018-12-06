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
        assertEquals(1, db.addPlace(p));
    }

    @Test
    public void testFindPlace(){
        Database db = Database.getDBTest();
        Place p = db.findPlace(17.37, 68.42);
        assertEquals("[1805, Ankenes, 55, Kyrkje, Kirke, Church, Narvik, Nordland, 68.42101, 17.37877, , http://www.yr.no/stad/Noreg/Nordland/Narvik/Ankenes~283165/varsel.xml, http://www.yr.no/sted/Norge/Nordland/Narvik/Ankenes~283165/varsel.xml, http://www.yr.no/place/Norway/Nordland/Narvik/Ankenes~283165/forecast.xml]" ,p.toString());
    }

}
