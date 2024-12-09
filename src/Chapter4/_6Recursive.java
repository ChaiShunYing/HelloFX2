package Chapter4;
// recursive: any method that call itself
public class _6Recursive {
	public static void main(String[]args)
	{
		sayHi(3);
	}
	
	// 1. base case: a condition inside method 
	//    to return without making another recursive call (make sure recursive call wont go deeper and deeper)
	// 2. something that allow u to work base case
	                         // 2.
	private static void sayHi(int count) // 3 2 1
	{
		System.out.println("Hi");
		// 1. (2. cause 1. can reach count <= 1)
		if(count <= 1) // 3(skip) 2(skip) 1
			return;
		// 2.
		sayHi(count - 1); // 2 1
	}
}
