package boundaries;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import Visualization.*;
import datatypes.*;
import datatypes.Error;
import executors.Main;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.event.ListSelectionEvent;
import store.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.event.ListSelectionListener;

public class UpdateStateObserverInv extends JPanel implements BaseBoundary {

    private StateObserverInvVis currentSOINV;
    private Set<Type> availableTypes;
    private DefaultListModel listSO;
    private JPanel StObP;
    private JList SOB;
    private JToggleButton addSOI, editSOI;
    private JButton delSOI;
    private JButton genSOI;
    private MyActionListener actionL;
    private MyListSelListener sel_list;
    private AddSoInvFrame addSoInvFrame;
    private AddSoInvFrame updateSoInvFrame;
    private Vector<String> vect;
    private int selectedSOI;
    private JToggleButton selectedButton;

    public UpdateStateObserverInv(StateObserverInvVis cdDVis, Set<Type> types) {
        currentSOINV = cdDVis;
        availableTypes = types;

        JPanel center;
        JPanel SOsouth;

        setLayout(new BorderLayout(5, 5));

        center = new JPanel(new GridLayout());

        StObP = UpdateProject.getTitledPanel("State Observers and Invariants");

        SOB = new JList();
        listSO = new DefaultListModel();
        sel_list = new MyListSelListener();
        SOB.setModel(listSO);
        SOB.setBorder(new LineBorder(Color.black, 1, false));
        SOB.addListSelectionListener(sel_list);

        addSOI = new JToggleButton("Add");
        editSOI = new JToggleButton("Edit");
        delSOI = new JButton("Delete");
        genSOI = new JButton("Generate Basic State Observers");

        editSOI.setEnabled(false);
        delSOI.setEnabled(false);

        actionL = new MyActionListener();

        SOsouth = new JPanel(new GridLayout());

        SOsouth.add(addSOI);
        SOsouth.add(editSOI);
        SOsouth.add(delSOI);

        StObP.add(genSOI, BorderLayout.NORTH);
        StObP.add(SOB, BorderLayout.CENTER);
        StObP.add(SOsouth, BorderLayout.SOUTH);

        center.add(StObP);

        add(center, BorderLayout.CENTER);

        addSOI.addActionListener(actionL);
        editSOI.addActionListener(actionL);
        delSOI.addActionListener(actionL);
        genSOI.addActionListener(actionL);
    }

    public JToggleButton getAddSOI() {
        return addSOI;
    }

    public JToggleButton getEditSOI() {
        return editSOI;
    }

    public JButton getDelSOI() {
        return delSOI;
    }

    public void setSOINV(StateObserverInvVis soVis, Set<Type> types) {
        currentSOINV = soVis;
        availableTypes = types;

        SOB.setListData(soVis.getSOFullStrings());
    }

    public void correct(Set<Error> errs) {
        throw new UnsupportedOperationException();
    }

    public void addStateObserver(StateObserverInfo el) {
        Main.doAddStateObserver(el);
    }

    public void deleteStateObserver(StateObserver el) {
        Main.doDeleteStateObserver(el);
    }

    public void updStateObserver(StateObserver el, StateObserverInfo newElIN) {
        Main.doUpdStateObserver(el, newElIN);
    }

