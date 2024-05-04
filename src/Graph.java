import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Graph {
    private ArrayList<ArrayList<Edge>> adjacencyList; // Lista sąsiedztwa

    private boolean graphLoaded = false; // Zmienna przechowująca informację o wczytanym grafie

    public boolean isGraphLoaded() {
        return graphLoaded;
    }


    public void generateRandomGraphToFile(int numVertices, int numEdges, String filename) {
        try {
            FileWriter writer = new FileWriter(filename);

            // Wygeneruj unikalne krawędzie
            Set<String> edges = generateUniqueEdges(numVertices, numEdges);

            // Zapisz liczbę krawędzi i wierzchołków do pliku
            writer.write(numEdges + " " + numVertices + "\n");

            // Zapisz krawędzie do pliku
            for (String edge : edges) {
                writer.write(edge + "\n");
            }

            writer.close();
            System.out.println("Graf został wygenerowany i zapisany do pliku " + filename);
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisywania pliku: " + e.getMessage());
        }
    }

    private Set<String> generateUniqueEdges(int numVertices, int numEdges) {
        Random random = new Random();
        Set<String> edges = new HashSet<>();

        while (edges.size() < numEdges) {
            int startVertex = random.nextInt(numVertices);
            int endVertex = random.nextInt(numVertices);
            int weight = random.nextInt(100); // Zakładam, że waga krawędzi będzie losowa z zakresu 0-99

            // Unikalność krawędzi - nie dodawaj krawędzi, jeśli już istnieje
            if (startVertex != endVertex) {
                edges.add(startVertex + " " + endVertex + " " + weight);
            }
        }

        return edges;
    }



    public void readGraphFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            // Wczytaj liczbę krawędzi i wierzchołków
            int numEdges = scanner.nextInt();
            int numVertices = scanner.nextInt();

            // Inicjalizuj listę sąsiedztwa
            adjacencyList = new ArrayList<>();
            for (int i = 0; i < numVertices; i++) {
                adjacencyList.add(new ArrayList<>());
            }

            // Wczytaj krawędzie
            for (int i = 0; i < numEdges; i++) {
                int startVertex = scanner.nextInt();
                int endVertex = scanner.nextInt();
                int weight = scanner.nextInt();

                // Dodaj krawędź do listy sąsiedztwa
                adjacencyList.get(startVertex).add(new Edge(endVertex, weight));

                // Dla problemu MST, dodaj także odwrotną krawędź
                adjacencyList.get(endVertex).add(new Edge(startVertex, weight));
            }

            System.out.println("Graf został wczytany pomyślnie.");
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku: " + e.getMessage());
        }
    }

    public void displayGraph() {
        if (adjacencyList == null) {
            System.out.println("Graf nie został wczytany.");
            return;
        }

        System.out.println("Graf:");
        for (int i = 0; i < adjacencyList.size(); i++) {
            System.out.print(i + ": ");
            for (Edge edge : adjacencyList.get(i)) {
                System.out.print("(" + edge.getEndVertex() + ", " + edge.getWeight() + ") ");
            }
            System.out.println();
        }
    }


    public void displayGraphAsList() {
        if (adjacencyList == null) {
            System.out.println("Graf nie został wczytany.");
            return;
        }

        System.out.println("Graf listowo:");
        for (int i = 0; i < adjacencyList.size(); i++) {
            System.out.print(i + ": ");
            for (Edge edge : adjacencyList.get(i)) {
                System.out.print("(" + edge.getEndVertex() + ", " + edge.getWeight() + ") ");
            }
            System.out.println();
        }
    }

    public void displayGraphAsMatrix() {
        if (adjacencyList == null) {
            System.out.println("Graf nie został wczytany.");
            return;
        }

        System.out.println("Graf macierzowo:");
        int numVertices = adjacencyList.size();
        int[][] adjacencyMatrix = new int[numVertices][numVertices];

        // Wypełnij macierz sąsiedztwa
        for (int i = 0; i < numVertices; i++) {
            for (Edge edge : adjacencyList.get(i)) {
                adjacencyMatrix[i][edge.getEndVertex()] = edge.getWeight();
            }
        }

        // Wyświetl macierz sąsiedztwa
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    private class Edge {
        private int endVertex;
        private int weight;

        public Edge(int endVertex, int weight) {
            this.endVertex = endVertex;
            this.weight = weight;
        }

        public int getEndVertex() {
            return endVertex;
        }

        public int getWeight() {
            return weight;
        }
    }

    public void createNewGraph() {
        // Tutaj dodaj kod do utworzenia nowego grafu
    }




    public void saveGraphToFile(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);

            // Tutaj dodaj kod do zapisu grafu do pliku tekstowego

            writer.close();
            System.out.println("Graf został zapisany do pliku " + filename);
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisywania pliku: " + e.getMessage());
        }
    }
}
