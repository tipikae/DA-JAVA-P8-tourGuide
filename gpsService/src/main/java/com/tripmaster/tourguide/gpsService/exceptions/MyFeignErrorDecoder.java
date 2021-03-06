package com.tripmaster.tourguide.gpsService.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * Custom Feign error decoder.
 * @author tipikae
 * @version 1.0
 *
 */
public class MyFeignErrorDecoder implements ErrorDecoder {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyFeignErrorDecoder.class);
	
	private final ErrorDecoder defaultErrorDecoder = new Default();

	@Override
	public Exception decode(String methodKey, Response response) {
		LOGGER.debug("decode: error: methodKey=" + methodKey + ", status=" + response.status());
		
		if (response.status() >= 400 && response.status() <= 599) {
			switch (response.status()) {
			case 400:
				return new HttpBadRequestException(response.status() + ": " + response.reason());
			case 404:
				return new HttpUserNotFoundException(response.status() + ": " + response.reason());
			default:
				return new HttpException(response.status() + ": " + response.reason());
			}
		}
		
		return defaultErrorDecoder.decode(methodKey, response);
	}

}
