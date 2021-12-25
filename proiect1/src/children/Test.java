package children;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Child> arr = new ArrayList<>();
        arr.add(new Baby());
        arr.add(new Kid());
        arr.add(new Teen());
        AverageScoreCalculator calculator = new AverageScoreImpl();
        for(var elem : arr) {
            System.out.println(elem.accept(calculator));
        }
        System.out.println("---------------------");
        Child obj = arr.get(1);
        obj = new Teen();
        arr.set(1, obj);
        for(var elem : arr) {
            System.out.println(elem.accept(calculator));
        }

    }
}
