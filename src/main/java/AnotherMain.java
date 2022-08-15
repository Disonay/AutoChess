import game.Game;

import java.io.IOException;

public class AnotherMain {
    public static void main(String[] args) throws IOException {
        System.out.println("It's another main class!");

        Game game = new Game();
        game.play();
    }
}
