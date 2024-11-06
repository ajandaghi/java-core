import com.sun.javafx.scene.layout.region.Margins;
import java.lang.Object;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.sun.jmx.mbeanserver.Util.cast;
import static java.util.Collections.sort;

public class ArraySortAlgorithm {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
        Float[] arr=new Float[]{2.3f,1f,3f,5f,1f,4f,1f,2f,1f,3f,1f,-2f};
        String[] str=new String[]{"ali","ale","a","ba","b","bb"};
        //sortDesc2(arr);
        ArraySortAlgorithm srt=new ArraySortAlgorithm();
        System.out.println(Arrays.stream(srt.sortDescUnique(arr)).map(a -> a + " ").collect(Collectors.joining()));

    }
    public  <T extends Comparable<T>> Object[] sortDescUnique(T[] arr) {
        Object[] arr2=new Object[arr.length];
        System.arraycopy(arr,0,arr2,0,arr.length);
        T max;
        T min=arr[0];
        int k=0;
        for(int i=0;i<arr.length;i++){
            max=min;
            for(int j=0;j<arr.length;j++){
                if((i==0 && arr[j].compareTo(max)>0 ) || (arr[j].compareTo(max)>0 && arr[j].compareTo((T)arr2[i-1])<0)){
                    max=arr[j];
                }
                if((arr[j].compareTo(min)<0 )){
                    min=arr[j];
                }
            }
            if(i>0 && max.compareTo((T)arr2[i-1])==0){
                k++;
            }
            arr2[i]=max;
        }
        return Arrays.copyOfRange(arr2,0, arr.length-k);
    }


    public   Number[] sortDesc1(Number[] arr) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
        Object[] arr2=new Number[arr.length];
        System.arraycopy(arr,0,arr2,0,arr.length);

        for(int i=0;i<arr.length;i++){
            Number max=arr[0];
            Number min=arr[0];
            int index=0;
            for(int j=0;j<arr.length;j++){
                if(((Number)arr[j]).floatValue()>(max.floatValue()) ){
                    max= ((Number) arr[j]).floatValue();
                    index=j;
                }

                if(((Number)arr[j]).floatValue()<(max.floatValue()) ){
                    min= ((Number) arr[j]).floatValue();

                }
            }
            arr[index]=min;
            arr2[i]=max;
          //  System.out.println(Arrays.stream(arr).map(a -> a + " ").collect(Collectors.joining()));
        }
        return (Number[])arr2;
    }
    public static <T extends Comparable<T>> T[] sortDesc2(T[] arr) {
       for(int i=0;i<arr.length;i++){
           for(int j=i+1;j<arr.length;j++){
               if(arr[j].compareTo(arr[i])>0){
                   T m=arr[j];
                   arr[j]=arr[i];
                   arr[i]=m;
                   j=i+1;
               }
              // System.out.println(Arrays.stream(arr).mapToObj(a -> a + " ").collect(Collectors.joining()));

           }
       }
       return arr;
    }
    public static <T extends Comparable<T>> Object[] removeRepeatedElements(T[] arr) {
        int k=0;
        for (int i = 0; i < arr.length -k-1; i++) {

            if (arr[i].compareTo(arr[i + 1]) == 0) {
                for (int j = i; j < arr.length - k - 1; j++) {
                    arr[j] = arr[j + 1];
                }
                i = i - 1;
                k++;
            }

        }
        Object[] arr2=new Object[arr.length-k];
        System.arraycopy(arr,0,arr2,0,arr.length-k);
        return arr2;
    }
}
