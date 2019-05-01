package com.hc.distributed.designMode.decorator;

/**
 * the test class for decorator
 *
 * @author HC
 * @create 2019-05-01 16:55
 */
public class DecoratorTest {

    public static void main(String[] args) {
        Hamburger chickenHamburger = new ChickenHamburger();
        System.out.println(chickenHamburger.getName() + ":" + chickenHamburger.getPrice());

        Hamburger lettuceCondiment = new LettuceCondiment(chickenHamburger);
        System.out.println(lettuceCondiment.getName() + ":" + lettuceCondiment.getPrice());

        Hamburger chilliCondiment = new ChilliCondiment(chickenHamburger);
        System.out.println(chilliCondiment.getName() + ":" + chilliCondiment.getPrice());

        Hamburger condiment = new ChilliCondiment(lettuceCondiment);
        System.out.println(condiment.getName() + ":" + condiment.getPrice());
    }
}
