package interpreter;

public class WrongIfException extends VaimosException {

	public WrongIfException(int line)
	{
		super("Wrong if at line " + line);
	}

}
