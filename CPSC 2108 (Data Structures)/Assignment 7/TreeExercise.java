/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math;

/**
 *
 * @author csu
 */
public class TreeExercise 
{
    public static void main(String args[])
    {
        String[] myStringChars = new String[26];
        
        for(int i = 0; i < 26; i++)
        {
            myStringChars[i] = new String(Character.toChars(i+65));
            System.out.println(myStringChars[i]);
        }
        
        LinkedList<TreeNode> charTree = new LinkedList();
        int lvl = 1;
        for(int x = 0; x < myStringChars.length; x++){
            TreeNode newNode;
            if(x == 0){ 
                newNode = new TreeNode(myStringChars[0], null); 
                newNode.setLevel(lvl);
            }else{
                if(x == Math.pow(2,lvl)-1){ lvl++; }
                if(myStringChars[x].toCharArray()[0]%2 == 0){
                    newNode = new TreeNode(myStringChars[x], charTree.get(x/2));
                    charTree.get(x/2).setLeftChild(newNode);
                    newNode.setLevel(lvl);
                }else{
                    newNode = new TreeNode(myStringChars[x], charTree.get((x/2)-1));
                    charTree.get((x/2)-1).setRightChild(newNode);
                    newNode.setLevel(lvl);
                }
            }
            charTree.add(newNode);
        }
        
        // create the Tree as a linked structure from the array myStringChars
        // the Strings are stored using the representation for trees as arrays in the book
        // (e.g. for an element i, the left child is stored in position 2*i + 1, right child is 
        // on position 2*(i + 1). Also specify the level of a TreeNode
        
        TreeNode[] nodeArray = new TreeNode[charTree.size()];
        nodeArray[0] = charTree.get(0);
        for(int i=0; i < nodeArray.length-1; i++){
            if((2*i)+1 < nodeArray.length){ nodeArray[(2*i)+1] = charTree.get(i).getLeftChild(); }
            if(2*(i+1) < nodeArray.length){ nodeArray[2*(i+1)] = charTree.get(i).getRightChild(); }
        }
        
        // create a traversal by levels and print as you traverse to check that the creation of the tree has happened correctly
        System.out.println();
        for(int a=0; a < nodeArray.length; a++){
            System.out.print(nodeArray[a].getContents());
            if(a == Math.pow(2,nodeArray[a].getLevel())-2){ System.out.println(); }
        }
        System.out.println("\n");
        
        // create the code that asks the user for two letters in uppercase and searches for the nodes in the tree that contain
        // such letters      
        Scanner letter1 = new Scanner(System.in);
        System.out.println("Please enter an uppercase letter. (1)");
        String L1 = letter1.next();

        Scanner letter2 = new Scanner(System.in);
        System.out.println("Please enter an uppercase letter. (2)");
        String L2 = letter2.next();
        
        //create the code that calls the static method below and finds the lowest common ancestor of two TreeNodes
        TreeNode commonAncestor = findLowestCommonAncestor(charTree.get(0).findNodeOnTree(L1), charTree.get(0).findNodeOnTree(L2));
        
        if(commonAncestor != null)
        {
            System.out.println(commonAncestor.getContents());
        }    
    }   
    
    public static TreeNode findLowestCommonAncestor(TreeNode node1, TreeNode node2)
    {
        // Given two nodes on the same tree, this method should return the lowest common ancestor.
        // if no common ancestor is found, this method returns null .
        if (node1.getContents().equals("A") || node2.getContents().equals("A")){ return node1; }
        else{
            if (node1.getContents().equals(node2.getContents())){ return node1; }
            else{
                if(node2.getLevel() < node1.getLevel()){node1 = node1.getParent(); }
                else if(node1.getLevel() < node2.getLevel()){node2 = node2.getParent(); }
                else{
                    node1 = node1.getParent();
                    node2 = node2.getParent();
                }
                return findLowestCommonAncestor(node1, node2);
            }
        }
    }
}