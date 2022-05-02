/*
Naive
 */
package lintcode;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.function.IntConsumer;

/**
 * 2450 · Start the thread to print hello world
 *
 * The question asks to print hello world.
 *
 * We want you to do this by opening a thread. The code you need to write is a method called
 * run_print_in_thread (runPrintInThread in Java/C++), which requires you not to print in the main thread,
 * so you need to open a new thread and call the print_hello_world (printHelloWorld method in C++) method to
 * print hello word (in Java you need to create a new thread and return any value via accept of print) to do this.
 *
 * You can find a Main.java file in the directory (main.py in Python, Main.cpp in C++)
 * to read and see how your code is called and run.
 *
 * Example
 * Your code should output:
 *
 * hello world
 */
public class _2450_StartThreadPrintHelloWorld {

    public static void main(String[] args) {
        try {
            String outputPath = args[1];
            PrintStream ps = new PrintStream(outputPath);
            IntConsumer print = (x -> {
                try {
                    String name = Thread.currentThread().getName();
                    if ("main".equals(name)) {
                        throw new Exception("You need to start a new thread.");
                    }
                    ps.write("hello world".getBytes(StandardCharsets.UTF_8));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            Solution_2450 solution = new Solution_2450();
            solution.printNumberInMainSubThread(print);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// 这个放在 Solution.java
class Solution_2450 {
    public void printNumberInMainSubThread(IntConsumer intConsumer) throws InterruptedException {
        new Thread(() -> {
            intConsumer.accept(1);
        }).start();
    }
}