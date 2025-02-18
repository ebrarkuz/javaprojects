import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Output {
    public static void playGame(String inputPath, String outputPath) {
        String[] fileContent = Input.readFile(inputPath, true, true); // read the all file
        int playerCount = Integer.parseInt(fileContent[0]); // take player count from the first line
        String[] playerNames = fileContent[1].split(","); // take player names from the second line

        if (playerNames.length != playerCount) {
            throw new IllegalArgumentException("Oyuncu sayısı ile isim sayısı uyuşmuyor.");
        }

        Gamer[] gamers = new Gamer[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {
            gamers[i] = new Gamer(playerNames[i].trim(), 0); // İsimleri trimleyin ve Gamer nesneleri oluşturun
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            Game game = new Game(gamers);

            for (int i = 2; i < fileContent.length && !game.isGameOver(); i++) {
                String[] shot = fileContent[i].split("-");
                int number1 = Integer.parseInt(shot[0]);
                int number2 = Integer.parseInt(shot[1]);

                int currentPlayerIndex = game.getCurrentPlayerIndex();
                Gamer currentPlayer = gamers[currentPlayerIndex];


                    game.gamePlay(number1, number2, currentPlayer, writer);
                    game.nextPlayer();

            }

            Gamer winner = game.getWinner();
            if (winner != null) {
                writer.write(winner.getName() + " is the winner of the game with the score of " + winner.getScore() + ". Congratulations " + winner.getName() + "!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}