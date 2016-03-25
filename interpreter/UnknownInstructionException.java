package interpreter;

public class UnknownInstructionException extends VaimosException {

	public UnknownInstructionException(int line)
	{
		super("Unknown instruction at line " + line);
	}

}
