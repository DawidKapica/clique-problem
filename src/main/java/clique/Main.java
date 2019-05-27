package clique;

import java.util.Scanner;

public class Main {
    public static void main(String[]args) {
        boolean continue_or_end = false;
        User_interface user_interface = new User_interface();
        while (continue_or_end == false) {
            user_interface.program_execute();
            System.out.println("Aby zakonczyc podaj 'END', aby kontynuowac podaj dowolna wartosc lub tekst");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            if(s.equals("END")) {
                continue_or_end = true;
            }
        }
    }
}
