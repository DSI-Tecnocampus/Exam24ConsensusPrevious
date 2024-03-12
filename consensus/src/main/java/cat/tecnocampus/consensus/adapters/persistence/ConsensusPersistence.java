package cat.tecnocampus.consensus.adapters.persistence;

import cat.tecnocampus.consensus.application.domain.Consensus;
import cat.tecnocampus.consensus.application.domain.Theme;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ConsensusPersistence implements cat.tecnocampus.consensus.application.portsOut.ConsensusPersistence {
    private final ConsensusRepository consensusRepository;

    public ConsensusPersistence(ConsensusRepository consensusRepository) {
        this.consensusRepository = consensusRepository;
    }

    @Override
    public void save(Consensus consensus) {
        ConsensusDTO consensusDTO =
                mapToPersistence(consensus);
        consensusRepository.save(consensusDTO);
    }

    @Override
    public Optional<Consensus> get(UUID consensusId) {
        Optional<ConsensusDTO> consensus = consensusRepository.findById(consensusId);
        return consensus.map(consensusDTO -> mapToDomain(consensusDTO));
    }

    @Override
    public List<Consensus> getOpenAndPassedDueDate() {
        return consensusRepository.getOpenAndPassedDueDate().stream().map(this::mapToDomain).toList();
    }

    @Override
    public List<Consensus> getAll() {
        return consensusRepository.findAll().stream().map(this::mapToDomain).toList();
    }

    private ConsensusDTO mapToPersistence(Consensus consensus) {
        ConsensusDTO consensusDTO =
                new ConsensusDTO();
        consensusDTO.setId(consensus.getId());
        ThemeDTO theme = new ThemeDTO(consensus.getTheme().id(), consensus.getTheme().title(), consensus.getTheme().description());
        consensusDTO.setTheme(theme);
        consensusDTO.setDueDate(consensus.getDueDate());
        HashMap<String, ConsensusDTO.VoteOption> participantVotesPersistence = new HashMap<>();
        consensus.getParticipantVotes().forEach((k, v) -> participantVotesPersistence.put(k, ConsensusDTO.VoteOption.valueOf(v.name())));
        consensusDTO.setParticipantVotes(participantVotesPersistence);
        consensusDTO.setState(ConsensusDTO.State.valueOf(consensus.getState()));
        return consensusDTO;
    }

    private Consensus mapToDomain(ConsensusDTO consensusDTO) {
        Theme theme = new Theme(consensusDTO.getTheme().getId(), consensusDTO.getTheme().getTitle(), consensusDTO.getTheme().getDescription());
        HashMap<String, Consensus.VoteOption> participantVotesDomain = new HashMap<>();
        consensusDTO.getParticipantVotes().forEach((k, v) -> participantVotesDomain.put(k, Consensus.VoteOption.valueOf(v.name())));
        Consensus consensus = new Consensus(consensusDTO.getId(), theme, consensusDTO.getDueDate(), participantVotesDomain, cat.tecnocampus.consensus.application.domain.Consensus.State.valueOf(consensusDTO.getState().name()));

        return consensus;
    }
}
