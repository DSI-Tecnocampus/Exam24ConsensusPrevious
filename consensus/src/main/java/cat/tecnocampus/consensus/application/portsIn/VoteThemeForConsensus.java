package cat.tecnocampus.consensus.application.portsIn;

import cat.tecnocampus.consensus.application.domain.Consensus;
import jakarta.transaction.Transactional;

import java.util.UUID;

public interface VoteThemeForConsensus {
    void vote(String participant, UUID consensusId, Consensus.VoteOption vote);
}
