package com.example.arouselsham.pojo.utilities;

import com.example.arouselsham.pojo.model.male.PriceOption;

import java.util.List;
import java.util.Random;

public class Sort {

    public static void bubbleSort(List<PriceOption> arr) {
        int n = arr.size();
        PriceOption temp = new PriceOption();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (arr.get(j - 1).getPriority() > arr.get(j).getPriority()) {
                    //swap elements
                    temp = arr.get(j - 1);
                    arr.set(j - 1, arr.get(j));
                    arr.set(j, temp);

                }

            }
        }

    }

    public static void quickSort(List<PriceOption> options) {
        quickSort(options, 0, options.size() - 1);
    }

    private static void quickSort(List<PriceOption> options, int low, int high) {
        if (low < high + 1) {
            int p = partition(options, low, high);
            quickSort(options, low, p - 1);
            quickSort(options, p + 1, high);
        }
    }

    private static void swap(List<PriceOption> options, int x, int y) {
        PriceOption option = options.get(x);
        options.add(x, options.get(y));
        options.add(y, option);
    }

    private static int getPivot(int low, int high) {
        Random random = new Random();
        return random.nextInt((high - low) + 1) + low;
    }

    private static int partition(List<PriceOption> options, int low, int high) {
        swap(options, low, getPivot(low, high));
        int border = low + 1;
        for (int i = border; i <= high; i++) {
            if (options.get(i).getPriority() < options.get(low).getPriority()) {
                swap(options, i, border++);
            }
        }
        swap(options, low, border - 1);
        return border - 1;
    }
}
