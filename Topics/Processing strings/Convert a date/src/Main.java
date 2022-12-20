import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String date = scanner.nextLine();
        String[] dateDetails = date.split("-");

        System.out.printf("%n%s/%s/%s", dateDetails[1], dateDetails[2], dateDetails[0]);
        
    }
}
