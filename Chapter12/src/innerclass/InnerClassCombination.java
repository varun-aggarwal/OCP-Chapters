package innerclass;

class A {

    void m() {
        System.out.println("outer");
    }
}

public class InnerClassCombination {

    void go() {
        new A().m();
        class A {

            void m() {
                System.out.println("inner");
            }
        }
    }

    class A {

        void m() {
            System.out.println("middle");
        }
    }
}
