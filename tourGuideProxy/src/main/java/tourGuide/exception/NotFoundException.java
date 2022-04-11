/**
 * 
 */
package tourGuide.exception;

/**
 * Not found exception.
 * @author tipikae
 * @version 1.0
 *
 */
public class NotFoundException extends HttpException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(String arg0) {
		super(arg0);
	}

}
