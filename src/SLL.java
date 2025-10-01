/**
 * Class to implement a singly linked list
 *
 * @author Emily Middleton
 * @date Oct 1, 2025
 * @version Fall 2025
 */
 public class SLL<T> implements Phase1SLL<T>, Phase2SLL<T>, Phase4SLL<T>{ // they both need <T> so that it's clear they're the same generic type
    /**
     * pointer to the beginning node of the linked list
     */
    private NodeSL<T> head;
    /**
     * Pointer to the last node in the linked list
     */
    private NodeSL<T> tail;


    /**
     * Constructor for an empty linked list
     */
    public SLL(){
        this.head = null;
        this.tail = null;
    }

    /**
     * Copy constructor for linked lists that
     * makes a deep copy of the linked list passed as an argument.
     * @param list the list being copied
     */
    public SLL(SLL<T> list){
        if (list.isEmpty()) {       // edge case of empty list
            this.head = null;
            this.tail = null;   
        } else {                    // typical case of nonempty list
            for(NodeSL<T> node = list.getHead(); node.getNext() != null; node = node.getNext()){    // copies everything except tail
                this.addLast(node.getData());
            }
            this.addLast(list.tail.getData());
        }
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
  
  /**
   * Converts the linked list to a string representation
   * @return String representation of the linked list object
   */
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
   * Inserts an item at the tail of the list,
   * updating the reference to the tail node
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
    if(here == null){
        addFirst(v); // add at the beginning if the node is null
        return;
    }

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
   * Makes a deep copy of elements from the original list
   * returning them as a separate linked list.
   * @param here starting point of the copy
   * @param n number of items to copy
   * @return the copied list
   */
  public SLL<T> subseqByCopy(NodeSL<T> here, int n){
    SLL<T> subsequence = new SLL<T>();
    if(here == null){        // if the node to begin at is null, it means begin at the head of the list
        here = this.head;
    }
    for (int i = 0; i < n; i++){
        subsequence.addLast(here.getData());
        here = here.getNext();
        if ((here == null) && (i+1 < n)){
            throw new MissingElementException();        // if there are too many elements requested
        }
    }
    return subsequence;
  }

  /**
   * Places copy of the provided list into the current list after the specified node.
   * @param list the list to splice in a deep copy of into this object.
   * @param afterHere marks the position in this list where the inserted list should begin.
   */
  public void spliceByCopy(SLL<T> list, NodeSL<T> afterHere){
    if(this == list){
        throw new SelfInsertException();        // no self splicing
    }

    SLL<T> copyList = new SLL<T>(list); // copy constructor

    if(copyList.isEmpty()){
        return; // nothing to do for splicing in an empty list, this remains unchanged
    }

    if(afterHere == null){ // insert at beginning, also takes care of case of empty list
        copyList.tail.setNext(this.head);
        this.head = copyList.head;
    } else {
        copyList.tail.setNext(afterHere.getNext());
        afterHere.setNext(copyList.head); // modify links
    }
  }
  
  /**
   * Extracts a subsequence of nodes and returns them as a new list,
   * removing them from the original list.
   * @param afterHere  marks the node just before the extraction
   * @param toHere  marks the node where the extraction ends
   * @return  the new list
   */
  public SLL<T> subseqByTransfer(NodeSL<T> afterHere, NodeSL<T> toHere){
    SLL<T> extraction = new SLL<T>();

    if(afterHere == null){
        // update new list links
        extraction.head = this.head;
        extraction.tail = toHere;

        // update old list links
        this.head = toHere.getNext(); // tail is generally unchanged unless toHere == this.tail

        if (toHere == this.tail) { // the entire list has been extracted
            this.tail = null;
        }

        toHere.setNext(null); // clip out nodes from old list

    } else if (afterHere == toHere){      // if the two nodes from-to are identical, it returns an empty list
        return extraction;
    } else {
        extraction.head = afterHere.getNext();
        extraction.tail = toHere;
        afterHere.setNext(toHere.getNext()); // clip out old elements

        if(toHere == this.tail){    // edge case for tail included in extraction
            this.tail = afterHere;
        }
        toHere.setNext(null); // separate out so it doesn't point to old list
    }
    return extraction;
  }

  /** 
   *  Takes the provided list and inserts its elements into this current list
   *  after the specified node.  The inserted list ends up empty.
   *  @param list  the list to splice in.  Becomes empty after the call
   *  @param afterHere  Marks the place where the new elements are inserted
   */
  public void spliceByTransfer(SLL<T> list, NodeSL<T> afterHere){
    if (this == list) {
        throw new SelfInsertException(); // you cannot both clear out a list and make it longer
    }

    if(list.isEmpty()) { // case for an empty list, nothing is done
        return;
    }
    
    if (afterHere == null) {
        list.tail.setNext(this.head); // if it is null, that means insert before the head
        this.head = list.head;
        list.head = list.tail = null; // clear out old list
    } else {
        list.tail.setNext(afterHere.getNext());
        afterHere.setNext(list.head);
        if (afterHere == this.tail) {
            this.tail = list.tail; // update the tail
        }
        list.head = list.tail = null; // clear out old list
    }
  }
}
