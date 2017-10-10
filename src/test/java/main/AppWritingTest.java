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

import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.domain.object.TestCollection;
import main.domain.repository.ITestCollectionRepository;
import main.domain.repository.ITestRepository;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfigWriting.class })
public class AppWritingTest extends TestCase {
			
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
	public AppWritingTest() {

        User user = new User("flosciale", "flosciale", Arrays.asList(new SimpleGrantedAuthority("ROLE_TEST2")));
        Authentication auth = new TestingAuthenticationToken(user, "password", "ROLE_TEST2");
        SecurityContextHolder.getContext().setAuthentication(auth);
	}
	

	/**
	 * @return the suite of tests being tested
	 */
	public static TestSuite suite() {
		return new TestSuite(AppWritingTest.class);
	}
		
	@Test
	@Rollback(false)
	@Transactional(propagation = Propagation.REQUIRED)
	public void testPopulate() {
		
		TestCollection testCollection = new TestCollection();
		testCollection.setReleaseName("new_release_name");
		testCollection = testCollectionRepository.add(testCollection);
		
		main.domain.object.Test test = new main.domain.object.Test(); // entity 1
		test.setTestCollection(testCollection);
		testCollection.addListTest(test);
			
		main.domain.object.Test test2 = new main.domain.object.Test(); // entity 2
		test2.setTestCollection(testCollection);
		testCollection.addListTest(test2);
				
		testCollection = testCollectionRepository.set(testCollection);
		testCollection = testCollectionRepository.get(1L, "id");
		assertTrue(testCollection != null && testCollection.getListTest().size() == 2);
			
		int i = 0;
		for (main.domain.object.Test t : testCollection.getListTest()) {
			if (i++ % 2 == 0) {
				testRepository.remove(t);
			}
		}		
		
	}
	
	
}
