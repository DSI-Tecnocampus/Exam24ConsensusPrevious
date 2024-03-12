package cat.tecnocampus.consensus.application.portsOut;

import reactor.core.publisher.Mono;

public interface ParticipantCommunication {
    boolean exists(String email);
}
