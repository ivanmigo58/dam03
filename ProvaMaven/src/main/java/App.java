import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello Maven!");

        Planeta planetaM = new Planeta("Maven", 50000);
        Planeta planetaA = new Planeta("Ant", 50000);
        Planeta planetaG = new Planeta("Gradle", 50000);

        List<Planeta> planetaList = new ArrayList<>();
        planetaList.addAll(Arrays.asList(planetaM, planetaA, planetaG));

        System.out.println(planetaList);
        planetaList.forEach(System.out::println);

        JSONArray jsonPlaneta = new JSONArray(planetaList);
        System.out.println(jsonPlaneta);


    }
}
