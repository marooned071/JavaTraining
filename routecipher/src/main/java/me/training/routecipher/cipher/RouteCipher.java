package me.training.routecipher.cipher;


import me.training.routecipher.grid.CyclicDirectionSupplier;
import me.training.routecipher.grid.DecryptGridWalkerStepConsumer;
import me.training.routecipher.grid.EncryptGridWalkerStepConsumer;
import me.training.routecipher.grid.GridWalker;
import me.training.routecipher.grid.Point;

public class RouteCipher {
    public static String encrypt(String plainText, int columns, int rows, RouteCycle routeCycle, int startX,
                                 int startY) {
        validateStartPoint(columns, rows, startX, startY);
        String toEncrypt = plainText.replaceAll("[ \\-'.?/!]", "").toUpperCase();
        GridWalker gridWalker = GridWalker.create(columns, rows);
        EncryptGridWalkerStepConsumer stepConsumer = EncryptGridWalkerStepConsumer.of(toEncrypt, rows, columns);
        gridWalker.walk(new CyclicDirectionSupplier(routeCycle.getDirectionOrder()),
                stepConsumer, new Point(startX, startY));

        return stepConsumer.result();
    }


    public static String encrypt(String plainText, int columns, int rows, RouteCycle routeCycle) {
        return encrypt(plainText, columns, rows, routeCycle, columns - 1, 0);
    }

    public static String decrypt(String encryptedText, int columns, int rows, RouteCycle routeCycle) {
        return decrypt(encryptedText, columns, rows, routeCycle, columns - 1, 0);

    }

    public static String decrypt(String encryptedText, int columns, int rows, RouteCycle routeCycle, int startX,
                                 int startY) {
        validateStartPoint(columns, rows, startX, startY);
        GridWalker gridWalker = GridWalker.create(columns, rows);
        DecryptGridWalkerStepConsumer stepConsumer = new DecryptGridWalkerStepConsumer(encryptedText, columns, rows);
        gridWalker.walk(new CyclicDirectionSupplier(routeCycle.getDirectionOrder()), stepConsumer,
                new Point(startX, startY));
        return stepConsumer.result();
    }

    private static void validateStartPoint(int columns, int rows, int startX, int startY) {
        if (startX < 0 || startX > columns - 1 || startY < 0 || startY > rows - 1) {
            throw new IllegalArgumentException(String.format("StartX or StartY cannot be (%d, %d)", startX, startY));
        }
    }
}
