import java.util.Scanner;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph = new Graph(); // Tworzenie obiektu Graph

        Menu.displayMenu(graph, scanner); // Przekazanie obiektu Graph do menu
    }
}