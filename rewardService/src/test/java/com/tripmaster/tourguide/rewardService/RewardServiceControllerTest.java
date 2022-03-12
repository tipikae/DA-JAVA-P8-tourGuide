package com.tripmaster.tourguide.rewardService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.tripmaster.tourguide.rewardService.controller.RewardServiceController;
import com.tripmaster.tourguide.rewardService.service.IRewardServiceService;

@WebMvcTest(RewardServiceController.class)
class RewardServiceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IRewardServiceService rewardService;

	@Test
	void getAttractionRewardPointsReturnsIntWhenOk() throws Exception {
		int ret = 10;
		when(rewardService.getAttractionRewardPoints(any(UUID.class), any(UUID.class))).thenReturn(ret);
		mockMvc.perform(get("/rewardCentral/points")
				.param("attractionId", UUID.randomUUID().toString())
				.param("userId", UUID.randomUUID().toString()))
			.andExpect(status().isOk())
			.andExpect(content().string(Integer.toString(ret)));
	}

}
