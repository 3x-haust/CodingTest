public class Main {
    public static void main(String[] args) {
        java.util.stream.IntStream.range(1, new java.util.Scanner(System.in).nextInt() + 1).forEach(i -> System.out.println("*".repeat(i)));
    }
}