package interpreter;

public class WrongCodeException extends VaimosException {
	
	private static final long serialVersionUID = 4L;

	public WrongCodeException(int faultyLine)
	{
		super("Wrong code at line " + (faultyLine+1));
	}
}