//package com.example.new_coin;
//
//import org.junit.jupiter.api.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assumptions.*;
//
//
//@DisplayName("Testing Game Logic Class")
//public class TestGame {
//
//    @Test
//    @DisplayName("Testing Coin Initialize with a value")
//    public void testCoinsNotNullWhenCalled() {
//        Coin coin = new Coin();
//        assumeTrue(coin != null);
//    }
//
//    @RepeatedTest(20)
//    @DisplayName("Testing Coin Face Value")
//    public void testCoinFaceValueIsCorrect() {
//        Coin coin = new Coin();
//        assumingThat(coin.isHeads(), () -> assertEquals(0, coin.getFace(), "coin is heads"));
//        assumingThat(!coin.isHeads(), () -> assertEquals(1, coin.getFace(), "coin is tails"));
//    }
//
//    @RepeatedTest(20)
//    @DisplayName("Testing Game With Coins All Outcomes")
//    public void testGameWithCoins() {
//        String testPlayer = "TEST_PLAYER";
//        String HH = "HEADS_HEADS";
//        String TT = "TAILS_TAILS";
//        Game game = new Game();
//
//        assertEquals("Victory", game.gameOfTwoUp(true, true, HH, testPlayer));
//        assertEquals("You Lose", game.gameOfTwoUp(false, false, HH, testPlayer));
//        assertEquals("Flip Again", game.gameOfTwoUp(true, false, HH, testPlayer));
//        assertEquals("Flip Again", game.gameOfTwoUp(false, true, HH, testPlayer));
//
//        assertEquals("Victory", game.gameOfTwoUp(false, false, TT, testPlayer));
//        assertEquals("You Lose", game.gameOfTwoUp(true, true, TT, testPlayer));
//        assertEquals("Flip Again", game.gameOfTwoUp(true, false, TT, testPlayer));
//        assertEquals("Flip Again", game.gameOfTwoUp(false, true, TT, testPlayer));
//    }
//
//}
