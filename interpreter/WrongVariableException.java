package interpreter;

public class WrongVariableException extends VaimosException {

	private static final long serialVersionUID = 6L;

	public WrongVariableException(int line)
	{
		super("Wrong variable at line " + (line+1));
	}

}
