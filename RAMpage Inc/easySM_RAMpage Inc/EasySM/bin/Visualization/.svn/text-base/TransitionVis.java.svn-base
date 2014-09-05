/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Visualization;

import datatypes.TransitionInfo;
import java.util.Vector;

/**
 *
 * @author davide
 */
public class TransitionVis {
    Vector<TransitionInfo> infoVect;

    public TransitionVis(){
        infoVect=new Vector<TransitionInfo>();
    }

    public void add(TransitionInfo t){
        infoVect.add(t);
    }

    public Vector<TransitionInfo> getInfoVect() {
        return infoVect;
    }

    public int getSize(){
        return infoVect.size();
    }

    public Object[][] getObjectArray(){
        Object[][] data=new Object[infoVect.size()][4];
        int i;
        for (i=0; i<infoVect.size(); i++){
            data[i][0]=infoVect.elementAt(i).getR().getName();
            data[i][1]=infoVect.elementAt(i).getE().getName();
            data[i][2]=infoVect.elementAt(i).getCR().getCondRest().getExpr();
            data[i][3]=infoVect.elementAt(i).getCR().getActRest().getAct();
        }
        return data;
    }

    public Object[][] getInitialObjectArray(){
        TransitionInfo initial=infoVect.firstElement();
        Object[][] data=new Object[1][5];
        System.out.println(infoVect);
        data[0][0]=initial.getR().getName();
        data[0][1]=initial.getE().getName();
        data[0][2]=initial.getCR().getCondRest().getExpr();
        data[0][3]=initial.getCR().getActRest().getAct();
        return data;
    }
    
    public Object[][] getObjectArrayForState(String stateName){
        Vector<TransitionInfo> temp=new Vector<TransitionInfo>();
        for (TransitionInfo ti : infoVect){
            if (ti.getR().getName().equals(stateName)) temp.add(ti);
        }
        Object[][] data=new Object[temp.size()][5];
        int i;
        for (i=0; i<temp.size(); i++){
            data[i][0]=temp.elementAt(i).getR().getName();
            data[i][1]=temp.elementAt(i).getE().getName();
            data[i][2]=temp.elementAt(i).getCR().getCondRest().getExpr();
            data[i][3]=temp.elementAt(i).getCR().getActRest().getAct();
        }
        return data;
    }


}
