package demo;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("ab");
        list.add("aa");
        list.add("bf");
        list.add("b");
        list.add("ppppp");

        Collections.sort(list);
        System.out.println(list.toString());

        Collections.sort(list, (a, b) -> -a.compareTo(b));
        System.out.println(list.toString());


    }


}
