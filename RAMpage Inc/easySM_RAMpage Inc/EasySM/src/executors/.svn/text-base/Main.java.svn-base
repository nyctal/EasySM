package executors;

import boundaries.*;
import Visualization.*;
import Visualization.StateTableVis.RowVis;
import java.util.*;
import datatypes.*;
import store.*;
import store.Class;
import store.Enumeration;
import context.*;
import calculators.*;
import java.io.File;

/**
 * derived
 * 
 * 
 * INPUT_CD = OPEN_PRJ.inputCD
 * 
 * SOINV = OPEN_PRJ.stateObserverInv
 * 
 * EVCR = OPEN_PRJ.eventCondReact
 * 
 * ST = OPEN_PRJ.stateTable
 * 
 * SM = OPEN_PRJ.stateMachine
 */

public class Main {
	static FileSystem FS;
	static Project OPEN_PRJ;
	static ClassDiagram INPUT_CD;
	static Invalidator INV;
        static Generator GEN;
        static Serialization SER;
	static StateObserverInv SOINV;
	static EventCondReact EVCR;
	static StateTable ST;
	static StateMachine SM;
	static private List<State> states = null;
	static private List<Transition> trans = null;

        /*Interfaccia*/
        static EasySM MainWindow;
        static UpdateProject ProjectWindow;
        static UpdateEventCondReact EventWindow;
        static UpdateInputCD CDWindow;
        static UpdateStateObserverInv StObsWindow;
        static UpdateStateTable StTableWindow;

	/**
	 * 
	 * @param fName
	 * @return 
	 */
	public static void doNewProject(File file) {
                if (ProjectWindow!=null){
                    doClose();
                }
                OPEN_PRJ=new Project(file);
                SOINV=OPEN_PRJ.getStObs();
                EVCR=OPEN_PRJ.getEvCR();
                ST=OPEN_PRJ.getStateTable();
                INPUT_CD=OPEN_PRJ.getInputCD();
                MainWindow.projectFrame=new UpdateProject(OPEN_PRJ.getVis());
                ProjectWindow=MainWindow.getUpdateProject();
                ProjectWindow.updateCDFrame = new UpdateInputCD(INPUT_CD.getVis(), INPUT_CD.types());
                ProjectWindow.updateECRPanel = new UpdateEventCondReact(EVCR.getVis(), INPUT_CD.types());
                ProjectWindow.updateSOIPanel = new UpdateStateObserverInv(SOINV.getVis(), INPUT_CD.types());
                ProjectWindow.updateSTPanel = new UpdateStateTable(ST.getVis(), SOINV.getVis().getSOVisSet(), EVCR.getVis());
                ProjectWindow.addTabs();
                MainWindow.addProjectWindow(ProjectWindow);
                CDWindow = MainWindow.getUpdateProject().getUpdateCDPanel();
                EventWindow = MainWindow.getUpdateProject().getUpdateECRPanel();
                StObsWindow = MainWindow.getUpdateProject().getUpdateSOIPanel();
                StTableWindow = MainWindow.getUpdateProject().getUpdateSTPanel();
                CDWindow.setInputCD(INPUT_CD.getVis(), INPUT_CD.types());
                EventWindow.setEVCR(EVCR.getVis(), INPUT_CD.types());
                StObsWindow.setSOINV(SOINV.getVis(), INPUT_CD.types());
                StTableWindow.setST(ST.getVis());
                ProjectWindow.ok(new Report("Project Created", true));
	}

	/**
	 * 
	 * @param fName
	 * @return 
	 */
	public static void doOpenProject(File file) {
		//throw new UnsupportedOperationException();
            if (ProjectWindow!=null){
                doClose();
            }
            OPEN_PRJ=SER.deserialize(file);
            SOINV=OPEN_PRJ.getStObs();
            EVCR=OPEN_PRJ.getEvCR();
            ST=OPEN_PRJ.getStateTable();
            INPUT_CD=OPEN_PRJ.getInputCD();
            MainWindow.projectFrame=new UpdateProject(OPEN_PRJ.getVis());
            ProjectWindow=MainWindow.getUpdateProject();
            ProjectWindow.updateCDFrame = new UpdateInputCD(INPUT_CD.getVis(), INPUT_CD.types());
            ProjectWindow.updateECRPanel = new UpdateEventCondReact(EVCR.getVis(), INPUT_CD.types());
            ProjectWindow.updateSOIPanel = new UpdateStateObserverInv(SOINV.getVis(), INPUT_CD.types());
            ProjectWindow.updateSTPanel = new UpdateStateTable(ST.getVis(), SOINV.getVis().getSOVisSet(), EVCR.getVis());
            ProjectWindow.addTabs();
            MainWindow.addProjectWindow(ProjectWindow);
            CDWindow = MainWindow.getUpdateProject().getUpdateCDPanel();
            EventWindow = MainWindow.getUpdateProject().getUpdateECRPanel();
            StObsWindow = MainWindow.getUpdateProject().getUpdateSOIPanel();
            StTableWindow = MainWindow.getUpdateProject().getUpdateSTPanel();
            CDWindow.setInputCD(INPUT_CD.getVis(), INPUT_CD.types());
            EventWindow.setEVCR(EVCR.getVis(), INPUT_CD.types());
            StObsWindow.setSOINV(SOINV.getVis(), INPUT_CD.types());
            StTableWindow.setST(ST.getVis());
            ProjectWindow.ok(new Report("Project Opened", true));
	}
    
