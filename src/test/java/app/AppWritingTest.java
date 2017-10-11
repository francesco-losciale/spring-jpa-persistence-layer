package app;


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

import domain.model.TestCollection;
import domain.util.DomainModelFactory;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import persistence.repository.TestCollectionRepository;
import persistence.repository.TestRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfigWriting.class })
public class AppWritingTest extends TestCase {
			
	@Autowired 
	private DomainModelFactory modelFactory;
	
	@Autowired
	private TestCollectionRepository testCollectionRepository;
	
	@Autowired
	private TestRepository testRepository;
	
	
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
		
		int count = 1;		
		while (count <= 20) 
		{
			
			TestCollection testCollection = modelFactory.createTestCollection("new_release_name_" + count);
			testCollection = testCollectionRepository.add(testCollection);
			
			modelFactory.createTest(testCollection); // entity 1		
			modelFactory.createTest(testCollection); // entity 2
					
			testCollection = testCollectionRepository.set(testCollection);
			testCollection = testCollectionRepository.get(count, "id");
			assertTrue(testCollection != null && testCollection.getListTest().size() == 2);
				
			int i = 0;
			for (domain.model.Test t : testCollection.getListTest()) {
				if (i++ % 2 == 0) {
					testRepository.remove(t);
				}
			}		
			
			count++;
		}
	}
	
	
}
