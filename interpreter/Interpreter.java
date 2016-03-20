package interpreter;

import java.util.ArrayList;

public class Interpreter {
	
	private ArrayList<Instruction> instructions;
	
	public Interpreter()
	{
		instructions = new ArrayList<Instruction>();
	}
	
	public void addInstruction(Instruction i)
	{
		instructions.add(i);
	}
	
	public String toString()
	{
		String s = "";
		for(Instruction i : instructions) s += i.toString();
		return s;
	}

}
