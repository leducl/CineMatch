package app.cinematch;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CsvStorage {

    private static final String FILE_PATH = "src/main/resources/data.csv";

    public static synchronized void save(String title, String status) {
        try {
            Files.createDirectories(Paths.get("src/main/resources"));
            List<String[]> existing = load();
            boolean found = false;
            for (String[] entry : existing) {
                if (entry.length >= 2 && entry[0].equalsIgnoreCase(title)) {
                    entry[1] = status;
                    found = true;
                    break;
                }
            }
            try (FileWriter fw = new FileWriter(FILE_PATH, false)) {
                if (!found) existing.add(new String[]{title, status});
                for (String[] e : existing) {
                    fw.write(e[0] + ";" + e[1] + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("[Erreur CSV] " + e.getMessage());
        }
    }

    public static synchronized List<String[]> load() {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 2) data.add(parts);
            }
        } catch (IOException ignored) {}
        return data;
    }

    public static synchronized boolean hasStatus(String title, String status) {
        for (String[] e : load()) {
            if (e[0].equalsIgnoreCase(title) && e[1].equalsIgnoreCase(status)) return true;
        }
        return false;
    }

    public static synchronized boolean alreadyExists(String title) {
        for (String[] e : load()) {
            if (e[0].equalsIgnoreCase(title)) return true;
        }
        return false;
    }

    public static synchronized List<String> getMoviesByStatus(String status) {
        List<String> movies = new ArrayList<>();
        for (String[] e : load()) {
            if (e[1].equalsIgnoreCase(status)) movies.add(e[0]);
        }
        return movies;
    }
}
