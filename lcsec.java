import java.io.*;

public class lcsec {
    public static void main(String[] args) {
        produceCSVFile(args[0]);
    }

    /**
     * Reads each file in the directory and subdirectories, counts the amount of times
     * fileName is mentioned
     *
     * @param fileName file name that the function will search for
     * @param path     path in which to search
     * @return if no files mention fileName 0, else amount of files mentioning fileName
     */
    public static int couplageSimpleEntreClasses(String fileName, String path) {
        int counter = 0;
        File parent = new File(path);
        File[] children = parent.listFiles();
        BufferedReader reader;
        for (File i : children) {
            if (i.isDirectory()) {
                counter += couplageSimpleEntreClasses(fileName, path + "/" + i.getName());
            } else {
                try {
                    String line;
                    boolean found = false;

                    //checks if main file is mentioned in other file
                    reader = new BufferedReader(new FileReader(i));
                    while ((line = reader.readLine()) != null) {
                        if (line.contains(fileName)) {
                            found = true;
                            break;
                        }
                    }
                    reader.close();
                    //checks if other file is mentioned in main file
                    if (!found) {
                        System.out.println(fileName);
                        reader = new BufferedReader(new FileReader(path + "/" + fileName));
                        while ((line = reader.readLine()) != null) {
                            if (line.contains(i.getName())) {
                                found = true;
                                break;
                            }
                        }
                        reader.close();
                    }
                    if (found)
                        counter++;

                    found = false;
                } catch (FileNotFoundException fnfe) {
                    System.out.println("File not found");
                    continue;
                } catch (IOException e) {
                    System.out.println("Empty line or file");
                }
            }
        }
        return counter;
    }

    public static void produceCSVFile(String path) {
        String content = produceCSVContent(path);
        jls fileProducer = new jls();
        fileProducer.produceCSVFile(content);
    }

    public static String produceCSVContent(String path) {
        String outputCSV = "";
        File parent = new File(path);
        jls lineProducer = new jls();

        if (!parent.isDirectory()) {
            return outputCSV;
        }


        File[] children = parent.listFiles();

        for (File i : children) {
            if (i.isDirectory()) {
                outputCSV = outputCSV + produceCSVContent(path + "/" + i.getName());
            } else {
                String childName = i.getName();
                String outputLine = lineProducer.produceCSVContent(path) + "," + couplageSimpleEntreClasses(childName, path);
                outputCSV += outputLine + "\n";
                //System.out.println(outputLine);
            }
        }

        return outputCSV;
    }

}
