package interpreter;

public class UnknownInstructionException extends VaimosException {

	private static final long serialVersionUID = 3L;

	public UnknownInstructionException(int line)
	{
		super("Unknown instruction at line " + (line+1));
	}

}
