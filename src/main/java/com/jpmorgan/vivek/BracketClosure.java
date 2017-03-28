package com.jpmorgan.vivek;

import java.util.Stack;

public class BracketClosure {

	public static boolean check(String str) {
		Stack<String> s = new Stack<String>();
		String poped = null;
		for (int i = 0; i < str.length(); i++) {
			if (i == 0) {
				s.push("" + str.charAt(i));
			} else {
				poped = s.pop();
				if ((poped.equalsIgnoreCase("[") && ("" + str.charAt(i)).equalsIgnoreCase("]"))
						|| (poped.equalsIgnoreCase("(") && ("" + str.charAt(i)).equalsIgnoreCase(")"))) {
				} else{
					s.push(poped);
					s.push("" + str.charAt(i));
				}					
			}
		}
		if (s.isEmpty())
			return true;
		return false;
	}

	public static void main(String[] args) {
		System.out.println(BracketClosure.check("[(()[[])]")); // False
		System.out.println(BracketClosure.check("[(()[])]")); // True
	}
}
