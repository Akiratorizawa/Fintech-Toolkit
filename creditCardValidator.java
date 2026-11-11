import java.util.Scanner;

public class creditCardValidator {
    public static void isCreditNumReal(String number){
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
                System.out.println("JCB");
                return;
            }

            if (number.startsWith("34") || number.startsWith("37")) {
                System.out.println("AMEX");
            }else if (number.startsWith("51") || number.startsWith("52") || number.startsWith("53") || number.startsWith("54") || number.startsWith("55")) {
                System.out.println("MasterCard");
            }else if (number.startsWith("4")) {
                System.out.println("Visa");
            }
        } else{
            System.out.println("Invalid Card Number");
        }
    }

    public static boolean LuhnAlgorithm(String number){
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
            return true;
        } else{
            return false;
        }
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        //all input as string
        System.out.println("Card Number: ");
        String CardNumber = scan.nextLine();
        isCreditNumReal(CardNumber);
        System.out.println(LuhnAlgorithm(CardNumber));
        

    }
}
