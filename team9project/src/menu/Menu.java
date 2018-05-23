package menu;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Menu implements ActionListener{
	//Menu class�������� �⺻ Ʋ�� �гΰ� ��ư��
	JFrame f = new JFrame("Comparing Text");
	JButton Compare = new JButton("Compare");
	JButton Merge = new JButton("Merge");
	JButton EXIT = new JButton("EXIT");
	
	JButton LeftLoad = new JButton("Load");
	JButton LeftEdit = new JButton("Edit");
	JButton LeftSave = new JButton("Save");
	JPanel LeftPanel = new JPanel();
	
	JButton RightLoad = new JButton("Load");
	JButton RightEdit = new JButton("Edit");
	JButton RightSave = new JButton("Save");
	JPanel RightPanel = new JPanel();
	
	
	public Menu(){
		f.setSize(900,600);//ȭ���� ũ�⸦ ����
		f.setLayout(new BorderLayout());
		
		//������ Compare & Merge �κ�
		JPanel menueastPanel = new JPanel();
		menueastPanel.setLayout(new GridLayout(3,1,4,20));
		f.add("East", menueastPanel);
		/*��ư�������ҷ��� ���⼭ ��������
		Compare.setBackground(Color.pink);
		Merge.setBackground(Color.pink);
		EXIT.setBackground(Color.pink);
		*/
		menueastPanel.add("East",Compare);
		menueastPanel.add("East",Merge);
		menueastPanel.add("East",EXIT);

		//�߾� ���� TextView �κ�
		LeftPanel.setLayout(new BorderLayout());
		JPanel LeftNorthPanel = new JPanel();
		LeftNorthPanel.setLayout(new GridLayout(1,3,4,4));
		LeftPanel.add("North", LeftNorthPanel);
		/*��ư�������ҷ��� ���⼭ ��������
		LeftLoad.setBackground(Color.pink);
		LeftEdit.setBackground(Color.pink);
		LeftSave.setBackground(Color.pink);
		*/
		LeftNorthPanel.add("North",LeftLoad);
		LeftNorthPanel.add("North",LeftEdit);
		LeftNorthPanel.add("North",LeftSave);
		
		//TextArea�� 50��, 50���� �����մϴ�.      
		JTextArea Lefttextfield = new JTextArea("", 50, 50); 
		LeftPanel.add("Center",new JScrollPane(Lefttextfield));

		
		//�߾� ������ TextView �κ�
		RightPanel.setLayout(new BorderLayout());
		JPanel RightNorthPanel = new JPanel();
		RightNorthPanel.setLayout(new GridLayout(1,3,4,4));
		RightPanel.add("North", RightNorthPanel);
		/*��ư�������ҷ��� ���⼭ ��������
		RightLoad.setBackground(Color.pink);
		RightEdit.setBackground(Color.pink);
		RightSave.setBackground(Color.pink);
		*/
		RightNorthPanel.add("North",RightLoad);
		RightNorthPanel.add("North",RightEdit);
		RightNorthPanel.add("North",RightSave);
		
		//TextArea�� 50��, 50���� �����մϴ�.      
		JTextArea Righttextfield = new JTextArea("", 50, 50); 
		RightPanel.add("Center",new JScrollPane(Righttextfield));

		
		//���� ������ Textview ���������� �߾ӿ� �гθ��� �߰� 
		JPanel CenterPanel = new JPanel();
		CenterPanel.setLayout(new GridLayout(1,2,4,4));
		CenterPanel.add("West",LeftPanel);
		CenterPanel.add("East",RightPanel);
		f.add("Center", CenterPanel);

		
		Toolkit tk = Toolkit.getDefaultToolkit(); //������ Toolkit��ü�� ��´�.
		Dimension screenSize = tk.getScreenSize();
		
		f.setLocation(screenSize.width/2 - 450, screenSize.height/2 - 300);
		f.addWindowListener(new EventHandler());
		f.setVisible(true);//������ Frame�� ȭ�鿡 ���̵��� �Ѵ�.
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == Merge){
			//Merge���� action�� ����ɰ͵� �����߰�
		}
		else if(e.getSource() == Compare){
			//Compare���� action�� ����ɰ͵� �����߰�
		}
		else if(e.getSource() == LeftLoad){
			//Load���� action�� ����ɰ͵� �����߰�
		}
		else if(e.getSource() == LeftEdit){
			//Edit���� action�� ����ɰ͵� �����߰�
		}
		else if(e.getSource() == LeftSave){
			//Save���� action�� ����ɰ͵� �����߰�
		}
		else if(e.getSource() == RightLoad){
			//Load���� action�� ����ɰ͵� �����߰�
		}
		else if(e.getSource() == RightEdit){
			//Edit���� action�� ����ɰ͵� �����߰�
		}
		else if(e.getSource() == RightSave){
			//Save���� action�� ����ɰ͵� �����߰�
		}
		else if(e.getSource() == EXIT){
			f.setVisible(false);
			f.dispose();
			System.exit(0);
		}
	}
	
	
}



class EventHandler implements WindowListener
{
	public void windowOpened(WindowEvent e){}
	public void windowClosing(WindowEvent e){
		e.getWindow().setVisible(false);
		e.getWindow().dispose();
		System.exit(0);
	}
	public void windowClosed(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowActivated(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
}
