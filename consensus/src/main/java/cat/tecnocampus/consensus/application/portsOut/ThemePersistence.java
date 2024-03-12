package cat.tecnocampus.consensus.application.portsOut;

import cat.tecnocampus.consensus.application.domain.Theme;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ThemePersistence {
    void save(Theme theme);
    boolean exists(UUID themeId);
    Optional<Theme> get(UUID themeId);
    List<Theme> getAll();
}
