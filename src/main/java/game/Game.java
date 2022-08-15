package game;

import figures.Figure;
import game.field.GameField;
import player.Black;
import player.Player;
import player.White;

import java.io.FileWriter;
import java.io.IOException;

public class Game {

    GameField gameField = new GameField();

    White white = new White();

    Black black = new Black();

    public Game() {
        resetGame();
    }

    public void resetGame() {
        gameField.resetField();

        for (Player player : new Player[]{white, black}) {
            player.resetFigures();
            for (Figure figure : player.getAvailableFigures()) {
                gameField.placeFigure(figure);
            }
        }

    }

    public void play() throws IOException {
        try (FileWriter fileWriter = new FileWriter("output.txt")) {
            fileWriter.write(gameField.toString());
            fileWriter.write("\n");
            boolean hasSteps = true;
            while (hasSteps) {
                for (Player player : new Player[]{white, black}) {
                    if (player.haveSteps(gameField)) {
                        player.step(gameField);
                        fileWriter.write(gameField.toString());
                        fileWriter.write("\n");
                    } else {
                        hasSteps = false;
                        break;
                    }
                }
            }
        }
    }
}
