import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        System.out.println("""
            ===================================================
                              FINTECH TOOLKIT
            ===================================================\n
            """);
        
        System.out.println("""
            [1] Credit Card Validator
            [2] Currency Denomination Calculator
            [3] Monthly Installment Calculator
            [4] Loan Interest Calculator
            [5] Percentage and Tax Calculator\n""");
        
        boolean searching = true;
        int option = 0;

        // Looping for valid user input
        while (searching) {
            System.out.print("Please enter a number from 1 to 5: ");
            
            String input = scanner.nextLine();

            try {
                option = Integer.parseInt(input);

                if (option >= 1 && option <= 5) {
                    searching = false;
                }

                else {
                    System.out.println();
                }
            }

            catch (NumberFormatException e) {
                System.out.println();
            }
        }
    
        if (option == 1) {
            // TODO, cardValidator()
        }

        else if (option == 2) {
            // TODO, currencyDenomination()
        }

        else if (option == 3) {
            // TODO, monthlyInstallment()
        }

        else if (option == 4) {
            // TODO, loanInterest()
        }

        else {
            // TODO, percentageAndTax()
        }
    }
}