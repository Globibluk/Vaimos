package interpreter;

public class NoEndException extends VaimosException {

	public NoEndException(String line)
	{	
		super("Instruction at line " + line + " got no end");
	}

}
