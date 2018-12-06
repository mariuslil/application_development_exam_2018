package no.ntnu.imt3281.yr_places;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestAllThePlaces {

    @Test
    public void TestImport() throws IOException {
        ArrayList<ArrayList<String>> p = new ArrayList<ArrayList<String>>();
        p = AllThePlaces.getAllPlaces();
        assertEquals("101\tAsak kirke\t55\tKyrkje\tKirke\tChurch\tHalden\tØstfold\t59.14465\t11.45458\t\t" +
                "http://www.yr.no/stad/Noreg/Østfold/Halden/Asak_kirke/varsel.xml\t" +
                "http://www.yr.no/sted/Norge/Østfold/Halden/Asak_kirke/varsel.xml\t" +
                "http://www.yr.no/place/Norway/Østfold/Halden/Asak_kirke/forecast.xml", p.get(1).get(1));
    }
}
