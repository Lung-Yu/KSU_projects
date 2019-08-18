import java.io.ObjectInputStream.GetField;
import java.util.Scanner;
import java.util.Set;


import static java.lang.System.out;
import static java.lang.System.in;
public class Academic{
	enum Nationality{
		Austria,
		America,
		Russia,
		Britain,
		France,
		Germany,
		Canada,
		Switzerland
	}
	private String name = null;
	private int birth = 0,death = 0;
	private double birthMathDay = 0.0,deathMathDay = 0.0;
	private String dedication = null;
	private Nationality nationality = null;
	private static int amount = 0;
	Scanner scanner = new Scanner(in);
	Academic(){
		amount++;
	}
	Academic(String name){
	  this();
		while(name.length() > 30){
			out.println("資料長度過長\n起重新輸入");
			name = scanner.nextLine();
		}
		this.name = name;
	}
	Academic(String name,String dedication){
		this(name);
		while(dedication.length()>60){
			out.println("資料過長顯示可能會有問題");
			out.println("是否重新輸入?[yes/no]");
			String answer = scanner.nextLine();
			if(answer.equals("yes")){
				dedication = scanner.nextLine();
			}else{
				break;
			}
		}
		this.dedication = dedication;
	}
	Academic(String name,int birth,String birthMathDay,
			int death,String dearthMathDay){
		this(name);
		
		while(birth<0){//出生年代不為負數
		  out.println("出生年代不為負數");
		  birth = scanner.nextInt();
		}
		this.birth = birth;
		while(!isMathDay(true, this.birth, birthMathDay)){
		  out.println("出生月份與日期不合理");
		  birthMathDay=scanner.nextLine();
		}
		while(death<this.birth){
		  out.println("死亡比出生早請重新輸入");
		  scanner.nextDouble();
		}
		while(death<=birth) {	//死亡年代不合理
		  out.println("死亡年代不合理");
		  death=scanner.nextInt();
		}
		this.death=death;
		while(!isMathDay(false, this.death, dearthMathDay)) {
		  out.println("死亡月份與日期不合理");
		  dearthMathDay = scanner.nextLine();
		}
	}
	Academic(String name,int birth,String brithMathDay,
			int death,String deathMathDay,String dedication){
		this(name,birth,brithMathDay,death,deathMathDay);
		while(dedication.length()>60){
			out.println("資料過長顯示可能會有問題");
			out.println("是否重新輸入?[yes/no]");
			String answer = scanner.nextLine();
			if(answer.equals("yes")){
				dedication = scanner.nextLine();
			}else{
				break;
			}
		}
		this.dedication = dedication;
		
	}
	Academic(String name,int birth,String birthMathDay,
			int death,String deathMathDay,String dedication,String Nationality){
		this(name, birth, birthMathDay, death, deathMathDay, dedication);
		setNationality(isNationality(Nationality));
	}
	public static String isNationality(String index){
			while(index.equals("Austria")&&
					index.equals("America")&&
					index.equals("Russia")&&
					index.equals("Britain")&&
					index.equals("France")&&
					index.equals("Germany")&&
					index.equals("Canada")&&
					index.equals("Switzerland")	){
						out.println("尋無此地  : "+index);
						Scanner scanner = new Scanner(System.in);
						index = scanner.nextLine();
				}
			if(index.equals("Austria")){
				return "Austria";
			}else if (index.equals("America")) {
				return "America";
			}else if (index.equals("Russia")){
				return "Russia";
			}else if(index.equals("Britain")){
				return "Britain";
			}else if(index.equals("France")){
				return "France";
			}else if(index.equals("Germany")){
				return "Germany";
			}else if(index.equals("Canada")){
				return "Canada";
			}else if (index.equals("Switzerland")) {
				return "Switzerland";
			}
			return "Other";
	}
	public  void setNationality(String index){
		switch (index) {
		case "Austria":	this.nationality = nationality.Austria;
			break;
		case "America":	this.nationality = nationality.America;
			break;
		case "Russia":	this.nationality = nationality.Russia;
			break;		
		case "Britain":	this.nationality = nationality.Britain;
			break;
		case "France":	this.nationality = nationality.France;
			break;		
		case "Germany":	this.nationality = nationality.Germany;
			break;
		case "Canada":	this.nationality = nationality.Canada;
			break;		
		case "Switzerland":	this.nationality = nationality.Switzerland;
			break;
		default:	this.nationality = null;
			break;
		}
	}
	public void SetName(String name){
		while(name.length() > 30){
			out.println("資料長度過長\n起重新輸入");
			name = scanner.nextLine();
		}
		this.name = name;
	}
	public void setBirthDay(int birth,String birthMathDay){
	  while(birth<0){//出生年代不為負數
		out.println("出生年代不為負數");
		birth = scanner.nextInt();
	  }
	  this.birth = birth;
	  while(isMathDay(true, this.birth, birthMathDay)){
		out.println("出生月份與日期不合理");
		birthMathDay=scanner.nextLine();
	  }
	}
	public void setDeathday(int death,String deathMathDay){
		while(death<this.birth){
		  out.println("死亡比出生早請重新輸入");
		  scanner.nextDouble();
		}
		while(death<=birth) {	//死亡年代不合理
		  out.println("死亡年代不合理");
		  death=scanner.nextInt();
		}
		this.death=death;
		while(isMathDay(false, this.death, deathMathDay)) {
		  out.println("死亡月份與日期不合理");
		  deathMathDay = scanner.nextLine();
		}
	}
	public String getName(){
		return this.name;
	}
	public int getBirth(){
		return this.birth;
	}
	public double getBirthMathDay(){
		return this.birthMathDay;
	}
	public int getDeath(){
		return this.death;
	}
	public double getDeathMathDay(){
		return this.deathMathDay;
	}
	public String getDedication(){
		return this.dedication;
	}
	public  int getDateAmount(){
		return amount;
	}
	public static int getAmount(){
	  return amount;
	}
	public Nationality getNatiomality(){
		return this.nationality;
	}
	public void delete(){
		this.name = null;
		this.birth = 0;
		this.death = 0;
		this.birthMathDay = 0.0;
		this.deathMathDay = 0.0;
		this.dedication=null;
		this.nationality = null;
		amount--;
	}

