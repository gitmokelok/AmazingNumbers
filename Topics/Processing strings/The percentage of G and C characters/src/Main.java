import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        double inputLinelength = inputLine.length();

        String inputLineWithoutTargetChars = inputLine.toLowerCase().replace("c","").replace("g","");
        double inputLineWithoutTargetCharsLength = inputLineWithoutTargetChars.length();
        double result = ((inputLinelength - inputLineWithoutTargetCharsLength)/inputLinelength) *100.0d;
        System.out.println(result);
        
    }
}
