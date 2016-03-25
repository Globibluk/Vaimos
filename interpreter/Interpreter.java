package interpreter;

import java.util.ArrayList;

public class Interpreter extends Thread{
	
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
		String name;
		String firstOp;
		String secondOp;
		Double value;
		String s;
		Instruction i;
		
		for(int l=0;l<instructions.size();l++)
		{
			line++;
			i = instructions.get(l);
			
			switch(i.getName())
			{
				case "":
					break;
			
				case "value":
					double valDouble = Double.parseDouble(i.getArgs().get(2));
					dataStructure.setValue(i.getArgs().get(0), valDouble);
					break;
					
				case "+":
					name = i.getArgs().get(0);
					firstOp = i.getArgs().get(1);
					secondOp = i.getArgs().get(2);
				
					if(dataStructure.getValue(firstOp) != null)
						value = (double) (dataStructure.getValue(firstOp));
					else
						value = Double.parseDouble(firstOp);
				
					if(dataStructure.getValue(secondOp) != null)
						value += (double) dataStructure.getValue(secondOp);
					else
						value += Double.parseDouble(secondOp);
				
					dataStructure.setValue(name, value);
					break;
					
				case "-":
					name = i.getArgs().get(0);
					firstOp = i.getArgs().get(1);
					secondOp = i.getArgs().get(2);
				
					if(dataStructure.getValue(firstOp) != null)
						value = (double) (dataStructure.getValue(firstOp));
					else
						value = Double.parseDouble(firstOp);
				
					if(dataStructure.getValue(secondOp) != null)
						value -= (double) dataStructure.getValue(secondOp);
					else
						value -= Double.parseDouble(secondOp);
				
					dataStructure.setValue(name, value);
					break;
					
				case "*":
					name = i.getArgs().get(0);
					firstOp = i.getArgs().get(1);
					secondOp = i.getArgs().get(2);
				
					if(dataStructure.getValue(firstOp) != null)
						value = (double) (dataStructure.getValue(firstOp));
					else
						value = Double.parseDouble(firstOp);
				
					if(dataStructure.getValue(secondOp) != null)
						value *= (double) dataStructure.getValue(secondOp);
					else
						value *= Double.parseDouble(secondOp);
				
					dataStructure.setValue(name, value);
					break;
					
				case "/":
					name = i.getArgs().get(0);
					firstOp = i.getArgs().get(1);
					secondOp = i.getArgs().get(2);
				
					if(dataStructure.getValue(firstOp) != null)
						value = (double) (dataStructure.getValue(firstOp));
					else
						value = Double.parseDouble(firstOp);
				
					if(dataStructure.getValue(secondOp) != null)
						value /= (double) dataStructure.getValue(secondOp);
					else
						value /= Double.parseDouble(secondOp);
				
					dataStructure.setValue(name, value);
					break;
					
				case "print":
					s = "";
					s += dataStructure.getValue(i.getArgs().get(0));
					System.out.print(s);
					break;
					
				case "println":
					System.out.println();
					break;
					
				default:
					new UnknownInstructionException(line);
					break;
					
			}
		}
	}
}
