package com.venkat.algos.trees.bintrees;

import java.util.Comparator;
import java.util.Random;

public class BSTAlgos<T> {

    public boolean isBST(TreeNode<T> root, Comparator<T> valueComparator) {
        boolean isBST = true;
        if (root != null) {
            isBST = (root.left == null ||
                        (valueComparator.compare(root.val, root.left.val) > 0 &&
                         isBST(root.left, valueComparator))) &&
         		   (root.right == null ||
                        (valueComparator.compare(root.val, root.right.val) < 0 &&
                         isBST(root.right, valueComparator)));
        }
        return isBST;
     }

    public static void main(String[] args) {
        Integer[] testArr = new Random().ints(10, 5, 100).sorted().boxed().toArray(Integer[]::new);

        TreeNode<Integer> r1 = BSTBuilder.buildBST(testArr);
        TreeNode<Integer> r2 = new BinaryTreeAlgos<Integer>().mirrorBinTree(r1);
        
        BSTAlgos<Integer> testAlgo = new BSTAlgos<>();

        System.out.format("isBST(Tree1)=%s?%n", testAlgo.isBST(r1, Comparator.naturalOrder()));
        System.out.format("isBST(Tree2)=%s?%n", testAlgo.isBST(r2, Comparator.naturalOrder()));
    }

}
