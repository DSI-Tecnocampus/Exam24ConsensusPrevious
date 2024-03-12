package cat.tecnocampus.consensus.adapters.apiRest;

import cat.tecnocampus.consensus.application.domain.ConsensusResult;
import cat.tecnocampus.consensus.application.portsIn.CreateQueryConsensus;
import cat.tecnocampus.consensus.application.portsIn.VoteThemeForConsensus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ConsensusRestApi {
    private final CreateQueryConsensus createQueryConsensus;
    private final VoteThemeForConsensus voteThemeForConsensus;

    public ConsensusRestApi(CreateQueryConsensus createQueryConsensus, VoteThemeForConsensus voteThemeForConsensus) {
        this.createQueryConsensus = createQueryConsensus;
        this.voteThemeForConsensus = voteThemeForConsensus;
    }

    @PostMapping("/consensus")
    public UUID createConsensus(@RequestBody ConsensusIn consensusIn) {
        return createQueryConsensus.createConsensus(consensusIn.participants(), UUID.fromString(consensusIn.themeId()), consensusIn.dueDate());
    }

    @GetMapping("/consensus")
    public List<ConsensusResult> getConsensus() {
        return createQueryConsensus.getAllConsensus();
    }

    @GetMapping("/consensus/{id}")
    public ConsensusResult getConsensus(@PathVariable String id) {
        return createQueryConsensus.getConsensus(UUID.fromString(id));
    }

    @PutMapping("/consensus/{id}/vote")
    public void voteConsensus(@PathVariable String id, @RequestBody VoteIn voteIn) {
        voteThemeForConsensus.vote(voteIn.participant(), UUID.fromString(id),
                cat.tecnocampus.consensus.application.domain.Consensus.VoteOption.valueOf(voteIn.vote().name()));
    }
}
