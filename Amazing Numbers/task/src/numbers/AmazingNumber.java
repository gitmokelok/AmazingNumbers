package numbers;

import java.util.ArrayList;

public class AmazingNumber {
    private boolean isOdd = false;
    private boolean isBuzzNumber = false;
    private boolean isDuckNumber = false;
    private boolean isPalindromic = false;
    private boolean isGapful = false;
    private boolean isSpy = false;
    private boolean isSquare = false;
    private boolean isSunny = false;
    private boolean isJumping = false;
    private boolean isHappy = false;
    private boolean isSad = false;
    private ArrayList<NumberProperty> properties = new ArrayList<>();
    private long value;
    private String valueAsString;
    private char[] valueAsChars;

    public AmazingNumber(long value) {
        this.value = value;
        this.valueAsString = String.valueOf(value);
        this.valueAsChars = this.valueAsString.toCharArray();
        setOdd();
        setBuzzNumber();
        setDuckNumber();
        setPalindromic();
        setGapful();
        setSpy();
        setSquare();
        setSunny();
        setJumping();
        setHappy(value);
        setSad();
        setProperties();
    }

    public boolean isOdd() {
        return isOdd;
    }
    public void setOdd() {
        isOdd = this.value % 2 != 0 ? true : false;
    }

    public boolean isBuzzNumber() {
        return isBuzzNumber;
    }
    public void setBuzzNumber() {

        boolean isDivisibleBySeven = false;
        boolean endsWithSeven = false;

        if (this.value % 7 == 0){
            isDivisibleBySeven = true;
        }

        if (String.valueOf(this.value).endsWith("7")) {
            endsWithSeven = true;
        }

        if (isDivisibleBySeven || endsWithSeven) {
            isBuzzNumber = true;
        } else {
            isBuzzNumber = false;
        }
    }

    public boolean isDuckNumber() {
        return isDuckNumber;
    }
    public void setDuckNumber() {
        isDuckNumber = this.valueAsString.substring(1).contains("0");
    }

    public boolean isPalindromic() {
        return isPalindromic;
    }
    public void setPalindromic() {
        int length = valueAsString.length();
        if (length == 1) {
            isPalindromic = true;
            return;
        }

        if (length % 2 == 0) {
            String leftHalf = valueAsString.substring(0, length/2);
            StringBuilder rightHalf = new StringBuilder(valueAsString.substring(length/2)).reverse();
            isPalindromic = leftHalf.equals(rightHalf.toString()) ? true : false;
        } else {
            String leftHalf = valueAsString.substring(0, length/2);
            StringBuilder rightHalf = new StringBuilder(valueAsString.substring(length/2+1)).reverse();
            isPalindromic = leftHalf.equals(rightHalf.toString()) ? true : false;
        }
    }

    public boolean isGapful() {
        return isGapful;
    }
    public void setGapful() {
        if (valueAsString.length() > 2) {
            char firstNumber = valueAsString.charAt(0);
            char lastNumber = valueAsString.charAt(valueAsString.length()-1);

            isGapful = value % Long.valueOf(String.format("%c%c", firstNumber, lastNumber)) == 0 ? true : false;
        } else {
            isGapful = false;
        }
    }

    public boolean isSpy() {
        return isSpy;
    }
    public void setSpy() {
        long sum = 0;
        long allNumbersProduct = 1;
        for (int i = 0; i < valueAsString.length(); i++) {
            long currentNumber = Long.parseLong(String.valueOf(valueAsString.charAt(i)));
            sum += currentNumber;
            allNumbersProduct *= currentNumber;
        }
        isSpy = sum == allNumbersProduct ? true : false;
    }

