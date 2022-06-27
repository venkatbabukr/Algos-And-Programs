package com.venkat.algos.trees.bintrees.traversal;

import java.util.function.Consumer;

import com.venkat.algos.trees.bintrees.TreeNode;

public class BFSTraverser<T> implements TreeTraverser<T> {

    private LevelOrderTraverser<T> lot;

    public BFSTraverser() {
        lot = new LevelOrderTraverser<>();
    }

    @Override
    public void traverse(TreeNode<T> root, Consumer<TreeNode<T>> nodeConsumer) {
        lot.traverse(root, nodeConsumer);
    }

}
