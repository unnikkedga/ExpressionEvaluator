package ga.unnikked.expressionevaluator.utils;

import ga.unnikked.expressionevaluator.lexer.Lexer;

public final class Util {
	public static boolean isNumber(String str) {
        return str.matches(Lexer.CONST);
    }
}
	