// Provided by Tim Carpenter
class Factor {
	Id id;
	String key;
	int constant;
	Expr expr;
	private int value;
	
	void parse() {
		if (Parser.scanner.currentToken() == Core.ID) {
			id = new Id();
			id.parse();
			if (Parser.scanner.currentToken() == Core.LSQUARE) {
				Parser.scanner.nextToken();
				key = Parser.scanner.getString();
				Parser.scanner.nextToken();
				Parser.expectedToken(Core.RSQUARE);
				Parser.scanner.nextToken();
			}
		} else if (Parser.scanner.currentToken() == Core.CONST) {
			constant = Parser.scanner.getConst();
			Parser.scanner.nextToken();
		} else if (Parser.scanner.currentToken() == Core.LPAREN) {
			Parser.scanner.nextToken();
			expr = new Expr();
			expr.parse();
			Parser.expectedToken(Core.RPAREN);
			Parser.scanner.nextToken();
		} else {
			System.out.println("ERROR: Expected ID, CONST, LPAREN, recieved " + Parser.scanner.currentToken());
			System.exit(0);
		}
	}
	
	void print() {
		if (id != null) {
			id.print();
			if (key != null) {
				System.out.print("['" + key + "']");
			}
		} else if (expr != null) {
			System.out.print("(");
			expr.print();
			System.out.print(")");
		} else {
			System.out.print(constant);
		}
	}

	int evaluate(Memory memory) {
		if (id != null) {
			if (key != null) {
				// Handle id[string] case
				return memory.getObjectValue(id.getName(), key);
			} else {
				// Handle simple id case
				return memory.getValue(id.getName());
			}
		} else if (expr != null) {
			// Handle (expr) case
			return expr.evaluate(memory);
		} else {
			// Handle constant case
			return constant;
		}
	}
	
}