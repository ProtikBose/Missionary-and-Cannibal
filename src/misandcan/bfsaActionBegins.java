/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misandcan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import static misandcan.Misandcan.bo;
import static misandcan.Misandcan.cann;
import static misandcan.Misandcan.mission;

/**
 *
 * @author PRANTO
 */
public class bfsaActionBegins {

    public static ArrayList<snode> actions;
    public static ArrayList<snode> visited;

    public static boolean checkVisited(snode s) {
        for (int i = 0; i < visited.size(); i++) {
            if (visited.get(i).getMissionary() == s.getMissionary()
                    && visited.get(i).getCannibal() == s.getCannibal()
                    && visited.get(i).getBoat() == s.getBoat()) {
                return true;
            }
        }

        return false;

    }

    public static boolean isValidState(snode n) {
        if (n.getMissionary() >= 0 && n.getMissionary() <= mission && n.getCannibal() >= 0
                && n.getCannibal() <= cann && (n.getCannibal() == n.getMissionary()
                || n.getMissionary() == 0 || n.getMissionary() == mission )) {
            return true;
        }
        return false;
    }

    public static void makeGraph() {

        snode temp = null;
        actions = new ArrayList<>();

        for (int m = 0; m <= bo; m++) {
            for (int n = 0; n <= bo; n++) {
                if (m == 0 && n == 0) {
                    continue;
                }
                if (m + n > bo) {
                    continue;
                }

                temp = new snode(m, n, 1);
                actions.add(temp);
            }
        }
        
      //  for(int i=0;i<actions.size();i++){
        //    System.out.println(actions.get(i).getMissionary());
        //}
    }

    public static void doit() {

        makeGraph();
        
        int nodeExplored=0;
        int nodeExpanded=0;
        snode finalnode=null;
        snode source = new snode(0, 0, 0);
        visited = new ArrayList<>();
        snode garb = new snode(-1, -1, -1);
        source.setPrev(garb);
        visited.add(source);
        Queue<snode> q = new LinkedList<snode>();
        q.add(source);
        nodeExplored++;
        boolean endSearch = false;
        long startTime=System.currentTimeMillis();
       // System.out.println(q.size());
        while (!q.isEmpty() && !endSearch) {

            snode top = q.poll();
            nodeExpanded++;
            
            if(((System.currentTimeMillis()-startTime)/1000.0)>30)
                break;
            if (top.getBoat() == 0) {

                for (int i = 0; i < actions.size(); i++) {
                    
                    if(((System.currentTimeMillis()-startTime)/1000.0)>30)
                        break;
                    snode temp = new snode(top.getMissionary() + actions.get(i).getMissionary(),
                            top.getCannibal() + actions.get(i).getCannibal(),
                            (top.getBoat() + actions.get(i).getBoat()) % 2);

                    if (isValidState(temp) && !checkVisited(temp)) {
                        //System.out.println(temp.getMissionary()+""+temp.getCannibal());
                        temp.setPrev(top);
                        visited.add(temp);
                        nodeExplored++;
                        if (temp.getMissionary() == mission && temp.getCannibal() == cann && temp.getBoat() == 1) {
                            finalnode=temp;
                            endSearch = true;
                            break;
                        }

                        //if not the goal state, then
                        q.add(temp);
                    }

                }

            } else {
                for (int i = 0; i < actions.size(); i++) {
                    if(((System.currentTimeMillis()-startTime)/1000.0)>30)
                        break;
                    
                    snode temp = new snode(top.getMissionary() - actions.get(i).getMissionary(),
                            top.getCannibal() - actions.get(i).getCannibal(),
                            (top.getBoat() + actions.get(i).getBoat()) % 2);

                    if (isValidState(temp) && !checkVisited(temp)) {
                        //System.out.println(temp.getMissionary()+""+temp.getCannibal());
                        temp.setPrev(top);
                        visited.add(temp);
                        nodeExplored++;
                        if (temp.getMissionary() == mission && temp.getCannibal() == cann && temp.getBoat() == 1) {
                            finalnode=temp;
                            endSearch = true;
                            break;
                        }

                        //if not the goal state, then
                        q.add(temp);
                    }

                }
            }
        }
       int cnt=0;
        if(endSearch){
            System.out.println("FOund");
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
        System.out.println("Explored SNode : "+nodeExplored);
        System.out.println("Expanded SNode : "+nodeExpanded);
        long endTime=System.currentTimeMillis();
        System.out.println("Elapsed Time :"+(endTime-startTime)+" milisecond");
        System.out.println(cnt);
          
    }

}
