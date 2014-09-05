package store;

import java.util.Set;

import Visualization.*;
import Visualization.ClassDiagramVis.ClassVis;
import Visualization.ClassDiagramVis.EnumVis;
import datatypes.*;
import java.util.Set;
import datatypes.Error;
import java.util.HashSet;

/**
 * types() returns all the available types, i.e., the predefined ones and those introduced by the class diagram
 */
public class ClassDiagram extends Element {

        Set<Class> classes;
	Set<Enumeration> enums;
        Set<Association> assoc;
	Class context;
	ClassDiagramVis vis;
        Environment env;

        public ClassDiagram() {
            classes=new HashSet<Class>();
            enums=new HashSet<Enumeration>();
            assoc=new HashSet<Association>();
            vis=new ClassDiagramVis();
            env=new Environment();

            /*** PROVA ***/

//            Class c=new Class(new ClassInfo("ClasseProva"));
//            Class c2=new Class(new ClassInfo("ClasseProva2"));
//            //c.addOp(new Operation(new OperationInfo()))
//            c.addAttr(new Attribute(new AttributeInfo(c, new Type().bool(), "attr1")));
//            c.addAttr(new Attribute(new AttributeInfo(c, new Type().bool(), "attr2")));
//            c.addAttr(new Attribute(new AttributeInfo(c, new Type().bool(), "attr3")));
//             c.addAttr(new Attribute(new AttributeInfo(c, new Type().bool(), "attr4")));
//            ParameterInfo p=new ParameterInfo(new Type().bool(), "par1");
//            ParameterInfo p2=new ParameterInfo(new Type().bool(), "par2");
//            Set<ParameterInfo> par=new HashSet<ParameterInfo>();
//            par.add(p);
//            par.add(p2);
//            c.addOp(new Operation(new OperationInfo("Oper1", par, c, new Type().real())));
//            c.addOp(new Operation(new OperationInfo("Oper2", par, c, new Type().real())));
//            classes.add(c);
//            classes.add(c2);
//            context=c;
//            Association a=new Association(new AssociationInfo("Ass.Prova", c, c2, "N1", "N2"));
//            assoc.add(a);
//            env.addType(new Type().asType(c));
//            env.addType(new Type().asType(c2));
            vis=new ClassDiagramVis(classes, enums, assoc, context);
            /*************/
	}

