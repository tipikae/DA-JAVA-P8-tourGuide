package com.tripmaster.tourguide.rewardsCentral;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.tripmaster.tourguide.rewardsCentral.controller.RewardCentralController;
import com.tripmaster.tourguide.rewardsCentral.service.IRewardCentralService;

@WebMvcTest(RewardCentralController.class)
class RewardCentralControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IRewardCentralService rewardService;

	@Test
	void getAttractionRewardPointsReturnsIntWhenOk() throws Exception {
		int ret = 10;
		when(rewardService.getAttractionRewardPoints(any(UUID.class), any(UUID.class))).thenReturn(ret);
		mockMvc.perform(get("/rewardCentral/points")
				.param("attractionId", UUID.randomUUID().toString())
				.param("userId", UUID.randomUUID().toString()))
			.andExpect(status().isOk());
	}

}
