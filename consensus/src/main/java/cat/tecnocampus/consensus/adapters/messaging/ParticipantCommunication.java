package cat.tecnocampus.consensus.adapters.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ParticipantCommunication implements cat.tecnocampus.consensus.application.portsOut.ParticipantCommunication {

    private final WebClient webClient;
    private final String peopleServiceUrl;

    public ParticipantCommunication(WebClient webClient,
                                   @Value("${app.people-service.host}") String peopleServiceHost,
                                   @Value("${app.people-service.port}") String peopleServicePort) {
        this.webClient = webClient;
        this.peopleServiceUrl = "http://" + peopleServiceHost + ":" + peopleServicePort + "/people";
    }
    @Override
    public boolean exists(String email) {
        return webClient.get()
                .uri(peopleServiceUrl + "/"+ email + "/exists")
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

}