    public void generateBasicSO() {
        Main.doGenerateBasicSO();
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

    class AddSoInvFrame extends JFrame {

        JButton okSOI;
        JTextField nameSO, inv;
        JComboBox typeSO;
        StateObserverInfo originalInfo;
        StateObserver originalSO;

        public AddSoInvFrame() {
            JPanel north, center, center2, center3, south;
            JPanel back;

            setTitle("Add State Observer");
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ico.png")));
            addWindowListener(new MyWindowAdpt());

            back = new JPanel(new BorderLayout(5, 5));
            north = UpdateProject.getTitledPanel("Name");
            north.setLayout(new GridLayout());
            center = UpdateProject.getTitledPanel("Type");
            center2 = UpdateProject.getTitledPanel("Invariant");
            center3 = new JPanel(new BorderLayout());
            south = new JPanel(new BorderLayout());

            okSOI = new JButton("Ok");

            okSOI.addActionListener(actionL);

            nameSO = new JTextField();


            north.add(nameSO);

            typeSO = UpdateProject.getTypeList("Choose one");

            inv = new JTextField();

            center.add(typeSO, BorderLayout.NORTH);

            center2.add(inv);

            south.add(okSOI, BorderLayout.EAST);
            center3.add(center, BorderLayout.NORTH);
            center3.add(center2, BorderLayout.SOUTH);

            back.add(north, BorderLayout.NORTH);
            back.add(center3, BorderLayout.CENTER);
            back.add(south, BorderLayout.SOUTH);

            setPreferredSize(new Dimension(300, 225));
            add(back);
            pack();
            setLocationRelativeTo(null);
        }

        public JButton getOkSOI() {
            return okSOI;
        }

        public JTextField getNameSO() {
            return nameSO;
        }

        public JTextField getInv() {
            return inv;
        }

        public JComboBox getTypeSO() {
            return typeSO;
        }

        public StateObserverInfo getOriginalInfo() {
            return originalInfo;
        }

        public StateObserver getOriginalSO() {
            return originalSO;
        }

        public void setTypeSOI(JComboBox typeSO) {
            this.typeSO = typeSO;
        }

        public void setOriginalSO(StateObserver originalSO) {
            this.originalSO = originalSO;
        }

        public void setOriginalInfo(StateObserverInfo originalInfo) {
            this.originalInfo = originalInfo;
        }
    }

//--------------- Listeners ----------------------------------------------------
    class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(genSOI)) {
                generateBasicSO();
            } else if (e.getSource().equals(addSOI)) {
                selectedButton = addSOI;
                addSoInvFrame = new AddSoInvFrame();
                addSoInvFrame.setVisible(true);
            } else if (e.getSource().equals(editSOI)) {
                selectedButton = editSOI;
                StateObserverVis vis_sup;
                updateSoInvFrame = new AddSoInvFrame();
                if(currentSOINV.getSOVis().elementAt(SOB.getSelectedIndex()).getName().equals("Final")){
                    updateSoInvFrame.getNameSO().setEditable(false);
                    updateSoInvFrame.getTypeSO().setEnabled(false);
                }
                updateSoInvFrame.setTitle("Edit State Observer");
                vis_sup = currentSOINV.vis.get(SOB.getSelectedIndex());
                updateSoInvFrame.originalSO = currentSOINV.vis.get(SOB.getSelectedIndex()).getLinkedSO();
                updateSoInvFrame.getNameSO().setText(vis_sup.getName());
                updateSoInvFrame.getTypeSO().setSelectedItem(UpdateProject.getType(currentSOINV.getSOVis().elementAt(selectedSOI).getType()).getName());

                updateSoInvFrame.inv.setText(vis_sup.getInv());
                updateSoInvFrame.setVisible(true);
            } else if (e.getSource().equals(delSOI)) {
                deleteStateObserver(currentSOINV.getSOVis().elementAt(SOB.getSelectedIndex()).getLinkedSO());
            }
            if (addSoInvFrame != null) {
                if (e.getSource().equals(addSoInvFrame.getOkSOI())) {
                    addStateObserver(new StateObserverInfo(UpdateProject.getType(addSoInvFrame.getTypeSO().getSelectedItem().toString()), new Expr(addSoInvFrame.getInv().getText()), addSoInvFrame.getNameSO().getText()));
                    addSoInvFrame.setVisible(false);
                    addSoInvFrame.dispose();
                    addSOI.setSelected(false);
                }
            }
            if (updateSoInvFrame != null) {
                if (e.getSource().equals(updateSoInvFrame.getOkSOI())) {
                    updStateObserver(updateSoInvFrame.originalSO, new StateObserverInfo(UpdateProject.getType(updateSoInvFrame.getTypeSO().getSelectedItem().toString()), new Expr(updateSoInvFrame.getInv().getText()), updateSoInvFrame.getNameSO().getText()));
                    updateSoInvFrame.setVisible(false);
                    updateSoInvFrame.dispose();
                    editSOI.setSelected(false);
                }
            }
        }
    }

    class MyListSelListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting() == false) {
                if (!SOB.isSelectionEmpty()) {
                    selectedSOI = SOB.getSelectedIndex();
                    
                    editSOI.setEnabled(true);
                    delSOI.setEnabled(true);

                    if(currentSOINV.getSOVis().elementAt(SOB.getSelectedIndex()).getName().equals("Final"))
                        delSOI.setEnabled(false);
                }
                else{
                    editSOI.setEnabled(false);
                    delSOI.setEnabled(false);
                }
            }
        }
    }

    class MyWindowAdpt extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            selectedButton.setSelected(false);
        }
    }
}
