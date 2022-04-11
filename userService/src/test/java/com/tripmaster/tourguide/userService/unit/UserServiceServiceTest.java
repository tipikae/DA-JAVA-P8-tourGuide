package com.tripmaster.tourguide.userService.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tripmaster.tourguide.userService.clients.IRewardServiceClient;
import com.tripmaster.tourguide.userService.converterDTO.IPreferenceConverterDTO;
import com.tripmaster.tourguide.userService.converterDTO.IUserConverterDTO;
import com.tripmaster.tourguide.userService.dto.NewPreferenceDTO;
import com.tripmaster.tourguide.userService.dto.NewUserDTO;
import com.tripmaster.tourguide.userService.dto.UserDTO;
import com.tripmaster.tourguide.userService.exceptions.ConverterException;
import com.tripmaster.tourguide.userService.exceptions.HttpException;
import com.tripmaster.tourguide.userService.exceptions.HttpUserNotFoundException;
import com.tripmaster.tourguide.userService.exceptions.UserAlreadyExistsException;
import com.tripmaster.tourguide.userService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.userService.model.Preference;
import com.tripmaster.tourguide.userService.model.User;
import com.tripmaster.tourguide.userService.repository.IUserRepository;
import com.tripmaster.tourguide.userService.service.UserServiceServiceImpl;

import tripPricer.Provider;
import tripPricer.TripPricer;

@ExtendWith(MockitoExtension.class)
class UserServiceServiceTest {
	
	@Mock
	private IUserRepository userRepository;
	
	@Mock
	private TripPricer tripPricer;
	
	@Mock
	private IRewardServiceClient rewardService;
	
	@Mock
	private IUserConverterDTO userDTOConverter;
	
	@Mock
	private IPreferenceConverterDTO preferenceDTOConverter;
	
	@InjectMocks
	private UserServiceServiceImpl userService;
	
	private static String username;
	private static UUID userId;
	private static User user;
	private static NewUserDTO newUserDTO;
	private static UserDTO userDTO;
	private static Provider provider;
	private static List<Provider> providers;
	private static List<User> users;
	private static List<UserDTO> userDTOs;
	
	@BeforeAll
	private static void setUp() {
		username = "username";
		userId = UUID.randomUUID();
		user = new User(userId, username, "phone", "email");
		newUserDTO = new NewUserDTO(UUID.randomUUID(), username, "phone", "email");
		users = new ArrayList<>();
		users.add(user);
		provider = new Provider(UUID.randomUUID(), "name", 1000);
		providers = new ArrayList<>();
		providers.add(provider);
		userDTO = new UserDTO();
		userDTO.setUserName(username);
		userDTO.setEmailAddress("email");
		userDTOs = new ArrayList<>();
		userDTOs.add(userDTO);
	}
	
	@Test
	void addUserReturnsUserWhenOk() throws UserAlreadyExistsException, ConverterException {
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
		when(userDTOConverter.converterDTOToEntity(any(NewUserDTO.class))).thenReturn(user);
		when(userRepository.save(any(User.class))).thenReturn(user);
		when(userDTOConverter.converterEntityToDTO(any(User.class))).thenReturn(userDTO);
		assertEquals(username, userService.addUser(newUserDTO).getUserName());
	}
	
	@Test
	void addUserThrowsExceptionWhenUserAlreadyExists() {
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
		assertThrows(UserAlreadyExistsException.class, () -> userService.addUser(newUserDTO));
	}
	
	@Test
	void addUserThrowsExceptionWhenConverterError() throws ConverterException {
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
		doThrow(ConverterException.class).when(userDTOConverter).converterDTOToEntity(any(NewUserDTO.class));
		assertThrows(ConverterException.class, () -> userService.addUser(newUserDTO));
	}
	
	@Test
	void getUserReturnsUserWhenOk() throws UserNotFoundException, ConverterException {
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
		when(userDTOConverter.converterEntityToDTO(any(User.class))).thenReturn(userDTO);
		assertEquals("email", userService.getUser(username).getEmailAddress());
	}
	
	@Test
	void getUserThrowsExceptionWhenUserNotFound() {
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> userService.getUser(username));
	}
	
	@Test
	void getAllUsersReturnsListWhenOk() throws ConverterException {
		when(userRepository.findAll()).thenReturn(users);
		when(userDTOConverter.converterEntitiesToDTOs(users)).thenReturn(userDTOs);
		assertTrue(userService.getAllUsers().size() > 0);
	}
	
	@Test
	void updatePreferencesCallsSaveWhenOk() throws UserNotFoundException, ConverterException {
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
		when(preferenceDTOConverter.converterDTOToEntity(any(NewPreferenceDTO.class)))
			.thenReturn(new Preference());
		userService.updatePreferences(username, new NewPreferenceDTO());
		Mockito.verify(userRepository).save(any(User.class));
	}
	
	@Test
	void updatePreferencesThrowsExceptionWhenUserNotFound() {
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, 
				() -> userService.updatePreferences(username, new NewPreferenceDTO()));
	}
	
	@Test
	void updatePreferencesThrowsExceptionWhenConverterError() throws ConverterException {
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
		doThrow(ConverterException.class).when(preferenceDTOConverter)
			.converterDTOToEntity(any(NewPreferenceDTO.class));
		assertThrows(ConverterException.class, 
				() -> userService.updatePreferences(username, new NewPreferenceDTO()));
	}
	
	@Test
	void getTripDealsReturnsListWhenOk() throws UserNotFoundException, HttpException {
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
		when(rewardService.getUserRewardsPoints(any(UUID.class))).thenReturn(100);
		when(tripPricer.getPrice(anyString(), any(UUID.class), anyInt(), anyInt(), anyInt(), anyInt()))
			.thenReturn(providers);
		assertTrue(userService.getTripDeals(username).size() > 0);
		
	}
	
	@Test
	void getTripDealsThrowsExceptionWhenUserNotFound() {
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> userService.getTripDeals(username));
	}
	
	@Test
	void getTripDealsThrowsExceptionWhenClientError() throws HttpException {
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
		doThrow(HttpUserNotFoundException.class).when(rewardService).getUserRewardsPoints(any(UUID.class));
		assertThrows(HttpException.class, () -> userService.getTripDeals(username));
	}
	
	@Test
	void getUserIdReturnsUUIDWhenOk() throws UserNotFoundException {
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
		assertEquals(userId, userService.getUserId(anyString()));
	}
	
	@Test
	void getUserIdThrowsExceptionWhenUserNotFound() {
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> userService.getUserId(username));
	}
	
	@Test
	void getAllUserIdsReturnsIdsWhenOk() {
		when(userRepository.findAll()).thenReturn(users);
		assertEquals(user.getUserId(), userService.getAllUserIds().get(0));
	}

}
