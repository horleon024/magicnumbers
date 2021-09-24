package magicnumbers;

import java.util.*;

public class Main extends Numbers{

	public static void printRequests() {
		System.out.println("Supported requests:");
		System.out.println("- enter a natural number to know its properties;");
		System.out.println("- enter two natural numbers to obtain the properties of the list:");
		System.out.println("  * the first parameter represents a starting number;");
		System.out.println("  * the second parameters show how many consecutive numbers are to be processed;");
		System.out.println("- two natural numbers and properties to search for;");
		System.out.println("- a property preceded by minus must not be present in numbers;");
		System.out.println("- separate the parameters with one space;");
		System.out.println("- enter 0 to exit.\n");
	}

	public static boolean dealWithInput(String line) {
		long repeat;
		long number;
		String[] tmp;
		if (line.isEmpty()) {
			printRequests();
		} else if (line.indexOf(" ") > 0) {
			tmp = line.split(" ");
			number = tryParseLong(tmp[0]);
			repeat = tryParseLong(tmp[1]);
			if (number < 0) {
				System.out.println("\nThe first parameter should be a natural number or zero.\n");		
			} else if (number == 0){
				return false;
			} else if (repeat <= 0) {
				System.out.println("\nThe second parameter should be a natural number.\n");
			} else if (tmp.length == 2) {
				printSingleLoop(number, repeat);
			} else {
				printMultiSpecificLoop(number, repeat, Arrays.copyOfRange(tmp, 2, tmp.length));
			}
		} else {
			number = tryParseLong(line);
			if (number < 0) {
				System.out.println("\nThe first parameter should be a natural number or zero.\n");
			}
			else if (number == 0) {
				return false;
			} else {
				printMultiLineProperties(number);
			}				
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean continueLoop = true;
		String line = "";
		System.out.println("Welcome to Amazing Numbers!\n");
		printRequests();
		while (continueLoop) {
			System.out.print("Enter a request: ");
			line = scanner.nextLine();
			continueLoop = dealWithInput(line);
		}
		System.out.println("\nGoodbye!");
		scanner.close();
	}
}

class Numbers {
	static boolean isBuzz(long number) {
		return (number % 7 == 0 || number % 10 == 7);
	}

	static boolean isDuck(long number) {
		return String.valueOf(number).substring(1).contains("0");
	}

	static boolean isEven(long number) {
		return number % 2 == 0;
	}

	static boolean isOdd(long number) {
		return number % 2 == 1;
	}

	static boolean isPalindromic(long number) {
		return String.valueOf(number).equals(new StringBuilder(String.valueOf(number)).reverse().toString());
	}

	static boolean isGapful(long number) {
		if (number < 100) {
			return false;
		}
		String[] array = String.valueOf(number).split("");
		int div = Integer.parseInt(array[0] + array[array.length - 1]);
		return number % div == 0;
	}

	static boolean isSpy(long number) {
		char[] array = String.valueOf(number).toCharArray();
		long sum = 0;
		long mult = 1;
		for (char ch : array) {
			sum += ch - '0';
			mult *= ch - '0';
		}
		return mult == sum;
	}

	static boolean isSquare(long number) {
		double sqrt = Math.sqrt((double)number);
		return (sqrt - Math.floor(sqrt) == 0);
	}

	static boolean isSunny(long number) {
		return isSquare(number + 1);
	}

	static boolean isJumping(long number) {
		char[] tmp = String.valueOf(number).toCharArray();
		for (int i = 1; i < tmp.length; i++) {
			if (tmp[i - 1] + 1 != tmp[i] && tmp[i - 1] != tmp[i] + 1) {
				return false;
			}
		}
		return true;
	}

	static boolean isHappy(long number) {
		long tmp = number;
		long next = 0;
		while (true) {
			while (tmp != 0) {
				next += Math.pow(tmp % 10, 2);
				tmp /= 10;
			}
			if (next == 1) {
				return true;
			} else if (next == number || next == 4) {
				return false;
			}
			tmp = next;
			next = 0;
		}
	}

	static String formatNumber(long number) {
		if (number < 1000) {
			return String.valueOf(number);
		} else {
			String notFormated = String.valueOf(number);
			String formated = "";
			for (int i = 0;;) {
				if (i == 0) {
					formated += notFormated.substring(i, notFormated.length() % 3 == 0 ? 3 : notFormated.length() % 3)
							+ ",";
					i += notFormated.length() % 3 == 0 ? 3 : notFormated.length() % 3;
				} else if (i == notFormated.length() - 3) {
					formated += notFormated.substring(i);
					break;
				} else {
					formated += notFormated.substring(i, i + 3) + ",";
					i += 3;
				}
			}
			return formated;
		}
	}

