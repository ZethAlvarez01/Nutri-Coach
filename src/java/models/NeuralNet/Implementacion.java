package models.NeuralNet;
import java.util.ArrayList;

/**
 *
 * @author Zeth
 */
public class Implementacion {
    ArrayList<Capa_neuronas> neural_net=new ArrayList<>();
    double[][] x=new double[1][];
    
    public Implementacion(ArrayList<Capa_neuronas> neural_net,double[] x){
        this.neural_net=neural_net;
        this.x[0]=x;
    }
    
    public double[][] Implement(){
        libMatrices op=new libMatrices();

        for(int l=0;l<neural_net.size();l++){;
                double[][] z;
                double[][] producto=op.dot(x, neural_net.get(l).w);
                z=op.sum(producto, neural_net.get(l).b);
                double[][] a=new double[1][z[0].length];
                for(int i=0;i<z[0].length;i++){
                    a[0][i]=neural_net.get(l).act_f(z[0][i]);
                }
                x=a;
        }     
        
        return x;
    }
    
}
