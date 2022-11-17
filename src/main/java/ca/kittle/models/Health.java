package ca.kittle.models;



public record Health(String code, String message) {
    public static final Health OK = new Health("OK", "all okay");
    public static final Health ERROR = new Health("KO", "error");

}
