package foodManagement;

/**
 * A SortedListOfImmutables represents a sorted collection of immutable objects 
 * that implement the Listable interface.  
 * 
 * An array of references to Listable objects is used internally to represent 
 * the list.  
 * 
 * The items in the list are always kept in alphabetical order based on the 
 * names of the items.  When a new item is added into the list, it is inserted 
 * into the correct position so that the list stays ordered alphabetically
 * by name.
 */
public class SortedListOfImmutables {
	private Listable[] items;

	/**
	 * This constructor creates an empty list by creating an internal array
	 * of size 0. 
	 */
	public SortedListOfImmutables() {
		items = new Listable[0];
	}

	/**
	 *  Copy constructor. The current object will become a copy of the
	 *  list that the parameter refers to.This copy constructor creates a 
	 *  shallow copy of items in which it is aliased to the current object. This 
	 *  is to prevent the adding or removing things from one of the lists to not
	 *  effect the other list
	 *  
	 *  @param other the list that is to be copied
	 */
	public SortedListOfImmutables(SortedListOfImmutables other) {
		items = new Listable[other.items.length];
		for (int i = 0; i < other.items.length; i++) {
			items[i] = other.items[i];
		}
	}

	/**
	 * Returns the number of items in the list.
	 * @return number of items in the list
	 */
	public int getSize() {
		return items.length;
	}
	
	/**
	 * Returns a reference to the item in the ith position in the list.
	 * 
	 * @param i index of item requested
	 * @return reference to the ith item in the list
	 */
	public Listable get(int i) {
		return items[i];
	}
	
	/**
	 * This method is used to add an item to the list. It assumes that the list 
	 * is already sorted in alphabetical order based on the names of the items 
	 * in the list. In the beginning of the method, a copy of the list is made
	 * except the array is resized one unit larger than before in order to allow 
	 * the insertion of a new item. Where the new item will be inserted, depends
	 * on it's name in a way such that the list will remain alphabetized.
	 * 
	 * The if-state gets name of the new item and compares it to the names of 
	 * existing items. Depending on the first few letters of the name,
	 * if the letter(s) is "less" than a letter of another item, it 
	 * the new item will be placed behind the existing item in the array
	 * and vice versa for a letter that is "greater". If there is no new item,
	 * then nothing happens to the array.
	 * 
	 * @param itemToAdd refers to a Listable item to be added to this list
	 */
	public void add(Listable itemToAdd) {
		Listable[] newList = new Listable[items.length+1];
		boolean isNew = false;
		for (int i = 0; i < items.length; i++) {
			newList[i] = items[i];
		}
		
		if (items.length == 0) {
			newList[0] = itemToAdd;
		} else {
			for (int k = 0; k < items.length; k++) {
				if (itemToAdd.getName().compareTo(items[k].getName()) < 0 && 
						isNew == false) {
					newList[k] = itemToAdd;
					for (int l = k + 1; l < newList.length; l++) {
						newList[l] = items[l-1];
					}
					isNew = true;
				}
			}
			
			if (isNew == false) {
				newList[newList.length-1] = itemToAdd;
			}
		}
		
		items = new Listable[newList.length];
		for (int row = 0; row < newList.length; row++) {
			items[row] = newList[row];
		}
	}

	/**
	 * Adds an entire list of items to the current list, maintaining the 
	 * alphabetical ordering of the list by the names of the items. This method
	 * goes through every item and adds this array of items to the current list
	 * (this).
	 * 
	 * @param listToAdd a list of items that are to be added to the current object
	 */
	public void add(SortedListOfImmutables listToAdd) {
		for (int i = 0; i < listToAdd.items.length; i++){
			this.add(listToAdd.items[i]);
		}
	}
	
