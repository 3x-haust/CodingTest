import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println( sc.nextInt() == IntStream.range(0, sc.nextInt()).map(i -> sc.nextInt() * sc.nextInt()).sum() ? "Yes" : "No");
    }
}