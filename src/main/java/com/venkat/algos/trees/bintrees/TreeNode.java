package com.venkat.algos.trees.bintrees;

public class TreeNode<T> {
    public T val;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T val, TreeNode<T> l, TreeNode<T> r) {
        this.val = val;
        this.left = l;
        this.right = r;
    }

    public String toString() {
        return String.format("{%s (l=%s, r=%s)}",
                              val,
                              left != null ? left.val : null,
                              right != null ? right.val : null);
    }
}
