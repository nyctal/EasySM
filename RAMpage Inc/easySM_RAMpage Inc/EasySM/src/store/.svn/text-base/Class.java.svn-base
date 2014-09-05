package store;

import java.util.*;

import datatypes.*;

public class Class extends CDElement {
	
	Set<Attribute> attr;
	Set<Operation> op;

        public Class(ClassInfo clIN) {
            setName(clIN.getName());
            attr=new HashSet<Attribute>();
            op=new HashSet<Operation>();
	}

        public void setAttr(Set<Attribute> a){
            attr=a;
        }

        public void setOp(Set<Operation> o){
            op=o;
        }

        public void addAttr(Attribute a){
            attr.add(a);
        }

        public void addOp(Operation o){

            op.add(o);
        }

        public Set<Attribute> getAttr(){
            return attr;
        }

        public Set<Operation> getOp(){
            return op;
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
            if (!(obj instanceof Class)) return false;
            Class s=(Class)obj;
            return getName().equals(s.getName());
        }


} 
