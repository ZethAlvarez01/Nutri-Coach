package models.NeuralNet;

import java.util.ArrayList;

/**
 *
 * @author Zeth
 */
public class Crear_RN {
    
    public Crear_RN(){
        
    }
    
    static ArrayList<Capa_neuronas> create_nn(int[] topology,int act_f){
       ArrayList<Capa_neuronas> nn=new ArrayList<>();
       
       for(int i=0;i<topology.length-1;i++){
           Capa_neuronas l=new Capa_neuronas(topology[i],topology[i+1],act_f);
           nn.add(l);
       }
       
       return nn;
    }
}
