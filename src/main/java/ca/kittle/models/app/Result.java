package ca.kittle.models.app;

public record Result(String status, int code, String message) {
    public Result ok(String message) {
        return new Result("OK", 0, message);
    }
    public Result error(String message) {
        return new Result("KO", -1, message);
    }
    public Result error(int code, String message) {
        return new Result("KO", code, message);
    }
}
