package store;

/**
 * inv:
 * not (isFinal and isInitial)
 * State.allInstances()->select(isInitial).size() = 1
 */
public class State {

        private Row row;
	private String name;
	private boolean isFinal;
	private boolean isInitial;
        
        public State(String n){
            setName(n);
        }

        public State(Row r){
            setRow(r);
            setName(r.getName());
        }

        public void setName(String n){
            name=n;
        }

        public String getName(){
            return name;
        }

        public boolean isFinal(){
            return isFinal;
        }

        public boolean isInitial(){
            return isInitial;
        }

        public void setFinal(){
            isFinal=true;
            isInitial=false;
        }

        public void setInitial(){
            isInitial=true;
            isFinal=false;
        }

        public void notFinal(){
            isFinal=false;
        }

        public void notInitial(){
            isInitial=false;
        }

        public void setRow(Row r){
            row=r;
        }

        public Row getRow(){
            return row;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 67 * hash + (this.getName() != null ? this.getName().hashCode() : 0);
            return hash;
        }


        @Override
        public boolean equals(Object obj){
            if (obj==null) return false;
            if (!(obj instanceof State)) return false;
            State s=(State)obj;
            return getName().equals(s.getName());
        }
}
