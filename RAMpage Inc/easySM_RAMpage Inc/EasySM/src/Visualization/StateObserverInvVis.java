package Visualization;
import java.util.*;
import store.StateObserver;

public class StateObserverInvVis {
    public Vector<StateObserverVis> vis;

    public StateObserverInvVis(){
        vis=new Vector<StateObserverVis>();
    }

    public StateObserverInvVis(Set<StateObserver> so){
        vis=new Vector<StateObserverVis>();
        for (StateObserver s : so){
            vis.add(new StateObserverVis(s));
        }
    }

    public Vector<StateObserverVis> getSOVis(){
        return vis;
    }

    public Vector<String> getSOFullStrings(){
        Vector<String> temp=new Vector<String>();
        for(StateObserverVis s : vis){
            temp.add(s.getFullString());
        }
        return temp;
    }
    

    public Set<StateObserverVis> getSOVisSet(){
        Set<StateObserverVis> temp=new HashSet<StateObserverVis>();
        for(StateObserverVis s : vis){
            temp.add(s);
        }
        return temp;
    }


}