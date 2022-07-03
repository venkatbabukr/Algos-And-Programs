package com.venkat.algos.trees.bintrees.traversal;

import java.util.Optional;
import java.util.function.Consumer;

import com.venkat.algos.trees.bintrees.TreeNode;

public class InOrderTraverser<T> implements TreeTraverser<T> {

    private void _traverse(TreeNode<T> root, Consumer<TreeNode<T>> nullSafeNodeConsumer) {
        if (root != null) {
            _traverse(root.left, nullSafeNodeConsumer);
            nullSafeNodeConsumer.accept(root);
            _traverse(root.right, nullSafeNodeConsumer);
        }
    }

    @Override
    public void traverse(TreeNode<T> root, Consumer<TreeNode<T>> nodeConsumer) {
        _traverse(root, Optional
                .ofNullable(nodeConsumer)
                .orElse(n -> {}));
    }

}
