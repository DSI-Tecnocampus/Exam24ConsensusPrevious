package cat.tecnocampus.consensus.adapters.apiRest;

import cat.tecnocampus.consensus.application.domain.Theme;

import java.time.LocalDateTime;
import java.util.List;

public record ConsensusIn(String themeId, LocalDateTime dueDate, List<String> participants) { }
