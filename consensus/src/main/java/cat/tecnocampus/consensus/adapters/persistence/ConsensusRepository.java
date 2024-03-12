package cat.tecnocampus.consensus.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ConsensusRepository extends JpaRepository<ConsensusDTO, UUID> {
    @Query("SELECT c FROM consensus c WHERE c.state = 0 AND c.dueDate < CURRENT_TIMESTAMP()")
    List<ConsensusDTO> getOpenAndPassedDueDate();
}