	static void printMultiLineProperties(long number) {
		System.out.println("\nProperties of " + formatNumber(number));
		System.out.println("        buzz: " + isBuzz(number));
		System.out.println("        duck: " + isDuck(number));
		System.out.println(" palindromic: " + isPalindromic(number));
		System.out.println("      gapful: " + isGapful(number));
		System.out.println("         spy: " + isSpy(number));
		System.out.println("      square: " + isSquare(number));
		System.out.println("       sunny: " + isSunny(number));
		System.out.println("     jumping: " + isJumping(number));
		System.out.println("       happy: " + isHappy(number));
		System.out.println("         sad: " + !isHappy(number));
		System.out.println("        even: " + isEven(number));
		System.out.println("         odd: " + isOdd(number));
		System.out.println();
	}

	static void printSingleLineProperties(long number) {
		StringBuilder res = new StringBuilder(formatNumber(number) + " is ");
		if (isBuzz(number)) {
			res.append("buzz, ");
		}
		if (isDuck(number)) {
			res.append("duck, ");
		}
		if (isPalindromic(number)) {
			res.append("palindromic, ");
		}
		if (isGapful(number)) {
			res.append("gapful, ");
		}
		if (isSpy(number)) {
			res.append("spy, ");
		}
		if (isSquare(number)) {
			res.append("square, ");
		}
		if (isSunny(number)) {
			res.append("sunny, ");
		}
		if (isJumping(number)) {
			res.append("jumping, ");
		}
		if (isHappy(number)) {
			res.append("happy, ");
		} else {
			res.append("sad, ");
		}
		if (isEven(number)) {
			res.append("even");
		} else {
			res.append("odd");
		}
		System.out.println("\t\t" + res);
	}

	static void printSingleLoop(long number, long repeat) {
		System.out.println();
		while (repeat > 0) {
			printSingleLineProperties(number);
			number++;
			repeat--;
		}
		System.out.println();
	}

	static boolean isValidFunction(String func) {
		if (func.equalsIgnoreCase("spy") || func.equalsIgnoreCase("odd") || func.equalsIgnoreCase("even")
			|| func.equalsIgnoreCase("duck") || func.equalsIgnoreCase("palindromic") || func.equalsIgnoreCase("gapful")
			|| func.equalsIgnoreCase("buzz") || func.equalsIgnoreCase("sunny") || func.equalsIgnoreCase("square")
			|| func.equalsIgnoreCase("jumping") || func.equalsIgnoreCase("happy") || func.equalsIgnoreCase("sad")) {
				return true;
			}
		return false;
	}

	static boolean callSpecificFunction(long number, String func) {
		switch (func) {
			case "odd" :
				return isOdd(number);
			case "even" :
				return isEven(number);
			case "buzz" :
				return isBuzz(number);
			case "palindromic" :
				return isPalindromic(number);
			case "duck" :
				return isDuck(number);
			case "gapful" :
				return isGapful(number);
			case "spy" :
				return isSpy(number);
			case "sunny" :
				return isSunny(number);
			case "square" :
				return isSquare(number);
			case "jumping" :
				return isJumping(number);
			case "happy" :
				return isHappy(number);
			case "sad" :
				return !isHappy(number);
			default :
				return false;
		}
	}

	static boolean isBothInArray(String func1, String func2, String[] funcs) {
		for (int i = 0; i < funcs.length; i++) {
			if (funcs[i].equalsIgnoreCase(func1)) {
				for (int j = 0; j < funcs.length; j++) {
					if (funcs[j].equalsIgnoreCase(func2)) {
						return true;
					}
				}
				return false;
			}
		}
		return false;
	}

	static int isMutuallyExclusive(String[] funcs) {
		if (isBothInArray("sunny", "square", funcs)) {
			return 1;
		} else if (isBothInArray("even", "odd", funcs)) {
			return 2;
		} else if (isBothInArray("spy", "duck", funcs)) {
			return 3;
		} else if (isBothInArray("sad", "happy", funcs)) {
			return 4;
		} else if (isBothInArray("-sunny", "-square", funcs)) {
			return 5;
		} else if (isBothInArray("-even", "-odd", funcs)) {
			return 6;
		} else if (isBothInArray("-spy", "-duck", funcs)) {
			return 7;
		} else if (isBothInArray("-sad", "-happy", funcs)) {
			return 8;
		}
		return 0;
	}

