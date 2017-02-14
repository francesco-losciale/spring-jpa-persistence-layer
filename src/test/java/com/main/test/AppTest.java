package com.main.test;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dto.TestDTO;
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

	OperationMetadata metadata = new OperationMetadata("test",Locale.getDefault());
	
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest() {
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static TestSuite suite() {
		return new TestSuite(AppTest.class);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(false)
	public void testApp() {
		assertTrue(true);
		
		TestDTO test = testManager.read(1L, metadata);
		assertTrue(test.getId() == 1L);
		
		TestDTO newTest = new TestDTO();
		newTest = testManager.create(newTest, metadata);
		newTest = testManager.read(newTest.getId(), metadata);
		assertTrue(newTest != null);
		
		testManager.delete(newTest, metadata);
		newTest = testManager.read(newTest.getId(), metadata);
		assertTrue(newTest == null);
		
	}
}
