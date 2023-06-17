package com.venkat.algos.trees.bintrees.traversal;

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

public class ZigZagTraverser<T> implements TreeTraverser<T, TreeNode<T>> {

    @Override
    public void traverse(TreeNode<T> root, Consumer<TreeNode<T>> nullsafeNodeConsumer) {
        if (root != null) {
            List<TreeNode<T>> currentLevelList = Arrays.asList(root);
            boolean zagTraverse = false;
            while (!currentLevelList.isEmpty()) {
            	List<TreeNode<T>> nextLevelList = currentLevelList.stream()
            			.peek(node -> {
                            System.out.format("%s\t", node);
                            nullsafeNodeConsumer.accept(node);
            			})
            			.flatMap(node -> Stream.of(node.right, node.left))
            			.filter(Objects::nonNull)
            			.collect(Collectors.toList());
            	System.out.println();
            	if (!zagTraverse) {
            		Collections.reverse(nextLevelList);
            	}
            	currentLevelList = nextLevelList;
            	zagTraverse = !zagTraverse;
            }
        }
    }

	@Override
	public void reverseTraverse(TreeNode<T> root, Consumer<TreeNode<T>> nullSafeNodeConsumer) {
		// TODO Auto-generated method stub
		
	}

    public static void main(String[] args) {
        Integer[] testArr = IntStream.rangeClosed(0, 10).boxed().toArray(Integer[]::new);
        TreeNode<Integer> root = BSTBuilder.getInstance(Integer.class).buildTree(testArr);
        TreeTraverser<Integer, TreeNode<Integer>> traverser = new ZigZagTraverser<>();
        traverser.traverse(root, false, null);
    }

}
