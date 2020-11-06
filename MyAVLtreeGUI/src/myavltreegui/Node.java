/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myavltreegui;

/**
 *
 * @author Mauricio Tenorio
 */
 class Node {
    int data;
    Node parent;
    Node leftChild;
    Node rightChild;
    int balance;
    
    public Node() {
     
    }
    public Node (int data){
        this.data = data;
        parent = leftChild = rightChild = null;
        balance = 0;
    }
}
