import java.util.Stack;

/**
 * @author Vitalii Zinchenko
 */
public class PushPopIsEmptyMinLinearStack<T extends Comparable<T>> {

    private Stack<T> stack = new Stack<>();
    private Stack<T> min = new Stack<>();

    public void push(T item) {
        stack.push(item);
        if(min.isEmpty() || item.compareTo(min.peek()) < 1) {
            min.push(item);
        } else {
            min.push(min.peek());
        }
    }

    public T pop() {
        min.pop();
        return stack.pop();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public T min() {
        return min.peek();
    }

    @Override
    public String toString() {
        return "PushPopIsEmptyMinLinearStack{" +
                "stack=" + stack +
                ", min=" + min +
                '}';
    }

    public static void main(String[] args) {
        PushPopIsEmptyMinLinearStack<Integer> stack = new PushPopIsEmptyMinLinearStack<>();
        System.out.println(stack.isEmpty());
        stack.push(18);
        System.out.println(stack);
        stack.push(19);
        System.out.println(stack);
        stack.push(29);
        System.out.println(stack);
        stack.push(15);
        System.out.println(stack);
        stack.push(16);
        System.out.println(stack);

        stack.pop();
        System.out.println(stack);
        System.out.println(stack.min());
        stack.pop();
        System.out.println(stack);
        System.out.println(stack.min());
        System.out.println(stack.isEmpty());
    }
}
