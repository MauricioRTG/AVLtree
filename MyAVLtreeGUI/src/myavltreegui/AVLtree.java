/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myavltreegui;
import java.lang.Math;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *
 * @author Mauricio Tenorio
 */
public class AVLtree {
    Node root = null;
    Node node;
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
            output = output + print_tree(node.leftChild,cont+1);
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
    
    public void rotate_left(Node node,Node balancenode){
        Node auxpointer = balancenode.leftChild;
        int w;
        int a;
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
                w = node.balance;
                node.balance = w - 1- Math.max(balancenode.balance,0);
                //a = Math.min(w-2, w + balancenode.balance-2);
                balancenode.balance = Math.min(Math.min(w-2, w + balancenode.balance-2), root.balance-1);
                        
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
     public void loadFile(){ //Lee e inserta un archivo de un .txt, que contiene numero, al arreglo.
        File f = new File("AVLFile.txt");
        int datoguardado;
		Scanner s;
		try {	
                    //cargamos el archivo con la clase Scan
                    s  = new Scanner(f);
                                        
                                        
                                            while(s.hasNextInt())
                                            {
                                                
                                               datoguardado =  s.nextInt(); //Se lee cada linea del documento y se guarda 
                                                add(datoguardado,node,root);
                                                
                                            }
                                        
                                    
                                
			
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
                }
                
    }
     public Node search(int val2){
         if(root != null){
             return searchNode(root,new Node (val2));
         }
         return null;
     }
     public Node searchNode(Node search, Node node){
         if(search == null){
             return null;
         }
         if(search.data == node.data){
             return search;
         }
         else{
             Node returnNode = searchNode(search.leftChild,node);
             if (returnNode == null){
                 returnNode = searchNode(search.rightChild,node);
             }
             return returnNode;
         }
     }
     public boolean delete(int val){
     
       Node nodetobedeleted = search(val);
       
      if( nodetobedeleted != null)
      {
        if(nodetobedeleted.leftChild == null && nodetobedeleted.rightChild == null)
        {
          
          //check if the node to be deleted is the left or right child of the parent of the node to be deletd
          deleteCase1(nodetobedeleted);
         
        }
        else if(nodetobedeleted.leftChild != null && nodetobedeleted.rightChild != null)
        {
        // case 3 the node has to children
            deleteCase3(nodetobedeleted);

        }
        else if (nodetobedeleted.leftChild != null)
        {
            //case 2 where left child should be deleted
            
            deleteCase2(nodetobedeleted);
            
        }
        else if (nodetobedeleted.rightChild != null)
        {
            //case 2 where left child should be deleted
            deleteCase2(nodetobedeleted);
        }
      }
         
         
     return false; 
     }
     
     
     private void deleteCase1(Node nodetobedeleted){
     
          if ( nodetobedeleted.parent.leftChild == nodetobedeleted)
          {
            nodetobedeleted.parent.leftChild = null;
          }
          else  if ( nodetobedeleted.parent.rightChild == nodetobedeleted)
          {
          
            nodetobedeleted.parent.rightChild = null;
          }
     }
     
     
     private void deleteCase2(Node nodetobedeleted){
     
     if (nodetobedeleted.parent.leftChild == nodetobedeleted)
     {
        if (nodetobedeleted.leftChild != null)
        {
         
            nodetobedeleted.parent.leftChild = nodetobedeleted.leftChild;
        }
        else if (nodetobedeleted.rightChild != null)
        {
            
           nodetobedeleted.parent.leftChild = nodetobedeleted.rightChild;
        }
     
     }
     else if (nodetobedeleted.parent.rightChild == nodetobedeleted)
     {
      if (nodetobedeleted.leftChild != null)
        {
         
            nodetobedeleted.parent.rightChild = nodetobedeleted.leftChild;
        }
        else if (nodetobedeleted.rightChild != null)
        {
            
           nodetobedeleted.parent.rightChild = nodetobedeleted.rightChild;
        }
     }
    
     }
     
     private void deleteCase3(Node tobedeleted)
     {
     
     
        Node minNode = minleftTraversal(tobedeleted.rightChild);
     
        deleteCase2(minNode);
        
        minNode.parent = tobedeleted.parent;
        minNode.leftChild = tobedeleted.leftChild;
        minNode.rightChild = tobedeleted.rightChild;
        
        if(tobedeleted.parent == null)
        {
            root = minNode;
        }
        else
        {
         if(tobedeleted.parent.leftChild == tobedeleted)
         {
     
            tobedeleted.parent.leftChild = minNode;
         }
         else if (tobedeleted.parent.rightChild == tobedeleted)
         {
        
            tobedeleted.parent.rightChild = minNode;
          }
        }
     }
     
     private Node minleftTraversal(Node node){
     
     if(node.leftChild == null)
     {
     return node;
     }
    return  minleftTraversal(node.leftChild);
     }
}
