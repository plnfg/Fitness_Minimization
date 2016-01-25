/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binary;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Pelin
 */



public class Individual {
    private final int GeneLength=8;
    ArrayList<String> Genes=new ArrayList<>();
    private int fitness;

 
    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    
    

     void  setAllGenes() {
        int i;
        Random rnd=new Random();
        for (i = 0; i <GeneLength; i++) {
            Genes.add(String.valueOf(rnd.nextInt(2)));
        }
   
    }

     
     
     
  
    
    
}

