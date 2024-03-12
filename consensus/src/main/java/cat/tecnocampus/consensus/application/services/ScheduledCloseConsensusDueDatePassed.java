package cat.tecnocampus.consensus.application.services;

import cat.tecnocampus.consensus.application.domain.Consensus;
import cat.tecnocampus.consensus.application.portsOut.ConsensusPersistence;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduledCloseConsensusDueDatePassed {
    private final ConsensusPersistence consensusPersistence;

    public ScheduledCloseConsensusDueDatePassed(ConsensusPersistence consensusPersistence) {
        this.consensusPersistence = consensusPersistence;
    }

    @Scheduled(fixedRate = 5000)
    @Transactional
    public void closeConsensusByDueDate() {
        List<Consensus> openConsensusAndPassedDueDate = consensusPersistence.getOpenAndPassedDueDate();
        System.out.println("Dealing with due date passed consensus. Number of them= " + openConsensusAndPassedDueDate.size());
        openConsensusAndPassedDueDate.forEach(consensus -> {
            consensus.closeIfDueDatePassed();
            consensusPersistence.save(consensus);
        });
    }
}
