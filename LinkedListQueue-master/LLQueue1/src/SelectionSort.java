
public class SelectionSort <T> {
	
	private final int SIZE;
	private T values[];

	/**
	 * @param values
	 */
	public SelectionSort(T[] values, int size) {
		this.SIZE = size;
		this.values = values;
	}

	
	@SuppressWarnings("unchecked")
	public int minIndex(int startIndex, int endIndex)
	// Returns the index of the smallest value in
	// values[startIndex]..values[endIndex].
	{
		int indexOfMin = startIndex;
		for (int index = startIndex + 1; index <= endIndex; index++)
			if(((Comparable<T>)values[index]).compareTo(values[indexOfMin])<0)
				indexOfMin = index;
		return indexOfMin;
	}

	public void selectionSort()
	// Sorts the values array using the selection sort algorithm.
	{
		int endIndex = SIZE - 1;
		for (int current = 0; current < endIndex; current++)
			swap(current, minIndex(current, endIndex));
	}

	private void swap(int current, int minIndex) {
		// TODO Auto-generated method stub
		T temp;
		temp = values[current];
		values[current] = values[minIndex];
		values[minIndex] = temp;
	}

}
