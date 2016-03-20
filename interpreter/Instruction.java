package interpreter;

import java.util.ArrayList;

public class Instruction {
	
	private String name;
	private ArrayList<String> args;
	
	public Instruction(String name, ArrayList<String> args)
	{
		this.name = name;
		this.args = args;
	}
	
	public String getName()
	{
		return name;
	}

	public ArrayList<String> getArgs()
	{
		return args;
	}
	
	public String toString()
	{
		String s = name;
		for(String arg : args) s += " " + arg;
		s += "\n";
		return s;
	}
}
