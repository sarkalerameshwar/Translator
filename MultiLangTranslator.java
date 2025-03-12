import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MultiLangTranslator {

    private static final Map<String, String> englishToFrench = new HashMap<>();
    private static final Map<String, String> englishToGerman = new HashMap<>();
    private static final Map<String, String> englishToRundi = new HashMap<>();

    static {
        // English to French
        englishToFrench.put("hello", "bonjour");
        englishToFrench.put("world", "monde");
        englishToFrench.put("goodbye", "au revoir");
        englishToFrench.put("please", "s'il vous plaît");
        englishToFrench.put("thank you", "merci");
        englishToFrench.put("yes", "oui");
        englishToFrench.put("no", "non");
        englishToFrench.put("friend", "ami");
        englishToFrench.put("family", "famille");
        englishToFrench.put("water", "eau");
        englishToFrench.put("food", "nourriture");
        englishToFrench.put("love", "amour");
        englishToFrench.put("morning", "matin");
        englishToFrench.put("night", "nuit");
        englishToFrench.put("sun", "soleil");
        englishToFrench.put("moon", "lune");
        englishToFrench.put("star", "étoile");
        englishToFrench.put("happy", "heureux");
        englishToFrench.put("sad", "triste");
        englishToFrench.put("cold", "froid");
        englishToFrench.put("hot", "chaud");
        englishToFrench.put("school", "école");
        englishToFrench.put("teacher", "professeur");
        englishToFrench.put("student", "étudiant");
        englishToFrench.put("beautiful", "beau");
        englishToFrench.put("ugly", "laid");
        englishToFrench.put("car", "voiture");
        englishToFrench.put("house", "maison");
        englishToFrench.put("book", "livre");
        englishToFrench.put("pen", "stylo");
        englishToFrench.put("paper", "papier");
        englishToFrench.put("computer", "ordinateur");
        englishToFrench.put("city", "ville");
        englishToFrench.put("village", "village");
        englishToFrench.put("river", "rivière");
        englishToFrench.put("mountain", "montagne");
        englishToFrench.put("ocean", "océan");
        englishToFrench.put("father", "père");
        englishToFrench.put("mother", "mère");
        englishToFrench.put("child", "enfant");
        // Add more words up to 100+...

        // English to German
        englishToGerman.put("hello", "hallo");
        englishToGerman.put("world", "welt");
        englishToGerman.put("goodbye", "auf Wiedersehen");
        englishToGerman.put("please", "bitte");
        englishToGerman.put("thank you", "danke");
        englishToGerman.put("yes", "ja");
        englishToGerman.put("no", "nein");
        englishToGerman.put("friend", "freund");
        englishToGerman.put("family", "familie");
        englishToGerman.put("water", "wasser");
        englishToGerman.put("food", "essen");
        englishToGerman.put("love", "liebe");
        englishToGerman.put("morning", "morgen");
        englishToGerman.put("night", "nacht");
        englishToGerman.put("sun", "sonne");
        englishToGerman.put("moon", "mond");
        englishToGerman.put("star", "stern");
        englishToGerman.put("happy", "glücklich");
        englishToGerman.put("sad", "traurig");
        englishToGerman.put("cold", "kalt");
        englishToGerman.put("hot", "heiß");
        englishToGerman.put("school", "schule");
        englishToGerman.put("teacher", "lehrer");
        englishToGerman.put("student", "schüler");
        englishToGerman.put("beautiful", "schön");
        englishToGerman.put("ugly", "hässlich");
        englishToGerman.put("car", "auto");
        englishToGerman.put("house", "haus");
        englishToGerman.put("book", "buch");
        englishToGerman.put("pen", "stift");
        englishToGerman.put("paper", "papier");
        englishToGerman.put("computer", "computer");
        englishToGerman.put("city", "stadt");
        englishToGerman.put("village", "dorf");
        englishToGerman.put("river", "fluss");
        englishToGerman.put("mountain", "berg");
        englishToGerman.put("ocean", "ozean");
        englishToGerman.put("father", "vater");
        englishToGerman.put("mother", "mutter");
        englishToGerman.put("child", "kind");
        // Add more words up to 100+...

        // English to Rundi
        englishToRundi.put("hello", "mbote");
        englishToRundi.put("world", "mokili");
        englishToRundi.put("goodbye", "gukira");
        englishToRundi.put("please", "mbega");
        englishToRundi.put("thank you", "urakoze");
        englishToRundi.put("yes", "ego");
        englishToRundi.put("no", "oya");
        englishToRundi.put("friend", "inshuti");
        englishToRundi.put("family", "umuryango");
        englishToRundi.put("water", "amazi");
        englishToRundi.put("food", "ibiryo");
        englishToRundi.put("love", "urukundo");
        englishToRundi.put("morning", "mu gitondo");
        englishToRundi.put("night", "ijoro");
        englishToRundi.put("sun", "izuba");
        englishToRundi.put("moon", "ukwezi");
        englishToRundi.put("star", "inyenyeri");
        englishToRundi.put("happy", "anezerewe");
        englishToRundi.put("sad", "ababaye");
        englishToRundi.put("cold", "gikonje");
        englishToRundi.put("hot", "gishyushye");
        englishToRundi.put("school", "ishuri");
        englishToRundi.put("teacher", "umwarimu");
        englishToRundi.put("student", "umunyeshuri");
        englishToRundi.put("beautiful", "nziza");
        englishToRundi.put("ugly", "bibi");
        englishToRundi.put("car", "imodoka");
        englishToRundi.put("house", "inzu");
        englishToRundi.put("book", "igitabo");
        englishToRundi.put("pen", "ikaramu");
        englishToRundi.put("paper", "urupapuro");
        englishToRundi.put("computer", "mudasobwa");
        englishToRundi.put("city", "umujyi");
        englishToRundi.put("village", "umudugudu");
        englishToRundi.put("river", "uruzi");
        englishToRundi.put("mountain", "umusozi");
        englishToRundi.put("ocean", "inyanja");
        englishToRundi.put("father", "se");
        englishToRundi.put("mother", "nyina");
        englishToRundi.put("child", "umwana");
        // Add more words up to 100+...
    }

    

    public static String translate(String word, String language) {
        if (word == null || language == null) {
            return "Invalid input";
        }

        switch (language.toLowerCase()) {
            case "french":
                return englishToFrench.getOrDefault(word.toLowerCase(), "Unknown");
            case "german":
                return englishToGerman.getOrDefault(word.toLowerCase(), "Unknown");
            case "rundi":
                return englishToRundi.getOrDefault(word.toLowerCase(), "Unknown");
            default:
                return "Language not supported";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Multi-Language Translator!");
        System.out.println("Supported languages: French, German, Rundi");
        
        while (true) {
            System.out.print("\nEnter a word to translate (or type 'exit' to quit): ");
            String word = scanner.nextLine().trim();

            if (word.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            System.out.print("Enter the target language: ");
            String language = scanner.nextLine().trim();

            String translation = translate(word, language);
            System.out.println("Translation: " + translation);
        }

        scanner.close();
    }
}
