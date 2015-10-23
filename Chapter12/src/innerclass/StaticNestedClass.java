package innerclass;

public class StaticNestedClass {

    static class PrintingPress {

        String hi = "Hello from StaticNested Inner class";

        void print() {
            System.out.println(hi);
        }
    }

    public void printfromPrintingPress() {
        PrintingPress printingPress = new PrintingPress();
        printingPress.print();
    }

}
