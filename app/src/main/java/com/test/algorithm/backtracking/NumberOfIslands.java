package com.test.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

public class NumberOfIslands {

    /**
     * 给定一个二维数组，只含有0和1两个字符，其中1代表陆地，0代表水域。
     * 横向和纵向的陆地连接成岛屿，被水域分隔开。
     * 问给出的地图中有多少岛屿？
     */
    public static int numIslands(List<List<Integer>> grid){
        int res = 0;
        if(grid == null || grid.size() == 0){
            return res;
        }
        List<List<Boolean>> visited = new ArrayList<>();
        for(int i = 0; i < grid.size(); i++){
            visited.add(new ArrayList<Boolean>());
            for(int j = 0; j < grid.get(i).size(); j++){
                visited.get(i).add(false);
            }
        }
        m = grid.size();
        for(int i = 0; i < grid.size(); i++){
            n = grid.get(i).size();
            for(int j = 0; j < grid.get(i).size(); j++){
                //找到一个岛屿，开始 深度优先 遍历
                if(grid.get(i).get(j) == 1 && !visited.get(i).get(j)){
                    res ++;
                    dfs(grid, i, j, visited);
                }
            }
        }
        return res;
    }

    private static void dfs(List<List<Integer>> grid, int startX, int startY, List<List<Boolean>> visited){
        visited.get(startX).set(startY, true);
        //从 startX,startY 开始，向 上下左右 四个方向寻找
        for(int i = 0; i < 4; i++){
            int newX = startX + d[i][0];
            int newY = startY + d[i][1];
            //没有越界，并且没有访问过
            if(inArea(newX, newY) && !visited.get(newX).get(newY)){
                if(grid.get(newX).get(newY) == 1){//有新的陆地
                    dfs(grid, newX, newY, visited);
                }
            }
        }
    }

    //代表 上下左右 四个方向
    private static final int[][] d = new int[][]{
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };
    private static int m, n;
    private static boolean inArea(int x, int y){
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
