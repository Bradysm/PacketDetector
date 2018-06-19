/**
 * 
 */
package detection;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Brady Murphy (bradysm)

/**
 * Implementation of LinkedList. Contains an iterator to allow for
 * traversal of collection of data, and
 * 
 * @author Brady Murphy (bradysm)
 * @version Apr 7, 2018
 * @param <T>
 *            generic type used to implement the list
 */
public class LinkedList<T> implements ListInterface<T>, Iterable<T> {
    /**
     * first node of the linked list
     */
    protected Node firstNode; // Reference to first node of chain
    /**
     * number of entries in the linkedList
     */
    protected int numberOfEntries; // number of entries in the chain


    /**
     * constructor to initialize the linked list
     */
    public LinkedList() {
        firstNode = null;
        numberOfEntries = 0;
    }


    /**
     * Node class used for linked chain implementations
     * 
     * @author Brady Murphy (bradysm)
     * @version Mar 30, 2018
     */
    protected class Node {
        private T data;
        private Node next;


        /**
         * node constructor that sets the data to the given data and sets the
         * next node to null
         * 
         * @param data
         *            to be placed within the new node created
         */
        protected Node(T data) {
            this(data, null);
        }


        /**
         * constructor for node class
         * 
         * @param data
         *            to be placed within node
         * @param nextNode
         *            to be set as next
         */
        protected Node(T data, Node nextNode) {
            this.data = data;
            next = nextNode;
        }


        /**
         * returns the data from Node
         * 
         * @return T data from a node
         */
        protected T getData() {
            return data;
        }


        /**
         * sets the data of a node to the data parameter
         * 
         * @param data
         *            to be set to the nodes data
         */
        protected void setData(T data) {
            this.data = data;
        }


        /**
         * gets the nextNode within the list and returns the node
         * 
         * @return Node object representing the next node is returned
         */
        protected Node getNextNode() {
            return this.next;
        }


        /**
         * sets the next instance variable to the given parameter
         * 
         * @param nextNode
         *            node to be placed as the next
         */
        protected void setNextNode(Node nextNode) {
            this.next = nextNode;
        }
    } // end of Node class


    /**
     * Iterator class used to create an iterator for a linked list
     * 
     * @author Brady Murphy (bradysm)
     * @version Mar 30, 2018
     */
    private class LListIterator implements Iterator<T> {
        // have a place for nextWasCalled
        private Node nextNode;


        /**
         * initializes the iterator object to be used
         */
        public LListIterator() {
            nextNode = firstNode;
        }


        /**
         * returns true if there is more entries to traverse
         * 
         * @return true if nextNode isn't null
         */
        @Override
        public boolean hasNext() {
            return (nextNode != null);
        }


        /**
         * returns the next entry to be iterated over
         * 
         * @return T containing the next entry in the collection
         * @throws NoSuchElementException
         *             if there is not a nextNode to iterate
         */
        @Override
        public T next() {
            if (hasNext()) {
                Node result = nextNode;
                nextNode = nextNode.getNextNode();
                return result.getData();
            }
            else {
                throw new NoSuchElementException(
                    "No more entries to iterate over");
            }
        }


        /**
         * this method is currently not allowed
         * 
         * @throws UnsupportedOperationException
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    /**
     * clears the linked list
     */
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }


    /**
     * Tries to add an entry to the linked list. If the entry is not null,
     * the entry will be added to the end of the linkedList
     *
     * @param entry
     *            entry to be added to the Linked List
     * @throws IllegalArgumentException
     *             if the given entry is null
     */
    public void add(T entry) {
        if (entry == null) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node(entry);

        if (isEmpty()) {
            firstNode = newNode;
        }
        else {
            Node lastNode = getNodeAt(numberOfEntries - 1);
            lastNode.setNextNode(newNode); // Make last node reference new node
        }
        numberOfEntries++;
    }


    /**
     * Tries to add an entry to a given position. If the entry is not null and
     * the position is within bounds, the method will add the entry to the given
     * position
     *
     * @param position
     *            position within the linked list to add the new entry
     * @param newEntry
     *            data entry to be added to the linked list
     */
    public void add(int position, T newEntry) {
        if (newEntry == null) {
            throw new IllegalArgumentException();
        }
        if ((position >= 0) && (position <= numberOfEntries)) {
            Node newNode = new Node(newEntry);
            // check to see if the position is at the beginning
            if (position == 0) {
                newNode.setNextNode(firstNode);
                firstNode = newNode;
            }
            else {
                Node nodeBefore = getNodeAt(position - 1);
                Node nodeAfter = nodeBefore.getNextNode();
                newNode.setNextNode(nodeAfter);
                nodeBefore.setNextNode(newNode);
            }
            numberOfEntries++;
        }
        else { // illegal position
            throw new IndexOutOfBoundsException(
                "Illegal position given to add operation.");
        }
    }


    /**
     * Removes a Node from a given position from the list. Once the node
     * is removed, the data from the removed node is returned.
     *
     * @param givenPosition
     *            position in the list to remove
     * @return the data from the Node removed from the list
     * @throws IndexOutOfBoundsException
     *             if given position is less than 0 or greater than or equal to
     *             the numberOfEntries
     */
    public T remove(int givenPosition) {
        T result = null; // Return value

        if ((givenPosition >= 0) && (givenPosition < numberOfEntries)) {

            if (givenPosition == 0) // remove the entry from the zero index
            {
                result = firstNode.getData();
                firstNode = firstNode.getNextNode();
            }
            else { // not the first entry
                Node nodeBefore = getNodeAt(givenPosition - 1);
                Node nodeToRemove = nodeBefore.getNextNode();
                result = nodeToRemove.getData(); // Save entry to be removed
                Node nodeAfter = nodeToRemove.getNextNode();
                nodeBefore.setNextNode(nodeAfter); // Remove entry
            }

            numberOfEntries--; // Update count
            return result; // Return removed entry
        }
        else {
            throw new IndexOutOfBoundsException(
                "Illegal position given to remove operation.");
        }
    }


