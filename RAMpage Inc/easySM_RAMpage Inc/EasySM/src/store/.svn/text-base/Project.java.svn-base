package store;
import java.util.Set;

import Visualization.*;
import java.io.File;
import java.io.Serializable;


public class Project implements Serializable{
	ClassDiagram inputCD;
	ClassDiagram outputCD;
	ProjectVis vis;
	private String name;
        private String path;
        private File currentFile;
	StateObserverInv stObs;
	EventCondReact ev;
	StateTable stTable;
	StateMachine stMachine;

        private static final long serialVersionUID = 1L;


	/**
	 * 
	 * @param name
	 * @return 
	 */
	public Project(File file) {
            setPath(file.getPath());
            setName(file.getName());
            setCurrentFile(file);
            stObs=new StateObserverInv();
            ev=new EventCondReact();
            stTable=new StateTable();
            inputCD=new ClassDiagram();
	}

        public ProjectVis getVis(){
            return vis;
        }

        public void setStObs(StateObserverInv s){
            stObs=s;
        }


        public void setEvCR(EventCondReact e){
            ev=e;
        }

        public void setStTable(StateTable s){
            stTable=s;
        }

        public void setInputCD(ClassDiagram c){
            inputCD=c;
        }

        public StateObserverInv getStObs(){
            return stObs;
        }

        public EventCondReact getEvCR(){
            return ev;
        }

        public StateTable getStateTable(){
            return stTable;
        }

        public ClassDiagram getInputCD(){
            return inputCD;
        }

        public ClassDiagram getOutputCD(){
            return outputCD;
        }

        public void setName(String n){
            name=n;
        }
        public void setPath(String p){
            path=p;
        }
        public void setCurrentFile(File file){
            currentFile=file;
        }
        public File getCurrentFile(){
            return currentFile;
        }
        public String getName(){
            return name;
        }
        public String getPath(){
            return name;
        }
	/**
	 * 
	 * @param sn
	 * @return 
	 */
	public void setSM(StateMachine sn) {
            stMachine=sn;
	}

	/**
	 * 
	 * @param cd
	 * @return 
	 */
	public void setOutputCD(ClassDiagram cd) {
             outputCD=cd;
	}

        public void clear(){
            
        }
}
