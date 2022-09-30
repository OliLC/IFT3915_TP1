import java.io.File;
import java.lang.Math;
import java.util.Arrays;

public class egon{

    //arg 0 : path du package, arg 1 seuil
    public static void main(String[] args){

        String classesSuspectes = "";
        String packageName = args[0];
        int seuil = Integer.parseInt(args[1]);

        String[] CVS = lcsec.produceCSVContent(packageName).split("[,\n]");
        int nbOfClasses = CVS.length / 4;
        String[] classPaths = new String[nbOfClasses];

        //System.out.println("nb of Classes : " + nbOfClasses);
        int[] allCSECByIndex = new int[nbOfClasses];
        int[] allCSECSorted = new int[nbOfClasses];

        //getting all the paths with lcsec, and CSEC values
        for(int i = 0; i < classPaths.length; i++){
            classPaths[i] = CVS[i*4];
            allCSECByIndex[i] = Integer.parseInt(CVS[i*4+3]);
            allCSECSorted[i] = Integer.parseInt(CVS[i*4+3]);
        }

        Arrays.sort(allCSECSorted);
        
        int[] allNVLOCByIndex = new int[nbOfClasses];
        int[] allNVLOCSorted = new int[nbOfClasses];
        
        //We run NVLOC on each file to get nb of lines
        for (int i = 0; i < nbOfClasses; i++){
            
            File file = new File(classPaths[i]);

            int NVLOC = nvloc.nonEmptyLines(classPaths[i]);

            allNVLOCByIndex[i] = NVLOC;
            allNVLOCSorted[i] = NVLOC;
        }

        Arrays.sort(allNVLOCSorted);

        //get the cutoff seuil% from both
        int cutoffIndex = (int) Math.ceil( (double) nbOfClasses * (seuil/100.0));
        if(cutoffIndex == 0){cutoffIndex++;}

        //System.out.println("Cutoff : " + cutoffIndex);

        int cutoffCSEC = allCSECSorted[nbOfClasses - cutoffIndex];
        int cutoffNVLOC = allNVLOCSorted[nbOfClasses - cutoffIndex];

        //System.out.println("cutoffCSEC : " + cutoffCSEC);
        //System.out.println("cutoffNVLOC : " + cutoffNVLOC);

        for(int i = 0; i < nbOfClasses; i++){

            if(allCSECByIndex[i] >= cutoffCSEC && allNVLOCByIndex[i] >= cutoffNVLOC){
                classesSuspectes += CVS[i*4] + ", " + CVS[i*4 + 1] + ", " + CVS[i*4 + 2] + ", " + CVS[i*4 + 3] + ", " + allNVLOCByIndex[i] + "\n";
            }
        }

        System.out.println(classesSuspectes);
    }
}


//for test:   java egon ./gr/spinellis 10