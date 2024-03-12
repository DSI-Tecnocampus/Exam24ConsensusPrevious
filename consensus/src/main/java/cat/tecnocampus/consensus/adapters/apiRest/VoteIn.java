package cat.tecnocampus.consensus.adapters.apiRest;

import java.util.UUID;

public record VoteIn (String participant, VoteOption vote) {
    enum VoteOption {
        YES, NO, ABSTENTION, NOT_VOTED;
    }
}
