/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misandcan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList; 
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author PRANTO
 */
public class Misandcan {

    /**
     * @param args the command line arguments
     */
    
    
    
    
    public static int mission;
    static int cann;
    static int bo;
    
   /* public static ArrayList<String> getAllAction(){
        ArrayList<String> action=new ArrayList<String>();
        for(int m=0;m<=bo;m++){
            for(int n=0;n<=bo;n++){
                if(m==0 && n==0)
                    continue;
                if(m+n>bo)
                    continue;
                
                action.add(""+m+n+"1");
            }
        }
        return action;
    } */
    
    
    public static void main(String[] args) {
        
        // TODO code application logic here
        
        Scanner sc=new Scanner(System.in);
        System.out.println("Missionary Number :");
        mission=sc.nextInt();
        System.out.println("Cannibal Number :");
        cann=sc.nextInt();
        System.out.println("Boat Number :");
        bo=sc.nextInt();
        
       bfsaActionBegins.doit();
       dfsActionBegins.doDFS();
     //  bfsaActionBegins.doit(startLeftNode,bfsqueue,actionList,leftToRight,visitedState);
       //dfsActionBegins.doDFS(startLeftNode,bfsqueue,actionList,leftToRight,visitedState);
        
    }
    
}
