package cat.tecnocampus.consensus.application.portsIn;

import cat.tecnocampus.consensus.application.domain.ConsensusResult;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface CreateQueryConsensus {
    UUID createConsensus(List<String> participants, UUID themeId, LocalDateTime dueDate);
    List<ConsensusResult> getAllConsensus();
    ConsensusResult getConsensus(UUID consensusId);
}
