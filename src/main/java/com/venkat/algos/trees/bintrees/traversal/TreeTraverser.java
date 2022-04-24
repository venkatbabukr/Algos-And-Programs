package com.venkat.algos.trees.bintrees.traversal;

import java.util.function.Consumer;

import com.venkat.algos.trees.bintrees.TreeNode;

public interface TreeTraverser<T> {

    void traverse(TreeNode<T> root, Consumer<TreeNode<T>> rootNodeConsumer);

}
