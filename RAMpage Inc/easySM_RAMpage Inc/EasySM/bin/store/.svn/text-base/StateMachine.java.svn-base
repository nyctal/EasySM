package store;

import java.util.*;
import Visualization.*;
import datatypes.*;
import datatypes.Error;


public class StateMachine extends Element {


	StateMachineVis vis;
	List<Transition> transitions;
	List<State> states;

	public StateMachine(StateMachineInfo smIN) {
		setStates(smIN.getStates());
                System.out.println("---Costruttore SM---");
                System.out.println("---Stati:---");
                for (State s: smIN.getStates()){
                    System.out.println(s.getName());
                }
                setTransitions(smIN.getTrans());
                System.out.println("---Transizioni---");
                for (Transition t: smIN.getTrans()){
                    System.out.println(t.getSource().getName());
                }
                System.out.println("---Creazione Vis---");
                vis=new StateMachineVis(this);
                System.out.println("---Fine costruttore---");
	}
	
	/**
	 * 
	 * @return 
	 */
	public Set<Error> check() {
		throw new UnsupportedOperationException();
	}

        public void setStates(List<State> s){
            states=s;
        }

        public List<State> getStates(){
            return states;
        }

        public void setTransitions(List<Transition> t){
            transitions=t;
        }

        public List<Transition> getTransitions(){
            return transitions;
        }

        public StateMachineVis getVis() {
            return vis;
        }


}
