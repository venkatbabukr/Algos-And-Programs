package com.venkat.algos.trees.bintrees.traversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import com.venkat.algos.trees.bintrees.BSTBuilder;
import com.venkat.algos.trees.bintrees.TreeNode;

public class PreOrderTraverser<T> implements TreeTraverser<T> {

    private void _traverse(TreeNode<T> root, Consumer<TreeNode<T>> nullSafeNodeConsumer) {
        if (root != null) {
            nullSafeNodeConsumer.accept(root);
            _traverse(root.left, nullSafeNodeConsumer);
            _traverse(root.right, nullSafeNodeConsumer);
        }
    }

    @Override
    public void traverse(TreeNode<T> root, Consumer<TreeNode<T>> nodeConsumer) {
        _traverse(root, Optional
                            .ofNullable(nodeConsumer)
                            .orElse(n -> {}));
    }

    public static void main(String[] args) {
        Integer[] testArr = IntStream.rangeClosed(0, 10).boxed().toArray(Integer[]::new);
        TreeNode<Integer> root = BSTBuilder.getInstance(Integer.class).buildTree(testArr);
        List<TreeNode<Integer>> treeNodesTraversalList = new ArrayList<>(testArr.length);
        TreeTraverser<Integer> traverser = new PreOrderTraverser<>();
        traverser.traverse(root, treeNodesTraversalList::add);
        System.out.format("Preorder traversal of BST %s is %s%n", Arrays.toString(testArr), treeNodesTraversalList);
    }

}
