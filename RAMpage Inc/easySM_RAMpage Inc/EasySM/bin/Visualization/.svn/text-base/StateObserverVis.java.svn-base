package Visualization;

import boundaries.UpdateInputCD;
import boundaries.UpdateProject;
import datatypes.*;
import store.*;

public class StateObserverVis {
    String name;
    String type;
    String inv;
    StateObserver linkedSO;

    public StateObserverVis(String n, Type t, Expr i){
        name=n;
        if (t!=null) type=t.getName();
        if (i!=null) inv=i.getExpr();
    }

    public StateObserverVis(StateObserver s){
        name=s.getName();
        if (s.getType()!=null) type=s.getType().getName();
        //else s.getType()=new Type();
        if (s.getInv()!=null) inv=s.getInv().getExpr();
        //else inv="";
        
        linkedSO=s;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public String getInv(){
        return inv;
    }

    public void setName(String n){
        name=n;
    }

    public void setType(Type t){
        type=t.getName();
    }

    public void setInv(Expr e){
        inv=e.getExpr();
    }

    public String getFullString(){
        return "<html>" + name + ": " + UpdateProject.getColorString(type,"blue") + " - " + UpdateProject.getColorString(inv,"purple") + "<html>";
    }

    public StateObserver getLinkedSO(){
            return linkedSO;
        }
}