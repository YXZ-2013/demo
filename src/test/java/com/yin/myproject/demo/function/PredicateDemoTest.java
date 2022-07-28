package com.yin.myproject.demo.function;

import junit.framework.TestCase;

public class PredicateDemoTest extends TestCase {

    public void testFilterGreenApples() {
    }

    public void testFilterApples() {
        PredicateDemo predicateDemo = new PredicateDemo();
        predicateDemo.filterApples(Apple::isGreenApple);
        predicateDemo.filterApples(Apple::isHeavyApple);
        predicateDemo.filterApples(Apple::isHeavyApple);
        predicateDemo.filterApples(e -> "green".equals(e.getColor()));
        predicateDemo.filterApples(e -> e.getWeight() > 150);
    }


}