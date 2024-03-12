package cat.tecnocampus.consensus.application.portsOut;

import cat.tecnocampus.consensus.application.domain.Consensus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConsensusPersistence {
    void save(Consensus consensus);
    Optional<Consensus> get(UUID consensusId);

    List<Consensus> getOpenAndPassedDueDate();

    List<Consensus> getAll();
}
