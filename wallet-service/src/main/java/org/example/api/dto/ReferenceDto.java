package org.example.api.dto;

import java.util.Map;
import java.util.UUID;

public record ReferenceDto(UUID id, String name, Map<String, String> metadata) {

    public static ReferenceDto of(UUID id) {
        return new ReferenceDto(id, null, null);
    }

    public static ReferenceDto of(UUID id, String name) {
        return new ReferenceDto(id, name, null);
    }

    public static ReferenceDto of(String name) {
        return new ReferenceDto(null, name, null);
    }
}