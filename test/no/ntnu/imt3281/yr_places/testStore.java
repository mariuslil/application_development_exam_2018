package no.ntnu.imt3281.yr_places;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Arrays;

import org.junit.Test;

public class testStore {
	String data = "101	Asak kirke	55	Kyrkje	Kirke	Church	Halden	Østfold	59.14465	11.45458		http://www.yr.no/stad/Noreg/Østfold/Halden/Asak_kirke/varsel.xml	http://www.yr.no/sted/Norge/	";
	Place place = new Place(Arrays.asList(data.split("\t")));


	/**
	 * A temporary datastore should be an in memory store that is discarded when it is no longer used or
	 * a new temporary store is requested. That is, when calling getTemporaryStore two times we should get
	 * two different objects.
	 */
	@Test
	public void testCreateTemporaryStore() {
		try {
			DataStore store = DataStore.getTemporaryStore();
			DataStore store1 = DataStore.getTemporaryStore();
			assertNotEquals(store, store1);						// A new DataStore should be created for each call to getTemporaryStore
		} catch (SQLException e) {
			fail("Error creating temporary datastore : "+e.getMessage());
		}
	}

	/**
	 * When creating a permanent store, the store should be kept open until the program terminates and
	 * successive calls to getDataStore should return the same reference as the first call to getDataStore.
	 */
	@Test
	public void testCreatePermanentStore() {
		try {
			DataStore store = DataStore.getDataStore();
			DataStore store1 = DataStore.getDataStore();
			assertEquals(store, store1);
		} catch (SQLException e) {
			fail ("Error create permanent datastore : "+e.getMessage());
		}
	}

	@Test
	public void testInsertPlace() {
		try {
			DataStore store = DataStore.getTemporaryStore();
			assertEquals(0, store.placesInStore());
			assertEquals(1, store.addPlace(place));
			assertEquals(1, store.placesInStore());
		} catch (SQLException e) {
			fail ("Error during database transactions : "+e.getMessage());
		}
	}

	@Test
	public void testClosestPlace() {
		try {
			DataStore store = DataStore.getTemporaryStore();
			store.addPlace(place);
			String data = "1	Test Place	1	Test place	Kirke	Church	Halden	Østfold	58.14465	12.45458		http://www.yr.no/stad/Noreg/Østfold/Halden/Asak_kirke/varsel.xml	http://www.yr.no/sted/Norge/	";
			place = new Place(Arrays.asList(data.split("\t")));
			store.addPlace(place);
			place = store.getClosestPlace(58.6, 11.45);
			assertEquals("Asak kirke", place.getStedsnavn());
			place = store.getClosestPlace(58.6, 12.4);
			assertEquals("Test Place", place.getStedsnavn());
		} catch (SQLException e) {
			e.printStackTrace();
			fail ("Error during database transactions : "+e.getMessage());
		}
	}
}
