/**
 * 
 */
package com.tripmaster.tourguide.rewardService.converterDTO;

import java.util.List;

import com.tripmaster.tourguide.rewardService.dto.RewardDTO;
import com.tripmaster.tourguide.rewardService.exceptions.ConverterException;
import com.tripmaster.tourguide.rewardService.model.Reward;

/**
 * Reward-DTO converter.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IRewardConverterDTO extends IConverterEntityToDTO<Reward, RewardDTO> {

	/**
	 * Convert Reward list to RewardDTO list.
	 * @param rewards List
	 * @return List
	 * @throws ConverterException 
	 */
	List<RewardDTO> converterRewardsToDTOs(List<Reward> rewards) throws ConverterException;
}
