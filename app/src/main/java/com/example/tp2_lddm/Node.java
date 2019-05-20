package com.example.tp2_lddm;

import java.util.ArrayList;

public class Node {
    public com.example.tp2_lddm.Node dad;
    public String content;
    public boolean leaf;
    public ArrayList<com.example.tp2_lddm.Node> childrens = new ArrayList<com.example.tp2_lddm.Node>();

    public Node(com.example.tp2_lddm.Node dad , String content, boolean leaf ){
        this.dad = dad;
        this.content = content;
        this.leaf = leaf;
    }

}
