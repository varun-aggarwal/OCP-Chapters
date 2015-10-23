package innerclass.staticnested;

public class StaticNestedClass {

    public static class PrintingPress {

        String hi = "Hello from StaticNested Inner class";

        public void print() {
            System.out.println(hi);
        }
    }

    public void printfromPrintingPress() {
        PrintingPress printingPress = new PrintingPress();
        printingPress.print();
    }

}
