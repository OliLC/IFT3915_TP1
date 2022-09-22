import java.io.File;
import java.io.FileNotFoundException;

public class jls {
    public static void main(String[] args){
        produceCVS(args[0]);
    }
    

    public static void produceCVS(String path){
        
        File parent = new File(path);

        if(!parent.isDirectory()){
            return;
        }


        File[] children = parent.listFiles();
        
        for(File i : children){
            if (i.isDirectory()){
                produceCVS(path + "/" + i.getName());
            }
            else{
                String child = i.getName();
                System.out.println(path + "/" + child + ", " + getPackageNameFromPath(path) + ", " + child.substring(0, child.indexOf('.')));
            }
        }
    }
    
    public static String getPackageNameFromPath(String path){
        return path.substring(2).replaceAll("/", ".");
    }
}