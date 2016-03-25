package interpreter;

public class WrongVariableException extends VaimosException {

	public WrongVariableException(int line)
	{
		super("Wrong variable at line " + line);
	}

}
