package store;

import datatypes.*;

public class Parameter extends Element {
	
	Type type;

        public Parameter(ParameterInfo parIN){
            System.out.println("costruttore di parameter");
            setName(parIN.getName());
            setType(parIN.getReturnType());
            System.out.println(getName());
        }

        public void setType(Type t){
            type=t;
        }

        public Type getType(){
            return type;
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
            if (!(obj instanceof Parameter)) return false;
            Parameter s=(Parameter)obj;
            return getName().equals(s.getName());
        }

}
