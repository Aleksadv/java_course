import java.util.Scanner;

public class Greeting{

    public static void  main(String[] arg){
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите ваше имя: ");

        String name = scan.next();
        System.out.println("Привет, " + name);
    }
    }