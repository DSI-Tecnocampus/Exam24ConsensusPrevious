package cat.tecnocampus.consensus.application.portsIn;

import cat.tecnocampus.consensus.application.domain.Theme;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ThemeUseCases {
    void createTheme(String title, String description);

    Optional<Theme> getTheme(UUID themeId);
    List<Theme> getAllThemes();
}
