import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;
import java.util.Optional;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class Main {

    static Scanner scanner = new Scanner(System.in);
    static String caller;
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
            [2] International Bank Account Number (IBAN) Validator
            [3] Currency Denomination Calculator
            [4] Monthly Installment Calculator
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
                IBANvalidator();
            }

            case 3 -> {
                transitionDelay();
                currencyDenomination();
            }

            case 4 -> {
                transitionDelay();
                monthlyInstallment();
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

            String originalAmount = String.valueOf(amount);
            String summary = "";

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
                summary += "Thousands (1000s): " + thousands + "\n";
            }

            if (amount >= 500) {
                fiveHundreds = amount / 500;
                amount = amount - (500 * fiveHundreds);
                summary += "Five Hundreds (500s): " + fiveHundreds + "\n";

            }

            if (amount >= 100) {
                hundreds = amount / 100;
                amount = amount - (100 * hundreds);
                summary += "Hundreds (100s): " + hundreds + "\n";
            }

            if (amount >= 50) {
                fiftys = amount / 50;
                amount = amount - (50 * fiftys);
                summary += "Fiftys (50s): " + fiftys + "\n";
            }

            if (amount >= 10) {
                tens = amount / 10;
                amount = amount - (10 * tens);
                summary += "Tens (10s): " + tens + "\n";

            }

            if (amount >= 5) {
                fives = amount / 5;
                amount = amount - (5 * fives);
                summary += "Fives (5s): " + fives + "\n";
            }

            if (amount != 0) {
                summary += "Ones (1s): " + amount + "\n";
            }

            System.out.println(summary);

            summary = "AMOUNT: " + originalAmount + "\n\n" + summary;
            writeHistory(summary);

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

            String summary = """
                                         ===========================
                                              PAYMENT BREAKDOWN
                                         ===========================
            """;

            String format = "| %-5s | %-17s | %-16s | %-16s | %-17s |%n";
            String line = "+-------+-------------------+------------------+------------------+-------------------+";

            summary += line + "\n";

            summary += String.format(format, "Month", "Principal Payment", "Interest Payment", "Total Payment", "Remaining Balance") + line + "\n";
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

                summary += String.format(format, i, principalPayment, interestPayment, totalPayment, remainingBalance);
                summary += line;

                if (i != months) {
                    summary += "\n";
                }
            }

            summary += "\nMonthly installment breakdown for a principal amount of P" + principalInput + ", and an annual interest rate of " + interestRateInput * 100 + "% over " + months + " months.";

            System.out.println(summary);
            
            writeHistory(summary);
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

    public static void IBANvalidator() {

        mainLoop:
        while (true) {
            System.out.println("""
                ======================================================
                    INTERNATIONAL BANK ACCOUNT NUMBER VALIDATOR
                ======================================================\n""");
        
            System.out.println("This tool verifies International Bank Account Numbers (IBAN) used for cross-border wire transfers.\n");
            
            boolean searching = true;
            String iban = null;

            while (searching) {

                System.out.print("Please enter an IBAN: ");

                iban = scanner.nextLine();
                iban = iban.replace(" ", "").toUpperCase();

                if (iban.length() < 16) {
                    System.out.println("The IBAN you entered is not long enough to be a valid IBAN.\n");
                }

                else if (iban.length() > 34) {
                    System.out.println("The IBAN you entered is too long to be a valid IBAN.\n");
                }

                else {
                    searching = false;
                    System.out.println();
                }
            }

            String countryCode = iban.substring(0, 2);
            String country = getCountry(countryCode);

            String summary = "IBAN: " + iban + "\n\n";

            if (country == null) {
                summary = summary + "IBAN STATUS: INVALID";
                System.out.println(summary);
                writeHistory(summary);
                if (askToReturn()) {
                    continue mainLoop;
                }

                else {
                    System.out.println("Thank you for using the IBAN validator.");
                    transitionDelay();
                    mainMenu();
                    return;
                }
            }

            if (!verifyLength(iban)) {
                summary = summary + "IBAN STATUS: INVALID";
                System.out.println(summary);
                writeHistory(summary);
                if (askToReturn()) {
                    continue mainLoop;
                }

                else {
                    System.out.println("Thank you for using the IBAN validator.");
                    transitionDelay();
                    mainMenu();
                    return;
                }
            }

            String moveToEnd = iban.substring(0, 4);
            String bringToStart = iban.substring(4, iban.length());

            String formattedIBAN = bringToStart.concat(moveToEnd);
            
            int x = formattedIBAN.length();

            for (int i = 0; i < x; i++) {
                char curr = formattedIBAN.charAt(i);

                if (!Character.isAlphabetic(curr) && !Character.isDigit(curr)) {
                    summary = summary + "IBAN STATUS: INVALID";
                    System.out.println(summary);
                    writeHistory(summary);
                    if (askToReturn()) {
                        continue mainLoop;
                    }

                    else {
                        System.out.println("Thank you for using the IBAN validator.");
                        transitionDelay();
                        mainMenu();
                        return;
                    }
                }

                if (Character.isLetter(curr)) {
                    int replacement = 10 + (curr % 'A');
                    
                    formattedIBAN = formattedIBAN.substring(0, i) + String.valueOf(replacement) + formattedIBAN.substring(i + 1, formattedIBAN.length());
                }

                x = formattedIBAN.length();
            }

            BigInteger ibanChecksum = new BigInteger(formattedIBAN);

            summary = summary + "IBAN STATUS: ";

            if (ibanChecksum.mod(BigInteger.valueOf(97)).compareTo(BigInteger.valueOf(1)) == 0) {
                summary = summary + "VALID" + "\nCOUNTRY: " + country + "\n\n" + getBank(iban);
            }

            else {
                summary = summary + "INVALID";
            }

            System.out.println(summary);
            writeHistory(summary);

            System.out.println();

            if (askToReturn()) {
                continue mainLoop;
            }

            else {
                System.out.println("Thank you for using the IBAN validator.");
                transitionDelay();
                mainMenu();
                return;
            }
        }
    }

    public static String getCountry(String countryCode) {
        String path = "resources/iban_rules.csv";
        String country = null;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                    
                if (countryCode.equals(values[0])) {
                    country = values[1];
                    return country;
                    
                }
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean verifyLength (String iban) {
        String path = "resources/iban_rules.csv";
        String countryCode = iban.substring(0, 2);

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                    
                if (countryCode.equals(values[0]) && iban.length() == Integer.parseInt(values[2])) {
                    return true;
                }
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }


        return false;
    }

    public static String getBank(String iban) {
        HttpClient client = HttpClient.newHttpClient();
        String ibanPayload = "[\"" + iban + "\"]";

        HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create("https://iban-analyzer.com/api/v1/public/analyze"))
                            .header("Content-Type", "application/json")
                            .POST(BodyPublishers.ofString(ibanPayload))
                            .build();

        HttpResponse<String> response = null;

        try {
            response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
            );
        }

        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        Pattern pattern = Pattern.compile("bankDetails(.*?)}");
        Matcher matcher = pattern.matcher(response.body());

        String bankDetails = null;

        if (matcher.find()) {
            bankDetails = matcher.group(1);
        }

        if (bankDetails.contains("\"bankDataAvailable\":false")) {
            return """
            BANK: UNKNOWN
            This is due to the IBAN being a published example IBAN for software development, and having possibly no connection to a real financial institution.
            """;
        }

        String[] details = {"bankName", "bankCity", "bankAddress", "bankZipCode"};
        String summary = "";

        for (int i = 0; i < details.length; i++) {
            int index = bankDetails.indexOf(details[i]);

            String category = null;

            if (details[i].equals("bankZipCode")) {
                category = "Bank " + details[i].substring(4, 7) + " " + details[i].substring(7, details[i].length()) + ": ";
            }

            else {
                category = "Bank " + details[i].split("k")[1] + ": ";
            }

            if (index != -1) {
                int end = bankDetails.indexOf(",", index);

                if (end == -1) {
                    end = bankDetails.length();
                }

                String bankDetail = bankDetails.substring((index + details[i].length()), end)
                                .replace("\"", "")
                                .replace(":", "");

                summary = summary + category + bankDetail + "\n";
            }

            else {
                summary = summary + category + "UNKNOWN\n";
            }
        }

        return summary;
    }

    public static boolean askToReturn() {
        System.out.println("Would you like to continue entering more values?");
        System.out.println("Enter Y to continue, and N to return to the main menu.");

        while (true) {
            System.out.print("Option: ");

            String option = scanner.nextLine();

            if (option.toLowerCase().equals("y")) {
                System.out.println();
                return true;
            }

            else if (option.toLowerCase().equals("n")) {
                System.out.println();
                return false;
            }

            else {
                System.out.println("Invalid option.\n");
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

    public static void writeHistory(String summary) {
        File historyFile = new File("history.txt");

        try {
            historyFile.createNewFile();
        }

        catch (IOException e) {
            System.out.println("There was an error in writing to history.\n");
            return;
        }


        Path historyPath = Paths.get("history.txt");

        String sectionBoundary = "----------------------------------------------";
        LocalDate currentDate = LocalDate.now();

        summary = currentDate + "\n" + summary + "\n";

        // Find caller of this method
        Optional<StackWalker.StackFrame> callerFrame = StackWalker.getInstance()
                                                    .walk(frames -> frames.skip(1).findFirst());

        callerFrame.ifPresent(frame -> {
            caller = frame.getMethodName();
        });

        // Ordering in history txt, based off this boundary string "----------------------------------------------"
        // 1. IBANvalidator
        // 2. currencyDenomination
        // 3. monthlyInstallment

        String[] callers = {"IBANvalidator", "currencyDenomination", "monthlyInstallment"};
        int callerBoundary = 0;

        // The array callers is made in order of their boundary lines in history.txt
        // callerBoundary = 1 would be the first boundary line, since callers[1 - 1] = "IBANvalidator", then it corresponds to the section for the iban validator history

        for (int i = 0; i < callers.length; i++) {
            if (callers[i].equals(caller)) {
                callerBoundary = i + 1;
            }
        }

        int boundaryIndex = 0;

        try {
            List<String> lines = Files.readAllLines(historyPath);

            if (lines.isEmpty()) {
                String initialFormat = """
==============================================
            IBAN VALIDATOR HISTORY
==============================================

----------------------------------------------

==============================================
        CURRENCY DENOMINATION HISTORY
==============================================

----------------------------------------------

==============================================
          MONTHLY INSTALLMENT HISTORY
==============================================

----------------------------------------------""";
                
                lines.add(initialFormat);
                Files.write(historyPath, lines);

                lines = Files.readAllLines(historyPath);
            }

            int boundaryLines = 0;

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);

                if (line.equals(sectionBoundary)) {
                    boundaryLines++;
                }

                if (boundaryLines == callerBoundary) {
                    boundaryIndex = i;
                    break;
                }
            }

            lines.add(boundaryIndex, summary);
            Files.write(historyPath, lines);

        }

        catch (IOException e) {
            System.out.println("There was an error in writing to history.\n");
            return;
        }
    }
}