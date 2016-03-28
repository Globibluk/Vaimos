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
		line = 0;
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
		ArrayList<Integer> lastLoop = new ArrayList<Integer>();
		
		for(line=0;line<instructions.size();line++)
		{
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {}
			i = instructions.get(line);
			args = i.getArgs();
			switch(i.getName())
			{
				case "":
					break;
			
				case "value":
					double valDouble = Double.parseDouble(args.get(1));
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
					if(!lastLoop.contains(line)) lastLoop.add(line);					
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
							if(value0 - value1 != 0) gotoEnd(lastLoop);							
							break;
						case "!=":
							if(value0 - value1 == 0) gotoEnd(lastLoop);
							break;
						case "<":
							if(value0 - value1 >= 0) gotoEnd(lastLoop);
							break;
						case "<=":
							if(value0 - value1 > 0) gotoEnd(lastLoop);
							break;
						case ">":
							if(value0 - value1 <= 0) gotoEnd(lastLoop);
							break;
						case ">=":
							if(value0 - value1 < 0) gotoEnd(lastLoop);
							break;
					}
					break;
					
				case "for":
					if(!lastLoop.contains(line))
					{
						lastLoop.add(line);
						dataStructure.setValue(args.get(0), Double.parseDouble(args.get(1)));
					}
					if(dataStructure.getValue(args.get(0)) != null)
{
						value0 = (double)(dataStructure.getValue(args.get(0)));
					}
					else
					{
						value0 = 0.0;
						new WrongVariableException(line);
					}
					if(dataStructure.getValue(args.get(2)) != null)
						value1 = (double) (dataStructure.getValue(args.get(2)));
					else
						value1 = Double.parseDouble(args.get(2));
					if(value0.equals(value1-1)) gotoEnd(lastLoop);
					else
					{
						switch(args.get(3))
						{
						case "+":
							dataStructure.setValue(args.get(0), value0+1);
							break;
						case "-":
							dataStructure.setValue(args.get(0), value0-1);
							break;
						default:
							new WrongCodeException(line);
							break;
						}
					}
					break;
					
				case "end":
					line = lastLoop.get(lastLoop.size()-1)-1;
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
	
	public void gotoEnd(ArrayList<Integer> lastLoop)
	{
		int l = line;
		Instruction i = instructions.get(l);
		
		while(!i.getName().equals("end"))
		{
			l = ++line;
			i = instructions.get(l);
			if(i.getName() == null) new NoEndException("" + lastLoop.get(lastLoop.size()-1));
		}
		line++;
		lastLoop.remove(lastLoop.size()-1);
	}
}


