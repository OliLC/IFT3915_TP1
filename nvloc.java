import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class nvloc {
    public static void main(String[] args) {
        nonEmptyLines(args[0]);
    }

    public static void nonEmptyLines(String file) {
        if (file.length() > 5) {
            String extension = file.substring(file.length() - 5);
            if (!extension.equals(".java")) {
                System.out.println("Not a java file.");
                return;
            }
        } else {
            System.out.println("Input is too short to be a java file");
            return;
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
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found");
            return;
        } catch (IOException e) {
            System.out.println("Empty line or file");
            return;
        }
    }
}
