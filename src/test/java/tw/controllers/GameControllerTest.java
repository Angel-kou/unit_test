package tw.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tw.commands.GuessInputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.GameStatus;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.views.GameView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {

    private GameController gameController;
    private AnswerGenerator answerGenerator;

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private GameView gameView = new GameView();

    @Mock
    private Game game;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        gameController = new GameController(game,gameView);
        System.setOut(new PrintStream(outContent));
    }

    private String systemOut() {
        return outContent.toString();
    }

    @Test
    public void should_print_game_start_tips_when_begin_game() throws IOException {
        gameController.beginGame();
        assertThat(systemOut()).isEqualTo("------Guess Number Game, You have 6 chances to guess!  ------\n");
    }



    @Test
    public void should_print_game_status_when_check_status_is_fail() throws IOException {
        when(game.checkStatus()).thenReturn(GameStatus.FAIL);
        gameController.play(new GuessInputCommand());
        assertThat(systemOut()).isEqualTo("Game Status: fail\n");
    }

    @Test
    public void should_print_game_status_when_check_status_is_success() throws IOException {
        when(game.checkStatus()).thenReturn(GameStatus.SUCCESS);
        gameController.play(new GuessInputCommand());
        assertThat(systemOut()).isEqualTo("Game Status: success\n");
    }

    @Test
    public void should_print_game_result_when_check_status_is_continue_and_results_first_time_success() throws IOException, OutOfRangeAnswerException {
        GameController gameController = getGameController();
        GuessInputCommand guessInputCommand = getGuessInput(Arrays.asList("1","2","3","4"));
        gameController.play(guessInputCommand);

        assertThat(systemOut()).isEqualTo("Guess Result: 4A0B\n" +
                "Guess History:\n" +
                "[Guess Numbers: 1 2 3 4, Guess Result: 4A0B]\n" +
                "Game Status: success\n");
    }


    @Test
    public void should_print_game_result_when_check_status_is_continue_no_success_times_equals_six() throws IOException, OutOfRangeAnswerException {
        GameController gameController = getGameController();
        GuessInputCommand guessInputCommand = getGuessInput(Arrays.asList("1","5","6","7"));
        gameController.play(guessInputCommand);
        assertThat(systemOut()).isEqualTo("Guess Result: 1A0B\n" +
                "Guess History:\n" +
                "[Guess Numbers: 1 5 6 7, Guess Result: 1A0B]\n" +
                "Guess Result: 1A0B\n" +
                "Guess History:\n" +
                "[Guess Numbers: 1 5 6 7, Guess Result: 1A0B]\n" +
                "[Guess Numbers: 1 5 6 7, Guess Result: 1A0B]\n" +
                "Guess Result: 1A0B\n" +
                "Guess History:\n" +
                "[Guess Numbers: 1 5 6 7, Guess Result: 1A0B]\n" +
                "[Guess Numbers: 1 5 6 7, Guess Result: 1A0B]\n" +
                "[Guess Numbers: 1 5 6 7, Guess Result: 1A0B]\n" +
                "Guess Result: 1A0B\n" +
                "Guess History:\n" +
                "[Guess Numbers: 1 5 6 7, Guess Result: 1A0B]\n" +
                "[Guess Numbers: 1 5 6 7, Guess Result: 1A0B]\n" +
                "[Guess Numbers: 1 5 6 7, Guess Result: 1A0B]\n" +
                "[Guess Numbers: 1 5 6 7, Guess Result: 1A0B]\n" +
                "Guess Result: 1A0B\n" +
                "Guess History:\n" +
                "[Guess Numbers: 1 5 6 7, Guess Result: 1A0B]\n" +
                "[Guess Numbers: 1 5 6 7, Guess Result: 1A0B]\n" +
                "[Guess Numbers: 1 5 6 7, Guess Result: 1A0B]\n" +
                "[Guess Numbers: 1 5 6 7, Guess Result: 1A0B]\n" +
                "[Guess Numbers: 1 5 6 7, Guess Result: 1A0B]\n" +
                "Guess Result: 1A0B\n" +
                "Guess History:\n" +
                "[Guess Numbers: 1 5 6 7, Guess Result: 1A0B]\n" +
                "[Guess Numbers: 1 5 6 7, Guess Result: 1A0B]\n" +
                "[Guess Numbers: 1 5 6 7, Guess Result: 1A0B]\n" +
                "[Guess Numbers: 1 5 6 7, Guess Result: 1A0B]\n" +
                "[Guess Numbers: 1 5 6 7, Guess Result: 1A0B]\n" +
                "[Guess Numbers: 1 5 6 7, Guess Result: 1A0B]\n" +
                "Game Status: fail\n");

    }


    private GameController getGameController() throws OutOfRangeAnswerException {
        Answer actualAnswer = new Answer();
        actualAnswer.setNumList(Arrays.asList("1","2","3","4"));
        answerGenerator = Mockito.mock(AnswerGenerator.class);
        when(answerGenerator.generate()).thenReturn(actualAnswer);
        Game game = new Game(answerGenerator);
        return new GameController(game,gameView);
    }

    private GuessInputCommand getGuessInput(List<String> list) throws IOException {
        GuessInputCommand guessInputCommand = Mockito.mock(GuessInputCommand.class);
        Answer inputAnswer = new Answer();
        inputAnswer.setNumList(list);
        when(guessInputCommand.input()).thenReturn(inputAnswer);
        return guessInputCommand;
    }




}