package innerclass;

import innerclass.anonymus.ArgumentDefinedAnonymusInnerClass;
import innerclass.anonymus.PlainOldAnonymusClass;
import innerclass.methodlocal.MethodLocalInnerClass;
import innerclass.regular.RegularInnerClass;
import innerclass.staticnested.StaticNestedClass;
import innerclass.staticnested.StaticNestedClass.PrintingPress;

public class Main {

    public static void main(String... args) {

        Main application = new Main();

        application.demoRegularInnerClass();

        application.demoRegularInnerClassReferencedByOutsideClass();

        application.demoMethodLocalInnerClass();

        application.demoPlainOldAnonymusInnerClass();

        application.demoArgumentDefinedInnerClass();

        application.demoStaticNestedClass();

        application.demoStaticNestedClassOuterReference();

        application.innerClassCombinationDemo();
    }

    private void demoRegularInnerClassReferencedByOutsideClass() {
        System.out.println(
            "==================RegularInnerClass inner class accessed by Oustide class ==================");
        RegularInnerClass regularInnerClass = new RegularInnerClass();
        regularInnerClass.new Print();
    }

    private void demoRegularInnerClass() {
        System.out
            .println("==================Regular Inner Class accessig Inner class ========================");
        RegularInnerClass regularInnerClass = new RegularInnerClass();
        regularInnerClass.new Print();
    }

    private void innerClassCombinationDemo() {

        System.out.println("==================InnerClassCombination======================================");

        InnerClassCombination innerClassCombination = new InnerClassCombination();
        innerClassCombination.go();
    }

    private void demoMethodLocalInnerClass() {
        System.out.println("==================MethodLocalInnerClass======================================");
        MethodLocalInnerClass methodLocalInnerClass = new MethodLocalInnerClass();
        methodLocalInnerClass.print();
    }

    private void demoPlainOldAnonymusInnerClass() {
        System.out.println("==================PlainOldAnonymusClass======================================");
        PlainOldAnonymusClass plainOldAnonymusClass = new PlainOldAnonymusClass();
        plainOldAnonymusClass.print();
    }

    private void demoArgumentDefinedInnerClass() {
        System.out.println(
            "==================ArgumentDefinedAnonymusInnerClass======================================");
        ArgumentDefinedAnonymusInnerClass argumentDefinedAnonymusInnerClass =
            new ArgumentDefinedAnonymusInnerClass();
        argumentDefinedAnonymusInnerClass.printUsingPrintingPressClass();

    }

    private void demoStaticNestedClass() {
        System.out.println(
            "==================StaticNestedClass Inner Reference======================================");
        StaticNestedClass staticNestedClass = new StaticNestedClass();
        staticNestedClass.printfromPrintingPress();

    }

    private void demoStaticNestedClassOuterReference() {
        System.out.println(
            "==================StaticNestedClass Outer Reference to Inner class===========================");
        PrintingPress staticNestedInnerClass = new StaticNestedClass.PrintingPress();
        staticNestedInnerClass.print();

    }
}
