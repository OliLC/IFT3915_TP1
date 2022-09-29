import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class nvloc {
    public static void main(String[] args) {
        nonEmptyLines(args[0]);
    }

    public static int nonEmptyLines(String file) {
        if (file.length() > 5) {
            String extension = file.substring(file.length() - 5);
            if (!extension.equals(".java")) {
                System.out.println("Not a java file.");
                return 0;
            }
        } else {
            System.out.println("Input is too short to be a java file");
            return 0;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int lines = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                if (!"".equals(line.trim()))
                    lines++;
            }
            reader.close();
            System.out.println(lines + "lines");
            return lines;
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found");
            return 0;
        } catch (IOException e) {
            System.out.println("Empty line or file");
            return 0;
        }
    }
}
