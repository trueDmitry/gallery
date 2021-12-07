package com.epam.learn.java.ad.gallery.app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.epam.learn.java.ad.gallery.api.DatabaseServiceI;
import com.epam.learn.java.ad.gallery.api.ExpositionServiceI;
import com.epam.learn.java.ad.gallery.api.db.ExpositionDaoI;
import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;

public class ExpositionServiceTest {
	DatabaseServiceI dbMock = Mockito.mock(DatabaseServiceI.class);
	ExpositionDaoI expoDaoMock; 
	
	@Before
	public void before() {
		dbMock = Mockito.mock(DatabaseServiceI.class);
		expoDaoMock = Mockito.mock(ExpositionDaoI.class);
		
		Mockito.when(dbMock.getExpositionDao()).thenReturn(expoDaoMock);
	}
	
	@Test
	public void shouldNotStoreNull() {
		ExpositionServiceI expoServ = new ExpositionService(null, null, dbMock);
		try {
			assertFalse(expoServ.store(null));
		}catch (DBProblemException e) {
			fail();
		}
	}
	
	@Test
	public void shouldCallDelete() {
		int fakeId = 1;
		try {
			
			new ExpositionService(null, null, dbMock).delete(fakeId);
			
			Mockito.verify(expoDaoMock, Mockito.times(1)).delete(Mockito.eq(fakeId));
		} catch (DBProblemException e) {
			fail();
		}
		
		
	}

}
