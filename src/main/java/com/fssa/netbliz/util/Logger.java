package com.fssa.netbliz.util;

public class Logger {
	private Logger() {
//		private constructor
	}

	public static void info(String msg) {
		System.out.println(msg);
	}
	
	public static void info(Object msg) {
		System.out.println(msg);
	}
}
