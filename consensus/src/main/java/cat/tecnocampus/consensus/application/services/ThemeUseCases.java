package cat.tecnocampus.consensus.application.services;

import cat.tecnocampus.consensus.application.domain.Theme;
import cat.tecnocampus.consensus.application.portsOut.ThemePersistence;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ThemeUseCases implements cat.tecnocampus.consensus.application.portsIn.ThemeUseCases {
    private final ThemePersistence themePersistence;

    public ThemeUseCases(ThemePersistence themePersistence) {
        this.themePersistence = themePersistence;
    }

    @Override
    public void createTheme(String title, String description) {
        Theme theme = new Theme(title, description);
        themePersistence.save(theme);
    }

    @Override
    public Optional<Theme> getTheme(UUID themeId) {
        return themePersistence.get(themeId);
    }

    @Override
    public List<Theme> getAllThemes() {
        return themePersistence.getAll();
    }
}
