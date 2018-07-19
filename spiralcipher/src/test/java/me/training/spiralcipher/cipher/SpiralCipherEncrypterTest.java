package me.training.spiralcipher.cipher;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpiralCipherEncrypterTest {

    @Test
    public void testSimpleClockWise() {
        //A B C D E
        //F G H I J
        //K L M N O
        String encrypted = SpiralCipherEncrypter.of("ABCDEFGHIJKLMNO", 5, 3, RotationDirection.CLOCK_WISE).encrypt();
        assertEquals("EJONMLKFABCDIHG", encrypted);
    }

    @Test
    public void testSimpleCounterClockWise() {
        //A B C D E
        //F G H I J
        //K L M N O
        String encrypted = SpiralCipherEncrypter.of("ABCDEFGHIJKLMNO", 5, 3, RotationDirection.COUNTER_CLOCK_WISE).encrypt();
        assertEquals("EDCBAFKLMNOJIHG", encrypted);
    }

    @Test
    public void test1() {
        assertEquals("CEXXECNOTAEOWEAREDISLFDEREV", SpiralCipherEncrypter.of("WE ARE DISCOVERED. FLEE AT ONCE", 9, 3, RotationDirection.CLOCK_WISE).encrypt());
    }

    @Test
    public void test2() {
        assertEquals("TSIYHWHFSNGOMGXIRORPSIEOBOROSS", SpiralCipherEncrypter.of("why is this professor so boring omg", 6, 5, RotationDirection.COUNTER_CLOCK_WISE).encrypt());
    }

    @Test
    public void test3() {
        assertEquals("CGNIVLOSHSYMUCHFUNXXMMLEGNELLAOPERISSOAIADRNROGR", SpiralCipherEncrypter.of("Solving challenges on r/dailyprogrammer is so much fun!!", 8, 6, RotationDirection.COUNTER_CLOCK_WISE).encrypt());
    }

    @Test
    public void test4() {
        assertEquals("LHSENURBGAISEHCNNOATUPHLUFORCTVABEDOSWDALNTTEAEN", SpiralCipherEncrypter.of("For lunch let's have peanut-butter and bologna sandwiches", 4, 12, RotationDirection.CLOCK_WISE).encrypt());
    }

    @Test
    public void test5() {
        assertEquals("IGAMXXXXXXXLETRTIVEEVENWASACAYFSIONESSEDNAMNW", SpiralCipherEncrypter.of("I've even witnessed a grown man satisfy a camel", 9, 5, RotationDirection.CLOCK_WISE).encrypt());
    }

    @Test
    public void test6() {
        assertEquals("YHWDSSPEAHTRSPEAMXJPOIENWJPYTEOIAARMEHENAR", SpiralCipherEncrypter.of("Why does it say paper jam when there is no paper jam?", 3, 14, RotationDirection.COUNTER_CLOCK_WISE).encrypt());
    }

//    "WE ARE DISCOVERED. FLEE AT ONCE" (9, 3) clockwise
//"why is this professor so boring omg" (6, 5) counter-clockwise
//"Solving challenges on r/dailyprogrammer is so much fun!!" (8, 6) counter-clockwise
//"For lunch let's have peanut-butter and bologna sandwiches" (4, 12) clockwise
//"I've even witnessed a grown man satisfy a camel" (9,5) clockwise
//"Why does it say paper jam when there is no paper jam?" (3, 14) counter-clockwise

}