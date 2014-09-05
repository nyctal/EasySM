package datatypes;

import java.util.*;
import store.Type;

/**
 * Give all available types, operations, attributes, operators and literals, determined by the class diagram and by the predefined types
 * Technically it associates to the various names their kind and the the signature (if it is the case)
 */
public class Environment {

	private String val;
        private Set<Type> types;

        public Environment(){
            types=new HashSet<Type>();
            types.add(new Type().integer());
            types.add(new Type().bool());
            types.add(new Type().real());
            types.add(new Type().string());
        }

        public Environment(String v){
            setVal(v);
        }

        public void setVal(String v){
            val=v;
        }

        public String getVal(){
            return val;
        }

        public Set<Type> getTypes(){
            return types;
        }

        public void addType(Type t){
            types.add(t);
        }

        public void removeType(Type t){
            Type temp=null;
            for(Type t2 : types){
                if (t.getName().equals(t2.getName())) temp=t2;
            }
            types.remove(temp);
        }

        public Type getTypeFromString(String s){
            Type t=null;
            for (Type temp : types){
                if (temp.getName().equals(s)) t=temp;
            }
            return t;
        }

}