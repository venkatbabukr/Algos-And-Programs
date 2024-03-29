package com.venkat.algos.trees.bintrees.traversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.venkat.algos.trees.bintrees.BSTBuilder;
import com.venkat.algos.trees.bintrees.TreeNode;

public class LevelOrderTraverser<T> implements TreeTraverser<T, TreeNode<T>> {

    private void _traverse(TreeNode<T> root, boolean reverseTraverse, Consumer<TreeNode<T>> nullsafeNodeConsumer) {
        List<TreeNode<T>> levelNodesList = Arrays.asList(root);
        while (!levelNodesList.isEmpty()) {
            List<TreeNode<T>> childrenList = new ArrayList<>();
            for (TreeNode<T> node : levelNodesList) {
                if (node != null) {
                    System.out.format("%s\t", node);
                    nullsafeNodeConsumer.accept(node);
                    childrenList.add(node.left);
                    childrenList.add(node.right);
                }
            }
            System.out.println();
            if (reverseTraverse) Collections.reverse(childrenList);
            levelNodesList = childrenList;
        }
    }

    private void _traverseJava8(TreeNode<T> root, Consumer<TreeNode<T>> nullsafeNodeConsumer) {
        List<TreeNode<T>> currentLevelNodes = Arrays.asList(root);
        while (!currentLevelNodes.isEmpty()) {
            List<TreeNode<T>> nextLevelNodes = currentLevelNodes.stream()
                                                   .peek(node -> {
                                                       System.out.format("%s\t", node);
                                                       nullsafeNodeConsumer.accept(node);
                                                   })
                                                   .filter(Objects::nonNull)
                                                   .flatMap(n -> Stream.of(n.left, n.right))
                                                   .collect(Collectors.toList());
            System.out.println();
            currentLevelNodes = nextLevelNodes;
        }
    }

    @Override
    public void traverse(TreeNode<T> root, Consumer<TreeNode<T>> nullsafeNodeConsumer) {
        _traverseJava8(root, nullsafeNodeConsumer);
    }

	@Override
	public void reverseTraverse(TreeNode<T> root, Consumer<TreeNode<T>> nullSafeNodeConsumer) {
	}

    public static void main(String[] args) {
        Integer[] testArr = IntStream.rangeClosed(0, 10).boxed().toArray(Integer[]::new);
        TreeNode<Integer> root = BSTBuilder.getInstance(Integer.class).buildTree(testArr);
        TreeTraverser<Integer, TreeNode<Integer>> traverser = new LevelOrderTraverser<>();
        traverser.traverse(root, false, null);
    }

}
