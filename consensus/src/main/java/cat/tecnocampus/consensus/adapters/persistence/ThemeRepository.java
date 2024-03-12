package cat.tecnocampus.consensus.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ThemeRepository extends JpaRepository<ThemeDTO, UUID> {
}
