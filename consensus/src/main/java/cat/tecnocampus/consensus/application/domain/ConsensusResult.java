package cat.tecnocampus.consensus.application.domain;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record ConsensusResult (Theme theme, UUID consensusId, Consensus.State state, Map<Consensus.VoteOption, Integer> votesCount, LocalDateTime dueDate) {
}
