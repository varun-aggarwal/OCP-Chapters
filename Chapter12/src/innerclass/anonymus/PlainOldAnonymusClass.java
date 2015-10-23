package innerclass.anonymus;

public class PlainOldAnonymusClass {

    private String hi = "Hello from PlainOldAnonymus Outer Class";

    public void print() {
        this.hi = "Hello from PlainOldAnonymus Inner Class";

        Print print = new Print() {

            @Override
            public void print(String text) {
                System.out.println(text);

            }

        };

        print.print(hi);
    }

}
