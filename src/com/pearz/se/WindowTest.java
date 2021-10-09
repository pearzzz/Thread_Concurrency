package com.pearz.se;

public class WindowTest {
    public static void main(String[] args) {
        Window window1 = new Window("窗口一");
        Window window2 = new Window("窗口二");
        Window window3 = new Window("窗口三");

        window1.start();
        window2.start();
        window3.start();
    }
}

class Window extends Thread {
    public Window() {
    }

    public Window(String name) {
        super(name);
    }

    private static int ticket = 1;

    @Override
    public void run() {
        while (ticket <= 100) {
            System.out.println(getName() + "：卖出票号" + ticket++);
//            ++ticket;
        }
    }
}
