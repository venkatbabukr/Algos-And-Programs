package com.venkat.algos.trees.bintrees.traversal;

import java.util.function.Consumer;

import com.venkat.algos.trees.bintrees.TreeNode;

public class DFSTraverser<T> implements TreeTraverser<T> {

    private InOrderTraverser<T> lot;

    public DFSTraverser() {
        lot = new InOrderTraverser<>();
    }

    @Override
    public void traverse(TreeNode<T> root, Consumer<TreeNode<T>> nodeConsumer) {
        lot.traverse(root, nodeConsumer);
    }

}
