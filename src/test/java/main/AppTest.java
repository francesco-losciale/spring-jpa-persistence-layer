package main;


import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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

import com.dto.TestCollectionDTO;
import com.dto.TestDTO;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.repository.ITestCollectionManager;
import main.repository.ITestManager;
import persistence2.helpers.bean.OperationMetadata;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class AppTest extends TestCase {
		
	@Autowired 
	private ITestManager testManager;
	
	@Autowired
	private ITestCollectionManager testCollectionManager;
	
	OperationMetadata metadata = new OperationMetadata("test",Locale.getDefault());
	
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest() {

        User user = new User("flosciale", "flosciale", Arrays.asList(new SimpleGrantedAuthority("ROLE_TEST2")));
        Authentication auth = new TestingAuthenticationToken(user, "password", "ROLE_TEST2");
        SecurityContextHolder.getContext().setAuthentication(auth);
	}
	

	/**
	 * @return the suite of tests being tested
	 */
	public static TestSuite suite() {
		return new TestSuite(AppTest.class);
	}
		
//	@Test
//	@Transactional(propagation = Propagation.REQUIRED)
//	@Rollback(false)
//	public void testPopulateListCollection() {
//		
//		// controllo create
//		
//		TestCollectionDTO testCollectionDTO = newTestCollectionDTO("releaseName3");
//		
//		//@Secured({"ROLE_TEST"}) <-- must be set upon create definition 
//		testCollectionDTO = testCollectionManager.create(testCollectionDTO, metadata);
//				
//		TestDTO testDTO = newTestDTO(); // entity 1
//		addTestDTO(testDTO, testCollectionDTO);
//		testDTO = testManager.create(testDTO, metadata); 
//		
//		assertTrue(testDTO != null);
//		assertTrue(testDTO.getTestCollection() != null && testDTO.getTestCollection().getListTest().size() == 1);
//		
//		TestDTO test2DTO = newTestDTO(); // entity 2
//		addTestDTO(test2DTO, testCollectionDTO);
//		test2DTO = testManager.create(test2DTO, metadata);
//		
//		assertTrue(test2DTO != null);
//		assertTrue(test2DTO.getTestCollection() != null && test2DTO.getTestCollection().getListTest().size() == 2);
//				
//		// commit ...
//	}
//	
//	
//	@Test
//	@Transactional(propagation = Propagation.REQUIRED)
//	@Rollback(false)
//	public void testCheckAfterPopulate() {
//						
//		List<TestCollectionDTO> listTestCollectionDTO = testCollectionManager.list(metadata);
//		
//		if (listTestCollectionDTO.size() > 0) {
//			
//			Long idCollection = listTestCollectionDTO.get(0).getId();
//		
//			TestCollectionDTO testCollectionDTO = testCollectionManager.read(idCollection, metadata); 
//			TestDTO[] testDtoArray = testCollectionDTO.getListTest().toArray(new TestDTO[testCollectionDTO.getListTest().size()]);
//			
//			int count = testCollectionDTO.getListTest().size();
//			int countRemoved = 0;
//			if (testDtoArray.length > 0) {
//				for (int i = 0; i < testDtoArray.length; i++) {
//					if (i % 2 == 0) {
//						testCollectionDTO.getListTest().remove(testDtoArray[i]); // orphan removal
//						countRemoved++;
//					} 
//				}
//			}
//			
//			//TODO: the entity that's being updated is detached as a result of the dto-to-entity conversion
//			//      but shouldn't be needed to update explicitly the entity, because it should be managed at the end JTA commit
//			//TODO: try to remove this line, could be unnecessary
//			testCollectionDTO = testCollectionManager.update(testCollectionDTO, metadata); 
//			assertTrue(testCollectionDTO.getListTest().size() == count - countRemoved);
//			
//		} else {
//			fail("no record data");
//		}
//	}	
//	
//	@Test
//	@Transactional(propagation = Propagation.REQUIRED)
//	@Rollback(false)
//	public void testUpdatingData() {
//		
//		List<TestCollectionDTO> listTestCollectionDTO = testCollectionManager.list(metadata);
//		if (listTestCollectionDTO.size() > 0) {
//			
//			for (TestCollectionDTO testCollectionDTO : listTestCollectionDTO) {				
//				testCollectionDTO.setReleaseName(testCollectionDTO.getReleaseName()+"_upd");
//				testCollectionDTO = testCollectionManager.update(testCollectionDTO, metadata); 				
//			}			
//			
//		} else {
//			fail("no record data");
//		}
//		
//	}
//	
//	private static TestCollectionDTO newTestCollectionDTO(String releaseName) {
//		TestCollectionDTO testCollectionDTO = new TestCollectionDTO();
//		testCollectionDTO.setReleaseName(releaseName);
//		return testCollectionDTO;
//	}
//	
//	private static TestDTO newTestDTO() {
//		return new TestDTO();
//	}
//	
//	private static void addTestDTO(TestDTO testDTO, TestCollectionDTO testCollectionDTO) {
//		testDTO.setTestCollection(testCollectionDTO);
//		testCollectionDTO.addTest(testDTO);
//	}
}
