package me.training.routecipher.cipher;


import org.junit.jupiter.api.Test;

import static me.training.routecipher.cipher.RouteCipher.decrypt;
import static me.training.routecipher.cipher.RouteCipher.encrypt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class RouteCipherTest {

    @Test
    public void testSimpleClockWise() {
        //A B C D E
        //F G H I J
        //K L M N O
        String encrypted = encrypt("ABCDEFGHIJKLMNO", 5, 3, RouteCycle.CLOCK_WISE);
        assertEquals("EJONMLKFABCDIHG", encrypted);
        String decrypted = decrypt(encrypted, 5, 3, RouteCycle.CLOCK_WISE);
        assertEquals("ABCDEFGHIJKLMNO", decrypted);
    }

    @Test
    public void testSimpleCounterClockWise() {
        //A B C D E
        //F G H I J
        //K L M N O
        String encrypted = encrypt("ABCDEFGHIJKLMNO", 5, 3, RouteCycle.COUNTER_CLOCK_WISE);
        assertEquals("EDCBAFKLMNOJIHG", encrypted);
        String decrypted = decrypt(encrypted, 5, 3, RouteCycle.COUNTER_CLOCK_WISE);
        assertEquals("ABCDEFGHIJKLMNO", decrypted);

    }


    @Test
    public void shouldStartAtStartingPointClockWise() {
        Direction[] clockWiseFor00 = {Direction.RIGHT, Direction.DOWN, Direction.LEFT, Direction.UP};
        assertEquals("ABCDEJOXXXXXKFGHINML",
                encrypt("ABCDEFGHIJKLMNO", 5, 4,
                        clockWiseFor00, 0, 0));
        assertEquals("ABCDEFGHIJKLMNOXXXXX",
                decrypt("ABCDEJOXXXXXKFGHINML", 5, 4,
                        clockWiseFor00, 0, 0));
    }

    @Test
    public void shouldStartAtStartingPointCounterClockWise() {
        Direction[] counterClockWiseFor00 = {Direction.DOWN, Direction.RIGHT, Direction.UP, Direction.LEFT};
        assertEquals("AFKXXXXXOJEDCBGLMNIH",
                encrypt("ABCDEFGHIJKLMNO", 5, 4,
                        counterClockWiseFor00, 0, 0));
        assertEquals("ABCDEFGHIJKLMNOXXXXX",
                decrypt("AFKXXXXXOJEDCBGLMNIH", 5, 4,
                        counterClockWiseFor00, 0, 0));
    }

    @Test
    public void test1() {
        assertEquals("CEXXECNOTAEOWEAREDISLFDEREV",
                encrypt("WE ARE DISCOVERED. FLEE AT ONCE", 9, 3, RouteCycle.CLOCK_WISE));
        assertEquals("WEAREDISCOVEREDFLEEATONCEXX",
                decrypt("CEXXECNOTAEOWEAREDISLFDEREV", 9, 3, RouteCycle.CLOCK_WISE));


    }

    @Test
    public void test2() {
        assertEquals("TSIYHWHFSNGOMGXIRORPSIEOBOROSS",
                encrypt("why is this professor so boring omg", 6, 5, RouteCycle.COUNTER_CLOCK_WISE));
        assertEquals("WHYISTHISPROFESSORSOBORINGOMGX",
                decrypt("TSIYHWHFSNGOMGXIRORPSIEOBOROSS", 6, 5, RouteCycle.COUNTER_CLOCK_WISE));

    }

    @Test
    public void test3() {
        assertEquals("CGNIVLOSHSYMUCHFUNXXMMLEGNELLAOPERISSOAIADRNROGR",
                encrypt("Solving challenges on r/dailyprogrammer is so much fun!!", 8, 6,
                        RouteCycle.COUNTER_CLOCK_WISE));
        assertEquals("SOLVINGCHALLENGESONRDAILYPROGRAMMERISSOMUCHFUNXX",
                decrypt("CGNIVLOSHSYMUCHFUNXXMMLEGNELLAOPERISSOAIADRNROGR", 8, 6,
                        RouteCycle.COUNTER_CLOCK_WISE));
    }

    @Test
    public void test4() {
        assertEquals("LHSENURBGAISEHCNNOATUPHLUFORCTVABEDOSWDALNTTEAEN",
                encrypt("For lunch let's have peanut-butter and bologna sandwiches", 4, 12,
                        RouteCycle.CLOCK_WISE));
        assertEquals("FORLUNCHLETSHAVEPEANUTBUTTERANDBOLOGNASANDWICHES",
                decrypt("LHSENURBGAISEHCNNOATUPHLUFORCTVABEDOSWDALNTTEAEN", 4, 12,
                        RouteCycle.CLOCK_WISE));

    }

    @Test
    public void test5() {
        assertEquals("IGAMXXXXXXXLETRTIVEEVENWASACAYFSIONESSEDNAMNW",
                encrypt("I've even witnessed a grown man satisfy a camel", 9, 5, RouteCycle.CLOCK_WISE));
        assertEquals("IVEEVENWITNESSEDAGROWNMANSATISFYACAMELXXXXXXX",
                decrypt("IGAMXXXXXXXLETRTIVEEVENWASACAYFSIONESSEDNAMNW", 9, 5, RouteCycle.CLOCK_WISE));

    }

    @Test
    public void test6() {
        assertEquals("YHWDSSPEAHTRSPEAMXJPOIENWJPYTEOIAARMEHENAR",
                encrypt("Why does it say paper jam when there is no paper jam?", 3, 14,
                        RouteCycle.COUNTER_CLOCK_WISE));
        assertEquals("WHYDOESITSAYPAPERJAMWHENTHEREISNOPAPERJAMX",
                decrypt("YHWDSSPEAHTRSPEAMXJPOIENWJPYTEOIAARMEHENAR", 3, 14,
                        RouteCycle.COUNTER_CLOCK_WISE));
    }


    @Test
    public void shouldThrowsIllegalArgumentExceptionWhenStartingPointIsOutOfGrid() {
        assertThrows(IllegalArgumentException.class, () -> encrypt("Test", 3, 3,
                RouteCycle.COUNTER_CLOCK_WISE, -1, 0));
        assertThrows(IllegalArgumentException.class, () -> encrypt("Test", 3, 3,
                RouteCycle.COUNTER_CLOCK_WISE, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> encrypt("Test", 3, 3,
                RouteCycle.COUNTER_CLOCK_WISE, 3, 2));
        assertThrows(IllegalArgumentException.class, () -> encrypt("Test", 3, 3,
                RouteCycle.COUNTER_CLOCK_WISE, 1, 3));

        assertThrows(IllegalArgumentException.class, () -> decrypt("Test", 3, 3,
                RouteCycle.COUNTER_CLOCK_WISE, -1, 0));
        assertThrows(IllegalArgumentException.class, () -> decrypt("Test", 3, 3,
                RouteCycle.COUNTER_CLOCK_WISE, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> decrypt("Test", 3, 3,
                RouteCycle.COUNTER_CLOCK_WISE, 3, 2));
        assertThrows(IllegalArgumentException.class, () -> decrypt("Test", 3, 3,
                RouteCycle.COUNTER_CLOCK_WISE, 1, 3));
    }

}