package ca.kittle.models.app;

public record Result(String status, int code, String message) {
    public static Result ok(String message) {
        return new Result("OK", 0, message);
    }
    public static Result error(String message) {
        return new Result("KO", -1, message);
    }
    public static Result error(int code, String message) {
        return new Result("KO", code, message);
    }
}
