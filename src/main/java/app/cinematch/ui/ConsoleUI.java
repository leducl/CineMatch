package app.cinematch.ui;

import app.cinematch.MovieRecommenderService;
import app.cinematch.CsvStorage;
import app.cinematch.model.Recommendation;
import java.util.*;

public class ConsoleUI {

    private final MovieRecommenderService service;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(MovieRecommenderService service) {
        this.service = service;
    }

    public void start() {
        while (true) {
            System.out.println("\n🎬 --- MENU CINE MATCH ---");
            System.out.println("1️⃣  Trouver un film similaire");
            System.out.println("2️⃣  Mode Tinder infini");
            System.out.println("3️⃣  Voir ma liste de films à regarder");
            System.out.println("0️⃣  Quitter");
            System.out.print("Choix : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> outil1();
                case "2" -> outil2();
                case "3" -> outil3();
                case "0" -> { System.out.println("👋 À bientôt !"); return; }
                default -> System.out.println("❌ Choix invalide !");
            }
        }
    }

    private void outil1() {
        System.out.print("Donne un film que tu as aimé : ");
        String liked = scanner.nextLine();
        Recommendation rec = service.recommendFromLike(liked);
        afficherEtSauvegarder(rec);
    }

    private void outil2() {
        while (true) {
            Recommendation rec = service.recommendRandom();
            if (CsvStorage.alreadyExists(rec.title())) continue;
            System.out.println("\n🎥 " + rec.title());
            System.out.println("💬 " + rec.reason());
            System.out.println("📺 " + rec.platform());
            System.out.print("❤️=envie / ❌=pas_interesse / 👁️=deja_vu / q=quitter : ");
            String ans = scanner.nextLine();
            switch (ans) {
                case "❤️", "y" -> {
                    CsvStorage.save(rec.title(), "envie");
                    String desc = service.generateDescription(rec.title());
                    System.out.println("📝 " + desc);
                }
                case "❌", "n" -> CsvStorage.save(rec.title(), "pas_interesse");
                case "👁️" -> CsvStorage.save(rec.title(), "deja_vu");
                case "q" -> { return; }
            }
        }
    }

    private void outil3() {
        System.out.println("\n🎞️ Tes envies de films :");
        List<String> envies = CsvStorage.getMoviesByStatus("envie");
        for (String title : envies) {
            System.out.println("\n🎥 " + title);
            String desc = service.generateDescription(title);
            System.out.println("📝 " + desc);
        }
    }

    private void afficherEtSauvegarder(Recommendation rec) {
        System.out.println("\n🎥 " + rec.title());
        System.out.println("💬 " + rec.reason());
        System.out.println("📺 " + rec.platform());
        System.out.print("Souhaites-tu l'ajouter à ta liste ? (y/n) : ");
        String ans = scanner.nextLine();
        if (ans.equalsIgnoreCase("y")) CsvStorage.save(rec.title(), "envie");
        System.out.print("Souhaites-tu une description ? (y/n) : ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            String desc = service.generateDescription(rec.title());
            System.out.println("\n📝 " + desc);
        }
    }
}
