package RB;

import hashTable.HashTable;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("src\\AVL\\pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            // Collections.sort(words);

            // Test BST
            long startTime = System.nanoTime();

            BST<String, Integer> bst = new BST<>();
            for (String word : words) {
                if (bst.contains(word))
                    bst.set(word, bst.get(word) + 1);
                else
                    bst.add(word, 1);
            }

            for(String word: words)
                bst.contains(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("BST: " + time + " s");


            // Test AVL
            startTime = System.nanoTime();

            AVLTree<String, Integer> avl = new AVLTree<>();
            for (String word : words) {
                if (avl.contains(word))
                    avl.set(word, avl.get(word) + 1);
                else
                    avl.add(word, 1);
            }

            for(String word: words)
                avl.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("AVL: " + time + " s");


            // Test RB
            startTime = System.nanoTime();

            RBTree<String, Integer> rb = new RBTree<>();
            for (String word : words) {
                if (rb.contains(word))
                    rb.set(word, rb.get(word) + 1);
                else
                    rb.add(word, 1);
            }

            for(String word: words)
                rb.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("rb: " + time + " s");


            // Test hashTable
            startTime = System.nanoTime();

            HashTable<String, Integer> hashTable = new HashTable<>(131071);
            for (String word : words) {
                if (hashTable.contains(word))
                    hashTable.set(word, hashTable.get(word) + 1);
                else
                    hashTable.add(word, 1);
            }

            for(String word: words)
                hashTable.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("hash: " + time + " s");
        }

        System.out.println();
    }
}
