/**
 * 
 */
package com.tripmaster.tourguide.userService.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * Custom feign error decoder.
 * @author tipikae
 * @version 1.0
 *
 */
public class MyFeignErrorDecoder implements ErrorDecoder {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyFeignErrorDecoder.class);

	@Override
	public Exception decode(String methodKey, Response response) {
		LOGGER.debug("decode: error: methodKey=" + methodKey + ", response=" + response);
		return new HttpClientException("methodKey=" + methodKey + ", response=" + response);
	}

}
