package app.cinematch.api;

import java.util.List;
import java.util.Locale;

/**
 * Placeholder: renvoie une plateforme "probable".
 * À remplacer par une intégration JustWatch/WatchMode/Streaming-Availability.
 */
public class PlatformResolver {

    private final List<String> bigOnes = List.of("Netflix", "Prime Video", "Disney+", "Canal+", "Apple TV+");

    public String guessPlatform(String title) {
        // Heuristique bête: choix pseudo-aléatoire stable selon le hash du titre
        int idx = Math.abs(title.toLowerCase(Locale.ROOT).hashCode()) % bigOnes.size();
        return bigOnes.get(idx) + " (à vérifier)";
    }
}
