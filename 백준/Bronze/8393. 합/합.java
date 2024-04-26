import java.util.stream.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        System.out.println(IntStream.range(1, new Scanner(System.in).nextInt() + 1).sum());
    }
}