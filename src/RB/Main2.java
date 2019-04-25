package RB;

import java.util.ArrayList;
import java.util.Random;

public class Main2 {

    public static void main(String[] args) {

        int n = 20000000;

        Random random = new Random();
        ArrayList<Integer> testData = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            testData.add(random.nextInt(Integer.MAX_VALUE));
        }

        //BST
        long startTime = System.nanoTime();
        BST<Integer, Integer> bst = new BST<>();
        for (Integer testDatum : testData) {
            bst.add(testDatum, null);
        }

        long endTime = System.nanoTime();

        double time =  (endTime - startTime) / 1000000000.0;
        System.out.println("BST:" + time + "s");


        //AVL
        startTime = System.nanoTime();

        AVLTree<Integer, Integer> avl = new AVLTree<>();
        for (Integer testDatum : testData) {
            avl.add(testDatum, null);
        }

        endTime = System.nanoTime();

        time =  (endTime - startTime) / 1000000000.0;
        System.out.println("AVL:" + time + "s");


        //RBT
        startTime = System.nanoTime();

        RBTree<Integer, Integer> rbt = new RBTree<>();
        for (Integer testDatum : testData) {
            rbt.add(testDatum, null);
        }

        endTime = System.nanoTime();

        time =  (endTime - startTime) / 1000000000.0;
        System.out.println("RBT:" + time + "s");


    }

}
