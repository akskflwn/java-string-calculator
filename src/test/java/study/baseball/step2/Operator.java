package study.baseball.step2;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Operator {

    PLUS("+", (first, second) -> first + second),
    MINUS("-", (first, second) -> first - second),
    MULTIPLY("*", (first, second) -> first * second),
    DIVIDE("/", (first, second) -> {
        if (second == 0) {
            throw new IllegalArgumentException();
        }
        return first / second;
    });

    public static final Map<String, Operator> operatorMap = Collections.unmodifiableMap
            (Stream.of(values())
                    .collect(Collectors.toMap(Operator::getSymbol, Function.identity())));
    private String symbol;
    private BiFunction<Integer, Integer, Integer> operation;

    Operator(String symbol, BiFunction<Integer, Integer, Integer> operation) {
        this.symbol = symbol;
        this.operation = operation;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public int operate(int first, int second) {
        return operation.apply(first, second);
    }


    public static Operator findOperator(String symbol) {

        Operator operator = operatorMap.get(symbol);

        if (operator == null) {
            throw new IllegalArgumentException();
        }
        return operator;
    }
}



