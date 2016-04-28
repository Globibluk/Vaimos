package interpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Loader {
	
	private Interpreter interpreter;
	
	public Loader(Interpreter interpreter)
	{
		this.interpreter = interpreter;
	}
	
	public void load(File file) throws IOException
	{
		try (BufferedReader br = new BufferedReader(new FileReader(file)))
		{
		    String line;
		    while ((line = br.readLine()) != null) {
		       String[] frags = line.split(" ");
		       ArrayList<String> args = new ArrayList<String>();
		       for(int i=1;i<frags.length;i++) args.add(frags[i]);
		       interpreter.addInstruction(new Instruction(frags[0], args));
		    }   
		}
		catch (Exception e)
		{
			System.out.println("Can't load file");
		}		
	}
}