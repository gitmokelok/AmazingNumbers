package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.print("""
                                Welcome to Amazing Numbers!
                                            
                                Supported requests:
                                - enter a natural number to know its properties;
                                - enter two natural numbers to obtain the properties of the list:
                                  * the first parameter represents a starting number;
                                  * the second parameter shows how many consecutive numbers are to be processed;
                                - two natural numbers and properties to search for;
                                - a property preceded by minus must not be present in numbers;
                                - separate the parameters with one space;
                                - enter 0 to exit.
                                """);

        long userInputNumber = -1;
        while (userInputNumber != 0) {
            System.out.print("""
                                
                                Enter a request:\040""");

            String[] userInputNumbers = scanner.nextLine().split(" ");
            userInputNumber = Long.parseLong(userInputNumbers[0]);
            if (userInputNumbers.length == 1) {
                if (userInputNumber == 0) {
                    System.out.print("""
                                                    
                            Goodbye!""");
                    continue;
                }
                if (userInputNumber < 0) {
                    System.out.print("""
                                                                
                            The first parameter should be a natural number or zero.
                            """);
                    continue;
                }

                AmazingNumber amazingNumber = new AmazingNumber(userInputNumber);
                amazingNumber.printNumberCard();
            } else if (userInputNumbers.length == 2) {

                int secondNumber = Integer.parseInt(userInputNumbers[1]);
                if (secondNumber < 0) {
                    System.out.print("""
                                                                
                            The second parameter should be a natural number.
                            """);
                    continue;
                }

                for (int i = 0; i < secondNumber; i++) {
                    AmazingNumber amazingNumber = new AmazingNumber(userInputNumber + i);
                    amazingNumber.printNumberInfoInline();
                }
            } else if (userInputNumbers.length > 2) {

                int numberOfOutputs = Integer.parseInt(userInputNumbers[1]);
                ArrayList<NumberProperty> propertiesToBeIncluded = new ArrayList<>();
                ArrayList<NumberProperty> propertiesToBeExcluded = new ArrayList<>();
                ArrayList<String> invalidProperties = new ArrayList<>();


                //Check if all properties are valid
                for (int i = 2; i < userInputNumbers.length; i++) {
                    String targetProperty = userInputNumbers[i].toUpperCase();
                    if (!checkIfNumberPropertyIsSupported(targetProperty.replace("-",""))) {
                        invalidProperties.add(targetProperty);
                    } else {
                        NumberProperty prop = NumberProperty.valueOf(targetProperty.replace("-",""));
                        if (targetProperty.startsWith("-")) {
                            propertiesToBeExcluded.add(prop);
                        } else {
                            propertiesToBeIncluded.add(prop);
                        }
                    }
                }

                if (invalidProperties.size() == 1) {
                    System.out.printf("""
                                                                    
                                The property [%s] is wrong.
                                Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]
                                """, invalidProperties.get(0));

                } else if (invalidProperties.size() > 1) {
                    StringBuilder sb = new StringBuilder();
                    for (String prop : invalidProperties) {
                        sb.append(prop + " ,");
                    }

                    System.out.printf("""

                                The properties [%s] are wrong.
                                Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]
                                """, sb.substring(0, sb.length()-2));

                } else if (checkIfRequestHasMutuallyExclusiveProps(propertiesToBeIncluded) != null) {
                    String result = checkIfRequestHasMutuallyExclusiveProps(propertiesToBeIncluded);
                    System.out.printf("""

                                The request contains mutually exclusive properties: [%s]
                                There are no numbers with these properties.
                                """, result);
                } else if (checkIfRequestHasMutuallyExclusiveProps(propertiesToBeExcluded) != null) {
                    String result = checkIfRequestHasMutuallyExclusiveProps(propertiesToBeExcluded);
                    System.out.printf("""
    
                                    The request contains mutually exclusive properties: [-%s, -%s]
                                    There are no numbers with these properties.
                                    """, result.split(",")[0].trim(), result.split(",")[1].trim() );
                } else if (checkIfRequestHasMutuallyExclusiveProps(propertiesToBeIncluded, propertiesToBeExcluded) != null) {
                    String result = checkIfRequestHasMutuallyExclusiveProps(propertiesToBeIncluded, propertiesToBeExcluded);
                    System.out.printf("""
    
                                    The request contains mutually exclusive properties: [%s]
                                    There are no numbers with these properties.
                                    """, result);
                } else {
                        for (int i = 0; i < Integer.MAX_VALUE; i++) {
                            if (numberOfOutputs <= 0) {
                                break;
                            }
                            AmazingNumber amazingNumber = new AmazingNumber(userInputNumber + i);
                            if ((amazingNumber.getProperties().containsAll(propertiesToBeIncluded) &&
                                    propertiesToBeExcluded.size() == 0) ||

                                    (amazingNumber.getProperties().containsAll(propertiesToBeIncluded) &&
                                            propertiesToBeExcluded.size() > 0 &&
                                            !amazingNumber.getProperties().containsAll(propertiesToBeExcluded))) {
                                amazingNumber.printNumberInfoInline();
                                numberOfOutputs--;
                            }
                        }
                }
            }
        }
    }

    public static boolean checkIfNumberPropertyIsSupported(String property) {

        for (NumberProperty np : NumberProperty.values()) {
            if (np.name().equals(property.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public static String checkIfRequestHasMutuallyExclusiveProps(ArrayList<NumberProperty> props) {
        if (props.contains(NumberProperty.ODD) && props.contains(NumberProperty.EVEN)) {
            return String.format("%s, %s", NumberProperty.ODD, NumberProperty.EVEN);
        } else if (props.contains(NumberProperty.DUCK) && props.contains(NumberProperty.SPY)) {
            return String.format("%s, %s", NumberProperty.DUCK, NumberProperty.SPY);
        } else if (props.contains(NumberProperty.SUNNY) && props.contains(NumberProperty.SQUARE)) {
            return String.format("%s, %s", NumberProperty.SUNNY, NumberProperty.SQUARE);
        } else if (props.contains(NumberProperty.HAPPY) && props.contains(NumberProperty.SAD)) {
            return String.format("%s, %s", NumberProperty.HAPPY, NumberProperty.SAD);
        } else {
            return null;
        }
    }

    public static String checkIfRequestHasMutuallyExclusiveProps(ArrayList<NumberProperty> propertiesToBeIncluded, ArrayList<NumberProperty> propertiesToBeExcluded) {
        for (NumberProperty prop : propertiesToBeIncluded) {
            if (propertiesToBeExcluded.contains(prop)) {
                return String.format("%s, -%s", prop.name(), prop.name());
            }
        }
        return null;
    }
}

