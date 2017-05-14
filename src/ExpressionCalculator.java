import java.util.ArrayList;
import java.util.Arrays;

public class ExpressionCalculator {
	private StackX theStack;
	private String input;

	public ExpressionCalculator(String s) {
		input = s;
	}

	public float calculate() {
//		input = input.replace(" ", "");
		ArrayList<String> inputArray = new ArrayList<String>(Arrays.asList(input.split(" ")));
		
		theStack = new StackX(inputArray.size());
		String item;
		int j;
		float num1, num2, interAns;
		for (j = 0; j < inputArray.size(); j++) {
			item = inputArray.get(j);
			float fValue = 0;
			try {
				fValue =Float.parseFloat(item);
			} catch (Exception e) {
			}
			// read from input
//			theStack.displayStack("" + item + " "); // *diagnostic*
			if (fValue >= Float.MIN_VALUE && fValue <= Float.MAX_VALUE)
				// if it's a number
				theStack.push(fValue);
			else if (theStack.size() >= 2) {
				num2 = theStack.pop();
				num1 = theStack.pop();
				switch (item) {
				case "+":
					interAns = num1 + num2;
					break;
				case "-":
					interAns = num1 - num2;
					break;
				case "*":
					interAns = num1 * num2;
					break;
				case "/":
					interAns = num1 / num2;
					break;
				default:
					interAns = 0;
				}
				theStack.push(interAns);
			}
		}
		interAns = theStack.pop();
		return interAns;
	}
}
