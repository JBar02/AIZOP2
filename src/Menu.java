import java.util.Scanner;

public class Menu {
    public static void displayMenu(Graph graph, Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("=== Menu ===");
            System.out.println("1. Stwórz graf");
            System.out.println("2. Odczytaj graf");
            System.out.println("3. Zapisz graf do pliku txt");
            System.out.println("4. Wyświetl graf listowo");
            System.out.println("5. Wyświetl graf macierzowo");
            System.out.println("6. Algorytm Kruskala");
            System.out.println("7. Algorytm Dijkstry");
            System.out.println("8. Wyjdź");

            System.out.print("Wybierz opcję: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    generateGraphMenu(graph, scanner);
                    break;
                case 2:
                    readGraphMenu(graph, scanner);
                    break;
                case 3:
                    saveGraphMenu(graph, scanner);
                    break;
                case 4:
                    if (graph.isGraphLoaded()) {
                        graph.displayGraphAsList();
                    } else {
                        System.out.println("Graf nie został wczytany.");
                    }
                    break;
                case 5:
                    if (graph.isGraphLoaded()) {
                        graph.displayGraphAsMatrix();
                    } else {
                        System.out.println("Graf nie został wczytany.");
                    }
                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:
                    exit = true;
                    System.out.println("Koniec programu.");
                    break;
                default:
                    System.out.println("Nieprawidłowa opcja. Wybierz ponownie.");
            }
        }
    }

    private static void generateGraphMenu(Graph graph, Scanner scanner) {
        System.out.print("Podaj liczbę wierzchołków grafu: ");
        int numVertices = scanner.nextInt();
        System.out.print("Podaj liczbę krawędzi grafu: ");
        int numEdges = scanner.nextInt();
        System.out.print("Podaj nazwę pliku do zapisu: ");
        String filename = scanner.next();
        graph.generateRandomGraphToFile(numVertices, numEdges, filename);
    }

    private static void readGraphMenu(Graph graph, Scanner scanner) {
        System.out.print("Podaj nazwę pliku do odczytu: ");
        String filename = scanner.next();
        graph.readGraphFromFile(filename);
    }

    private static void saveGraphMenu(Graph graph, Scanner scanner) {
        System.out.print("Podaj nazwę pliku do zapisu: ");
        String filename = scanner.next();
        graph.saveGraphToFile(filename);
    }
}
