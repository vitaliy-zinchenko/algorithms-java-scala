import java.util.HashSet;
import java.util.Set;

/**
 * @author Vitalii Zinchenko
 */
public class SumOfTwoItemsInArray {
    public static void main(String[] args) {
        System.out.println(isExistNaive(new int[]{1, 5, 1, 3, 9}, 10));
        System.out.println(isExistHash(new Integer[]{1, 5, 1, 3, 9}, 10));
    }

    public static boolean isExistNaive(int[] arr, int sum) {
        for(int a: arr) {
            for(int b: arr) {
                if(a + b == sum) {
                    System.out.println(a + " " + b);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isExistHash(Integer[] arr, Integer sum) {
        Set<Integer> hash = new HashSet<>();
        for(int i = 0; i < arr.length; i++) {
            int item = arr[i];
            if(hash.contains(sum - item)) {
                System.out.println(item + " " + (sum - item));
                return true;
            } else {
                hash.add(sum - item);
            }
        }
        return false;
    }
}
