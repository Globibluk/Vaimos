package interpreter;

public class WrongIfException extends VaimosException {

	private static final long serialVersionUID = 5L;

	public WrongIfException(int line)
	{
		super("Wrong if at line " + (line+1));
	}

}