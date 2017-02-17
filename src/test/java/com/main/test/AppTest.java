package com.main.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dto.TestCollectionDTO;
import com.dto.TestDTO;
import com.dto.UserDetailsDTO;
import com.manager.ITestCollectionManager;
import com.manager.ITestManager;

import commons.model.bean.OperationMetadata;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
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
		AppInfo.setUserDetails(new UserDetailsDTO());
		AppInfo.getUserDetails().setFirstName("Francesco");
		AppInfo.getUserDetails().setLastName("Losciale");
		AppInfo.getUserDetails().setUsername("flosciale");
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static TestSuite suite() {
		return new TestSuite(AppTest.class);
	}

	//@Test
	@Transactional(propagation = Propagation.REQUIRED)
	public void testApp() {
		assertTrue(true);
		
		TestDTO newTest = new TestDTO();
		newTest = testManager.create(newTest, metadata);
		newTest = testManager.read(newTest.getId(), metadata);
		assertTrue(newTest != null);
		
		testManager.delete(newTest, metadata);
		newTest = testManager.read(newTest.getId(), metadata);
		assertTrue(newTest == null);		
	}
	
	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(false)
	public void testCollection() {
		
		TestDTO testDTO = new TestDTO();
		TestCollectionDTO testCollectionDTO = new TestCollectionDTO();
		testCollectionDTO.setReleaseName("releaseName");
		List<TestDTO> listTest = new ArrayList<TestDTO>();
		testDTO.setTestCollectionDTO(testCollectionDTO);
		listTest.add(testDTO);
		testCollectionDTO.setListTest(listTest);
		testCollectionDTO = testCollectionManager.create(testCollectionDTO, metadata);
				
	}
}
