package Visualization;

import java.util.Vector;
import store.State;
import store.StateMachine;
import store.Transition;

public class StateMachineVis {

    Vector<StateVis> states;
    Vector<TransVis> trans;
    StateMachine linkedSM;

    public StateMachineVis (StateMachine sm){
        linkedSM=sm;
        states=new Vector<StateVis>();
        trans=new Vector<TransVis>();
        int i=0;
        for (State s: sm.getStates()){
            states.add(new StateVis(s));
        }
        for (Transition t : sm.getTransitions()){
            trans.add(new TransVis(t));
        }
    }

    public StateMachine getLinkedSM() {
        return linkedSM;
    }

    public Vector<StateVis> getStates() {
        return states;
    }

    public Vector<TransVis> getTrans() {
        return trans;
    }

    public Vector<String> getStateString(){
        Vector<String> v=new Vector<String>();
        for (StateVis sv : states){
            v.add(sv.getName());
        }
        return v;
    }

    public Vector<String> getTransString(){
        Vector<String> v=new Vector<String>();
        for (TransVis tv : trans){
            v.add(tv.getFullString());
        }
        return v;
    }

    


    class StateVis{
        String name;
        State linkesState;

        public StateVis(State s) {
            name=s.getName();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class TransVis{
        String source;
        String dest;
        String ev;
        String cond;
        String react;
        Transition linkedTrans;

        TransVis(Transition t){
            source=t.getSource().getName();
            dest=t.getTarget().getName();
            ev=t.getTrigger().getName();
            cond=t.getCondition().getExpr();
            react=t.getReaction().getAct();
            linkedTrans=t;
        }

        public String getCond() {
            return cond;
        }

        public String getDest() {
            return dest;
        }

        public String getEv() {
            return ev;
        }

        public Transition getLinkedTrans() {
            return linkedTrans;
        }

        public String getReact() {
            return react;
        }

        public String getSource() {
            return source;
        }

        public String getFullString(){
            return "From " + source + " to " + dest + " : " + ev + " " + cond + " " + react;
        }
    }
}