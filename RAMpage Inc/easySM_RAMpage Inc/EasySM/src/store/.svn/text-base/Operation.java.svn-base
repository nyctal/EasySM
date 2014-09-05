package store;

import java.util.*;

import datatypes.*;

public class Operation extends CDElement {
	Type returnType;
	Set<Parameter> parameters;

        public Operation(OperationInfo opIN){
            setName(opIN.getName());
            setReturnType(opIN.getReturnType());
            setContainer(opIN.getOfClass());
            parameters=new HashSet<Parameter>();
            for(ParameterInfo pi : opIN.getParam()){
                parameters.add(new Parameter(pi));
            }
        }

        public Operation(String name, Set<Parameter> par, Class cont, Type rt){
            setName(name);
            parameters=par;
            returnType=rt;
            setContainer(cont);
        }

        public void setParam(Set<Parameter> p){
            parameters=p;
        }

        /*public void setOfClass(store.Class c){
            ofClass=c;
        }*/

        public void setReturnType(Type t){
            returnType=t;
        }


        public Set<Parameter> getParam(){
            return parameters;
        }

        /*public store.Class getOfClass(){
            return ofClass;
        }*/

        public Type getReturnType(){
            return returnType;
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
            if (!(obj instanceof Operation)) return false;
            Operation s=(Operation)obj;
            return getName().equals(s.getName());
        }

}