    public boolean isSquare() {
        return isSquare;
    }
    public void setSquare() {
        double square = Math.sqrt(value);
        isSquare = Math.round(square) * Math.round(square) == value  ? true : false;;
    }
    public void printNumberInfoInline() {
        StringBuilder stringBuilder = new StringBuilder(String.format("%d is", this.value));
        if (this.isOdd) {
            stringBuilder.append(" odd");
        } else {
            stringBuilder.append(" even");
        }

        if (this.isBuzzNumber) {
            stringBuilder.append(", buzz");
        }

        if (this.isDuckNumber) {
            stringBuilder.append(", duck");
        }

        if (this.isGapful) {
            stringBuilder.append(", gapful");
        }

        if (this.isPalindromic) {
            stringBuilder.append(", palindromic");
        }
        if (this.isSpy) {
            stringBuilder.append(", spy");
        }
        if (this.isSquare) {
            stringBuilder.append(", square");
        }
        if (this.isSunny) {
            stringBuilder.append(", sunny");
        }
        if (this.isJumping) {
            stringBuilder.append(", jumping");
        }
        if (this.isHappy) {
            stringBuilder.append(", happy");
        }
        if (this.isSad) {
            stringBuilder.append(", sad");
        }

        System.out.println(stringBuilder);
    }
    public void printNumberCard() {
        System.out.println(String.format("""
                Properties of %d
                        buzz: %b
                        duck: %b
                 palindromic: %b
                      gapful: %b
                        even: %b
                         odd: %b
                         spy: %b
                      square: %b
                       sunny: %b
                     jumping: %b
                       happy: %b
                         sad: %b""", value, isBuzzNumber, isDuckNumber, isPalindromic, isGapful, !isOdd, isOdd, isSpy, isSquare, isSunny, isJumping, isHappy, isSad));
    }

    public boolean isSunny() {
        return isSunny;
    }
    public void setSunny() {
        int targetValue = (int)value + 1;
        int squareOfTargetValue = (int) Math.sqrt(targetValue);

        isSunny = targetValue == squareOfTargetValue*squareOfTargetValue ? true : false;
    }

    public boolean isJumping() {
        return isJumping;
    }
    public void setJumping() {
        if (valueAsChars.length == 1) {
            isJumping = true;
            return;
        }
        for (int i = 0; i <= valueAsChars.length-2; i++) {
            if (Math.abs((int)valueAsChars[i] - (int)valueAsChars[i+1]) == 1) {
                continue;
            } else {
                isJumping = false;
                return;
            }
        }
        isJumping = true;
    }

    public boolean isHappy() {
        return isHappy;
    }

    ///n number theory, a happy number is a number that reaches 1 after
    // a sequence during which the number is replaced by the sum of each digit squares.
    // For example, 13 is a happy number, as 12 + 32 = 10 which leads to 12 + 02 = 1.
    // On the other hand, 4 is not a happy number because the sequence starts with 42 = 16, 12 + 62 = 37,
    // and finally reaches 22 + 02 = 4. This is the number that started the sequence,
    // so the process goes on in an infinite cycle. A number that is not happy is called Sad (or Unhappy).
    public void setHappy(long number) {
        long result = 0;
        char[] numberAsChars = String.valueOf(number).toCharArray();
        for (int i = 0; i < numberAsChars.length; i++) {
            result += Math.pow(Double.parseDouble(String.valueOf(numberAsChars[i])), 2.0);
        }

        if (result == 1) {
            isHappy = true;
        } else if (result == 4) {
            isHappy = false;
        } else {
            setHappy(result);
        }
    }

    public boolean isSad() {
        return isSad;
    }
    public void setSad() {
        isSad = !isHappy; //the opposite of isHappy
    }
    public ArrayList<NumberProperty> getProperties() {
        return properties;
    }
    public void setProperties() {
        for (NumberProperty existingProperty : NumberProperty.values()) {
            switch (existingProperty) {
                case EVEN:
                    if (!isOdd) {
                        properties.add(existingProperty);
                    }
                    break;
                case ODD:
                    if (isOdd) {
                        properties.add(existingProperty);
                    }
                    break;
                case BUZZ:
                    if (isBuzzNumber) {
                        properties.add(existingProperty);
                    }
                    break;
                case DUCK:
                    if (isDuckNumber) {
                        properties.add(existingProperty);
                    }
                    break;
                case PALINDROMIC:
                    if (isPalindromic) {
                        properties.add(existingProperty);
                    }
                    break;
                case GAPFUL:
                    if (isGapful) {
                        properties.add(existingProperty);
                    }
                    break;
                case SPY:
                    if (isSpy) {
                        properties.add(existingProperty);
                    }
                    break;
                case SQUARE:
                    if (isSquare) {
                        properties.add(existingProperty);
                    }
                    break;
                case SUNNY:
                    if (isSunny) {
                        properties.add(existingProperty);
                    }
                    break;
                case JUMPING:
                    if (isJumping) {
                        properties.add(existingProperty);
                    }
                    break;
                case HAPPY:
                    if (isHappy) {
                        properties.add(existingProperty);
                    }
                    break;
                case SAD:
                    if (isSad) {
                        properties.add(existingProperty);
                    }
                    break;
            }
        }
    }
}
