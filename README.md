**Project 3 - CORE Interpreter**

Rita Brokhman

Files Submitted:
1. Main.java: Provided by Tim Carpenter. Starts the program, sets up the scanner, runs parsing, and prints the results.
2. Scanner.java: Provided by Tim Carpenter. Breaks the input file into tokens for the parser.
3. Core.java: Provided by Tim Carpenter. Defines all the possible token types.
4. Parser.java: Provided by Tim Carpenter. Parses the tokens and checks for errors.
5. Procedure.java: Provided by Tim Carpenter. Parses a procedure to ensure it follows the correct structure.
6. DeclSeq.java: Provided by Tim Carpenter. Handles multiple variable declarations.
7. Decl.java: Provided by Tim Carpenter. Handles a single variable declaration.
8. DeclObj.java: Provided by Tim Carpenter. Handles object declarations.
9. DeclInteger.java: Provided by Tim Carpenter. Handles integer declarations.
10. StmtSeq.java: Provided by Tim Carpenter. Parses multiple statements.
11. Stmt.java: Provided by Tim Carpenter. Figures out what kind of statement it is and processes it.
12. Assign.java: Provided by Tim Carpenter. Handles assignments (e.g., x = 5;).
13. Print.java: Provided by Tim Carpenter. Handles print statements.
14. Read.java: Provided by Tim Carpenter. Handles read statements.
15. If.java: Provided by Tim Carpenter. Handles if and if-else statements.
16. Loop.java: Provided by Tim Carpenter. Handles for-loops.
17. Cond.java: Provided by Tim Carpenter. Handles conditions used in if and loop statements.
18. Cmpr.java: Provided by Tim Carpenter. Handles comparison expressions (e.g., x < y).
19. Expr.java: Provided by Tim Carpenter. Handles math expressions.
20. Term.java: Provided by Tim Carpenter. Handles multiplication and division.
21. Factor.java: Provided by Tim Carpenter. Handles individual values in expressions.
22. Memory.java: Manages variable storage, scoping, object memory, and assignment operations.
23. Executor.java: Executes parsed statements and expressions, reads input, and manages memory during execution.
24. Id.java: Provided by Tim Carpenter. Represents an identifier (variable name) and provides functionality to retrieve its value.
25. tester.sh: Provided by Tim Carpenter. Runs multiple test cases automatically.
26. Correct Folder: Provided by Tim Carpenter. Contains valid Core programs and their expected outputs.
27. Error Folder: Provided by Tim Carpenter. Contains Core programs designed to test error handling.

Special Features:
1. The interpreter supports nested scopes and proper variable shadowing.
2. Object variables are stored separately, allowing key-value storage.
3. Scope management makes sure variables declared inside a block do not leak into other scopes.

Design Overview:
1. Scanning: The input program is tokenized by Scanner.java.
2. Parsing: Parser.java processes the tokens and builds an internal representation.
3. Execution: Executor.java Memory.java manage execution.

Variable Handling & Scoping:
Memory.java handles variable storage usinga global hashmap for global variables, a stack of hashmaps for local scopes, and a separate hashmap for object variables, allowing object-like storage. When entering a new scope, a new hashmap is pushed onto the stack. When exiting a scope, the current hashmap is popped. Object variables are stored in a separate hashmap, allowing them to exist and function outside normal scope rules.

Testing:
The interpreter was tested using tester.sh, which runs all test cases in Correct/ and Error/.

Known Bugs:
Undefined Variable Errors: Some test cases (9.code, 22.code, 25.code, 27.code, 30.code) result in an undefined variable runtime error. 

For 02.code, nothing/no message is printed when testing with ./tester.sh.
