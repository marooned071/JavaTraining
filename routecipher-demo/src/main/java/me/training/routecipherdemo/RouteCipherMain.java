package me.training.routecipherdemo;


import me.training.routecipher.cipher.RouteCycle;
import me.training.routecipher.cipher.RouteCipher;

public class RouteCipherMain {
    public static void main(String[] args) {
        String encrypted = RouteCipher.encrypt("test java modules", 4, 4, RouteCycle.CLOCK_WISE);
        System.out.println(encrypted);
        String decrypted = RouteCipher.decrypt(encrypted, 4,4, RouteCycle.CLOCK_WISE);
        System.out.println(decrypted);

    }
}
