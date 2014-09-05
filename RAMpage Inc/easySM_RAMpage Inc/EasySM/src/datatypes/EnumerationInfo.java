package datatypes;

import datatypes.*;
import java.util.Set;


public class EnumerationInfo extends CDElementInfo {

	private Set<String> litterals;

        public EnumerationInfo(Set<String> l, String n){
            setLitterals(l);
            setName(n);
        }

        public void setLitterals (Set<String> l){
            litterals=l;
        }

        public Set<String> getLitterals (){
            return litterals;
        }
}