package com.psy888;

public class Main {

    public static void main(String[] args) {
        // write your code here
        FS tools = new FS();
        long[] laps = new long[10];
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            //ignore
        }


        for (int i = 0; i < 10; i++) {
            long startTime = System.currentTimeMillis();
            tools.copyFileNIO("test.jpg");
            long endTime = System.currentTimeMillis();
            laps[i]= endTime - startTime;
            System.out.println("NIO time = " + laps[i]);

        }
        System.out.println("Среднее значение : " + getAvg(laps));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            //ignore
        }

        for (int i = 0; i < 10; i++) {
            long startTime = System.currentTimeMillis();
            tools.copyFileIO("test2.jpg");
            long endTime = System.currentTimeMillis();
            laps[i]= endTime - startTime;
            System.out.println("IO time = " + laps[i]);
        }
        System.out.println("Среднее значение : " + getAvg(laps));


    }

    public static long getAvg(long[] laps){
        long avg = 0;
        for (int i = 0; i < laps.length; i++) {
            avg +=laps[i];
        }
        return avg/10;
    }
}
