package cat.tecnocampus.consensus.application.domain;

import java.util.UUID;

public record Theme(UUID id, String title, String description) {
    public Theme(String title, String description) {
        this(UUID.randomUUID(), title, description);
    }
}
