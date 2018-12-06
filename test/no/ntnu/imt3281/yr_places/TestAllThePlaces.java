package no.ntnu.imt3281.yr_places;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestAllThePlaces {

    /**
     * Tests the returned list of all places, with an arbitary place from the list to check that it's been imported correctly.
     * @throws IOException
     */
    @Test
    public void testImport() throws IOException {
        ArrayList<ArrayList<String>> p = new ArrayList<ArrayList<String>>();
        p = AllThePlaces.getAllPlaces();
        assertEquals("[1805, Ankenes, 55, Kyrkje, Kirke, Church, Narvik, Nordland, 68.42101, 17.37877, , http://www.yr.no/stad/Noreg/Nordland/Narvik/Ankenes~283165/varsel.xml, http://www.yr.no/sted/Norge/Nordland/Narvik/Ankenes~283165/varsel.xml, http://www.yr.no/place/Norway/Nordland/Narvik/Ankenes~283165/forecast.xml]" ,p.get(8197).toString());
    }
}
