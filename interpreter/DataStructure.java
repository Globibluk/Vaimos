package interpreter;

import java.util.HashMap;

public class DataStructure {
	
	private HashMap<String, Object> dataList;
	
	public DataStructure()
	{
		dataList = new HashMap<String, Object>();
	}
	
	public Object getValue(String name)
	{
		return dataList.get(name);
	}
	
	public Object setValue(String name, Object value)
	{
		return dataList.put(name, value);
	}	

}