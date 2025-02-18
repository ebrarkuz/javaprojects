
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Input {
    public static String[] readPlayerNames(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            if (lines.size() < 2) {
                return new String[0];
            }
            /*
             *@return the players names split by commas

             */
            return lines.get(1).split(",");
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    } public static String[] readFile(String path, boolean discardEmptyLines, boolean trim) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            if (discardEmptyLines) {
                lines.removeIf(line -> line.trim().equals(""));
            }
            if (trim) {
                lines.replaceAll(String::trim);
            }
            /*
             *@return the elements of the lists as an array
             */
            return lines.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
