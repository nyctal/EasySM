package boundaries;

import Visualization.ProjectVis;
import Visualization.StateMachineVis;
import datatypes.Report;
import executors.Main;
import java.awt.*;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Locale;
import javax.swing.*;
import javax.swing.JMenuBar;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.filechooser.FileFilter;


import store.Element;

public class EasySM extends JFrame implements BaseBoundary{
    private JMenuBar menu;
    private JMenu file;
    private static JMenu stateM;
    private JMenu help;
    private JMenuItem newPrj;
    private JMenuItem openPrj;
    private JMenuItem closePrj;
    private JMenuItem exit;
    private JMenuItem genSM;
    private JMenuItem aboutESM;
    private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    public UpdateProject projectFrame;
    private MyActionListener actionL;
    private MyToggleListener toggleL;
    private MyWindowAdapter windowL;
    private static JTextField errorMessage;
    private aboutFrame aboutF;

    private Container cont;
    final JFileChooser fc;
    final JFileChooser fcm;
    FileFilter fileFilter;
    
    private String prjName;
    private boolean prjOpen = false;

    public EasySM(){
        setTitle("EasySM - State Machine Designer");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ico.png")));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        try{
            UIManager.setLookAndFeel(PREFERRED_LOOK_AND_FEEL);
        } catch(Exception e){
            System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
            + " on this platform:" + e.getMessage());
        }

        cont = getContentPane();

        toggleL = new MyToggleListener();
        actionL = new MyActionListener();
        windowL = new MyWindowAdapter();
        addWindowListener(windowL);

        fc = new JFileChooser();
        fcm = new JFileChooser();

        fileFilter = new fileFilter();

        fcm.setAcceptAllFileFilterUsed(false);
        fcm.setFileFilter(fileFilter);

        fc.setAcceptAllFileFilterUsed(false);
        fc.setFileFilter(fileFilter);

        cont.setLayout(new BorderLayout());
        
        errorMessage = new JTextField();
        errorMessage.setFont(new Font("Arial Black", Font.PLAIN, 14));
        errorMessage.setBackground(new Color(0,0,0));
        errorMessage.setEditable(false);

        cont.add(errorMessage, BorderLayout.SOUTH);

        setJMenuBar(getMenu());
        setSize(900,600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public UpdateProject getUpdateProject(){ return projectFrame;   }
    public static void setErrorMessage(String m, char c){
        errorMessage.setForeground(c=='r'? new Color(255,0,0): new Color(0,255,0));
        errorMessage.setText(m);
    }
    public void addProjectWindow(UpdateProject p){
        cont.add(p, BorderLayout.CENTER);
        cont.validate();
//------------------ Update CD -------------------------------------------------
        projectFrame.getUpdateCDPanel().getAddEnum().addItemListener(toggleL);
        projectFrame.getUpdateCDPanel().getEditEnum().addItemListener(toggleL);

        projectFrame.getUpdateCDPanel().getAddCl().addItemListener(toggleL);
        projectFrame.getUpdateCDPanel().getEditCl().addItemListener(toggleL);

        projectFrame.getUpdateCDPanel().getAddAs().addItemListener(toggleL);
        projectFrame.getUpdateCDPanel().getEditAs().addItemListener(toggleL);
//------------------ Update ECR ------------------------------------------------
        projectFrame.getUpdateECRPanel().getAddEv().addItemListener(toggleL);
        projectFrame.getUpdateECRPanel().getEditEv().addItemListener(toggleL);

        projectFrame.getUpdateECRPanel().getAddCR().addItemListener(toggleL);
        projectFrame.getUpdateECRPanel().getEditCR().addItemListener(toggleL);
//------------------ Update SOI ------------------------------------------------
        projectFrame.getUpdateSOIPanel().getAddSOI().addItemListener(toggleL);
        projectFrame.getUpdateSOIPanel().getEditSOI().addItemListener(toggleL);
    }

    private JMenuBar getMenu(){
        menu = new JMenuBar();
        
        file = new JMenu("File");
        stateM = new JMenu("State Machine");
        stateM.setEnabled(false);
        help = new JMenu("?");
        
        newPrj = new JMenuItem("New Project");
        openPrj = new JMenuItem("Open Project");
        closePrj = new JMenuItem("Close Project");
        closePrj.setEnabled(false);
        exit = new JMenuItem("Exit");
        
        genSM = new JMenuItem("Generate State Machine");
        
        aboutESM = new JMenuItem("About EasySM");
        
        file.add(newPrj);
        file.add(openPrj);
        file.add(closePrj);
        file.addSeparator();
        file.add(exit);
        
        stateM.add(genSM);
        
        help.add(aboutESM);
        
        menu.add(file);
        menu.add(stateM);
        menu.add(help);

        newPrj.addActionListener(actionL);
        openPrj.addActionListener(actionL);
        closePrj.addActionListener(actionL);
        exit.addActionListener(actionL);
        genSM.addActionListener(actionL);
        aboutESM.addActionListener(actionL);

        return menu;
    }
    private void closeProcedure(){
        int ret = JOptionPane.showConfirmDialog (EasySM.this, "Save changes to " + prjName,
                                                     "Close EasySM", JOptionPane.YES_NO_CANCEL_OPTION);

        switch(ret){
            case JOptionPane.YES_OPTION:
                closeProject();
                System.exit (0);
                break;
            case JOptionPane.NO_OPTION:
                System.exit (0);
        }
    }
    public static void activeGenSM(){   stateM.setEnabled(true);}

    /**
     *
     * @param filename
     * @return
     */
    public void newProject(File file) {
        Main.doNewProject(file);
    }

    /**
     *
     * @param fName
     * @return
     */
    public void openProject(File file) {
        Main.doOpenProject(file);
    }

    /**
     *
     * @return
     */
    public void closeProject() {
        Main.doClose();
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
//-------------- Frame ---------------------------------------------------------
    class aboutFrame extends  JFrame{
        private JButton close;

        public aboutFrame(){
            String lnf = "javax.swing.plaf.metal.MetalLookAndFeel";

            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ico.png")));
            setUndecorated(true);
            try{
                UIManager.setLookAndFeel(lnf);
            } catch(Exception e){
                System.err.println("Cannot install " + lnf
                + " on this platform:" + e.getMessage());
            }

            close = new JButton("Close");
            close.setBackground(Color.BLACK);
            close.setForeground(Color.GREEN);
            close.addActionListener(actionL);
            ImageIcon ii=new ImageIcon(getClass().getResource("/AboutESM.png"));
            JLabel i = new JLabel(ii);
            setLayout(new BorderLayout());
            add(i,BorderLayout.CENTER);
            add(close,BorderLayout.SOUTH);
            pack();
            setLocationRelativeTo(null);
        }

        public JButton getClose() {
            return close;
        }
    }
//-------------- Listeners -----------------------------------------------------
    class MyActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource().equals(newPrj)){
                int returnVal = fcm.showDialog(EasySM.this, "Set Name and Working Directory");

                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fcm.getSelectedFile();
                    setTitle("EasySM - State Machine Designer - " + file.getName());
                    newProject(file);
                    prjOpen = true;
                    prjName = file.getName();
                    closePrj.setEnabled(true);
                }
            }            
            else if(e.getSource().equals(openPrj)){
                int returnVal = fc.showOpenDialog(EasySM.this);

                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    setTitle("EasySM - State Machine Designer - " + file.getName());
                    openProject(file);
                    prjOpen = true;
                    prjName = file.getName();
                    closePrj.setEnabled(true);
                }
            }
            else if(e.getSource().equals(closePrj)){
                closeProject();
                prjOpen = false;
                closePrj.setEnabled(false);
            }
            else if(e.getSource().equals(exit)){
                if(prjOpen) closeProcedure();
                else        System.exit(0);
            }
            else if(e.getSource().equals(genSM)){
                //setEnabled(false);
                projectFrame.generateSM();
            }
            else if(e.getSource().equals(aboutESM)){
                aboutF = new aboutFrame();
                aboutF.setVisible(true);
            }
            if(aboutF != null){
                if(e.getSource().equals(aboutF.getClose()))
                    aboutF.dispose();
            }
        }
    }

    class MyToggleListener implements ItemListener{
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange() == ItemEvent.SELECTED){
                setEnabled(false);
                requestFocus();
            }
            else
                setEnabled(true);
        }
    }

    class MyWindowAdapter extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent e){
            if(e.getSource().equals(EasySM.this)){
                if(prjOpen)   closeProcedure();
                else          System.exit(0);
            }
            else if(e.getSource().equals(projectFrame.getStateMFrame())){
                setEnabled(true);
            }
        }
    }

    class fileFilter extends FileFilter {

        @Override
        public boolean accept(File f) {
            return f.isDirectory() || f.getName().toLowerCase().endsWith(".esm");
        }

        @Override
        public String getDescription() {
            //throw new UnsupportedOperationException("Not supported yet.");
            return "EasySM project files (*.esm)";
        }
    }
}