package me.training.spiralcipher.cipher;

public class SpiralCipherDecrypter {

    public static SpiralCipherDecrypter of(String plainText, int columns, int rows, RotationDirection rotationDirection) {
        return new SpiralCipherDecrypter();
    }
}
