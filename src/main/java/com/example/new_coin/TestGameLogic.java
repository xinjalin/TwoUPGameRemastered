package com.example.new_coin;

import org.junit.Test;

import static org.junit.Assume.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class TestGameLogic {
    @Test
    public void testCoinsNotNullWhenCalled() {
        Coin coin = new Coin();
        assumeTrue(coin != null);
    }

    @Test
    public void testCoinFaceValueIsCorrect() {
        Coin coin = new Coin();
        assumingThat(coin.isHeads(), () -> assertEquals(0, coin.getFace(), "coin is heads"));
        assumingThat(!coin.isHeads(), () -> assertEquals(1, coin.getFace(), "coin is tails"));
    }

    @Test
    public void testGameLogicWithCoins() {
        String testPlayer = "TEST_PLAYER";
        String HH = "Heads Heads";
        String TT = "Tails Tails";
        Twoup game = new Twoup();

        assertEquals("HH", game.playGame(true, true, HH, testPlayer));
        assertEquals("HH Lose", game.playGame(false, false, HH, testPlayer));
        assertEquals("HH Flip Again", game.playGame(true, false, HH, testPlayer));
        assertEquals("HH Flip Again", game.playGame(false, true, HH, testPlayer));

        assertEquals("TT", game.playGame(false, false, TT, testPlayer));
        assertEquals("TT Lose", game.playGame(true, true, TT, testPlayer));
        assertEquals("TT Flip Again", game.playGame(true, false, TT, testPlayer));
        assertEquals("TT Flip Again", game.playGame(false, true, TT, testPlayer));
    }
}
