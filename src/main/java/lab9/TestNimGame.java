package lab9;

import java.util.List;

public class TestNimGame {
    public static void main(String[] args) {
        Node rootNode = new Node();
        rootNode.addAll(List.of(1, 2, 4));

        int minimaxValue = rootNode.minimax(Integer.MAX_VALUE, true);
        System.out.println("Minimax Value for the root node: " + minimaxValue);

        Node bestMoveForMin = rootNode.bestMoveForMin();
        System.out.println("Best Move for MIN:" + bestMoveForMin);

        Node rootNode8 = new Node();
        rootNode8.addAll(List.of(1, 2, 5));

        int minimaxValue8 = rootNode8.minimax(Integer.MAX_VALUE, true);
        System.out.println("Minimax Value for the root node (8 tokens): " + minimaxValue8);


        Node bestMoveForMin8 = rootNode8.bestMoveForMin();
        System.out.println("Best Move for MIN (8 tokens): " + bestMoveForMin8);

        // Task 5: Test with 9 tokens
        Node rootNode9 = new Node();
        rootNode9.addAll(List.of(2, 3, 4));

        int minimaxValue9 = rootNode9.minimax(Integer.MAX_VALUE, true);
        System.out.println("Minimax Value for the root node (9 tokens): " + minimaxValue9);

        Node bestMoveForMin9 = rootNode9.bestMoveForMin();
        System.out.println("Best Move for MIN (9 tokens): " + bestMoveForMin9);
    }
}
