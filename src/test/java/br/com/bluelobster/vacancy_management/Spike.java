package br.com.bluelobster.vacancy_management;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Spike {

    @Test
    public void should_be_able_say_hello() {
        var expected = "Hello";
        assertEquals(expected, sayHello());
    }

    public static String sayHello() {
        return "Hello";
    }
}
