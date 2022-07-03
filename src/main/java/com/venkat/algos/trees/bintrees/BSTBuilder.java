package com.venkat.algos.trees.bintrees;

import java.util.Arrays;

import com.venkat.utils.ext.ArraysExt;

public class BSTBuilder<T> implements TreeBuilder<T> {

    private static final TreeBuilder INSTANCE = new BSTBuilder<>();

    private TreeNode<T> buildBST(T[] arr, int minIdx, int maxIdx) {
        TreeNode<T> root = null;
        if (minIdx == maxIdx) {
            root = new TreeNode<>(arr[minIdx], null, null);
        } else if (minIdx < maxIdx) {
            int mid = (minIdx + maxIdx) / 2;
            root = new TreeNode<>(arr[mid],
                       buildBST(arr, minIdx, mid - 1),
                       buildBST(arr, mid + 1, maxIdx));
        }
        return root;
    }

    @Override
    public TreeNode<T> buildTree(T[] arr) {
    	TreeNode<T> root = null;
    	if (!ArraysExt.isEmpty(arr)) {
            T[] sortedArr = Arrays.copyOf(arr, arr.length);
            Arrays.sort(sortedArr);
            root = buildBST(sortedArr, 0, sortedArr.length - 1);
        }
        return root;
    }

    @SuppressWarnings("unchecked")
	public static <T> TreeBuilder<T> getInstance(Class<T> clazz) {
        return INSTANCE;
    }

}
