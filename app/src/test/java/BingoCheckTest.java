import com.example.lottoapp.BingoCheck;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class BingoCheckTest {

    @Test
    public void winnerGame() {
        int[] winningNumbers = new int[]{1, 2, 5};
        BingoCheck bingoCheck = new BingoCheck(winningNumbers);
        assertTrue(bingoCheck.checkResult(winningNumbers));
    }

    @Test
    public void loserGamer() {
        int[] winningNumbers = new int[]{1, 2, 5};
        BingoCheck bingoCheck = new BingoCheck(winningNumbers);
        assertFalse(bingoCheck.checkResult(new int[]{2, 1, 5}));
    }
}
