package com.example.fn;

import java.util.Locale;

/**
 *
 * @author davidd
 */
public class Coupon {

    public String coupon;

    public Coupon(String coupon) {
        this.coupon = coupon;
    }

    private final static int feistelRounds = 4;
    private final static int randRounds = 4;
    private final static int seed = 12345;

    // modulus for half a string:
    private final static int mod = 60466176; //36^5

    private static int f(int x) {
        // http://en.wikipedia.org/wiki/Linear_congruential_generator
        final int a = 12 + 1;
        final int c = 1361423303;
        x = (x + seed) % mod;
        int r = randRounds;
        while (r-- != 0) {
            x = (a * x + c) % mod;
        }
        return x;
    }

    private static String generate(int i) { //unused
        int a = i / mod;
        int b = i % mod;
        int r = feistelRounds;
        while (r-- != 0) {
            a = (a + f(b)) % mod;
            b = (b + f(a)) % mod;
        }
        return pad5(Integer.toString(a, 36)) + pad5(Integer.toString(b, 36));
    }

    public static String generate(int rate, int review) {

        int k = Integer.valueOf(String.valueOf(rate) + String.valueOf(review)); // rating & price : rrpp, rrppp

        int a = k / mod;
        int b = k % mod;
        int r = feistelRounds;
        while (r-- != 0) {
            a = (a + f(b)) % mod;
            b = (b + f(a)) % mod;
        }

        return pad5(Integer.toString(a, 36)) + pad5(Integer.toString(b, 36));
    }

    public static int validateRate(String coupon) {

        int a = Integer.valueOf(coupon.substring(0, 5), 36);
        int b = Integer.valueOf(coupon.substring(5, 10), 36);
        int r = feistelRounds;
        while (r-- != 0) {
            b = (b - f(a)) % mod;
            a = (a - f(b)) % mod;
        }
        // make the modulus positive:
        a = (a + mod) % mod;
        b = (b + mod) % mod;

        String result = String.valueOf(a * mod + b);

        int rate = Integer.valueOf(result.substring(0, 3));

        return rate;
    }

    
        public static int validateReview(String coupon) {

        int a = Integer.valueOf(coupon.substring(0, 5), 36);
        int b = Integer.valueOf(coupon.substring(5, 10), 36);
        int r = feistelRounds;
        while (r-- != 0) {
            b = (b - f(a)) % mod;
            a = (a - f(b)) % mod;
        }
        // make the modulus positive:
        a = (a + mod) % mod;
        b = (b + mod) % mod;

        String result = String.valueOf(a * mod + b);
        
        int review = Integer.valueOf(result.substring(2));

        return review;
    }    
    
    
    private static String pad5(String s) {
        return String.format("%5s", s).replace(' ', '0').toUpperCase(Locale.ENGLISH);
    }

    private static String pad10(String s) {
        return String.format("%10s", s).replace(' ', '0').toUpperCase(Locale.ENGLISH);
    }


}

