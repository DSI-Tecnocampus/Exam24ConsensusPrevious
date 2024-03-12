package cat.tecnocampus.consensus.application.services;

import cat.tecnocampus.consensus.application.domain.Consensus;
import cat.tecnocampus.consensus.application.domain.ConsensusResult;
import cat.tecnocampus.consensus.application.domain.Theme;
import cat.tecnocampus.consensus.application.portsOut.ConsensusPersistence;
import cat.tecnocampus.consensus.application.portsOut.ParticipantCommunication;
import cat.tecnocampus.consensus.application.portsOut.ThemePersistence;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CreateQueryConsensus implements cat.tecnocampus.consensus.application.portsIn.CreateQueryConsensus {
    private final ConsensusPersistence consensusPersistence;
    private final ThemePersistence themePersistence;
    private final ParticipantCommunication participantCommunication;

    public CreateQueryConsensus(ConsensusPersistence consensusPersistence, ThemePersistence themePersistence,
                                ParticipantCommunication participantCommunication) {
        this.consensusPersistence = consensusPersistence;
        this.themePersistence = themePersistence;
        this.participantCommunication = participantCommunication;
    }

    @Transactional
    @Override
    public UUID createConsensus(List<String> participants, UUID themeId, LocalDateTime dueDate) {
        Theme theme = themePersistence.get(themeId).orElseThrow(() -> new IllegalArgumentException("Theme does not exist"));
        if (LocalDateTime.now().isAfter(dueDate)) {
            throw new IllegalArgumentException("Due date is in the past");
        }
        if (!allParticipantsExist(participants)) {
            throw new IllegalArgumentException("Not all participants exist");
        }
        Consensus consensus = new Consensus(theme, dueDate, participants);
        consensusPersistence.save(consensus);
        return consensus.getId();
    }

    private boolean allParticipantsExist(List<String> participants) {
        return participants.stream().allMatch(p -> participantCommunication.exists(p));
    }

    @Override
    public List<ConsensusResult> getAllConsensus() {
        return consensusPersistence.getAll().stream().map(c -> c.getResult()).toList();
    }

    @Override
    public ConsensusResult getConsensus(UUID consensusId) {
        return consensusPersistence.get(consensusId).orElseThrow(() -> new IllegalArgumentException("Consensus does not exist")).getResult();
    }
}
