package top.yms.recent.c202206;


import org.junit.jupiter.api.Test;

public class Code0606 {

    public void dfs(int [][] map, boolean [] visited, int i, int cl) {
        for(int j=0; j<cl; j++) {
            if (!visited[j] && map[i][j] == 1) {
                visited[j] = true;
                dfs(map, visited, j, cl);
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int cl = isConnected.length;

        int province = 0;
        boolean [] visited = new boolean[cl];

        for(int i=0; i<cl; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, i, cl);
                province++;
            }
        }

        return province;

    }


    public static void main(String[] args) {
        Code0606 code0606 = new Code0606();


    }


    @Test
    public void test1630() {
        int [][] map1 = {{1,1,0},{1,1,0},{0,0,1}};

        System.out.println(findCircleNum(map1));

        int [][] map2 = {{1,0,0},{0,1,0},{0,0,1}};
        System.out.println(findCircleNum(map2));

    }


    static
    class Solution {
        public int findCircleNum(int[][] isConnected) {
            int cities = isConnected.length;
            boolean[] visited = new boolean[cities];
            int provinces = 0;
            for (int i = 0; i < cities; i++) {
                if (!visited[i]) {
                    dfs(isConnected, visited, cities, i);
                    provinces++;
                }
            }
            return provinces;
        }

        public void dfs(int[][] isConnected, boolean[] visited, int cities, int i) {
            for (int j = 0; j < cities; j++) {
                if (isConnected[i][j] == 1 && !visited[j]) {
                    visited[j] = true;
                    dfs(isConnected, visited, cities, j);
                }
            }
        }
    }


    class Solutions {
        public int findCircleNum(int[][] isConnected) {
            int cities = isConnected.length;
            int[] parent = new int[cities];
            for (int i = 0; i < cities; i++) {
                parent[i] = i;
            }
            for (int i = 0; i < cities; i++) {
                for (int j = i + 1; j < cities; j++) {
                    if (isConnected[i][j] == 1) {
                        union(parent, i, j);
                    }
                }
            }
            int provinces = 0;
            for (int i = 0; i < cities; i++) {
                if (parent[i] == i) {
                    provinces++;
                }
            }
            return provinces;
        }

        public void union(int[] parent, int index1, int index2) {
            parent[find(parent, index1)] = find(parent, index2);
        }

        public int find(int[] parent, int index) {
            if (parent[index] != index) {
                parent[index] = find(parent, parent[index]);
            }
            return parent[index];
        }
    }





}
