package menu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;



public class Filecontroller implements ActionListener {

    private Fileview view;
    private Filemodel model; 
    
    // 밑에 두 변수 없애고 model에 있는 arraylist 쓰려했는데 에러나서 그대로 놔둠
    private ArrayList<String> leftTXT;  // 왼쪽 파일
    private ArrayList<String> rightTXT; // 오른쪽 파일
    
    
    public Filecontroller(Fileview view, Filemodel model){
        this.view = view;
        this.model = model;
        addListener();
    }
    
    private void addListener()
    {        
        view.Compare.addActionListener(this);
        view.LeftMerge.addActionListener(this);
        view.RightMerge.addActionListener(this);
        view.EXIT.addActionListener(this);    
        view.LeftLoad.addActionListener(this);
        view.LeftEdit.addActionListener(this);
        view.LeftSave.addActionListener(this);
        view.RightLoad.addActionListener(this);
        view.RightEdit.addActionListener(this);
        view.RightSave.addActionListener(this);    
    }
    
    public void actionPerformed(ActionEvent e){
      
        
        if(e.getSource() == view.LeftMerge){ // right to left
            //Merge관련 action시 실행될것들 내용추가
            String[] sychright = view.Righttextfield.getText().split("\r\n",100000);
            String[] sychleft = view.Lefttextfield.getText().split("\r\n",100000);
            ArrayList<String> left = new ArrayList<String>();
            ArrayList<String> right = new ArrayList<String>();
                
            for(int i = 0; i < sychright.length ; i++) {
                right.add(sychright[i]);
            }
            for(int i = 0; i < sychleft.length ; i++) {
                left.add(sychleft[i]);
            }
            for(int i = 0; i < model.getdiff().size(); i++) {
                if(right.get(model.getdiff().get(i)).equals("")) {
                        continue;
                }
                left.set(model.getdiff().get(i), right.get(model.getdiff().get(i)));
            }
            view.Lefttextfield.setText("");
            String lText = new String();
            for(int i = 0; i < left.size(); i++) { // 텍스트필드에 저장
                lText = lText + left.get(i) + "\r\n";
            }
            view.Lefttextfield.setText(lText);
        }
        else if(e.getSource() == view.RightMerge){ // left to right
            //Merge관련 action시 실행될것들 내용추가
           
            String[] sychright = view.Righttextfield.getText().split("\r\n",100000);
            String[] sychleft = view.Lefttextfield.getText().split("\r\n",100000);
            ArrayList<String> left = new ArrayList<String>();
            ArrayList<String> right = new ArrayList<String>();
                
            for(int i = 0; i < sychright.length ; i++) {
                right.add(sychright[i]);
            }
            for(int i = 0; i < sychleft.length ; i++) {
                left.add(sychleft[i]);
            }       
            
            
            for(int i = 0; i < model.getdiff().size(); i++) {
                if(!left.get(model.getdiff().get(i)).equals("")) {
                    right.set(model.getdiff().get(i), left.get(model.getdiff().get(i)));  
                }
                
            }
            view.Righttextfield.setText("");
            String rText = new String();
            for(int i = 0; i < right.size(); i++) { // 텍스트필드에 저장
                rText = rText + right.get(i) + "\r\n";
            }
            view.Righttextfield.setText(rText);
        }
        else if(e.getSource() == view.Compare){
            //미완성코드임 예시로 처음0에서4까지만변경하도록해보았음 둘중하나로 바꾸는듯?
            view.Lefttextfield.getStyledDocument().setCharacterAttributes(4, 8, view.attribute, false);
        //  Lefttextfield.getStyledDocument().setParagraphAttributes(4, 8, attribute, false);
            //Compare관련 action시 실행될것들 내용추가
            
            FileCompare compare = new FileCompare();
            int max = 10000000;
            
            // table 만들기 위해 textfield에서 문자열 가져오기
            String str_tmp = view.Lefttextfield.getText();
            
            str_tmp = "0" + "\r\n" + str_tmp;   // 테이블 특징상 문자열 앞에 0 처리
            String[] left = str_tmp.split("\r\n", max);
            
            
            str_tmp = view.Righttextfield.getText();
            str_tmp = "0" + "\r\n" + str_tmp;   // 테이블 특징상 문자열 앞에 0 처리
            String[] right = str_tmp.split("\r\n", max);
            
            // table 만들기
            int[][] table = compare.makeLCSTable(left, right);
            int lcsLength = compare.getLcsLength(); // LCS_Length 값을 저장
            
            // convert to array -> List
            // initialize
            leftTXT = new ArrayList<String>();  
            for(int i = 1 ; i < left.length ; i++) // copy array to List
                leftTXT.add(left[i]);
            
            // initialize
            rightTXT = new ArrayList<String>();
            for(int i = 1 ; i < right.length ; i++) // copy array to List
                rightTXT.add(right[i]);
            
            ArrayList<String> lcs = compare.makeLCSString(left.length, right.length, lcsLength, table, right);  // lcs 문자열을 구하는 함수가 또 필요하다.
            compare.synchronizingTextContent(leftTXT, rightTXT, lcs);
            
            
            String lText = new String();
            view.Lefttextfield.setText(""); // 텍스트필드 초기화 후 출력
            for(int i = 0; i < leftTXT.size(); i++) { // 텍스트필드에 출력
                if(i == leftTXT.size() - 1)
                    lText = lText + leftTXT.get(i);
                else
                    lText = lText + leftTXT.get(i) + "\r\n";
            }
            view.Lefttextfield.setText(lText);
            
            String rText = new String();
            view.Righttextfield.setText(""); // 텍스트필드 초기화 후 출력
            for(int i = 0; i < rightTXT.size(); i++) { // 텍스트필드에 출력
                if(i == rightTXT.size() - 1)
                    rText = rText + rightTXT.get(i);
                else
                    rText = rText + rightTXT.get(i) + "\r\n";
            }
            view.Righttextfield.setText(rText);
            
            // 이부분은 서로 다른 부분의 인덱스를 얻어준다
            model.setdiff(compare.getDifferentLineNumberIndex(leftTXT, rightTXT));
            
            view.Lefttextfield.getStyledDocument().setCharacterAttributes(4, 8, view.attribute, false);
            //  
        }
        else if(e.getSource() == view.LeftLoad){
            //Load관련 action시 실행될것들 내용추가
            FileLoader load = new FileLoader(); // 탐색기
            leftTXT = load.fileRead(); // 파일 가져오기
            model.setLeftFile(load.fileLoad);
            String lText = new String();
            view.Lefttextfield.setText(""); // 텍스트필드 초기화 후 출력
            for(int i = 0; i < leftTXT.size(); i++) { // 텍스트필드에 출력
                lText = lText + leftTXT.get(i);
                model.addLeft(lText);     
                lText = lText + "\r\n";
            }
            view.Lefttextfield.setText(lText);            
            view.LeftName.setText("파일명 : "+model.getLeftFile().getName());
        }
        else if(e.getSource() == view.LeftEdit){
            //Edit관련 action시 실행될것들 내용추가
            if(view.LeftEditonoff == false){
                view.LeftEdit.setText("EDIT ON");
                view.LeftEdit.setFont(new Font("고딕",Font.ITALIC,12));
                view.LeftEditonoff = true;
                
            }
            else if(view.LeftEditonoff = true){
                view.LeftEdit.setText("Edit");
                view.LeftEdit.setFont(new Font("Dialog",Font.BOLD,12));
                view.LeftEditonoff = false;
                String[] sychleft = view.Lefttextfield.getText().split("\r\n",100000);
                ArrayList<String> left = new ArrayList<String>();
                for(int i = 0; i < sychleft.length ; i++) {
                    left.add(sychleft[i]);
                }
                model.setLeft(left);
            }
            view.Lefttextfield.setEditable(view.LeftEditonoff);
        }
        else if(e.getSource() == view.LeftSave){
            FileSave filesave = new FileSave(model.getLeftFile(),view.Lefttextfield);
            model.setLeftFile(filesave.savefile);
            //Save관련 action시 실행될것들 내용추가
        }
        else if(e.getSource() == view.RightLoad){
            //Load관련 action시 실행될것들 내용추가
            FileLoader load = new FileLoader(); // 탐색기
            rightTXT = load.fileRead(); // 파일 가져오기
            model.setRightFile(load.fileLoad);
            String rText = new String();
            view.Righttextfield.setText("");
            for(int i = 0; i < rightTXT.size(); i++) { // 텍스트필드에 저장
                rText = rText + rightTXT.get(i);
                model.addright(rText);
                rText = rText + "\r\n";
            }
            view.Righttextfield.setText(rText);
            view.RightName.setText("파일명 : "+model.getRightFile().getName());
        }
        else if(e.getSource() == view.RightEdit){
            //Edit관련 action시 실행될것들 내용추가
            if(view.RightEditonoff == false){
                view.RightEdit.setText("EDIT ON");
                view.RightEdit.setFont(new Font("고딕",Font.ITALIC,12));
                view.RightEditonoff = true;
            }
            else if(view.RightEditonoff = true){
                view.RightEdit.setText("Edit");
                view.RightEdit.setFont(new Font("Dialog",Font.BOLD,12));
                view.RightEditonoff = false;
                String[] sychright = view.Righttextfield.getText().split("\r\n",100000);
                ArrayList<String> right = new ArrayList<String>();
                for(int i = 0; i < sychright.length ; i++) {
                    right.add(sychright[i]);
                }                
                model.setRight(right);
            }
            view.Righttextfield.setEditable(view.RightEditonoff);
        }
        else if(e.getSource() == view.RightSave){
            FileSave filesave = new FileSave(model.getRightFile(),view.Righttextfield);
            model.setRightFile(filesave.savefile);
            //Save관련 action시 실행될것들 내용추가
        }
        else if(e.getSource() == view.EXIT){
            view.f.setVisible(false);
            view.f.dispose();
            System.exit(0);
        }
    }
}


