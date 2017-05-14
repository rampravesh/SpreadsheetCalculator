import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Spreadsheet {

	int row, column;
	String[][] input;
	String[][] output;
	Map<String, String> calculatedValue = new HashMap<String, String>();
	StringBuilder expressionBuilder = new StringBuilder();

	public static void main(String[] args) {
		Spreadsheet spreadSheet = new Spreadsheet();
		spreadSheet.readInput();
		spreadSheet.calculateExpresion();
		spreadSheet.printOutput();
	}

	private void readInput() {
		try {
			
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String widthHeight = br.readLine();
			
			ArrayList<String> inputArray = new ArrayList<String>(Arrays.asList(widthHeight.split(" ")));
			column = Integer.parseInt(inputArray.get(0));
			row = Integer.parseInt(inputArray.get(1));
			input = new String[row][column];
			output = new String[row][column];

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					input[i][j] = br.readLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception while reading input");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("Exception while integer parsing");
			System.out.println("Please enter numeric value");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception while Evaluating expresion");
		}

	}

	private void calculateExpresion() {
		try {
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					expressionBuilder = new StringBuilder();
					String expressionInput = getValueRcurrisive(input[i][j]);
					ExpressionCalculator expCalculator = new ExpressionCalculator(expressionInput);
					output[i][j] = String.format("%.5f", expCalculator.calculate());
				}
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("Exception while integer parsing");
			System.out.println("Please enter numeric value");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception while Evaluating expresion");
		}
	}

	private String getValueRcurrisive(String inputValue) {
		// split string based on spaces;
		ArrayList<String> inputArray = new ArrayList<String>(Arrays.asList(inputValue.split(" ")));
		int i = 0;
		while (inputArray.size() != i) {
			int intValue = 0;
			String rowColumn = inputArray.get(i);
			// check if it has direct value
			if (inputArray.size() == 1) {
				if (checkValueAlreadyCalculated(rowColumn))
					intValue = Integer.parseInt(calculatedValue.get(rowColumn));

			}
			// check it has multiple expression or not
			if (intValue == 0) {
				if (checkValueAlreadyCalculated(rowColumn)) {
					intValue = Integer.parseInt(calculatedValue.get(rowColumn));
					expressionBuilder.append(Integer.toString(intValue)).append(" ");
				} else if (isAlfaNumericOnly(rowColumn)) {
					char r = rowColumn.charAt(0);
					int asciiValue = (int) r; // Converte char to ascii int
												// value A-Z
					int rowIndex = (int) (asciiValue - 65);
					int columnIndex = Character.getNumericValue(rowColumn.charAt(1));
					getValueRcurrisive(input[rowIndex][columnIndex - 1]);
				} else {
					expressionBuilder.append(rowColumn).append(" ");
				}

			} else {
				expressionBuilder.append(Integer.toString(intValue)).append(" ");
			}
			i++;
		}
		return expressionBuilder.toString();
	}

	private boolean checkValueAlreadyCalculated(String rowColumn) {
		boolean valid = true;
		int intValue = 0;
		try {
			if (calculatedValue.containsKey(rowColumn)) {
				intValue = Integer.parseInt(calculatedValue.get(rowColumn));
			} else {
				intValue = Integer.parseInt(rowColumn);
				calculatedValue.put(rowColumn, Integer.toString(intValue));
			}
		} catch (Exception ex) {
			valid = false;
		}
		return valid;
	}

	public static String getUserInput() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	// If String contain A-Z and 0-9
	public static boolean isAlfaNumericOnly(String s) {
		boolean valid = true;

		char[] a = s.toCharArray();

		for (char c : a) {
			valid = ((c >= 'A') && (c <= 'Z')) || ((c >= '0') && (c <= '9'));

			if (!valid) {
				break;
			}
		}

		return valid;

	}

	private void printOutput() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				System.out.println(String.format(output[i][j]));
			}
		}
	}
}
