package boundaries;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;

import Visualization.*;
import datatypes.*;
import datatypes.Error;
import executors.Main;
import java.awt.Dimension;
import java.awt.Toolkit;
import store.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class UpdateEventCondReact extends JPanel implements BaseBoundary {
    private EventCondReactVis currentEVCR;
    private Set<Type> availableTypes;
    
    private JList EVN;
    private JList CND;
    private JList RCT;

    private JToggleButton selectedButton;

    private JToggleButton addEv,editEv;
    private JButton delEv;
    private JButton genBEV;
    private JToggleButton addCR,editCR;
    private JButton delCR;
    private JTextField paramField;

    private MyActionListener actionL;
    private MyListSelListener listL;
    private MyWindowAdapter windowL;
    private MyKeyAdapter keyL;
    private MyItemListener itemL;

    private AddEventFrame addEvF, editEvF;
    private AddCondReactFrame addCRF, editCRF;

    private int selectedEvent;
    private JPanel condP, paramP;

    public UpdateEventCondReact(EventCondReactVis cdDVis, Set<Type> types) {
        currentEVCR = cdDVis;
        availableTypes = types;

        JPanel cCenter, eSouth, cSouth, backCenter;
        JPanel eventP;
        
        setName("Event and Cond/React");
        setBorder(BorderFactory.createTitledBorder(null,"Update Events and Conditions/Reactions",TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,new Font("Dialog",Font.BOLD, 12),new Color(51, 51, 51)));
        setLayout(new BorderLayout(5,5));

        windowL = new MyWindowAdapter();
        actionL = new MyActionListener();
        listL = new MyListSelListener();
        keyL = new MyKeyAdapter();
        itemL = new MyItemListener();
        
        eventP =  UpdateProject.getTitledPanel("Events");
        condP =   UpdateProject.getTitledPanel("Conditions/Reactions");
        cCenter = new JPanel();
        cCenter.setLayout(new GridLayout(1, 2, 5, 5));

        EVN = new JList();
        EVN.setFont(new Font("Arial", Font.BOLD, 12));
        EVN.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        EVN.addListSelectionListener(listL);
        EVN.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        CND = new JList();
        CND.setFont(new Font("Arial", Font.BOLD, 12));
        CND.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        CND.addListSelectionListener(listL);
        CND.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        RCT = new JList();
        RCT.setFont(new Font("Arial", Font.BOLD, 12));
        RCT.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        RCT.addListSelectionListener(listL);
        RCT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cCenter.add(new JScrollPane(CND));
        cCenter.add(new JScrollPane(RCT));

        addEv =     new JToggleButton("Add Event");
        editEv =  new JToggleButton("Edit Event");
        editEv.setEnabled(false);
        delEv =     new JButton("Delete Event");
        delEv.setEnabled(false);
        addCR =     new JToggleButton("Add");
        addCR.setEnabled(false);
        editCR =  new JToggleButton("Edit");
        editCR.setEnabled(false);
        delCR =     new JButton("Delete");
        delCR.setEnabled(false);
        genBEV = new JButton("Generate Basic Events");

        paramField = new JTextField();


        eSouth = new JPanel(new GridLayout(3,1));
        cSouth = new JPanel(new GridLayout(1,3));
        backCenter = new JPanel(new BorderLayout());
        paramP = UpdateProject.getTitledPanel("Parameters");

        paramP.add(paramField);

        eSouth.add(addEv);
        eSouth.add(editEv);
        eSouth.add(delEv);
        cSouth.add(addCR);
        cSouth.add(editCR);
        cSouth.add(delCR);

        backCenter.add(paramP, BorderLayout.NORTH);
        backCenter.add(condP, BorderLayout.CENTER);

        eventP.add(new JScrollPane(EVN),BorderLayout.CENTER);

        eventP.add(  eSouth,BorderLayout.SOUTH);

        eventP.setPreferredSize(new Dimension(150, 405));

        condP.add(cCenter,BorderLayout.CENTER);
        condP.add(cSouth,BorderLayout.SOUTH);

        add(eventP, BorderLayout.WEST);
        add(backCenter, BorderLayout.CENTER);
        add(genBEV, BorderLayout.NORTH);

        addEv.addActionListener(actionL);
        editEv.addActionListener(actionL);
        delEv.addActionListener(actionL);

        addCR.addActionListener(actionL);
        editCR.addActionListener(actionL);
        delCR.addActionListener(actionL);

        genBEV.addActionListener(actionL);
    }

    public void setPanelTitle(String s, JPanel p){
        p.setBorder(BorderFactory.createTitledBorder(null,s,TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,new Font("Dialog",Font.BOLD, 12),new Color(51, 51, 51)));
    }

    public JToggleButton getAddEv(){      return addEv;     }
    public JToggleButton getEditEv(){     return editEv;    }
    public JButton getDelEv(){            return delEv;     }
    public JToggleButton getAddCR(){      return addCR;     }
    public JToggleButton getEditCR(){     return editCR;    }
    public JButton getDelCR(){            return delCR;     }
    public JPanel getCondP(){   return condP;   }
    public JPanel getParamP(){   return paramP;   }

    public void setEVCR(EventCondReactVis cdDVis, Set<Type> types) {
        availableTypes=types;
        currentEVCR=cdDVis;

        EVN.setListData(cdDVis.evString);
    }

    public void correct(Set<Error> errs) {
        throw new UnsupportedOperationException();
    }

    public void addEvent(EventInfo el) {
        Main.doAddEvent(el);
    }

    public void deleteEvent(Event el) {
        Main.doDeleteEvent(el);
    }

    public void updEvent(Event el, EventInfo newElIN) {
        Main.doUpdEvent(el, newElIN);
    }

    public void addCR(Event el, CondReactInfo condReact) {
        Main.doAddCR(el, condReact);
    }

    public void deleteCR(Event el, CondReact cr) {
        Main.doDeleteCR(el, cr);
    }

    public void updateCR(CondReact condReact, CondReactInfo condReactNew) {
        Main.doUpdateCR(condReact, condReactNew);
    }

    public void generateBasicEV() {
        Main.doGenerateBasicEV();
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

//-------------- Frame aggiuntivi ----------------------------------------------
    class AddEventFrame extends JFrame{
        private JButton addEP, delEP, okE;
        private JList par;
        private JTextField nameE, nameEP;
        private JComboBox typeEP;

        private Vector<String> paramName;
        private Vector<String> paramType;
        private Vector<String> paramFull;
        
        private EventInfo originalInfo;
        private Event originalEvent;

        public AddEventFrame(){
            JPanel north,center,south;
            JPanel back;
            JPanel pNorth;
            
            setTitle("Add Event");
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ico.png")));
            addWindowListener(windowL);

            back = new JPanel(new BorderLayout(5,5));
            north = UpdateProject.getTitledPanel("Name");
            north.setLayout(new GridLayout());
            center = UpdateProject.getTitledPanel("Parameters");
            south = new JPanel(new BorderLayout());
            pNorth = new JPanel(new BorderLayout());

            addEP = new JButton("Add");
            addEP.setEnabled(false);
            delEP = new JButton("Delete Parameter");
            delEP.setEnabled(false);
            okE = new JButton("Ok");
            okE.setEnabled(false);

            addEP.addActionListener(actionL);
            delEP.addActionListener(actionL);
            okE.addActionListener(actionL);

            par = new JList();
            par.setLayoutOrientation(JList.VERTICAL);
            par.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            par.addListSelectionListener(listL);

            nameE = new JTextField();
            nameEP = new JTextField();
            nameE.addKeyListener(keyL);
            nameEP.addKeyListener(keyL);

            north.add(nameE);
            par.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
            center.add(new JScrollPane(par),BorderLayout.CENTER);

            typeEP = UpdateProject.getTypeList("Choose one");
            typeEP.addItemListener(itemL);

            pNorth.add(typeEP,BorderLayout.WEST);
            pNorth.add(nameEP,BorderLayout.CENTER);
            pNorth.add(addEP,BorderLayout.EAST);

            center.add(delEP,BorderLayout.SOUTH);
            center.add(pNorth,BorderLayout.NORTH);

            south.add(okE, BorderLayout.EAST);

            back.add(north,BorderLayout.NORTH);
            back.add(center,BorderLayout.CENTER);
            back.add(south, BorderLayout.SOUTH);

            paramName = new Vector<String>();
            paramType = new Vector<String>();
            paramFull = new Vector<String>();

            setPreferredSize(new Dimension(300,400));
            add(back);
            pack();
            setLocationRelativeTo(null);
        }

        public JButton          getAddEP(){         return addEP;       }
        public JButton          getDelEP(){         return delEP;       }
        public JButton          getOkE(){           return okE;         }
        public JList            getPar(){           return par;         }
        public JTextField       getNameE(){         return nameE;       }
        public JTextField       getNameEP(){        return nameEP;      }
        public JComboBox        getTypeEP(){        return typeEP;      }
        public Vector<String>   getParamName(){     return paramName;   }
        public Vector<String>   getParamType(){     return paramType;   }
        public EventInfo        getOriginalInfo(){  return originalInfo;}
        public EventInfo        getEventInfo(){
            String n = nameE.getText();
            Set<ParameterInfo> s=new HashSet<ParameterInfo>();
            for(int i=0; i<paramType.size(); i++)
                s.add(new ParameterInfo(UpdateProject.getType(paramType.get(i)), paramName.get(i)));
            
            return new EventInfo(n, s);
        }
        
        public void addParameter(String n, String t){
            paramName.add(n);
            paramType.add(t);
            paramFull.add(n + ": " + t);
            par.setListData(paramFull);
            typeEP.setSelectedIndex(0);
            nameEP.setText("");
            addEP.setEnabled(false);
        }

        public void delParameter(){

            paramName.remove(par.getSelectedIndex());
            paramType.remove(par.getSelectedIndex());
            par.setListData(paramName);
            typeEP.setSelectedIndex(0);
            nameEP.setText("");
        }
    }

    class AddCondReactFrame extends JFrame{
        private JTextArea condTA, reactTA;
        private JButton okCR;
        private CondReact OriginalCR;
        private Event Event;

        public AddCondReactFrame(){
            JPanel center,south;
            JPanel back;
            JPanel lSouth;
            JPanel split;
            
            setTitle("Add Condition/Reaction");
            addWindowListener(windowL);

            back = new JPanel(new BorderLayout(5,5));
            center = new JPanel(new BorderLayout());
            south = new JPanel(new BorderLayout());
            lSouth = new JPanel(new GridLayout(2,1));
            split = new JPanel(new GridLayout(1,2));

            okCR = new JButton("Ok");
            okCR.setEnabled(false);
            okCR.addActionListener(actionL);

            condTA = new JTextArea();
            condTA.addKeyListener(keyL);
            reactTA = new JTextArea();
            reactTA.addKeyListener(keyL);

            condTA.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
            reactTA.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

            split.add(new JScrollPane(condTA));
            split.add(new JScrollPane(reactTA));

            center.add(split,BorderLayout.CENTER);

            center.add(lSouth,BorderLayout.SOUTH);
            center.setBorder(BorderFactory.createTitledBorder(null,"Condition/Reaction",TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,new Font("Dialog",Font.BOLD, 12),new Color(51, 51, 51)));

            south.add(okCR, BorderLayout.EAST);

            back.add(center,BorderLayout.CENTER);
            back.add(south, BorderLayout.SOUTH);

            setPreferredSize(new Dimension(600,200));
            add(back);
            pack();
            setLocationRelativeTo(null);
        }

        public JTextArea getCondTA(){   return condTA;  }
        public JTextArea getReactTA(){  return reactTA; }
        public JButton getOkCR(){       return okCR;    }
        public Event getEvent(){
            return currentEVCR.evVis.get(selectedEvent).getLinkedEvent();
        }
        public CondReactInfo getCRInfo(){
            return new CondReactInfo(condTA.getText(),reactTA.getText());
        }

    }
//-------------- Listeners -----------------------------------------------------
    class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource().equals(genBEV)){
                generateBasicEV();
            }
            else if(e.getSource().equals(addEv)){
                selectedButton = addEv;
                addEvF = new AddEventFrame();
                addEvF.setVisible(true);
            }
            else if(e.getSource().equals(editEv)){
                selectedButton = editEv;
                editEvF = new AddEventFrame();
                editEvF.getOkE().setEnabled(true);
                editEvF.paramName=new Vector<String>();
                editEvF.paramType=new Vector<String>();
                editEvF.paramFull=new Vector<String>();
                editEvF.getNameE().setText(currentEVCR.evVis.get(selectedEvent).getName());

                for(ParamVis pv: currentEVCR.evVis.get(selectedEvent).getParam()){
                    editEvF.paramName.add(pv.getName());
                    editEvF.paramType.add(pv.getType());
                    editEvF.paramFull.add(pv.getFullString());
                }

                editEvF.par.setListData(editEvF.paramFull);
                editEvF.originalEvent=currentEVCR.evVis.get(selectedEvent).getLinkedEvent();
                editEvF.setVisible(true);
            }
            else if(e.getSource().equals(delEv)){
                deleteEvent(currentEVCR.evVis.get(selectedEvent).getLinkedEvent());
                paramField.setText("");
                CND.setListData(new Vector<String>());
                RCT.setListData(new Vector<String>());
            }
            else if(e.getSource().equals(addCR)){
                selectedButton = addCR;
                addCRF = new AddCondReactFrame();
                addCRF.setVisible(true);
            }
            else if(e.getSource().equals(editCR)){
                selectedButton = editCR;
                int i = CND.getSelectedIndex();
                editCRF = new AddCondReactFrame();
                editCRF.getOkCR().setEnabled(true);
                if(EVN.getSelectedValue().equals("Created"))
                    editCRF.getCondTA().setEditable(false);
                String cond=currentEVCR.getEvents().elementAt(selectedEvent).getCR().elementAt(i).getCondVis();
                String react=currentEVCR.getEvents().elementAt(selectedEvent).getCR().elementAt(i).getReactVis();
                editCRF.Event=currentEVCR.evVis.get(selectedEvent).getLinkedEvent();
                editCRF.OriginalCR=currentEVCR.evVis.get(selectedEvent).getCR().get(i).getLinkedCR();
                editCRF.condTA.setText(cond);
                editCRF.reactTA.setText(react);
                editCRF.setVisible(true);
            }
            else if(e.getSource().equals(delCR)){
                int i=CND.getSelectedIndex();
                String cond=currentEVCR.getEvents().elementAt(selectedEvent).getCR().elementAt(i).getCondVis();
                String react=currentEVCR.getEvents().elementAt(selectedEvent).getCR().elementAt(i).getReactVis();
                deleteCR(currentEVCR.evVis.get(selectedEvent).getLinkedEvent(), currentEVCR.getEvents().get(selectedEvent).getCR().elementAt(i).getLinkedCR());
                EVN.setSelectedIndex(selectedEvent);
            }
            if (addEvF != null){
                if(e.getSource().equals(addEvF.getAddEP())){
                    addEvF.addParameter(addEvF.getNameEP().getText(),addEvF.getTypeEP().getSelectedItem().toString());
                }
                else if(e.getSource().equals(addEvF.getDelEP())){
                    addEvF.delParameter();
                }
                else if(e.getSource().equals(addEvF.getOkE())){
                    addEvent(addEvF.getEventInfo());
                    addEv.setSelected(false);
                    addEvF.dispose();
                    paramField.setText("");
                    CND.setListData(new Vector<String>());
                    RCT.setListData(new Vector<String>());
                }
            }
            if (editEvF != null){
                if(e.getSource().equals(editEvF.getAddEP())) {
                    editEvF.addParameter(editEvF.getNameEP().getText(),editEvF.getTypeEP().getSelectedItem().toString());
                }
                else if(e.getSource().equals(editEvF.getDelEP())){
                    editEvF.delParameter();
                }
                else if(e.getSource().equals(editEvF.getOkE())){
                    updEvent(editEvF.originalEvent, editEvF.getEventInfo());
                    editEv.setSelected(false);
                    editEvF.dispose();
                    EVN.setSelectedIndex(selectedEvent);
                }
            }
            if (addCRF != null){
                if(e.getSource().equals(addCRF.getOkCR())){
                    addCR(addCRF.getEvent(),addCRF.getCRInfo());
                    addCR.setSelected(false);
                    addCRF.dispose();
                    EVN.setSelectedIndex(selectedEvent);
                }
            }
            if (editCRF != null){
                if(e.getSource().equals(editCRF.getOkCR())){
                    updateCR(editCRF.OriginalCR, editCRF.getCRInfo());
                    EVN.setSelectedIndex(-1);
                    editCR.setSelected(false);
                    editCRF.dispose();
                    EVN.setSelectedIndex(selectedEvent);
                }
            }
        }
    }

    class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e) {
            String s, s1, s2;
            boolean on;

            if(addEvF != null){
                if(e.getSource().equals(addEvF.getNameE())){
                    s = addEvF.getNameE().getText();

                    on = !s.equals("") && UpdateProject.isNew(s, currentEVCR.getEventNames());

                    addEvF.getOkE().setEnabled(on);
                }
                if(e.getSource().equals(addEvF.getNameEP())){
                    s = addEvF.getNameEP().getText();

                    on = !s.equals("") && !(addEvF.getTypeEP().getSelectedIndex() == 0);
                    
                    addEvF.getAddEP().setEnabled(on);
                }
            }
            if(editEvF != null){
                if(e.getSource().equals(editEvF.getNameE())){
                    s = editEvF.getNameE().getText();

                    on = !s.equals("") &&
                         UpdateProject.isNew(s, currentEVCR.getEventNames()) ||
                         (!UpdateProject.isNew(s, currentEVCR.getEventNames()) && s.equals(editEvF.getOriginalInfo().getName()));

                    editEvF.getOkE().setEnabled(on);
                }

                if(e.getSource().equals(editEvF.getNameEP())){
                    s = editEvF.getNameEP().getText();

                    on = !s.equals("") && !(editEvF.getTypeEP().getSelectedIndex() == 0);

                    editEvF.getAddEP().setEnabled(on);
                }
            }
            if(addCRF != null){
                if(e.getSource().equals(addCRF.getCondTA()) || e.getSource().equals(addCRF.getReactTA())){
                    Vector<String> v = currentEVCR.getEvents().elementAt(selectedEvent).getCondStrings();
                    s1 = addCRF.getCondTA().getText();
                    s2 = addCRF.getReactTA().getText();


                    on = !(s1.equals("") && s2.equals("")) || UpdateProject.isNew(s1, v) || (s1.equals("") && UpdateProject.isNew("TRUE", v));

                    addCRF.getOkCR().setEnabled(on);
                }
            }
            if(editCRF != null){
                if(e.getSource().equals(editCRF.getCondTA())){
                    Vector<String> v = currentEVCR.getEvents().elementAt(selectedEvent).getCondStrings();
                    s = editCRF.getCondTA().getText();

                    on = !s.equals("") &&
                         UpdateProject.isNew(s, v) ||
                         (!UpdateProject.isNew(s, v) && s.equals(editEvF.getOriginalInfo().getName()));

                    editCRF.getOkCR().setEnabled(on);
                }
            }
        }
    }

    class MyItemListener implements ItemListener{
        public void itemStateChanged(ItemEvent e) {
            if(addEvF != null){
                if(e.getSource().equals(addEvF.getTypeEP())){
                    String s = addEvF.getNameEP().getText();

                    boolean on = !(addEvF.getTypeEP().getSelectedIndex() == 0) && !s.equals("");

                    addEvF.getAddEP().setEnabled(on);
                }
            }
            if(editEvF != null){
                if(e.getSource().equals(editEvF.getTypeEP())){
                    String s = editEvF.getNameEP().getText();

                    boolean on = !(editEvF.getTypeEP().getSelectedIndex() == 0) && !s.equals("");

                    editEvF.getAddEP().setEnabled(on);
                }
            }
        }
    }
    
    class MyListSelListener implements ListSelectionListener{
        public void valueChanged(ListSelectionEvent e){
            if(e.getSource().equals(EVN)){
                if(!EVN.isSelectionEmpty()){
                    selectedEvent = EVN.getSelectedIndex();
                    
                    if(!currentEVCR.getEventFullStrings().elementAt(selectedEvent).equals(""))
                        paramField.setText(currentEVCR.getEventFullStrings().elementAt(selectedEvent));
                    else
                        paramField.setText("No parameter for this event");

                    setPanelTitle("Parameters of event " + EVN.getSelectedValue().toString(), getParamP());
                    setPanelTitle("Conditions/Reactions for event " + EVN.getSelectedValue().toString(), getCondP());
 
                    CND.setListData(currentEVCR.getEvents().elementAt(EVN.getSelectedIndex()).getCondStrings());
                    RCT.setListData(currentEVCR.getEvents().elementAt(EVN.getSelectedIndex()).getReactStrings());
                    
                    editEv.setEnabled(true);
                    delEv.setEnabled(true);
                    addCR.setEnabled(true);
                    
                    if(EVN.getSelectedValue().equals("Created")){
                        editEv.setEnabled(false);
                        delEv.setEnabled(false);
                        addCR.setEnabled(false);
                    }
                }
                else{
                    paramField.setText("");
                    setPanelTitle("Parameters", getParamP());
                    setPanelTitle("Conditions/Reactions", getCondP());
                    CND.removeAll();
                    RCT.removeAll();

                    editEv.setEnabled(false);
                    delEv.setEnabled(false);
                    addCR.setEnabled(false);
                }
                

                
            }
            else if(e.getSource().equals(CND)){
                editCR.setEnabled(!CND.isSelectionEmpty());
                delCR.setEnabled(!CND.isSelectionEmpty());
                RCT.setSelectedIndex(CND.getSelectedIndex());
            }
            else if(e.getSource().equals(RCT)){
                CND.setSelectedIndex(RCT.getSelectedIndex());
            }
            if(addEvF != null){
                if(e.getSource().equals(addEvF.getPar())){
                    addEvF.getDelEP().setEnabled(!addEvF.getPar().isSelectionEmpty());
                }
            }
            if(editEvF != null){
                if(e.getSource().equals(editEvF.getPar()))
                    editEvF.getDelEP().setEnabled(!editEvF.getPar().isSelectionEmpty());
            }
        }
    }

    class MyWindowAdapter extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent e) {
            selectedButton.setSelected(false);
        }
    }
}
