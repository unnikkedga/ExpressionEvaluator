package ga.unnikked.expressionevaluator.lexer;

import ga.unnikked.expressionevaluator.utils.DoubleHashMap;

import java.io.*;

public class Lexer {
	private StreamTokenizer input;

	private int symbol = NONE;
	public static final int INVALID = -1;
	public static final int NONE = 0;
	public static final int PLUS = 1;
	public static final int MINUS = 2;
	public static final int MULTIPLY = 3;
	public static final int DIVIDE = 4;
	public static final int POWER = 10;
	public static final int CONSTANT = 5;
	public static final int FUNCTION = 13;
	public static final int LEFT = 7;
	public static final int RIGHT = 8;
	public static final int COMMA = 11;
	public static final int EOF = 9;
	public static final int EOL = 12;

	public final DoubleHashMap<String, Integer> mnemonics = new DoubleHashMap<String, Integer>() {{
		put("+", PLUS);
		put("-", MINUS);
		put("*", MULTIPLY);
		put("/", DIVIDE);
		put("^", POWER);
		put("(", LEFT);
		put(")", RIGHT);
		put(",", COMMA);
	}};

	public static final String CONST= "[0-9]*\\.?[0-9]*";
	public static final String FUNC = "[a-zA-z]([a-zA-z]|[0-9])*";

	public Lexer(InputStream in) {
		Reader r = new BufferedReader(new InputStreamReader(in));
		input = new StreamTokenizer(r);

		input.resetSyntax();
		input.eolIsSignificant(true);
		input.wordChars('a', 'z');
		input.wordChars('A', 'Z');
		input.wordChars('0', '9');
		input.wordChars('.', '.');
		input.whitespaceChars('\u0000', ' '); //characters from 0 to 32
		input.whitespaceChars('\n', '\t');
	}

	public String getString() {
		return input.sval;
	}

	public int nextSymbol() {
		try {
			switch (input.nextToken()) {
				case StreamTokenizer.TT_EOL:
					symbol = EOL; break;
				case StreamTokenizer.TT_EOF:
					symbol = EOF; break;
				case StreamTokenizer.TT_WORD: {
					if(input.sval.matches(CONST)) symbol = CONSTANT;
					else if(input.sval.matches(FUNC)) symbol = FUNCTION;
					else symbol = INVALID;
					break;
				}
				default:
					symbol = (mnemonics.get(Character.toString((char)input.ttype)) != null) ? mnemonics.get(Character.toString((char)input.ttype)) : NONE; break;
			}
		}catch (IOException e) {
			symbol = EOF;
		}
		return symbol;
	}

	public String stringfy(int token) {
		switch (token) {
			case CONSTANT: return getString();
			case FUNCTION: return getString();
			default: return mnemonics.getValue(token);
		}
	}

	public static void main(String[] args) {
		Lexer l = new Lexer(new ByteArrayInputStream("2+3.2*(3-3)+sin(3)-ack(4,3)".getBytes()));
		int s;
		while ( (s = l.nextSymbol()) != Lexer.EOF) if(s != EOL) System.out.println(s + " " + l.stringfy(s));
	}
}