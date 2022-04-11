/**
 * 
 */
package tourGuide.exception;

/**
 * Already exists exception.
 * @author tipikae
 * @version 1.0
 *
 */
public class AlreadyExistException extends HttpException {

	private static final long serialVersionUID = 1L;

	public AlreadyExistException(String arg0) {
		super(arg0);
	}

}
