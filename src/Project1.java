public class Project1 {
        public static void main(String[] args) {


            FactoryImpl factory = new FactoryImpl();
            factory.initializeFactory();
            var operations = factory.readOperationsFromFile(args[0]);

            for(var operation : operations){
                var operationNameAndParameters = operation.split("\\s");
                factory.makeOperation(operationNameAndParameters);
            }


//            StringBuilder s1 = new StringBuilder("{");
//            for(var operation : operations){
//                s1.append(operation);
//            }
//            s1.append("}");
//            System.out.println(s1);
        }

}