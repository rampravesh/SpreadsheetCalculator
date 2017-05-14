import java.io.*;

class StackX {
	private int maxSize;
	private float[] stackArray;
	private int top;

	public StackX(int size) {
		maxSize = size;
		stackArray = new float[maxSize];
		top = -1;
	}

	// put item on top of stack
	public void push(float j) {
		stackArray[++top] = j;
	}

	// take item from top of stack
	public float pop() {
		return stackArray[top--];
	}

	// peek at top of stack
	public float peek() {
		return stackArray[top];
	}

	// true if stack is empty
	public boolean isEmpty() {
		return (top == -1);
	}

	// true if stack is full
	public boolean isFull() {
		return (top == maxSize - 1);
	}

	// return size
	public int size() {
		return top + 1;
	}

	// peek at index n
	public float peekN(int n) {
		return stackArray[n];
	}

	public void displayStack(String s) {
		for (int j = 0; j < size(); j++) {
			System.out.print(peekN(j));
			System.out.print(' ');
		}
		System.out.println("");
	}
}
