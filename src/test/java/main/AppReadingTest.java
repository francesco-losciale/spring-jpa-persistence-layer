package main;


import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.domain.object.TestCollection;
import main.domain.repository.ITestCollectionRepository;
import main.domain.repository.ITestRepository;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfigReading.class })
public class AppReadingTest extends TestCase {
			
	@Autowired
	private ITestCollectionRepository testCollectionRepository;
	
	@Autowired
	private ITestRepository testRepository;
	
	
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppReadingTest() {

        User user = new User("flosciale", "flosciale", Arrays.asList(new SimpleGrantedAuthority("ROLE_TEST2")));
        Authentication auth = new TestingAuthenticationToken(user, "password", "ROLE_TEST2");
        SecurityContextHolder.getContext().setAuthentication(auth);
	}
	

	/**
	 * @return the suite of tests being tested
	 */
	public static TestSuite suite() {
		return new TestSuite(AppReadingTest.class);
	}
	
	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	public void testRead() {
									
		List<TestCollection> listTestCollection = testCollectionRepository.getAll();
		
		assertTrue("no data found to run test",listTestCollection.size() > 0);
		
		for (TestCollection testCollection : listTestCollection) {
			
			assertTrue(testCollection != null);
			assertTrue(testCollection.getListTest() != null);
			
			// test soft delete management (ie. we are not selecting deleted records)
			for (main.domain.object.Test t : testCollection.getListTest()) {
				assertTrue(t.getDateDelete() == null && t.getUserDelete() == null);
				
				main.domain.object.Test t2 = testRepository.get(t.getId(), "id");
				assertTrue(t2.getDateDelete() == null && t2.getUserDelete() == null);
			}
					
		}					

	}	
	
}
