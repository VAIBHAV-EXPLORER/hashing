import java.util.*;
@SuppressWarnings({"unchecked","unused"})
public class HashTable <Key,Value>{
	private Key keys[];
	private Value values[];
	private int numofItems;
	private int capacity;//It is the no of items inserted in the table   numofItems < = capacity
	
	public HashTable()
	{
		this.keys=(Key[]) new Object[Constants.Table_Size];
		this.values=(Value[]) new Object[Constants.Table_Size];
		this.capacity=Constants.Table_Size;
		this.numofItems=0;
	}
	public HashTable(int newCapacity)
	{
		this.keys=(Key[]) new Object[newCapacity];
		this.values=(Value[]) new Object[newCapacity];
		this.capacity=newCapacity;
		this.numofItems=0;
	}
	public int size()
	{
		return this.numofItems;
	}
	
	public boolean isEmpty()
	{
		return this.numofItems==0;
	}
	
	
	private int hash(Key key)
	{
		return Math.abs(key.hashCode())%capacity;
	}
	
	
	
	public void put(Key key ,Value value)
	{
		if(key==null || value ==null)
			return ;
		if(numofItems >= capacity * 0.75 )
		{
			resize(2*capacity);
		}
		int index=hash(key);
		while(keys[index]!=null)
		{
			//update
			if(keys[index].equals(key))
			{
				values[index]=value;
				return;
			}
			index=(index+1)%capacity;
		}
		keys[index]=key;
		values[index]=value;
		numofItems++;
	}
	public Value get(Key key)
	{
		if(key==null)
			return null;
		int index=hash(key);
		while(keys[index]!=null)
		{
		if(keys[index].equals(key))
		{
			return values[index];
		}
		index=(index+1)%capacity;
		}
		return null;
	}
	
	public void remove(Key key)
	{
		if(key==null)
			return;
		
		int index=hash(key);
		if(!keys[index].equals(key))
		{
			index=(index+1)% capacity;
		}
		keys[index]=null;
		values[index]=null;
		numofItems--;
		
		while(keys[index]!=null)
		{
			Key temkey=keys[index];
			Value temvalue=values[index];
			
			keys[index]=null;
			values[index]=null;
			numofItems--;
			
			put(temkey,temvalue);
		}
		
		
		if(numofItems<=capacity/3)
		{
			resize(capacity/2);
		}
	}
	public Key[] getKeys() {
		return keys;
	}
	public void setKeys(Key[] keys) {
		this.keys = keys;
	}
	public Value[] getValues() {
		return values;
	}
	public void setValues(Value[] values) {
		this.values = values;
	}
	public int getNumofItems() {
		return numofItems;
	}
	public void setNumofItems(int numofItems) {
		this.numofItems = numofItems;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	private void resize(int newCapacity) {
		HashTable<Key,Value> newTable=new HashTable<>(newCapacity);
		for(int i=0;i<capacity;i++)
		{
			if(keys[i]!=null)
			{
				newTable.put(keys[i], values[i]);
			}
		}
		
		keys=newTable.getKeys();
		values=newTable.getValues();
		capacity=newTable.getCapacity();
	}
}
