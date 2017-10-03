package org.launchcode;

import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) {
        double S = 151.89; // Stock price
        double P = 152.5; // Strike price
        double r = 0.0177; // Interest rate - 8%
        int N = 400; // Number of steps (randomizations)
        double T = (double) 39/365; // Number of years until option expiration
        int M = 100000; // Number of computations (number of possible payoffs calculated)
        double sigma = 0.225; // Volatility - 20%
        double totalPayoff = 0;
        double averagePayoff;
        double compundRate;
        double O; // Premium (option price)
        int m = 0;
        while (m < M) {
            totalPayoff += generatePayoffs(N, S, r, T, sigma, P);
            m++;
        }
        averagePayoff = totalPayoff/M;
        compundRate = getCompundCoefficient(r, T, N);
        O = averagePayoff/compundRate;
        DecimalFormat df = new DecimalFormat("##.####");
        System.out.println(df.format(O));
    }

    public static double generatePayoffs(int N, double S, double r, double T, double sigma, double P){
        int eta;  // The up-or-down parameter
        int n = 0;
        double tempS = S;
        while (n < N) {
            double rd = Math.random();
            if (rd > 0.5) {
                eta = 1;
            } else {
                eta = -1;
            }
            tempS = tempS + r*tempS*(T/N) + sigma*eta*tempS*Math.sqrt(T/N);
            n++;
        }
        return Math.max(tempS - P, 0); // We are going to exercise our right to buy only if S > P
    }

    public static double getCompundCoefficient(double r, double T, int N) {
        double coefficient = Math.pow(1+r*(T/N), (double) N);
        return coefficient;
    }

}