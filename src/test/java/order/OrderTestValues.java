package order;

import java.util.Arrays;
import java.util.List;

public class OrderTestValues {

    static String bunR2D3 = "61c0c5a71d1f82001bdaaa6d";
    static String meteoriteCutlet = "61c0c5a71d1f82001bdaaa70";
    static String sauceSpiceX = "61c0c5a71d1f82001bdaaa72";
    static String mineralRings = "61c0c5a71d1f82001bdaaa76";
    static String miniSalad = "61c0c5a71d1f82001bdaaa79";
    static String cheeseWithAsteroidMold = "61c0c5a71d1f82001bdaaa7a";
    static String superNonExistentSpaceCowboyWhiskey = "1337see420you2001space2071cowboy";

    static List<String> ingredientsValid = Arrays.asList(bunR2D3, meteoriteCutlet, sauceSpiceX, mineralRings, miniSalad, cheeseWithAsteroidMold, bunR2D3);

    static List<String> ingredientsNotValidEmptyList = List.of();
    static List<String> ingredientsNotValidWrongHash = Arrays.asList(bunR2D3, meteoriteCutlet, cheeseWithAsteroidMold, bunR2D3, superNonExistentSpaceCowboyWhiskey);

    public static OrderCard orderValid = new OrderCard(ingredientsValid);

    public static OrderCard orderNotValidEmptyList = new OrderCard(ingredientsNotValidEmptyList);
    public static OrderCard orderNotValidWrongHash = new OrderCard(ingredientsNotValidWrongHash);
}
