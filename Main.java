// Provided by Tim Carpenter
// class Main {
// 	public static void main(String[] args) {
// 		// Initialize the scanner with the input file
// 		Scanner S = new Scanner(args[0]);
// 		Parser.scanner = S;
		
// 		Procedure p = new Procedure();
		
// 		p.parse();
				
// 		p.print();
// 	}
// }

class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("ERROR: Two arguments required - code file and data file");
            return;
        }
        
        // Initialize the scanner with the input code file
        Scanner S = new Scanner(args[0]);
        Parser.scanner = S;
        
        // Parse the program
        Procedure p = new Procedure();
        p.parse();
        
        // Execute the program with the data file
        Executor executor = new Executor(args[1]);
        executor.execute(p);
    }
}