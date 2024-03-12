package cat.tecnocampus.consensus.adapters.persistence;

import cat.tecnocampus.consensus.application.domain.Theme;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ThemePersistence implements cat.tecnocampus.consensus.application.portsOut.ThemePersistence {

    private final ThemeRepository themeRepository;

    public ThemePersistence(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @Override
    public void save(cat.tecnocampus.consensus.application.domain.Theme themeDomain) {
        ThemeDTO theme =
                new ThemeDTO(themeDomain.id(), themeDomain.title(), themeDomain.description());
        themeRepository.save(theme);
    }

    @Override
    public boolean exists(UUID themeId) {
        return false;
    }

    @Override
    public Optional<Theme> get(UUID themeId) {
        Optional<ThemeDTO> theme = themeRepository.findById(themeId);
        if (theme.isPresent()) {
            return Optional.of(new Theme(theme.get().getId(), theme.get().getTitle(), theme.get().getDescription()));
        }
        return Optional.empty();
    }

    @Override
    public List<Theme> getAll() {
        List<ThemeDTO> themes = themeRepository.findAll();
        return themes.stream()
                .map(theme -> new Theme(theme.getId(), theme.getTitle(), theme.getDescription()))
                .toList();
    }
}
