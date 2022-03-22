package com.tripmaster.tourguide.rewardService.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tripmaster.tourguide.rewardService.clients.IUserServiceClient;
import com.tripmaster.tourguide.rewardService.exceptions.HttpException;
import com.tripmaster.tourguide.rewardService.model.User;
import com.tripmaster.tourguide.rewardService.remoteServices.IUserService;
import com.tripmaster.tourguide.rewardService.remoteServices.UserServiceImpl;

import feign.FeignException;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	@Mock
	private IUserServiceClient userClient;
	
	@InjectMocks
	private IUserService userService = new UserServiceImpl();

	@Test
	void getUserReturnsUserWhenOk() throws HttpException {
		User user = new User();
		user.setUserName("username");
		when(userClient.getUser(anyString())).thenReturn(user);
		assertEquals(user.getUserName(), userService.getUser("username").getUserName());
	}

	@Test
	void getUserThrowsExceptionWhenError() throws HttpException {
		doThrow(FeignException.class).when(userClient).getUser(anyString());
		assertThrows(HttpException.class, () -> userService.getUser("username"));
	}

	@Test
	void getUserIdReturnsIdWhenOk() throws HttpException {
		UUID userId = UUID.randomUUID();
		when(userClient.getUserId(anyString())).thenReturn(userId);
		assertEquals(userId, userService.getUserId("username"));
	}

	@Test
	void getUserIdThrowsExceptionWhenError() throws HttpException {
		doThrow(FeignException.class).when(userClient).getUserId(anyString());
		assertThrows(HttpException.class, () -> userService.getUserId("username"));
	}

}
