import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Class to execute parsed Core programs
class Executor {
    private Memory memory; // Instance of Memory to store variables
    private static Scanner dataScanner; // Scanner to read input from a .data file

    public Executor(String dataFile) {
        memory = new Memory();
        try {
            File file = new File(dataFile);
            dataScanner = new Scanner(file); 
        } catch (FileNotFoundException e) {
            throw new RuntimeException("ERROR: Data file not found");
        }
    }

    public static int readInput() {  
        if (!dataScanner.hasNextInt()) {
            throw new RuntimeException("ERROR: Not enough input data");
        }
        return dataScanner.nextInt();
    }

    // Execute a procedure by entering its scope and executing its statements
    public void execute(Procedure procedure) {
        memory.enterScope();
        procedure.execute(memory);
        memory.exitScope();
    }

    // Evaluate an expression and return its integer value
    public int executeExpression(Expr expr) {
        return expr.evaluate(memory);
    }

    // Assign a value to a variable
    public void assign(String id, int value) {
        memory.assign(id, value);
    }

    // Print an integer value to standard output
    public void print(int value) {
        System.out.println(value);
    }

    // Read an integer from the .data file and assign it to a variable
    public void read(String id) {
        if (!dataScanner.hasNextInt()) {
            throw new RuntimeException("ERROR: Not enough input data");
        }
        int value = dataScanner.nextInt();
        memory.assign(id, value);
    }
}
