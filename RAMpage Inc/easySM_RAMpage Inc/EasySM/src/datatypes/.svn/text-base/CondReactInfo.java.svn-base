package datatypes;

public class CondReactInfo extends ElementInfo {

	private Expr cond;
	private Action react;

        public CondReactInfo(String c, String r){
            if (c==null) cond=null;
                else setCond(new Expr(c));
            if (r==null) react=null;
                else setReact(new Action(r));
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
}