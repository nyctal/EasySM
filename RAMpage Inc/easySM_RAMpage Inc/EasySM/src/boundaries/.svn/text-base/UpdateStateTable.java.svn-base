package boundaries;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.BorderFactory;
import javax.swing.border.*;

import java.awt.*;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import Visualization.*;
import Visualization.EventCondReactVis.CRVis;
import Visualization.StateTableVis.RowVis;
import datatypes.*;
import datatypes.Action;
import java.awt.BorderLayout;
import store.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import executors.Main;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class UpdateStateTable extends JPanel implements BaseBoundary {

    private StateTableVis currentST;
    private Set<StateObserverVis> allSO;
    private List<StateObserverVis> finiteSO;
    private EventCondReactVis currentEVCR;
    private Set<String> typeString;
    private List<SplitInfo> splitInfo;
    private JButton selectUSO, splitCR, generateS, confT;
    private JTable table;
    private SplitCRFrame splitCRF;
    private SelectUsedSOFrame selectUsedSOFrame;
    private MyActionListener actionL;
    private MyTableModel mod;
    private JScrollPane scrollPane;

    public UpdateStateTable(StateTableVis stVis, Set<StateObserverVis> allSOvis, EventCondReactVis evCondReact) {
        JPanel north, south;
        currentST = stVis;
        allSO = allSOvis;
        
        finiteSO=new LinkedList<StateObserverVis>();
        StateObserverVis fin=null;
        for (StateObserverVis s : allSO){
            if (s.getName().equals("Final")) fin=s;
        }
        for (StateObserverVis s : allSO){
            if (s.getLinkedSO().getType().isFinite() && !s.getName().equals("Final")){
                finiteSO.add(s);
            }
        }
        finiteSO.add(fin);
        

        currentEVCR = evCondReact;

        actionL = new MyActionListener();

        setBorder(BorderFactory.createTitledBorder(null, "Update State Table", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));

        setName("State Table");
        setLayout(new BorderLayout(5, 5));
        selectUSO = new JButton("Select Used SO");
        selectUSO.addActionListener(actionL);
        splitCR = new JButton("Split CR");
        splitCR.addActionListener(actionL);
        generateS = new JButton("Generate States");
        generateS.addActionListener(actionL);
        confT = new JButton("Confirm Table");
        confT.addActionListener(actionL);

        north = new JPanel(new GridLayout(1, 3));
        south = new JPanel(new GridLayout(1, 1));
        north.add(selectUSO);
        north.add(splitCR);
        north.add(generateS);
        south.add(confT);


        mod = new MyTableModel();
        table = new JTable(mod);

        table.getTableHeader().setReorderingAllowed(false);
        setUpNameColumn(table, table.getColumnModel().getColumn(currentST.getSOarray().length - 1));

        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        add(north, BorderLayout.NORTH);
        add(south, BorderLayout.SOUTH);
    }

    public void setST(StateTableVis stVis) {
        currentST = stVis;
    }

    public void selectUsedSO(List<StateObserverVis> soUsed) {
        Main.doSelectUsedSO(soUsed);
    }

    public void setEVCR(EventCondReactVis evcr) {
        currentEVCR = evcr;
        splitInfo = new LinkedList<SplitInfo>();
        for (CRVis c : evcr.getFullCondReactVis()) {
            splitInfo.add(new SplitInfo(c.getLinkedCR()));
        }
        splitCRF = new SplitCRFrame();
        splitCRF.setVisible(true);
    }

    public void splitCR(List<SplitInfo> splitted) {
        Main.doSplitCR(splitted);
    }

    public void generateStates() {
        Main.doGenerateStates();
    }

    public void defineState(Row row, String name) {
        Main.doDefineState(row, name);
    }

    public void splitCR() {
        Main.doSplitCR();
    }

    public void validateST() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void ko(Report r) {
    }

    @Override
    public void ok(Report r) {
    }

    @Override
    public void close() {
    }

    @Override
    public void validate(Element el) {
    }

    public void setSO(Set<StateObserverVis> s) {
        allSO = s;
        finiteSO=new LinkedList<StateObserverVis>();
        StateObserverVis fin=null;
        for (StateObserverVis sov : allSO){
            if (sov.getName().equals("Final")) fin=sov;
        }
        for (StateObserverVis sov : allSO){
            if (sov.getLinkedSO().getType().isFinite() && !sov.getName().equals("Final")){
                finiteSO.add(sov);
            }
        }
        finiteSO.add(fin);
    }

    class SplitCRFrame extends JFrame {
        private JButton okSB, confirmButton;
        private JSplitPane splitPane;
        private JList eventList, condList;
        private JTextField condTxt, condTxtl, condTxtr;
        private JTextField eventTxt, eventTxtl, eventTxtr;
        private MyListListener listL;

        public SplitCRFrame() {
            Container back = getContentPane();
            JPanel ECR, cond, react, panelSplit, condS, reactS, center, south, confirmPane;

            setTitle("Split Conditions/Reactions");
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ico.png")));

            ECR = UpdateProject.getTitledPanel("Events and Conditions/Reactions");
            cond = UpdateProject.getTitledPanel("Split Condition");
            cond.setLayout(new GridLayout(2, 1, 5, 5));
            react = UpdateProject.getTitledPanel("Split Reaction");
            react.setLayout(new GridLayout(2, 1, 5, 5));
            condS = new JPanel(new GridLayout(1, 2, 5, 5));
            reactS = new JPanel(new GridLayout(1, 2, 5, 5));
            center = new JPanel(new GridLayout(1, 2, 5, 5));
            south = new JPanel(new BorderLayout());
            confirmPane = new JPanel(new BorderLayout());

            eventList = new JList();
            condList = new JList();

            splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(eventList), new JScrollPane(condList));
            splitPane.setOneTouchExpandable(true);
            splitPane.setContinuousLayout(true);

            ECR.add(splitPane);
            center.add(ECR);

            condTxt = new JTextField();
            condTxt.setEditable(false);
            condTxtl = new JTextField();
            condTxtr = new JTextField();

            eventTxt = new JTextField();
            eventTxt.setEditable(false);
            eventTxtl = new JTextField();
            eventTxtr = new JTextField();

            condS.add(condTxtl);
            condS.add(condTxtr);
            reactS.add(eventTxtl);
            reactS.add(eventTxtr);

            cond.add(condTxt);
            cond.add(condS);
            react.add(eventTxt);
            react.add(reactS);

            panelSplit = new JPanel(new GridLayout(2, 1));
            confirmPane.setBorder(BorderFactory.createTitledBorder(null, "Split and confirm used parts of CR", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));

            panelSplit.add(cond);
            panelSplit.add(react);
            


            eventList.setListData(currentEVCR.evString);
            listL = new MyListListener();
            eventList.addListSelectionListener(listL);
            condList.addListSelectionListener(listL);

            okSB = new JButton("Ok");
            confirmButton = new JButton("Confirm");

            confirmPane.add(panelSplit, BorderLayout.CENTER);
            confirmPane.add(confirmButton, BorderLayout.SOUTH);
            center.add(confirmPane);

            ActListener actListener = new ActListener();
            okSB.addActionListener(actListener);
            confirmButton.addActionListener(actListener);

            south.add(okSB, BorderLayout.EAST);

            back.add(center, BorderLayout.CENTER);
            back.add(south, BorderLayout.SOUTH);

            setPreferredSize(new Dimension(800, 250));
            setResizable(false);
            pack();
            setLocationRelativeTo(null);
        }

        public JList getCondList() {
            return condList;
        }

        public JTextField getCondTxt() {
            return condTxt;
        }

        public JTextField getCondTxtl() {
            return condTxtl;
        }

        public JTextField getCondTxtr() {
            return condTxtr;
        }

        public JList getSoList() {
            return eventList;
        }

        public JTextField getEventTxt() {
            return eventTxt;
        }

        public JTextField getEventTxtl() {
            return eventTxtl;
        }

        public JTextField getEventTxtr() {
            return eventTxtr;
        }

        public MyListListener getListL() {
            return listL;
        }

        public JButton getOkSB() {
            return okSB;
        }

        public JButton getConfirmButton() {
            return confirmButton;
        }

        public void setCondList(JList condList) {
            this.condList = condList;
        }

        public void setCondTxt(JTextField condTxt) {
            this.condTxt = condTxt;
        }

        public void setCondTxtl(JTextField condTxtl) {
            this.condTxtl = condTxtl;
        }

        public void setCondTxtr(JTextField condTxtr) {
            this.condTxtr = condTxtr;
        }

        public void setSoList(JList eventList) {
            this.eventList = eventList;
        }

        public void setEventTxt(JTextField eventTxt) {
            this.eventTxt = eventTxt;
        }

        public void setEventTxtl(JTextField eventTxtl) {
            this.eventTxtl = eventTxtl;
        }

        public void setEventTxtr(JTextField eventTxtr) {
            this.eventTxtr = eventTxtr;
        }

        public void setListL(MyListListener listL) {
            this.listL = listL;
        }

        public void setOkSB(JButton okSB) {
            this.okSB = okSB;
        }

        public SplitInfo getSplitInfo(CondReact c) {
            SplitInfo temp = null;
            for (SplitInfo sp : splitInfo) {
                if (c.equals(sp.getCR())) {
                    temp = sp;
                }
            }
            return temp;
        }

        class MyListListener implements ListSelectionListener {

            public void valueChanged(ListSelectionEvent e) {
                if (e.getSource().equals(eventList)) {
                    if (!eventList.isSelectionEmpty()) {
                        int sel = eventList.getSelectedIndex();
                        condList.setListData(currentEVCR.getEvents().elementAt(sel).getCondStrings());
                    } else {
                        condList.removeAll();
                    }
                }
                if (e.getSource().equals(condList)) {
                    if (!condList.isSelectionEmpty()) {
                        int selCond = condList.getSelectedIndex();
                        int selEv = eventList.getSelectedIndex();
                        CondReact r = currentEVCR.getEvents().elementAt(selEv).getCR().elementAt(selCond).getLinkedCR();
                        SplitInfo sp = getSplitInfo(r);
                        condTxt.setText(sp.getCR().getCond().getExpr());
                        eventTxt.setText(sp.getCR().getReact().getAct());
                        condTxtl.setText(sp.getCondUsed().getExpr());
                        eventTxtl.setText(sp.getReactUsed().getAct());
                        condTxtr.setText(sp.getCondRest().getExpr());
                        eventTxtr.setText(sp.getReactRest().getAct());
                    } else {
                        condTxt.setText("");
                        eventTxt.setText("");
                        condTxtl.setText("");
                        eventTxtl.setText("");
                        condTxtr.setText("");
                        eventTxtr.setText("");
                    }
                }
            }
        }

        class ActListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(confirmButton)) {
                    int selCond = condList.getSelectedIndex();
                    int selEv = eventList.getSelectedIndex();
                    CondReact r = currentEVCR.getEvents().elementAt(selEv).getCR().elementAt(selCond).getLinkedCR();
                    SplitInfo sp = getSplitInfo(r);

                    System.out.println(condTxtr.getText() + " " + condTxtl.getText() + " " + eventTxtr.getText() + " " + eventTxtl.getText() + " ");

                    sp.setCondRest(new Expr(condTxtr.getText()));
                    sp.setCondUsed(new Expr(condTxtl.getText()));
                    sp.setReactRest(new Action(eventTxtr.getText()));
                    sp.setReactUsed(new Action(eventTxtl.getText()));
                }
                if (e.getSource().equals(okSB)) {
                    splitCR(splitInfo);
                    dispose();
                }
            }
        }
    }

    class SelectUsedSOFrame extends JFrame {

        private JList list;
        private Vector<String> soVect;
        private List<StateObserverVis> soVisSet;

        public SelectUsedSOFrame() {
            setTitle("Select State Observers");
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ico.png")));

            soVect = new Vector<String>();
            for (StateObserverVis s : finiteSO) {
                soVect.add(s.getName());
            }

            list = new JList(createData(soVect));
            list.setCellRenderer(new CheckListRenderer());
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setBorder(new EmptyBorder(0, 4, 0, 0));

            for (String s : currentST.getUsedSoNames()) {
                for (int i = 0; i < list.getModel().getSize(); i++) {
                    CheckableItem item = (CheckableItem) list.getModel().getElementAt(i);
                    if (s.equals(item.toString())) {
                        item.setSelected(true);
                    }
                }
            }
            for (int i = 0; i < list.getModel().getSize(); i++) {
                CheckableItem item = (CheckableItem) list.getModel().getElementAt(i);
                if (item.toString().equals("Final")) {
                    item.setSelected(true);
                }
            }

            list.addMouseListener(new MouseAdapter() {

                public void mouseClicked(MouseEvent e) {
                    int index = list.locationToIndex(e.getPoint());
                    CheckableItem item = (CheckableItem) list.getModel().getElementAt(index);
                    if (!item.toString().equals("Final"))item.setSelected(!item.isSelected());
                    Rectangle rect = list.getCellBounds(index, index);
                    list.repaint(rect);
                }
            });
            JScrollPane sp = new JScrollPane(list);


            JButton okButton = new JButton("Ok");
            okButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    ListModel model = list.getModel();
                    int n = model.getSize();
                    soVisSet = new LinkedList<StateObserverVis>();
                    for (int i = 0; i < n; i++) {
                        CheckableItem item = (CheckableItem) model.getElementAt(i);
                        if (item.isSelected()) {
                            soVisSet.add(new StateObserverVis(item.toString(), null, null));
                        }
                    }
                    selectUsedSO(soVisSet);
                    selectUsedSOFrame.dispose();
                }
            });

            JPanel panel = new JPanel(new GridLayout());
            panel.add(okButton);

            getContentPane().add(sp, BorderLayout.CENTER);
            getContentPane().add(panel, BorderLayout.SOUTH);

            setPreferredSize(new Dimension(300, 200));
            pack();
            setLocationRelativeTo(null);
        }

        private CheckableItem[] createData(Vector<String> strs) {
            int n = strs.size();
            CheckableItem[] items = new CheckableItem[n];
            for (int i = 0; i < n; i++) {
                items[i] = new CheckableItem(strs.elementAt(i));
            }
            return items;
        }

        class CheckableItem {

            private String str;
            private boolean isSelected;

            public CheckableItem(String str) {
                this.str = str;
                isSelected = false;
            }

            public void setSelected(boolean b) {
                isSelected = b;
            }

            public boolean isSelected() {
                return isSelected;
            }

            public String toString() {
                return str;
            }
        }

        class CheckListRenderer extends JCheckBox implements ListCellRenderer {

            public CheckListRenderer() {
                setBackground(UIManager.getColor("List.textBackground"));
                setForeground(UIManager.getColor("List.textForeground"));
            }

            public Component getListCellRendererComponent(JList list, Object value,
                    int index, boolean isSelected, boolean hasFocus) {
                setEnabled(list.isEnabled());
                setSelected(((CheckableItem) value).isSelected());
                setFont(list.getFont());
                setText(value.toString());
                return this;
            }
        }
    }

    class MyActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(selectUSO)) {
                selectUsedSOFrame = new SelectUsedSOFrame();
                selectUsedSOFrame.setVisible(true);
            } else if (e.getSource().equals(generateS)) {
                if (scrollPane != null) {
                    remove(scrollPane);
                    validate();
                }

                generateStates();
                mod = new MyTableModel();
                table = new JTable(mod);
                table.getTableHeader().setReorderingAllowed(false);
                setUpNameColumn(table, table.getColumnModel().getColumn(currentST.getSOarray().length - 1));
                scrollPane = new JScrollPane(table);
                for (int i = 0; i < (mod.getRowCount()); i++) {
                    table.setValueAt("Impossible", i, mod.getColumnCount() - 1);
                }
                add(scrollPane, BorderLayout.CENTER);
                validate();
            } else if (e.getSource().equals(confT)) {
                int i;
                int numCol = currentST.getRowVis().size();
                int numSO = currentST.getUsedSoVis().size();
                for (i = 0; i < numCol; i++) {
                    String name = mod.getValueAt(i, numSO).toString();
                    RowVis r = currentST.getRowVis().elementAt(i);
                    defineState(r.getLinkedRow(), name);
                }
                EasySM.activeGenSM();
            } else if (e.getSource().equals(splitCR)) {
                splitCR();
            }
        }
    }

    public void setUpNameColumn(JTable table, TableColumn nameColumn) {
        JComboBox comboBox = new JComboBox();
        comboBox.setEditable(true);
        comboBox.addItem("Impossible");
        nameColumn.setCellEditor(new DefaultCellEditor(comboBox));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for edit State Name");
        nameColumn.setCellRenderer(renderer);
    }

    class MyTableModel extends AbstractTableModel {

        private String[] columnNames = currentST.getSOarray();
        private Object[][] data = currentST.getROWarray();

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
}
