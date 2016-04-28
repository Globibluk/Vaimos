package interpreter;

import java.util.ArrayList;

import boat.Boat;
import sim.Simulation;

public class Interpreter extends Thread {
	
	private ArrayList<Instruction> instructions;
	private DataStructure dataStructure;
	
	private int line;
	private Boat boat;
	
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
	
	public void setBoat(Boat boat)
	{
		this.boat = boat;
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
		}
		catch (NumberFormatException e)
		{
			System.out.println("Wrong variable at line " + line);
		}
		catch (IndexOutOfBoundsException e)
		{
			System.out.println("Wrong code at line " + line);
		} catch (Exception e) {}
	}
		
	@SuppressWarnings("unchecked")
	public void execute() throws Exception
	{
		ArrayList<String> args;
		ArrayList<Double> dList;
		Object result;
		int index;
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
				
				case "stop":
					return;
			
				case "value":
					double valDouble = Double.parseDouble(args.get(1));
					dataStructure.setValue(args.get(0), valDouble);
					break;
					
				case "list":
					ArrayList<Double> list = new ArrayList<Double>();
					dataStructure.setValue(args.get(0), list);
					break;
				
				case "set":

					result = dataStructure.getValue(args.get(0));
					if(!(result instanceof ArrayList)) throw new WrongVariableException(line);
					dList = (ArrayList<Double>) result;
					index = (int) getData(args.get(1));
					value0 = (double) getData(args.get(2));
					
					dList.set(index, value0);
					break;
					
				case "get":
					
					result = dataStructure.getValue(args.get(0));
					if(!(result instanceof ArrayList)) throw new WrongVariableException(line);
					dList = (ArrayList<Double>) result;
					index = (int) (getData(args.get(1)));
					s = args.get(2);
					value1 = dList.get(index);
					
					dataStructure.setValue(s, value1);
					break;
					
				case "add":
					result = dataStructure.getValue(args.get(0));
					if(!(result instanceof ArrayList)) throw new WrongVariableException(line);
					dList = (ArrayList<Double>) result;
					value0 = (double) getData(args.get(1));
					
					dList.add(value0);
					break;
					
				case "remove":
					result = dataStructure.getValue(args.get(0));
					if(!(result instanceof ArrayList)) throw new WrongVariableException(line);
					dList = (ArrayList<Double>) result;
					index = (int) getData(args.get(1));
					
					dList.remove(index);
					break;
					
				case "+": 
				case "-":
				case "*":
				case "/":

					value0 = initValue(args, 1);
					value1 = initValue(args, 2);
				
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
					
				case "if":
					value0 = initValue(args, 0);
					value1 = initValue(args, 2);
					if(test(i.getName(), value0, value1, args))
						gotoNext();
					break;
					
				case "else":
					gotoNext();
					break;
					
				case "fi":
					break;
					
				case "while":
					if(!lastLoop.contains(line))
					{
						lastLoop.add(line);
					}
					value0 = initValue(args, 0);
					value1 = initValue(args, 2);
					if(test(i.getName(), value0, value1, args)) gotoEnd(lastLoop);
					
					break;
					
				case "for":
					if(!lastLoop.contains(line))
					{
						lastLoop.add(line);
						dataStructure.setValue(args.get(0), Double.parseDouble(args.get(1)));
					}
					value0 = initValue(args, 0);
					value1 = initValue(args, 2);
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
							throw new WrongCodeException(line);
						}
					}
					break;
					
				case "end":
					if(lastLoop.size() > 0)
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
					
				case "posx":
					dataStructure.setValue(i.getArgs().get(0), boat.getIPosX());
					break;
					
				case "posy":
					dataStructure.setValue(i.getArgs().get(0), boat.getIPosY());
					break;
					
				case "depth":
					dataStructure.setValue(i.getArgs().get(0), boat.getProfPos());
					break;
					
				case "salt":
					dataStructure.setValue(i.getArgs().get(0), boat.getSalPos());
					break;
					
				case "calculate":
					boat.calculate();
					break;
				
				case "setTarget":
					int ax, ay, bx, by;
					ax = (int) getData(args.get(0));
					ay = (int) getData(args.get(1));
					bx = (int) getData(args.get(2));
					by = (int) getData(args.get(3));
					
					boat.setTarget(ax, ay, bx, by);
					break;
										
				default:
					throw new UnknownInstructionException(line);
					
			}
		}
	}

	private double initValue(ArrayList<String> args, int index)
	{
		if(this.dataStructure.getValue(args.get(index)) != null)
			return (double) (this.dataStructure.getValue(args.get(index)));
		else
			return Double.parseDouble(args.get(index));
	}
	
	private void gotoEnd(ArrayList<Integer> lastLoop)
	{
		int l = ++line;
		int ignore = 0;
		String name;
		Instruction i = instructions.get(l);

		while(!i.getName().equals("end") || ignore > 0)
		{
			name = i.getName();
			if(name.equals("end"))
				ignore--;
			if(name.equals("while") || name.equals("for"))
				ignore++;
				
			l = ++line;
			try
			{
				i = instructions.get(l);
			} catch (Exception e)
			{
				new NoEndException("" + lastLoop.get(lastLoop.size()-1));
				return;
			}
		}
		lastLoop.remove(lastLoop.size()-1);
	}
	
	private void gotoNext()
	{
		int sourceLine = line;
		int l = ++line;
		int ignore = 0;
		String name;
		Instruction i = instructions.get(l);

		while(!(i.getName().equals("fi") || i.getName().equals("else")) || ignore > 0)
		{
			name = i.getName();
			if(name.equals("fi"))
				ignore--;
			if(name.equals("if"))
				ignore++;
				
			l = ++line;
			try
			{
				i = instructions.get(l);
			} catch (Exception e)
			{
				new WrongIfException(sourceLine);
				return;
			}
		}
	}
	
	private boolean test(String test, double value0, double value1, ArrayList<String> args)
	{
		switch(args.get(1))
		{
			case "==":
				if(value0 - value1 != 0) 
					return true;
				break;
			case "!=":
				if(value0 - value1 == 0) 
					return true;
				break;
			case "<":
				if(value0 - value1 >= 0) 
					return true;
				break;
			case "<=":
				if(value0 - value1 > 0) 
					return true;
				break;
			case ">":
				if(value0 - value1 <= 0) 
					return true;
				break;
			case ">=":
				if(value0 - value1 < 0) 
					return true;
				break;
		}
		
		return false;
	}
	
	private double getData(String key) throws WrongVariableException
	{
		double value;
		try
		{
			value = Double.parseDouble(key);
		} catch (Exception e)
		{
			value = (double) dataStructure.getValue(key);
		}
		return value;
	}
	
}