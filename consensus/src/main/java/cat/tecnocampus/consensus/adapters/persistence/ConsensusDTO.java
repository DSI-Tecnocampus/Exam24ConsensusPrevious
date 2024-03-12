package cat.tecnocampus.consensus.adapters.persistence;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity(name = "consensus")
public class ConsensusDTO {
    enum VoteOption {
        YES, NO, ABSTENTION, NOT_VOTED;
    }
    public enum State {
        OPEN, CLOSED;
    }

    @Id
    private UUID id;

    @ManyToOne
    private ThemeDTO theme;
    private LocalDateTime dueDate;

    @ElementCollection
    @MapKeyColumn(name="participant")
    @Column(name="vote")
    private Map<String, VoteOption> participantVotes;
    private State state;

    public ConsensusDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ThemeDTO getTheme() {
        return theme;
    }

    public void setTheme(ThemeDTO theme) {
        this.theme = theme;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Map<String, VoteOption> getParticipantVotes() {
        return participantVotes;
    }

    public void setParticipantVotes(Map<String, VoteOption> participantVotes) {
        this.participantVotes = participantVotes;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConsensusDTO consensus = (ConsensusDTO) o;

        return id.equals(consensus.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
