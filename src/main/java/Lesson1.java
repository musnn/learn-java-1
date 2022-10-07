import de.vandermeer.asciitable.AsciiTable;

import java.util.Scanner;

public class Lesson1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Name:");
        String name = sc.nextLine();

        System.out.println("Surname:");
        String surname = sc.nextLine();

        System.out.println("Age:");
        int age = sc.nextInt();

        System.out.println("Profession:");
        sc.nextLine();
        String prof = sc.nextLine();
        sc.close();

        AsciiTable at = new AsciiTable();

        at.addRule();
        at.addRow("Name", name);
        at.addRule();
        at.addRow("Surname", surname);
        at.addRule();
        at.addRow("Age", age);
        at.addRule();
        at.addRow("Profession", prof);
        at.addRule();
        String rend = at.render();
        System.out.println(rend);
    }
}
