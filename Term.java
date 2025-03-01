// Provided by Tim Carpenter
class Term {
	Factor factor;
	Term term;
	int option;
	
	void parse() {
		factor  = new Factor();
		factor.parse();
		if (Parser.scanner.currentToken() == Core.MULTIPLY) {
			option = 1;
		} else if (Parser.scanner.currentToken() == Core.DIVIDE) {
			option = 2;
		}
		if (option != 0) {
			Parser.scanner.nextToken();
			term = new Term();
			term.parse();
		}						
	}
	
	void print() {
		factor.print();
		if (option == 1) {
			System.out.print("*");
			term.print();
		} else if (option == 2) {
			System.out.print("/");
			term.print();
		}
	}

	int evaluate(Memory memory) {
		int value = factor.evaluate(memory);
		if (option == 1) {
			// Multiplication
			value = value * term.evaluate(memory);
		} else if (option == 2) {
			// Division
			int divisor = term.evaluate(memory);
			if (divisor == 0) {
				throw new RuntimeException("ERROR: Division by zero");
			}
			value = value / divisor;
		}
		return value;
	}
	
}