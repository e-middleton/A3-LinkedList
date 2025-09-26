/** Keeps track of position in a linked list */
public class SLL_Iterator<T> implements Phase5SLL_Iterator<T> {
    private NodeSL<T> currNode;
    private NodeSL<T> prevNode;
    private NodeSL<T> prevPrevNode;
    private SLL<T> list;

    /**
     * Creates a new iterator on the given list.
     * Default position is leftmost
     * 
     * @param list the list to iterate on
     */
    public SLL_Iterator(SLL<T> list) {
        this.currNode = list.getHead();
        this.prevNode = null;
        this.prevPrevNode = null;
        this.list = list; // alias not a copy
    }

    /**
     * Tests whether there are any more
     * 
     * @return T/F is it safe to call next()?
     */
    public boolean hasNext() {
        return currNode != null ;
    }

    /**
     * Returns next or throws an exception
     * and advances the iterator
     * 
     * @return the next element
     */
    public T next() {
        if(hasNext()){
            prevPrevNode = prevNode;
            prevNode = currNode;
            currNode = currNode.getNext();
            return prevNode.getData();
        } else {
            throw new MissingElementException();
        }
    }

    /**
     * Sets the data for the element just passed
     * 
     * @param data value to set
     */
    public void set(T data) {
        if (prevNode != null){
            prevNode.setData(data);
        } else {
            throw new MissingElementException();
        }
    }

    /**
     * Returns the data for the element just passed
     * Might throw a null pointer exception.
     * @return data value in the element just passed
     */
    public T get() {
        return prevNode.getData();
    }

    /**
     * Inserts a node with the specified data
     * Cannot be called twice in a row without intervening next()
     * 
     * @param data the value to insert
     */
    public void add(T data) {
        list.addAfter(prevNode, data);
        if(prevNode == null){
            prevNode = list.getHead();
        } else{
            prevPrevNode = prevNode;
            prevNode = prevNode.getNext();
        }
    }

    /**
     * Removes the node just passed
     * Cannot be called twice in a row without intervening next()
     */
    public void remove() {
        if(prevNode==null){                 // covers the case of empty list, or iterator at beginning
            throw new MissingElementException();
        } 
        if(prevNode==list.getHead()){
            list.removeAfter(prevPrevNode);
            prevNode = null; // if it was the first element, prevNode is back to pointing at null
        } else{
            list.removeAfter(prevPrevNode);
            prevNode = prevPrevNode.getNext();
        }
    }

    /**
     * Main method for debugging the SLL_Iterator class
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SLL<String> test = new SLL<String>();
        test.addLast("B");
        test.addLast("C");

        SLL_Iterator<String> iter = new SLL_Iterator<String>(test);

        iter.add("A");
        System.out.println(test);
        iter.remove();
        System.out.println(test);
        // iter.remove();
        // System.out.println(test);
    }
}
