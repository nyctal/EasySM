package datatypes;

import store.*;

public class SplitInfo {

	private CondReact CR;
	private Expr condUsed;
	private Expr condRest;
	private Action reactUsed;
	private Action reactRest;

        public SplitInfo(CondReact c){
            CR=c;
            condUsed=c.getCondUsed();
            condRest=c.getCondRest();
            reactUsed=c.getActUsed();
            reactRest=c.getActRest();
        }

        public SplitInfo(CondReact c, Expr c_u, Expr c_r, Action r_u, Action r_r){
            setCR(c);
            setCondUsed(c_u);
            setCondRest(c_r);
            setReactUsed(r_u);
            setReactRest(r_r);
        }

        public void setCR(CondReact c){
            CR=c;
        }

        public void setCondUsed(Expr c_u){
            condUsed=c_u;
        }

        public void setCondRest(Expr c_r){
            condRest=c_r;
        }

        public void setReactUsed(Action r_u){
            reactUsed=r_u;
        }

        public void setReactRest(Action r_r){
            reactRest=r_r;
        }

        public CondReact getCR(){
            return CR;
        }

        public Expr getCondUsed(){
            return condUsed;
        }

        public Expr getCondRest(){
            return condRest;
        }

        public Action getReactUsed(){
            return reactUsed;
        }

        public Action getReactRest(){
            return reactRest;
        }

}