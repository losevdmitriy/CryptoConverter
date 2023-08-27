package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scr = new Scanner(System.in);
        System.out.println("How much cryptocurrency do you want to convert?");
        double fromValue = scr.nextDouble();
        scr.nextLine();
        System.out.println("What cryptocurrency do you want to convert from?");
        String convertFrom = scr.nextLine().toUpperCase();
        System.out.println("What cryptocurrency do you want to convert to?");
        String convertTo = scr.nextLine().toUpperCase();
        HTTPReq newHTTPReq = new HTTPReq();
        try {
            newHTTPReq.sendRequest(convertFrom, convertTo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        double result = (fromValue * newHTTPReq.getFromPrice()) / newHTTPReq.getToPrice();
        System.out.println(fromValue + " in " + convertFrom + " = " + result + " in " + convertTo);
        System.out.println("Current "+ convertFrom + " price: " + newHTTPReq.getFromPrice());
        System.out.println("Current " + convertTo + " price: " + newHTTPReq.getToPrice());
    }
}
