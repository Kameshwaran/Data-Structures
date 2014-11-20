package com.example;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class SumOfVerticalTree{
    private int numberOfNodes;
    private int[] tree;
    private boolean[] traversalStatus;
    private int[] sumOfVerticalLines;

    public SumOfVerticalTree(Integer numberOfNodes){
        this.numberOfNodes = numberOfNodes;
        this.tree = new int[numberOfNodes + 1];
        this.traversalStatus = new boolean[numberOfNodes + 1];
        this.sumOfVerticalLines = new int[numberOfNodes];
    }

    private int leftChildOf(int node){ return 2*node; }

    private int rightChildOf(int node){ return 2*node + 1; }

    private boolean hasLeftChild(int node){
        try{ int leftChild =  tree[leftChildOf(node)]; }
        catch(Exception e){ return false; }
        return true;
    }

    private boolean hasRightChild(int node){
        try{ int rightChild = tree[rightChildOf(node)]; }
        catch(Exception e){ return false; }
        return true;
    }

    private boolean hasLeftRightGrandChild(int node){
        return hasLeftChild(node) ? hasRightChild(leftChildOf(node)) : false;
    }

    private boolean hasRightLeftGrandChild(int node){
        return hasRightChild(node) ? hasLeftChild(rightChildOf(node)) : false;
    }

    private int calculateSumFromTree(Integer node, Integer sumIndex){
        if( !traversalStatus[node] ){
            sumOfVerticalLines[sumIndex] += node;
            traversalStatus[node] = true;
            if(hasLeftRightGrandChild(node)){
                int leftRightGrandChild = rightChildOf(leftChildOf(node));
                sumOfVerticalLines[sumIndex] += tree[leftRightGrandChild];
                traversalStatus[leftRightGrandChild] = true;
            }
            if(hasRightLeftGrandChild(node)){
                int rightLeftGrandChild = leftChildOf(rightChildOf(node));
                sumOfVerticalLines[sumIndex] += tree[rightLeftGrandChild];
                traversalStatus[rightLeftGrandChild] = true;
            }
            return sumIndex+1;
        }
        return sumIndex;
    }

    private void readTree(){
        Scanner sc = new Scanner(System.in);
        for(int i = 1 ; i <= numberOfNodes ; i ++ ){
            System.out.println(i == 1 ? "Enter the root Node" :  i % 2 == 0 ? "Enter the Left child of "+ tree[i/2] + " Node" :  "Enter the Right child of "+ tree[i/2] + " Node");
            tree[i] = sc.nextInt();
        }
    }

    public static void main(String args[]){
        SumOfVerticalTree sum = new SumOfVerticalTree(15);
        sum.readTree();
        int j = 0;
        for(int i = 1 ; i <= sum.numberOfNodes ; i ++){
            j = sum.calculateSumFromTree(i, j);
        }
        System.out.println("Result");
        for(int i = 0 ; i < j ; i ++){
            System.out.print(sum.sumOfVerticalLines[i] + "\t");
        }
    }
}
