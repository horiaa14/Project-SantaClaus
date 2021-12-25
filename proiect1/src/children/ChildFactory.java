package children;

import common.Constants;

public class ChildFactory {
    public static Child createChild(final Integer age) {
        if (age < Constants.MAX_AGE_BABY) {
            return new Baby();
        }

        else if (age < Constants.MAX_AGE_KID) {
            return new Kid();
        }

        else if (age < Constants.MAX_AGE_TEEN) {
            return new Teen();
        }

       return null;
    }
}
