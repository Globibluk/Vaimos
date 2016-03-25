package interpreter;

public class WrongCodeException extends VaimosException {
	
	public WrongCodeException(int faultyLine)
	{
		super("Wrong code at line " + faultyLine);
	}
}
