import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Graph {
    private List<List<Edge>> adjacencyList;
    private boolean graphLoaded = false;
    private int numVertices;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
    }

    public Graph() {
        // Nie ustawiamy liczby wierzchołków
    }

    public boolean isGraphLoaded() {
        return graphLoaded;
    }

    public int getNumberOfVertices() {
        return numVertices;
    }

    public void addEdge(int startVertex, int endVertex, int weight) {
        if (adjacencyList == null) {
            adjacencyList = new ArrayList<>();
            for (int i = 0; i < numVertices; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }
        adjacencyList.get(startVertex).add(new Edge(startVertex, endVertex, weight));
    }

    public void clear() {
        if (adjacencyList != null) {
            adjacencyList.clear();
        }
    }

    public void generateRandomGraphToFile(int numVertices, int numEdges, String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            Set<String> edges = generateUniqueEdges(numVertices, numEdges);
            writer.write(numEdges + " " + numVertices + "\n");
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
            if (startVertex == endVertex) continue;
            int weight = random.nextInt(100);
            edges.add(startVertex + " " + endVertex + " " + weight);
        }
        return edges;
    }

    public void readGraphFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            int numEdges = scanner.nextInt();
            int numVertices = scanner.nextInt();
            adjacencyList = new ArrayList<>(numVertices);
            for (int i = 0; i < numVertices; i++) {
                adjacencyList.add(new ArrayList<>());
            }
            for (int i = 0; i < numEdges; i++) {
                int startVertex = scanner.nextInt();
                int endVertex = scanner.nextInt();
                int weight = scanner.nextInt();
                adjacencyList.get(startVertex).add(new Edge(startVertex, endVertex, weight));
                adjacencyList.get(endVertex).add(new Edge(endVertex, startVertex, weight));
            }
            graphLoaded = true;
            System.out.println("Graf został wczytany pomyślnie.");
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku: " + e.getMessage());
        }
    }

    public List<Edge> getAdjacencyList(int vertex) {
        if (adjacencyList == null || vertex < 0 || vertex >= adjacencyList.size()) {
            return null;
        }
        return adjacencyList.get(vertex);
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
        for (int i = 0; i < numVertices; i++) {
            for (Edge edge : adjacencyList.get(i)) {
                adjacencyMatrix[i][edge.getEndVertex()] = edge.getWeight();
            }
        }
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static class Edge implements Comparable<Edge> {
        private int startVertex;
        private int endVertex;
        private int weight;

        public Edge(int startVertex, int endVertex, int weight) {
            this.startVertex = startVertex;
            this.endVertex = endVertex;
            this.weight = weight;
        }

        public int getStartVertex() {
            return startVertex;
        }

        public int getEndVertex() {
            return endVertex;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public void saveGraphToFile(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(adjacencyList.size() + " " + getNumEdges() + "\n");
            for (int i = 0; i < adjacencyList.size(); i++) {
                for (Edge edge : adjacencyList.get(i)) {
                    writer.write(i + " " + edge.getEndVertex() + " " + edge.getWeight() + "\n");
                }
            }
            writer.close();
            System.out.println("Graf został zapisany do pliku " + filename);
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisywania pliku: " + e.getMessage());
        }
    }

    public int getNumEdges() {
        int numEdges = 0;
        for (List<Edge> edges : adjacencyList) {
            numEdges += edges.size();
        }
        return numEdges / 2;
    }
}
