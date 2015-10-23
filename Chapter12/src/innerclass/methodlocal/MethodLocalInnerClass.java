package innerclass.methodlocal;

public class MethodLocalInnerClass {

    private String hi = "Hello from MethodLocal Outer Class";

    public void print() {

        class Print {

            public Print() {
                hi = "Hello from MethodLocal Inner class";
                System.out.println(hi);
            }
        }

        Print print = new Print();
    }
}
