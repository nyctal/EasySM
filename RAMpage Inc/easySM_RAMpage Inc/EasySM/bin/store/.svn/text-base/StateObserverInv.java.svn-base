package store;

import java.util.*;

import Visualization.*;
import datatypes.*;
import datatypes.Error;

public class StateObserverInv extends Element {

    StateObserverInvVis vis;
    Set<StateObserver> stObs;

    public StateObserverInv() {
        stObs = new HashSet<StateObserver>();
        stObs.add(new StateObserver("Final", new Type().bool(), new Expr("")));
        vis = new StateObserverInvVis(stObs);

    }

    /**
     *
     * @return
     */
    public Set<Error> check() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param so
     * @return
     */
    public Report addStateObserver(StateObserver so) {
        boolean ok=true;
        String msg="";
        if (stObs.contains(so)){
            ok=false;
            return new Report("A StateObserver called " + so.getName() + " already exixts", ok);
        } else {
            stObs.add(so);
            vis=new StateObserverInvVis(stObs);
            return new Report("StateObserver " + so.getName() + " added", ok);
        }
    }

    /**
     *
     * @param so
     * @return
     */
    public Report addStateObservers(Set<StateObserver> so) {
        boolean ok=true;
        String msg="These State Observers already exist: ";
        for (StateObserver s : so){
            if (stObs.contains(s)) {
                ok=false;
                msg += s.getName() + " ";
            } else stObs.add(s);
        }
        vis=new StateObserverInvVis(stObs);
        if (ok) return new Report("Basic State Observers added", ok);
        else return new Report(msg, ok);
    }

    /**
     *
     * @param so
     * @return
     */
    public Report deleteStateObserver(StateObserver so) {
        String name=so.getName();
        stObs.remove(so);
        vis=new StateObserverInvVis(stObs);
        return new Report("State Observer " + name + " deleted", true);
    }

    /**
     *
     * @param so
     * @param SoInfonew
     * @return
     */
    public Report updateSO(StateObserver so, StateObserverInfo SoInfonew) {
          so.setName(SoInfonew.getName());
          so.setInv(SoInfonew.getInv());
          so.setType(SoInfonew.getType());
          vis=new StateObserverInvVis(stObs);
          return new Report("State Observer " + so.getName() + " updated", true);
    }

    public StateObserverInvVis getVis() {
        return vis;
    }

    public Set<StateObserver> getStateObs(){
        return stObs;
    }
}
