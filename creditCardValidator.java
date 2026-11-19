import java.util.Scanner;

public class creditCardValidator {
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


    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        //all input as string
        String[] CardNumber = new String[999]; 
        String input = "";
        int i = 0;

        System.out.printf("""
        =======================================================================
                                Credit Card Validator
        =======================================================================

        This validates Credit Card numbers with its Card Type, and Luhn's Algorhithm
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
}
