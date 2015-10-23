package innerclass.regular;

public class RegularInnerClass {

    private String hi = "Hello from Outer Class";

    public class Print {

        public Print() {
            hi = "Hello from inner class";
            System.out.println(hi);
        }
    }

    public void print() {
        Print print = new Print();
    }
}
