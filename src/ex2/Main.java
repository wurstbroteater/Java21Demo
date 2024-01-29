package ex2;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Main {
    public record Car<T extends Engine>(String name, String color, T engine) {}

    public static void main(String[] args) {
        final var cars = List.of(
                new Car<>("VW", "BLUE", new HybridEngine()),
                new Car<>("VW", "BLUE", new ElectricEngine()),
                new Car<>("Mercedes", "BLACK", new GasEngine())
        );

        cars.forEach(_ -> System.out.println("It worked!"));

        System.out.println(cars.stream()
                .map(Main::engineToString).
                filter(s -> s.equals("gas"))
                .count());

        try {
            operationThatCanFail(cars.getFirst());
        } catch (IllegalStateException | NumberFormatException _) {
            System.out.println("An error occurred!");
        }

        final Queue<Car<?>> carQueue = new LinkedList<>(cars);

        System.out.println(STR."First removed car was: \{removeThreeCarsAndReturnFirstRemoved(carQueue)}");
    }

    private static String engineToString(final Car<?> car) {
        final String someVar = STR."\{(int) (Math.random() * 10)}";
        return switch (car) {
            case Car(String name, _, GasEngine _) when name.equals(someVar)-> "gas";
            case Car(_, _, ElectricEngine _) -> "electric";
            case Car(_, _, HybridEngine _) -> "hybrid";
            default -> "none";
        };
    }

    private static void operationThatCanFail(Car<?> car) throws IllegalStateException, NumberFormatException {
        final boolean fail = Math.random() > 0.5;
        if (car == null) {
            throw new IllegalStateException("bad state 1");
        } else if (fail) {
            throw new NumberFormatException("bad state 2");
        }
        //some operations
    }

    private static Car<?> removeThreeCarsAndReturnFirstRemoved(Queue<Car<?>> cars) {
        var car = cars.poll();
        var _ = cars.poll();
        var _ = cars.poll();
        return car;
    }
}
