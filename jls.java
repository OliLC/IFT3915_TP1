import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class jls {
    public static void main(String[] args){
        String cvsContent = produceCVS(args[0]);

        try {
            File CVSfile = new File("output.cvs");
            CVSfile.createNewFile();
        } catch (IOException e) {
            System.out.println("File creation ERROR");
        }
        
        try {
            FileWriter writer = new FileWriter("output.cvs");
            writer.write(cvsContent);
            writer.close();
        } catch (IOException e) {
            System.out.println("FILEWRITER error");
        }
    }
    

    public static String produceCVS(String path){
        String outputCVS = "";
        File parent = new File(path);

        if(!parent.isDirectory()){
            return outputCVS;
        }


        File[] children = parent.listFiles();
        
        for(File i : children){
            if (i.isDirectory()){
                outputCVS = outputCVS + produceCVS(path + "/" + i.getName());
            }
            else{
                String child = i.getName();
                String output = path + "/" + child + ", " + getPackageNameFromPath(path) + ", " + child.substring(0, child.indexOf('.'));
                outputCVS += output + "\n";
                System.out.println(output);
            }
        }

        return outputCVS;
    }
    
    public static String getPackageNameFromPath(String path){
        return path.substring(2).replaceAll("/", ".");
    }
}
