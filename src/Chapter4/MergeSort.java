package Chapter4;
import java.util.Random;
public class MergeSort {

	public static void main(String[] args) {
	
		Random r = new Random();
		int [] numbers = new int[10];
	
		for(int i = 0; i < numbers.length; i++)
		{
			numbers[i] = r.nextInt(10000);
		}
		System.out.println("Before");
		printArray(numbers);
	
		mergeSort(numbers);
	
		System.out.print("\nAfter:");
		printArray(numbers);
	} 
	
	private static void mergeSort(int[]inputArray)
	{
		int inputLength = inputArray.length;
		if(inputLength < 2) // length: 0-1
			return;
		
		// 9/2 = 4
		// leftHalf = new int[4]
		// rightHalf = new int[9 - 4] = new int[5]
		int midIndex = inputLength / 2; 
		int [] leftHalf = new int[midIndex];
		int [] rightHalf = new int[inputLength - midIndex];
		
		// input left half
		// int i = 0; i < 4; i++
		for(int i = 0; i < midIndex; i++)
		{
			leftHalf[i] = inputArray[i];
		}
		
		// int i = 4; i < 9; i++
		for(int i = midIndex; i < inputLength; i++)
		{
			// rightHalf[4 - 4] = [0]
			// rightHalf[5 - 4] = [1] 把它变成最普通的index
			rightHalf[i - midIndex] = inputArray[i];
		}
		
		mergeSort(leftHalf);
		mergeSort(rightHalf);
		
		// merge
		merge(inputArray, leftHalf, rightHalf);
	}
	
	private static void merge(int [] inputArray, int [] leftHalf, int [] rightHalf)
	{
		int leftSize = leftHalf.length;
		int rightSize = rightHalf.length;
		
		int i = 0, j = 0, k = 0;
		
		// if any of the i > leftSize or j > rightSize, 
		// the while condition will be false and process out of the loop
		while(i < leftSize && j < rightSize) 
		{
			if(leftHalf[i] <= rightHalf[j])
			{
				inputArray[k] = leftHalf[i];
				i++;
			}
			else
			{
				inputArray[k] = rightHalf[j];
				j++;
			}
			k++;
		}
		// the remaining leftHalf or rightHalf element add sequentially into inputArray
		// since before merging the array, the sorting is already done in leftHalf and rightHalf array
		while(i < leftSize)
		{
			inputArray[k] = leftHalf[i];
			i++;
			k++;
		}
		
		while(j < rightSize)
		{
			inputArray[k] = rightHalf[j];
			j++;
			k++;
		}
		
	}
	private static void printArray(int [] numbers)
	{
		for(int i = 0; i < numbers.length; i++)
		{
			System.out.println(numbers[i]);
		}
	}

}
