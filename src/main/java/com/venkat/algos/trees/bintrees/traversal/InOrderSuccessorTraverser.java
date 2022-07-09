package com.venkat.algos.trees.bintrees.traversal;

import java.util.function.Consumer;

import com.venkat.algos.trees.bintrees.LinkedTreeNode;

/**
 * Check these https://www.techiedelight.com/set-next-pointer-inorder-successor-binary-tree/
 * 
 * @author vbkomarl
 *
 * @param <T>
 */
public class InOrderSuccessorTraverser<T> implements TreeTraverser<T, LinkedTreeNode<T>> {

    private LinkedTreeNode<T> buildLinks(LinkedTreeNode<T> curr, LinkedTreeNode<T> prev) {
        if (curr == null)
            return prev;

        prev = buildLinks((LinkedTreeNode<T>) curr.left, prev);
        if (prev != null)
            prev.next = curr;
        prev = (LinkedTreeNode<T>) curr;
        return buildLinks((LinkedTreeNode<T>) curr.right, prev);
    }

	@Override
	public void reverseTraverse(LinkedTreeNode<T> root, Consumer<LinkedTreeNode<T>> nullSafeNodeConsumer) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void traverse(LinkedTreeNode<T> root, Consumer<LinkedTreeNode<T>> nodeConsumer) {
        buildLinks(root, null);
        LinkedTreeNode<T> iterNode = null;
        for (LinkedTreeNode<T> leftMost = root;
                 leftMost != null;
                 iterNode = leftMost, leftMost = (LinkedTreeNode<T>) leftMost.left);

        while (iterNode != null) {
        	nodeConsumer.accept(iterNode);
        	iterNode = (LinkedTreeNode<T>) iterNode.next;
        }
    }

    public static void main(String[] args) {
    }

}
