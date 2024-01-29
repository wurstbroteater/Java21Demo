package ex1;

import java.util.List;
import java.util.function.ToIntFunction;

public class Main {
    public static void main(String[] args) {
        Populated berlin = new City("Berlin", 3_645_000);
        Populated london = new City("London", 8_982_000);
        Populated paris = new City("Paris", 2_161_000);
        Populated kyiv = new City("Kyiv", 2_884_000);
        Populated madrid = new City("Madrid", 3_223_000);
        
        List<Populated> cities = List.of(berlin,london,paris,kyiv,madrid);

        System.out.println(STR."Total population: \{calcTotalPopulation(cities)}");
    }
    
    private static int calcTotalPopulation(final List<Populated> cities) {
        ToIntFunction<Populated> pop = populated ->
            switch (populated) {
                case City(_, int population) -> population;
                case Department(String _, int population) -> population;
                case Country _ -> 0;
        };
        return cities.stream().mapToInt(pop).sum();
    }
}
