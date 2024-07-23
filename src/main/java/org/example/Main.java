package org.example;

import java.io.PrintStream;
import java.io.Serializable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Supplier;

import static java.lang.invoke.MethodType.methodType;

@SuppressWarnings({"unused", "UnnecessaryBoxing"})
public class Main {

    public static List<Integer> list;

    public static void main(String[] args) throws Throwable {
        Thread t = new Thread( Main::throwException);
        t.setDaemon(true);
        t.start();
        consume(5, 6);
    }

    public static void throwException() {
        throw new RuntimeException();
    }

    public static void runLambda() {
        consume(5, 9f);
        Foo f = (Serializable
            & Foo
            & Marker) () -> 5;
        foo(f);
    }

    public static void consume(int a, float b) {
        System.out.println("a: " + a + "; b: " + b);
    }

    public static void run() {
        callInt(Main::supplyInt);
    }

    static void callInt(Supplier<Integer> r) {
    }

    private static int supplyInt() {
        return 0;
    }

    static void callRunnable(Runnable r) {
        r.run();
    }

    static void doSmth() {
    }

    ;

    interface Heh {
        Number lol();
    }

    interface SupplyObj {
        Object f();
    }

    interface SupplyNum {
        Number f();
    }

    interface SupplyInt {
        Integer f();
    }

    interface Foo extends SupplyObj, SupplyNum, SupplyInt {
    }

    static class FooImpl implements Foo {
        public Integer f() {
            return null;
        }
    }

    static void foo(Foo foo) {
    }

    public void c(int a) {
    }

    public void c(long a) {
    }

    public void c(float a) {
    }

    public void c(double a) {
    }

    public void b(int a, Object b, List<Integer> c) {
    }

    public void a() {
    }

    public void callMethod() throws Exception {
        Method method = this.getClass().getMethod("a");

        method.invoke(this);
    }

    void foo(int a) {
    }

    interface Marker {}

    static void intMethod(Main m, int a) {
        System.out.println(a);
    }

    /*
    static void callHandle() throws Throwable {
        MethodHandle print = System.out::println; // (PrintStream, String)void

        MethodHandle strConcat = String::concat; // (String, String)String


        print = print.bindTo(System.out); // (String)void
        print = MethodHandles.dropArguments(print, 1, String.class, String.class); // (String, String, String)void
        print = MethodHandles.foldArguments(print, 0, strConcat); // (String)void

        print.invoke("a", "b"); // Output: "ab"
    }

     */

    interface I {
        default Number m() {
            return 4;
        }
    }

    interface J extends I {
        default Integer m() {
            return 5;
        }

        int a();

    }

    static void runShiza() {
        runJ(() -> 5);
    }

    static void runJ(J j) {
        runI(j);
    }

    static void runI(I i) {
        System.out.println(i.m());
    }

    public static void lambda$main$0() {
        doSmth();
    }
}