        public ClassDiagram(Set<Class> cl, Set<Enumeration> en, Set<Association> as, Class cont){
            classes=cl;
            enums=en;
            assoc=as;
            context=cont;
            vis=new ClassDiagramVis(classes, enums, assoc, context);
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
	 * @return 
	 */
	public Set<Type> types() {
		//throw new UnsupportedOperationException();
            return env.getTypes();
	}

	/**
	 * 
	 * @return 
	 */
	public Environment env() {
		//throw new UnsupportedOperationException();
            return env;
	}

	/**
	 * 
	 * @param cdEl
	 * @return 
	 */
	public Report addCDElement(CDElement cdEl) {
            boolean ok;
            String msg;
            if (cdEl instanceof Class){
                if (classes.contains((Class)cdEl)){
                    ok=false;
                    return new Report("A Class named " + cdEl.getName() + " already exist", ok);
                } else {
                    ok=true;
                    classes.add((Class)cdEl);
                    Type t=new Type().asType((Class)cdEl);
                    env.addType(t);
                    vis=new ClassDiagramVis(classes, enums, assoc, context);
                    return new Report("Class " + cdEl.getName() + " added", ok);
                }
            } else if (cdEl instanceof Enumeration){
                if (enums.contains((Enumeration)cdEl)){
                    ok=false;
                    return new Report("An Enumeration named " + cdEl.getName() + " already exist", ok);
                } else {
                    ok=true;
                    enums.add((Enumeration)cdEl);
                    Type t=new Type().asType((Enumeration)cdEl);
                    env.addType(t);
                    vis=new ClassDiagramVis(classes, enums, assoc, context);
                    return new Report("Enumeration " + cdEl.getName() + " added", ok);
                }              
            } else if (cdEl instanceof Attribute){
                Class c=null;
                for (Class c1 : classes){
                    if (c1.getName().equals(cdEl.containedIn().getName())){
                        c=c1;
                    }
                }
                c.attr.add((Attribute)cdEl);
                vis=new ClassDiagramVis(classes, enums, assoc, context);
                return new Report("Attribute Added", true);
            } else if (cdEl instanceof Operation){
                Class c=null;
                for (Class c1 : classes){
                    if (c1.getName().equals(cdEl.containedIn().getName())){
                        c=c1;
                    }
                }
                c.op.add((Operation)cdEl);
                vis=new ClassDiagramVis(classes, enums, assoc, context);
                return new Report("Operation Added", true);
            } else if (cdEl instanceof Association){
                if (assoc.contains((Association)cdEl)){
                    ok=false;
                    return new Report("An Association named " + cdEl.getName() + " already exist", ok);
                } else {
                    ok=true;
                    assoc.add((Association)cdEl);
                    vis=new ClassDiagramVis(classes, enums, assoc, context);
                    return new Report("Association added", ok);
                }
		//throw new UnsupportedOperationException();
            } else return new Report("", true);
        }

	/**
	 * 
	 * @param el
	 * @return 
	 */
	public Report deleteCDElement(CDElement el) {
            if (el instanceof Class){

                classes.remove((Class)el);
                if (context.equals((Class)el)) context=null;
                env.removeType(new Type().asType((Class)el));

            } else if (el instanceof Enumeration){

                enums.remove((Enumeration)el);
                env.removeType(new Type().asType((Enumeration)el));
            } else if (el instanceof Attribute){

                Class c=(Class)el.containedIn();
                c.attr.remove((Attribute)el);

            } else if (el instanceof Operation){

                Class c=(Class)el.containedIn();
                c.op.remove((Operation)el);

            } else if (el instanceof Association){
                assoc.remove((Association)el);
            }
            vis=new ClassDiagramVis(classes, enums, assoc, context);
            return new Report(el.getName() + " deleted", true);
	}

	/**
	 * 
	 * @param el
	 * @param ElINnew
	 * @return 
	 */
	public Report updCDElement(Enumeration el, EnumerationInfo ElINnew) {
           
                enums.remove((Enumeration)el);
                env.removeType(new Type().asType((Enumeration)el));
                Enumeration enNew=new Enumeration((EnumerationInfo)ElINnew);
                enums.add(enNew);
                env.addType(new Type().asType(enNew));

            vis=new ClassDiagramVis(classes, enums, assoc, context);
            return new Report("Enumeration " + el.getName() + " updated", true);
		//throw new UnsupportedOperationException();
	}

        public Report updCDElement(Class el, ClassInfo ElINnew) {

                env.removeType(new Type().asType((Class)el));
                el.op.clear();
                el.attr.clear();
                el.setName(ElINnew.getName());
                env.addType(new Type().asType((Class)el));

            vis=new ClassDiagramVis(classes, enums, assoc, context);
            return new Report("Class " + el.getName() + " updated", true);
		//throw new UnsupportedOperationException();
	}

        public Report updCDElement(Association el, AssociationInfo ElINnew) {

            el.setDest(ElINnew.getDest());
            el.setSource(ElINnew.getSource());
            el.setName(ElINnew.getName());
            el.setDestName(ElINnew.getDestName());
            el.setSourceName(ElINnew.getSourceName());

            vis=new ClassDiagramVis(classes, enums, assoc, context);
            return new Report("Association " + el.getName() + " updated", true);
		//throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param c
	 * @return 
	 */
	public Report setContextClass(ClassInfo c) {
            System.out.println("Nella boundary:" + c.getName());
            for (Class cl : classes){
                if (c.getName().equals(cl.getName())){
                    context=cl;
                    System.out.println("Nuova Context: " + cl.getName());
                }
            }
            vis=new ClassDiagramVis(classes, enums, assoc, context);
            return new Report(c.getName() + " is the new context class", true);
		//throw new UnsupportedOperationException();
	}

        public Class getContextClass(){
            return context;
        }

        public ClassDiagramVis getVis(){
            return vis;
        }

        public Set<Association> getAssoc() {
            return assoc;
        }

        public Set<Class> getClasses() {
            return classes;
        }

        public Set<Enumeration> getEnums() {
            return enums;
        }


}
