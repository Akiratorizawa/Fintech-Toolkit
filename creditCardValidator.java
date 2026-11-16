import java.util.Scanner;

public class creditCardValidator {
    public static String Credit_Type(String number){
        //length check
        //American Express uses 15-digit numbers
        //MasterCard uses 16-digit numbers
        //Visa uses 13- and 16-digit numbers

        //All American Express numbers start with 34 or 37
        //most MasterCard numbers start with 51, 52, 53, 54, or 55 (they also have some other potential starting numbers which we won’t concern ourselves with for this problem)
        //all Visa numbers start with 4

        //ALL OK
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
        int[] twoDigits;
        for(int i = 1; i <= max; i++){
            //the sum of the digits that weren’t multiplied by 2
            if (i%2 != 0) {
                //System.out.println("Adding: " + Character.getNumericValue(CardNumberArray[(number.length())-i]));
                toBeAdded += Character.getNumericValue(CardNumberArray[(number.length())-i]);
                //System.out.println("Sum: " + toBeAdded);
                //System.out.println();
            }else {
                //System.out.println("Product: " + Character.getNumericValue(CardNumberArray[(number.length())-i]));
                if ((Character.getNumericValue(CardNumberArray[(number.length())-i])) * 2 >=10) {
                    toBeMultiplied += (Character.getNumericValue(CardNumberArray[(number.length())-i])*2) % 10 + (Character.getNumericValue(CardNumberArray[(number.length())-i])*2) / 10;
                }

                else {
                toBeMultiplied += (Character.getNumericValue(CardNumberArray[(number.length())-i])*2);
                }
                //System.out.println("Total: " + toBeMultiplied);
                //System.out.println();
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
        
        //need alphabet checker

        // while (CardNumber.isEmpty() == true) {
        //     System.out.print("Enter Card Number to verify: ");
        //     CardNumber = scan.nextLine();
        //     if (CardNumber.isEmpty() == true) {
        //         System.out.println("Try Again: \n");
        //     }
        // }
        // String CardType = Credit_Type(CardNumber);
        // System.out.println("Type of Card Detected: " + CardType);
        // System.out.println(LuhnAlgorithm(CardNumber));
        
        while (true) {
            System.out.print("Enter Card Number to Verify: ");
            input = scan.nextLine();
            if (input.isEmpty() == true) {
                System.out.println("Try Again or enter x to view summary: \n");
            }
            if ((input.equals("x")) == true) {
                break;
            }
            CardNumber[i] = input;
            i++;
        }


        System.out.println("=======================================================================");
        System.out.printf("%-25s | %-10s | %-25s%n",
                "Card Number", "Type", "Legitimacy");
        System.out.println("-----------------------------------------------------------------------");
        
        for(int x=0; x<CardNumber.length ; x++){
            if ((CardNumber[x]) == null) {
                break;
            }
            String CardType = Credit_Type(CardNumber[x]);
            System.out.printf("%-25s | %-10s | %-25s%n",
        CardNumber[x], CardType, LuhnAlgorithm(CardNumber[x]));
        }
        System.out.println("=======================================================================");
    }
}
