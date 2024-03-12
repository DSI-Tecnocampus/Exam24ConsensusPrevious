package cat.tecnocampus.consensus.application.services;

import cat.tecnocampus.consensus.application.domain.Consensus;
import cat.tecnocampus.consensus.application.portsOut.ConsensusPersistence;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VoteThemeForConsensusForConsensus implements cat.tecnocampus.consensus.application.portsIn.VoteThemeForConsensus {
    private final ConsensusPersistence consensusPersistence;

    public VoteThemeForConsensusForConsensus(ConsensusPersistence consensusPersistence) {
        this.consensusPersistence = consensusPersistence;
    }

    @Transactional
    @Override
    public void vote(String participant, UUID consensusId, Consensus.VoteOption vote) {
        Consensus consensus = consensusPersistence.get(consensusId).orElseThrow(() -> new IllegalArgumentException("Consensus does not exist"));
        consensus.vote(participant, vote);
        consensusPersistence.save(consensus);
    }
}