	/**
	 * 
	 * @param f
	 * @return 
	 */
	public static void passFile(File f) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public static void failed() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public static void doOpenInputCD() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param el
	 * @return 
	 */
	public static void doAddCDElement(CDElementInfo el) {
		//throw new UnsupportedOperationException();
            Report r=new Report();
                if (el instanceof ClassInfo){
                    r=INPUT_CD.addCDElement(new Class((ClassInfo)el));
                } else if (el instanceof EnumerationInfo){
                    r=INPUT_CD.addCDElement(new Enumeration((EnumerationInfo)el));
                } else if (el instanceof AttributeInfo){
                    r=INPUT_CD.addCDElement(new Attribute((AttributeInfo)el));
                } else if (el instanceof OperationInfo){
                    r=INPUT_CD.addCDElement(new Operation((OperationInfo)el));
                } else if (el instanceof AssociationInfo){
                    r=INPUT_CD.addCDElement(new Association((AssociationInfo)el));
                }
                CDWindow.setInputCD(INPUT_CD.getVis(), INPUT_CD.types());
                if (r.isOk()) ProjectWindow.ok(r);
                else ProjectWindow.ko(r);
	}

	/**
	 * 
	 * @param el
	 * @return 
	 */
	public static void doDeleteCDElement(CDElement el) {
            Report r=INPUT_CD.deleteCDElement(el);
            CDWindow.setInputCD(INPUT_CD.getVis(), INPUT_CD.types());
            if (r.isOk()) ProjectWindow.ok(r);
            else ProjectWindow.ko(r);
		//throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param el
	 * @param ElINnew
	 * @return 
	 */
	public static void doUpdCDElement(CDElement el, CDElementInfo ElINnew) {
            Report r=new Report();
            if (el instanceof Class){
                 r=INPUT_CD.updCDElement((Class)el, (ClassInfo)ElINnew);
            } else if (el instanceof Enumeration){
                 r=INPUT_CD.updCDElement((Enumeration)el, (EnumerationInfo)ElINnew);
            } else if (el instanceof Association){
                r=INPUT_CD.updCDElement((Association)el, (AssociationInfo)ElINnew);
            }
            CDWindow.setInputCD(INPUT_CD.getVis(), INPUT_CD.types());
            if (r.isOk()) ProjectWindow.ok(r);
            else ProjectWindow.ko(r);
            //throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param classes
	 * @return 
	 */
	public static void doSetContextClass(ClassInfo classes) {
            Report r=INPUT_CD.setContextClass(classes);
            CDWindow.setInputCD(INPUT_CD.getVis(), INPUT_CD.types());
            if (r.isOk()) ProjectWindow.ok(r);
            else ProjectWindow.ko(r);
		//throw new UnsupportedOperationException();
	}

	/**
	 * All subparts of el will be also validated
	 * @param el
	 * @return 
	 */
	public static void doValidate(Element el) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public static void doClose() {
		//throw new UnsupportedOperationException();
            SER.serialize(OPEN_PRJ);
            MainWindow.remove(ProjectWindow);
            MainWindow.validate();
            MainWindow.repaint();
	}

	/**
	 * 
	 * @return 
	 */
	public static void doOpenSOINV() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param el
	 * @param ElINnew
	 * @return 
	 */
	public static void doUpdStateObserver(StateObserver el, StateObserverInfo ElINnew) {
            Report r=SOINV.updateSO(el, ElINnew);
            StObsWindow.setSOINV(SOINV.getVis(), INPUT_CD.types());
            StTableWindow.setSO(SOINV.getVis().getSOVisSet());
            if (r.isOk()) ProjectWindow.ok(r);
            else ProjectWindow.ko(r);
            //throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param so
	 * @return 
	 */
	public static void doDeleteStateObserver(StateObserver so) {
            Report r=SOINV.deleteStateObserver(so);
            StObsWindow.setSOINV(SOINV.getVis(), INPUT_CD.types());
            StTableWindow.setSO(SOINV.getVis().getSOVisSet());
            if (r.isOk()) ProjectWindow.ok(r);
            else ProjectWindow.ko(r);
		//throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param el
	 * @return 
	 */
	public static void doAddStateObserver(StateObserverInfo el) {
            Report r=SOINV.addStateObserver(new StateObserver(el));
            StObsWindow.setSOINV(SOINV.getVis(), INPUT_CD.types());
            StTableWindow.setSO(SOINV.getVis().getSOVisSet());
            if (r.isOk()) ProjectWindow.ok(r);
            else ProjectWindow.ko(r);
	}

	/**
	 * 
	 * @return 
	 */
	public static void doGenerateBasicSO() {
		//throw new UnsupportedOperationException();
            Report r=new Report();
            if (INPUT_CD.getContextClass() != null){
                
                SOINV.addStateObservers(GEN.generateBasicSO(INPUT_CD, INPUT_CD.getContextClass()));
                StObsWindow.setSOINV(SOINV.getVis(), INPUT_CD.types());
                StTableWindow.setSO(SOINV.getVis().getSOVisSet());
                if (r.isOk()) ProjectWindow.ok(r);
                else ProjectWindow.ko(r);
            }
            else ProjectWindow.ko(new Report("No context class", true));
	}

	/**
	 * 
	 * @return 
	 */
	public static void doOpenEVCR() {
		throw new UnsupportedOperationException();
           
	}

	/**
	 * 
	 * @param el
	 * @return 
	 */
	public static void doAddEvent(EventInfo el) {
            Report r=EVCR.addEvent(new Event(el));
            EventWindow.setEVCR(EVCR.getVis(), INPUT_CD.types());
            if (r.isOk()) ProjectWindow.ok(r);
            else ProjectWindow.ko(r);
		//throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public static void doGenerateBasicEV() {
		//throw new UnsupportedOperationException();
            Report r;
             if (INPUT_CD.getContextClass() != null){

                r=EVCR.addEvents(GEN.generateBasicEV(INPUT_CD, INPUT_CD.getContextClass()));
                EventWindow.setEVCR(EVCR.getVis(), INPUT_CD.types());
                if (r.isOk()) ProjectWindow.ok(r);
                else ProjectWindow.ko(r);
            }
            else ProjectWindow.ko(new Report("No context class", true));
	}

	/**
	 * 
	 * @param el
	 * @return 
	 */
	public static void doDeleteEvent(Event el) {
		//throw new UnsupportedOperationException();
                Report r=EVCR.deleteEvent(el);
                EventWindow.setEVCR(EVCR.getVis(), INPUT_CD.types());
                if (r.isOk()) ProjectWindow.ok(r);
                else ProjectWindow.ko(r);

	}

	/**
	 * 
	 * @param el
	 * @param condReact
	 * @return 
	 */
	public static void doAddCR(Event el, CondReactInfo condReact) {
		//throw new UnsupportedOperationException();
                Report r=EVCR.addCR(el, condReact);
                EventWindow.setEVCR(EVCR.getVis(), INPUT_CD.types());
                if (r.isOk()) ProjectWindow.ok(r);
                else ProjectWindow.ko(r);
	}

	/**
	 * 
	 * @param cr
	 * @return 
	 */
	public static void doDeleteCR(Event el, CondReact cr) {
                Report r=EVCR.deleteCR(el, cr);
                EventWindow.setEVCR(EVCR.getVis(), INPUT_CD.types());
                if (r.isOk()) ProjectWindow.ok(r);
                else ProjectWindow.ko(r);
		//throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param condReact
	 * @param CRInew
	 * @return 
	 */
	public static void doUpdateCR(CondReact condReact, CondReactInfo CRInew) {
		//throw new UnsupportedOperationException();
                Report r=EVCR.updateCR(condReact, CRInew);
                EventWindow.setEVCR(EVCR.getVis(), INPUT_CD.types());
                if (r.isOk()) ProjectWindow.ok(r);
                else ProjectWindow.ko(r);
	}

	/**
	 * 
	 * @param el
	 * @param ElINnew
	 * @return 
	 */
	public static void doUpdEvent(Event el, EventInfo ElINnew) {
		//throw new UnsupportedOperationException();
            Report r=EVCR.updateEvent(el, ElINnew);
            EventWindow.setEVCR(EVCR.getVis(), new HashSet<Type>());
            if (r.isOk()) ProjectWindow.ok(r);
            else ProjectWindow.ko(r);

	}

	/**
	 * 
	 * @param usedSO
	 * @return 
	 */
	public static void doSelectUsedSO(List<StateObserverVis> usedSO) {
		//throw new UnsupportedOperationException();
                List<StateObserver> used=new LinkedList<StateObserver>();
                for(StateObserverVis sVis : usedSO){
                    for(StateObserver s: SOINV.getStateObs()){
                        if (s.getName().equals(sVis.getName())) used.add(s);
                    }
                }
            Report r=ST.setUsedSO(used);
            StTableWindow.setST(ST.getVis());
            if (r.isOk()) ProjectWindow.ok(r);
            else ProjectWindow.ko(r);
	}

	/**
	 * 
	 * @param splits
	 * @return 
	 */
	public static void doSplitCR(List<SplitInfo> splits) {
            EVCR.split(splits);
	}

	/**
	 * 
	 * @return 
	 */
	public static void doOpenST() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public static void doGenerateStates() {
		//throw new UnsupportedOperationException();
            Report r=ST.setROW(GEN.generateST(ST.getUsedStObs()));
            StTableWindow.setST(ST.getVis());
            if (r.isOk()) ProjectWindow.ok(r);
            else ProjectWindow.ko(r);
	}

	/**
	 * 
	 * @param row
	 * @param name
	 * @return 
	 */
	public static void doDefineState(Row row, String name) {
		//throw new UnsupportedOperationException();
            ST.defineState(row, name);
            StTableWindow.setST(ST.getVis());
	}

	/**
	 * 
	 * @return 
	 */
	public static void doSplitCR() {
		//throw new UnsupportedOperationException();
            StTableWindow.setEVCR(EVCR.getVis());
	}

	/**
	 * 
	 * @return 
	 */
	public static void doValidateST() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public static void doGenerateSM() {
            System.out.println("generazione SM");
            states=GEN.generateStates(ST);
            trans=new LinkedList<Transition>();

            for (State st : states){
                if (!st.getName().equals("Initial")){
                   System.out.println("Caso Generico");
                   for (Event ev : EVCR.getEvents()){
                        if (!ev.getName().equals("Created")){
                            System.out.println("Evento " + ev.getName());
                            for (CondReact cr : ev.getCR()){
                                ProjectWindow.examine(st.getRow(), cr, ev); 
                            }
                        }
                    }
                }
                else {
                    System.out.println("Caso Iniziale");
                    Event created=null;
                    for (Event e : EVCR.getEvents()){
                        if (e.getName().equals("Created")){
                            created=e;
                            System.out.println("Trovato l'evento " + created.getName());
                        }
                    }
                    System.out.println("Nome Stato" + st.getName() + " " + st.getRow().getName());
                    ProjectWindow.examine(st.getRow(), created.getCreatedCR(), created);
                }
            }
            for (State s : states) if (s.isFinal()) System.out.println(s.getName() + " è final");
             ProjectWindow.askTransitions(states, ST.getVis());
		//throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param row
	 * @return 
	 */
	public static void ok(TransitionInfo tr, State st) {
		//throw new UnsupportedOperationException();
           Transition t=GEN.generateTransition(tr, st);
           System.out.println("GENERATA TRANSIZIONE: " + t.getSource().getName() + t.getTarget().getName());
           trans.add(t);
//            System.out.println(preTrans.get(index)/*.getTrigger().getName() + " " + preTrans.get(index).getCondition().getExpr() + " " + preTrans.get(index).getReaction().getAct()*/);
//            System.out.println("Target:" + row.getValues().get(0).getVal()+ row.getValues().get(1).getVal()+ row.getValues().get(2).getVal());
	}

        public static void createSM(){
            System.out.println("CREATE SM:");
            if (trans==null) System.out.println("trans è NULL");
            for (Transition t : trans){
                System.out.println(t.getCondition().getExpr());
                //System.out.println(t.getSource().getName());
            }
            //OPEN_PRJ.setSM(new StateMachine(new StateMachineInfo(states, trans)));
            SM=new StateMachine(new StateMachineInfo(states, trans));
            OPEN_PRJ.setOutputCD(GEN.generateOutputCD(INPUT_CD, SOINV.getStateObs(), EVCR.getEvents(), ST.getUsedStObs()));
            ProjectWindow.showSM(SM.getVis(), OPEN_PRJ.getOutputCD().getVis());
        }

        public static List<State> getStates(){
            return states;
        }

        public class PreTransition{

            State st;
            Event ev;
            CondReact cr;

            PreTransition(State s, Event e, CondReact c){
                st=s;
                ev=e;
                cr=c;
            }

            public CondReact getCr() {
                return cr;
            }

            public Event getEv() {
                return ev;
            }

            public State getSt() {
                return st;
            }
        }

	public static void main(String[] args){
		MainWindow=new EasySM();
		INV=new Invalidator();
		FS=new FileSystem();
                GEN=new Generator();
                SER=new Serialization();

	}
}
