/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misandcan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import static misandcan.Misandcan.bo;
import static misandcan.Misandcan.cann;
import static misandcan.Misandcan.mission;

/**
 *
 * @author PRANTO
 */
public class dfsActionBegins {

    public static ArrayList<snode> actionsDFS;
    public static ArrayList<snode> visitedDFS;
    public static long startTimeDFS;
    public static int maxDepth = 1000000;
    public static int nodeExploredDFS;
    public static int nodeExpandedDFS;
    public static boolean foundValue = false;
    public static snode finalnode;

    public static boolean checkVisited(snode s) {
        for (int i = 0; i < visitedDFS.size(); i++) {
            if (visitedDFS.get(i).getMissionary() == s.getMissionary()
                    && visitedDFS.get(i).getCannibal() == s.getCannibal()
                    && visitedDFS.get(i).getBoat() == s.getBoat()) {
                return true;
            }
        }

        return false;

    }

    public static boolean isValidState(snode n) {
        if (n.getMissionary() >= 0 && n.getMissionary() <= mission && n.getCannibal() >= 0
                && n.getCannibal() <= cann && (n.getCannibal() == n.getMissionary()
                || n.getMissionary() == 0 || n.getMissionary() == mission)) {
            return true;
        }
        return false;
    }

    public static void makeGraph() {

        snode temp = null;
        actionsDFS = new ArrayList<>();

        for (int m = 0; m <= bo; m++) {
            for (int n = 0; n <= bo; n++) {
                if (m == 0 && n == 0) {
                    continue;
                }
                if (m + n > bo) {
                    continue;
                }

                temp = new snode(m, n, 1);
                actionsDFS.add(temp);
            }
        }

        //  for(int i=0;i<actions.size();i++){
        //    System.out.println(actions.get(i).getMissionary());
        //}
    }

    public static boolean dfs(snode state,int depth) {

       // snode goalState = new snode(3, 3, 1);
        if (((System.currentTimeMillis() - startTimeDFS) / 1000.0) > 30) {
            return false;
        }
        boolean result=false;
        nodeExploredDFS++;
        visitedDFS.add(state);
        if (state.getMissionary() == mission && state.getCannibal() == cann && state.getBoat() == 1) {
            finalnode = state;
            return true;

        }
        
        if(depth>maxDepth)
            return false;
        else{
             nodeExpandedDFS++;
            
            if(((System.currentTimeMillis()-startTimeDFS)/1000.0)>30)
                return false;
            if (state.getBoat() == 0) {

                for (int i = 0; i < actionsDFS.size(); i++) {
                    
                    if(((System.currentTimeMillis()-startTimeDFS)/1000.0)>30)
                        break;
                    snode temp = new snode(state.getMissionary() + actionsDFS.get(i).getMissionary(),
                            state.getCannibal() + actionsDFS.get(i).getCannibal(),
                            (state.getBoat() + actionsDFS.get(i).getBoat()) % 2);

                    if (isValidState(temp) && !checkVisited(temp)) {
                        //System.out.println(temp.getMissionary()+""+temp.getCannibal());
                        temp.setPrev(state);
                        result=dfs(temp,depth+1);
                        if(result)
                            return true;
                       
                    }

                }

            }
            else{
                 for (int i = 0; i < actionsDFS.size(); i++) {
                    
                    if(((System.currentTimeMillis()-startTimeDFS)/1000.0)>30)
                        break;
                    snode temp = new snode(state.getMissionary() - actionsDFS.get(i).getMissionary(),
                            state.getCannibal() - actionsDFS.get(i).getCannibal(),
                            (state.getBoat() + actionsDFS.get(i).getBoat()) % 2);

                    if (isValidState(temp) && !checkVisited(temp)) {
                        //System.out.println(temp.getMissionary()+""+temp.getCannibal());
                        temp.setPrev(state);
                        result=dfs(temp,depth+1);
                        if(result)
                            return true;
                       
                    }

                }
            }
        }
        return result;
    }

    public static void doDFS() {
        makeGraph();
        snode source = new snode(0, 0, 0);
        snode garb=new snode(-1,-1,-1);
        source.setPrev(garb);
        nodeExpandedDFS = 0;
        nodeExploredDFS = 0;
        finalnode = null;
        int depth=0;
        visitedDFS = new ArrayList<>();
        startTimeDFS = System.currentTimeMillis();
        System.out.println("");
        System.out.println("####################");
        System.out.println("");
        System.out.println("DFS Solution :");

           int cnt=0;  
        if(dfs(source,depth)){
                
             while(true){
                if(finalnode.getMissionary()==-1 && finalnode.getCannibal()==-1 )
                    break;
                else
                    System.out.println(finalnode.getMissionary()+"M "+finalnode.getCannibal()+"C "
                    +"------ "+(mission-finalnode.getMissionary())+"M "+(cann-finalnode.getCannibal())+"C");
                finalnode=finalnode.getPrev();
                cnt++;
            }
        }
        
        else{
            System.out.println("no solution");
        }
        System.out.println("Explored SNode : "+nodeExploredDFS);
        System.out.println("Expanded SNode : "+nodeExpandedDFS);
        long endTime=System.currentTimeMillis();
        System.out.println("Elapsed Time :"+(endTime-startTimeDFS)+" milisecond");
        System.out.println(cnt);
                
            
    }

}
