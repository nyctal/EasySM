package store;

import java.util.*;

import datatypes.*;

public class Event extends Element {

	Set<Parameter> params;
	Set<CondReact> cr;
	
        public Event(EventInfo evIN){
            setName(evIN.getName());
            params=new HashSet<Parameter>();
            for(ParameterInfo pi : evIN.getParam()){
                params.add(new Parameter(pi));
            }
            cr=new HashSet<CondReact>();
        }

        public Event(String n, Set<Parameter> p){
            setName(n);
            setParam(p);
            cr=new HashSet<CondReact>();
        }

        public void setParam(Set<Parameter> p){
            params=p;
        }


        public void setParamFromInfo(Set<ParameterInfo> p){
            params=new HashSet<Parameter>();
            for(ParameterInfo pi : p){
                params.add(new Parameter(pi));

            }
        }

        public Set<Parameter> getParam(){
            return params;
        }

        public void addCR(CondReactInfo crIn){
            cr.add(new CondReact(crIn));
        }

        public Set<CondReact> getCR (){
            return cr;
        }

        public CondReact getCreatedCR(){
            CondReact c=null;
            if (getName().equals("Created")){
                for (CondReact cor : cr) c=cor;
            }
            return c;
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
            if (!(obj instanceof Event)) return false;
            Event s=(Event)obj;
            return getName().equals(s.getName());
        }

}
