package cat.tecnocampus.consensus.adapters.apiRest;

import cat.tecnocampus.consensus.application.portsIn.ThemeUseCases;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ThemeRestApi {
    private final ThemeUseCases themeUseCases;

    public ThemeRestApi(ThemeUseCases themeUseCases) {
        this.themeUseCases = themeUseCases;
    }

    @PostMapping("/themes")
    public void createTheme(@RequestBody ThemeIn themeIn) {
        themeUseCases.createTheme(themeIn.title(), themeIn.description());
    }

    @GetMapping("/themes")
    public List<ThemeOut> getThemes() {
        return themeUseCases.getAllThemes().stream()
                .map(theme -> new ThemeOut(theme.id(), theme.title(), theme.description()))
                .toList();
    }

    @GetMapping("/themes/{id}")
    public ThemeOut getTheme(@PathVariable String id) {
        return themeUseCases.getTheme(UUID.fromString(id))
                .map(theme -> new ThemeOut(theme.id(), theme.title(), theme.description()))
                .orElse(null);
    }
}
