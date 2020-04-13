package com.test.datastructure.segmenttree;

/**
 * 处理 当前节点 和 左右两个子节点 之间的关系
 */
public interface Merger<E> {

    E merge(E a, E b);

}
