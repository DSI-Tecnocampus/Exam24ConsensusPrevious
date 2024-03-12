package cat.tecnocampus.consensus.application.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Consensus {
    public enum VoteOption {
        YES, NO, ABSTENTION, NOT_VOTED;
    }
    private final UUID id;
    private final Theme theme;
    private final LocalDateTime dueDate;
    private final Map<String, VoteOption> participantVotes;
    private State state;

    public enum State {
        OPEN, CLOSED;
    }

    public Consensus(Theme theme, LocalDateTime dueDate, List<String> participants) {
        this.id = UUID.randomUUID();
        this.theme = theme;
        this.dueDate = dueDate;
        this.state = State.OPEN;

        this.participantVotes = new HashMap<>();
        participants.forEach(participant -> participantVotes.put(participant, VoteOption.NOT_VOTED));
    }

    public Consensus(UUID id, Theme theme, LocalDateTime dueDate, Map<String, VoteOption> participantVotes, State state) {
        this.id = id;
        this.theme = theme;
        this.dueDate = dueDate;
        this.participantVotes = participantVotes;
        this.state = state;
    }

    public void vote(String participant, VoteOption vote) {
        LocalDateTime currentTime = LocalDateTime.now();
        if (participantVotes.containsKey(participant) && participantVotes.get(participant) == VoteOption.NOT_VOTED
            && currentTime.isBefore(dueDate) && vote != VoteOption.NOT_VOTED) {
            participantVotes.put(participant, vote);
        }
        else {
            throw new IllegalArgumentException("Participant " + participant + " has emitted an illegal vote");
        }
        closeIfAllVoted();
    }

    private void closeIfAllVoted() {
        if (participantVotes.values().stream().allMatch(vote -> vote != VoteOption.NOT_VOTED)) {
            state = State.CLOSED;
        }
    }

    public void closeIfDueDatePassed() {
        if (LocalDateTime.now().isAfter(dueDate)) {
            state = State.CLOSED;
        }
    }

    public ConsensusResult getResult() {
        Map<VoteOption, Integer> votesCount = new HashMap<>();
        votesCount.put(VoteOption.YES, 0);
        votesCount.put(VoteOption.NO, 0);
        votesCount.put(VoteOption.ABSTENTION, 0);
        votesCount.put(VoteOption.NOT_VOTED, 0);

        participantVotes.values().forEach(vote -> {
            votesCount.put(vote, votesCount.get(vote) + 1);
        });

        return new ConsensusResult(theme, id, state, votesCount, dueDate);
    }

    public UUID getId() {
        return id;
    }

    public Theme getTheme() {
        return theme;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public boolean isClosed() {
        return state == State.CLOSED;
    }

    public Map<String, VoteOption> getParticipantVotes() {
        return participantVotes;
    }

    public String getState() {
        return state.name();
    }

    public List<String> getParticipants() {
        return List.copyOf(participantVotes.keySet());
    }
}
