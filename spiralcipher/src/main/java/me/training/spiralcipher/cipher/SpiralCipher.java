package me.training.spiralcipher.cipher;


import me.training.spiralcipher.impl.CyclicDirectionSupplier;
import me.training.spiralcipher.impl.DecryptGridWalkerStepConsumer;
import me.training.spiralcipher.impl.EncryptGridWalkerStepConsumer;
import me.training.spiralcipher.impl.GridWalker;
import me.training.spiralcipher.impl.Point;

public class SpiralCipher {


    public static String encrypt(String plainText, int columns, int rows, RotationDirection rotationDirection) {
        String toEncrypt = plainText.replaceAll("[ \\-'.?/!]", "").toUpperCase();
        GridWalker gridWalker = GridWalker.create(columns, rows);
        EncryptGridWalkerStepConsumer stepConsumer = EncryptGridWalkerStepConsumer.of(toEncrypt, rows, columns);
        gridWalker.walk(new CyclicDirectionSupplier(rotationDirection.getDirectionOrder()),
                stepConsumer, new Point(columns - 1, 0));
        return stepConsumer.result();
    }

    public static String decrypt(String encryptedText, int columns, int rows, RotationDirection rotationDirection) {
        GridWalker gridWalker = GridWalker.create(columns, rows);
        DecryptGridWalkerStepConsumer stepConsumer = new DecryptGridWalkerStepConsumer(encryptedText, columns, rows);
        gridWalker.walk(new CyclicDirectionSupplier(rotationDirection.getDirectionOrder()), stepConsumer,
                new Point(columns - 1, 0));
        return stepConsumer.result();
    }
}
