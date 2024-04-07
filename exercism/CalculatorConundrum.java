package exercism;

class IllegalOperationException extends RuntimeException {
    public IllegalOperationException(String message) {
        super(message);
    }
}

public class CalculatorConundrum {
    public String calculate(int operand1, int operand2, String operation) {
        if (operation == null) {
            throw new IllegalArgumentException("Operation cannot be null");
        } else if (operation == "") {
            throw new IllegalArgumentException("Operation cannot be empty");
        }
        switch (operation) {
            case "+":
                return operand1 + " + " + operand2 + " = " + (operand1 + operand2);
            case "*":
                return operand1 + " * " + operand2 + " = " + (operand1 * operand2);
            case "/":
                try {
                    if (operand2 == 0) {
                        throw new ArithmeticException();
                    }
                    return operand1 + " / " + operand2 + " = " + (operand1 / operand2);
                } catch (ArithmeticException e) {
                    throw new IllegalOperationException("Division by zero is not allowed");
                }
            default:
                throw new IllegalOperationException("Operation '" + operation + "' does not exist");
        }
    }

}
