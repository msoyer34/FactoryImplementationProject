import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class FactoryImpl implements Factory {
    private Holder first;
    private Holder last;
    private int size = 0;
    private Vector<String> outputString = new Vector<String>();
    Map<String, Function<Vector<Integer>, Product>> methodsReturnsProducts = new HashMap<>();
    Map<String, Consumer<Vector<Integer>>> methodsWithNoReturns = new HashMap<>();
    Map<String, Function<Vector<Integer>, Integer>> methodsReturnsInteger = new HashMap<>();

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
        checkHolderExists(first);
        Product removedProduct = first.getProduct();
        Holder nextHolder = first.getNextHolder();
        first.setProduct(null);
        first.setNextHolder(null);
        first = nextHolder;
        if (nextHolder == null)
            last = null;
        else
            nextHolder.setPreviousHolder(null);
        size--;
        outputString.add(removedProduct.toString());
        return removedProduct;
    }

    @Override
    public Product removeLast() throws NoSuchElementException {
        checkHolderExists(last);
        Product removedProduct = last.getProduct();
        Holder previousHolder = last.getPreviousHolder();
        last.setProduct(null);
        last.setPreviousHolder(null);
        last = previousHolder;
        if(previousHolder == null)
            first = null;
        else
            previousHolder.setNextHolder(null);
        size--;
        outputString.add(removedProduct.toString());
        return removedProduct;
    }

    @Override
    public Product find(int id) throws NoSuchElementException {

        Product findedProduct = getProductWithId(id);
        outputString.add(findedProduct.toString());
        return findedProduct;

    }

    @Override
    public Product update(int id, Integer value) throws NoSuchElementException {
        Holder updatedProductHolder = getHolderWithProductId(id);
        Product toBeUpdatedProduct = updatedProductHolder.getProduct();
        outputString.add(toBeUpdatedProduct.toString());
        toBeUpdatedProduct.setValue(value);
        updatedProductHolder.setProduct(toBeUpdatedProduct);
        return toBeUpdatedProduct;
    }

    @Override
    public Product get(int index) throws IndexOutOfBoundsException {
        checkPositionIndex(index);
        Holder holderOfProductInGivenIndex = getHolderAtIndex(index);
        Product productInTheGivenIndex = holderOfProductInGivenIndex.getProduct();
        outputString.add(productInTheGivenIndex.toString());
        return productInTheGivenIndex;
    }

    @Override
    public void add(int index, Product product) throws IndexOutOfBoundsException {
        checkPositionIndex(index);
        if (size != 0  && index == size ){
            addLast(product);
        }
        else if(index == 0){
            addFirst(product);
        }
        else{
            addBefore(product, getHolderAtIndex(index));
        }

    }

    @Override
    public Product removeIndex(int index) throws IndexOutOfBoundsException {
        checkPositionIndex(index);
        return removeHolderWithIndex(index);
    }

    @Override
    public Product removeProduct(int value) throws NoSuchElementException {
        Holder ToBeRemovedProductHolder = getHolderAtIndex(getHolderIndexWithProductValue(value));
        return removeHolder(ToBeRemovedProductHolder);
    }

    @Override
    public int filterDuplicates() {
        HashSet<Integer> duplicatedProducts = new HashSet<>();
        Holder tempHolder = first;
        Holder previousHolder = null;
        while(tempHolder != null){
            int valueOfProduct = tempHolder.getProduct().getValue();
            if(duplicatedProducts.contains(valueOfProduct))
            {
                Holder nextHolderOfTemp = tempHolder.getNextHolder();
                if(nextHolderOfTemp != null ){
                    nextHolderOfTemp.setPreviousHolder(previousHolder);
                }
                else{
                    last = previousHolder;
                }
                previousHolder.setNextHolder(tempHolder.getNextHolder());
            }
            else{
                duplicatedProducts.add(valueOfProduct);
                previousHolder = tempHolder;
            }
            tempHolder = tempHolder.getNextHolder();
        }
        var filteredDuplaticateCount = size - duplicatedProducts.size();
        size = size - filteredDuplaticateCount;
        outputString.add(String.valueOf(filteredDuplaticateCount));
        return filteredDuplaticateCount;
    }

    @Override
    public void reverse() {
        Holder temp = first;
        first = last;
        last = temp;

        Holder holder = first;

        while (holder != null) {
            temp = holder.getNextHolder();
            holder.setNextHolder(holder.getPreviousHolder());
            holder.setPreviousHolder(temp);
            holder = holder.getNextHolder();
        }
        print();
    }


    private boolean isIndexInPosition(int index){

        return index >= 0 && index <= size;
    }
    private void checkPositionIndex(int index) {
        if (!isIndexInPosition(index))
            throw new IndexOutOfBoundsException("Index out of bounds.");
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
                if(tempHolder != null){
                    tempHolder = tempHolder.getNextHolder();
                }
                else{
                    throw new NoSuchElementException("Product not found.");
                }
            if(tempHolder != null){
                return tempHolder;
            }
            else{
                throw new NoSuchElementException("Index out of bounds.");
            }
        }
        else
            throw new IndexOutOfBoundsException("Index out of bounds.");
    }
    private Product getProductWithId(int id){
        Holder tempHolder = first;
        for(int i = 0; i < size; i++){
            if(tempHolder.getProduct().getId() == id){
                return tempHolder.getProduct();
            }
            tempHolder = tempHolder.getNextHolder();
        }
        throw new NoSuchElementException("Product not found.");
    }
    private int getHolderIndexWithProductValue(int value){
        Holder tempHolder = first;
        for(int i = 0; i < size; i++){
            if(tempHolder != null){
                if(tempHolder.getProduct().getValue() == value){
                    return i;
                }
                if(tempHolder.getProduct() == null){
                    throw new NoSuchElementException("Product not found.");
                }
            }
            else{
                throw new NoSuchElementException("Product not found.");
            }
            tempHolder = tempHolder.getNextHolder();

        }
        throw new NoSuchElementException("Product not found.");
    }
    private Holder getHolderWithProductId(int id){
        Holder tempHolder = first;
        for(int i = 0; i < size; i++){
            if(tempHolder.getProduct().getId() == id){
                return tempHolder;
            }
            tempHolder = tempHolder.getNextHolder();
        }
        throw new NoSuchElementException("Product not found.");
    }
    private boolean isHolderExists(Holder holder){

        return holder != null;
    }
    private void checkHolderExists(Holder holder){
         if(!isHolderExists(holder)){
             throw new NoSuchElementException("Factory is empty.");
         }
    }
    private Product removeHolderWithIndex(int index){
        Holder holderOfProductInGivenIndex = getHolderAtIndex(index);
        Product toBeRemovedProduct = holderOfProductInGivenIndex.getProduct();
        Holder previousHolder = holderOfProductInGivenIndex.getPreviousHolder();
        Holder nextHolder = holderOfProductInGivenIndex.getNextHolder();
        if(previousHolder == null){
            first = nextHolder;
        }
        else{
            previousHolder.setNextHolder(nextHolder);
            holderOfProductInGivenIndex.setPreviousHolder(null);
        }
        if(nextHolder == null){
            last = previousHolder;
        }
        else{
            nextHolder.setPreviousHolder(previousHolder);
            holderOfProductInGivenIndex.setNextHolder(null);
        }
        holderOfProductInGivenIndex.setProduct(null);
        size--;
        outputString.add(toBeRemovedProduct.toString());
        return toBeRemovedProduct;
    }
    private Product removeHolder(Holder holder){
        Product toBeRemovedProduct = holder.getProduct();
        Holder previousHolder = holder.getPreviousHolder();
        Holder nextHolder = holder.getNextHolder();

        if (previousHolder == null) {
            first = nextHolder;
        } else {
            previousHolder.setNextHolder(nextHolder);
            holder.setPreviousHolder(null);
        }

        if (nextHolder == null) {
            last = previousHolder;
        } else {
            nextHolder.setPreviousHolder(previousHolder);
            holder.setNextHolder(null);
        }
        holder.setProduct(null);
        holder = null;
        size--;
        outputString.add(toBeRemovedProduct.toString());
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
    public void print(){
        StringBuilder toBePrintedString = new StringBuilder("{");
        for(int i = 0; i < size; i++){
            Holder holderAtIndex = getHolderAtIndex(i);
            if(i == size - 1 ){
                toBePrintedString.append(holderAtIndex.getProduct().toString());
            }
            else{
                toBePrintedString.append(holderAtIndex.getProduct().toString() + ",");
            }
        }
        toBePrintedString.append("}");
        outputString.add(toBePrintedString.toString());
    }
    public void initializeFactory(){
        methodsWithNoReturns.put("AF", (k) -> this.addFirst(new Product(k.get(0), k.get(1))));
        methodsWithNoReturns.put("AL", (k) -> this.addLast(new Product( k.get(0),k.get(1))));
        methodsWithNoReturns.put("A", (k) -> this.add((Integer) k.get(0), new Product(k.get(1), k.get(2))));
        methodsWithNoReturns.put("R", (k) -> this.reverse());
        methodsReturnsProducts.put("RF", (k) -> this.removeFirst());
        methodsReturnsProducts.put("RL", (k) -> this.removeLast());
        methodsReturnsProducts.put("RI", (k) -> this.removeIndex(k.get(0)));
        methodsReturnsProducts.put("RP", (k) -> this.removeProduct(k.get(0)));
        methodsReturnsProducts.put("F", (k) -> this.find(k.get(0)));
        methodsReturnsProducts.put("G", (k) -> this.get(k.get(0)));
        methodsReturnsProducts.put("U", (k) -> this.update(k.get(0), k.get(1)));
        methodsReturnsInteger.put("FD", (k) -> this.filterDuplicates());
        methodsWithNoReturns.put("P", (k) -> this.print());

    }
    public void makeOperation(String[] operationAndParameters)
    {
        var methodName = operationAndParameters[0];
        Vector<Integer> listOfParameters = new Vector<Integer>();
        for(int i = 1; i < operationAndParameters.length; i++){
            listOfParameters.add(Integer.parseInt(operationAndParameters[i]));
        }
        try{
            if(methodsWithNoReturns.containsKey(methodName)){
                methodsWithNoReturns.get(methodName).accept(listOfParameters);
            }
            else if(methodsReturnsProducts.containsKey(methodName)){
                var x = methodsReturnsProducts.get(methodName).apply(listOfParameters);
            }
            else if(methodsReturnsInteger.containsKey(methodName)){
                var x = methodsReturnsInteger.get(methodName).apply(listOfParameters);
            }
        }
        catch (NoSuchElementException ex){
            outputString.add(ex.getMessage());
        }
        catch (IndexOutOfBoundsException ex){
            outputString.add(ex.getMessage());
        }
    }
    public void createOutputFile(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        for(var line: outputString ){
            fw.write(line + "\n");
        }
        fw.close();
    }

}