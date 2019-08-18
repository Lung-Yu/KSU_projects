import static java.lang.System.out;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Area;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.text.html.Option;

import org.omg.CORBA.PRIVATE_MEMBER;





public class Management extends JFrame{  //繼承 JFrame的類別
  /**
   * @param args這裡為管理者類別
   */
  /*** Fields ***/
  private JMenuBar menuBar;	//宣告menuBar
  
  private JMenu fileMenu;	//宣告fileMenu
  private JMenu operateMenu;//宣告operateMenu
  private JMenu displayMenu;//宣告displayMenu
  private JMenu aboutMenu;	//宣告aboutMenu
  
  private JMenuItem menuOpen;	//宣告menuOpen
  private JMenuItem menuSave;	//宣告menuSave
  private JMenuItem menuSaveAs;	//宣告menuSaveAs
  private JMenuItem menuClose;	//宣告menuClose
  
  private JMenuItem menuInsert;	//宣告menuInsert
  private JMenuItem menuQuiry;	//宣告menuQuiry
  private JMenuItem menuDelete;	//宣告menuDelete
  private JMenuItem menuChange;	//宣告menuChange
  
  private JMenuItem menuShow;	//宣告menuShow
  private JMenuItem menuList;	//宣告menuList
 
  private JMenuItem menuAbout;	//宣告menuAbout
  
  private JTextArea textArea;	//宣告textArea
  private JLabel stateBar;	//宣告stateBar
  
  private Container contenPane;
  
  private JFileChooser fileChooser;
  
  public Management() {
  
	// TODO Auto-generated constructor stub
	super("pyschology academic");	//呼叫富類別建構子順便定義title
	initComponents();	//初始元件外觀
	initEventListeners();	//初始元件傾聽器
  }
  
  private void initComponents() {
  
	// TODO Auto-generated method stub
	setSize(400, 300); //設定視窗大小
	

	//set選單
	fileMenu = new JMenu("檔案");
	operateMenu = new JMenu("操作");
	displayMenu = new JMenu("顯示");
	aboutMenu = new JMenu("關於");
	
	//set選單項目
	menuOpen = new JMenuItem("開啟就資料庫");
	menuSave = new JMenuItem("儲存資料庫");
	menuSaveAs = new JMenuItem("另存資料庫");
	menuClose = new JMenuItem("離開");
	
	menuInsert = new JMenuItem("新增");
	menuQuiry = new JMenuItem("查詢");
	menuDelete = new JMenuItem("刪除");
	menuChange = new JMenuItem("修改");
	
	menuShow = new JMenuItem("單筆資料顯示");
	menuList = new JMenuItem("顯示全部資料");
	
	menuAbout = new JMenuItem("關於");
	
	menuBar = new JMenuBar();	//建立選單列
	
	stateBar = new JLabel("未修改");
	stateBar.setHorizontalAlignment(SwingConstants.CENTER);	//設定文字顯示在中間
	stateBar.setBorder(BorderFactory.createEtchedBorder());
	
	
	textArea = new JTextArea();
	textArea.setFont(new Font("細明體", Font.PLAIN, 12));
	textArea.setLineWrap(true);
	JScrollPane panel = new JScrollPane(textArea,
		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,	//設定垂直拉軸有需要時就出現可以拉
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);	//設定水平軸不出現
	
	contenPane = getContentPane();
	
	contenPane.add(stateBar,BorderLayout.SOUTH);	//設定bar 在下方

	contenPane.add(panel,BorderLayout.CENTER);
	
	//add選單項目
	fileMenu.add(menuOpen);
	fileMenu.addSeparator();	//分隔線
	fileMenu.add(menuSave);
	fileMenu.add(menuSaveAs);
	fileMenu.addSeparator();	//分隔線
	fileMenu.add(menuClose);
	
	operateMenu.add(menuInsert);
	operateMenu.add(menuQuiry);
	operateMenu.add(menuDelete);
	operateMenu.add(menuChange);
	
	displayMenu.add(menuShow);
	displayMenu.add(menuList);
	
	aboutMenu.add(menuAbout);
	
	//add選單列
	menuBar.add(fileMenu);	//將檔案的選單列加入選單列
	menuBar.add(operateMenu);	//將操作的選單加入選單列
	menuBar.add(displayMenu);	//將顯示的選單加入選單列
	menuBar.add(aboutMenu);	//將關於的選單加入選單列
	
	// set選單列
	setJMenuBar(menuBar);	//使用JFrame的setMenuBar()設置選單列
	
	fileChooser = new JFileChooser();
	
	
  }
  
