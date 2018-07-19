package me.training.clockcipherusage;


import me.training.spiralcipher.cipher.RotationDirection;
import me.training.spiralcipher.cipher.SpiralCipherEncrypter;

public class ClockCipherMain {
    public static void main(String[] args) {
        String result = SpiralCipherEncrypter.of("test java modules", 4, 4, RotationDirection.CLOCK_WISE).encrypt();
        System.out.println(result);
    }
}
