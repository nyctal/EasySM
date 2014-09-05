package store;

import datatypes.*;

public class Attribute extends CDElement {
	
	Type type;


	public Attribute(AttributeInfo attIN) {
		setName(attIN.getName());
                setType(attIN.getType());
                setContainer(attIN.getOfClass());
	}

        public Attribute(String n, Type t){
            setName(n);
            setType(t);
        }

        /*
        public void setOfClass(store.Class c){
            ofClass=c;
        }*/

        public void setType(Type t){
            type=t;
        }
        /*
        public store.Class getOfClass(){
            return ofClass;
        }*/

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
            if (!(obj instanceof Attribute)) return false;
            Attribute s=(Attribute)obj;
            return getName().equals(s.getName());
        }
}
