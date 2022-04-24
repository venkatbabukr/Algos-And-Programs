package com.venkat.algos.trees.bintrees.traversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import com.venkat.algos.trees.bintrees.BSTBuilder;
import com.venkat.algos.trees.bintrees.TreeNode;

public class LevelOrderTraverser<T> implements TreeTraverser<T> {

    @Override
    public void traverse(TreeNode<T> root, Consumer<TreeNode<T>> rootNodeConsumer) {
        List<TreeNode<T>> levelNodesList = Arrays.asList(root);
        while (!levelNodesList.isEmpty()) {
            List<TreeNode<T>> childrenList = new ArrayList<>();
            for (TreeNode<T> node : levelNodesList) {
                if (node != null) {
                    System.out.format("%s\t", node);
                    if (rootNodeConsumer != null) rootNodeConsumer.accept(node);
                    childrenList.add(node.left);
                    childrenList.add(node.right);
                }
            }
            System.out.println();
            levelNodesList = childrenList;
        }
    }

    public static void main(String[] args) {
        Integer[] testArr = IntStream.rangeClosed(0, 10).boxed().toArray(Integer[]::new);
        TreeNode<Integer> root = BSTBuilder.buildBST(testArr);
        TreeTraverser<Integer> traverser = new LevelOrderTraverser<>();
        traverser.traverse(root, null);
    }

}
