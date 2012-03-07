package neuralcryptography.net;

import java.util.ArrayList;
/**
 *
 * @author phoenix
 */
public class TPM {

    private int[] w, h;
    public int K, N, L;
    private int output;

    /* constructor */
    public TPM(int n, int k, int l) {
        this.K = k;
        this.N = n;
        this.L = l;
        w = new int[k * n];
        h = new int[k];
    }

    /* methods of Tree Parity Machines */
    public void calcOutput(int[] x) {
        output=1;
        for (int i = 0; i < K; i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                sum += w[i * N + j] * x[i * N + j];
            }
            h[i] = sigma(sum);
            output *= sigma(sum);
        }
    }
    public void updateWeight(int[] x){
        for(int i=0;i<K;i++){
           for(int j=0;j<N;j++){
               int newW=w[i*N+j];
               //calcOutput(x);
               newW+=x[i*N+j]*output*equ(output,h[i]);
               if(newW>L) newW=L;
               if(newW<-L) newW=-L;
               w[i*N+j]=newW;
           }
        }
    }
    public void randomize(){
        for(int i=0;i<K*N;i++){
            w[i]=(int)(Math.random()*L);
        }
    }
    public int getVectorValue(){
        int s=0;
        for(int i=0;i<K*N;i++){
            s+=w[i];
        }
        return s;
    }

    public int getOutput(){
        return output;
    }
    public int getSum(TPM a){
        int s=0;
        for(int i=0;i<K*N;i++){
            s+=Math.abs(w[i]-a.getWeight(i));
        }
        return s;
    }
    
    /* helper functions */
    public static int sigma(double r) {
        return (r > 0) ? 1 : -1;
    }
    public static int equ(int a,int b){
        return (a==b)?1:0;
    }
    
    /*getter functions */
    public int getWeight(int i){
        return w[i];
    }
   

    public void display(){
        for(int i=0;i<K;i++)
            for(int j=0;j<N;j++){
                System.out.print(" "+w[i*N+j]);
            }
        System.out.println("");
    }
    public ArrayList<String> getWeightVector(){
        
        ArrayList<String> weightList=new ArrayList();
        for(int i=0;i<K;i++){
            String weight="";
            for(int j=0;j<N;j++){
                weight=""+(w[i*N+j]);                
            }
            weightList.add(weight.trim());
        }
        return weightList;
    }
    
    /* making the key function */
    public String makeKey(){
        StringBuilder key=new StringBuilder();
        int keySize=(int)(ABC.length()/(L*2+1));
        int keyLength=(int)(K*N /keySize);
        for(int i=1;i<keyLength;i++){
            int k=1;
            for(int j=(i-1)*keySize;j<i*keySize;j++){
                k+=w[j]+L;
            }
            key.append(ABC.charAt(k));
            
        }
        return key.toString();
    }
    static final String ABC="ABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789abcdefghijklmnopqrstuvwxyz";
}
