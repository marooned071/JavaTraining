package me.training.spiralcipher.cipher;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpiralCipherTest {

    @Test
    public void testSimpleClockWise() {
        //A B C D E
        //F G H I J
        //K L M N O
        String encrypted = SpiralCipher.encrypt("ABCDEFGHIJKLMNO", 5, 3, RotationDirection.CLOCK_WISE);
        assertEquals("EJONMLKFABCDIHG", encrypted);
    }

    @Test
    public void testSimpleCounterClockWise() {
        //A B C D E
        //F G H I J
        //K L M N O
        String encrypted = SpiralCipher.encrypt("ABCDEFGHIJKLMNO", 5, 3, RotationDirection.COUNTER_CLOCK_WISE);
        assertEquals("EDCBAFKLMNOJIHG", encrypted);
    }

    @Test
    public void test1() {
        assertEquals("CEXXECNOTAEOWEAREDISLFDEREV",
                SpiralCipher.encrypt("WE ARE DISCOVERED. FLEE AT ONCE", 9, 3, RotationDirection.CLOCK_WISE));
    }

    @Test
    public void test2() {
        assertEquals("TSIYHWHFSNGOMGXIRORPSIEOBOROSS", SpiralCipher
                .encrypt("why is this professor so boring omg", 6, 5, RotationDirection.COUNTER_CLOCK_WISE));
    }

    @Test
    public void test3() {
        assertEquals("CGNIVLOSHSYMUCHFUNXXMMLEGNELLAOPERISSOAIADRNROGR", SpiralCipher
                .encrypt("Solving challenges on r/dailyprogrammer is so much fun!!", 8, 6,
                        RotationDirection.COUNTER_CLOCK_WISE));
    }

    @Test
    public void test4() {
        assertEquals("LHSENURBGAISEHCNNOATUPHLUFORCTVABEDOSWDALNTTEAEN", SpiralCipher
                .encrypt("For lunch let's have peanut-butter and bologna sandwiches", 4, 12,
                        RotationDirection.CLOCK_WISE));
    }

    @Test
    public void test5() {
        assertEquals("IGAMXXXXXXXLETRTIVEEVENWASACAYFSIONESSEDNAMNW", SpiralCipher
                .encrypt("I've even witnessed a grown man satisfy a camel", 9, 5, RotationDirection.CLOCK_WISE));
    }

    @Test
    public void test6() {
        assertEquals("YHWDSSPEAHTRSPEAMXJPOIENWJPYTEOIAARMEHENAR", SpiralCipher
                .encrypt("Why does it say paper jam when there is no paper jam?", 3, 14,
                        RotationDirection.COUNTER_CLOCK_WISE));
    }

//    "WE ARE DISCOVERED. FLEE AT ONCE" (9, 3) clockwise
//"why is this professor so boring omg" (6, 5) counter-clockwise
//"Solving challenges on r/dailyprogrammer is so much fun!!" (8, 6) counter-clockwise
//"For lunch let's have peanut-butter and bologna sandwiches" (4, 12) clockwise
//"I've even witnessed a grown man satisfy a camel" (9,5) clockwise
//"Why does it say paper jam when there is no paper jam?" (3, 14) counter-clockwise

}