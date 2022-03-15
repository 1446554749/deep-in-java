/**
 * https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html
 */
public class OuterClass {

    String str = "i am str from outer class";

    public class InnerClass {
        private void accessMembers() {
            System.out.println("---InnerClass----");
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        final OuterClass outerClass = new OuterClass();
        final InnerClass innerClass = outerClass.new InnerClass();
        innerClass.accessMembers();
    }

    static class StaticNestedClass {

    }

}
