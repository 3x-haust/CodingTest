import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        
        if(a==b && a==c){
            System.out.println(10000+ a*1000);
        }else if(a==b){
            System.out.println(1000 + (100 * a));
        }else if(b==c){
            System.out.println(1000 + (100 * b));
        }else if(a==c){
            System.out.println(1000 + (100 * c));
        }else {
            if(a>b && a >c) {
                System.out.println(a*100);
            }else if(a<b && b > c) {
                System.out.println(b*100);
            } else if(c>b && a <c) {
                System.out.println(c*100);
            } 
         }
    }
}