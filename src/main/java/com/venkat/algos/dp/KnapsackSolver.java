package com.venkat.algos.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class KnapsackSolver {
    public static final class KnapsackItem {
        private int weight;
        private int value;
        
        public KnapsackItem(int w, int v) {
            this.weight = w;
            this.value = v;
        }
        
        public int getWeight() {
            return weight;
        }
        
        public int getValue() {
            return value;
        }
        
        public String toString() {
            return String.format("{w: %d, v: %d}", weight, value);
        }
    }
    
    private KnapsackItem[] sackItems;
    private int maxSackWeight;

    public KnapsackSolver(KnapsackItem[] items, int msw) {
        Objects.requireNonNull(items);
        this.sackItems = items;
        Arrays.sort(this.sackItems, Comparator.comparing(KnapsackItem::getWeight));
        this.maxSackWeight = msw;
    }

    public List<KnapsackItem> solve() {
        if (maxSackWeight < this.sackItems[0].weight) {
            return null;
        }

        int[][] cummValueMatrix = new int[sackItems.length][maxSackWeight + 1]; // col 0 = weight 0...

        // Fill the value for first row with the appropriate sackItem[0]'s value...
        for (int weight = 0 ; weight < sackItems[0].weight ; weight++)
            cummValueMatrix[0][weight] = 0;
        for (int weight = sackItems[0].weight; weight <= maxSackWeight ; weight++)
            cummValueMatrix[0][weight] = sackItems[0].value;

        int itemRow = 1;
        for (; itemRow < sackItems.length && sackItems[itemRow].weight <= maxSackWeight ; itemRow++) {
            cummValueMatrix[itemRow] = Arrays.copyOf(cummValueMatrix[itemRow-1], cummValueMatrix[itemRow-1].length);
            int itemWeight = sackItems[itemRow].weight;
            for (int weight = itemWeight ; weight <= maxSackWeight ; weight++) {
                cummValueMatrix[itemRow][weight] = Math.max(cummValueMatrix[itemRow-1][weight], sackItems[itemRow].value + cummValueMatrix[itemRow-1][weight - itemWeight]);
            }
        }

        // System.out.println("Cumm value matrix:");
        // System.out.println(ArraysExt.to2DString(cummValueMatrix));

        // At the end of this for loop, itemRow - 1 will be the idx of max permissible item!!!
        List<KnapsackItem> knapSack = new ArrayList<>(itemRow - 1);
        int addItemRow, remainingWeight;
        for (addItemRow = itemRow - 1, remainingWeight = maxSackWeight ; remainingWeight > 0 && addItemRow > 0 ; ) {
            if (cummValueMatrix[addItemRow][remainingWeight] == cummValueMatrix[addItemRow - 1][remainingWeight]) {
                // Skip this item...  No value added by this item
                addItemRow--;
            } else {
                // Add this item to kanpsack and reduce fillWeight...
                knapSack.add(sackItems[addItemRow]);
                remainingWeight -= sackItems[addItemRow].weight;
            }
        }
        // This last if condition is required, as the above for loop doesn't check for zero th item...
        if (remainingWeight >= sackItems[0].weight) {
            knapSack.add(sackItems[0]);
        }
        return knapSack;
    }

    /*
     * TODO: Need to fix this... Not working yet!
     */
    public List<KnapsackItem> solveWithSparseCVM() {
        if (maxSackWeight < this.sackItems[0].weight) {
            return null;
        }

        int[][] cummValueMatrix = new int[sackItems.length][maxSackWeight - sackItems[0].weight + 2]; // +2 because col0 is weight 0...

        // Fill the value for first row with the sackItem[0]'s value...
        Arrays.fill(cummValueMatrix[0], sackItems[0].value);
        cummValueMatrix[0][0] = 0;

        int itemRow = 1;
        for (; itemRow < sackItems.length && sackItems[itemRow].weight <= maxSackWeight ; itemRow++) {
            cummValueMatrix[itemRow] = Arrays.copyOf(cummValueMatrix[itemRow-1], cummValueMatrix[itemRow-1].length);
            int relItemWeight = sackItems[itemRow].weight - sackItems[0].weight + 1;
            for (int weight = relItemWeight ; weight <= cummValueMatrix[0].length ; weight++) {
                cummValueMatrix[itemRow][weight] = Math.max(cummValueMatrix[itemRow-1][weight], sackItems[itemRow].value + cummValueMatrix[itemRow-1][weight - relItemWeight]);
            }
        }

        // At the end of this for loop, itemRow - 1 will be the idx of max permissible item!!!
        List<KnapsackItem> knapSack = new ArrayList<>(itemRow - 1);
        int addItemRow, remainingWeight;
        for (addItemRow = itemRow - 1, remainingWeight = maxSackWeight ; remainingWeight > 0 && addItemRow > 0 ; ) {
            if (cummValueMatrix[addItemRow][remainingWeight] == cummValueMatrix[addItemRow - 1][remainingWeight]) {
                // Skip this item... No value added by this item
                addItemRow--;
            } else {
                // Add this item to kanpsack and reduce fillWeight...
                knapSack.add(sackItems[addItemRow]);
                remainingWeight -= sackItems[addItemRow].weight;
            }
        }
        // This last if condition is required, as the above for loop doesn't check for zero th item...
        if (remainingWeight >= sackItems[0].weight) {
            knapSack.add(sackItems[0]);
            remainingWeight -= sackItems[0].weight;
        }
        return knapSack;
    }

    public static void main(String[] args) {
        KnapsackItem[] items = new KnapsackItem[] {
                                   new KnapsackItem(2, 1),
                                   new KnapsackItem(3, 4),
                                   new KnapsackItem(4, 5),
                                   new KnapsackItem(5, 7)
                               };
        int maxSackWeight = 7;
        KnapsackSolver solver = new KnapsackSolver(items, maxSackWeight);
        List<KnapsackItem> sackItems = solver.solve();
        System.out.format("For Items=%s, Max sack weight=%d: Knap sack items=%s, Value=%d, Weight=%d%n",
                              Arrays.toString(items), maxSackWeight, sackItems,
                              sackItems.stream().mapToInt(i -> i.getValue()).sum(),
                              sackItems.stream().mapToInt(i -> i.getWeight()).sum());

        maxSackWeight = 5;
        solver = new KnapsackSolver(items, maxSackWeight);
        sackItems = solver.solve();
        System.out.format("For Items=%s, Max sack weight=%d: Knap sack items=%s, Value=%d, Weight=%d%n",
                              Arrays.toString(items), maxSackWeight, sackItems,
                              sackItems.stream().mapToInt(i -> i.getValue()).sum(),
                              sackItems.stream().mapToInt(i -> i.getWeight()).sum());
    }

}