    /**
     * gets an entry from the given position. The index must be greater
     * than zero and less than the current number of entries
     *
     * @param givenPosition
     *            position to get entry from
     * @return the data at the respective entry
     * @throws IndexOutOfBoundsException
     *             if index < 0 or >= number of entries
     */
    public T getEntry(int givenPosition) {
        if ((givenPosition >= 0) && (givenPosition < numberOfEntries)) {
            return getNodeAt(givenPosition).getData();
        }
        else {
            throw new IndexOutOfBoundsException(
                "Illegal position given to getEntry operation.");
        }
    } // end getEntry


    /**
     * returns an array representation of the LinkedList
     *
     * @return T[] an array consisting of the entries form the list
     */
    public T[] toArray() {
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries];

        int index = 0;
        Node currentNode = firstNode;
        while (currentNode != null) {
            result[index] = currentNode.getData();
            currentNode = currentNode.getNextNode();
            index++;
        } // end while

        return result;
    } // end toArray


    /**
     * checks to see if the list contains a specified entry
     *
     * @param anEntry
     *            entry to check and see if within the list
     * @return true if the entry is within the list
     */
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.getData())) {
                found = true;
            }
            else {
                currentNode = currentNode.getNextNode();
            }
        }
        return found;
    }


    /**
     * returns the length of the list
     *
     * @return int representing the length of the list
     */
    public int getLength() {
        return numberOfEntries;
    } // end getLength


    /**
     * checks to see if the list is empty
     *
     * @return true if the list is empty
     */
    public boolean isEmpty() {
        boolean result = false;
        if (numberOfEntries == 0) {
            result = true;
        }
        return result;
    }


    /**
     * returns a reference to the node at the given position
     * 
     * @precondition givenPosition must be >= 0 and < size
     * @param givenPosition
     *            index of node to be returned
     * @return reference to Node
     */
    private Node getNodeAt(int givenPosition) {
        Node currentNode = firstNode;
        for (int index = 0; index < givenPosition; index++) {
            currentNode = currentNode.getNextNode();
        }
        return currentNode;
    }


    /**
     * returns the size of the list
     * 
     * @return int representing the size of the list
     */
    public int size() {
        return numberOfEntries;
    }


    /**
     * removes a given object from the Linked List
     * 
     * @param obj
     *            to be removed from the linked list
     * @return true if the object was found and removed. False if not.
     */
    public boolean remove(T obj) {
        boolean found = false;

        if (contains(obj)) {
            Node currentNode = firstNode;
            // check to see if the object to be removed is at the front
            if (currentNode.getData().equals(obj)) {
                found = true;
                firstNode = firstNode.getNextNode();
                currentNode = null;
                numberOfEntries--;
            }
            while (!found && currentNode != null) {
                if (currentNode.getNextNode().getData().equals(obj)) {
                    Node nodeToRemove = currentNode.getNextNode();
                    Node nodeAfter = nodeToRemove.getNextNode();
                    currentNode.setNextNode(nodeAfter);
                    nodeToRemove = null;
                    found = true;
                    numberOfEntries--;

                }
                else {
                    currentNode = currentNode.getNextNode();
                }
            }
        }
        return found;
    }


    /**
     * returns the data from a given index
     * 
     * @param index
     *            to return data from
     * @return T data from the given index
     * @throws IndexOutOfBoundsException
     *             if index isn't >=0 and less than the number of entries
     */
    public T get(int index) {
        T result = null;
        if ((index >= 0) && (index < numberOfEntries)) {
            Node currentNode = getNodeAt(index);
            result = currentNode.getData();
        }
        else { // illegal index
            throw new IndexOutOfBoundsException(
                "Illegal position given to get operation.");
        }

        return result;
    }


    /**
     * returns the last index of the object in the current list
     * 
     * @param obj
     *            to be found in the current list
     * @return integer representing the index where the object is. If the object
     *         is not found, -1 is returned
     */
    public int lastIndexOf(T obj) {
        int result = -1;
        // check to make sure it contains the obj
        if (contains(obj)) {
            // iterate through the chain
            Node currentNode = firstNode;
            for (int counter = 0; counter < numberOfEntries; counter++) {
                // check to see if they are equal
                if (currentNode.getData().equals(obj)) {
                    result = counter;
                }
                currentNode = currentNode.getNextNode();
            }
        }
        return result;
    }


    /**
     * returns a String representation of the given Objects within the list
     *
     * @return
     */
    @Override
    public String toString() {
        // create a StringBuilder object to create the string
        StringBuilder result = new StringBuilder();
        result.append("{");
        Node currentNode = firstNode;

        for (int index = 0; index < numberOfEntries; index++) {
            result.append(currentNode.getData());
            if (index + 1 < numberOfEntries) {
                result.append(", ");
            }
            currentNode = currentNode.getNextNode();
        }
        result.append("}");
        return result.toString();
    }


    /**
     * returns an iterator object. This will allow for traversal across
     * the linked list
     *
     * @return Iterator<T> object that contains hasNext, next and remove methods
     */
    @Override
    public Iterator<T> iterator() {
        return new LListIterator();
    }

}
