package store;

import Visualization.*;
import datatypes.*;

/**
 * the creator of a state observer leaves empty the invariant
 */
public class StateObserver extends Element {

	StateObserverVis vis;
	private Expr inv;
	Type type;
	
	public StateObserver(StateObserverInfo soIN){
            setType(soIN.getType());
            setName(soIN.getName());
            if (soIN.getInv()==null) inv=new Expr("");
                else setInv(soIN.getInv());
            vis=new StateObserverVis(this);
        }

        public StateObserver(String n, Type t, Expr i){
            setName(n);
            setType(t);
            if (i==null) inv=new Expr("");
                else setInv(i);
            vis=new StateObserverVis(this);
        }

        public void setType(Type t){
            type=t;
        }

        public void setInv(Expr i){
            inv=i;
        }

        public Type getType (){
            return type;
        }

        public Expr getInv(){
            return inv;
        }


       @Override
        public int hashCode() {
            int hash = 7;
            hash = 67 * hash + (this.getName() != null ? this.getName().hashCode() : 0);
            return hash;
        }

       @Override
       public boolean equals(Object obj){
            if (obj==null) return false;
            if (!(obj instanceof StateObserver)) return false;
            StateObserver s=(StateObserver)obj;
            System.out.println("1: " + getName());
            System.out.println("2: " + s.getName());
            return getName().equals(s.getName());
        }


}
