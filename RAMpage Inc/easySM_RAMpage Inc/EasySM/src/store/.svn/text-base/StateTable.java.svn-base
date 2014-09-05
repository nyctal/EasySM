package store;


import Visualization.*;
import datatypes.*;
import datatypes.Error;
import java.util.*;

public class StateTable extends Element {

	
	StateTableVis vis;
	List<StateObserver> used;
	List<Row> rows;
	
	public StateTable() {
		used=new LinkedList<StateObserver>();
                rows=new LinkedList<Row>();
                vis = new StateTableVis(used, rows);
	}


	/**
	 * 
	 * @return 
	 */
	public Set<Error> check() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param soUsed
	 * @return 
	 */
	public Report setUsedSO(List<StateObserver> soUsed) {
            used=soUsed;
            vis = new StateTableVis(used, rows);
            return new Report("Used State Observer selected", true);
	}

	/**
	 * 
	 * @param newRows
	 * @return 
	 */
	public Report setROW(List<Row> newRows) {
            rows=newRows;
            vis=new StateTableVis(used, rows);
            return new Report("State Table generated", true);
	}


	/**
	 * 
	 * @param row
	 * @param name
	 * @return 
	 */
	public void defineState(Row row, String name) {
            row.setName(name);
            vis = new StateTableVis(used, rows);
	}

        public StateTableVis getVis(){
            return vis;
        }

        public List<StateObserver> getUsedStObs(){
            return used;
        }

        public List<Row> getRows(){
            return rows;
        }

}
