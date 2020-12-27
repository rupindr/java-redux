package com.rupindr.javaredux;

public class ActionCreator {

    /**
     * @param type action type
     * @param payload payload
     * @return action object
     */
    public static Action<String> create(String type, String payload) {
        return new Action<>(type, payload);
    }

    /**
     * @param type action type
     * @param payload payload
     * @return action object
     */
    public static Action<Integer> create(String type, Integer payload) {
        return new Action<>(type, payload);
    }

    /**
     * @param type action type
     * @param payload payload
     * @return action object
     */
    public static Action<Float> create(String type, Float payload) {
        return new Action<>(type, payload);
    }

    /**
     * @param type action type
     * @param payload payload
     * @return action object
     */
    public static Action<Double> create(String type, Double payload) {
        return new Action<>(type, payload);
    }

    /**
     * @param type action type
     * @param payload payload
     * @return action object
     */
    public static Action<Character> create(String type, Character payload) {
        return new Action<>(type, payload);
    }

    /**
     * @param type action type
     * @param payload payload
     * @return action object
     */
    public static Action<Boolean> create(String type, Boolean payload) {
        return new Action<>(type, payload);
    }

}
