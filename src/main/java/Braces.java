import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * @author Vitalii Zinchenko
 */
public class Braces {
    public static void main(String[] args) {
        System.out.println(check("()(())"));
        System.out.println(check("())()(()"));
        System.out.println(check2("[()]{}{[()()]()}"));
        System.out.println(check2("[(])"));
    }

    public static boolean check(String expr) {
        char[] arr = expr.toCharArray();
        int counter = 0;
        for(char braceChar: arr) {
            if(braceChar == '(') counter--;
            else if(braceChar == ')') counter++;
            else throw new IllegalArgumentException("Wrong char: " + braceChar);

            if(counter > 0) return false;
        }

        return counter == 0;
    }

    private static final Set<Character> openBraces;
    private static final Map<Character, Character> openToClose;
    static {
        openBraces = new HashSet<>();
        openBraces.add('(');
        openBraces.add('{');
        openBraces.add('[');
        openToClose = new HashMap<>();
        openToClose.put('(', ')');
        openToClose.put('{', '}');
        openToClose.put('[', ']');
    }

//    [()]{}{[()()]()}

    public static boolean check2(String expr) {
        Stack<Character> openStack = new Stack<>();
        for(Character brace: expr.toCharArray()) {
            if(isOpenBrace(brace)) {
                openStack.push(brace);
            } else if (openStack.isEmpty() || !compareOpenToClose(openStack.pop(), brace)) {
                return false;
            }
        }
        return openStack.isEmpty();
    }

    private static boolean isOpenBrace(Character brace) {
        return openBraces.contains(brace);
    }

    private static boolean compareOpenToClose(Character open, Character close) {
        return close.equals(openToClose.get(open));
    }
//    private static boolean isCloseBrace(Character brace) {
//        return closeBraces.contains(brace);
//    }
}
