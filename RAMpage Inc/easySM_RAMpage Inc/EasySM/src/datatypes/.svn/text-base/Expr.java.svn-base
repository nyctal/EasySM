package datatypes;
import java.util.Set;


public class Expr {

      String expr;

      public Expr (String e){
          expr=e;
      }


      public Set<Error> check(Environment env) {
		throw new UnsupportedOperationException();
    }
      public String getExpr(){ return expr; }

      public boolean equals(Expr e){
        return expr.equals(e.getExpr());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + (this.expr != null ? this.expr.hashCode() : 0);
        return hash;
    }

    @Override
      public boolean equals(Object obj){
            if (obj==null) return false;
            if (!(obj instanceof Expr)) return false;
            Expr s=(Expr)obj;
            return getExpr().equals(s.getExpr());
        }
}
