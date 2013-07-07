package javaapplication3;
import java.util.Vector;

/**
 * Implements a min - priority queue.
 * @author trachten
 */

public class priorityQueue {
	// CONSTRUCTOR
	public priorityQueue() {
		element = new Vector<ItemPri>();
	}

	// CLASSES

	/**
	 * A container for an item and its priority.
	 */
	public class ItemPri {

		/**
		 * Constructs a pair containing an item and its priority, and maintaining
		 * a boolean "extracted" recording whether the item has been extracted from the priority queue.
		 * @param item A number uniquely identifying the item.
		 * @param pri A priority for the given item.
		 */
		ItemPri(int item, int pri) {
			itemNum = item; priority = pri;
			extracted = false;
		}

		public int itemNum;
		public int priority;
		public boolean extracted;
	}

	// METHODS

	/**
	 * Adds an item numbered <itemNumber> with priority <priority> to the priorityQueue.
	 * @param itemNumber An identifying number of the item.
	 * @param priority The priority of the item.
	 */
	void addItem(int itemNumber, int priority) {
		element.add(new ItemPri(itemNumber, priority));
	}

	/**
	 * @return If the priority queue is empty.
	 */
	boolean isEmpty() {
		for (int ii=0; ii<element.size(); ii++)
			if (!element.get(ii).extracted)
				return false; // I found one non-extracted element.

		return true;
	}

	/**
	 * @param itemNum The vertex whose priority is being sought.
	 * @return The priority of vertex <vertex>.
	 * @require <vertex> must be less than the number of items in the priority queue
	 */
	int getPriority(int itemNum) {
		for (int ii=0; ii<element.size(); ii++)
			if (element.get(ii).itemNum == itemNum)
				return element.get(ii).priority;
		return 0; // i.e. vertex not found
	}

	/**
	 * 
	 * @param itemNum
	 * @param priority
	 */
	void setPriority(int itemNum, int priority) {
		for (int ii=0; ii<element.size(); ii++)
			if (element.get(ii).itemNum == itemNum)
				element.get(ii).priority = priority;
	}

	/**
	 * Extracts the item with lowest priority from the priority queue and returns its item number.
	 * @return The item with the lowest priority in the priority queue.
	 * @requires The priority must have at least one item in it *before* this call.
	 */
	ItemPri extractMin() {
		// 1.  Find the item with lowest priority that has not been extracted
		ItemPri min = new ItemPri(-1,Integer.MAX_VALUE);
		for (int ii=0; ii<element.size(); ii++)
			if (!element.get(ii).extracted &&              // not extracted
					element.get(ii).priority <= min.priority) // priority is lower than what I have seen thus far
				min = element.get(ii);

		// 2. Extract the element and return it
		element.get(element.indexOf(min)).extracted = true; // mark the minimum item as extracted

		return min;
	}

	/**
	 * @return A human-readable version of the queue in its current state.
	 */
	String printQueue() {
		String result="";
		for (int ii=0; ii<element.size(); ii++)
			result+=element.get(ii).itemNum +
			": " +
			element.get(ii).priority +
			//(element.get(ii).extracted?" (extracted)":"") +
			"\n";
		return result;
	}

	/**
	 * @return The sum of all priorities stored in the queue.
	 */
	int prioritySum() {
		int sum=0;
		for (int ii=0; ii<element.size(); ii++)
			sum+=element.get(ii).priority;
		return sum;
	}

	// DATA FIELDS
	private Vector<ItemPri> element; /** The elements of the priority queue. */
}
