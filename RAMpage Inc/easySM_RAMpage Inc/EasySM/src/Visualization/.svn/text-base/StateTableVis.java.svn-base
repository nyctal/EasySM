package Visualization;

import java.util.*;
import store.*;
import datatypes.*;

public class StateTableVis {

    Vector<RowVis> rowVis;
    Vector<StateObserverVis> usedSoVis;

    public StateTableVis(List<StateObserver> s, List<Row> r){
        rowVis=new Vector<RowVis>();
        usedSoVis=new Vector<StateObserverVis>();
        for (StateObserver so : s){
            usedSoVis.add(new StateObserverVis(so));
        }
        for (Row ro: r){
            rowVis.add(new RowVis(ro));
        }
    }

    public Vector<RowVis> getRowVis(){
        return rowVis;
    }

    public Vector<StateObserverVis> getUsedSoVis(){
        return usedSoVis;
    }

    public Vector<String> getUsedSoNames(){
        Vector<String> v=new Vector<String>();
        for (StateObserverVis so : usedSoVis){
            v.add(so.getName());
        }
        return v;
    }

    public String[] getSOarray(){
        String[] o=new String[usedSoVis.size()+1];
        int i;
        for (i=0; i<usedSoVis.size(); i++){
            o[i]=usedSoVis.elementAt(i).getName();
        }
        o[i]="NOME STATO";
        return o;
    }

    public Object[][] getROWarray(){
        Object[][] o=new Object[rowVis.size()][usedSoVis.size()+1];
        int i, j;
        for (i=0; i<rowVis.size(); i++){
            for (j=0; j<usedSoVis.size(); j++){
                o[i][j]=rowVis.elementAt(i).values.elementAt(j);
            }
            o[i][j]=rowVis.elementAt(i).stateName;
        }
        return o;
    }

    public Object[][] getStateArray(){
        Vector<RowVis> temp=new Vector<RowVis>();
        for (RowVis r : rowVis){
            if (!r.getStateName().equals("Impossible")) temp.add(r);
        }
        Object[][] o=new Object[temp.size()][usedSoVis.size()+1];
        int i, j;
        for (i=0; i<temp.size(); i++){
            for (j=0; j<usedSoVis.size(); j++){
                o[i][j]=temp.elementAt(i).values.elementAt(j);
            }
            o[i][j]=temp.elementAt(i).stateName;
        }
        return o;
    }

    public Object[][] getNormalStateArray(){
        Vector<RowVis> temp=new Vector<RowVis>();
        for (RowVis r : rowVis){
            if (!r.getStateName().equals("Impossible") && !r.isFinal()) temp.add(r);
        }
        Object[][] o=new Object[temp.size()][usedSoVis.size()+1];
        int i, j;
        for (i=0; i<temp.size(); i++){
            for (j=0; j<usedSoVis.size(); j++){
                o[i][j]=temp.elementAt(i).values.elementAt(j);
            }
            o[i][j]=temp.elementAt(i).stateName;
        }
        return o;
    }

    public Object[][] getFinalStateArray(){
        Vector<RowVis> temp=new Vector<RowVis>();
        for (RowVis r : rowVis){
            if (!r.getStateName().equals("Impossible") && r.isFinal()) temp.add(r);
        }
        Object[][] o=new Object[temp.size()][usedSoVis.size()+1];
        int i, j;
        for (i=0; i<temp.size(); i++){
            for (j=0; j<usedSoVis.size(); j++){
                o[i][j]=temp.elementAt(i).values.elementAt(j);
            }
            o[i][j]=temp.elementAt(i).stateName;
        }
        return o;
    }

    public class RowVis {

        String stateName;
        Vector<String> values;
        Row linkedRow;

        public RowVis(Row r){
            linkedRow=r;
            stateName=r.getName();
            values=new Vector<String>();
            for(Value v : r.getValues()){
                values.add(v.getVal());
            }
        }

        public String getStateName(){
            return stateName;
        }

        public Vector<String> getValues(){
            return values;
        }

        public Row getLinkedRow(){
            return linkedRow;
        }

        public boolean isFinal(){
            return linkedRow.isFinal();
        }

    }
}