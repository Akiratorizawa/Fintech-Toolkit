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
            [5] Percentage and Tax Calculator
            [6] Quit\n""");
        
        boolean searching = true;
        int option = 0;

        // Looping for valid user input
        while (searching) {
            System.out.println("Please enter a number from 1 to 6.");
            System.out.print("Option: ");

            
            String input = scanner.nextLine();

            try {
                option = Integer.parseInt(input);

                if (option >= 1 && option <= 6) {
                    searching = false;
                    System.out.println();
                }

                else {
                    System.out.println();
                }
            }

            catch (NumberFormatException e) {
                System.out.println();
            }
        }
    
        switch (option) {
            // TODO, cardValidator()
            case 1 -> {
                creditCardValidator();
            }

            case 2 -> {
                transitionDelay();
                currencyDenomination();
            }

            case 3 -> {
                transitionDelay();
                monthlyInstallment();
            }

            case 4 -> {
                // TODO, loanInterest(), will be changed with something else
                System.out.println("todo, loan interest, will be changed");
            }

            case 5 -> {
                percentage_and_tax_calc();
            }

            case 6 ->  {
                System.out.println("Thank you for using Fintech Toolkit.");
                transitionDelay();
                return;
            }
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
            System.out.println("Enter Q to go back to the main menu.\n");
            
            boolean searching = true;
            Long amount = 0L;

            while (searching) {
                System.out.print("Enter the amount of money you would like to convert into bills and coins (Whole number): ");
                
                String input = scanner.nextLine();

                if (input.toLowerCase().equals("q")) {
                    transitionDelay();
                    System.out.println();
                    mainMenu();
                    return;
                }

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
                System.out.println("Please enter Y to input another currency amount or N to return to the main menu.");
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
                    return;
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
            
            System.out.println("This tool calculates the monthly principal and interest installment payments for amortized loans.");
            System.out.println("Enter Q at any time to return to the main menu.\n");

            System.out.println("Please enter the following:\n");
            
            boolean searching = true;

            BigDecimal principal = BigDecimal.valueOf(0.0);
            BigDecimal interestRate = BigDecimal.valueOf(0.0);
            int months = 0;

            double principalInput = 0;
            String input;

            while (searching) {
                System.out.print("Principal Amount: ");

                try {
                    input = scanner.nextLine();

                    if (input.toLowerCase().equals("q")) {
                        transitionDelay();
                        System.out.println();
                        mainMenu();
                        return;
                    }

                    principalInput = Double.parseDouble(input);

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
                    input = scanner.nextLine();

                    if (input.toLowerCase().equals("q")) {
                        transitionDelay();
                        System.out.println();
                        mainMenu();
                        return;
                    }

                    interestRateInput = Double.parseDouble(input) / 100;

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

                    input = scanner.nextLine();

                    if (input.toLowerCase().equals("q")) {
                        transitionDelay();
                        System.out.println();
                        mainMenu();
                        return;
                    }

                    months = Integer.parseInt(input);

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

            String format = "| %-5s | %-17s | %-16s | %-16s | %-17s |%n";
            String line = "+-------+-------------------+------------------+------------------+-------------------+";

            System.out.println(line);
            System.out.printf(format, "Month", "Principal Payment", "Interest Payment", "Total Payment", "Remaining Balance");
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

                BigDecimal totalPayment = interestPayment.add(principalPayment).setScale(2, RoundingMode.HALF_UP);

                System.out.printf(format, i, principalPayment, interestPayment, totalPayment, remainingBalance);
                System.out.println(line);
            }

            System.out.println("Monthly installment breakdown for a principal amount of P" + principalInput + ", and an annual interest rate of " + interestRateInput * 100 + "% over " + months + " months.");
            
            transitionDelay();
            System.out.println();
        
            System.out.println("\nWould you like to input another loan amount?");

            searching = true;
            boolean quit = false;

            while (searching) {
                System.out.println("Please enter Y to input another loan amount or N to return to the main menu.");
                System.out.print("Option: ");

                input = scanner.nextLine().toLowerCase();

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

    //CREDIT CARD VALIDATOR AREA
    public static String Credit_Type(String number){
        if (number.length()>=13 && number.length()<=19 && number.length() != 14) {
            int firstFourNumbers = Integer.parseInt(number.substring(0, 4));

            if (firstFourNumbers>=3528 && firstFourNumbers<=3589) {
                return "JCB";
            }

            if (number.startsWith("34") || number.startsWith("37")) {
                return "AMEX";
            }else if (number.startsWith("51") || number.startsWith("52") || number.startsWith("53") || number.startsWith("54") || number.startsWith("55")) {
                return "MasterCard";
            }else if (number.startsWith("4")) {
                return "Visa";
            }
        }
        return "Invalid Card Number";
    }

    public static String LuhnAlgorithm(String number){
        char[] CardNumberArray = number.toCharArray();
        int max = (CardNumberArray.length);
        int toBeAdded = 0;
        int toBeMultiplied = 0;
        for(int i = 1; i <= max; i++){
            if (i%2 != 0) {
                toBeAdded += Character.getNumericValue(CardNumberArray[(number.length())-i]);
            }else {
                if ((Character.getNumericValue(CardNumberArray[(number.length())-i])) * 2 >=10) {
                    toBeMultiplied += (Character.getNumericValue(CardNumberArray[(number.length())-i])*2) % 10 + (Character.getNumericValue(CardNumberArray[(number.length())-i])*2) / 10;
                }

                else {
                toBeMultiplied += (Character.getNumericValue(CardNumberArray[(number.length())-i])*2);
                }
            }
        }
        if (((toBeAdded+toBeMultiplied)%10)==0) {
            return "Card is Legitimate!";
        } else{
            return "Card is Illegitimate!";
        }
    }


public static void displayResult(String cardNumber, String type, String legitimacy) {

    System.out.println("====================================================================");
    System.out.printf("%-25s | %-18s | %-15s%n",
            "Card Number", "Type", "Legitimacy");
    System.out.println("--------------------------------------------------------------------");

    System.out.printf("%-25s | %-18s | %-15s%n",
            cardNumber, type, legitimacy);

    System.out.println("====================================================================");
}


    public static void creditCardValidator(){
        Scanner scan = new Scanner(System.in);
        //all input as string
        String[] CardNumber = new String[999]; 
        String input = "";
        int i = 0;

        System.out.printf("""
        =======================================================================
                                Credit Card Validator
        =======================================================================

        It validates the Credit Number with Luhn's Algorhitm and its Card Type
        Only work with JCB, AMEX, MasterCard, and Visa
        """);
        System.out.println();
        System.out.println("Enter Q to go back to the main menu.\n");
        //need alphabet checker
        while (true) {
            System.out.print("Enter Card Number to Verify: ");
            input = scan.nextLine();
            if (input.isEmpty() == true) {
                System.out.println("Try Again: \n");
            }else if ((input.equals("Q")) == true || (input.equals("q")) == true) {
                break;
            }else if ((input.matches("[0-9]+")) == false) {
                System.out.println("Numbers only!");
        }
            CardNumber[i] = input;
            i++;
        }


        System.out.println("\n\n=======================================================================");
        System.out.printf("%-25s | %-10s | %-25s%n",
                "Card Number", "Type", "Legitimacy");
        System.out.println("-----------------------------------------------------------------------");
        
        for(int x=0; x<CardNumber.length ; x++){
            if ((CardNumber[x]) == null || ((CardNumber[x]).matches("[0-9]+")) == false) {
                continue;
            }
            String CardType = Credit_Type(CardNumber[x]);
            System.out.printf("%-25s | %-10s | %-25s%n",
        CardNumber[x], CardType, LuhnAlgorithm(CardNumber[x]));
        }
        System.out.println("=======================================================================");
    }
    //END OF CREDIT CARD VALIDATOR AREA

    //START OF PRECENTAGE AND TAX CALCULATOR AREA
    public static void percentage_and_tax_calc() {
        Scanner scan = new Scanner(System.in);

        System.out.printf("""
        ====================================================================
                            Percentage and Tax calculator
        ====================================================================
        Description

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
        //choice = "";
        System.out.print("Choice: "); //it asks twice??
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