import java.util.AbstractCollection;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        System.out.println(hi(new int[]{0,1}));
    }
    public static int hi(AbstractCollection<Integer> list){
        int sum=0;
        for(int i:list){
            sum+=i;
        }
        return sum;
    }
}