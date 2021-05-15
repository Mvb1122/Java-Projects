package com.company;

public class Main {

    public static void main(String[] args) {
	/*
	Just for funzies (and because I need practice in java,
	I'm going to write a problem from khan in Java.
	 */
        int x = 5;
        int sum = 0;
        while (x > 0) {
            x = x - 1;
            sum += x; // sum = sum + x
        }
        System.out.println(sum);
    }
}
