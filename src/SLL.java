/**
 * Class to implement a singly linked list
 *
 * @author Emily Middleton
 * @version Fall 2025
 */

 public class SLL<T> implements Phase1SLL<T>, Phase2SLL<T>{ // they both need <T> so that it's clear they're the same generic type
    private NodeSL<T> head;
    private NodeSL<T> tail;


    /**
     * Constructor for an empty list
     */
    public SLL(){
        this.head = null;
        this.tail = null;
    }

    /**
     * Access method for the head of the LL
     * @return the head of the LinkedLIst
     */
    public NodeSL<T> getHead(){
        return this.head;
    }
    

    /**
     * Accessor for the tail node
     * @return the tail of the linkedList
     */
    public NodeSL<T> getTail(){
        return this.tail;
    }

    /**
     * Determines whether or not the linked list is empty
     * @return T/F the linked list is empty
     */
    public boolean isEmpty(){
        return this.head == null;
    }

    /**
     * Inserts the given item at the head of the list
     * @param v item to insert
     */
    public void addFirst(T v){
        NodeSL<T> node = new NodeSL<T>(v, null); // initialize the node

        if(this.head == null) { // case of an empty list, both the head and tail are the node added
            node.setNext(this.head); // set the next value
            this.tail = node;       
            this.head = node;
        } else{
            node.setNext(this.head); // otherwise only the head needs to be updated
            this.head = node;
        }
    }
  
  /** Converts to a string representation */
  public String toString(){
    if(this.head == null) { // edge case for an empty list
        return "[]";
    } else {
        String list = "[";
        for(NodeSL<T> node = this.head; node.getNext() != null; node = node.getNext()){
            String item = node.getData().toString();
            list = list.concat(item + ", ");
        }
        String item = this.tail.getData().toString(); // for the tail node
        list = list.concat(item + "]");
        return list;
    }
  }

  /**
   * Inserts an item at the tail of the list
   * @param v the item to insert
   */
  public void addLast(T v){
    NodeSL<T> newNode = new NodeSL<T>(v, null);

    if(this.head == null){ // edge case for an empty list
        this.head = newNode;
        this.tail = newNode;
    } else{
        this.tail.setNext(newNode); // find the node that is currently the tail, and make the 'next' point to the new node
        this.tail = newNode; // change the tail of the linked list to be this new node
    }
  }
  
  /**
   * Inserts the given item after the specified node
   * @param here node to insert after
   * @param v item to insert
   */
  public void addAfter(NodeSL<T> here, T v){
    // empty list would throw an exception
    NodeSL<T> node = new NodeSL<T>(v, here.getNext());
    if (here.getNext() == null) { // case for the tail
        here.setNext(node); // update the next field for the current node
        this.tail = node; // update the tail
    } else{
        here.setNext(node);
    }
  }


  /**
   * Removes the given item from the head of the list.
   * Throws a MissingElementException if it is an empty list.
   * @return the item removed
   */
  public T removeFirst(){ 
    if(this.head == null){          // empty list
        throw new MissingElementException(); 
    }

    T item = this.head.getData(); // store the data of the item being deleted

    if(this.head.getNext()==null){ // if the list is one element long, the tail must be updated
        this.tail = null;
    }
    this.head = this.head.getNext(); // updates the head to be the next element of the old head

    return item;
  }

  /**
   * Removes the given item from the tail of the list
   * @return the item removed from the end of the list
   */
  public T removeLast(){
    if (this.head == null){ // if the list is empty
        throw new MissingElementException();
    }

    if (this.head.getNext() == null){ // one element long list
        T item = this.head.getData();
        this.head = null; // removing the one item makes this an empty list
        this.tail = null;
        return item;
    }

    // loop through the list to find the element before the tail
    NodeSL<T> node = this.head;

    while(node.getNext().getNext() != null){ 
        node = node.getNext();
    }

    T item = this.tail.getData(); // store item being removed
    node.setNext(null); // why do I have to do this? 

    this.tail = node; // update the tail to be the node before the old tail
    return item;
  }
  
  /**
   *  Removes the node after the given position. Throws a MissingElementException if 
   * you try to remove an element after the tail, or from an empty list.
   *  @param here marks position to remove after
   *  @return item removed
   */
  public T removeAfter(NodeSL<T> here){
    if(here == null) {                  // if the node is null, it means remove the head
        T item = removeFirst();
        return item;
    } else {
        if(here.getNext()==null){        // cannot remove after the tail of the list or from an empty list
            throw new MissingElementException();
        }
        
        T item = here.getNext().getData(); // save the data of the item being deleted
        here.setNext(here.getNext().getNext()); // pass over the item being deleted, jvm garbage collector takes care of it
        if(here.getNext() == null){ // if this is now the tail, update the tail pointer in the linkedList
            this.tail = here;
        }
        return item; 
    }
  }

  /**
   * Returns a count of the number of elements in the list
   * @return current number of nodes
   */
  public int size(){
    int size = 0;
    if(this.head == null){ // empty list
        return size;
    } else {
        for (NodeSL<T> node = this.head; node.getNext() != null; node = node.getNext()){
            size += 1;
        }
        size += 1; // account for the tail
    }
    return size;
  }

    /** 
   *  Makes a copy of elements from the original list
   *  @param here  starting point of copy
   *  @param n  number of items to copy
   *  @return the copied list
   */
  public SLL<T> subseqByCopy(NodeSL<T> here, int n){
    SLL<T> subsequence = new SLL<T>();

    for (int i = 0; i < n; i++){
        subsequence.addLast(here.getData());
        here = here.getNext();
    }
    return subsequence;
  }

    /**
   *  Places copy of the provided list into this after the specified node.
   *  @param list  the list to splice in a copy of
   *  @param afterHere  marks the position in this where the new list should go
   */
  public void spliceByCopy(SLL<T> list, NodeSL<T> afterHere){
    SLL<T> copyList = list.subseqByCopy(list.getHead(), list.size());

    if(copyList.isEmpty()){
        return; // nothing to do for splicing in an empty list
    }

    if(afterHere == null){ // insert at beginning, also takes care of case of empty list
        copyList.getTail().setNext(this.head);
        this.head = copyList.getHead();
    } else {
        copyList.getTail().setNext(afterHere.getNext());
        afterHere.setNext(copyList.getHead()); // modify links
    }
  }


  
  public static void main(String[] args) {
    SLL<String> test = new SLL<String>();
    test.addFirst("C");
    test.addFirst("B");
    test.addFirst("A");
    System.out.println(test);
    SLL<String> test2 = new SLL<String>();
    test2.addLast("D");
    test2.addLast("E");
    test2.addLast("F");
    test.spliceByCopy(test2, null);
    System.out.println(test);
  }
}
