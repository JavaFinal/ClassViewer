import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

class ViewFrame extends JFrame implements ActionListener, TreeSelectionListener{
	private JPanel left = new JPanel();
	private JPanel right;
	private JPanel treeP = new JPanel();
	private JTree tree;
	private JTextArea useP, text;
	private JMenuBar menubar;
	private JMenu File;
	private JMenuItem Open, Save, Exit;
	private JScrollPane spTree, spText;
	private ClassInfo ci;
	private Value v;
	private DefaultMutableTreeNode root;
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
		
		left.setLayout(new GridLayout(0,1));
		useP = new JTextArea("something");
		left.add(useP);

		right = new JPanel();
		text = new JTextArea(20,40);
		spText = new JScrollPane(text);
		spText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		spText.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		right.add(spText);

		add(left,BorderLayout.WEST);
		add(right,BorderLayout.EAST);
//		pack();
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Open){
			JFileChooser jfc=new JFileChooser("C:\\Users\\user\\git\\ClassViewer\\JavaFinal");
			v=new Value();
			int choice = jfc.showOpenDialog(this);
			if(choice==JFileChooser.APPROVE_OPTION){
				File f = jfc.getSelectedFile();
				v.setFileName(f.getName());
				new ReadFileData(v.getFileName());
				makeTree();
			}
		} else if(e.getSource() == Save){
			JFileChooser jfc=new JFileChooser();
			jfc.showSaveDialog(this);
			File f = jfc.getSelectedFile();
			System.out.println(f.getAbsoluteFile());
		} else if(e.getSource() == Exit){
			System.exit(0);
		}
	}

	private void makeTree() {
		// TODO Auto-generated method stub
		ci=new ClassInfo();
		
		DefaultMutableTreeNode root=new DefaultMutableTreeNode(ci.className);
		for(int i=0;i<ci.getMName().size();i++){
			root.add(new DefaultMutableTreeNode(ci.getMName().get(i)));
		}
		for(int i=0;i<ci.getDName().size();i++){
			root.add(new DefaultMutableTreeNode(ci.getDName().get(i)+":"+ci.getDType().get(i)));
		}
		tree = new JTree(root);
		tree.addTreeSelectionListener(this);
		treeP.add(tree);
		spTree = new JScrollPane(treeP);
		spTree.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		spTree.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		left.add(spTree);
		left.add(useP);
		setVisible(true);
	}

	public void valueChanged(TreeSelectionEvent e) {
		String node=e.getNewLeadSelectionPath().getLastPathComponent().toString();
		ci=new ClassInfo();
		if(node.contains(":")){
			text.setText("변수");
		}
		else if(node.contains("(")){
				String[] currentM=node.split("\\(");
//				System.out.println(currentM[0]);
				MethodClass mc=new MethodClass(currentM[0]);
				text.setText(mc.getMethod().toString());
		} else if(node.equals(ci.className)){
				text.setText("NAME | "+"TYPE | "+"ACCESS");
		}
		
	}
}
public class ClassViewer {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ViewFrame();
	}
}
