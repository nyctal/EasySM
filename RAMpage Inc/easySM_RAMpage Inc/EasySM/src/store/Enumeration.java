package store;

import datatypes.*;
import java.util.Set;


public class Enumeration extends CDElement {
	private Set<String> litterals;

	public Enumeration(EnumerationInfo enIN){
            setLitterals(enIN.getLitterals());
            setName(enIN.getName());
        }

        public void setLitterals (Set<String> l){
            litterals=l;
        }

        public Set<String> getLitterals (){
            return litterals;
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
            if (!(obj instanceof Enumeration)) return false;
            Enumeration s=(Enumeration)obj;
            return getName().equals(s.getName());
        }
}
