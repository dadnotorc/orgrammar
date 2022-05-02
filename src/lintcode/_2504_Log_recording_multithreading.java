package lintcode;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.*;
import java.io.*;
import java.util.concurrent.CountDownLatch;

/**
 * 2504 · Log recording with multithreading
 * Easy
 * #Thread Pool, # Concurrency
 *
 * This question will simulate the logging process.
 *
 * You will be given a series of files that you can run directly,
 * and when run the system will log a total of 16 logs in about 16 seconds (once per second),
 * so you will get a Time Limit Exceeded evaluation.
 * To speed up the logging, you will need to rewrite some of the code.
 *
 * In the initial code, the createLog function in the Solution.java
 * will loop through the background function ParseLog a total of 16 times.
 * The thread executing parseLog will sleep for 1 second after logging
 * (in other words, this function takes 1 second to execute).
 * After calling parseLog(x), the evaluation system will record a log
 * in the format Log x has been recorded.
 *
 * Your aim is to modify the createLog function so that it can log all 16 logs in 3 seconds.
 * You must use a multi-threaded approach, and calling the function parseLog
 * using the main thread will be judged as an error.
 * You don't have to worry about multithreading messing up the numbering of the logs,
 * the system will automatically sort the logs logged.
 *
 * Example
 * There is no input data for this question, your code should output:
 *
 * Log 1 has been recorded.
 * Log 2 has been recorded.
 * Log 3 has been recorded.
 * Log 4 has been recorded.
 * Log 5 has been recorded.
 * Log 6 has been recorded.
 * Log 7 has been recorded.
 * Log 8 has been recorded.
 * Log 9 has been recorded.
 * Log 10 has been recorded.
 * Log 11 has been recorded.
 * Log 12 has been recorded.
 * Log 13 has been recorded.
 * Log 14 has been recorded.
 * Log 15 has been recorded.
 * Log 16 has been recorded.
 *
 * Challenge
 * Can you solve this problem by using a thread pool ?
 */

// 这部分不能改动
public class _2504_Log_recording_multithreading {
    private static String mainThreadName;
    private static PrintStream printStream;
    private static Map<Integer, String> results = new ConcurrentHashMap<Integer, String>();

    public static void parseLog(int x) {
        try {
            Thread.sleep(1000);
            if (mainThreadName == Thread.currentThread().getName()) {
                Exception exception = new Exception("You should call this method in a sub thread.");
                throw exception;
            }
            results.put(x, "Log " + String.valueOf(x) + " has been recorded.\n");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            String outputPath = args[1];
            printStream = new PrintStream(outputPath);
            mainThreadName = Thread.currentThread().getName();
            Solution_2504.createLog();
            for(Integer i= 1; i < 17; i++) {
                System.out.print((String)results.get(i));
            }
            for(Integer i= 1; i < 17; i++) {
                printStream.write(((String)results.get(i)).getBytes("Utf-8"));
            }
            printStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


// 这个放在 Solution.java

class Solution_2504 {
    public static void createLog() throws Exception {
        // 这是原始代码, 需要被修改
//        for(int i = 1; i < 17; i++) {
//            final int temp = i;
//            Thread thread = new Thread(() -> {
//                _2504_Log_recording_multithreading.parseLog(temp);
//            });
//            thread.start();
//            thread.join();
//        }

        final CountDownLatch countDownLatch =new CountDownLatch(16);

        for(int i = 1; i < 17; i++) {
            final int temp = i;
            Thread thread = new Thread(() -> {
                try{
                    _2504_Log_recording_multithreading.parseLog(temp);
                }finally{
                    countDownLatch.countDown();
                }

            });
            thread.start();
        }
        countDownLatch.await();
    }
}