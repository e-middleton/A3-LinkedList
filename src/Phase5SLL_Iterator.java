/** Operations to define for the SLL_Iterator class */
public interface Phase5SLL_Iterator<T> {
    /**
     * Tests whether there are any more
     * 
     * @return T/F is it safe to call next()?
     */
    public boolean hasNext();

    /**
     * Returns next or throws an exception
     * and advances the iterator
     * 
     * @return the next element
     */
    public T next();

    /**
     * Sets the data for the element just passed
     * 
     * @param data value to set
     */
    public void set(T data);

    /**
     * Returns the data for the element just passed
     * 
     * @return data value in the element just passed
     */
    public T get();

    /**
     * Inserts a node with the specified data
     * Cannot be called twice in a row without intervening next()
     * 
     * @param data the value to insert
     */
    public void add(T data);

    /**
     * Removes the node just passed
     * Cannot be called twice in a row without intervening next()
     */
    public void remove();
}
