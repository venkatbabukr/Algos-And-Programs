package com.venkat.algos.trees.bintrees.traversal;

import java.util.Optional;
import java.util.function.Consumer;

import com.venkat.algos.trees.bintrees.TreeNode;

public interface TreeTraverser<T, TN extends TreeNode<T>> {

    void traverse(TN root, Consumer<TN> nullSafeNodeConsumer);

    void reverseTraverse(TN root, Consumer<TN> nullSafeNodeConsumer);

    default void traverse(TN root, boolean reverseTraverse, Consumer<TN> nodeConsumer) {
        nodeConsumer = Optional.ofNullable(nodeConsumer).orElse(n -> {});
        if (reverseTraverse) {
            reverseTraverse(root, nodeConsumer);
        } else {
            traverse(root, nodeConsumer);
        }
    }

}
