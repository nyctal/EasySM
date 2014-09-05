package datatypes;

import store.*;
import store.Class;
import java.util.*;

public class OperationInfo extends CDElementInfo {

	private store.Class ofClass;
	private Set<ParameterInfo> parameters;
	private Type returnType;

        public OperationInfo(String n, Set<ParameterInfo> p, Class c, Type t){
            setName(n);
            setParam(p);
            setReturnType(t);
            setOfClass(c);
        }

        public void setParam(Set<ParameterInfo> p){
            parameters=p;
        }

        public void setOfClass(store.Class c){
            ofClass=c;
        }

        public void setReturnType(Type t){
            returnType=t;
        }


        public Set<ParameterInfo> getParam(){
            return parameters;
        }

        public store.Class getOfClass(){
            return ofClass;
        }

        public Type getReturnType(){
            return returnType;
        }
}