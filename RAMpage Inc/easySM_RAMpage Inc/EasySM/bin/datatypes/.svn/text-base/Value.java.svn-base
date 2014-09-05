package datatypes;

public class Value {

	private String val;

        public Value(String v){
            setVal(v);
        }

        public void setVal(String v){
            val=v;
        }

        public String getVal(){
            return val;
        }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.val != null ? this.val.hashCode() : 0);
        return hash;
    }

    @Override
        public boolean equals(Object obj){
            if (obj==null) return false;
            if (!(obj instanceof Value)) return false;
            Value s=(Value)obj;
            return getVal().equals(s.getVal());
        }
}