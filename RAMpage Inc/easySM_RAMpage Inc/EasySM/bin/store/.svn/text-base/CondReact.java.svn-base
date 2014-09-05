package store;

import datatypes.*;

public class CondReact extends Element {
	
	
	private Expr cond;
	private Expr condUsed;
	private Expr condRest;
	private Action react;
	private Action reactUsed;
	private Action reactRest;

        public CondReact(CondReactInfo crIn){
            if (crIn.getCond()==null || crIn.getCond().getExpr().equals("")){
                cond=new Expr("TRUE");
            } else setCond(crIn.getCond());
            if (crIn.getReact()==null || crIn.getReact().getAct().equals("")){
                react=new Action("IGNORED");
            } else setReact(crIn.getReact());
            condUsed=new Expr("");
            condRest=new Expr("");
            reactUsed=new Action("");
            reactRest=new Action("");
        }

        public void setCond(Expr c){
            cond=c;
        }

        public void setReact(Action r){
            react=r;
        }

        public Expr getCond(){
            return cond;
        }

        public Action getReact(){
            return react;
        }

        public void setCondUsed(Expr a){
            condUsed=a;
        }

        public void setActUsed(Action a){
            reactUsed=a;
        }

        public void setCondRest(Expr a){
            condRest=a;
        }

        public void setActRest(Action a){
            reactRest=a;
        }

        public Expr getCondUsed(){
            return condUsed;
        }

        public Action getActUsed(){
            return reactUsed;
        }

        public Expr getCondRest(){
            return condRest;
        }

        public Action getActRest(){
            return reactRest;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 37 * hash + (this.cond != null ? this.cond.hashCode() : 0);
            hash = 37 * hash + (this.react != null ? this.react.hashCode() : 0);
            return hash;
        }

        @Override
        public boolean equals(Object obj){
            if (obj==null) return false;
            if (!(obj instanceof CondReact)) return false;
            CondReact c=(CondReact)obj;
            return (getCond().equals(c.getCond()) && getReact().equals(c.getReact()) );
        }

}
