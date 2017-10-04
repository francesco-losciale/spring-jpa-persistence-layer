package main;


import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dto.TestCollection;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.repository.ITestCollectionManager;
import main.repository.ITestManager;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class AppTest2 extends TestCase {
			
	@Autowired
	private ITestCollectionManager testCollectionManager;
	
	@Autowired
	private ITestManager testManager;
	
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest2() {

        User user = new User("flosciale", "flosciale", Arrays.asList(new SimpleGrantedAuthority("ROLE_TEST2")));
        Authentication auth = new TestingAuthenticationToken(user, "password", "ROLE_TEST2");
        SecurityContextHolder.getContext().setAuthentication(auth);
	}
	

	/**
	 * @return the suite of tests being tested
	 */
	public static TestSuite suite() {
		return new TestSuite(AppTest2.class);
	}
		
	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(false)
	public void testPopulateListCollection() {
		
		TestCollection testCollection = new TestCollection();
		testCollection.setReleaseName("new_release_name");
		testCollection = testCollectionManager.add(testCollection);
		
		com.dto.Test test = new com.dto.Test(); // entity 1
		test.setTestCollection(testCollection);
		testCollection.addListTest(test);
		test = testManager.add(test); 
		
		assertTrue(test != null);
		assertTrue(test.getTestCollection() != null && test.getTestCollection().getListTest().size() == 1);
		
		com.dto.Test test2 = new com.dto.Test(); // entity 2
		test2.setTestCollection(testCollection);
		testCollection.addListTest(test);
		test2 = testManager.add(test2); 
		
		assertTrue(test2 != null);
		assertTrue(test2.getTestCollection() != null && test2.getTestCollection().getListTest().size() == 2);
				
		// check number of tests fetching from collection
		
		testCollection = testCollectionManager.get(1L);
		assertTrue(testCollection != null);
		assertTrue(testCollection.getListTest() != null && testCollection.getListTest().size() == 2);
		
		
		// commit ...		
		
	}
	
}