	private boolean isMathDay(boolean start,int year,String mathDay){	//start=true為出生年
	  String temp[]=mathDay.split("\\.");	//以點作分割
	  String math = temp[0];
	  String day = temp[1];
	  
	  if(Integer.parseInt(math)>0&&Integer.parseInt(math)<13	//如果是1~12月之間
		  &&Integer.parseInt(day)>0){	//且日期不為負數
		if(math.equals(1)||math.equals(2)||math.equals(3)||math.equals(7)||math.equals(8)
			||math.equals(10)||math.equals(12)){	//如果是大月(31日)
		  if(Integer.parseInt(day)<32){
			if (start) {
			  this.birthMathDay = Double.parseDouble(math+"."+day);
			} else {
			  this.deathMathDay = Double.parseDouble(math+"."+day); 
			}
			return true;
		  }else {
			return false;
		  }
		}else if (math.equals(4)||math.equals(6)||math.equals(9)||math.equals(11)) {	//如果是小月(30日)
		  if (Integer.parseInt(day)<31) {
			if (start) {
			  this.birthMathDay = Double.parseDouble(math+"."+day);
			} else {
			  this.deathMathDay = Double.parseDouble(math+"."+day); 
			}
			return true;
		  }else {
			return false;
		  }
		}else {	//一定是2月
		  if(((year%4==0)&&(year%100!=0))||(year%400==0)){	//閏年
			if (Integer.parseInt(day)<30) {
				if (start) {
				  this.birthMathDay = Double.parseDouble(math+"."+day);
				} else {
				  this.deathMathDay = Double.parseDouble(math+"."+day); 
				}
			  return true;
			}else {
			  return false;
			}
		  }else {	//平年
			if (Integer.parseInt(day)<29) {
				if (start) {
				  this.birthMathDay = Double.parseDouble(math+"."+day);
				} else {
				  this.deathMathDay = Double.parseDouble(math+"."+day); 
				}
			  return true;
			}else {
			  return false;
			}
		  }
		}
	  }else {
		return false;
	  }
	}
}