package datatypes;
import java.util.Set;


public class Action {

    String act;

    public Action(String a){
        act=a;
    }

    public String getAct(){
        return act;
    }

    public Set<Error> check(Environment env) {
		throw new UnsupportedOperationException();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (this.act != null ? this.act.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj){
            if (obj==null) return false;
            if (!(obj instanceof Action)) return false;
            Action s=(Action)obj;
            return getAct().equals(s.getAct());
        }
}