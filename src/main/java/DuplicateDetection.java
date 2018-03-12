/**
 * @author Vitalii Zinchenko
 */
public class DuplicateDetection {

    public static int duplicateDetectionWoExtraSpace(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            int rawItem = arr[i];
            int item = rawItem < 0 ? -rawItem : rawItem;
            if(arr[item] < 0) return item;
            else arr[item] = -arr[item];
        }
        throw new IllegalArgumentException("The array doesn't have duplicates");
    }

    public static int duplicateDetectionWithArray(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for(int i = 1; i < arr.length; i++) {
            int item = arr[i];
            if(item < min) min = item;
            if(item > max) max = item;
        }

        System.out.println("min: " + min);
        System.out.println("max: " + max);

        int[] histogram = new int[max - min + 1];
        for(int i = 0; i < arr.length; i++) {
            int item = arr[i];
            histogram[item - 1] += 1;
        }

        for(int i = 0; i < histogram.length; i++) {
            if(histogram[i] > 1) return i + 1;
        }

        throw new IllegalArgumentException("The array doesn't have duplicates");
    }

    public static void main(String[] args) {
        int duplicate = duplicateDetectionWoExtraSpace(new int[]{3, 1, 1, 3});
        System.out.println(duplicate);
        int duplicate2 = duplicateDetectionWithArray(new int[]{3, 1, 1, 3});
        System.out.println(duplicate2);
    }
}