  private void initEventListeners() {

	// TODO Auto-generated method stub
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //按下右上X按鈕的預設行為
	menuOpen.setAccelerator(	//選單項目中的開啟舊資料庫設定快捷鍵
		KeyStroke.getKeyStroke(KeyEvent.VK_O,	//快捷鍵為ctrl+o
			InputEvent.CTRL_MASK));
	menuSave.setAccelerator(	//選單項目中的儲存新資料庫設定快捷鍵
		KeyStroke.getKeyStroke(KeyEvent.VK_S,	//設定快捷鍵為ctrl+s
			InputEvent.CTRL_DOWN_MASK));
	menuClose.setAccelerator(	//選單項目中的離開設定快捷鍵
		KeyStroke.getKeyStroke(KeyEvent.VK_Q,	//設定快捷鍵為ctrl+s
			InputEvent.CTRL_DOWN_MASK));
	
	addWindowListener(new WindowAdapter() {	//按下是窗關閉按鈕事件處理
		public void windowCloding(WindowEvent e){
		  closeFile();
		}
	  });
	
	menuOpen.addActionListener(new ActionListener() {	//選單  - 開啟舊資料庫事件傾聽
	  
	  @Override
	  public void actionPerformed(ActionEvent e) {
	    
		// TODO Auto-generated method stub
	  	openFile();
	  }
	});
	  
	menuSave.addActionListener(new ActionListener() {	//選單  - 儲存資料庫事件傾聽
	    
	  @Override
	  public void actionPerformed(ActionEvent e) {
	    
	  	// TODO Auto-generated method stub
	  	saveFile();
	  }
	});
	  
	  
	menuSaveAs.addActionListener(new ActionListener() {	//選單  - 另存資料庫事件傾聽
	    
	  @Override
	  public void actionPerformed(ActionEvent e) {
	    
		// TODO Auto-generated method stub
	  	saveAsFile();
	  }
	});
	  
	menuClose.addActionListener(new ActionListener() {	//選單  - 離開事件傾聽	
	    
	  @Override
	  public void actionPerformed(ActionEvent e) {
	    
	  	// TODO Auto-generated method stub
	  	closeFile();
	  }
	});
	
	menuInsert.addActionListener(new ActionListener() {	//選單  - 新增的事件傾聽
	  
	  @Override
	  public void actionPerformed(ActionEvent e) {
	  
		// TODO Auto-generated method stub
		insertOperate();
	  }
	});

	menuQuiry.addActionListener(new ActionListener() {	//選單 - 查詢的事件傾聽
	  
	  @Override
	  public void actionPerformed(ActionEvent e) {
	  
		// TODO Auto-generated method stub
		quiryOperate();
	  }
	});
	
	menuDelete.addActionListener(new ActionListener() {	//選單 - 刪除的事件傾聽
	  
	  @Override
	  public void actionPerformed(ActionEvent e) {
	  
		// TODO Auto-generated method stub
		deleteOperate();
	  }
	});
	
	menuChange.addActionListener(new ActionListener() {	//選單 - 修改的事件傾聽
	  
	  @Override
	  public void actionPerformed(ActionEvent e) {
	  
		// TODO Auto-generated method stub
		changeOperate();
	  }
	});
	
	menuShow.addActionListener(new ActionListener() {	//選單 - 單筆顯示的事件傾聽
	  
	  @Override
	  public void actionPerformed(ActionEvent e) {
	  
		// TODO Auto-generated method stub
		showDisplay();
	  }
	});
	
	menuList.addActionListener(new ActionListener() {	//選單 - 顯示全部的事件傾聽
	  
	  @Override
	  public void actionPerformed(ActionEvent e) {
	  
		// TODO Auto-generated method stub
		listDisplay();
	  }
	});
	
	menuAbout.addActionListener(new ActionListener() {
	  
	  @Override
	  public void actionPerformed(ActionEvent e) {
		String msg = String.format("%25s\n來自於java程式設計實習(二)",
			"pyschology database"); //編輯顯示訊息
		String title = String.format("關於 pyshology database");	//編輯顯示title
		// TODO Auto-generated method stub
		JOptionPane.showOptionDialog(null,	//設定彈出視窗
			msg,title, JOptionPane.DEFAULT_OPTION,	//設定彈出視窗的title和msg(內容)
			JOptionPane.INFORMATION_MESSAGE,
			null, null, null);
	  }
	});
  }
  static Academic[] pyschologyAcademic = new Academic[100];
  public static void main(String[] args) {
	System.out.print(Academic.getAmount());
	pyschologyAcademic[Academic.getAmount()]=new Academic("John William Atkinson"
		, 1923, "2.04", 2003, "2.04","研究成就動機","美國");
	// TODO Auto-generated method stub
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
	  
	  @Override
	  public void run() {
	  
		// TODO Auto-generated method stub
		new Management().setVisible(true);
	  }
	});	 
	System.out.print(Academic.getAmount());
  }
  
  private static String show(Academic data) {
	//利用String中的format方法整理字串,並將整個字串回傳
	return String.format("姓名 : %-30s\n國籍 : %-12s\n生卒年 : %d %4.2f  ~ %d %4.2f\n貢獻 : %s\n",
		data.getName(),data.getNatiomality(),data.getBirth(),data.getBirthMathDay(),
		data.getDeath(),data.getBirthMathDay(),data.getDedication()
		);
  }
  
  private void openFile(){
	if(stateBar.getText().equals("未修改"))
	  showFileDialog();
	else{
	  int option = JOptionPane.showConfirmDialog(null,"檔案已修改，是否儲存?", "儲存檔案?",
		  JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null);
	  switch (option) {
		case JOptionPane.YES_OPTION:	saveFile();
		  
		  break;
		
		case JOptionPane.NO_OPTION:	showFileDialog();
		  break;
	  }
	}
  }
  private void saveFile() {
	Path path = Paths.get(getTitle());
	try{
	  if (Files.notExists(path)) {
		saveAsFile();
	  } else {
		//儲存檔案
		  try {
			BufferedWriter writer = Files.newBufferedWriter(Paths.get(path.toString()),
				Charset.forName(System.getProperty("fuke.encoding")));
			writer.write(textArea.getText());
			stateBar.setText("未修改");
		  } catch(IOException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null,e.toString(), "寫入檔案失敗",
				JOptionPane.ERROR_MESSAGE);
		  }
		stateBar.setText("未修改");
	  }
	}catch(Throwable e){
	  JOptionPane.showMessageDialog(null, e.toString(),"寫入檔案失敗",JOptionPane.ERROR_MESSAGE);
	}
  }
  private  void saveAsFile() {
	int option = fileChooser.showDialog(null, null);
	if(option==JFileChooser.APPROVE_OPTION){
	  
	  setTitle(fileChooser.getSelectedFile().toString());
	  try {
		Files.createFile(Paths.get(fileChooser.getSelectedFile().toString()));
	  } catch(IOException e) {
		// TODO: handle exception
		Logger.getLogger(Management.class.getName()).log(Level.SEVERE,null,e);
	  }
	}
  }
  private void closeFile() {
	if (stateBar.getText().equals("未修改")) {
	  dispose();
	} else {
	  int option = JOptionPane.showConfirmDialog(null, 
		  "檔案已修改，是否儲存?","儲存檔案?",JOptionPane.YES_NO_CANCEL_OPTION,
		  JOptionPane.WARNING_MESSAGE,null);
	  switch (option) {
		case JOptionPane.YES_OPTION:
		  saveFile();
		  break;
		
		case JOptionPane.NO_OPTION:
		  dispose();
		  break;
	  }
	}
  }
  private void insertOperate(){
	contenPane.removeAll();
	/***宣告使用變數***/
	JLabel labName = new JLabel("姓   名  : ");
	final JTextField texName = new JTextField(22);	//建構且直接給寬度
	
	JLabel labNationality = new JLabel("國   籍  : ");
	final JTextField texNationality = new JTextField(22);
	
	JLabel labBrith = new JLabel(" 生年:");
	final JTextField texBrith = new JTextField(10);
	
	JLabel labBrithMathDay = new JLabel("月份");
	final JTextField texBrithMathDay = new JTextField(12);
	
	JLabel labDeath = new JLabel(" 卒年:");
	final JTextField texDeath = new JTextField(10);
	
	JLabel labDeathMathDay = new JLabel("月份  ");
	final JTextField texDeathMathDay = new JTextField(12);
	
	JLabel labDedication = new JLabel("貢   獻  : ");
	final JTextField texDedication = new JTextField(22);
	
	JButton btnDetermine= new JButton("確定");
	
	
	JPanel panel = new JPanel(new FlowLayout());	//宣告JPanel的容器
	
	

	labName.setFont(new Font("標楷體",Font.PLAIN,16));
	labNationality.setFont(new Font("標楷體",Font.PLAIN,16));
	labBrith.setFont(new Font("標楷體",Font.PLAIN,16));
	labBrithMathDay.setFont(new Font("標楷體",Font.PLAIN,16));
	labDeath.setFont(new Font("標楷體",Font.PLAIN,16));
	labBrithMathDay.setFont(new Font("標楷體",Font.PLAIN,16));
	labDedication.setFont(new Font("標楷體",Font.PLAIN,16));
	
	btnDetermine.addActionListener(new ActionListener() {
	  
	  @Override
	  public void actionPerformed(ActionEvent arg0) {
	  
		// TODO Auto-generated method stub
		String name = texName.getText();	//獲得使用者輸入的名稱
		String nationality = texNationality.getText();	//獲得使用者輸入的國籍
		String birth = texBrith.getText();	//獲得使用者輸入的生年
		String birthMathDay = texBrithMathDay.getText();	//獲得使用者輸入的出生月份
		String death = texDeath.getText();	//獲得使用者輸入的卒年
		String deathMathDay = texBrithMathDay.getText();	//獲得使用者輸入的卒月
		String dedication = texDedication.getText();	//獲得使用者輸入的貢獻
		
		int birthForData = Integer.parseInt(birth);
		int deathForData = Integer.parseInt(death);
		pyschologyAcademic[Academic.getAmount()] = new Academic(name, 
			birthForData, birthMathDay, deathForData, deathMathDay, dedication, nationality);
		JOptionPane.showMessageDialog(null, "新建完成");
	  }
	});
	
	panel.add(labName);
	panel.add(texName);
	panel.add(labNationality);
	panel.add(texNationality);

	panel.add(labDedication);
	panel.add(texDedication);
	panel.add(labBrith);
	panel.add(texBrith);
	panel.add(labBrithMathDay);
	panel.add(texBrithMathDay);
	panel.add(labDeath);
	panel.add(texDeath);
	panel.add(labDeathMathDay);
	panel.add(texDeathMathDay);
	panel.add(btnDetermine);
	contenPane.add(panel,BorderLayout.CENTER);
	
	
	
  }
  private void quiryOperate(){
	contenPane.removeAll();
	JLabel labQuiryName = new JLabel("請輸入預查詢名稱:");
	final JTextField texQuiryName = new JTextField(30);
	JButton btnDetermine= new JButton("確定");

	
	labQuiryName.setFont(new Font("標楷體",Font.PLAIN,16));
	
	final Panel panel = new Panel(new FlowLayout());
	
	panel.add(labQuiryName);
	panel.add(texQuiryName);
	panel.add(btnDetermine);
	contenPane.add(panel,BorderLayout.CENTER);
	
	btnDetermine.addActionListener(new ActionListener() {
	  
	  @Override
	  public void actionPerformed(ActionEvent arg0) {
	  
		// TODO Auto-generated method stub

		boolean flag=false;
		String name = texQuiryName.getText();
		panel.removeAll();
		panel.add(textArea,BorderLayout.CENTER);
		contenPane.add(panel);
		textArea.setText("112");
		for(int i=0;i<Academic.getAmount();i++){
		  if (pyschologyAcademic[i].equals(name)) {	//如果相同
			String msg = String.format("尋找到的資料為\n%s", show(pyschologyAcademic[i]));
			textArea.setText(msg);
			
			flag = true;	//有找到
		  }
		}
		if(!flag){
		  textArea.setText("找不到資料");
		}
	  }
	});
  }
  private void deleteOperate(){
	if(Academic.getAmount()>0){
	  pyschologyAcademic[Academic.getAmount()-1].delete();
	}else {
	  textArea.setText("無資料可刪");
	}
  }
  private void changeOperate(){
	contenPane.removeAll();
	contenPane.add(textArea);
	textArea.setText("");
	String msg;
	if (!(Academic.getAmount()>0)) {
	  textArea.setText("沒有資料");
	}
	for(int i=0;i<Academic.getAmount();i++){
	  msg = String.format("第%d筆資料:%s\n",i+1,pyschologyAcademic[i].getName()); 
	  textArea.append(msg);
	}
  }
  private void showDisplay(){
	
	contenPane.removeAll();
	textArea = new JTextArea();
	textArea.setFont(new Font("細明體", Font.PLAIN, 12));
	textArea.setLineWrap(true);
	JScrollPane panel = new JScrollPane(textArea,
		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,	//設定垂直拉軸有需要時就出現可以拉
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);	//設定水平軸不出現
	
	contenPane.add(textArea,BorderLayout.CENTER);
	if(Academic.getAmount()==0){
	  textArea.setText("沒有資料");
	}else {
	  String msg = String.format("顯示第%d筆資料\n%s",Academic.getAmount(),
		  show(pyschologyAcademic[Academic.getAmount()-1]));
	  textArea.setText(msg);
	}
	
  }
  private void listDisplay(){
	contenPane.removeAll();
	contenPane.add(textArea);
	textArea.setText("");	//清空畫面
	int i=0;
	JButton btnExit = new JButton("離開");
	JButton btnNext = new JButton("下一頁");
	JButton btnPrev = new JButton("上一頁");
	
	boolean exit=false;	
	if(!(Academic.getAmount()>0)){	//判斷是否有資料，如果沒有資料
	  textArea.setText("沒有資料");
	}else{	//如果有資料
	  do{
		do{
		  System.out.println(i);
		  textArea.append(show(pyschologyAcademic[i])+"\n");
		  i++;
		}while(((i<Academic.getAmount())&&((i%3)!=0)));
		if ((i<=3)&&(i==Academic.getAmount())){	//只有一頁
		  
		} else if((i<=3)&&(i!=Academic.getAmount())){	//位於第一頁
		  
		} else if ((i>3)&&(i!=Academic.getAmount())){	//位於中間頁
		  
		} else {	//最後一頁
		  
		}
		String msg=String.format("\n。－。－。－。－。－。－。－。－。－。－。－。－。－。－。－。\n");
		textArea.append(msg);
		exit=true;
	  }while(!exit);
	}
	

	
	
  }
  private void showFileDialog(){
	int option = fileChooser.showDialog(null,null);
//	int option=0;
	//使用者按下確認按鍵
	if(option == JFileChooser.APPROVE_OPTION){
	  try{
		setTitle(fileChooser.getSelectedFile().toString());
		textArea.setText("");
		stateBar.setText("未修改");
		String text = Readable(fileChooser.getSelectedFile().toString());
		textArea.setText(text);
	  }catch(Throwable e){
		JOptionPane.showMessageDialog(null,e.toString(),"開檔失敗",JOptionPane.ERROR_MESSAGE);
	  }
	}
  }
  private String Readable(String file){
	byte[] datas = null;
	try {
	  datas = Files.readAllBytes(Paths.get(file));
	} catch(IOException e) {
	  // TODO: handle exception
	  Logger.getLogger(Management.class.getName()).log(Level.SEVERE,null,e);
	}
	return new String(datas);
  }
  private void create(String file) {
	  
	// TODO Auto-generated method stub
	try {
	  Files.createFile(Paths.get(file));
	} catch(IOException e) {
	  // TODO: handle exception
	  Logger.getLogger(Management.class.getName()).log(Level.SEVERE,null,e);
	}
  }
  private void save(String file, String text) {
	  
	// TODO Auto-generated method stub
	try {
	  BufferedWriter writer = Files.newBufferedWriter(Paths.get(file),
		  Charset.forName(System.getProperty("file.encoding")));
	} catch(IOException e) {
	  // TODO: handle exception
	  Logger.getLogger(Management.class.getName()).log(Level.SEVERE,null,e);
	}
  }
  
}
