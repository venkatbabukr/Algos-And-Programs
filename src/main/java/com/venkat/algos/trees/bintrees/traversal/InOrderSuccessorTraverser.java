package com.venkat.algos.trees.bintrees.traversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import com.venkat.algos.trees.bintrees.BSTBuilder;
import com.venkat.algos.trees.bintrees.TreeNode;

public class InOrderSuccessorTraverser<T> implements TreeTraverser<T> {

    public static class InOrderSuccessorBuilder<T> {

        private void _populateInOrderSuccessors(TreeNode<T> root, TreeNode<T> inorderNextNode) {
            if (root.left != null) {
                _populateInOrderSuccessors(root.left, root);
            }
            if (root.right == null) {
                root.right = inorderNextNode;
            } else {
                _populateInOrderSuccessors(root.right, inorderNextNode);
            }
        }

        public void buildInOrderSuccessors(TreeNode<T> root) {
            if (root == null) return;
            _populateInOrderSuccessors(root, null);
        }

    }

    @Override
    public void traverse(TreeNode<T> root, Consumer<TreeNode<T>> nodeConsumer) {
        if (root != null) {
            new InOrderSuccessorBuilder<T>().buildInOrderSuccessors(root);
            TreeNode<T> iterNode = null;
            for (iterNode = root; iterNode.left != null; iterNode = iterNode.left);
            for (; iterNode != null; iterNode = iterNode.right) {
                nodeConsumer.accept(iterNode);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] testArr = IntStream.rangeClosed(0, 10).boxed().toArray(Integer[]::new);
        TreeNode<Integer> root = BSTBuilder.getInstance(Integer.class).buildTree(testArr);
        List<TreeNode<Integer>> treeNodesTraversalList = new ArrayList<>(testArr.length);
        TreeTraverser<Integer> traverser = new InOrderTraverser<>();
        traverser.traverse(root, treeNodesTraversalList::add);
        System.out.format("InOrder traversal of BST %s is %s%n", Arrays.toString(testArr), treeNodesTraversalList);

        treeNodesTraversalList.clear();
        traverser = new InOrderSuccessorTraverser<>();
        traverser.traverse(root, treeNodesTraversalList::add);
        System.out.format("InOrderSuccessor traversal of BST %s is %s%n", Arrays.toString(testArr), treeNodesTraversalList);
    }

}
