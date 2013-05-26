package by.bsuir.iit.abramov.ppvis.calculator.util;


public class Util {
	public static long factorial(final long n) {

		long ret = 1;
		for (long i = 1; i <= n; ++i) {
			ret *= i;
		}
		return ret;
	}
}
