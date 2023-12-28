import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Calculator {
    public static String evaluate(String expression) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            Object result = engine.eval(expression);

            if (result instanceof Integer) {
                return Integer.toString((Integer) result);
            } else if (result instanceof Double) {
                return Double.toString((Double) result);
            } else {
                return result.toString();
            }
        } catch (ScriptException e) {
            throw new ArithmeticException("Error");
        }
    }
}
