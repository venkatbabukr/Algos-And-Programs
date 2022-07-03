package com.venkat.algos.trees.bintrees;

import java.util.Comparator;
import java.util.Random;

import com.venkat.utils.ext.ObjectsExt;

public class BSTAlgos<T extends Comparable<T>> {

    public boolean isBST(TreeNode<T> root, Comparator<T> valueComparator) {
        boolean isBST = true;
        if (root != null) {
            isBST = (root.left == null ||
                        (valueComparator.compare(root.val, root.left.val) > 0 &&
                         isBST(root.left, valueComparator))) &&
         		   (root.right == null ||
                        (valueComparator.compare(root.val, root.right.val) <= 0 &&
                         isBST(root.right, valueComparator)));
        }
        return isBST;
    }

    @SuppressWarnings("unchecked")
	public boolean isBST(TreeNode<T> root) {
        return isBST(root, (Comparator<T>) Comparator.naturalOrder());
    }

    public TreeNode<T> search(TreeNode<T> root, T value) {
        TreeNode<T> n = root;
        while (n != null) {
            int matchResult = ObjectsExt.nullSafeCompare(n.val, value);
            if (matchResult == 0) break;
            else if (matchResult > 0) n = n.left;
            else n = n.right;
        }
        return n;
    }

    public TreeNode<T> insert(TreeNode<T> root, T value) {
        TreeNode<T> n = root, insertParent = null;
        int compareResult = 0;
        boolean insertLeft = true;
        while (n != null && compareResult != 0) {
            insertParent = n;
            compareResult = ObjectsExt.nullSafeCompare(n.val, value);
            insertLeft = compareResult < 0;
            n = insertLeft ? n.left : n.right;
        }
        if (insertParent != null) {
            TreeNode<T> newNode = new TreeNode<>(value, null, null);
            if (!insertLeft) {
                newNode.right = insertParent.right;
                insertParent.right = newNode;
            } else {
            	insertParent.left = newNode;
            }
        } else {
        	root = new TreeNode<>(value, null, null);
        }
        return root;
    }

    public static void main(String[] args) {
        Integer[] testArr = new Random().ints(10, 5, 100).sorted().boxed().toArray(Integer[]::new);

        TreeNode<Integer> r1 = BSTBuilder.getInstance(Integer.class).buildTree(testArr);
        TreeNode<Integer> r2 = new BinaryTreeAlgos<Integer>().mirrorBinTree(r1);
        
        BSTAlgos<Integer> testAlgo = new BSTAlgos<>();

        System.out.format("isBST(Tree1)=%s?%n", testAlgo.isBST(r1, Comparator.naturalOrder()));
        System.out.format("isBST(Tree2)=%s?%n", testAlgo.isBST(r2));
    }

}
