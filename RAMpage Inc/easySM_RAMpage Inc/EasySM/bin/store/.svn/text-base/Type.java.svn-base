package store;

import datatypes.*;
import java.util.*;


public class Type extends Element {

        private List<Value> values;
        boolean finite;

        public Type() {
		
	}

	/**
	 * 
	 * @return 
	 */
	public Type integer() {
		setName("Int");
                finite=false;
                return this;
	}

	/**
	 * 
	 * @return 
	 */
	public Type bool() {
		setName("Boolean");
                finite=true;
                values = new LinkedList<Value>();
                values.add(new Value("TRUE"));
                values.add(new Value("FALSE"));
                return this;
	}

	/**
	 * 
	 * @return 
	 */
	public Type real() {
		setName("Real");
                finite=false;
                return this;
	}

	/**
	 * 
	 * @return 
	 */
	public Type string() {
		setName("String");
                finite=false;
                return this;
	}

	/**
	 * 
	 * @param c
	 * @return 
	 */
	public Type asType(Class c) {
            setName(c.getName());
            finite=false;
            return this;
	}

	/**
	 * 
	 * @param e
	 * @return 
	 */
	public Type asType(Enumeration e) {
            setName(e.getName());
            finite=true;
            values=new LinkedList<Value>();
            for (String l : e.getLitterals()){
                values.add(new Value(l));
            }
            return this;
	}

	/**
	 * 
	 * @return 
	 */
	public boolean isFinite() {
		return finite;
	}

	/**
	 * 
	 * @return 
	 */
	public List<Value> values() {
		if (isFinite()) return values;
                else return null;
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
            if (!(obj instanceof Type)) return false;
            Type s=(Type)obj;
            return getName().equals(s.getName());
        }

        public String getTypeVis(){
            String vis=getName();
            return vis;
        }


}
