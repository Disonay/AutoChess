package game;

import figures.Figure;
import player.Black;
import player.White;
import game.field.GameField;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Game {

    GameField gameField = new GameField();
    White white = new White();
    Black black = new Black();

    public Game() {
        resetGame();
    }


    public void resetGame() {
        gameField.resetField();

        white.resetFigures();
        black.resetFigures();

        for (Figure figure: white.getAvailableFigures()) {
            gameField.placeFigure(figure);
        }

        for (Figure figure: black.getAvailableFigures()) {
            gameField.placeFigure(figure);
        }

    }
    public void play() throws IOException {
        PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.name());
        try (FileWriter fileWriter = new FileWriter("output.txt")) {
            fileWriter.write(gameField.toString());
            fileWriter.write("\n");
            while (true) {
                if (white.haveSteps(gameField)) {
                    white.step(gameField);
                    fileWriter.write(gameField.toString());
                    fileWriter.write("\n");
                }
                else {
                    break;
                }

                if (black.haveSteps(gameField)) {
                    black.step(gameField);
                    fileWriter.write(gameField.toString());
                    fileWriter.write("\n");
                }
                else {
                    break;
                }
            }
        }


    }
}
