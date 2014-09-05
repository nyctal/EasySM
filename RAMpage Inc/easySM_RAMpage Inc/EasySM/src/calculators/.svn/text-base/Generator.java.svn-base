package calculators;

import datatypes.*;
import executors.Main;
import store.*;
import store.Class;
import store.Enumeration;
import java.util.*;


public class Generator {
	/**
	 * 
	 * @param usedSO
	 * @return 
	 */
	public static List<Row> generateST(List<StateObserver> usedSO) {
		//throw new UnsupportedOperationException();
            List<Row> r=new LinkedList<Row>();
            List<Value> lv= new LinkedList<Value>();
            List<StateObserver> listSO= new LinkedList<StateObserver>();
            for (StateObserver s: usedSO){
                listSO.add(s);
            }

            int num_stateObs=listSO.size();
            Vector<Integer> numValori=new Vector<Integer>();
            for (StateObserver so : listSO){
                numValori.add(new Integer(so.getType().values().size()));
            }
            int num_righe=1;
            for (Integer i : numValori){
                num_righe*=i;
            }

            Value[][] tab=new Value[num_stateObs][num_righe];
            int divisore=num_righe;
            int var=0;
            for (int i=0; i<num_stateObs; i++){
                divisore/=numValori.elementAt(i);
                for (int j=0; j<num_righe; j++){
                    if (j!=0) System.out.println("Resto= " + (j % divisore) + " List size=" + numValori.get(i));
                    if ( (j!=0) && (j % divisore) == 0) var++;
                    if (var == numValori.get(i)) var=0;
                    System.out.println("i= " + i + " j = " + j + " Div= " + divisore + " Var = " + var);
                        tab[i][j]=listSO.get(i).getType().values().get(var);
                        
                }
                var=0;
            }

            for (int j=0; j<num_righe; j++){
                lv=new LinkedList<Value>();
                for (int i=0; i<num_stateObs; i++){
                    lv.add(tab[i][j]);
                }
                r.add(new Row(lv));
            }

            return r;
	}

	/**
	 * 
	 * @param cd
	 * @param context
	 * @return 
	 */
	public static Set<StateObserver> generateBasicSO(ClassDiagram cd, store.Class context) {
		//throw new UnsupportedOperationException();
            Set<StateObserver> basicSO=new HashSet<StateObserver>();
            if (context!=null){
                for (Attribute a : context.getAttr()){
                basicSO.add(new StateObserver(a.getName(), a.getType(), null));
            }
            return basicSO;
            } else return null;
            
	}

	/**
	 * 
	 * @param cd
	 * @param context
	 * @return 
	 */
	public static Set<Event> generateBasicEV(ClassDiagram cd, store.Class context) {
		//throw new UnsupportedOperationException();
            Set<Event> basicEv=new HashSet<Event>();
            if (context!=null){
                for (Operation o : context.getOp()){
                    basicEv.add(new Event(o.getName(), o.getParam()));
                }
            return basicEv;
            } else return null;
	}

	/**
	 * 
	 * @param st
	 * @return 
	 */
	public static List<State> generateStates(StateTable st) {
            List<State> states = new LinkedList<State>();
            State in=new State("Initial");
            in.setRow(new Row(null, "Initial"));
            in.setInitial();
            states.add(in);
            for(Row r : st.getRows()){
                if (!r.getName().equals("Impossible")){
                    System.out.println("------------Aggiunto lo stato : " + r.getName());
                    int numSO=r.getValues().size();
                    State s=new State(r);
                    if (r.isFinal()) {
                        System.out.println("E' final");
                        s.setFinal();
                    }
                    else s.notFinal();
                    states.add(s);
                } 
            }
            System.out.println(" ----- NUMERO STATI:   " + states.size());
            for(State stato : states){
                System.out.println(stato.getName());
            }
            return states;
		//throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param icd
	 * @param sos
	 * @param evs
	 * @return 
	 */
	public static ClassDiagram generateOutputCD(ClassDiagram icd, Set<StateObserver> sos, Set<Event> evs, List<StateObserver> used) {
            Set<Enumeration> en=new HashSet<Enumeration>();
            Set<Class> cl=new HashSet<Class>();
            Set<Association> as=new HashSet<Association>();
            for (Class c : icd.getClasses()){
                cl.add(c);
            }
            for (Enumeration e : icd.getEnums()){
                en.add(e);
            }
            for (Association a : icd.getAssoc()){
                as.add(a);
            }
            Class context=new Class(new ClassInfo(icd.getContextClass().getName()));
            for (Attribute at : icd.getContextClass().getAttr()){
                context.addAttr(at);
            }
            for (Operation op : icd.getContextClass().getOp()){
                context.addOp(op);
            }
            for (StateObserver s : sos){
                if (!used.contains(s)) context.addAttr(new Attribute(s.getName(), s.getType()));
            }
            for (Event e : evs){
                if (e.getName().equals("Created")) context.addOp(new Operation(e.getName(), e.getParam(), context, new Type().bool()));
            }
            for (Attribute a : context.getAttr()) System.out.println ("CONTEXT " + a.getName());
                    //icd.getContextClass();
            return new ClassDiagram(cl, en, as, context);
            //throw new UnsupportedOperationException();
	}

        public static Transition generateTransition(TransitionInfo tr, State st){
            System.out.println("Genera Transizione");
            State source=null;
            for (State s: Main.getStates()){
//                System.out.println("Stato nel for: " + s.getName());
                    if (s.getRow().equals(tr.getR())){
                        source=s;
//                        System.out.println("Source= " + s.getName());
                    }
                }
            Transition t = new Transition(source, st, tr.getE(), tr.getCR());
            return t;
        }
}
