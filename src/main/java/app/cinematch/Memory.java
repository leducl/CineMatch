package app.cinematch;

import java.util.*;

public class Memory {
    private final Set<String> liked = new HashSet<>();
    private final Set<String> disliked = new HashSet<>();
    private final Set<String> seen = new HashSet<>();
    private final Set<String> wishlist = new HashSet<>();

    public void add(String title, String status) {
        switch (status) {
            case "aime" -> liked.add(title);
            case "pas_interesse" -> disliked.add(title);
            case "deja_vu" -> seen.add(title);
            case "envie" -> wishlist.add(title);
        }
    }

    public boolean isKnown(String title) {
        return liked.contains(title) || disliked.contains(title) || seen.contains(title) || wishlist.contains(title);
    }

    public Set<String> getWishlist() { return wishlist; }
}
