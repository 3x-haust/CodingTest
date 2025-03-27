import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String command = sc.next();
            
            if (command.equals("push")) {
                int x = sc.nextInt();
                push(x);
            } else if (command.equals("pop")) {
                pop();
            } else if (command.equals("size")) {
                size();
            } else if (command.equals("empty")) {
                empty();
            } else if (command.equals("top")) {
                top();
            }
        }
        sc.close();
    }

    public static void push(int n) {
        stack.push(n);
    }

    public static void pop() {
        System.out.println(stack.isEmpty() ? -1 : stack.pop());
    }

    public static void size() {
        System.out.println(stack.size());
    }

    public static void empty() {
        System.out.println(stack.isEmpty() ? 1 : 0);
    }

    public static void top() {
        System.out.println(stack.isEmpty() ? -1 : stack.peek());
    }
}
