
public class Appli {
	public static void main(String args[])
	{
		HashTable<String,Integer> hashTable=new HashTable<>();
		
		hashTable.put("Adam", 27);
		hashTable.put("Gourav", 21);
		hashTable.put("Tem", 30);
		
		System.out.println(hashTable.get("Adam"));
		System.out.println(hashTable.get("He"));
		}
}
