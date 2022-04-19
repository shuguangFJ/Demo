package LockTest;

public class Test {

    private int sharedState;

    public void nonSafeAction() {
        while (sharedState < 100000) {
            int pre = sharedState++;
            int post = sharedState;
            if (pre != post - 1) {
                System.out.println(pre + "--" + post);
            }
        }
    }

    public void safeAction() {
        while (sharedState < 100000) {
            synchronized (this) {
                int pre = sharedState++;
                int post = sharedState;
                if (pre != post - 1) {
                    System.out.println(pre + "--" + post);
                }
            }
        }
    }

    public static void main(String[] args) {
        Test test = new Test();

        Thread thread = new Thread() {
            @Override
            public void run() {
                test.safeAction();
            }
        };

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                test.safeAction ();
            }
        };

        thread.start();
        thread1.start();
    }
}
