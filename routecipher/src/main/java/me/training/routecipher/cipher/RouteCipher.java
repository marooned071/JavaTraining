package me.training.routecipher.cipher;


import me.training.routecipher.grid.CyclicDirectionSupplier;
import me.training.routecipher.grid.DecryptGridWalkerStepConsumer;
import me.training.routecipher.grid.EncryptGridWalkerStepConsumer;
import me.training.routecipher.grid.GridWalker;
import me.training.routecipher.grid.Point;

public class RouteCipher {

    public static String encrypt(String plainText, int columns, int rows, RouteCycle routeCycle) {
        String toEncrypt = plainText.replaceAll("[ \\-'.?/!]", "").toUpperCase();
        GridWalker gridWalker = GridWalker.create(columns, rows);
        EncryptGridWalkerStepConsumer stepConsumer = EncryptGridWalkerStepConsumer.of(toEncrypt, rows, columns);
        gridWalker.walk(new CyclicDirectionSupplier(routeCycle.getDirectionOrder()),
                stepConsumer, new Point(columns - 1, 0));

        return stepConsumer.result();
    }

    public static String decrypt(String encryptedText, int columns, int rows, RouteCycle routeCycle) {
        GridWalker gridWalker = GridWalker.create(columns, rows);
        DecryptGridWalkerStepConsumer stepConsumer = new DecryptGridWalkerStepConsumer(encryptedText, columns, rows);
        gridWalker.walk(new CyclicDirectionSupplier(routeCycle.getDirectionOrder()), stepConsumer,
                new Point(columns - 1, 0));
        return stepConsumer.result();
    }
}
