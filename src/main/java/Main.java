public class Main {
    public static void main(String[] args) {
        var program = new FAProgram();
        try {
            program.run("src/main/resources/FA.in");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
