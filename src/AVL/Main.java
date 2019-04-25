package AVL;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("src\\AVL\\pride-and-prejudice.txt", words)) {
//            Collections.sort(words, (a, b) -> -a.compareTo(b));
            System.out.println("Total words: " + words.size());
            long startTime = System.nanoTime();

            BST<String, Integer> mapBST = new BST<>();
            for (String word : words) {
                if (mapBST.contains(word))
                    mapBST.set(word, mapBST.get(word) + 1);
                else
                    mapBST.add(word, 1);
            }
            for (String word : words) {
                mapBST.contains(word);
            }

            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;



            System.out.println("BST: " + time + "s");

            startTime = System.nanoTime();
            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }
            for (String word : words) {
                map.contains(word);
            }
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;
            System.out.println("AVL: " + time + "s");
        }



    }
}
