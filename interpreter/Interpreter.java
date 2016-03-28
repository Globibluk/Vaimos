package interpreter;

import java.util.ArrayList;

public class Interpreter extends Thread {
	
	private ArrayList<Instruction> instructions;
	private DataStructure dataStructure;
	
	private int line;
	
	public Interpreter()
	{
		instructions = new ArrayList<Instruction>();
		dataStructure =  new DataStructure();
		line = -1;
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
	
	public void run()
	{
		try
		{
			execute();
		} catch (Exception e)
		{
			if(e instanceof NumberFormatException)
				new WrongVariableException(line);
			if(e instanceof IndexOutOfBoundsException)
				new WrongCodeException(line);
			if(e.getMessage() == "Unknown instruction at line " + line)
				new UnknownInstructionException(line);				
			else
				e.printStackTrace();
		}
	}
		
	public void execute()
	{
		ArrayList<String> args;
		Double value0, value1;
		String s;
		Instruction i;
		
		for(int l=0;l<instructions.size();l++)
		{
			line++;
			i = instructions.get(l);
			args = i.getArgs();
			switch(i.getName())
			{
				case "":
					break;
			
				case "value":
					// gérer le "="
					double valDouble = Double.parseDouble(args.get(2));
					dataStructure.setValue(args.get(0), valDouble);
					break;
					
				case "+": 
				case "-":
				case "*":
				case "/":
					if(dataStructure.getValue(args.get(1)) != null)
						value0 = (double) (dataStructure.getValue(args.get(1)));
					else
						value0 = Double.parseDouble(args.get(1));
				
					if(dataStructure.getValue(args.get(2)) != null)
						value1 = (double) dataStructure.getValue(args.get(2));
					else
						value1 = Double.parseDouble(args.get(2));
				
					switch(i.getName())
					{
						case "+":
							value0 += value1;
							break;
						case "-":
							value0 -= value1;
							break;
						case "*":
							value0 *= value1;
							break;
						case "/":
							value0 /= value1;
							break;
					}
					dataStructure.setValue(args.get(0), value0);
					break;
					
				case "while":
					
					if(dataStructure.getValue(args.get(0)) != null)
						value0 = (double) (dataStructure.getValue(args.get(0)));
					else
						value0 = Double.parseDouble(args.get(0));
				
					if(dataStructure.getValue(args.get(2)) != null)
						value1 = (double) dataStructure.getValue(args.get(2));
					else
						value1 = Double.parseDouble(args.get(2));
					
					switch(args.get(1))
					{
						case "==":
							
						case "<":
						case "<=":
						case ">":
						case ">=":		
					}
					break;
					
				case "print":
				case "println":
					s = "";
					s += dataStructure.getValue(i.getArgs().get(0));
					if(i.getName() == "print") 
						System.out.print(s);
					else 
						System.out.println(s);
					break;
					
				default:
					new UnknownInstructionException(line);
					break;
					
			}
		}
	}
	
	public void gotoEnd(int line)
	{
		Instruction i;
		ArrayList<String> args;
		
		for(int l=line;l<instructions.size();l++)
		{
			line++;
			i = instructions.get(l);
		}
			
	}
}
