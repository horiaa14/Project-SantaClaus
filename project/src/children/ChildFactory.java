package children;

import common.Constants;

public final class ChildFactory {
    private ChildFactory() {

    }

    /**
     * This method is used to implement the Abstract Object Factory
     * design pattern.
     * @return an object derived from the Child class according to age
     * (upcasting).
     */
    public static Child createChild(final Integer age) {
        if (age < Constants.MAX_AGE_BABY) {
            return new Baby();
        } else if (age < Constants.MAX_AGE_KID) {
            return new Kid();
        } else if (age <= Constants.MAX_AGE_TEEN) {
            return new Teen();
        }

        return null;
    }
}
