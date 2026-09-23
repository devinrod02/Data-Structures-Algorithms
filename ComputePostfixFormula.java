import java.util.Queue;
import java.util.Stack;

public class ComputePostfixFormula
{
  public static void printName()
  {
    System.out.println("This solution was completed by:");
    System.out.println("Devin Rodriguez");
  }

  /**
   * Evaluates a postfix (Reverse Polish Notation) boolean expression using a stack.
   * Supports operators: AND, OR, NOT, XOR, NAND, NOR, COND, BICOND.
   * Returns "true", "false", or "error" if the expression is invalid.
   */
  public static String processPostfixBooleanFormula(Queue<String> symbolQueue) throws IllegalArgumentException
  {
    Stack<Boolean> stack = new Stack<Boolean>();
    while (!symbolQueue.isEmpty())
    {
      String token = symbolQueue.poll();
      if (token.equals("true") || token.equals("false"))
      {
        stack.push(Boolean.valueOf(token));
      }
      else if (token.equals("NOT"))
      {
        if (stack.size() < 1)
          return "error";
        boolean op1 = stack.pop();
        stack.push(!op1);
      }
      else
      {
        if (stack.size() < 2)
          return "error";

        boolean op2 = stack.pop();
        boolean op1 = stack.pop();
        boolean result;
        if (token.equals("AND"))
          result = op1 && op2;
        else if (token.equals("NAND"))
          result = !(op1 && op2);
        else if (token.equals("OR"))
          result = op1 || op2;
        else if (token.equals("NOR"))
          result = !(op1 || op2);
        else if (token.equals("XOR"))
          result = op1 != op2;
        else if (token.equals("COND"))
          result = !op1 || op2;
        else if (token.equals("BICOND"))
          result = op1 == op2;
        else
          return "error";

        stack.push(result);
      }
    }
    if (stack.size() != 1)
      return "error";
    return Boolean.toString(stack.pop());
  }
}
