package cn.intv;

public class AMainTest {
    public static void main(String[] args) {
        String[] words = {"cat", "banana", "dog", "nana", "walk", "walker", "dogwalker"};

        TrieTree tree = new TrieTree();
        for (String word : words) {
            tree.insert(word.toCharArray());
        }

        String res = "";
        for (String word : words) {
            if (tree.find(word.toCharArray(), 0, 0)) {
                if (word.length() > res.length() || (word.length() == res.length() && word.compareTo(res) < 0)) {
                    res = word;
                }
            }
        }

        System.out.println(res);
    }
}

class TrieTree {
    public Node root;

    public TrieTree() {
        this.root = new Node();
    }

    public void insert(char[] word) {
        Node temp = this.root;
        for (int i = 0; i < word.length; i++) {
            int idx = word[i] - 'a';
            if (temp.next[idx] == null) {
                temp.next[idx] = new Node();
            }
            temp = temp.next[idx];
        }
        temp.isEnd = true;
    }

    public boolean find(char[] word, int start, int flag) {
        //flag:word中是否已经出现过一个其他单词了
        if (start == word.length) {
            return flag > 1;
        }
        
        Node temp = this.root;
        if (temp.next[word[start] - 'a'] == null) {
            return false;
        }

        for (int i = start; i < word.length; i++) {
            temp = temp.next[word[i] - 'a'];
            if (temp == null) {
                return false;
            }

            if (temp.isEnd) {
                if (find(word, i + 1, flag + 1)) {
                    return true;
                }
            }
        }
        return false;
    }
}

class Node {
    public boolean isEnd;
    public Node[] next;

    public Node() {
        this.next = new Node[26];
        this.isEnd = false;
    }
}
