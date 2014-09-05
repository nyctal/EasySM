package boundaries;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.*;

import java.awt.*;

import Visualization.*;
import Visualization.ClassDiagramVis.*;
import Visualization.ClassDiagramVis.ClassVis;
import datatypes.*;
import datatypes.Error;
import executors.Main;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListDataListener;
import store.*;
import store.Class;
import java.util.*;

import java.awt.event.*;
import java.nio.channels.ClosedByInterruptException;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class UpdateInputCD extends JPanel implements BaseBoundary {
    private ClassDiagramVis currentInputCD;
    private static Set<Type> availableTypes;

    private JList EDT;
    private JList CLS;
    private JList ASC;
    
    private JTextField contextCl;

    private JToggleButton selectedButton;
    private JFrame upperFrame;
    
    private JToggleButton addEnum,editEnum;
    private JButton delEnum;
    private JToggleButton addCl,editCl;
    private JButton delCl;
    private JToggleButton addAs,editAs;
    private JButton delAs;

    private AddDataTFrame addDTF,editDTF;

    private AddClassFrame addClF,editClF;
    private AddCAttrFrame addCAF,editCAF;
    private AddCOperFrame addCOF,editCOF;

    private AddAssocFrame addAsF,editAsF;
    
    private MyActionListener actionL;
    private MyListSelListener listselL;
    private MyKeyAdapter keyL;
    private MyWindowAdpt windowL;
    private MyItemListener itemL;
    private MyMouseAdapter mouseL;

    private JPopupMenu popup;
    private JMenuItem toCont;
    private JButton setCont;

    private int selectedClass;
    private Vector<String> classVect;

    public UpdateInputCD(ClassDiagramVis cdDVis, Set<Type> types){
        currentInputCD = cdDVis;
        availableTypes = types;

        JPanel eSouth,cSouth,aSouth,cBack,context;
        JPanel enumDT,classes,assoc;
        
        
        setBorder(BorderFactory.createTitledBorder(null,"Update Class Diagram in Input",TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,new Font("Dialog",Font.BOLD, 12),new Color(51, 51, 51)));
        setName("Class Diagram");
        setLayout(new GridLayout(1,3));

        windowL = new MyWindowAdpt();
        listselL = new MyListSelListener();
        actionL = new MyActionListener();
        keyL = new MyKeyAdapter();
        itemL = new MyItemListener();
        mouseL = new MyMouseAdapter();

        enumDT =    UpdateProject.getTitledPanel("Enumeration Datatypes");
        classes =   UpdateProject.getTitledPanel("Classes");
        assoc =     UpdateProject.getTitledPanel("Associations");

        EDT = new JList();
        EDT.setForeground(Color.ORANGE);
        EDT.setFont(new Font("Arial", Font.BOLD, 12));
        EDT.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        EDT.addListSelectionListener(listselL);

        CLS = new JList();
        CLS.setForeground(Color.MAGENTA);
        CLS.setFont(new Font("Arial", Font.BOLD, 12));
        CLS.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        CLS.addListSelectionListener(listselL);
        CLS.addMouseListener(mouseL);

        popup = new JPopupMenu();
        toCont = new JMenuItem("Set as Context Class");
        
        toCont.addActionListener(actionL);
        popup.add(toCont);
        
        ASC = new JList();
        ASC.setForeground(Color.BLUE);
        ASC.setFont(new Font("Arial", Font.BOLD, 12));
        ASC.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        ASC.addListSelectionListener(listselL);

        addEnum =   new JToggleButton("Add Enumeration");
        editEnum =  new JToggleButton("Edit Enumeration");
        editEnum.setEnabled(false);
        delEnum =   new JButton("Delete Enumeration");
        delEnum.setEnabled(false);

        addCl =     new JToggleButton("Add Class");
        editCl =    new JToggleButton("Edit Class");
        editCl.setEnabled(false);
        delCl =     new JButton("Delete Class");
        delCl.setEnabled(false);

        addAs =     new JToggleButton("Add Association");
        editAs =    new JToggleButton("Edit Association");
        editAs.setEnabled(false);
        delAs =     new JButton("Delete Association");
        delAs.setEnabled(false);


        addEnum.addActionListener(actionL);
        editEnum.addActionListener(actionL);
        delEnum.addActionListener(actionL);

        addCl.addActionListener(actionL);
        editCl.addActionListener(actionL);
        delCl.addActionListener(actionL);

        addAs.addActionListener(actionL);
        editAs.addActionListener(actionL);
        delAs.addActionListener(actionL);

        eSouth = new JPanel(new GridLayout(3,1));

        eSouth.add(addEnum);
        eSouth.add(editEnum);
        eSouth.add(delEnum);

        cSouth = new JPanel(new GridLayout(3,1));

        cSouth.add(addCl);
        cSouth.add(editCl);
        cSouth.add(delCl);

        aSouth = new JPanel(new GridLayout(3,1));

        aSouth.add(addAs);
        aSouth.add(editAs);
        aSouth.add(delAs);
        
        cBack = new JPanel();
        cBack.setLayout(new BorderLayout());
        cBack.add(new JScrollPane(CLS),BorderLayout.CENTER);
        context = UpdateProject.getTitledPanel("Context");
        contextCl = new JTextField();
        contextCl.setEditable(false);
        setCont = new JButton("Set");
        setCont.setEnabled(false);
        setCont.addActionListener(actionL);
        
        context.add(contextCl,BorderLayout.CENTER);
        context.add(setCont,BorderLayout.EAST);

        cBack.add(context,BorderLayout.SOUTH);

        enumDT.add(new JScrollPane(EDT),BorderLayout.CENTER);
        classes.add(cBack,BorderLayout.CENTER);
        assoc.add(new JScrollPane(ASC),BorderLayout.CENTER);

        enumDT.add( eSouth,BorderLayout.SOUTH);
        classes.add(cSouth,BorderLayout.SOUTH);
        assoc.add(  aSouth,BorderLayout.SOUTH);

        add(enumDT);
        add(classes);
        add(assoc);
    }

    public JToggleButton getAddEnum(){      return addEnum;     }
    public JToggleButton getEditEnum(){     return editEnum;    }
    public JButton getDelEnum(){            return delEnum;     }
    public JToggleButton getAddCl(){        return addCl;       }
    public JToggleButton getEditCl(){       return editCl;      }
    public JButton getDelCl(){              return delCl;       }    
    public JToggleButton getAddAs(){        return addAs;       }
    public JToggleButton getEditAs(){       return editAs;      }
    public JButton getDelAs(){              return delAs;       }
    public JTextField getContextCl(){		return contextCl;	}
    public JPopupMenu getPopup(){			return popup;		}
    public JMenuItem getToCont(){			return toCont;		}
    public static Set<Type> getAvailableTypes(){
        return availableTypes;
    }
    public void setContextCl(JTextField contextCl){
        this.contextCl = contextCl;
    }
    public void setPopup(JPopupMenu popup){
        this.popup = popup;
    }
    public void setToCont(JMenuItem toCont){
        this.toCont = toCont;
    }

    /**
     *
     * @param cdDVis
     * @param types
     * @return
     */
    public void setInputCD(ClassDiagramVis cdVis, Set<Type> types) {
        currentInputCD = cdVis;
        availableTypes = types;
        
        EDT.setListData(currentInputCD.getEnumString());
        CLS.setListData(currentInputCD.getClassString());
        ASC.setListData(currentInputCD.getAssocString());
        contextCl.setText(currentInputCD.getContextClass());
    }

    /**
     *
     * @param errs
     * @return
     */
    public void correct(Set<Error> errs) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param el
     * @return
     */
    public void addCDElement(CDElementInfo el) {
        Main.doAddCDElement(el);
    }

    /**
     *
     * @param el
     * @return
     */
    public void deleteCDElement(CDElement el) {
        Main.doDeleteCDElement(el);
    }

    /**
     *
     * @param el
     * @param ElIN
     * @return
     */
    public void updCDElement(CDElement el, CDElementInfo ElIN) {
        Main.doUpdCDElement(el, ElIN);
    }

    /**
     *
     * @param classes
     * @return
     */
    public void setContextClass() {
        String className = currentInputCD.getClassString().elementAt(CLS.getSelectedIndex());

        Main.doSetContextClass(new ClassInfo(className));
    }

    @Override
    public void ko(Report r) {
        // TODO Auto-generated method stub
    }

    @Override
    public void ok(Report r) {
        // TODO Auto-generated method stub
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public void validate(Element el) {
        // TODO Auto-generated method stub
    }

//--------------- Frame Aggiuntivi ---------------------------------------------
    class AddDataTFrame extends JFrame{
        private JButton addLB, delLB, okEB;
        private JList lit;
        private JTextField nameE,nameL;
        private JLabel error;
        private Vector<String> litV;
        private Set<String> litS;
        private store.Enumeration originalDataT;

        public AddDataTFrame(){
            JPanel north,center,south;
            JPanel back;
            JPanel lNorth;

            setTitle("Add Enumeration");
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ico.png")));
            addWindowListener(windowL);

            back = new JPanel(new BorderLayout(5,5));
            north = UpdateProject.getTitledPanel("Name");
            north.setLayout(new GridLayout());
            center = UpdateProject.getTitledPanel("Literals");
            south = new JPanel(new BorderLayout());
            lNorth = new JPanel(new BorderLayout());

            addLB = new JButton("Add");
            delLB = new JButton("Delete Literal");
            okEB = new JButton("Ok");

            addLB.addActionListener(actionL);
            addLB.setEnabled(false);
            delLB.addActionListener(actionL);
            delLB.setEnabled(false);
            okEB.addActionListener(actionL);
            okEB.setEnabled(false);

            nameL = new JTextField();
            nameL.addKeyListener(keyL);

            lNorth.add(nameL,BorderLayout.CENTER);
            lNorth.add(addLB,BorderLayout.EAST);
            
            lit = new JList();
            lit.setLayoutOrientation(JList.VERTICAL);
            lit.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            lit.addListSelectionListener(listselL);

            litS = new HashSet<String>();
            litV = new Vector<String>();

            nameE = new JTextField();
            nameE.addKeyListener(keyL);

            north.add(nameE);
            lit.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
            center.add(new JScrollPane(lit),BorderLayout.CENTER);

            center.add(delLB,BorderLayout.SOUTH);
            center.add(lNorth,BorderLayout.NORTH);

            south.add(okEB, BorderLayout.EAST);

            error = new JLabel(" Enumeration name empty");
            error.setForeground(new Color(255,0,0));

            south.add(error,BorderLayout.CENTER);

            back.add(north,BorderLayout.NORTH);
            back.add(center,BorderLayout.CENTER);
            back.add(south, BorderLayout.SOUTH);

            setPreferredSize(new Dimension(300,300));
            setResizable(false);
            add(back);
            pack();
            setLocationRelativeTo(null);
        }
        
        public JButton getAddLB(){      return addLB;   }
        public JButton getDelLB(){      return delLB;   }
        public JButton getOkEB(){       return okEB;    }
        public JList getLit(){          return lit;     }
        public JTextField getNameE(){   return nameE;   }
        public JTextField getNameL(){   return nameL;   }
        public JLabel getError(){   return error;   }

        public void addLiteral(){
            litV.add(nameL.getText());
            lit.setListData(litV); 
            addLB.setEnabled(false);
            litS.add(nameL.getText());
            nameL.setText("");
        }
        public void deleteLiteral(){
            litS.remove(litV.get(lit.getSelectedIndex()));
            litV.remove(lit.getSelectedIndex());
            lit.setListData(litV);
        }
    }

    class AddClassFrame extends JFrame{
        private JButton addAB,delAB,addOB,delOB,okCB;

        private JList attrL;
        private JList operL;

        private JTextField nameC;

        private Vector<AttributeInfo> attrVect;
        private Vector<OperationInfo> operVect;
        private Vector<String> attrStrings;
        private Vector<String> operStrings;
        private Vector<String> attrNames;
        
        private JLabel error;
        
        private Class OriginalClass;

        public AddClassFrame(){
            attrVect=new Vector<AttributeInfo>();
            operVect=new Vector<OperationInfo>();
            attrStrings = new Vector<String>();
            operStrings = new Vector<String>();
            attrNames = new Vector<String>();

            JPanel north, center, south;
            JPanel back;
            JPanel attr, oper;
            JPanel aSouth, oSouth;

            setTitle("Add Class");
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ico.png")));
            addWindowListener(windowL);

            back =      new JPanel(new BorderLayout());
            north = UpdateProject.getTitledPanel("Name");
            north.setLayout(new GridLayout());
            center =    new JPanel(new GridLayout());
            south =     new JPanel(new BorderLayout());
            attr = UpdateProject.getTitledPanel("Attributes");
            oper = UpdateProject.getTitledPanel("Operations");
            aSouth =    new JPanel(new GridLayout(2,1));
            oSouth =    new JPanel(new GridLayout(2,1));

            addAB =  new JButton("Add Attribute");
            delAB =  new JButton("Delete Attribute");
            delAB.setEnabled(false);
            addOB =  new JButton("Add Operation");
            delOB =  new JButton("Delete Operation");
            delOB.setEnabled(false);
            okCB =    new JButton("Ok");
            okCB.setEnabled(false);
            
            error = new JLabel(" Class name empty");
            error.setForeground(new Color(255,0,0));

            addAB.addActionListener(actionL);
            delAB.addActionListener(actionL);
            addOB.addActionListener(actionL);
            delOB.addActionListener(actionL);
            okCB.addActionListener(actionL);

            attrL = new JList();
            attrL.addListSelectionListener(listselL);
            operL = new JList();
            operL.addListSelectionListener(listselL);

            nameC = new JTextField();
            nameC.addKeyListener(keyL);

            north.add(nameC);

            attrL.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
            attr.add(new JScrollPane(attrL), BorderLayout.CENTER);

            aSouth.add(addAB);
            aSouth.add(delAB);

            attr.add(aSouth, BorderLayout.SOUTH);

            operL.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
            oper.add(new JScrollPane(operL), BorderLayout.CENTER);

            oSouth.add(addOB);
            oSouth.add(delOB);

            oper.add(oSouth, BorderLayout.SOUTH);

            center.add(attr);
            center.add(oper);

            back.add(north,BorderLayout.NORTH);
            back.add(center,BorderLayout.CENTER);

            south.add(okCB, BorderLayout.EAST);
            south.add(error, BorderLayout.CENTER);
            
            back.add(south, BorderLayout.SOUTH);

            setPreferredSize(new Dimension(500,400));
            setResizable(false);
            add(back);
            pack();
            setLocationRelativeTo(null);
        }

        public JTextField getNameC() {
            return nameC;
        }
        public void setNameC(JTextField nameC) {
            this.nameC = nameC;
        }
        public JButton getAddAB(){      return addAB;           }
        public JButton getDelAB(){      return delAB;           }
        public JButton getAddOB(){      return addOB;           }
        public JButton getDelOB(){      return delOB;           }
        public JButton getOkCB(){       return okCB;            }
        public JList getAttrL(){    return attrL;   }
        public JList getOperL(){    return operL;   }
        public JLabel getError() {
            return error;
        }
        public Vector<String> getAttrNames(){  return attrNames;    }
        
        public void setError(JLabel error) {
            this.error = error;
        }
        public void addAttr(String n, String t){
            attrVect.add(new AttributeInfo(null,UpdateProject.getType(t),n));
            attrNames.add(n);
            attrStrings.add("<html>" + n + ": " + UpdateProject.getColorString(t,"blue") + "</html>");
            attrL.setListData(attrStrings);
        }
        public void removeAttr(){   
            attrVect.remove(attrL.getSelectedIndex());
            attrStrings.remove(attrL.getSelectedIndex());
            attrL.setListData(attrStrings);
        }
        public void addOper(String n, Set<ParameterInfo> p,String t){ 
            operVect.add(new OperationInfo(n,p,null,UpdateProject.getType(t)));
            operStrings.add(n + "(" + paramString(p) + ")" + (!t.equals("void")? ": " + t:""));
            operL.setListData(operStrings);
        }
        public void removeOper(){   
            operVect.remove(operL.getSelectedIndex());
            operStrings.remove(operL.getSelectedIndex());
            operL.setListData(operStrings);
        }
        private String paramString(Set<ParameterInfo> p){
            String pList = "";
            int i=0;
            for(Iterator it = p.iterator(); it.hasNext(); it.next(), i++){
                pList = pList + p.iterator().next().getName() + ": " + p.iterator().next().getReturnType().getName();
                if(i != p.size()-1)
                    pList = pList + ", ";
            }
            return pList;
        }
    }

    class AddCAttrFrame extends JFrame{
        private JTextField nameA;
        private JComboBox typeA;
        private JButton okAB;
        private JLabel error;

        public AddCAttrFrame(){
            JPanel center, south, back;
            JPanel nameP, typeP;

            setTitle("Add Attribute");
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ico.png")));
            addWindowListener(windowL);

            back = new JPanel(new BorderLayout());
            center = new JPanel(new GridLayout(2,1));
            south = new JPanel(new BorderLayout());
            
            nameP = UpdateProject.getTitledPanel("Name");
            nameP.setLayout(new GridLayout());
            typeP = UpdateProject.getTitledPanel("Type");
            typeP.setLayout(new GridLayout());

            nameA = new JTextField();
            nameA.addKeyListener(keyL);
            nameP.add(nameA);
            typeA = UpdateProject.getTypeList("Choose type");
            

            typeA.addItemListener(itemL);

            typeP.add(typeA);

            okAB = new JButton("Ok");
            okAB.setEnabled(false);
            okAB.addActionListener(actionL);
            error = new JLabel(" Attribute name empty");
            error.setForeground(new Color(255, 0, 0));

            south.add(okAB,BorderLayout.EAST);
            south.add(error,BorderLayout.CENTER);

            center.add(nameP);
            center.add(typeP);

            back.add(center,BorderLayout.CENTER);
            back.add(south, BorderLayout.SOUTH);
            setPreferredSize(new Dimension(300,165));
            setResizable(false);
            add(back);
            pack();
            setLocationRelativeTo(null);
        }

        public JButton getOkAB(){   return okAB;   }
        public JTextField getNameA(){   return nameA; }
        public JComboBox getTypeA(){   return typeA; }
        private JLabel getError(){  return error;   }
    }
    
    class AddCOperFrame extends JFrame{
        private JButton addCOPB, delCOPB, okCOB;
        private JList par;
        private JTextField nameCO;

        private JTextField nameP;
        private JComboBox typeP,typeORT;

        Set<ParameterInfo> parSet;
        Vector<ParameterInfo> parVec;
        Vector<String> parStrings;

        public AddCOperFrame(){
            parSet = new HashSet<ParameterInfo>();
            parVec = new Vector<ParameterInfo>();
            parStrings = new Vector<String>();

            JPanel north,center,south;
            JPanel back;
            JPanel pNorth;

            setTitle("Add Operation");
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ico.png")));
            addWindowListener(windowL);

            back = new JPanel(new BorderLayout(5,5));
            north = UpdateProject.getTitledPanel("Name and return type");
            center = UpdateProject.getTitledPanel("Parameters");
            south = new JPanel(new BorderLayout());
            pNorth = new JPanel(new BorderLayout());

            addCOPB = new JButton("Add");
            addCOPB.setEnabled(false);
            delCOPB = new JButton("Delete Parameter");
            delCOPB.setEnabled(false);
            okCOB = new JButton("Ok");
            okCOB.setEnabled(false);

            addCOPB.addActionListener(actionL);
            delCOPB.addActionListener(actionL);
            okCOB.addActionListener(actionL);

            par = new JList();
            par.setLayoutOrientation(JList.VERTICAL);
            par.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            par.addListSelectionListener(listselL);

            nameCO = new JTextField();
            nameCO.addKeyListener(keyL);

            north.add(nameCO,BorderLayout.CENTER);
            par.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
            center.add(new JScrollPane(par),BorderLayout.CENTER);

            typeP = UpdateProject.getTypeList("Choose type");
            typeP.addItemListener(itemL);
            
            typeORT = UpdateProject.getTypeList("void");

            north.add(typeORT,BorderLayout.WEST);

            nameP = new JTextField();
            nameP.addKeyListener(keyL);

            pNorth.add(typeP, BorderLayout.WEST);
            pNorth.add(nameP, BorderLayout.CENTER);
            pNorth.add(addCOPB, BorderLayout.EAST);

            center.add(delCOPB,BorderLayout.SOUTH);
            center.add(pNorth,BorderLayout.NORTH);

            south.add(okCOB, BorderLayout.EAST);

            back.add(north,BorderLayout.NORTH);
            back.add(center,BorderLayout.CENTER);
            back.add(south, BorderLayout.SOUTH);

            setPreferredSize(new Dimension(300,300));
            setResizable(false);
            add(back);
            pack();
            setLocationRelativeTo(null);
        }

        public JButton getAddCOPB(){        return addCOPB;     }
        public JButton getDelCOPB(){        return delCOPB;     }
        public JButton getOkCOB(){          return okCOB;       }
        public JList getPar(){              return par;         }
        public JTextField getNameCO(){      return nameCO;      }
        public JTextField getNameP(){       return nameP;       }
        public JComboBox getTypeP(){        return typeP;       }
        public JComboBox getTypeORT(){      return typeORT;     }

        public void addParameter(String n , String t){
            ParameterInfo p = new ParameterInfo(UpdateProject.getType(t), n);
            parSet.add(p);
            parVec.add(p);
            parStrings.add(n + ": " + t);
            par.setListData(parStrings);
            typeP.setSelectedIndex(0);
            nameP.setText("");
            addCOPB.setEnabled(false);
        }
        public void deleteParameter() {
            parSet.remove(parVec.get(par.getSelectedIndex()));
            parVec.remove(par.getSelectedIndex());
            parStrings.remove(par.getSelectedIndex());
            par.setListData(parStrings);
        }
    }

    class AddAssocFrame extends JFrame{
        private JButton okAsB;
        private JTextField nameAs, end1, end2;

        private JComboBox classFrom, classTo;

        private Association OriginalAssoc;

        public AddAssocFrame(){
            JPanel north, center, south;
            JPanel west, east;
            JPanel back;
            JLabel endN1, endN2;

            setTitle("Add Association");
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ico.png")));
            addWindowListener(windowL);

            back =      new JPanel(new BorderLayout(5,5));
            center =    new JPanel(new GridLayout(1,2));
            west = UpdateProject.getTitledPanel("From");
            west.setLayout(new GridLayout(3,1));
            east = UpdateProject.getTitledPanel("To");
            east.setLayout(new GridLayout(3,1));
            north = UpdateProject.getTitledPanel("Name");
            north.setLayout(new GridLayout());
            south =     new JPanel(new BorderLayout());

            okAsB = new JButton("Ok");

            okAsB.addActionListener(actionL);

            nameAs = new JTextField();


            north.add(nameAs);

            classFrom = new JComboBox();
            classTo = new JComboBox();

            for(String i: currentInputCD.getClassString()){
                classFrom.addItem(i);
                classTo.addItem(i);
            }
            

            endN1 = new JLabel("End Name");
            endN2 = new JLabel("End Name");

            end1 = new JTextField();
            end2 = new JTextField();

            west.add(classFrom);
            west.add(endN1);
            west.add(end1);

            east.add(classTo);
            east.add(endN2);
            east.add(end2);

            center.add(west);
            center.add(east);

            south.add(okAsB, BorderLayout.EAST);

            back.add(north,BorderLayout.NORTH);
            back.add(center,BorderLayout.CENTER);
            back.add(south, BorderLayout.SOUTH);

            setPreferredSize(new Dimension(300,200));
            add(back);
            pack();
            setLocationRelativeTo(null);
        }

        public JButton getOkB(){            return okAsB;       }
        public JTextField getNameAs(){      return nameAs;        }
        public JTextField getEnd1(){        return end1;        }
        public JTextField getEnd2(){        return end2;        }
        public JComboBox getClassFrom(){    return classFrom;   }
        public JComboBox getClassTo(){      return classTo;     }
    }

//--------------- Listeners ----------------------------------------------------    
    class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String s;
            boolean on;

            System.out.println("holy fuckin' shit");

            if(e.getSource().equals(toCont) || e.getSource().equals(setCont)){
                int ret = JOptionPane.showConfirmDialog(null, "Are you sure you want change the context class? \nthis update will cause loss of data", "Change context class", JOptionPane.OK_CANCEL_OPTION);
                if(ret == JOptionPane.OK_OPTION)
                    setContextClass();
            }
//-------------------- Tasti Pannello principale -------------------------------
            if(e.getSource().equals(addEnum)){
                selectedButton = addEnum;
                upperFrame = addDTF = new AddDataTFrame();
                addDTF.setVisible(true);
            }
            else if(e.getSource().equals(editEnum)){
                selectedButton = editEnum;
                upperFrame = editDTF = new AddDataTFrame();
                editDTF.setTitle("Edit Enumeration");
                editDTF.getError().setText("");
                editDTF.getOkEB().setEnabled(true);
                
                editDTF.originalDataT = currentInputCD.getEnumVis().elementAt(EDT.getSelectedIndex()).getLinkedEnum();
                editDTF.nameE.setText(currentInputCD.getEnumString().elementAt(EDT.getSelectedIndex()));
                editDTF.litV=currentInputCD.getEnumVis().elementAt(EDT.getSelectedIndex()).getLit();
                for (String str : editDTF.litV){
                    editDTF.litS.add(str);
                }
                editDTF.lit.setListData(editDTF.litV);
                editDTF.okEB.setEnabled(true);
                editDTF.setVisible(true);
            }
            else if(e.getSource().equals(delEnum)){
                System.out.println("Enumer: " + EDT.getSelectedIndex());
                System.out.println(currentInputCD.enum_vis.elementAt(EDT.getSelectedIndex()).getLinkedEnum().getName());
                deleteCDElement(currentInputCD.enum_vis.elementAt(EDT.getSelectedIndex()).getLinkedEnum());
                EDT.setListData(currentInputCD.getEnumString());
            }
            else if(e.getSource().equals(addCl)){
                selectedButton = addCl;
                upperFrame = addClF = new AddClassFrame();
                addClF.setVisible(true);
            }
            else if(e.getSource().equals(editCl)){
                selectedButton = editCl;
                upperFrame = editClF = new AddClassFrame();
                editClF.setTitle("Edit Class");
                editClF.getError().setText("");
                editClF.getOkCB().setEnabled(true);
                editClF.OriginalClass = currentInputCD.getClassVis().elementAt(CLS.getSelectedIndex()).getLinkedClass();
                editClF.nameC.setText(currentInputCD.getClassString().elementAt(CLS.getSelectedIndex()));
                editClF.attrStrings=currentInputCD.getClassVis().elementAt(CLS.getSelectedIndex()).getAttrStrings();
                editClF.operStrings=currentInputCD.getClassVis().elementAt(CLS.getSelectedIndex()).getOpStrings();
                for (AttrVis av : currentInputCD.getClassVis().elementAt(CLS.getSelectedIndex()).getAttr()){
                    editClF.attrVect.add(new AttributeInfo((Class)av.getLinkedAttr().containedIn(), av.getLinkedAttr().getType(), av.getName()));
                }
                for (OpVis ov : currentInputCD.getClassVis().elementAt(CLS.getSelectedIndex()).getOp()){
                    Set<ParameterInfo> parSet=new HashSet<ParameterInfo>();
                    for (ParamVis pv : ov.getParam()){
                        parSet.add(new ParameterInfo(pv.getLinkedParam().getType(), pv.getName()));
                    }
                    editClF.operVect.add(new OperationInfo(ov.getName(), parSet,(Class)ov.getLinkedOp().containedIn(), ov.getLinkedOp().getReturnType()));
                }
                
                editClF.attrL.setListData(editClF.attrStrings);
                editClF.operL.setListData(editClF.operStrings);

                editClF.setVisible(true);
            }
            else if(e.getSource().equals(delCl)){
                deleteCDElement(currentInputCD.getClassVis().elementAt(CLS.getSelectedIndex()).getLinkedClass());
                CLS.setListData(currentInputCD.getClassString());
            }
            else if(e.getSource().equals(addAs)){
                selectedButton = addAs;
                upperFrame = addAsF = new AddAssocFrame();
                addAsF.setVisible(true);
            }
            else if(e.getSource().equals(editAs)){
                selectedButton = editAs;
                upperFrame = editAsF = new AddAssocFrame();
                editAsF.setVisible(true);

                editAsF.OriginalAssoc = currentInputCD.getAssocVis().elementAt(ASC.getSelectedIndex()).getLinkedAssoc();
                editAsF.getNameAs().setText(editAsF.OriginalAssoc.getName());
                editAsF.getClassFrom().setSelectedItem(editAsF.OriginalAssoc.getSource().getName());
                editAsF.getClassTo().setSelectedItem(editAsF.OriginalAssoc.getDest().getName());
                editAsF.getEnd1().setText(editAsF.OriginalAssoc.getSourceName());
                editAsF.getEnd2().setText(editAsF.OriginalAssoc.getDestName());
            }
            else if(e.getSource().equals(delAs)){
                deleteCDElement(currentInputCD.getAssocVis().elementAt(ASC.getSelectedIndex()).getLinkedAssoc());
                ASC.setListData(currentInputCD.getAssocString());
            }
//-------------------- Tasti Datatype frame ------------------------------------
            if(addDTF != null){
                if(e.getSource().equals(addDTF.getAddLB())){
                    addDTF.addLiteral();
                    s = addDTF.getNameE().getText();

                    on = !s.equals("") && UpdateProject.isNew(s, currentInputCD.getEnumString());

                    addDTF.getOkEB().setEnabled(on);
                }
                else if(e.getSource().equals(addDTF.getDelLB())){
                    addDTF.deleteLiteral();
                }
                else if(e.getSource().equals(addDTF.getOkEB())){
                    addCDElement(new EnumerationInfo(addDTF.litS,addDTF.getNameE().getText()));
                    addEnum.setSelected(false);
                    upperFrame = null;
                    addDTF.dispose();
                }
            }
            if(editDTF != null){
                if(e.getSource().equals(editDTF.getAddLB())){
                    editDTF.addLiteral();
                    s = editDTF.getNameE().getText();
                    
                    on = !s.equals("") && UpdateProject.isNew(s, currentInputCD.getEnumString());
                    
                    editDTF.getOkEB().setEnabled(on);
                }
                else if(e.getSource().equals(editDTF.getDelLB())){
                    editDTF.deleteLiteral();
                }
                else if(e.getSource().equals(editDTF.getOkEB())){
                    updCDElement(editDTF.originalDataT,new EnumerationInfo(editDTF.litS,editDTF.getNameE().getText()));
                    editEnum.setSelected(false);
                    upperFrame = null;
                    editDTF.dispose();
                }
            }
 //-------------------- Tasti Class frame ---------------------------------------
            if(addClF != null){
                if(e.getSource().equals(addClF.getAddAB())){
                    addClF.setEnabled(false);
                    addCAF = new AddCAttrFrame();
                    addCAF.setVisible(true);
                }
                else if(e.getSource().equals(addClF.getDelAB())){
                    addClF.removeAttr();
                }
                else if(e.getSource().equals(addClF.getAddOB())){
                    addClF.setEnabled(false);
                    addCOF = new AddCOperFrame();
                    addCOF.setVisible(true);
                }
                else if(e.getSource().equals(addClF.getDelOB())){
                    addClF.removeOper();
                }
                else if(e.getSource().equals(addClF.getOkCB())){
                    ClassInfo thisCl = new ClassInfo(addClF.getNameC().getText());
                    
                    addCDElement(thisCl);
                    for (AttributeInfo attI : addClF.attrVect){
                        attI.setOfClass(new Class(thisCl));
                        addCDElement(attI);
                    }
                    for (OperationInfo opI : addClF.operVect){
                        opI.setOfClass(new Class(thisCl));
                        addCDElement(opI);
                    }
                    addCl.setSelected(false);
                    upperFrame = null;
                    addClF.dispose();
                }
            }
            if(editClF != null){
                if(e.getSource().equals(editClF.getAddAB())){
                    editClF.setEnabled(false);
                    editCAF = new AddCAttrFrame();
                    editCAF.setVisible(true);
                    editCAF.okAB.setEnabled(true);
                }
                else if(e.getSource().equals(editClF.getDelAB())){
                    editClF.removeAttr();
                }
                else if(e.getSource().equals(editClF.getAddOB())){
                    editCOF = new AddCOperFrame();
                    editCOF.setVisible(true);
                    editCOF.okCOB.setEnabled(true);
                }
                else if(e.getSource().equals(editClF.getDelOB())){
                    editClF.removeOper();
                }
                else if(e.getSource().equals(editClF.getOkCB())){
                    updCDElement(editClF.OriginalClass , new ClassInfo(editClF.getNameC().getText()));
                    for (AttributeInfo attI : editClF.attrVect){
                        attI.setOfClass(editClF.OriginalClass);
                        addCDElement(attI);
                    }
                    for (OperationInfo opI : editClF.operVect){
                        opI.setOfClass(editClF.OriginalClass);
                        addCDElement(opI);
                    }

                    editCl.setSelected(false);
                    upperFrame = null;
                    editClF.dispose();
                }
            }
//-------------------- Tasti Attribute frame -----------------------------------
            if(addCAF != null){
                if(e.getSource().equals(addCAF.getOkAB())){
                    addClF.addAttr(addCAF.getNameA().getText(),addCAF.getTypeA().getSelectedItem().toString());
                    addClF.setEnabled(true);
                    addCAF.dispose();
                }
            }
            if(editCAF != null){
                if(e.getSource().equals(editCAF.getOkAB())){
                    editClF.addAttr(editCAF.getNameA().getText(),editCAF.getTypeA().getSelectedItem().toString());
                    editClF.setEnabled(true);
                    editCAF.dispose();
                }
            }
//-------------------- Tasti Operation frame -----------------------------------
            if(addCOF != null){
                if(e.getSource().equals(addCOF.getAddCOPB())){
                    addCOF.addParameter(addCOF.getNameP().getText() , addCOF.getTypeP().getSelectedItem().toString());
                }
                else if(e.getSource().equals(addCOF.getDelCOPB())){
                    addCOF.deleteParameter();
                }
                else if(e.getSource().equals(addCOF.getOkCOB())){
                    addClF.addOper(addCOF.nameCO.getText(),addCOF.parSet,addCOF.typeORT.getSelectedItem().toString());
                    addClF.setEnabled(true);
                    addCOF.dispose();
                }
            }

//-------------------- Tasti Association frame ---------------------------------
            if(addAsF != null){ //TODO Parte implementativa Associazioni
                if(e.getSource().equals(addAsF.getOkB())){
                    ClassVis c1, c2;

                    c1 = currentInputCD.getClassVisByName(addAsF.getClassFrom().getSelectedItem().toString());
                    c2 = currentInputCD.getClassVisByName(addAsF.getClassTo().getSelectedItem().toString());

                    addCDElement(new AssociationInfo(addAsF.getNameAs().getText(), c1.getLinkedClass(), c2.getLinkedClass(), addAsF.getEnd1().getText(), addAsF.getEnd2().getText()));
                    ASC.setListData(currentInputCD.getAssocString());
                    addAs.setSelected(false);
                    addAsF.dispose();
                }
            }
            if(editAsF != null){
                if(e.getSource().equals(editAsF.getOkB())){
                    ClassVis c1, c2;

                    c1 = currentInputCD.getClassVisByName(editAsF.getClassFrom().getSelectedItem().toString());
                    c2 = currentInputCD.getClassVisByName(editAsF.getClassTo().getSelectedItem().toString());

                    updCDElement(editAsF.OriginalAssoc, new AssociationInfo(editAsF.getNameAs().getText(), c1.getLinkedClass(), c2.getLinkedClass(), editAsF.getEnd1().getText(), editAsF.getEnd2().getText()));
                    editAs.setSelected(false);
                    editAsF.dispose();
                }
            }
        }
    }

    class MyListSelListener implements ListSelectionListener{
        public void valueChanged(ListSelectionEvent e){
            editEnum.setEnabled(!EDT.isSelectionEmpty());
            delEnum.setEnabled(!EDT.isSelectionEmpty());

            if(!CLS.isSelectionEmpty())
                selectedClass = CLS.getSelectedIndex();

            toCont.setEnabled(!CLS.isSelectionEmpty());
            setCont.setEnabled(!CLS.isSelectionEmpty());

            editCl.setEnabled(!CLS.isSelectionEmpty());
            delCl.setEnabled(!CLS.isSelectionEmpty());

            editAs.setEnabled(!ASC.isSelectionEmpty());
            delAs.setEnabled(!ASC.isSelectionEmpty());

            if(addDTF != null){
                addDTF.getDelLB().setEnabled(!addDTF.getLit().isSelectionEmpty());
            }
            if(editDTF != null){
                editDTF.getDelLB().setEnabled(!editDTF.getLit().isSelectionEmpty());
            }
            if(addClF != null){
                addClF.getDelAB().setEnabled(!addClF.getAttrL().isSelectionEmpty());
                addClF.getDelOB().setEnabled(!addClF.getOperL().isSelectionEmpty());
            }
            if(editClF != null){
                editClF.getDelAB().setEnabled(!editClF.getAttrL().isSelectionEmpty());
                editClF.getDelOB().setEnabled(!editClF.getOperL().isSelectionEmpty());
            }
            if(addCOF != null){
                addCOF.getDelCOPB().setEnabled(!addCOF.getPar().isSelectionEmpty());
            }
        }
    }

    class MyWindowAdpt extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent e) {
            if(upperFrame != null)
                upperFrame.setEnabled(true);

            selectedButton.setSelected(false);
            upperFrame = null;
        }
    }

    class MyKeyAdapter extends KeyAdapter   {
        private boolean fullL = false;
        private boolean newL = false;
        private boolean fullE = false;
        private boolean newE = false;
        private boolean newEC = false;
        
        private boolean fullC = false;
        private boolean newC = false;
        private boolean newCE = false;

        private boolean fullA = false;
        private boolean newA = false;
        private boolean typeA = false;
        
        @Override
        public void keyReleased(KeyEvent e) {
            String enumName, litName, className, attrName, s;

            if(addDTF != null){
                enumName = addDTF.getNameE().getText();
                litName = addDTF.getNameL().getText();
                
                fullL = !litName.equals("");
                newL = UpdateProject.isNew(litName, addDTF.litV);
                fullE = !enumName.equals("");
                newE = UpdateProject.isNew(enumName, currentInputCD.getEnumString());
                newEC = UpdateProject.isNew(enumName, currentInputCD.getClassString());

                if(e.getSource().equals(addDTF.getNameL())){
                    System.out.println(litName);

                    addDTF.getAddLB().setEnabled(fullL&&newL);
                }
                else if(e.getSource().equals(addDTF.getNameE())){
                    System.out.println(enumName);

                    addDTF.getOkEB().setEnabled(fullE&&newE&&newEC);
                }
                
                if(!fullE)          addDTF.getError().setText(" Enumeration name empty");
                else if(!newE)      addDTF.getError().setText(" Enumeration already exists");
                else if(!newEC)     addDTF.getError().setText(" A Class already has this name");
                else if(!newL)      addDTF.getError().setText(" Literal already exists");
                else                addDTF.getError().setText("");
            }
            if(editDTF != null){
                enumName = editDTF.getNameE().getText();
                litName = editDTF.getNameL().getText();
                
                fullL = !litName.equals("");
                newL = UpdateProject.isNew(litName, editDTF.litV);
                fullE = !enumName.equals("");
                newE = UpdateProject.isNew(enumName, currentInputCD.getEnumString()) || (!UpdateProject.isNew(enumName, currentInputCD.getEnumString()) && enumName.equals(editDTF.originalDataT.getName()));
                newEC = UpdateProject.isNew(enumName, currentInputCD.getClassString());

                if(e.getSource().equals(editDTF.getNameL())){
                    System.out.println(litName);
                    
                    editDTF.getAddLB().setEnabled(fullL&&newL);
                }
                else if(e.getSource().equals(editDTF.getNameE())){
                    System.out.println(enumName);
                    
                    editDTF.getOkEB().setEnabled(fullE&&newE&&newEC);
                }

                if(!fullE)          editDTF.getError().setText(" Enumeration name empty");
                else if(!newE)      editDTF.getError().setText(" Enumeration already exists");
                else if(!newEC)     editDTF.getError().setText(" A Class already has this name");
                else if(!newL)      editDTF.getError().setText(" Literal already exists");
                else                editDTF.getError().setText("");
            }
            if(addClF != null){
                className = addClF.getNameC().getText();

                fullC = !className.equals("");
                newC = UpdateProject.isNew(className, currentInputCD.getClassString());
                newCE = UpdateProject.isNew(className, currentInputCD.getEnumString());
                
                if(e.getSource().equals(addClF.getNameC())){
                    System.out.println(className);
                    
                    addClF.getOkCB().setEnabled(fullC&&newC&&newCE);
                }

                if(!fullC)          addClF.getError().setText(" Class name empty");
                else if(!newC)      addClF.getError().setText(" Class already exists");
                else if(!newCE)     addClF.getError().setText(" An Enumeration already has this name");
                else                addClF.getError().setText("");
            }
            if(editClF != null){
                className = editClF.getNameC().getText();

                fullC = !className.equals("");
                newC = UpdateProject.isNew(className, currentInputCD.getClassString()) || (!UpdateProject.isNew(className, currentInputCD.getClassString()) && className.equals(editClF.OriginalClass.getName()));
                newCE = UpdateProject.isNew(className, currentInputCD.getEnumString());

                if(e.getSource().equals(editClF.getNameC())){
                    System.out.println(className);

                    editClF.getOkCB().setEnabled(fullC&&newC&&newCE);
                }

                if(!fullC)          editClF.getError().setText(" Class name empty");
                else if(!newC)      editClF.getError().setText(" Class already exists");
                else if(!newCE)     editClF.getError().setText(" An Enumeration already has this name");
                else                editClF.getError().setText("");
            }

            if(addCAF != null){
                attrName = addCAF.getNameA().getText();

                fullA = !attrName.equals("");
                newA = UpdateProject.isNew(attrName, addClF.getAttrNames());
                typeA = addCAF.getTypeA().getSelectedIndex() != 0;
                
                if(e.getSource().equals(addCAF.getNameA())){
                    System.out.println(attrName);
                    
                    addCAF.getOkAB().setEnabled(fullA&&newA&&typeA);
                }
                
                if(!fullA)          addCAF.getError().setText(" Attribute name empty");
                else if(!newA)      addCAF.getError().setText(" Attribute already exists");
                else if(!typeA)     addCAF.getError().setText(" Attribute without a type");
                else                addCAF.getError().setText("");
            }
            if(addCOF != null){
                if(e.getSource().equals(addCOF.getNameCO())){
                    s = addCOF.getNameCO().getText();
                    System.out.println(s);

                    addCOF.getOkCOB().setEnabled(!s.equals(""));
                }
                if(e.getSource().equals(addCOF.getNameP())){
                    s = addCOF.getNameP().getText();
                    System.out.println(s);

                    if(s.equals("") || addCOF.getTypeP().getSelectedIndex() == 0){
                        addCOF.getAddCOPB().setEnabled(false);
                    }
                    else{
                        addCOF.getAddCOPB().setEnabled(true);
                    }
                }
            }
        }
    
        public void clearE(){
            fullL = false;
            newL = false;
            fullE = false;
            newE = false;
        }
        public void clearC(){
            fullC = false;
            newC = false;
        }
    }

    class MyItemListener implements ItemListener{
        public void itemStateChanged(ItemEvent e) {
            if(addCOF != null){
                if(e.getSource().equals(addCOF.getTypeP())){
                    if(addCOF.getTypeP().getSelectedIndex() == 0 || addCOF.getNameP().getText().equals(""))
                        addCOF.getAddCOPB().setEnabled(false);
                    else
                        addCOF.getAddCOPB().setEnabled(true);
                }
            }
            if(addCAF != null){
                if(e.getSource().equals(addCAF.getTypeA())){
                    if(addCAF.getTypeA().getSelectedIndex() == 0 || addCAF.getNameA().getText().equals(""))
                        addCAF.getOkAB().setEnabled(false);
                    else
                        addCAF.getOkAB().setEnabled(true);
                }
            }
        }
    }
    
    class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON3){
               popup.show(CLS, e.getX(), e.getY());
            }
        }
    }
}