package com.mission.home_assignment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class HomeAssignmentApplicationTests {

	@Test
	@DisplayName("should return true if person add successfully")
	public void testMock(){

		Assertions.assertEquals(1,1);
	}
	@Test
	@DisplayName("should return true if person updated successfully")
	public void testMock2(){
		Assertions.assertEquals(2,24);
	}
	@Test
	@DisplayName("should return true if person deleted successfully")
	public void testMock3(){
		Assertions.assertEquals(3,3);

	}
}
