package interpreter;

public class NoEndException extends VaimosException {

	private static final long serialVersionUID = 2L;

	public NoEndException(String line)
	{	
		super("Instruction at line " + (line+1) + " got no end");
	}

}