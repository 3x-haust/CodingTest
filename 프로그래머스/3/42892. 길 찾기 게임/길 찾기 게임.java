import java.util.*;
import java.util.stream.*;

class Solution {
    static class Node {
        int x, y, data;
        Node left, right;
        Node(int x, int y, int data) {
            this.x = x; this.y = y; this.data = data;
        }
    }
    
    public int[][] solution(int[][] nodeinfo) {
        Node[] nodes = new Node[nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1);
        }
        
        Arrays.sort(nodes, (a, b) -> a.y == b.y ? a.x - b.x : b.y - a.y);
        
        Node root = nodes[0];
        for (int i = 1; i < nodes.length; i++) {
            insert(root, nodes[i]);
        }
        
        List<Integer> preorder = new ArrayList<>();
        List<Integer> postorder = new ArrayList<>();
        traverse(root, preorder, postorder);
        
        return new int[][] {
            preorder.stream().mapToInt(i -> i).toArray(),
            postorder.stream().mapToInt(i -> i).toArray()
        };
    }
    
    private void insert(Node parent, Node child) {
        if (child.x < parent.x) {
            if (parent.left == null) parent.left = child;
            else insert(parent.left, child);
        } else {
            if (parent.right == null) parent.right = child;
            else insert(parent.right, child);
        }
    }
    
    private void traverse(Node node, List<Integer> pre, List<Integer> post) {
        if (node == null) return;
        pre.add(node.data);
        traverse(node.left, pre, post);
        traverse(node.right, pre, post);
        post.add(node.data);
    }
}
