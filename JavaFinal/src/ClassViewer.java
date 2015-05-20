import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

class ViewFrame extends JFrame implements ActionListener{
	private JPanel left;
	private JPanel right;
	private JPanel treeP;
	private JTree tree;
	private JTextArea useP;
	private JTextArea text;
	private JMenuBar menubar;
	private JMenu File;
	private JMenuItem Open;
	private JMenuItem Save;
	private JMenuItem Exit;
	
	public ViewFrame(){
		setTitle("Class Viewer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,400);
		menubar=new JMenuBar();
		File =new JMenu("File");
		Open= new JMenuItem("Open");
		Save= new JMenuItem("Save");
		Exit= new JMenuItem("Exit");
		File.add(Open);
		File.add(Save);
		File.add(Exit);
		menubar.add(File);
		setJMenuBar(menubar);
		
		Open.addActionListener(this);
		Save.addActionListener(this);
		Exit.addActionListener(this);
		
		left = new JPanel();
		left.setLayout(new GridLayout(0,1));
		treeP = new JPanel();
		useP = new JTextArea("something");
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("Queue");
		DefaultMutableTreeNode method1=new DefaultMutableTreeNode("Queue()");
		DefaultMutableTreeNode method2=new DefaultMutableTreeNode("~Queue()");
		root.add(method1);
		root.add(method2);
		tree = new JTree(root);
		treeP.add(tree);
		left.add(treeP);
		left.add(useP);
	
		right = new JPanel();
		text = new JTextArea("something");
		right.add(text);
		add(left,BorderLayout.WEST);
		add(right,BorderLayout.EAST);
//		pack();
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Open){
			JFileChooser jfc=new JFileChooser();
			jfc.showOpenDialog(this);
			File f = jfc.getSelectedFile();
			System.out.println(f.getAbsoluteFile());
		} else if(e.getSource() == Save){
			JFileChooser jfc=new JFileChooser();
			jfc.showSaveDialog(this);
			File f = jfc.getSelectedFile();
			System.out.println(f.getAbsoluteFile());
		} else if(e.getSource() == Exit){
			System.exit(0);
		}
	}
}
public class ClassViewer {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ViewFrame();
	}
}
