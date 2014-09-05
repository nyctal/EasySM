package boundaries;

import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;

import Visualization.*;
import datatypes.*;
import executors.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.List;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import store.*;
import store.Event;
import Visualization.StateMachineVis.TransVis;
import Visualization.ClassDiagramVis.EnumVis;
import Visualization.ClassDiagramVis.ClassVis;
import Visualization.ClassDiagramVis.AssocVis;

public class UpdateProject extends JTabbedPane implements BaseBoundary {

    static final int IconSET = 0;
    private ProjectVis currentPRJ;
    public UpdateInputCD updateCDFrame;
    public UpdateEventCondReact updateECRPanel;
    public UpdateStateObserverInv updateSOIPanel;
    public UpdateStateTable updateSTPanel;
    private TransitionVis trVector;
    private TransitionVis possibleTrVector;
    private State[] targetRows;
    private Vector<String> stateNames;
    private Vector<String> targetStateNames;
    private Vector<String> sourceStateNames;
    private List<State> states;
    private StateTableVis currentST;
    private TransitionFrame trFrame;
    private StateMachineFrame stateMFrame;
    private ViewEFrame viewEF;
    private ViewCFrame viewCF;
    private ViewAFrame viewAF;

    public UpdateProject(ProjectVis pr) {
        setProject(pr);
    }

    public UpdateInputCD getUpdateCDPanel() {
        return updateCDFrame;
    }

    public UpdateEventCondReact getUpdateECRPanel() {
        return updateECRPanel;
    }

    public UpdateStateObserverInv getUpdateSOIPanel() {
        return updateSOIPanel;
    }

    public UpdateStateTable getUpdateSTPanel() {
        return updateSTPanel;
    }

    public StateMachineFrame getStateMFrame() {
        return stateMFrame;
    }

    public static JComboBox getTypeList(String first) {
        JComboBox cB = new JComboBox();

        cB.addItem(first);

        for (Type elem : UpdateInputCD.getAvailableTypes()) {
            cB.addItem(elem.getName());
        }

        return cB;
    }

    public static JPanel getTitledPanel(String n) {
        JPanel p = new JPanel(new BorderLayout(5, 5));
        p.setBorder(BorderFactory.createTitledBorder(null, n, TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));

        return p;
    }

    public static boolean isNew(String el, Vector<String> v) {
        for (String s : v) {
            if (s.equals(el)) {
                return false;
            }
        }
        return true;
    }

    public static Type getType(String s) {
        for (Type t : UpdateInputCD.getAvailableTypes()) {
            if (t.getName().equals(s)) {
                return t;
            }
        }
        return new Type();
    }

    public static Vector<String> getColorStrings(Vector<String> vect, String color) {
        Vector<String> res = new Vector<String>();

        for (String i : vect) {
            res.add("<html>" + getColorString(i, color) + "</html>");
        }

        return res;
    }

    public static String getColorString(String str, String color) {
        return "<font color='" + color + "'>" + str + "</font>";
    }

    public Vector<String> getTransByState(String s, Vector<TransVis> v) {
        Vector<String> res = new Vector<String>();

        for (TransVis i : v) {
            if (i.getSource().equals(s)) {
                res.add(i.getFullString());
            }
        }

        return res;
    }

    public void addTabs() {
        setTabPlacement(JTabbedPane.LEFT);
        addTab("", new ImageIcon(getClass().getResource("/CD2.png")), updateCDFrame);
        addTab("", new ImageIcon(getClass().getResource("/ECR2.png")), updateECRPanel);
        addTab("", new ImageIcon(getClass().getResource("/SOI2.png")), updateSOIPanel);
        addTab("", new ImageIcon(getClass().getResource("/ST2.png")), updateSTPanel);
        validate();
    }

    /**
     *
     * @param pr
     * @return
     */
    public void setProject(ProjectVis pr) {
        currentPRJ = pr;
    }

    /**
     *
     * @return
     */
    public void closeProject() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @return
     */
    public void generateSM() {
        trVector = new TransitionVis();
        possibleTrVector = new TransitionVis();
        Main.doGenerateSM();
    }

    /**
     *
     * @return
     */
    public void done() {
        throw new UnsupportedOperationException();
    }

