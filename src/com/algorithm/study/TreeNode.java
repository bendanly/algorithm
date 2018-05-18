package com.algorithm.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * 广度优先遍历，2018试题
 * @author liyang
 *
 */
public class TreeNode {
	String str;
	List<TreeNode> childNodeList;
	public static void traverse(TreeNode root) {
		List<TreeNode> list = new ArrayList<>();
		list.add(root);
		for (int i = 0; i < list.size(); i++) {
			TreeNode node = list.get(i);
			if (node != null) {
				System.out.println(node.str);
				for (TreeNode childNode : node.childNodeList) {
					list.add(childNode);
				}
			}
		}
	}

	public TreeNode(String str, TreeNode parentNode) {
		this.str = str;
		childNodeList = new ArrayList<TreeNode>();
		if (parentNode != null) {
			parentNode.childNodeList.add(this);
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode("1", null);
		TreeNode root1 = new TreeNode("2", root);
		TreeNode root2 = new TreeNode("3", root);
		TreeNode root3 = new TreeNode("4", root2);
		TreeNode root4 = new TreeNode("5", root1);
		TreeNode root5 = new TreeNode("6", root1);
		TreeNode root6 = new TreeNode("7", root2);
		TreeNode root7 = new TreeNode("8", root3);
		TreeNode root8 = new TreeNode("9", root1);
		TreeNode root9 = new TreeNode("10", root4);
		TreeNode root10 = new TreeNode("11", root4);
		TreeNode root11 = new TreeNode("12", root6);
		TreeNode root12 = new TreeNode("13", root5);
		TreeNode root13 = new TreeNode("14", root8);
		TreeNode.traverse(root);
	}

};
