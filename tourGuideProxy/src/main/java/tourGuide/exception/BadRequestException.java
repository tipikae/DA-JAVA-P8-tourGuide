/**
 * 
 */
package tourGuide.exception;

/**
 * Bad request exception.
 * @author tipikae
 * @version 1.0
 *
 */
public class BadRequestException extends HttpException {

	private static final long serialVersionUID = 1L;

	public BadRequestException(String arg0) {
		super(arg0);
	}

}
