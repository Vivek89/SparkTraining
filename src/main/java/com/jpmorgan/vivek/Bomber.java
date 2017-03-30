package com.jpmorgan.vivek;

import java.util.Stack;

public class Bomber {

	public static String check(String str) {
		Stack<String> s = new Stack<String>();
		String poped = null;
		String bomb = null;
		for (int i = 0; i < str.length(); i++) {
			if (s.isEmpty()) {
				s.push("" + str.charAt(i));
			} else {
				poped = !s.isEmpty() ? s.pop() : bomb;
				if (poped.equalsIgnoreCase("" + str.charAt(i))) {
					bomb = poped;
				} else if (bomb.equalsIgnoreCase(poped)) {
					bomb = poped;
				} else {
					s.push(poped);
					s.push("" + str.charAt(i));
				}
			}
		}
		if (s.isEmpty())
			return "All Characters Bombed!";
		return s.toString();
	}

	public static void main(String[] args) {
		System.out.println(Bomber.check("AAA"));
	}
}
