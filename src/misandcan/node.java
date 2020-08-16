/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misandcan;

/**
 *
 * @author PRANTO
 */
public class snode {
   private int missionary;
   private int cannibal;
   private int boat;
   private snode prev;
   
   
    snode(int missionary,int cannibal,int boat){
       this.missionary=missionary;
       this.cannibal=cannibal;
       this.boat=boat;
       
   }

    /**
     * @return the missionary
     */
    public int getMissionary() {
        return missionary;
    }

    /**
     * @param missionary the missionary to set
     */
    public void setMissionary(int missionary) {
        this.missionary = missionary;
    }

    /**
     * @return the cannibal
     */
    public int getCannibal() {
        return cannibal;
    }

    /**
     * @param cannibal the cannibal to set
     */
    public void setCannibal(int cannibal) {
        this.cannibal = cannibal;
    }

    /**
     * @return the boat
     */
    public int getBoat() {
        return boat;
    }

    /**
     * @param boat the boat to set
     */
    public void setBoat(int boat) {
        this.boat = boat;
    }

    /**
     * @return the prev
     */
    public snode getPrev() {
        return prev;
    }

    /**
     * @param prev the prev to set
     */
    public void setPrev(snode prev) {
        this.prev = prev;
    }
   
   
    
}
