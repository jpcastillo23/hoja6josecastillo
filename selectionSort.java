

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * POr Jose Pablo Castillo
 * @author JayPiCastillo
 * Jose Pablo Castillo Rodas
 * Referencia: LIbro de java structures, de donde se sacaron los tipos de sorting existentes
 */
public class selectionSort {
    public int[] answer;
    /**
     * 
     * @param matriz
     * @param which 
     */
    public selectionSort(int[] matriz, boolean which){
        int numUnsorted = matriz.length;
        int index;
        int max;
        if(which){
            while(numUnsorted>0){
                //Determine maximum Value in array
                max=0;
                for(index =1;index < numUnsorted ;index++){
                    if(matriz[max]< matriz[index])
                        max=index;
                }
                swap(matriz,max,numUnsorted-1);
                numUnsorted--;
            }
        }
        else{
              matriz=quickSort(matriz,matriz.length);        
        }
        answer=matriz;
    }
    /**
     * 
     * @param data
     * @param i
     * @param j 
     */
    public static void swap(int[] data,int i , int j){
        //this method create the interchange
        int temp;
        temp = data[i];
        data[i]= data[j];
        data[j] = temp;
    }/**
     * 
     * @return 
     */
    public int[] getMatriz(){
        return answer;
    }
    /**
     * 
     * @param data
     * @param left
     * @param right
     * @return 
     */
    private static int partition(int data[], int left, int right){
        // pre: left <= right 
        // post: data[left] placed in the correct (returned) location
    
        while (true) {
            // move right "pointer" toward left 
            while (left < right && data[left] < data[right]) right--; 
                if (left < right) 
                    swap(data,left++,right); 
                else return left; 
            // move left pointer toward right 
            while (left < right && data[left] < data[right]) left++; 
                if (left < right) 
                    swap(data,left,right--); 
                else return right;
        }
    }/**
     * 
     * @param data
     * @param n
     * @return 
     */
    public static int[] quickSort(int[] data, int n){
        quickSortRecursive(data,0,n-1);
        return data;
    }/**
     * 
     * @param data
     * @param left
     * @param right 
     */
    private static void quickSortRecursive(int[] data,int left,int right){
        // pre: left <= right 
        // post: data[left..right] in ascending order 
    int pivot;	
    // the final location of the 
    if (left >= right) return; 
    pivot = partition(data,left,right);	/* 1- place pivot*/
    quickSortRecursive(data,left,pivot-1); /*2.- sprt small*/
    quickSortRecursive(data,pivot+1,right);/* 3.- sort Large*/
    /* done! */
    }

    
    
}
