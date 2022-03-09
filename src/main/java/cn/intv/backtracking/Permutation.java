package cn.intv.backtracking;

import java.util.*;

public class Permutation {
    public static void main(String[] args) {
        Permutation tool = new Permutation();
        // ["aba","aab","baa"]
        String[] result = tool.permutation("aba");
        System.out.println(result);
    }

    public String[] permutation(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);

        List<String> res = new LinkedList<>();
        StringBuilder path = new StringBuilder();
        boolean[] visited = new boolean[arr.length];

        backtrack(arr, path, visited, res);

        return res.toArray(new String[0]);
    }

    private void backtrack(char[] arr, StringBuilder path, boolean[] visited, List<String> res) {
        if (arr.length == path.length()) {
            res.add(path.toString());
        }

        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) {
                continue;
            }

            // !visited[i - 1]
            // 如果对树层中前一位去重，就用used[i - 1] == false，如果要对树枝前一位去重用used[i - 1] == true
            if (i > 0 && arr[i] == arr[i - 1] && visited[i - 1]) {
                continue;
            }

            visited[i] = true;
            path.append(arr[i]);
            backtrack(arr, path, visited, res);
            path.deleteCharAt(path.length() - 1);
            visited[i] = false;
        }
    }
}
