package com.venkat.algos.trees.bintrees;

public class BSTBuilder {

    private static <T> TreeNode<T> buildBST(T[] sortedArr, int minIdx, int maxIdx) {
        TreeNode<T> root = null;
        if (minIdx == maxIdx) {
            root = new TreeNode<>(sortedArr[minIdx], null, null);
        } else if (minIdx < maxIdx) {
            int mid = (minIdx + maxIdx) / 2;
            root = new TreeNode<>(sortedArr[mid],
                       buildBST(sortedArr, minIdx, mid - 1),
                       buildBST(sortedArr, mid + 1, maxIdx));
        }
        return root;
    }

    public static <T> TreeNode<T> buildBST(T[] sortedArr) {
        return sortedArr != null ? buildBST(sortedArr, 0, sortedArr.length - 1) : null;
    }

}
