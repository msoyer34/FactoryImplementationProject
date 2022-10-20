



public class Project1 {
        public static void main(String[] args) {
        FactoryImpl factory = new FactoryImpl();
        factory.addFirst(new Product(0,1));
        factory.add(1, new Product(2,33));
        factory.add(2, new Product(3,33));
        factory.add(3, new Product(4,33));
        factory.add(4, new Product(5,33));
        factory.add(5, new Product(6,33));

        factory.add(3, new Product(7,33));

        factory.removeIndex(1);
        factory.removeIndex(3);
        try {
            var product1 = factory.find(2);
            System.out.println(product1.getValue());
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
}