package Visualization;

import java.util.*;
import store.*;

public class EventCondReactVis {
    public Vector<EventVis> evVis;
    public Vector<String> evString;

    public class EventVis{
        String name;
        Vector<ParamVis> par;
        Vector<CRVis> cr;
        Event linkedEvent;

        public EventVis(Event e){
            name=e.getName();
            par=new Vector<ParamVis>();
            for (Parameter p : e.getParam()){
                par.add(new ParamVis(p));
            }
            cr=new Vector<CRVis>();
            for (CondReact c : e.getCR()){
                cr.add(new CRVis(c));
            }
            linkedEvent=e;
        }

        public String getName(){
            return name;
        }

        public Vector<ParamVis> getParam(){
            return par;
        }

        public String getFullString(){
            String parList = "";
            for(int i=0; i<par.size(); i++){
                parList = parList + par.get(i).getFullString();
                if(i != par.size()-1)                        
                        parList = parList + ", ";
            }

            return parList;
        }

        public Vector<CRVis> getCR(){
            return cr;
        }

        public Vector<String> getCondStrings(){
            Vector<String> v=new Vector<String>();
            for (CRVis crv : cr){
                v.add(crv.getCondVis());
            }
            return v;
        }

        public Vector<String> getReactStrings(){
            Vector<String> v=new Vector<String>();
            for (CRVis crv : cr){
                v.add(crv.getReactVis());
            }
            return v;
        }

        public Event getLinkedEvent(){
            return linkedEvent;
        }
    }

    public class CRVis {
        String cond;
        String react;
        String condRest;
        String reactRest;
        String condUsed;
        String reactUsed;
        CondReact linkedCR;

        public CRVis(CondReact c){
            cond=c.getCond().getExpr();
            react=c.getReact().getAct();
            condRest=c.getCondRest().getExpr();
            condUsed=c.getCondUsed().getExpr();
            reactRest=c.getActRest().getAct();
            reactUsed=c.getActUsed().getAct();
            linkedCR=c;
        }

        public String getCondVis(){
            return cond;
        }

        public String getReactVis(){
            return react;
        }

        public String getFullString(){
            return cond + " " + react;
        }

        public CondReact getLinkedCR(){
            return linkedCR;
        }

        public String getCondRest() {
            return condRest;
        }

        public String getCondUsed() {
            return condUsed;
        }

        public String getReactRest() {
            return reactRest;
        }

        public String getReactUsed() {
            return reactUsed;
        }

        public String getCondReactRest(){
            return condRest + " " + reactRest;
        }

        public String getCondReactUsed(){
            return condUsed + " " + reactUsed;
        }
    }

    public EventCondReactVis(Set<Event> e){
        evVis=new Vector<EventVis>();
        evString=new Vector<String>();
        for(Event ev: e){
            evVis.add(new EventVis(ev));
            evString.add(ev.getName());
        }
        
    }

    public Vector<EventVis> getEvents(){
        return evVis;
    }

    public Vector<String> getEventNames(){
        return evString;
    }

    public Vector<String> getEventFullStrings(){
        Vector<String> eventFull = new Vector<String>();
        for(EventVis e : evVis){
            eventFull.add(e.getFullString());
        }
        return eventFull;
    }

    public Set<CRVis> getFullCondReactVis(){
        Set<CRVis> vis=new HashSet<CRVis>();
        for(EventVis e : getEvents()){
            for (CRVis c : e.getCR()){
                vis.add(c);
            }
        }
        return vis;
    }
}