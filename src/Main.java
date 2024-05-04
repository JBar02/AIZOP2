import java.util.Scanner;

    public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in); // Utwórz obiekt Scanner
            Graph graph = new Graph(); // Utwórz obiekt Graph
            Menu.displayMenu(graph, scanner); // Przekaż obiekt Graph i obiekt Scanner do metody displayMenu
            scanner.close(); // Zamknij obiekt Scanner po zakończeniu jego użycia
        }
    }