package tourGuide;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tourGuide.clients.IGpsServiceClient;
import tourGuide.clients.IRewardServiceClient;
import tourGuide.clients.IUserServiceClient;
import tourGuide.dto.NewPreferenceDTO;
import tourGuide.dto.NewUserDTO;
import tourGuide.exception.AlreadyExistException;
import tourGuide.exception.BadRequestException;
import tourGuide.exception.HttpException;
import tourGuide.exception.NotFoundException;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class TourGuideIT {
	
	@Autowired
	private IGpsServiceClient gpsClient;
	
	@Autowired
	private IRewardServiceClient rewardClient;
	
	@Autowired
	private IUserServiceClient userClient;
	
	private static String userName;
	private static UUID userId;
	private static NewPreferenceDTO newPreferenceDTO;
	private static NewUserDTO newUserDTO;
	
	@BeforeAll
	private static void setUp() {
		userName = "username0";
		userId = UUID.randomUUID();
		newPreferenceDTO = new NewPreferenceDTO();
		newPreferenceDTO.setNumberOfAdults(2);
		newPreferenceDTO.setNumberOfChildren(2);
		newPreferenceDTO.setTicketQuantity(4);
		newPreferenceDTO.setTripDuration(10);
		newUserDTO = new NewUserDTO();
		newUserDTO.setUserId(userId);
		newUserDTO.setUserName(userName);
		newUserDTO.setEmailAddress("email");
		newUserDTO.setPhoneNumber("phone");
	}

	@Test
	@Order(1)
	void getUserLocationReturns404WhenNotFound() {
		assertThrows(NotFoundException.class, () -> gpsClient.getUserLocation(userName));
	}

	@Test
	@Order(2)
	void getNearByAttractionsReturns404WhenNotFound() {
		assertThrows(NotFoundException.class, () -> gpsClient.getNearByAttractions(userName));
	}

	@Test
	@Order(3)
	void getUserRewardsReturns404WhenNotFound() {
		assertThrows(NotFoundException.class, () -> rewardClient.getUserRewards(userName));
	}

	@Test
	@Order(4)
	void getTripDealsReturns404WhenNotFound() {
		assertThrows(NotFoundException.class, () -> userClient.getTripDeals(userName));
	}

	@Test
	@Order(5)
	void updatePreferencesReturns404WhenNotFound() {
		assertThrows(NotFoundException.class, () -> userClient.updatePreferences(userName, newPreferenceDTO));
	}

	@Test
	@Order(6)
	void addUserReturnsUserWhenOk() throws AlreadyExistException, BadRequestException, HttpException {
		assertEquals(userName, userClient.addUser(newUserDTO).getUserName());
	}

	@Test
	@Order(7)
	void addUserReturns405WhenAlreadyExists() {
		assertThrows(AlreadyExistException.class, () -> userClient.addUser(newUserDTO));
	}

	@Test
	@Order(8)
	void updatePreferencesWhenOk() throws NotFoundException, BadRequestException, HttpException {
		userClient.updatePreferences(userName, newPreferenceDTO);
		assertTrue(true);
	}

	@Test
	@Order(9)
	void getUserLocationReturnsVisitedLocationWhenOk() 
			throws NotFoundException, BadRequestException, HttpException {
		assertTrue(gpsClient.getUserLocation(userName).getUserId().equals(userId));
	}

	@Test
	@Order(10)
	void getAllUsersLastLocationReturnsListWhenOk() throws HttpException {
		assertTrue(gpsClient.getAllUsersLastLocation().size() > 0);
	}

	@Test
	@Order(11)
	void getNearByAttractionsReturnsListWhenOk() 
			throws NotFoundException, BadRequestException, HttpException {
		assertTrue(gpsClient.getNearByAttractions(userName).size() > 0);
	}

	@Test
	@Order(12)
	void getUserRewardsReturnsListWhenOk() 
			throws NotFoundException, BadRequestException, HttpException {
		assertTrue(rewardClient.getUserRewards(userName).size() > 0);
	}

	@Test
	@Order(13)
	void getTripDealsReturnsListWhenOk() 
			throws NotFoundException, BadRequestException, HttpException {
		assertTrue(userClient.getTripDeals(userName).size() > 0);
	}

}
