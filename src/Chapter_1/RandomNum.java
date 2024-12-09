package Chapter_1;

import java.util.Random;

public class RandomNum {
	public static void main(String[]args)
	{
		Random random = new Random();
		int min = 2;
		int max = 10;
		float num1 = min + random.nextFloat(max - min + 1);
		int num2 = min + (int)(Math.random() * (max - min + 1));
		System.out.println(num1);
		System.out.println(num2);
	}
}
