
public class Main {
    public static void main(String[] args) {

        String filePath = args[0];
        Output.writeTerminalOutputToFile(args[1], true);

        String[] commendLines = Input.readFile(filePath, true, true);
        if (commendLines != null){
            CreateSystem.readCommend(commendLines);
        }
        }

}