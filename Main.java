package codeeval_StringPermutations;

/*
 * Copyright (C) 2015 hankoor
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    private static ArrayList<String> al;
    private static String curStr;

    private static void loadFile(String fileName) {
        File file = new File(fileName);
        if (!file.canRead() || !file.isFile()) {
            System.exit(0);
        }
        BufferedReader in = null;
        String row;
        try {
            in = new BufferedReader(new FileReader(fileName));
            while ((row = in.readLine()) != null) {
                al = new ArrayList<>();
                curStr = "";
                permute(row.toCharArray(), row.length());
                printArrayList(al);
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ioEx) {
                    ioEx.printStackTrace();
                }
            }
        }
    }

    private static void printArrayList(ArrayList al) {
        Collections.sort(al);
        for (int i = 0; i < al.size(); i++) {
            if (i < (al.size() - 1)) {
                System.out.print(al.get(i) + ",");
            }
            if (i == (al.size() - 1)) {
                System.out.print(al.get(i) + "\n");
            }
        }
    }

    /**
     *
     * @param v
     * @param i
     * @param j
     */
    private static void swap(char[] v, int i, int j) {
        char t = v[i];
        v[i] = v[j];
        v[j] = t;
    }
    /*
     I used Heap's algorithm.
     Have a look @wikipedia for details.
     */

    /**
     *
     * @param v
     * @param n
     */
    public static void permute(char[] v, int n) {
        if (n == 1) {
            for (int j = 0; j < v.length; j++) {
                if (j < (v.length - 1)) {
                    curStr += v[j];
                }
                if (j == (v.length - 1)) {
                    curStr += v[j];
                    al.add(curStr);
                    curStr = "";
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                permute(v, n - 1);
                if (n % 2 == 1) {
                    swap(v, 0, n - 1);
                } else {
                    swap(v, i, n - 1);
                }
            }
        }
    }

    public static void main(String[] pathToFile) {
        loadFile(pathToFile[0]);
    }
}