	static boolean areValidFunctionsInArray(String[] funcs) {
		for (String func : funcs) {
			if (func.charAt(0) != '-' && !isValidFunction(func)) {
				return false;
			}
		}
		for (String func : funcs) {
			if (func.length() == 1 || (func.charAt(0) == '-' && !isValidFunction(func.substring(1)))) {
				return false;
			}
		}
		return true;
	}

	static boolean callSpecificFunctionsInArray(long number, String[] funcs) {
		for (String func : funcs) {
			if (func.charAt(0) == '-' && callSpecificFunction(number, func.substring(1).toLowerCase())) {
				return false;
			}
			else if (func.charAt(0) != '-' && !callSpecificFunction(number, func.toLowerCase())) {
				return false;
			}
		}
		return true;
	}

	static String listInvalidFunctionsInArray(String[] funcs, int nbOfInvalids) {
		StringBuilder sb = new StringBuilder();
		int curr = 1;
		for (String func : funcs) {
			if (!isValidFunction(func) && !isValidFunction(func.substring(1))) {
				sb.append(func.toUpperCase());
				if (curr < nbOfInvalids) {
					sb.append(", ");
					curr++;
				}
			}
		}
		return sb.toString();
	}

	static void printInvalidFunctionsInArray(String[] funcs) {
		int nbOfInvalids = 0;
		for (String func : funcs) {
			if (!isValidFunction(func) && !isValidFunction(func.substring(1))) {
				nbOfInvalids++;
			}
		}
		if (nbOfInvalids == 1) {
			System.out.println("\nThe property of [" + listInvalidFunctionsInArray(funcs, nbOfInvalids) + "] is wrong");
		} else {
			System.out.println("\nThe properties [" + listInvalidFunctionsInArray(funcs, nbOfInvalids) + "] are wrong");
		}
	}

	static String checkOpposityPairsInArray(String[] funcs) {
		for (String func : funcs) {
			if (func.charAt(0) != '-') {
				for (String func2 : funcs) {
					if (func2.charAt(0) == '-' && func2.length() > 1 && func2.substring(1).equalsIgnoreCase(func)) {
						return func;
					}
				}
			}
		}
		return null;
	}

	static void printMultiSpecificLoop(long number, long repeat, String[] funcs) {
		int exclusive = isMutuallyExclusive(funcs);
		String opposite = checkOpposityPairsInArray(funcs);
		if (exclusive > 0) {
			switch (exclusive) {
				case 1 :
					System.out.println("\nThe request contains mutually exclusive properties: [SQUARE, SUNNY]");
					break;
				case 2 :
					System.out.println("\nThe request contains mutually exclusive properties: [ODD, EVEN]");
					break;
				case 3 :
					System.out.println("\nThe request contains mutually exclusive properties: [SPY, DUCK]");
					break;
				case 4 :
					System.out.println("\nThe request contains mutually exclusive properties: [HAPPY, SAD]");
					break;
				case 5 :
					System.out.println("\nThe request contains mutually exclusive properties: [-SQUARE, -SUNNY]");
					break;
				case 6 :
					System.out.println("\nThe request contains mutually exclusive properties: [-ODD, -EVEN]");
					break;
				case 7 :
					System.out.println("\nThe request contains mutually exclusive properties: [-SPY, -DUCK]");
					break;
				case 8 :
					System.out.println("\nThe request contains mutually exclusive properties: [-HAPPY, -SAD]");
					break;
			}
			System.out.println("There are no numbers with these properties.\n");
		} else if (opposite != null) {
			System.out.println("\nThe request contains mutually exclusive properties: [" + opposite.toUpperCase() + ", -" + opposite.toUpperCase() + "]");
			System.out.println("There are no numbers with these properties.\n");
		} else if (areValidFunctionsInArray(funcs)) {
			System.out.println();
			while (repeat > 0) {
				if (callSpecificFunctionsInArray(number, funcs)) {
					printSingleLineProperties(number);
					repeat--;
				}
				number++;
			}
			System.out.println();
		} else {
			printInvalidFunctionsInArray(funcs);
			System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]\n");
		}
	}

	static long tryParseLong(String str) {
		try {
			return Long.parseLong(str);
		} catch (NumberFormatException e) {
			return -1;
		}
	}
}