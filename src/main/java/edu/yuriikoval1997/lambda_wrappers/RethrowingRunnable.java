package edu.yuriikoval1997.lambda_wrappers;

public interface RethrowingRunnable<E extends Exception> {
    
    void run() throws E;
}
