package com.test.algorithm.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NumberOfBoomerangs {

    /**
     * 给出一个平面上的n个点，寻找存在多少个由这些点构成的三元组(i,j,k)，
     * 使得i,j两点的距离等于i,k两点的距离，其中n最多为500，且所有点的
     * 坐标范围在[-10000, 10000]之间
     * 如[[0,0],[1,0],[2,0]]，结果为2，两个结果为
     * [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
     *
     * 时间复杂度O(n^2)
     * 空间复杂度O(n)
     */
    public static int test(List<Point> points){
        int res = 0;
        for(int i = 0; i < points.size(); i++){
            //记录以i为起始点，到其他点的距离，和距离出现的频次
            HashMap<Integer, Integer> record = new HashMap<>();
            for(int j = 0; j < points.size(); j++){
                if(j != i){
                    int distance = distance(points.get(i), points.get(j));
                    if(record.containsKey(distance)){
                        record.put(distance, record.get(distance) + 1);
                    }else{
                        record.put(distance, 1);
                    }
                }
            }
            //统计以i为起始点，有多少符合条件的
            ArrayList<Integer> values = new ArrayList<>(record.values());
            for(int value : values){
                //相同距离的频次超过2个，说明符合条件
                if(value >= 2){
                    res += value * (value - 1);
                }
            }
        }
        return res;
    }

    /**
     * 两点之间的距离 = √[(x1-x2)²+(y1-y2)²]
     * 由于开根号会有浮点数，所以不用开根号了，直接 (x1-x2)²+(y1-y2)²
     *
     * 有一个陷阱：最终结果有可能越界，但是对于 -10000 到 10000 不会越界
     * 当前题目可以不用处理越界问题，否则需要换成 long 类型
     */
    private static int distance(Point a, Point b){
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }

    private final class Point{
        public int x;
        public int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

}
