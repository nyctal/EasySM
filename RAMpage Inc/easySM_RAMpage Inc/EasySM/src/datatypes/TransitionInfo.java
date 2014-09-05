/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datatypes;


import store.CondReact;
import store.Event;
import store.Row;

/**
 *
 * @author davide
 */
public class TransitionInfo {
        Row r;
        CondReact c;
        Event e;

        public TransitionInfo(Row row, CondReact cr, Event ev){
            r=row;
            c=cr;
            e=ev;
        }

//        public Object[] getTableRow(){
//
//        }

        public String getFullString(){
            String tStr="Row: ";
            for (Value v : r.getValues()){
                tStr = tStr + v.getVal() + " ";
            }
            tStr=tStr + " Cond: " + c.getCond().getExpr() + " React: " + c.getReact().getAct();
            return tStr;
        }

        public CondReact getCR() {
            return c;
        }

        public Row getR() {
            return r;
        }

        public Event getE(){
            return e;
        }


}
