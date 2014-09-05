package Visualization;

import boundaries.UpdateProject;
import datatypes.ClassInfo;
import java.util.*;
import store.*;
import store.Class;
import store.Enumeration;

public class ClassDiagramVis {
    public Vector<ClassVis> class_vis;
    public Vector<EnumVis> enum_vis;
    public Vector<AssocVis> assoc_vis;
    String Context_class;


    public ClassDiagramVis(){
        class_vis=new Vector<ClassVis>();
        enum_vis=new Vector<EnumVis>();
        assoc_vis=new Vector<AssocVis>();
    }

    public ClassDiagramVis(Set<Class> c, Set<Enumeration> e, Set<Association> a, Class cont){
        class_vis=new Vector<ClassVis>();
        enum_vis=new Vector<EnumVis>();
        assoc_vis=new Vector<AssocVis>();

        for(Class cl : c){
            class_vis.add(new ClassVis(cl));
        }
        for(Enumeration en : e){
            enum_vis.add(new EnumVis(en));
        }
        if(cont != null){
            Context_class=cont.getName();
        }
        for(Association as : a){
            assoc_vis.add(new AssocVis(as));
        }
        

    }

    public String getContextClass(){
        return Context_class;
    }

    public void addClass(ClassVis c){
        class_vis.add(c);
    }

    public void addEnum(EnumVis e){
        enum_vis.add(e);
    }

    public Vector<ClassVis> getClassVis(){
        return class_vis;
    }

    public ClassVis getClassVisByName(String n){
        for(ClassVis i : class_vis)
            if(i.getName().equals(n)) return i;
        return new ClassVis("");
    }

    public Vector<EnumVis> getEnumVis(){
        return enum_vis;
    }

    public Vector<AssocVis> getAssocVis(){
        return assoc_vis;
    }

    public Vector<String> getClassString(){
        Vector<String> s=new Vector<String>();
        for (ClassVis c : class_vis){
            s.add(c.getName());
        }
        return s;
    }

    public Vector<String> getEnumString(){
        Vector<String> s=new Vector<String>();
        for (EnumVis e : enum_vis){
            s.add(e.getName());
        }
        return s;
    }

    public Vector<String> getAssocString(){
        Vector<String> s=new Vector<String>();
        for(AssocVis a : assoc_vis){
            s.add(a.getFullString());
        }
        return s;
    }

    public class ClassVis {
        String name;
        Vector<OpVis> op;
        Vector<AttrVis> attr;
        Class linkedClass;

        public ClassVis(Class c){
            name=c.getName();
            op=new Vector<OpVis>();
            attr=new Vector<AttrVis>();
            for (Operation oper: c.getOp()){
                op.add(new OpVis(oper));
            }
            for (Attribute at: c.getAttr()){
                attr.add(new AttrVis(at));
            }
            linkedClass=c;
        }

//        public ClassVis(String n, Set<Attribute> a, Set<Operation> o){
//            name=n;
//            op=new Vector<OpVis>();
//            attr=new Vector<AttrVis>();
//            for (Operation oper: o){
//                op.add(new OpVis(oper));
//            }
//            for (Attribute at: a){
//                attr.add(new AttrVis(at));
//            }
//        }

        public ClassVis(String n){
            name=n;
            op=new Vector<OpVis>();
            attr=new Vector<AttrVis>();
        }

        public String getName(){
            return name;
        }

        public void addOp(Operation o){
            op.add(new OpVis(o));
        }
        
        public void addAttr(Attribute a){
            attr.add(new AttrVis(a));
        }

        public Vector<OpVis> getOp(){
            return op;
        }

        public Vector<AttrVis> getAttr(){
            return attr;
        }

        public Vector<String> getOpStrings(){
            Vector<String> s=new Vector<String>();
            for (OpVis o : op){
                s.add(o.getFullString());
            }
            return s;
        }

        public Vector<String> getAttrStrings(){
            Vector<String> s = new Vector<String>();
            for (AttrVis a : attr){
                s.add("<html>" + a.getFullString() + "</html>");
            }
            return s;
        }

        public Class getLinkedClass(){
            return linkedClass;
        }
        

    }

    public class EnumVis{
        String name;
        Vector<String> lit;
        Enumeration linkedEnum;

//        public EnumVis(String n, Set<String> l){
//            name=n;
//            lit = new Vector<String>();
//            for (String s: l){
//                lit.add(s);
//            }
//        }

        public EnumVis(Enumeration e){
            name=e.getName();
            lit = new Vector<String>();
            for (String s: e.getLitterals()){
                lit.add(s);
            }
            linkedEnum=e;
        }

        public String getName(){
            return name;
        }

        public Vector<String> getLit(){
            return lit;
        }

        public Enumeration getLinkedEnum(){
            return linkedEnum;
        }
    }

    public class AttrVis{
        String type;
        String name;
        Attribute linkedAttr;

        public AttrVis(Attribute p){
            type=p.getType().getName();
            name=p.getName();
            linkedAttr=p;
        }

        public String getType(){
            return type;
        }
        public String getName(){
            return name;
        }
        public String getFullString(){
            return name + " : " + UpdateProject.getColorString(type, "blue");
        }

        public Attribute getLinkedAttr(){
            return linkedAttr;
        }
    }

    public class OpVis{
        String name;
        Vector<ParamVis> par;
        String retType;
        Operation linkedOp;

        public OpVis(Operation o){
            name=o.getName();
            retType=o.getReturnType().getName();
            par=new Vector<ParamVis>();
            for (Parameter p : o.getParam()){
                //System.out.println(p.getName());
                par.add(new ParamVis(p));
            }
            linkedOp=o;
        }

        public String getName(){
            return name;
        }

        public Vector<ParamVis> getParam(){
            return par;
        }

        public String getRetType(){
            return retType;
        }

        public String getFullString(){
            String temp=retType + " " + name + " (";
            for (ParamVis p : par){
                temp=temp + p.getFullString() + " ";
            }
            temp=temp + ")";
            return temp;
        }

        public Operation getLinkedOp(){
            return linkedOp;
        }
    }

    public class AssocVis {
        String source;
        String dest;
        String name;
        String sourceName;
        String destName;
        Association linkedAssoc;

        public AssocVis(Association a){
            source=a.getSource().getName();
            dest=a.getDest().getName();
            name=a.getName();
            sourceName=a.getSourceName();
            destName=a.getDestName();
            linkedAssoc=a;
        }

        public String getDest() {
            return dest;
        }

        public String getDestName() {
            return destName;
        }

        public Association getLinkedAssoc() {
            return linkedAssoc;
        }

        public String getName() {
            return name;
        }

        public String getSource() {
            return source;
        }

        public String getSourceName() {
            return sourceName;
        }

        public String getFullString(){
            return "From " + source + " to " + dest + " : " + name;
        }
    }

}