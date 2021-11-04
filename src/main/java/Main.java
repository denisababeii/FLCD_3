import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void printMenu(FAProgram program) throws IOException {
        var run = true;
        while(run) {
            System.out.println("\n1. Get states\n" +
                    "2. Get alphabet\n" +
                    "3. Get transitions\n" +
                    "4. Get final states\n" +
                    "5. Get initial state\n" +
                    "6. Is it a DFA?\n" +
                    "7. Verify sequence\n" +
                    "8. Exit\n");
            Scanner scanner = new Scanner(System.in);
            var choice = scanner.next();
            switch (choice) {
                case "1":
                    program.printStates();
                    break;
                case "2":
                    program.printAlphabet();
                    break;
                case "3":
                    program.printTransitions();
                    break;
                case "4":
                    program.printFinalStates();
                    break;
                case "5":
                    program.printInitialState();
                    break;
                case "6":
                    program.printDFA();
                    break;
                case "7":
                    var sequence = scanner.next();
                    program.printValidity(sequence);
                    break;
                case "8":
                    run = false;
                    break;
                default:
                    System.out.println("Please pick an available choice!");
            }
        }
    }

    public static void main(String[] args) {
        var program = new FAProgram();
        try {
            program.getElements("src/main/resources/FA.in");
            printMenu(program);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
