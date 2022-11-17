package ca.kittle.util;

public class EnvUtil {

    public static int intFromEnvironment(String variable, int defaultValue) {
//        String ENV_VAR1 = Optional.ofNullable(System.getenv("ENV_VAR1")).orElseThrow(
//                () -> new CustomException("ENV_VAR1 is not set in the environment"));
        var env = System.getenv(variable);
        if (env != null) {
            try {
                return Integer.parseInt(env);
            } catch (NumberFormatException ignored) {
            }
        }
        return defaultValue;
    }

    public static String stringFromEnvironment(String name, String defaultValue) {
        var env = System.getenv(name);
        if (env != null)
            return env;
        return defaultValue;
    }

}
