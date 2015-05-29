import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

class ViewFrame extends JFrame implements ActionListener, TreeSelectionListener{
	private JPanel left,right,treeP;
	private JTree tree;
	private JTextArea useP, text;
	private JMenuBar menubar;
	private Border border;
	private JMenu File;
	private JMenuItem Open, Save, Exit;
	private JScrollPane spTree, spText, spTable;
	private JTable table;
	
	private ClassInfo ci;
	
	private String oriMethod=null;
	private String changedText=null;
	private String beforenode;
	private String colName[];
	
	public ViewFrame(){
		setTitle("Class Viewer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(610,400);
		
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
		useP = new JTextArea("");
		useP.setEditable(false);
		border = BorderFactory.createTitledBorder("use");
		useP.setBorder(border);	
		useP = new JTextArea();
		left.add(useP);

		right = new JPanel();
		text = new JTextArea(20,40);
		
		add(left,BorderLayout.WEST);
		add(right,BorderLayout.EAST);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Open){
			JFileChooser jfc=new JFileChooser("C:\\Users\\user\\git\\ClassViewer\\JavaFinal");
			int choice = jfc.showOpenDialog(this);
			if(choice==JFileChooser.APPROVE_OPTION){
				File f = jfc.getSelectedFile();
				new ReadFileData(f);
				makeTree();
			}
		} else if(e.getSource() == Save){
			JFileChooser jfc=new JFileChooser("C:\\Users\\user\\git\\ClassViewer\\JavaFinal");
			jfc.showSaveDialog(this);
			File f = jfc.getSelectedFile();
//			System.out.println(f.getAbsoluteFile());
			PrintWriter out = null;
			try {
				if(beforenode!=null&&oriMethod!=null && changedText!=null && !oriMethod.equals(changedText)){
					int flag =Value.buffer.indexOf("::"+beforenode);
					int flag2 =Value.buffer.indexOf("{",flag);
					int start = Value.buffer.indexOf(oriMethod,flag2);
					int end =start+oriMethod.length()-1;
					changeMethod(start,end,changedText);
					oriMethod=null;
					changedText=null;
					beforenode=null;
				}
				out=new PrintWriter(f);
				out.println(Value.buffer);
				out.flush();
				out.close();
			}  catch(FileNotFoundException e1){
				System.out.println("Oops : FileNotFoundException");
			}
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
		treeP = new JPanel();
		treeP.add(tree);
		spTree = new JScrollPane(treeP);
		spTree.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		spTree.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		left.add(spTree);
		left.add(useP);		
		left.revalidate();
	}

	public void valueChanged(TreeSelectionEvent e) {
		String node=e.getNewLeadSelectionPath().getLastPathComponent().toString();
		ci=new ClassInfo();
		
		if(node.contains(":")){		//data
			useP.setText("");
			right.removeAll();
			String[] currentD=node.split(":");
			MethodUsingMember mum=new MethodUsingMember(currentD[0]);
			String colName[] = {"Name","Method"};
			DefaultTableModel model=new DefaultTableModel(colName,0);
			table = new JTable(model);
			String row[] = new String[2];
			row[0] = currentD[0];
			StringBuffer memberBuffer = new StringBuffer();
			for(int i=0; i<mum.getMembersUsing().size(); i++) {
				memberBuffer.append(mum.getMembersUsing().get(i) + ", ");
			}
			memberBuffer.deleteCharAt(memberBuffer.length()-2);
			row[1] = memberBuffer.toString();
			model.addRow(row);
			spTable = new JScrollPane(table);
			spTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			spTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			right.add(spTable);
			right.revalidate();
			
		}
		else if(node.contains("(")){	//method
			useP.setText("");
			right.removeAll();
			if(beforenode!=null&&oriMethod!=null && changedText!=null && !oriMethod.equals(changedText)){
				int flag =Value.buffer.indexOf("::"+beforenode);
				int flag2 =Value.buffer.indexOf("{",flag);
				int start = Value.buffer.indexOf(oriMethod,flag2);
				int end =start+oriMethod.length()-1;
				changeMethod(start,end,changedText);
			}
			beforenode=node;
			String[] currentM=node.split("\\(");
			MethodClass mc=new MethodClass(currentM[0]);
			oriMethod=mc.getMethod().toString();
			text.setText(oriMethod);
			MethodMember mm=new MethodMember(currentM[0]);
			useP.setText("");
			for(int i=0; i<mm.getMembers().size(); i++)
				useP.append(mm.getMembers().get(i) + "\n");
			text.getDocument().addDocumentListener(new DocListener());
			spText = new JScrollPane(text);
			spText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			spText.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			right.add(spText);
			right.revalidate();
		}
		else if(node.equals(ci.className)){	//class name
			useP.setText("");
			right.removeAll();
			String colName[] = {"Name","Type","Access"};
			DefaultTableModel model=new DefaultTableModel(colName,0);
			table = new JTable(model);
			String row[] = new String[3];
			for(int i=0;i<ci.getMName().size();i++){
				row[0] = ci.getMName().get(i);
				row[1] = ci.getMType().get(i);
				row[2] = ci.getMAccess().get(i);
				model.addRow(row);
			}
			for(int i=0;i<ci.getDName().size();i++){
				row[0] = ci.getDName().get(i);
				row[1] = ci.getDType().get(i);
				row[2] = ci.getDAccess().get(i);
				model.addRow(row);
			}
			spTable = new JScrollPane(table);
			spTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			spTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			right.add(spTable);
			right.revalidate();
		}
	}

	private void changeMethod(int start, int end, String chg) {
		// TODO Auto-generated method stub
//		System.out.println(chg);
		Value.buffer=Value.buffer.replace(start, end, chg);
	}
	
	private class DocListener implements DocumentListener{
		public void insertUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			changedText=text.getText();
		}
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			changedText=text.getText();
		}
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			changedText=text.getText();
		}
	}
}
public class ClassViewer {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ViewFrame();
	}
}
