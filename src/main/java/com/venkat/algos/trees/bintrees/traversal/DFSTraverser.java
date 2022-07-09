package com.venkat.algos.trees.bintrees.traversal;

import java.util.function.Consumer;

import com.venkat.algos.trees.bintrees.TreeNode;

public class DFSTraverser<T> implements TreeTraverser<T, TreeNode<T>> {

    private InOrderTraverser<T> lot;

    public DFSTraverser() {
        lot = new InOrderTraverser<>();
    }

	@Override
	public void traverse(TreeNode<T> root, Consumer<TreeNode<T>> nullSafeNodeConsumer) {
        lot.traverse(root, nullSafeNodeConsumer);
	}

	@Override
	public void reverseTraverse(TreeNode<T> root, Consumer<TreeNode<T>> nullSafeNodeConsumer) {
        lot.reverseTraverse(root, nullSafeNodeConsumer);
	}

}
