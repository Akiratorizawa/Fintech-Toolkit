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
            System.out.println("Please enter a number from 1 to 5.");
            System.out.print("Option: ");

            
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
            transitionDelay();
            currencyDenomination();
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

    public static void currencyDenomination() {
        System.out.println("""
            \n========================================
                CURRENCY DENOMINATION CALCULATOR
            ========================================
            """);
        
        System.out.println("This tool calculates the total amount of cash bills and coins needed to amount to a certain amount of pesos.");
        
        boolean searching = true;
        Long amount = 0L;

        while (searching) {
            System.out.print("Enter the amount of money you would like to convert into bills and coins (Whole number): ");
            
            String input = scanner.nextLine();

            try {
                amount = Long.parseLong(input);
                searching = false;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount of money.\n");
            }
        }

        Long thousands = 0L;
        Long fiveHundreds = 0L;
        Long hundreds = 0L;
        Long fiftys = 0L;
        Long tens = 0L;
        Long fives = 0L;

        System.out.println();

        transitionDelay();
        
        // Getting denomination values
        if (amount >= 1000) {
            thousands = amount / 1000;
            amount = amount - (1000 * thousands);
            System.out.println("Thousands (1000s): " + thousands);
        }

        if (amount >= 500) {
            fiveHundreds = amount / 500;
            amount = amount - (500 * fiveHundreds);
            System.out.println("Five Hundreds (500s): " + fiveHundreds);

        }

        if (amount >= 100) {
            hundreds = amount / 100;
            amount = amount - (100 * hundreds);
            System.out.println("Hundreds (100s): " + hundreds);
        }

        if (amount >= 50) {
            fiftys = amount / 50;
            amount = amount - (50 * fiftys);
            System.out.println("Fiftys (50s): " + fiftys);
        }

        if (amount >= 10) {
            tens = amount / 10;
            amount = amount - (10 * tens);
            System.out.println("Tens (10s): " + tens);

        }

        if (amount >= 5) {
            fives = amount / 5;
            amount = amount - (5 * fives);
            System.out.println("Fives (5s): " + fives);
        }

        if (amount != 0) {
            System.out.println("Ones (1s): " + amount);
        }

        searching = true;

        transitionDelay();
        System.out.println("\nWould you like to input another amount?");
        while (searching) {
            System.out.println("Please enter Y or N.");
            System.out.print("Option: ");

            String input = scanner.nextLine().toLowerCase();

            if (input.equals("y")) {
                transitionDelay();
                searching = false;
                currencyDenomination();
                return;
            }

            else if (input.equals("n")) {
                transitionDelay();
                searching = false;
                mainMenu();
                return;
            }

            else {
                System.out.println();
            }
        }
    }

    public static void transitionDelay() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}