    public void examine(Row row, CondReact cr, Event ev) {
        trVector.add(new TransitionInfo(row, cr, ev));
        if (row.getName().equals("Initial")) {
            possibleTrVector.add(new TransitionInfo(row, cr, ev));
        } else if (!row.isFinal()) {
            possibleTrVector.add(new TransitionInfo(row, cr, ev));
        }
    }

    public void askTransitions(List<State> st, StateTableVis vis) {
        System.out.println("trVectorSize = " + trVector.getSize());
        System.out.println("PossibletrVectorSize = " + possibleTrVector.getSize());
        targetRows = new State[trVector.getSize()];
        System.out.println("targetRows = " + targetRows.length);
        stateNames = new Vector<String>();
        targetStateNames = new Vector<String>();
        sourceStateNames = new Vector<String>();
        for (int i = 0; i < targetRows.length; i++) {
            targetRows[i] = new State("Not used");
        }
        for (State s : st) {
            if (s.isFinal()) {
                System.out.println(s.getName() + " è final");
            }
        }
        for (State s : st) {
            stateNames.add(s.getName());
            System.out.println(s.getName() + " Aggiunto a StateNames");
            if (!s.isInitial()) {
                targetStateNames.add(s.getName());
                System.out.println(s.getName() + " Aggiunto a TargetStateNames");
                if (!s.isFinal()) {
                    sourceStateNames.add(s.getName());
                    System.out.println(s.getName() + " Aggiunto a SourceStateNames");
                }
            }
        }
        states = st;
        currentST = vis;
        trFrame = new TransitionFrame();
        trFrame.setVisible(true);
        for (int i = 0; i < targetRows.length; i++) {
            System.out.println("+++++++++:" + targetRows[i].getName());
        }
    }

    public void answer(boolean sat, TransitionInfo tr, State target) {
        for (int i = 0; i < targetRows.length; i++) {
            System.out.println("/////:" + targetRows[i].getName());
        }
        System.out.println("--Il nome è:");
        System.out.println(target.getName());
        if (sat && !target.getName().equals("Not used")) {
            Main.ok(tr, target);
        }
    }

    public void confirmSMCreation() {
        Main.createSM();
    }

    public void showSM(StateMachineVis smv, ClassDiagramVis cdv) {
        stateMFrame = new StateMachineFrame(smv, cdv);
        stateMFrame.getTransL().setListData(getTransByState(stateMFrame.getSmv().getStateString().get(0), stateMFrame.getSmv().getTrans()));
        stateMFrame.setVisible(true);
    }

    @Override
    public void ko(Report r) {
        EasySM.setErrorMessage(r.getMsg(), 'r');
    }

