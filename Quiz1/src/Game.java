import java.io.BufferedWriter;
import java.io.IOException;

public class Game {
    private Gamer[] gamers;
    private int currentPlayerIndex;
    private boolean[] eliminatedPlayers;

    public Game(Gamer[] gamers) {
        this.gamers = gamers;
        this.currentPlayerIndex = 0;
        this.eliminatedPlayers = new boolean[gamers.length];
    }
    /*
     * @param number1 the first number which the player threw
     * @param number2 the second number which the player threw
     * @param gamer the player
     * @param bufferedWriter  is a object to write the output on a file
     */
    public void gamePlay(int number1, int number2, Gamer gamer, BufferedWriter writer) {
        try {
            if (number1 != 1 && number2 != 1 && number1 != 0 && number2 != 0) {
                gamer.setScore(gamer.getScore() + number1 + number2);
                writer.write(gamer.getName() + " threw " + number1 + "-" + number2
                        + " and " + gamer.getName() + "\u0027s score is " + gamer.getScore() + ".\n");
            } else if (number1 == 0 && number2 == 0) {
                writer.write(gamer.getName() + " skipped the turn and " + gamer.getName() + "\u0027s score is " + gamer.getScore() + ".\n");
            } else if (number1 == 1 && number2 != 1 || number1 != 1 && number2 == 1) {
                writer.write(gamer.getName() + " threw " + number1 + "-" + number2
                        + " and " + gamer.getName() + "\u0027s score is " + gamer.getScore() + ".\n");
            } else if (number1 == 1 && number2 == 1) {
                writer.write(gamer.getName() + " threw " + number1 + "-" + number2
                        + ". Game over " + gamer.getName() + "!\n");
                eliminatePlayer(gamer,writer); // Oyuncuyu elenmiş olarak işaretle
            }
            writer.flush(); // Buffer'ı temizle
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    if the game is over for a player sign him as a eliminated player.
     */
    private void eliminatePlayer(Gamer gamer, BufferedWriter writer) {
        for (int i = 0; i < gamers.length; i++) {
            if (gamers[i].equals(gamer)) {
                eliminatedPlayers[i] = true;
                try {
                    writer.flush(); // Buffer'ı temizle
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }


    /*
     *if the game is over for a player threw him out of the loop
     * @return if the number of active player is less than 1 return true
     */
    public boolean isGameOver() {
        int activePlayers = 0;
        for (boolean eliminated : eliminatedPlayers) {
            if (!eliminated) {
                activePlayers++;
            }
        }
        return activePlayers <= 1;
    }

    public void nextPlayer() {
        do {
            currentPlayerIndex = (currentPlayerIndex + 1) % gamers.length;
        } while (eliminatedPlayers[currentPlayerIndex]); // skip the eliminated players
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
    /*
    if the player is not eliminated he/she is the winner
     */
    public Gamer getWinner() {
        for (int i = 0; i < gamers.length; i++) {
            if (!eliminatedPlayers[i]) {
                return gamers[i];
            }
        }
        return null;
    }
}

