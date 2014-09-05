package store;

import datatypes.*;
import datatypes.Error;
import java.util.*;
import Visualization.*;

public class EventCondReact extends Element {

        EventCondReactVis vis;
        Set<Event> events;

        public EventCondReact() {
		events = new HashSet<Event>();
                Event created=new Event(new EventInfo("Created", new HashSet<ParameterInfo>()));
                created.addCR(new CondReactInfo(null, null));
                events.add(created);
                vis = new EventCondReactVis(events);
	}

        public Set<Event> getEvents(){
            return events;
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
	public Report addEvent(Event so) {
		//throw new UnsupportedOperationException();
            boolean ok=true;
            if (events.contains(so)){
                ok=false;
                return new Report("An event called " + so.getName() + " already exists", ok);
            }
            else {
                events.add(so);
                vis=new EventCondReactVis(events);
                return new Report("Event " + so.getName() + " added", ok);
            }
	}

	/**
	 * 
	 * @param so
	 * @return 
	 */
	public Report addEvents(Set<Event> so) {
            boolean ok=true;
            String msg="These events already exist: ";
            for (Event e : so){
                if (events.contains(e)){
                    ok=false;
                    msg+=e.getName() + " ";
                }
                else events.add(e);
            }
            if (ok) {
                vis=new EventCondReactVis(events);
                return new Report("Basic events added", ok);
            } else {
                vis=new EventCondReactVis(events);
                return new Report(msg, ok);
            }
	}

	/**
	 * 
	 * @param so
	 * @return 
	 */
	public Report deleteEvent(Event so) {
            String name=so.getName();
            events.remove(so);
            vis = new EventCondReactVis(events);
            return new Report("Event " + name + " deleted", true);
	}

	/**
	 * 
	 * @param so
	 * @param Evnew
	 * @return 
	 */
	public Report updateEvent(Event so, EventInfo Evnew) {
            so.setName(Evnew.getName());
            so.setParamFromInfo(Evnew.getParam());
            vis = new EventCondReactVis(events);
            return new Report("Event " + so.getName() + " updated", true);
	}

	/**
	 * 
	 * @param so
	 * @param CRnew
	 * @return 
	 */
	public Report addCR(Event so, CondReactInfo CRnew) {
                so.addCR(CRnew);
                vis = new EventCondReactVis(events);
                return new Report("Condition Reaction added", true);
	}

	/**
	 * 
	 * @param cr
	 * @param CRnew
	 * @return 
	 */
	public Report updateCR(CondReact cr, CondReactInfo CRnew) {
            cr.setCond(CRnew.getCond());
            cr.setReact(CRnew.getReact());
            vis = new EventCondReactVis(events);
            return new Report("Condition Reaction updated" , true);
	}

	/**
	 * 
	 * @param nCR
	 * @return 
	 */
	public Report deleteCR(Event el, CondReact nCR) {
            el.getCR().remove(nCR);
            vis = new EventCondReactVis(events);
            return new Report("Condition Reaction deleted" , true);
	}

	/**
	 * 
	 * @param splittedCR
	 * @return 
	 */
	public void split(List<SplitInfo> splittedCR) {
            for (SplitInfo sp : splittedCR){
                if (sp.getReactRest()!=null){
                    sp.getCR().setActRest(sp.getReactRest());
                    System.out.println("actRest :" + sp.getReactRest().getAct());
                } else sp.getCR().setActRest(new Action(""));
                if (sp.getReactUsed()!=null){
                    sp.getCR().setActUsed(sp.getReactUsed());
                    System.out.println("actUsed :" + sp.getReactUsed().getAct());
                } else sp.getCR().setActUsed(new Action(""));
                if (sp.getCondRest()!=null){
                    sp.getCR().setCondRest(sp.getCondRest());
                    System.out.println("condRest :" + sp.getCondRest().getExpr());
                } else sp.getCR().setCondRest(new Expr(""));
                if (sp.getCondUsed()!=null){
                    sp.getCR().setCondUsed(sp.getCondUsed());
                    System.out.println("condUsed :" + sp.getCondUsed().getExpr());
                } else sp.getCR().setCondUsed(new Expr(""));
            }
		//throw new UnsupportedOperationException();
	}

        public EventCondReactVis getVis(){
            return vis;
        }
}