    @Override
    public void ok(Report r) {
        EasySM.setErrorMessage(r.getMsg(), 'g');
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public void validate(Element el) {
        // TODO Auto-generated method stub
    }

    class TransitionFrame extends JFrame {

        private JPanel back, center, south;
        private JScrollPane scrollSup, scrollMed, scrollInf;
        private JButton previous, next;
        private JTable tabSup, tabMed, tabInf;
        private MyTableModel mod;
        private MyTableModel2 mod2;
        private MyTableModel3 mod3;
        private int row;
        private int rowMaxSize;

        public TransitionFrame() {

            setTitle("Transitions");
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ico.png")));

            row = 0;

            back = new JPanel(new BorderLayout());
            back.setBorder(BorderFactory.createTitledBorder(null, "Update Transitions", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));


            center = new JPanel(new GridLayout(3, 1));
            south = new JPanel(new BorderLayout());

            previous = new JButton("< Previous");
            previous.setPreferredSize(new Dimension(85, 25));
            next = new JButton("Next >");
            next.setPreferredSize(new Dimension(85, 25));
            previous.setEnabled(false);

            previous.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (row == 1) {
                        tabSup.getSelectionModel().addSelectionInterval(row, row);
                        mod2.setDataValues(trVector.getInitialObjectArray());
                        tabInf.setValueAt(targetRows[0].getName(), 0, 4);
                        validate();
                        previous.setEnabled(false);
                        row--;
                    } else {
                        tabSup.getSelectionModel().addSelectionInterval(row, row);
                        mod2.setDataValues(trVector.getObjectArrayForState(sourceStateNames.elementAt(row - 2)));
                        int mult = (row - 2) * mod2.getRowCount();
                        for (int i = 0; i < mod2.getRowCount(); i++) {
                            tabInf.setValueAt(targetRows[mult + i + 1].getName(), i, 4);
                        }
                        row--;
                    }
                    next.setText("Avanti");
                    repaint();
                }
            });
            next.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    if (row == 0) {
                        System.out.println("ROW: " + row);
                        /*Caso iniziale. La riga è quella della trans. iniziale e clicco avanti*/
                        targetRows[0] = findState(mod2.getValueAt(0, 4).toString());
                        tabSup.getSelectionModel().addSelectionInterval(row, row);
                        previous.setEnabled(true);
                        mod2.setDataValues(trVector.getObjectArrayForState(sourceStateNames.elementAt(row)));
                        System.out.println("TargetRowSize " + targetRows.length);
                        for (int i = 0; i < mod2.getRowCount(); i++) {
                            if (targetRows[1 + i] != null) {
                                tabInf.setValueAt(targetRows[1 + i].getName(), i, 4);
                            }
                        }
                        row++;
                        validate();
                        repaint();
                    } else if (row < rowMaxSize) {
                        System.out.println("ROW: " + row);
                        /*Caso intermedio*/
                        int mult = (row - 1) * mod2.getRowCount();
                        for (int i = 0; i < mod2.getRowCount(); i++) {
                            targetRows[1 + mult + i] = findState(mod2.getValueAt(i, 4).toString());
                        }
                        tabSup.getSelectionModel().addSelectionInterval(row, row);
                        //previous.setEnabled(true);
                        mod2.setDataValues(trVector.getObjectArrayForState(sourceStateNames.elementAt(row)));
                        //System.out.println("TargetRowSize " + targetRows.length);
                        mult = row * mod2.getRowCount();
                        for (int i = 0; i < mod2.getRowCount(); i++) {
                            if (targetRows[1 + i + mult] != null) {
                                tabInf.setValueAt(targetRows[1 + i + mult].getName(), i, 4);
                            }
                        }
                        if (row == rowMaxSize - 1) {
                            next.setText("Fine");
                        }
                        row++;
                    } else if (row == rowMaxSize) {
                        System.out.println("ROW: " + row);
                        /*Caso finale. Ho le transiz. dell'ultimo stato e clicco su ok*/
                        int mult = (row - 1) * mod2.getRowCount();
                        for (int i = 0; i < mod2.getRowCount(); i++) {
                            targetRows[1 + mult + i] = findState(mod2.getValueAt(i, 4).toString());
                        }
                        for (int j = 0; j < possibleTrVector.getSize(); j++) {
                            System.out.println("Riga: " + j);

                            answer(true, possibleTrVector.getInfoVect().elementAt(j), targetRows[j]);
                        }
                        confirmSMCreation();
                        dispose();
                    }
                    repaint();
                }
            });

            mod = new MyTableModel();
            mod2 = new MyTableModel2();
            mod3 = new MyTableModel3();
            tabSup = new JTable(mod);

            tabSup.setEnabled(false);
            tabMed = new JTable(mod3);
            tabMed.setEnabled(false);
            tabSup.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tabInf = new JTable(mod2);

            tabSup.getTableHeader().setReorderingAllowed(false);
            tabMed.getTableHeader().setReorderingAllowed(false);
            tabInf.getTableHeader().setReorderingAllowed(false);

            tabSup.getSelectionModel().addSelectionInterval(0, 0);
            tabSup.clearSelection();

            setUpNameColumn(tabInf, tabInf.getColumnModel().getColumn(4));
            for (int i = 0; i < mod2.getRowCount(); i++) {
                tabInf.setValueAt("Not used", i, 4);
            }

            scrollSup = new JScrollPane(tabSup);
            scrollMed = new JScrollPane(tabMed);
            scrollInf = new JScrollPane(tabInf);

            scrollSup.setBorder(BorderFactory.createTitledBorder(null, "States", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
            scrollMed.setBorder(BorderFactory.createTitledBorder(null, "Final States", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
            scrollInf.setBorder(BorderFactory.createTitledBorder(null, "Possible Transitions", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));


            center.add(scrollSup);
            center.add(scrollMed);
            center.add(scrollInf);

            south.add(previous, BorderLayout.WEST);
            south.add(next, BorderLayout.EAST);

            back.add(center, BorderLayout.CENTER);
            back.add(south, BorderLayout.SOUTH);

            rowMaxSize = tabSup.getRowCount();

            add(back);

            setPreferredSize(new Dimension(800, 400));
            pack();
            setLocationRelativeTo(null);
        }
    }

    class StateMachineFrame extends JFrame {

        private JButton viewCD;
        private ClassDiagramPanel cdP;
        private JPanel back, center, stateP, transP, up;
        private JList statesL, transL;
        private StateMachineVis smv;

        public StateMachineFrame(StateMachineVis smVis, ClassDiagramVis cdVis) {
            setTitle("State machine viewer");
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ico.png")));

            smv = smVis;

            back = new JPanel(new BorderLayout());
            center = new JPanel(new GridLayout(1, 1));
            up = UpdateProject.getTitledPanel("State Machine for Class " + cdVis.getContextClass());
            stateP = UpdateProject.getTitledPanel("States");
            stateP.setPreferredSize(new Dimension(200, 200));
            transP = UpdateProject.getTitledPanel("Transition and Destination State");

            statesL = new JList();
            statesL.addListSelectionListener(new MyListSelListener());
            statesL.setSelectedIndex(0);
            statesL.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
            transL = new JList();
            transL.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

            statesL.setListData(smv.getStateString());

            stateP.add(new JScrollPane(statesL));
            transP.add(new JScrollPane(transL));

            up.add(stateP, BorderLayout.WEST);
            up.add(transP, BorderLayout.CENTER);

            center.add(up);

            cdP = new ClassDiagramPanel(cdVis);

            viewCD = new JButton("▼ ▼ ▼ ▼ ▼     View Class Diagram     ▼ ▼ ▼ ▼ ▼");
            viewCD.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    visualizeCD();
                }
            });

            back.add(center, BorderLayout.CENTER);
            back.add(viewCD, BorderLayout.SOUTH);

            add(back);

            setPreferredSize(new Dimension(850, 280));
            setResizable(false);
            pack();
            setLocationRelativeTo(null);
        }

        public ClassDiagramPanel getCdP() {
            return cdP;
        }

        public JList getStatesL() {
            return statesL;
        }

        public JList getTransL() {
            return transL;
        }

        public StateMachineVis getSmv() {
            return smv;
        }

        public void visualizeCD() {
            if (getHeight() == 280) {
                setSize(new Dimension(850, 510));
                viewCD.setText("▲ ▲ ▲ ▲ ▲     Hide Class Diagram     ▲ ▲ ▲ ▲ ▲");
                center.remove(up);
                center.setLayout(new GridLayout(2, 1));
                center.add(up);
                center.add(cdP);
                validate();
            } else {
                setSize(new Dimension(850, 280));
                viewCD.setText("▼ ▼ ▼ ▼ ▼     View Class Diagram     ▼ ▼ ▼ ▼ ▼");
                center.remove(up);
                center.remove(cdP);
                center.setLayout(new GridLayout(1, 1));
                center.add(up);
                validate();
            }
        }
    }

    class ClassDiagramPanel extends JPanel {

        private JList EDT, CLS, ASC;
        private JButton viewE, viewC, viewA;
        private ClassDiagramVis cdVis;

        public ClassDiagramPanel(ClassDiagramVis cdv) {
            cdVis = cdv;

            JPanel edtP, clsP, ascP;

            edtP = new JPanel(new BorderLayout());
            clsP = new JPanel(new BorderLayout());
            ascP = new JPanel(new BorderLayout());

            setLayout(new GridLayout(1, 3));

            setBorder(BorderFactory.createTitledBorder(null, "Class Diagram", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));

            EDT = new JList();
            EDT.addListSelectionListener(new MyListSelListener());
            EDT.setListData(cdv.getEnumString());
            EDT.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
            viewE = new JButton("View Enumeration");
            viewE.addActionListener(new MyActionListener());
            viewE.setEnabled(false);
            edtP.add(EDT, BorderLayout.CENTER);
            edtP.add(viewE, BorderLayout.SOUTH);

            CLS = new JList();
            CLS.addListSelectionListener(new MyListSelListener());
            CLS.setListData(cdv.getClassString());
            CLS.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
            viewC = new JButton("View Class");
            viewC.addActionListener(new MyActionListener());
            viewC.setEnabled(false);
            clsP.add(CLS, BorderLayout.CENTER);
            clsP.add(viewC, BorderLayout.SOUTH);

            ASC = new JList();
            ASC.addListSelectionListener(new MyListSelListener());
            ASC.setListData(cdv.getAssocString());
            ASC.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
            viewA = new JButton("View Association");
            viewA.addActionListener(new MyActionListener());
            viewA.setEnabled(false);
            ascP.add(ASC, BorderLayout.CENTER);
            ascP.add(viewA, BorderLayout.SOUTH);

            add(edtP);
            add(clsP);
            add(ascP);
        }

        public JList getASC() {
            return ASC;
        }

        public JList getCLS() {
            return CLS;
        }

        public JList getEDT() {
            return EDT;
        }

        public JButton getViewA() {
            return viewA;
        }

        public JButton getViewC() {
            return viewC;
        }

        public JButton getViewE() {
            return viewE;
        }

        public ClassDiagramVis getCdVis() {
            return cdVis;
        }
    }

    class ViewEFrame extends JFrame {

        JList list1;
        JButton close;

        public ViewEFrame(EnumVis e) {
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ico.png")));

            setUndecorated(true);

            JPanel cont = new JPanel(new BorderLayout());
            cont.setBorder(BorderFactory.createTitledBorder(null, e.getName(), TitledBorder.CENTER, TitledBorder.CENTER, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
            setPreferredSize(new Dimension(300, 300));

            list1 = new JList();
            list1.setEnabled(false);
            list1.setListData(e.getLit());
            cont.add(new JScrollPane(list1), BorderLayout.CENTER);

            close = new JButton("Close");
            close.addActionListener(new MyActionListener());
            cont.add(close, BorderLayout.SOUTH);
            add(cont);
            setResizable(false);
            pack();
            setLocationRelativeTo(null);
        }

        public JButton getClose() {
            return close;
        }
    }

    class ViewCFrame extends JFrame {
        JList list1, list2;
        JButton close;

        public ViewCFrame(ClassVis c) {
            setUndecorated(true);
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ico.png")));

            JPanel cont = new JPanel(new BorderLayout());
            cont.setBorder(BorderFactory.createTitledBorder(null, c.getName(), TitledBorder.CENTER, TitledBorder.CENTER, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));

            setPreferredSize(new Dimension(500, 400));

            JPanel center = new JPanel(new GridLayout(1, 2));

            list1 = new JList();
            list1.setEnabled(false);
            list2 = new JList();
            list2.setEnabled(false);
            list1.setListData(c.getAttrStrings());
            list2.setListData(c.getOpStrings());

            center.add(new JScrollPane(list1));
            center.add(new JScrollPane(list2));

            cont.add(center, BorderLayout.CENTER);

            close = new JButton("Close");
            close.addActionListener(new MyActionListener());
            cont.add(close, BorderLayout.SOUTH);
            add(cont);
            setResizable(false);
            pack();
            setLocationRelativeTo(null);
        }

        public JButton getClose() {
            return close;
        }
    }

    class ViewAFrame extends JFrame {

        JButton close;
        private JTextField classFrom = new JTextField();
        private JTextField classTo = new JTextField();
        private JTextField end1 = new JTextField();
        private JTextField end2 = new JTextField();

        public ViewAFrame(AssocVis a) {
            setUndecorated(true);
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ico.png")));

            JPanel cont = new JPanel(new BorderLayout());
            cont.setBorder(BorderFactory.createTitledBorder(null, a.getName(), TitledBorder.CENTER, TitledBorder.CENTER, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
            setPreferredSize(new Dimension(300, 150));

            JPanel center, west, east;
            JLabel endN1, endN2;

            center = new JPanel(new GridLayout(1, 2));
            west = getTitledPanel("From");
            west.setLayout(new GridLayout(3, 1));
            east = getTitledPanel("To");
            east.setLayout(new GridLayout(3, 1));

            classFrom.setText(a.getSource());
            classFrom.setEditable(false);
            classTo.setText(a.getDest());
            classTo.setEditable(false);
            end1.setText(a.getSourceName());
            end1.setEditable(false);
            end2.setText(a.getDestName());
            end2.setEditable(false);

            endN1 = new JLabel("End Name");
            endN2 = new JLabel("End Name");

            west.add(classFrom);
            west.add(endN1);
            west.add(end1);

            east.add(classTo);
            east.add(endN2);
            east.add(end2);

            center.add(west);
            center.add(east);

            cont.add(center, BorderLayout.CENTER);

            close = new JButton("Close");
            close.addActionListener(new MyActionListener());
            cont.add(close, BorderLayout.SOUTH);
            add(cont);
            setResizable(false);
            pack();
            setLocationRelativeTo(null);
        }

        public JButton getClose() {
            return close;
        }
    }

    class MyTableModel extends AbstractTableModel {

        private String[] columnNames = currentST.getSOarray();
        private Object[][] data = currentST.getNormalStateArray();

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public java.lang.Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {

            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }

        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }

        public void setColumnNames(String[] a) {
            columnNames = a;
        }

        public void setDataValues(Object[][] a) {
            data = a;
        }
    }

    class MyTableModel2 extends AbstractTableModel {

        private String[] columnNames = {"Source State", "Event", "Condition", "Reaction", "Target State"};
        private Object[][] data = trVector.getInitialObjectArray();/*trVector.getObjectArrayForState(stateNames.firstElement());*/


        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public java.lang.Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {

            if (col < 4) {
                return false;
            } else {
                return true;
            }
        }

        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }

        public void setColumnNames(String[] a) {
            columnNames = a;
        }

        public void setDataValues(Object[][] a) {
            data = a;
        }
    }

    class MyTableModel3 extends AbstractTableModel {

        private String[] columnNames = currentST.getSOarray();
        private Object[][] data = currentST.getFinalStateArray();

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public java.lang.Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {

            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }

        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }

        public void setColumnNames(String[] a) {
            columnNames = a;
        }

        public void setDataValues(Object[][] a) {
            data = a;
        }
    }

    public void setUpNameColumn(JTable table, TableColumn nameColumn) {
        JComboBox comboBox = new JComboBox();
        comboBox.setEditable(false);
        comboBox.addItem("Not used");
        for (String s : targetStateNames) {
            comboBox.addItem(s);
        }
        nameColumn.setCellEditor(new DefaultCellEditor(comboBox));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for edit Target State");
        nameColumn.setCellRenderer(renderer);
    }

    public State findState(String n) {
        State ris = null;
        for (State s : states) {
            if (s.getName().equals(n)) {
                ris = s;
            }
        }
        if (ris == null) {
            ris = new State("Not used");
        }
        return ris;
    }

    class MyListSelListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            if (e.getSource().equals(stateMFrame.getStatesL())) {
                stateMFrame.getTransL().setListData(getTransByState(stateMFrame.getStatesL().getSelectedValue().toString(), stateMFrame.getSmv().getTrans()));
            } else if (e.getSource().equals(stateMFrame.getCdP().getEDT())) {
                stateMFrame.getCdP().getViewE().setEnabled(!stateMFrame.getCdP().getEDT().isSelectionEmpty());
            } else if (e.getSource().equals(stateMFrame.getCdP().getCLS())) {
                stateMFrame.getCdP().getViewC().setEnabled(!stateMFrame.getCdP().getCLS().isSelectionEmpty());
            } else if (e.getSource().equals(stateMFrame.getCdP().getASC())) {
                stateMFrame.getCdP().getViewA().setEnabled(!stateMFrame.getCdP().getASC().isSelectionEmpty());
            }
        }
    }

    class MyActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(stateMFrame.getCdP().getViewE())) {
                viewEF = new ViewEFrame(stateMFrame.getCdP().getCdVis().getEnumVis().elementAt(stateMFrame.getCdP().getEDT().getSelectedIndex()));
                viewEF.setVisible(true);
            } else if (e.getSource().equals(stateMFrame.getCdP().getViewC())) {
                viewCF = new ViewCFrame(stateMFrame.getCdP().getCdVis().getClassVis().elementAt(stateMFrame.getCdP().getCLS().getSelectedIndex()));
                viewCF.setVisible(true);
            } else if (e.getSource().equals(stateMFrame.getCdP().getViewA())) {
                viewAF = new ViewAFrame(stateMFrame.getCdP().getCdVis().getAssocVis().elementAt(stateMFrame.getCdP().getASC().getSelectedIndex()));
                viewAF.setVisible(true);
            }
            if (viewEF != null) {
                if (e.getSource().equals(viewEF.getClose())) {
                    viewEF.dispose();
                }
            }
            if (viewCF != null) {
                if (e.getSource().equals(viewCF.getClose())) {
                    viewCF.dispose();
                }
            }
            if (viewAF != null) {
                if (e.getSource().equals(viewAF.getClose())) {
                    viewAF.dispose();
                }
            }



        }
    }
}
