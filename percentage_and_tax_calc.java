import java.util.Scanner;

public class percentage_and_tax_calc {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.printf("""
        ====================================================================
                            Percentage and Tax calculator
        ====================================================================
        
        Calculates Markup, Discount, and Partial Amount.

        Choose an option:
        [1] Markup Amount
        [2] Discount Amount
        [3] Partial Amount
        [Q] Quit
        """);

        //add break code 
        float base = 0;
        float rate = 0;
        float output = 0;
        String choice;
        while (true) {
        choice = "";
        System.out.print("Choice: "); //it asks somehow twice??
        choice = scan.nextLine();
        if (choice.equals("1")) {
            while ((askContinuation()) == true) {
                    System.out.print("Enter Base Price: ");
                    base = scan.nextFloat();
                    System.out.print("Enter Markup Rate: ");
                    rate = scan.nextFloat();
                    output = base + ((base)*(rate));
                    System.out.println(output);
                    System.out.println();
            }
        }else if(choice.equals("2")){
            if (askContinuation()) {
                    System.out.print("Enter Base Price: ");
                    base = scan.nextFloat();
                    System.out.print("Enter Discount Rate: ");
                    rate = scan.nextFloat();
                    output = base - (base*(rate/100));
                    System.out.println(output);
                    System.out.println();
                }
        }else if(choice.equals("3")){
            if (askContinuation()) {
                    System.out.print("Enter Base Price: ");
                    base = scan.nextFloat();
                    System.out.print("Enter Partial Discount Rate: ");
                    rate = scan.nextFloat();
                    output = base*(rate/100);
                    System.out.println(output);
                    System.out.println();
            }
        }else if(choice.equals("Q")){
            break;
            }
            } 
            choice = "";
        }
    public static boolean askContinuation() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Continue?   Y/N");
        String choice = scan.nextLine();
        System.out.println();
        if (choice.equals("Y") || choice.equals("y")) {
            return true;
        } else if (choice.equals("N") || choice.equals("n")) {
            return false;
        }
        return false;
    }
}
