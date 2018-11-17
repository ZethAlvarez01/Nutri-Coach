package models.NeuralNet;
/**
 *
 * @author Zeth
 */
public class libMatrices {
    
    //Suma de matrices
    public double[][] sum(double[][] a,double[][] b){
        if((a.length != b.length) || (a[0].length != b[0].length)){
            System.out.println("SUMA Error: Matrices con un orden diferente");
            return null;
        }else{
            double[][] res =new double[a.length][a[0].length];
            for(int i=0;i<a.length;i++){
                for(int j=0;j<a[i].length;j++){
                    res[i][j]=a[i][j]+b[i][j];
                }
            }
            return res;
        }
    }
    
    //Resta de matrices
    public double[][] res(double[][] a,double[][] b){
        if((a.length != b.length) || (a[0].length != b[0].length)){
            System.out.println("RESTA Error: Matrices con un orden diferente");
            return null;
        }else{
            double[][] res =new double[a.length][a[0].length];
            for(int i=0;i<a.length;i++){
                for(int j=0;j<a[i].length;j++){
                    res[i][j]=a[i][j]-b[i][j];
                }
            }
            return res;
        }
    }
    
    //Producto escalar
    public double[][] escalar(double[][] a,double s){
        double[][] res =new double[a.length][a[0].length];
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[i].length;j++){
                res[i][j]=a[i][j]*s;
            }
        }
        return res;
   }
   
   //Producto de la matriz DOUBLE * DOUBLE
   public double[][] dot(double[][] a, double[][] b){
       if(a[0].length != b.length){
           System.out.println("Error: Columna de la matriz A ("+a[0].length+") es diferente a "
                            + "la fila de la matriz B ("+b.length+")");
           return null;
       }else{
        double[][] res=new double[a.length][b[0].length];
        
        for(int i=0;i<res.length;i++){
            for(int j=0;j<res[0].length;j++){
                double sum=0;
                for(int k=0;k<a[0].length;k++){
                    sum+=a[i][k]*b[k][j];
                }
                res[i][j]=sum;
            }
        }
        
        return res;
       }
   }
   
   //Transpuesta de la matriz
   public double[][] trans(double[][] a){
       double[][] res=new double[a[0].length][a.length];
       
       for(int i=0;i<res.length;i++){
           for(int j=0;j<res[0].length;j++){
               res[i][j]=a[j][i];
           }
       }
       
       return res;
   }
   
   //Print matrix DOUBLE
   public void print(double[][] a){
       for(int i=0;i<a.length;i++){
           for(int j=0;j<a[i].length;j++){
               System.out.print(a[i][j]+" ");
           }
           System.out.println();
       }
       System.out.println();
   }
   
}
