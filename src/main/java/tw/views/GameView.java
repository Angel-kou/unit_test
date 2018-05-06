package tw.views;

import tw.core.model.GuessResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by jxzhong on 2017/5/19.
 */
public class GameView {

    private BufferedReader bufferedReader;

    {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void showGuessResult(GuessResult guessResult) {
        System.out.print("Guess Result: " + guessResult.getResult()+"\n");
    }

    public void showGameStatus(String status) {
        System.out.print("Game Status: " + status+"\n");
    }

    public void showGuessHistory(List<GuessResult> guessResults) {
        System.out.print("Guess History:\n");
        guessResults.stream().forEach(guessResult -> {
            System.out.print(String.format("[Guess Numbers: %1$s, Guess Result: %2$s]",
                    guessResult.getInputAnswer().toString(),
                    guessResult.getResult())+"\n");
        });
    }

    public void showBegin() throws IOException {
        System.out.print("------Guess Number Game, You have 6 chances to guess!  ------\n");

    }
}
