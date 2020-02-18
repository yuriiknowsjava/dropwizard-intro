package edu.yuriikoval1997.lambda_wrappers;

public class LambdaWrapper {

    private LambdaWrapper() {
    }

    public static void runnable(RethrowingRunnable<Exception> rethrowingRunnable) {
        try {
            rethrowingRunnable.run();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
