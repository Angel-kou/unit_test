package tw.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;

import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {

    private AnswerGenerator answerGenerator;
    private Game game;

    @Before
    public void setUp() throws OutOfRangeAnswerException {
        Answer actualAnswer = new Answer();
        actualAnswer.setNumList(Arrays.asList("1","2","3","4"));

        answerGenerator = Mockito.mock(AnswerGenerator.class);
        when(answerGenerator.generate()).thenReturn(actualAnswer);
        game = new Game(answerGenerator);
    }

    @Test
    public void should_return_4A0B_when_inputAnswer_is_correct() {
        Answer inputAnswer = new Answer();
        inputAnswer.setNumList(Arrays.asList("1","2","3","4"));
        assertEquals("4A0B",game.guess(inputAnswer).getResult());
    }

    @Test
    public void should_return_1A0B_when_inputAnswer_has_one_correct() {
        Answer inputAnswer = new Answer();
        inputAnswer.setNumList(Arrays.asList("1","5","6","7"));
        assertEquals("1A0B",game.guess(inputAnswer).getResult());
    }

    @Test
    public void should_return_0A2B_when_inputAnswer_has_two_position_wrong() {
        Answer inputAnswer = new Answer();
        inputAnswer.setNumList(Arrays.asList("2","4","6","8"));
        assertEquals("0A2B",game.guess(inputAnswer).getResult());
    }

    @Test
    public void should_return_1A2B_when_inputAnswer_has_one_correct_and_two_position_wrong() {
        Answer inputAnswer = new Answer();
        inputAnswer.setNumList(Arrays.asList("0","3","2","4"));
        assertEquals("1A2B",game.guess(inputAnswer).getResult());
    }

    @Test
    public void should_return_0A0B_when_inputAnswer_has_zero_correct() {
        Answer inputAnswer = new Answer();
        inputAnswer.setNumList(Arrays.asList("5","6","7","8"));
        assertEquals("0A0B",game.guess(inputAnswer).getResult());
    }

    @Test
    public void should_return_0A4B_when_inputAnswer_has_four_position_wrong() {
        Answer inputAnswer = new Answer();
        inputAnswer.setNumList(Arrays.asList("4","3","2","1"));
        assertEquals("0A4B",game.guess(inputAnswer).getResult());
    }

    @Test
    public void should_not_equals_zero_when_guess_no_more_than_one() {
        Answer inputAnswer = new Answer();
        inputAnswer.setNumList(Arrays.asList("4","3","2","1"));
        game.guess(inputAnswer);
        game.guess(inputAnswer);
        assertEquals(2,game.guessHistory().size());
    }

    @Test
    public void should_return_fail_when_guess_times_exceed_or_equal_max_time() {
        Answer inputAnswer = new Answer();
        inputAnswer.setNumList(Arrays.asList("4","3","2","1"));
        game.guess(inputAnswer);
        game.guess(inputAnswer);
        game.guess(inputAnswer);
        game.guess(inputAnswer);
        game.guess(inputAnswer);
        game.guess(inputAnswer);
        assertEquals(GameStatus.FAIL,game.checkStatus());
    }

    @Test
    public void should_return_success_when_guess_results_contain_4A0B() {
        Answer inputAnswer1 = new Answer();
        inputAnswer1.setNumList(Arrays.asList("4","3","2","1"));
        Answer inputAnswer2 = new Answer();
        inputAnswer2.setNumList(Arrays.asList("1","2","3","4"));
        game.guess(inputAnswer1);
        game.guess(inputAnswer2);
        assertEquals(GameStatus.SUCCESS,game.checkStatus());
    }

    @Test
    public void should_return_continue_when_guess_times_less_than_max_time_and_guess_results_not_contain_4A0B() {
        Answer inputAnswer1 = new Answer();
        inputAnswer1.setNumList(Arrays.asList("4","3","2","1"));
        Answer inputAnswer2 = new Answer();
        inputAnswer2.setNumList(Arrays.asList("1","5","6","7"));
        game.guess(inputAnswer1);
        game.guess(inputAnswer2);
        assertEquals(GameStatus.CONTINUE,game.checkStatus());
    }

    @Test
    public void should_return_true_when_check_status_is_continue() {
        Answer inputAnswer1 = new Answer();
        inputAnswer1.setNumList(Arrays.asList("4","3","2","1"));
        Answer inputAnswer2 = new Answer();
        inputAnswer2.setNumList(Arrays.asList("1","5","6","7"));
        game.guess(inputAnswer1);
        game.guess(inputAnswer2);
        assertTrue(game.checkCoutinue());
    }

    @Test
    public void should_return_false_when_check_status_is_fail() {
        Answer inputAnswer = new Answer();
        inputAnswer.setNumList(Arrays.asList("4","3","2","1"));
        game.guess(inputAnswer);
        game.guess(inputAnswer);
        game.guess(inputAnswer);
        game.guess(inputAnswer);
        game.guess(inputAnswer);
        game.guess(inputAnswer);
        assertFalse(game.checkCoutinue());
    }

    @Test
    public void should_return_false_when_check_status_is_success() {
        Answer inputAnswer1 = new Answer();
        inputAnswer1.setNumList(Arrays.asList("4","3","2","1"));
        Answer inputAnswer2 = new Answer();
        inputAnswer2.setNumList(Arrays.asList("1","2","3","4"));
        game.guess(inputAnswer1);
        game.guess(inputAnswer2);
        assertFalse(game.checkCoutinue());
    }


}
