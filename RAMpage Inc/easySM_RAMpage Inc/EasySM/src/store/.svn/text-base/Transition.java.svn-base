package store;

import datatypes.*;

public class Transition {
	State source;
	State target;
	private Expr cond;
	private Action react;
        Event trigger;

        public Transition(State source, State target, Event ev, CondReact cr){
            this.source=source;
            this.target=target;
            this.trigger=ev;
            this.cond=cr.getCond();
            this.react=cr.getReact();
        }

        public void setSource(State s){
            source=s;
        }

        public void setTarget(State s){
            target=s;
        }

        public void setCond(Expr c){
            cond=c;
        }

        public void setReact(Action r){
            react=r;
        }

        public void setTrigger(Event t){
            trigger=t;
        }

        public State getSource(){
            return source;
        }

        public State getTarget(){
            return target;
        }

        public Expr getCondition(){
            return cond;
        }

        public Action getReaction(){
            return react;
        }

        public Event getTrigger(){
            return trigger;
        }
}
