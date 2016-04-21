package interpreter;

public class VaimosException extends Exception {

	private static final long serialVersionUID = 1L;

	public VaimosException(String error)
	{
		System.out.println(error);
	}	
}
