package store;

import datatypes.*;
import java.util.*;


public class Row {
	private List<Value> values;
	private String name;

        public Row(List<Value> v, String n){
            setValues(v);
            setName(n);
        }

        public Row(List<Value> v){
            setValues(v);
        }

        public void setValues(List<Value> v){
            values=v;
        }

        public List<Value> getValues(){
            return values;
        }

        public void setName(String n){
            name=n;
        }

        public String getName(){
            return name;
        }

        public boolean isFinal(){
            return getValues().get(getValues().size()-1).getVal().equals("TRUE");
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 71 * hash + (this.values != null ? this.values.hashCode() : 0);
            return hash;
        }

        @Override
        public boolean equals(Object obj){
            if (obj==null) return false;
            if (!(obj instanceof Row)) return false;
            Row s=(Row)obj;
            boolean risp=true;
            int i=0;
            if (s.getValues()==null || values==null){
                risp = name.equals(s.getName());
            } else {
                for (i=0; i<s.getValues().size(); i++){
                    risp=risp && values.get(i).equals(s.getValues().get(i));
                }
            }
            
            return risp;
        }
        
}
