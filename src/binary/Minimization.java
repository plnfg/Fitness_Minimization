package binary;

/**
 *
 * @author Pelin
 */
public class Minimization {

    final int POP_SIZE = 6;
    static int SUM;

    public static void main(String[] args) {
        SUM=0;

       int i;
       for(i=0;i<100;i++){
           runSolution();
       }
        
        System.out.println(SUM/100); 

    }

    private static void runSolution() {

        Population pop = new Population();

        Individual popBest;

        int i = 1,j;
        Individual[] parentPop;
        Individual[] childPop;
        parentPop = pop.init_population();
        popBest = pop.getFittest(parentPop);

   
        while (true) {
            if(popBest.getFitness()== -64) break;
            childPop = pop.generatePop(parentPop);
            popBest = pop.getFittest(childPop);
            parentPop = childPop;
            i++;
        }
        
        SUM+=i;
        
 
        
        
        
        String chromosome = "";
        for (j = 0; j < 8; j++) {
            chromosome += popBest.Genes.get(j);
        }
        System.out.println("Chromosome " +(chromosome));
        System.out.println("Fitness:" +(popBest.getFitness()));
        System.out.println("iteration "+ i);
        System.out.println("");
        
      
   
 
    }

}
