package app.cinematch;

public final class AgentPrompts {
    private AgentPrompts() {}

    public static String systemPrompt() {
        return String.join("\n",
            "Tu es un expert en recommandations de films.",
            "Règles: ",
            "- Réponds en français.",
            "- Si on te donne un film aimé, propose un autre film similaire.",
            "- Donne une courte justification (acteurs/genres/ambiance). ",
            "- Propose UNE seule recommandation concise (titre exact). ",
            "- Ne pas inventer de détails factuels (année, casting) si tu n'es pas sûr."
        );
    }

    public static String userPrompt(String likedTitle) {
        return "L'utilisateur a aimé: '" + likedTitle + "'. Propose un film similaire (titre exact) avec une justification courte.";
    }
}
