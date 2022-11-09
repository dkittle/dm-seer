package ca.kittle.models;

import java.util.Map;

public record RestRequest(String body, Map Headers) {
}

