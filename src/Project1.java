import java.io.IOException;

public class Project1 {
        public static void main(String[] args) {


            FactoryImpl factory = new FactoryImpl();
            factory.initializeFactory();
            var operations = factory.readOperationsFromFile(args[0]);

            for(var operation : operations){
                var operationNameAndParameters = operation.split("\\s");
                factory.makeOperation(operationNameAndParameters);
            }
            try{
                factory.createOutputFile(args[1]);
            }
            catch (IOException e){
                System.out.println("Something went wrong file creating file");
            }
        }

}