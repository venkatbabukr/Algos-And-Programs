package com.venkat.algos.trees.bintrees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.venkat.utils.ext.ArraysExt;

public class SumTreeBuilder implements TreeBuilder<Integer> {

    @Override
    public TreeNode<Integer> buildTree(Integer[] arr) {
        TreeNode<Integer> root = null;
        if (!ArraysExt.isEmpty(arr)) {
            List<TreeNode<Integer>> currentNodes = Arrays.stream(arr)
                                                            .map(val -> new TreeNode<Integer>(val, null, null))
                                                            .collect(Collectors.toList());
            while (currentNodes.size() > 1) {
                List<TreeNode<Integer>> parentNodes = new ArrayList<>();
                for (Iterator<TreeNode<Integer>> iter = currentNodes.iterator();
                       iter.hasNext();) {
                    TreeNode<Integer> l = iter.next();
                    TreeNode<Integer> r = iter.hasNext() ? iter.next() : null;
                    parentNodes.add(new TreeNode<Integer>(l.val + r.val, l, r));
                }
                currentNodes = parentNodes;
            }
            root = currentNodes.get(0);
        }
        return root;
    }

}
