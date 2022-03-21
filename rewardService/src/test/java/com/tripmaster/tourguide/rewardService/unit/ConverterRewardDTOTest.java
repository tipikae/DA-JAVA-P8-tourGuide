package com.tripmaster.tourguide.rewardService.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tripmaster.tourguide.rewardService.converterDTO.IAttractionConverterDTO;
import com.tripmaster.tourguide.rewardService.converterDTO.IVisitedLocationConverterDTO;
import com.tripmaster.tourguide.rewardService.converterDTO.RewardDTOConverterImpl;
import com.tripmaster.tourguide.rewardService.dto.AttractionDTO;
import com.tripmaster.tourguide.rewardService.dto.VisitedLocationDTO;
import com.tripmaster.tourguide.rewardService.exceptions.ConverterException;
import com.tripmaster.tourguide.rewardService.model.Attraction;
import com.tripmaster.tourguide.rewardService.model.Reward;
import com.tripmaster.tourguide.rewardService.model.VisitedLocation;

@ExtendWith(MockitoExtension.class)
class ConverterRewardDTOTest {
	
	@Mock
	private IVisitedLocationConverterDTO visitedLocationConverter;
	
	@Mock
	private IAttractionConverterDTO attractionConverter;
	
	@InjectMocks
	private RewardDTOConverterImpl rewardConverter;
	
	private static Reward reward;
	private static VisitedLocation visitedLocation;
	private static Attraction attraction;
	private static List<Reward> rewards;
	
	@BeforeAll
	private static void setUp() {
		attraction = new Attraction();
		visitedLocation = new VisitedLocation();
		reward = new Reward();
		reward.setAttraction(attraction);
		reward.setRewardPoints(100);
		reward.setVisitedLocation(visitedLocation);
		rewards = new ArrayList<>();
		rewards.add(reward);
	}

	@Test
	void converterEntityToDTOReturnsDTOWhenOk() throws ConverterException {
		when(attractionConverter.converterEntityToDTO(any(Attraction.class))).thenReturn(new AttractionDTO());
		when(visitedLocationConverter.converterEntityToDTO(any(VisitedLocation.class)))
			.thenReturn(new VisitedLocationDTO());
		assertEquals(100, rewardConverter.converterEntityToDTO(reward).getRewardPoints());
	}

	@Test
	void converterRewardsToDTOsReturnsListWhenOk() throws ConverterException {
		when(attractionConverter.converterEntityToDTO(any(Attraction.class))).thenReturn(new AttractionDTO());
		when(visitedLocationConverter.converterEntityToDTO(any(VisitedLocation.class)))
			.thenReturn(new VisitedLocationDTO());
		assertEquals(1, rewardConverter.converterRewardsToDTOs(rewards).size());
		assertEquals(100, rewardConverter.converterRewardsToDTOs(rewards).get(0).getRewardPoints());
	}

}
