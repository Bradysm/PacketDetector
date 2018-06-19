package detection;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Brady Murphy (bradysm)

/**
 * an interface for the ADT list
 * Entries in a list have positions that begin with one
 * 
 * @author Brady Murphy
 * @version 2018.03.30
 * @param <T>
 *            generic type used
 */
public interface ListInterface<T> {
    /**
     * adds a new entry to the END of this list
     * Entries currently in the list are unaffected
     * The lists size is increased by 1
     * 
     * @param newEntry
     *            the object to be added as a new entry
     */
    public void add(T newEntry);


    /**
     * Adds a new entry at a specified position within this list
     * Entries originally at or above the specified position are at the next
     * higher
     * position within the list
     * The lists size is increased by one
     * 
     * @param newPosition
     *            An integer that specifies the desired position of the new
     *            array
     * @param newEntry
     *            the object to be added as a new Entry
     * @throws IndexOutOfBoundsException
     *             if either givenPosition < 0 or givenPosition > getLength()
     */
    public void add(int newPosition, T newEntry);


    /**
     * removes the entry at the given positon, from the list
     * Entries originally at positions higher than the given position are at the
     * next lower
     * position within the list, and the lists size is decreased by 1
     * 
     * @param givenPosition
     *            An integer that indicates the position of the entry to be
     *            removed
     * @return T A reference to the removed entry
     * @throws IndexOutOfBoundsException
     *             if either givenPosition < 0 or givenPosition > getLength()-1
     */
    public T remove(int givenPosition);


    /**
     * removes all entries and sets the size to zero
     */
    public void clear();


    /**
     * returns the entry at a given position in this list
     * 
     * @param givenPosition
     *            an integer that indicates the position of the desired entry
     * @return a reference to the entry in the list
     * @throws IndexOutOfBoundsException
     *             if either givenPosition < 0 or givenPosition > getLength()-1
     */
    public T getEntry(int givenPosition);


    /**
     * retrieves all entries that are in this list in the order in which they
     * occur in the list
     * 
     * @return A newly allocated array of all the entries in the list. If the
     *         list is empty, the returned array is empty
     */
    public T[] toArray();


    /**
     * sees whether this list contains the given entry
     * 
     * @param anEntry
     *            the object that is the desired entry
     * @return true if the object is within the list, and false if not
     */
    public boolean contains(T anEntry);


    /**
     * gets the length of this list
     * 
     * @return the integer number of entries currently in the list
     */
    public int getLength();


    /**
     * sees whether this list is empty
     * 
     * @return returns true if the size of the list is zero
     */
    public boolean isEmpty();
}
