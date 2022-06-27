package com.venkat.algos.trees.bintrees;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import com.venkat.utils.Pair;
import com.venkat.utils.ext.ObjectsExt;

public class BinaryTreeAlgos<T> {

    public Pair<Long> depth(TreeNode<T> root) {
        Pair<Long> depthWidth = new Pair<>(0L, 0L);
        if (root != null) {
            Long depth = 0L;
            Long width = 0L;
            Long w = 0L;
            Queue<TreeNode<T>> levelQueue = new LinkedList<>(Arrays.asList(root, null));
            while (!levelQueue.isEmpty()) {
                TreeNode<T> node = levelQueue.remove();
                if (node == null) {
                	depth++;
                	width = Math.max(width, w);
                	w = 0L;
                }
            }
        }
        return depthWidth;
    }

    public TreeNode<T> mirrorBinTree(TreeNode<T> root) {
        TreeNode<T> mirrorRoot = null;
        if (root != null) {
            mirrorRoot = new TreeNode<T>(root.val,
                                            mirrorBinTree(root.right),
                                            mirrorBinTree(root.left));
        }
        return mirrorRoot;
    }

    /**
     * Logic from - https://www.geeksforgeeks.org/iterative-function-check-two-trees-identical/
     * 
     * @param r1
     * @param r2
     * @return
     */
    public boolean isIdentical(TreeNode<T> r1, TreeNode<T> r2) {
        boolean treesIdentical = true;
        if (r1 != null && r2 != null) {
            Queue<TreeNode<T>> t1Queue = new LinkedList<>();
            Queue<TreeNode<T>> t2Queue = new LinkedList<>();
            t1Queue.add(r1);
            t2Queue.add(r2);
            while (treesIdentical && !t1Queue.isEmpty() && !t2Queue.isEmpty()) {
                TreeNode<T> t1Node = t1Queue.remove();
                TreeNode<T> t2Node = t2Queue.remove();

                treesIdentical = ObjectsExt.nullSafeEquals(t1Node.val, t2Node.val);
                if (treesIdentical) {
                    if (t1Node.left != null && t2Node.left != null) {
                        t1Queue.add(t1Node.left);
                        t2Queue.add(t2Node.left);
                    } else if (t1Node.left != null || t2Node.left != null)
                        treesIdentical = false;
                    if (t1Node.right != null && t2Node.right != null) {
                        t1Queue.add(t1Node.right);
                        t2Queue.add(t2Node.right);
                    } else if (t1Node.right != null || t2Node.right != null)
                        treesIdentical = false;
                }
            }
            treesIdentical &= t1Queue.isEmpty() && t2Queue.isEmpty();
        } else {
            treesIdentical = r1 == null && r2 == null;
        }
        return treesIdentical;
    }

    /**
     * Logic from - https://www.geeksforgeeks.org/iterative-function-check-two-trees-identical/
     * 
     * @param r1
     * @param r2
     * @return
     */
    public boolean isMirrorIdentical(TreeNode<T> r1, TreeNode<T> r2) {
        boolean treesIdentical = true;
        if (r1 != null && r2 != null) {
            Queue<TreeNode<T>> t1Queue = new LinkedList<>();
            Queue<TreeNode<T>> t2Queue = new LinkedList<>();
            t1Queue.add(r1);
            t2Queue.add(r2);
            while (treesIdentical && !t1Queue.isEmpty() && !t2Queue.isEmpty()) {
                TreeNode<T> t1Node = t1Queue.remove();
                TreeNode<T> t2Node = t2Queue.remove();

                treesIdentical = ObjectsExt.nullSafeEquals(t1Node.val, t2Node.val);
                if (treesIdentical) {
                    if (t1Node.left != null && t2Node.right != null) {
                        t1Queue.add(t1Node.left);
                        t2Queue.add(t2Node.right);
                    } else if (t1Node.left != null || t2Node.right != null)
                        treesIdentical = false;
                    if (t1Node.right != null && t2Node.left != null) {
                        t1Queue.add(t1Node.right);
                        t2Queue.add(t2Node.left);
                    } else if (t1Node.right != null || t2Node.left != null)
                        treesIdentical = false;
                }
            }
            treesIdentical &= t1Queue.isEmpty() && t2Queue.isEmpty();
        } else {
            treesIdentical = r1 == null && r2 == null;
        }
        return treesIdentical;
    }

    public static void main(String[] args) {
        BinaryTreeAlgos<Integer> testAlgo = new BinaryTreeAlgos<>();

        Integer[] testArr = new Random().ints(10, 5, 100).sorted().boxed().toArray(Integer[]::new);
        TreeNode<Integer> r1 = BSTBuilder.buildBST(testArr);

        TreeNode<Integer> r2 = testAlgo.mirrorBinTree(r1);
        System.out.format("Tree1=%s%n", r1.toDeepString());
        System.out.format("Tree2 (Tree1 Mirror)=%s%n%n", r2.toDeepString());
        System.out.format("isMirrorIdentical(Tree1, Tree2)=%s?%n", testAlgo.isMirrorIdentical(r1, r2));
    }

}
