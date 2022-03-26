/**
 * 
 */
package com.tripmaster.tourguide.rewardService.converterDTO;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tripmaster.tourguide.rewardService.dto.RewardDTO;
import com.tripmaster.tourguide.rewardService.exceptions.ConverterException;
import com.tripmaster.tourguide.rewardService.model.Reward;

/**
 * Reward-DTO converter.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class RewardDTOConverterImpl implements IRewardConverterDTO {

	private static final Logger LOGGER = LoggerFactory.getLogger(RewardDTOConverterImpl.class);
	
	@Autowired
	private IVisitedLocationConverterDTO visitedLocationConverter;
	
	@Autowired
	private IAttractionConverterDTO attractionConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RewardDTO converterEntityToDTO(Reward entity) throws ConverterException {
		RewardDTO rewardDTO = new RewardDTO();
		
		try {
			rewardDTO.setAttraction(attractionConverter.converterEntityToDTO(entity.getAttraction()));
			rewardDTO.setRewardPoints(entity.getRewardPoints());
			rewardDTO.setVisitedLocation(visitedLocationConverter
					.converterEntityToDTO(entity.getVisitedLocation()));
		} catch (Exception e) {
			LOGGER.debug("converterEntityToDTO: exception: " + e.getClass().getSimpleName() 
					+ ", error: " + e.getMessage());
			throw new ConverterException(e.getMessage());
		}
		
		return rewardDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RewardDTO> converterRewardsToDTOs(List<Reward> rewards) throws ConverterException {
		List<RewardDTO> rewardDTOs = new ArrayList<>();
		
		for(Reward reward: rewards) {
			rewardDTOs.add(converterEntityToDTO(reward));
		}
		
		return rewardDTOs;
	}

}
