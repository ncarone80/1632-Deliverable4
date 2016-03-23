import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class TestSort 
{
	//create 100 random arrays of max length 100
	public static int[][] allArrays = new int[100][100];
	
	@Before
	public void setUp() throws Exception
	{
		Random rand = new Random();
		
		for (int i=0; i<allArrays.length; i++)
		{
			//create an array of random length between 10-100
			int[] singleArray = new int[rand.nextInt(100)+10];
			
			for(int j=0; j<singleArray.length; j++)
			{
				//populate array with random ints from 0-999
				singleArray[j] = rand.nextInt(1000);
			}
			allArrays[i] = singleArray;
		}
	}
	//Test to ensure Arrays.sort() sorts in ascending order
	@Test
	public void TestIncreasing()
	{
		for(int i=0; i<allArrays.length; i++)
		{
			boolean isIncreasing = true;
			int [] testArray = allArrays[i];
			Arrays.sort(testArray);
			
			
			for(int j=0; j<(testArray.length-1); j++)
			{
				if(testArray[j] > testArray[j+1])
					isIncreasing = false;
			}
			assertTrue(isIncreasing);
		}
	}
	//Test to ensure Idempotence (consistency through multiple sorts)
	@Test
	public void TestConsistency()
	{
		for(int i=0; i<allArrays.length; i++)
		{
			int [] testArray1 = allArrays[i];
			int [] testArray2 = allArrays[i];
			Arrays.sort(testArray1);
			Arrays.sort(testArray2);
			assertArrayEquals(testArray1, testArray2);
		}
	}
	//Test to ensure all values from original array are in sorted array
	@Test
	public void TestValue()
	{
		boolean allValuesPresent = true;
		for(int i=0; i<allArrays.length; i++)
		{
			int [] sortedTestArray = allArrays[i];
			int [] unsortedTestArray = allArrays[i];
			Arrays.sort(sortedTestArray);
			
			for(int j=0; j<sortedTestArray.length; j++)
			{
				if(Arrays.binarySearch(sortedTestArray, unsortedTestArray[j]) < 0)
					allValuesPresent = false;
			}
			assertTrue(allValuesPresent);
		}
		
	}
	//Test to make sure the size of the sorted array is the same as the original
	@Test
	public void TestSize()
	{
		for(int i=0; i<allArrays.length; i++)
		{
			int origLength;
			int sortedLength;
			int[] sortedArray = allArrays[i];
			Arrays.sort(sortedArray);
			origLength = allArrays[i].length;
			sortedLength = sortedArray.length;
			assertEquals(origLength, sortedLength);
		}
	}
}
