package com.venkat.algos.trees.bintrees.traversal;

import java.util.function.Consumer;

import com.venkat.algos.trees.bintrees.TreeNode;

public class InOrderTraverser<T> implements TreeTraverser<T, TreeNode<T>> {

	@Override
	public void traverse(TreeNode<T> root, Consumer<TreeNode<T>> nullSafeNodeConsumer) {
        if (root != null) {
            traverse(root.left, nullSafeNodeConsumer);
            nullSafeNodeConsumer.accept(root);
            traverse(root.right, nullSafeNodeConsumer);
        }
	}

	@Override
	public void reverseTraverse(TreeNode<T> root, Consumer<TreeNode<T>> nullSafeNodeConsumer) {
        if (root != null) {
            traverse(root.right, nullSafeNodeConsumer);
            nullSafeNodeConsumer.accept(root);
            traverse(root.left, nullSafeNodeConsumer);
        }
	}

}
