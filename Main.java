import java.math.BigDecimal;
import java.math.RoundingMode;
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
            transitionDelay();
            monthlyInstallment();
        }

        else if (option == 4) {
            // TODO, loanInterest()
        }

        else {
            // TODO, percentageAndTax()
        }
    }

    public static void currencyDenomination() {
        while (true) {
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
                }

                else if (input.equals("n")) {
                    transitionDelay();
                    searching = false;
                    mainMenu();
                    break;
                }

                else {
                    System.out.println();
                }
            }
        }
    }

    public static void monthlyInstallment() {
        while (true) {
            System.out.println("""
                \n==========================================
                       MONTHLY INSTALLMENT CALCULATOR
                ==========================================
                """);
            
            System.out.println("This tool calculates the monthly principal and interest installment payments for amortized loans.\n");
            System.out.println("Please enter the following:\n");
            
            boolean searching = true;

            BigDecimal principal = BigDecimal.valueOf(0.0);
            BigDecimal interestRate = BigDecimal.valueOf(0.0);
            int months = 0;

            double principalInput = 0;

            while (searching) {
                System.out.print("Principal Amount: ");

                try {
                    principalInput = Double.parseDouble(scanner.nextLine());

                    if (principalInput > 0) {
                        principal = BigDecimal.valueOf(principalInput).setScale(2, RoundingMode.HALF_UP);
                        searching = false;
                        System.out.println();
                    }

                    else {
                        System.out.println("Please enter an whole number amount greater than 0.\n");
                    }
                }

                catch (NumberFormatException e) {
                    System.out.println("Please enter a whole number.\n");
                }
            }

            searching = true;

            double interestRateInput = 0;
            while (searching) {
                System.out.print("Annual Interest Rate: ");
                try {
                    interestRateInput = Double.parseDouble(scanner.nextLine()) / 100;

                    if (interestRateInput >= 0 && interestRateInput <= 100) {
                        interestRate = BigDecimal.valueOf(interestRateInput)
                                        .divide(BigDecimal.valueOf(12.0), 10, RoundingMode.HALF_UP);
                        searching = false;
                        System.out.println();
                    }

                    else {
                        System.out.println("Please enter an annual interest rate from 0 to 100.\n");
                    }
                }

                catch (NumberFormatException e) {
                    System.out.println("Please enter an annual interest rate from 0 to 100.\n");
                }
            }

            searching = true;

            while (searching) {
                System.out.print("Payment Period (in months): ");
                try {
                    months = Integer.parseInt(scanner.nextLine());

                    if (months > 0) {
                        searching = false;
                        System.out.println();
                    }

                    else {
                        System.out.println("Please enter a payment period of greater than 0 months.\n");
                    }
                }

                catch (NumberFormatException e) {
                    System.out.println("Please enter a whole number as the payment period in months.\n");
                }
            }

            
            BigDecimal tmpNumerator = interestRate
                                        .multiply(interestRate.add(BigDecimal.valueOf(1.0)).pow(months));

            BigDecimal tmpDenominator = (interestRate
                                            .add(BigDecimal.valueOf(1.0)).pow(months))
                                            .subtract(BigDecimal.valueOf(1.0));
            
            BigDecimal totalMonthlyPayment = principal.multiply(
                tmpNumerator.divide(tmpDenominator, 10, RoundingMode.HALF_UP)
            ).setScale(2, RoundingMode.HALF_UP);

            BigDecimal remainingBalance = principal;
            
            System.out.println("                   ===========================");
            System.out.println("                        PAYMENT BREAKDOWN");
            System.out.println("                   ===========================");

            String format = "| %-5s | %-17s | %-16s | %-17s |%n";
            String line = "+-------+-------------------+------------------+-------------------+";

            System.out.println(line);
            System.out.printf(format, "Month", "Principal Payment", "Interest Payment", "Remaining Balance");
            System.out.println(line);

            transitionDelay();
            
            for (int i = 1; i <= months; i++) {
                BigDecimal interestPayment = remainingBalance.multiply(interestRate).setScale(2, RoundingMode.HALF_UP);
                BigDecimal principalPayment = totalMonthlyPayment.subtract(interestPayment).setScale(2, RoundingMode.HALF_UP);
                remainingBalance = remainingBalance.subtract(principalPayment).setScale(2, RoundingMode.HALF_UP);

                if (i == months) {
                    principalPayment = principalPayment.add(remainingBalance);
                    remainingBalance = BigDecimal.ZERO;
                }

                System.out.printf(format, i, principalPayment, interestPayment, remainingBalance);
                System.out.println(line);
            }

            System.out.println("Monthly installment breakdown for a principal amount of P" + principalInput + ", and an annual interest rate of " + interestRateInput * 100 + "% over " + months + " months.");
            
            transitionDelay();
            System.out.println();
        
            System.out.println("\nWould you like to input another loan amount?");

            searching = true;
            boolean quit = false;

            while (searching) {
                System.out.println("Please enter Y or N.");
                System.out.print("Option: ");

                String input = scanner.nextLine().toLowerCase();

                if (input.equals("y")) {
                    transitionDelay();
                    searching = false;
                }

                else if (input.equals("n")) {
                    transitionDelay();
                    searching = false;
                    quit = true;
                }

                else {
                    System.out.println();
                }
            }

            if (quit == true) {
                transitionDelay();
                System.out.println();
                mainMenu();
                return;
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