import com.example.lottoapp.NumberChecker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class NumberCheckerTest {

    @Test
    public void winnerGame() {
        int[] winningNumbers = new int[]{1, 2, 5};
        NumberChecker numberChecker = new NumberChecker(winningNumbers);
        assertTrue(numberChecker.allEquals(winningNumbers));
    }

    @Test
    public void loserGamer() {
        int[] winningNumbers = new int[]{1, 2, 5};
        NumberChecker numberChecker = new NumberChecker(winningNumbers);
        assertFalse(numberChecker.allEquals(new int[]{3, 1, 5}));
    }

    @Test
    public void winnerNumber() {
        int[] winningNumbers = new int[]{1, 2, 5};
        NumberChecker numberChecker = new NumberChecker(winningNumbers);
        assert(numberChecker.exists(5));
    }

    @Test
    public void notWinnerNumber() {
        int[] winningNumbers = new int[]{1, 2, 5};
        NumberChecker numberChecker = new NumberChecker(winningNumbers);
        assertFalse(numberChecker.exists(3));
    }

    @Test
    public void noMatches() {
        int[] winningNumbers = new int[]{1, 2, 5};
        NumberChecker numberChecker = new NumberChecker(winningNumbers);
        assert(numberChecker.numOfMatches(new int[]{0, 10, 15}) == 0);
    }

    @Test
    public void oneMatch() {
        int[] winningNumbers = new int[]{1, 2, 5};
        NumberChecker numberChecker = new NumberChecker(winningNumbers);
        assert(numberChecker.numOfMatches(new int[]{5, 10, 15}) == 1);
    }
}
