import java.util.LinkedList;
import java.util.NoSuchElementException;

public class FactoryImpl implements Factory {

    private Holder first;
    private Holder last;
    private int size = 0;

    @Override
    public void addFirst(Product product) {
        final Holder firstHolder = first;
        final Holder newHolder = new Holder(null, product, firstHolder);
        first = newHolder;
        if(firstHolder == null){
            first = newHolder;
        }
        else{
            first.setPreviousHolder(newHolder);
        }
        size++;
    }
    @Override
    public void addLast(Product product) {
        final Holder lastHolder = last;
        final Holder newHolder = new Holder(lastHolder, product, null);
        last = newHolder;
        if(lastHolder == null){
            last = newHolder;
        }
        else{
            last.setNextHolder(newHolder);
        }
        size++;
    }
    @Override
    public Product removeFirst() throws NoSuchElementException {
        final Holder firstHolder = first;
        checkHolderExists(firstHolder);
        final Product removedProduct = firstHolder.getProduct();
        final Holder nextHolder = firstHolder.getNextHolder();
        firstHolder.setProduct(null);
        firstHolder.setNextHolder(null);
        first = nextHolder;
        if (nextHolder == null)
            last = null;
        else
            nextHolder.setPreviousHolder(null);
        size--;
        return removedProduct;
    }

    @Override
    public Product removeLast() throws NoSuchElementException {
        final Holder lastHolder = last;
        checkHolderExists(lastHolder);
        final Product removedProduct = last.getProduct();
        final Holder previousHolder = last.getPreviousHolder();
        lastHolder.setProduct(null);
        lastHolder.setPreviousHolder(null);
        if(previousHolder == null)
            first = null;
        else
            previousHolder.setNextHolder(null);
        size--;
        return removedProduct;
    }

    @Override
    public Product find(int id) throws NoSuchElementException {

        return getProductWithId(id);

    }

    @Override
    public Product update(int id, Integer value) throws NoSuchElementException {
        final Product toBeUpdatedProduct = getProductWithId(id);
        toBeUpdatedProduct.setValue(value);
        return toBeUpdatedProduct;
    }

    @Override
    public Product get(int index) throws IndexOutOfBoundsException {
        checkPositionIndex(index);
        final Holder holderOfProductInGivenIndex = getHolderAtIndex(index);
        return holderOfProductInGivenIndex.getProduct();
    }

    @Override
    public void add(int index, Product product) throws IndexOutOfBoundsException {
        checkPositionIndex(index);

        if (index == size)
            addLast(product);
        else
            addBefore(product, getHolderAtIndex(index));

    }

    @Override
    public Product removeIndex(int index) throws IndexOutOfBoundsException {
        checkPositionIndex(index);
        if(index == size)
            return removeLast();
        else if (index == 0)
            return removeFirst();
        else
            return removeHolder(index);
    }

    @Override
    public Product removeProduct(int value) throws NoSuchElementException {
        return null;
    }

    @Override
    public int filterDuplicates() {
        return 0;
    }

    @Override
    public void reverse() {

    }

    private boolean isIndexInPosition(int index){

        return index >= 0 && index <= size;
    }
    private void checkPositionIndex(int index) {
        if (!isIndexInPosition(index))
            throw new IndexOutOfBoundsException("Index " + index + "is out of bounds");
    }

    private void addBefore(Product product, Holder holder) {
        final Holder previousHolder = holder.getPreviousHolder();
        final Holder newHolder = new Holder(previousHolder, product, holder);
        holder.setPreviousHolder(newHolder);
        if (previousHolder == null)
            first = newHolder;
        else
            newHolder.setNextHolder(newHolder);
        size++;
    }

    private Holder getHolderAtIndex(int index){
        if (index <= size) {
            Holder tempHolder = first;
            for (int i = 0; i < index; i++)
                tempHolder = tempHolder.getNextHolder();
            return tempHolder;
        }
        else
            throw new IndexOutOfBoundsException("Index " + index + "is out of bounds");
    }
    private Product getProductWithId(int id){
        Holder tempHolder = first;
        for(int i = 0; i < size; i++){
            if(tempHolder.getProduct().getId() == id){
                return tempHolder.getProduct();
            }
            tempHolder = tempHolder.getNextHolder();
        }
        throw new NoSuchElementException("There is no product with the given id " + id);
    }
    private boolean isHolderExists(Holder holder){
        return holder != null;
    }
    private void checkHolderExists(Holder holder){
         if(!isHolderExists(holder)){
             throw new NoSuchElementException("There is no product.");
         }
    }
    private Product removeHolder(int index){
        final Holder holderOfProductInGivenIndex = getHolderAtIndex(index);
        final Product toBeRemovedProduct = holderOfProductInGivenIndex.getProduct();
        final Holder previousHolder = holderOfProductInGivenIndex.getPreviousHolder();
        final Holder nextHolder = holderOfProductInGivenIndex.getNextHolder();
        holderOfProductInGivenIndex.setProduct(null);
        holderOfProductInGivenIndex.setPreviousHolder(null);
        previousHolder.setNextHolder(nextHolder);
        nextHolder.setPreviousHolder(previousHolder);
        size--;
        return toBeRemovedProduct;
    }
}