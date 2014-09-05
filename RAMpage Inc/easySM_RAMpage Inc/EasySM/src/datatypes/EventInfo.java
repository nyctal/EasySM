package datatypes;

import java.util.*;

public class EventInfo extends ElementInfo {

	private Set<ParameterInfo> parameters;

        public EventInfo(String n, Set<ParameterInfo> p){
            setName(n);
            setParam(p);
        }

        public void setParam(Set<ParameterInfo> p){
            parameters=p;
        }

        public Set<ParameterInfo> getParam(){
            return parameters;
        }
}