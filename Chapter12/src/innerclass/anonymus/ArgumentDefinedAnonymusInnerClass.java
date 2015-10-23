package innerclass.anonymus;

interface Print {

    void print(String text);
}

class PrintingPress {

    void printing(Print p, String text) {
        p.print(text);
    }
}

public class ArgumentDefinedAnonymusInnerClass {

    private String hi = "Hello from ArgumentDefinedAnonymus Outer Class";

    public void printUsingPrintingPressClass() {
        hi = "Hello from ArgumentDefinedAnonymus Inner class";

        PrintingPress printingPress = new PrintingPress();
        printingPress.printing(new Print() {

            @Override
            public void print(String text) {
                System.out.println(hi);
            }
        }, hi);
    }

}
