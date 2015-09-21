package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean hasText(String text) {
        boolean isValid = false;

        if (text.trim().length() > 0) {
            isValid = true;
        }

        return isValid;
    }

    public static boolean isPositiveWholeNumber(String text) {
        boolean isValid = false;

        if (text.matches("[0-9]+")) {
            isValid = true;
        }

        return isValid;
    }//end isPositiveWholeNumber

    public static boolean isAlphabetic(String text) {

        boolean isValid = false;

        if (text.matches("^\\p{L}+(?: \\p{L}+)*$")) {
            isValid = true;
        }

        return isValid;

    } // End of isAlphabetic()

    public static boolean isAnyWholeNumber(String text) {
        boolean isValid = false;

        if (text.matches("[-?, 0-9]+")) {
            isValid = true;
        }

        return isValid;
    }//end isAnyWholeNumber

    public static boolean isAnyDecimalNumber(String text) {
        boolean isValid = false;
        if (text.matches("(\\+|-)?([0-9]+(\\.[0-9]+))")) {
            isValid = true;
        }
        return isValid;
    }//end isAnyDecimalNumber

    public static boolean isAnyPositiveNumber(String text) {
        boolean isValid = false;
        text = replaceCharacters(text);
        if (text.matches("(\\+)?([0-9]+((\\.)?[0-9]+))")) {
            isValid = true;
        }
        return isValid;
    }

    public static String replaceCharacters(String text) {
        //first argument is a regular express
        //replace all $ and , with an empty string
        text = text.replaceAll("[\\$,\\,]", "");

        return text;
    }

    public static boolean isValidEmail(String emailAddress) {
        Pattern pattern;
        Matcher matcher;
        //This expression handles much more then just making sure the @ 
        //and ._ _ _ is correct...
        final String REGX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        //Pattern holds the regular expression
        pattern = Pattern.compile(REGX_EMAIL);
        //matcher object to regex a string against my pattern
        matcher = pattern.matcher(emailAddress);
        //return if email is valid (true) or not (false)
        return matcher.matches();
    }//end isValidEmail

    public static boolean isValidCreditCard(String ccNum) {

        int index = ccNum.length();
        long number = 0;
        boolean alternate = false;
        long total = 0;
        boolean isValid;

        ccNum = ccNum.replace("-", "");
        ccNum = ccNum.replace(" ", "");

        if (!isPositiveWholeNumber(ccNum)) {
            isValid = false;
        } else {
            if (ccNum.length() > 12 && ccNum.length() < 17) {
                while (index-- > 0) {
                    number = Long.parseLong(ccNum.substring(index, index + 1));
                    if (alternate) {
                        number *= 2;

                        if (number > 9) {
                            number -= 9;
                        }

                    }
                    total += number;

                    alternate = !alternate;
                }//end of while loop

                isValid = (total % 10) == 0;

            }//end if for length check
            else {
                isValid = false;
            }//end else for length check
        }//end else for !isPositiveWholeNumber
        return isValid;

    }//end isValidCreditCard

    public static boolean isZipValid(String zip) {
        boolean retval = false;
        String zipCodePattern = "\\d{5}(-\\d{4})?";
        retval = zip.matches(zipCodePattern);

        return retval;
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        boolean isValid = false;

        String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
