package top.yms.past11.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AppleTest {

    public static List<Apple> filterApples(List<Apple> inventory,
                                           ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory){
            if (p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(1,"red"));
        inventory.add(new Apple(2,"red"));
        inventory.add(new Apple(3,"green"));
        inventory.add(new Apple(14,"red"));
        inventory.add(new Apple(5,"green"));
        inventory.add(new Apple(36,"red"));
        inventory.add(new Apple(7,"green"));
        inventory.add(new Apple(8,"green"));
        inventory.add(new Apple(9,"red"));

        filterApples(inventory, (Apple apple) -> "red".equals(apple.getColor())).forEach(System.out::println);
        System.out.println("---------------------");
        filterApples(inventory, apple -> apple.getWeight()>=2 && apple.getWeight()<=44).sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));




    }

}