	/**
	 * This method removes an item from the list. First, it assumes that no item
	 * has been removed. Then, it goes through every item and sees if the item's
	 * name matches to another item in the list. If so, then the item is removed
	 * 
	 * In the second if-statement, if the item has been removed, then the new
	 * new list is one unit less than its original size. Then, it goes through
	 * every list and if the item appears in the list more than once, just one 
	 * instance will be removed.
	 * 
	 * @param itemToRemove refers to the item that is to be removed from the list
	 */
	public void remove(Listable itemToRemove) {
		boolean itemRemoved = false;
		for (int i = 0; i < items.length; i++){
			if (items[i].getName().equals(itemToRemove.getName())){
				itemRemoved = true;
			}
		}

		if(itemRemoved == true){
			Listable[] newListofItems = new Listable[items.length - 1];
			int x = 0;
			boolean removed = false;
			for (int i = 0; i < items.length; i++){
				if (items[i].getName().equals(itemToRemove.getName()) && 
					removed == false){
					removed = true;
					continue;
				} else {
					newListofItems[x] = items[i];
					x++;
				}
			}
			
			items = new Listable[newListofItems.length];
			for (int j = 0; j < items.length; j++){
				items[j] = newListofItems[j];
			}
		}
	}

	/**
	 * Removes an entire list of items from the current list.  Any items in the
	 * parameter that appear in the current list are removed; any items in the
	 * parameter that do not appear in the current list are ignored.
	 * 
	 * @param listToRemove list of items that are to be removed from this list
	 */
	public void remove(SortedListOfImmutables listToRemove) {
		for (int i = 0; i < listToRemove.items.length; i++){
			this.remove(listToRemove.items[i]);
		}
	}

	/**
	 * Returns the sum of the wholesale costs of all items in the list by going
	 * through all of the items and the wholesale cost sum is equal to the sum
	 * of each cost of the items
	 * 
	 * @return sum of the wholesale costs of all items in the list
	 */
	public int getWholesaleCost() {
		int wholesaleCostSum = 0;
		for (int i = 0; i < items.length; i++){
			wholesaleCostSum += items[i].getWholesaleCost();
		}
		return wholesaleCostSum;
	}

	/**
	 * Returns the sum of the retail values of all items in the list by going
	 * through all items and adding up their retail values.
	 * 
	 * @return sum of the retail values of all items in the list
	 */
	public int getRetailValue() {
		int retailValueSum = 0;
		for (int i = 0; i < items.length; i++){
			retailValueSum += items[i].getRetailValue();
		}
		return retailValueSum;
	}

	/**
	 * This method checks to see if a particular item is in the list by going
	 * through all items and if an item is found in the list, then itemInList 
	 * is true
	 * 
	 * @param itemToFind item to look for
	 * @return true if the item is found in the list, false otherwise
	 */
	public boolean checkAvailability(Listable itemToFind) {
		boolean itemInList = false;

		for (int i = 0; i < items.length && !itemInList; i++){
			if (items[i].equals(itemToFind)){
				itemInList = true;
			}
		}
		return itemInList;
	}

	/**
	 * Checks if a list of items is contained in the current list.
	 * If more than one copy of a particular element appear in the 
	 * parameter, then the current list must contain at least that many as well.
	 * 
	 * @param listToCheck list of items that may or may not appear in the
	 * current list
	 * @return true if all items in the parameter are contained in the current 
	 * list. (If more than one copy of a particular element appear in the
	 * parameter, then the current list must contain at least that many as well.)
	 */
	public boolean checkAvailability(SortedListOfImmutables listToCheck) {
		SortedListOfImmutables newList = new SortedListOfImmutables(this);
		boolean isItemThere = false;
		/* goes through the items and checks if the items in this list appears 
		 * in the current list. If not, then the item is not there. Otherwise, 
		 * the item is there
		 */
		for (int i = 0; i < listToCheck.items.length; i++){
			if (newList.checkAvailability(listToCheck.items[i]) == false){
				isItemThere = false;
				break;
			} else {
				isItemThere = true;
				for (int j = 0;j < newList.items.length; j++){
					if (newList.items[j].equals(listToCheck.items[i])){
						newList.remove(newList.items[j]);
						break;
					}
				}
			}
		}
		return isItemThere;

	}

	public String toString() {
		String retValue = "[ ";
		for (int i = 0; i < items.length; i++) {
			if (i != 0) {
				retValue += ", ";
			}
			retValue += items[i];
		}
		retValue += " ]";
		return retValue;
	}
}
