package com.venkat.algos.trees.bintrees.traversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import com.venkat.algos.trees.bintrees.BSTBuilder;
import com.venkat.algos.trees.bintrees.TreeNode;

public class ZigZagTraverser<T> implements TreeTraverser<T, TreeNode<T>> {

    @Override
    public void traverse(TreeNode<T> root, Consumer<TreeNode<T>> nullsafeNodeConsumer) {
        if (root != null) {
            List<TreeNode<T>> currentLevelList = Arrays.asList(root);
            boolean zagTraverse = false;
            while (!currentLevelList.isEmpty()) {
                List<TreeNode<T>> nextLevelList = new ArrayList<>(currentLevelList.size() * 2);
                for (ListIterator<TreeNode<T>> iter = currentLevelList.listIterator(currentLevelList.size());
                        iter.hasPrevious();) {
                    TreeNode<T> node = iter.previous();
                    if (node != null) {
                        System.out.format("%s\t", node);
                        nullsafeNodeConsumer.accept(node);
                        if (zagTraverse) {
                    	    nextLevelList.add(node.right);
                    	    nextLevelList.add(node.left);
                        } else {
                    	    nextLevelList.add(node.left);
                    	    nextLevelList.add(node.right);
                        }
                    }
                }
                System.out.println();
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
