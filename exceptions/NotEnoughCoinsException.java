package exceptions;

public class NotEnoughCoinsException extends CommandExecuteException {

	public NotEnoughCoinsException(String message) {
		super(message);
	}

}
