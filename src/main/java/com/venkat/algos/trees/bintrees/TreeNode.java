package com.venkat.algos.trees.bintrees;

import java.util.function.Supplier;

public class TreeNode<T> implements Cloneable {
    public T val;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode() {
    	val = null;
        left = right = null;
    }

    public TreeNode(T val, TreeNode<T> l, TreeNode<T> r) {
        this.val = val;
        this.left = l;
        this.right = r;
    }

    protected TreeNode<T> _cloneBinTree(TreeNode<T> root, Supplier<TreeNode<T>> newTreeNodeSupplier) {
        TreeNode<T> cloneRoot = null;
        if (root != null) {
            cloneRoot = newTreeNodeSupplier.get();
            cloneRoot.val = root.val;
            cloneRoot.left = _cloneBinTree(root.left, newTreeNodeSupplier);
            cloneRoot.right = _cloneBinTree(root.right, newTreeNodeSupplier);
        }
        return cloneRoot;
    }

    public Object clone() {
		return _cloneBinTree(this, TreeNode::new);
    }

    public String toString() {
        return String.format("{%s (l=%s, r=%s)}",
                              val,
                              left != null ? left.val : null,
                              right != null ? right.val : null);
    }

    public String toDeepString() {
        return String.format("{%s (%s, %s)}",
                              val,
                              left != null ? left.toDeepString() : null,
                              right != null ? right.toDeepString() : null);
    }
}
