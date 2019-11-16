/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myavltreegui;
import java.lang.Math;
/**
 *
 * @author LIVERPOOL I5
 */
public class AVLtree {
    Node root = null;
    Node node = root;
    int data;
    int cont=0;
    public String print_tree(Node node,int cont ){
        String output = "";
        int j;
        if(node!=null){
            output = output + print_tree(node.rightChild,cont+1);
            output = output + "\n";
            for(j=0; j<cont; j++){
                output = output + " - ";
            }
            output= output+node.data+"["+node.balance+"]";
            output = output + print_tree(node.rightChild,cont+1);
        }
        return output;
    }
    public int add (int data, Node node, Node parent){
        int insert = 0;
        int increment = 0; 
        if (node == null){
         node = new Node(data);
            if (parent != null){
                if(data>parent.data){
                    parent.rightChild = node;
                    //cont++;
                }
                else{
                    parent.leftChild = node;
                    //cont++;
                }
            }    
            node.parent = parent;
            if(root == null){
                root = node;
                
                //cont++;
            }
            insert = 1;
        }
        else{
            if (node.data == data){
                return 0;
            }
            else{
                if(data > node.data){
                    increment = add(data,node.rightChild,node);
                   // cont++;
                }
                else{
                    increment = -add(data,node.leftChild,node);
                    //cont++;
                }
                node.balance = node.balance + increment;
                if (increment != 0 && node.balance != 0){
                    if(node.balance < -1){
                        Node balancenode = node.leftChild;
                        if (balancenode.balance <= 0){
                            rotate_right(node, balancenode);
                        }
                        else{
                            rotate_left(node, balancenode);
                            rotate_right(node, balancenode);
                        }
                    }
                    else{
                        if(node.balance > 1){
                            Node balancenode = node.rightChild;
                            if (balancenode.balance >= 0){
                                rotate_left(node, balancenode);
                            }
                            else{
                                rotate_right(node, balancenode);
                                rotate_left(node, balancenode);
                            }
                        }
                        else{
                            insert = 1;
                        }
                    }
                }
            }
        }
        return insert;
    }
    /*public int add (int data, Node node, Node parent){
        int insert = 0;
        int increment = 0; 
        if (node == null){
         node = new Node(data);
            if (parent != null){
                if(data>parent.data){
                    parent.rightChild = node;
                    //cont++;
                    System.out.println(node);
                }
                else{
                    parent.leftChild = node;
                    System.out.println(node);
                    //cont++;
                }
            }    
            insert = 1;
        }
        else{
            if (node.data == data){
                return 0;
            }
            else{
                if(data > node.data){
                    increment = add(data,node.rightChild,node);
                    System.out.println(increment);
                   // cont++;
                }
                else{
                    increment = -add(data,node.leftChild,node);
                    System.out.println(increment);
                    //cont++;
                }
                node.balance = node.balance + increment;
                if (increment != 0 && node.balance != 0){
                    if(node.balance < -1){
                        Node balancenode = node.leftChild;
                        if (balancenode.balance < 0){
                            //rotate_right(node);
                        }
                        else{
                            rotate_left(balancenode);
                           // rotate_right(node);
                        }
                    }
                    else{
                        if(node.balance > 1){
                            Node balancenode = node.rightChild;
                            if (balancenode.balance > 0){
                                rotate_left(node);
                            }
                            else{
                                //rotate_right(balancenode);
                                rotate_left(node);
                            }
                        }
                        else{
                            insert = 1;
                        }
                    }
                }
            }
        }
        return insert;
    }*/
    public void rotate_left(Node node,Node balancenode){
        Node auxpointer = balancenode.rightChild;
        if(node == root){
            root = balancenode;
            balancenode.parent = null;
        }
        else{
            if(node.data > node.parent.data){
                node.parent.rightChild = balancenode;            
            }
            else{
                node.parent.leftChild = balancenode;               
            }
            balancenode.parent = node.parent;
            balancenode.leftChild = node;
            node.rightChild = auxpointer;
            node.parent = balancenode;
            if(auxpointer != null){
                auxpointer.parent = node;
                int w = node.balance;
                node.balance = w - 1 - Math.max(balancenode.balance,0);
                int a = Math.min(w-2, w + balancenode.balance-2);
                balancenode.balance = Math.min(a, root.balance-1);
                        
            }
        }
    }
     public void rotate_right(Node node,Node balancenode){
        Node auxpointer = balancenode.rightChild;
        if(node == root){
            root = balancenode;
            balancenode.parent = null;
        }
        else{
            if(node.data > node.parent.data){
                node.parent.rightChild = balancenode;            
            }
            else{
                node.parent.leftChild = balancenode;               
            }
            balancenode.parent = node.parent;
            balancenode.rightChild = node;
            node.leftChild = auxpointer;
            node.parent = balancenode;
            if(auxpointer != null){
                auxpointer.parent = node;
                int w = node.balance;
                node.balance = w + 1 - Math.min(balancenode.balance,0);
                balancenode.balance = Math.max(Math.min(w+2 ,w - balancenode.balance+2), balancenode.balance+1);
                        
            }
        }
    }
     /*public void rotate_left(Node node){
        int w = 0;
        int a;
        Node auxpointer = node;
        Node t = node.rightChild;
        auxpointer.rightChild = t.leftChild;
        t.leftChild = auxpointer;
        w= auxpointer.balance;
        auxpointer.balance = w -1 -Math.max(t.balance, 0);
        a = Math.min(w-2,w+t.balance-2);
        t.balance = Math.min(a,t.balance-1);   
    }*/
}
