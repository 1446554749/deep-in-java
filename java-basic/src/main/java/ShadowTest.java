public class ShadowTest {

    int x = 0;

    class FirstLevel {
        int x = 1;

        void methodInFirstLevel(int x) {
            System.out.println("x = " + x);
            System.out.println("this.x = " + this.x);
            System.out.println("ShadowTest.this.x = " + ShadowTest.this.x);
        }

    }

    public static void main(String[] args) {
        final ShadowTest shadowTest = new ShadowTest();
        final FirstLevel firstLevel = shadowTest.new FirstLevel();
        firstLevel.methodInFirstLevel(23);
    }
}
