import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;

public class FactoryImpl implements Factory {

    private Holder first;
    private Holder last;
    private int size = 0;

    Map<String, Consumer<Vector<Integer>>> methods = new HashMap<>();

    @Override
    public void addFirst(Product product) {
        Holder firstHolder = first;
        Holder newHolder = new Holder(null, product, firstHolder);
        first = newHolder;
        if(firstHolder == null){
            last = newHolder;
        }
        else{
            firstHolder.setPreviousHolder(newHolder);
        }
        size++;
    }
    @Override
    public void addLast(Product product) {
        Holder lastHolder = last;
        Holder newHolder = new Holder(lastHolder, product, null);
        last = newHolder;
        if(lastHolder == null){
            first = newHolder;
        }
        else{
            lastHolder.setNextHolder(newHolder);
        }
        size++;
    }
    @Override
    public Product removeFirst() throws NoSuchElementException {
        Holder firstHolder = first;
        checkHolderExists(firstHolder);
        Product removedProduct = firstHolder.getProduct();
        Holder nextHolder = firstHolder.getNextHolder();
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
        Holder lastHolder = last;
        checkHolderExists(lastHolder);
        Product removedProduct = last.getProduct();
        Holder previousHolder = last.getPreviousHolder();
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
        Product toBeUpdatedProduct = getProductWithId(id);
        toBeUpdatedProduct.setValue(value);
        return toBeUpdatedProduct;
    }

    @Override
    public Product get(int index) throws IndexOutOfBoundsException {
        checkPositionIndex(index);
        Holder holderOfProductInGivenIndex = getHolderAtIndex(index);
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
        //removeHolder(getHolderWithValue());
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
            throw new IndexOutOfBoundsException("Index out of bounds");
    }

    private void addBefore(Product product, Holder holder) {
        Holder previousHolder = holder.getPreviousHolder();
        Holder newHolder = new Holder(previousHolder, product, holder);
        holder.setPreviousHolder(newHolder);
        if (previousHolder == null)
            first = newHolder;
        else
            previousHolder.setNextHolder(newHolder);
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
            throw new IndexOutOfBoundsException("Index out of bounds");
    }
    private Product getProductWithId(int id){
        Holder tempHolder = first;
        for(int i = 0; i < size; i++){
            if(tempHolder.getProduct().getId() == id){
                return tempHolder.getProduct();
            }
            tempHolder = tempHolder.getNextHolder();
        }
        throw new NoSuchElementException("Factory is empty.");
    }
    private boolean isHolderExists(Holder holder){

        return holder != null;
    }
    private void checkHolderExists(Holder holder){
         if(!isHolderExists(holder)){
             throw new NoSuchElementException("Factory is empty.");
         }
    }
    private Product removeHolder(int index){
        Holder holderOfProductInGivenIndex = getHolderAtIndex(index);
        Product toBeRemovedProduct = holderOfProductInGivenIndex.getProduct();
        Holder previousHolder = holderOfProductInGivenIndex.getPreviousHolder();
        Holder nextHolder = holderOfProductInGivenIndex.getNextHolder();
        holderOfProductInGivenIndex.setProduct(null);
        holderOfProductInGivenIndex.setPreviousHolder(null);
        previousHolder.setNextHolder(nextHolder);
        nextHolder.setPreviousHolder(previousHolder);
        size--;
        return toBeRemovedProduct;
    }

    public List<String> readOperationsFromFile(String filePath){
        List<String> listOfOperations = Collections.emptyList();
        try{
            listOfOperations = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        }
        catch (IOException ex){
            System.out.println("Something went wrong while reading the file.");
        }
        return listOfOperations;
    }
    public String print(){
        StringBuilder toBePrintedString = new StringBuilder("{");
        for(int i = 0; i < size; i++){
            Holder holderAtIndex = getHolderAtIndex(i);
            toBePrintedString.append(holderAtIndex.getProduct().toString());
        }
        toBePrintedString.append("}");
        System.out.println(toBePrintedString);
        return toBePrintedString.toString();
    }
    public void initializeFactory(){
        methods.put("AF", (k) -> this.addFirst(new Product(k.get(0), k.get(1))));
        methods.put("AL", (k) -> this.addLast(new Product( k.get(0),k.get(1))));
        methods.put("A", (k) -> this.add((Integer) k.get(0), new Product(k.get(1), k.get(2))));
        methods.put("RF", (k) -> this.removeFirst());
        methods.put("RL", (k) -> this.removeLast());
        methods.put("RI", (k) -> this.removeIndex(k.get(0)));
        methods.put("RP", (k) -> this.removeProduct(k.get(0)));
        methods.put("F", (k) -> this.find(k.get(0)));
        methods.put("G", (k) -> this.get(k.get(0)));
        methods.put("U", (k) -> this.update(k.get(0), k.get(1)));
        methods.put("FD", (k) -> this.filterDuplicates());
        methods.put("P", (k) -> this.print());
        methods.put("R", (k) -> this.reverse());

    }
    public void makeOperation(String[] operationAndParameters)
    {
        var MethodName = operationAndParameters[0];
        Vector<Integer> listOfParameters = new Vector<Integer>();
        for(int i = 1; i < operationAndParameters.length; i++){
            listOfParameters.add(Integer.parseInt(operationAndParameters[i]));
        }
        try{
            if(methods.containsKey(MethodName)){
                methods.get(MethodName).accept(listOfParameters);
            }
        }
        catch (NoSuchElementException ex){
            System.out.println(ex.getMessage());
        }
        catch (IndexOutOfBoundsException ex){
            System.out.println(ex.getMessage());
        }
    }

}