/********4*0*1*0*E*1*0*5**蔡**龍**佑****心**理**學**家**資**料**庫********/
 
/*函市庫宣告*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "ture_c.h"  //呼叫自己定義的結構 
/*設定全域變數*/
FILE *fp; //檔案的指標 
STU stu[99];
int num=0;

/*宣告副函式*/ 
char User_Interface(const int); //選單 int=使用者錯誤次數,char回傳使用者選擇之指令 
void opendata(const char);    //開啟並讀取psychologydata.txt的資料,char:'r'=rb+,'w'=wb+
void closedata(void);   //關閉並儲存資料至psychologydata.txt
void Insert_data(void);      //新增 Insret
void Inquiry_data(void);     //查詢 inquury 
void delete_data(void);      //刪除 delete
void revise_data(void);      //更改 revise
void data_amount(void);      //資料數量 amount  
void quit(void);             //離開 quit
int Display_data(const int);//顯示,int=0為主程式顯示使用,
//int=1則為更新之顯示功能,int=2則為刪除,int=3則為查詢之列表查法 
void show(STU data,int,int);     //顯示單筆資料,若int = -2 則單純顯示該資料 
int clear(int);              //clear data
char Inname(int,const int);  //Insert名稱int=位置,int=0則為Insert之功能 
char InBD(int,const int);    //Insert Birth and Death  => Insert生卒年
char InNationality(int,const int); //Insert國籍int=位置,int=0則為Insert之功能 
char Inschool(int,const int);//Insert學派int=位置,int=0則為Insert之功能 
char Instatus(int,const int);//Insert身份int=位置,int=0則為Insert之功能 
void keyin(void);            //鍵入式
void list(void);             //列舉式 
void sequence(void);         //排序
void change(int,int);            //將兩筆資料交換 
/*** main 主程式執行 ***/ 
int main(){
    char bee='\07'; //輸入錯誤時Bee一聲 
    int k=0,quit_m=0;//K計算錯誤次數,quit_m=1為離開,quit_m=0為錯誤而過多跳出 
    opendata('r'); //開啟.txt檔 =>用rb+ 
    closedata(); 
    do{
       sequence(); //排順序  ←每做一個動作都會排一次 
       system("cls");
       switch(User_Interface(k)){
          case 'I':  //新增資料_OK 
          case 'i':  k=0;
                     Insert_data(); 
                break;
          case 'D':  //刪除資料_OK 
          case 'd':  k=0;
                     delete_data();
               break;
          case 'Q':  //查詢資料_OK 
          case 'q':  k=0;
                     Inquiry_data();
               break;
          case 'M':  //顯示資料_ok
          case 'm':  k=0;
                     Display_data(0);
               break;
          case 'E':  //離開_OK 
          case 'e':  quit();
                     quit_m=1;
               break;
          case 'K':  //顯示資料數量_OK 
          case 'k':  k=0;
                     data_amount();
               break;
          case 'R' : //更新_OK 
          case 'r' : k=0;
         	         revise_data(); 
               break;
          default :system("cls");
                   printf("您輸入錯誤的指令!請從新輸入\n");
                   printf("%c",bee); 
                   system("pause");
                   k++;
       }    
    }while(k<5&&quit_m==0);
    system("cls");
    if(quit==0){
       printf("輸入錯誤超過5次");
       system("pause");
    }
    opendata('w'); //開啟.txt檔 =>用w+ 
    closedata(); //關閉 .txt檔 
    return 0;
    }
    
/***User Interface 使用者介面***/
char User_Interface(const int a){
       char ch;
       system("cls");
           printf("∴°﹒☆°．﹒°﹒°•°∴°•°○。+Ｏ•°o•°o．﹒•°∴°．﹒•°");
           printf("\n◢█████████████████████◣");
           printf("\n　　　█　　　　　 ☆請選擇您欲工作的項目☆   　　█");
           printf("\n　█◣█◢█　　          (I)  加入新資料       　█");
           printf("\n◥ˍ歡█迎ˍ◤　　　　　　(D)  刪除資料   　　　　█");
           printf("\n◢█◢█◣█◣　　　　　　(Q)  查詢資料 　　　　　█");
           printf("\n　　　█　　　　　　　　　(M)  顯示資料     　　　█");
           printf("\n　　　█　　　　　　　　　(R)  更改資料           █");
           printf("\n　　　█　　　　　　　　  (K)  顯示資料數目   　　█");
           printf("\n　　　█　◢█◣◢█◣　　(E)   離   開       　　█　");
           printf("\n　　　█　█　●●　█　☆　　☆　　☆　　☆　　　█");
           printf("\n　　　█◢██◤◥██◣　　　　　　　　　　　█◣█◢█");
           if(a==0)
           printf("\n　　　██○████○█                    ◥ˍ使█用ˍ◤");
           else
           printf("\n　　　██○████○█     輸入錯誤%d次    ◥ˍ使█用ˍ◤",a);
           printf("\n　　　█◥██◣◢██◤　　　　　　　　　　◢█◢█◣█◣");
           printf("\n　　　█◢██████◣　　☆　　☆　　☆　　　　█");
           printf("\n　　　◥█████████████\n");
           printf("∴°﹒☆°．﹒°﹒°•°∴°•°○。+Ｏ•°o•°o．﹒•°∴°．﹒•°\n\n");
        printf("\t請  輸  入  : \t");
        scanf("%c",&ch);
        fflush(stdin); // 清理 儲存位置 
        return ch;
}

/***Insert data插入(資料)***/
void Insert_data(void){
    char yorn;
     system("cls");
     num++;        //num++ 將新的空間空出來
  if(num>=99){     //判斷資料量是否已滿 
     printf("資料庫已滿\n");
  }else{
        Inname(num,0); // num,int=0則為Insert之功能 
        InBD(num,0);   //Insert Birth and Death  => 插入生卒年
        InNationality(num,0);
        Inschool(num,0);
        Instatus(num,0);
        /*確認剛剛輸入的資料*/
       system("cls"); //清除畫面 
       printf("請確認輸入資料是否無誤\n");
       show(stu[num-1],-2,-2);
       printf("是否存入?(Y/N) : \n");
       printf("�����������龤嘵��������鱭n");
       printf("　　　　　╭�屣﹛@　　　　　　　　 ◢\n");
       printf("▁╭▅▆▇□□█▇▆▅▄▃▂▁(╳)█╮\n");
       printf("　╰�灨d__▁▁▁▁▁∠�������灨v▔▔　\n");
       printf("　　　　�顢 　 �顢\n");
       do{              // do...while 最少執行一次 
          scanf("%c",&yorn);
          fflush(stdin); // 清理 儲存位置
          if((yorn=='y')||(yorn=='Y')) //yes 將資料儲存
                 printf("已將資料儲存,謝謝您使用\n");
          else if((yorn=='n')||(yorn=='N')){ //no 將資料消除 
                printf("此筆資料將不會被存入\n");
                printf("謝謝使用\n");
                num--;  //將剛剛新增的資料空間歸還,並且不允予顯示 
          }else{
                yorn = 'r';   //令判斷 yorn ='r' 使迴圈繼續執行  
                printf("輸入錯誤\n"); 
                printf("請輸入 (Y/N) : ");
          } 
       }while(yorn=='r'); //yorn == 'r' 為執行條件
 }
 system("pause");
}
/*list 列表式查詢*/
void list(void){
     int n=0,k,temp;
     char ch,ch2='\0'; //ch2 設定無相關值為初始值,以免迴圈錯誤 
          do{
             ch2='\0'; //每次都要將值給清空否則執行過一次就不會再判斷儲存 
             system("cls");
             do{ 
                 show(stu[n],n%7,-1);
                 n++;
             }while(n<num&&(n%7!=0));
             printf("\t\t\t輸入欲查看編號\n");
             printf("\t上一頁請輸入\"p\"下一頁請輸入\"n\"離開請輸入\"q\"\n");
             scanf("%c",&ch);
             fflush(stdin);
             if(ch=='n'||ch=='N'){  //下一頁
                if(n>=num)
                  if(n==num)
                    if(n%7!=0)
                      n-=n%7;
                    else
                      n-=7;
                  else
                    if(n%7!=0)
                      n-=n%7;
                     else
                       n-=7;  
                else
                  if(n%7!=0)
                    n -= n%7;
             }else if(ch=='p'||ch=='P'){ //上一頁
                if(n>0)
                  if(n<num)
                    if(n<=7)
                      n=0;
                    else
                      if(n%7!=0)
                        n -= (n%7)+7;
                      else
                        n-=14;
                  else
                    if(n<=7)
                      n=0;
                    else
                      if(n%7!=0)
                        n -= (n%7)+7;
                      else
                        n-=14;
                else
                  n=0;
             }else if(ch>='1'&&ch<='5'){ //判斷輸入是否為1~5的數字 
                k=ch -'0';  //將ASC||碼的數字換成一般數字 
                temp = n/7; //計算頁數 
                system("cls");
                printf("\t您查詢的資料為 ....\n\n");
                if(n<=7) //若只有一頁 
                show(stu[k-1],-2,-2); //將數字帶入當筆 - 顯示函式 
                else  //若不只一頁 
                show(stu[((temp-1)*7)+k-1],-2,-2);//頁數*一頁5筆+使用者選擇的項目數字 
                system("pause");
             }else if(ch=='Q'||ch=='q')
                   ch='q';
             else
                if(n<num)
                  if(n<=7)
                     n=0;
                  else
                    n -=n%7+7;
                else
                  if(n==num)
                    if(n>7)
                      if(n%7!=0)
                         n -= (n%7);
                      else
                         n-=7;
                    else
                      n=0;
                  else
                    if(n>7)
                       if(num%7!=0)
                         n = num - (num%7);
                       else
                         n = num-7;
                    else
                       n=0;
          }while(ch!='q');
}
/***data amount 資料量***/
void data_amount(){
     system("cls");
     printf("     ﹒☆°﹒☆．﹒☆°﹒☆．﹒☆°\n");
     printf("�歈嘵������裐歈��������裐歈��������裐歈��������綅n");
     if(num==0)  //沒有資料則顯示沒有 
        printf("�囓堳e∼∼�僓漈禤おw∼�僓漶膍S有∼�僓漈禤ヾ耤裺赨n");
     else        //否則顯示資料的數量                                     
        printf("�囓堳e∼∼�僓滮w經有∼�僓�%d∼筆∼�僓漈禤ヾ耤裺赨n",num);
     printf("�耤盟��龤盟潁耤盟��龤盟潁耤盟��龤盟潁耤盟��龤盟嫹n");
     printf("°﹒☆°．﹒•°∴°﹒°．﹒•°∴°.﹒•°∴°﹒☆\n\n");
     system("pause"); 
}

/***delete data 刪除資料***/
void delete_data(void){
     int n=0,k,temp;
     char ch,ch2='\0'; //ch2 設定無相關值為初始值,以免迴圈錯誤 
     system("cls"); 
     if(num==0){
       printf("\n\n\t資料庫內無資料\n\n");
       system("pause");
     }else{
          do{
            system("cls");
            ch2='\0'; //給定初始值 
             do{ 
                 show(stu[n],n%5,-1);
                 n++;
             }while(n<num&&(n%5!=0));
             printf("\t\t\t輸入欲刪除編號\n");
             printf("\t上一頁請輸入\"p\"下一頁請輸入\"n\"離開請輸入\"q\"\n");
             scanf("%c",&ch);
             fflush(stdin);
             if(ch=='n'||ch=='N'){  //下一頁
                if(n>=num)
                  if(n==num)
                    if(n%5!=0)
                      n-=n%5;
                    else
                      n-=5;
                  else
                    if(n%5!=0)
                      n-=n%5;
                     else
                       n-=5;  
                else
                  if(n%5!=0)
                    n -= n%5;
             }else if(ch=='p'||ch=='P'){ //上一頁
                if(n>0)
                  if(n<num)
                    if(n<=5)
                      n=0;
                    else
                      if(n%5!=0)
                        n -= (n%5)+5;
                      else
                        n-=10;
                  else
                    if(n<=5)
                      n=0;
                    else
                      if(n%5!=0)
                        n -= (n%5)+5;
                      else
                        n-=10;
                else
                  n=0;
             }else if(ch>='1'&&ch<='5'){ //判斷輸入是否為1~5的數字 
                k=ch -'0';  //將ASC||碼的數字換成一般數字 
                temp = n/5; //計算頁數 
                system("cls");
                printf("請問是否刪除此筆資料\n");
                if(n<=5) //若只有一頁 
                show(stu[k-1],-2,-2); //將數字帶入當筆 - 顯示函式 
                else  //若不只一頁 
                show(stu[((temp-1)*5)+k-1],-2,-2);//頁數*一頁5筆+使用者選擇的項目數字 
                while(ch2!='n'&&ch2!='N'&&ch2!='Y'&&ch2!='y'){    
                printf("\n請輸入(Y/N):\t");     
                scanf("%c",&ch2);
                fflush(stdin);
                   if((ch2=='y')||(ch2=='Y'))
                      if(n<=5)
                         n=clear(k-1);
                      else
                         n=clear(n-temp-1);
                   else if((ch2=='n')||(ch=='N'))
                      n=0;
                   else{ 
                      printf("您輸入錯誤\n");
                   } 
                }
             }else if(ch=='Q'||ch=='q')
                   ch='q';
             else
                if(n<num)
                  if(n<=5)
                     n=0;
                  else
                    n -=n%5+5;
                else
                  if(n==num)
                    if(n>5)
                      if(n%5!=0)
                         n -= (n%5);
                      else
                         n-=5;
                    else
                      n=0;
                  else
                    if(n>5)
                       if(num%5!=0)
                         n = num - (num%5);
                       else
                         n = num-5;
                    else
                       n=0;
          }while(ch!='q');
     } 
}

/***clear data 清除資料***/     
int clear(int n){
     int i;
     printf("刪除中 ....\n");
     /*將後面的資料往前補 ... */ 
     for(i=n;i<num;i++){
        strcpy(stu[i].name,stu[i+1].name);
        stu[i].score_start_year = stu[i+1].score_start_year;
        stu[i].score_start_MD = stu[i+1].score_start_MD;
        stu[i].score_end_year = stu[i+1].score_end_year;              
        stu[i].socre_end_MD = stu[i+1].socre_end_MD;
        strcpy(stu[i].Nationality,stu[i+1].Nationality);
        strcpy(stu[i].school,stu[i+1].school);
        strcpy(stu[i].status,stu[i+1].status);
     }
     /*將最後一筆資料刪除*/
     /*clear final name*/
     stu[num].name[0]='\0'; 
     /*clear final Nationality*/
     stu[num].Nationality[0]='\0';
     /*clear final school*/
     stu[num].school[0]='\0';
     /*clear final status*/
     stu[num].status[0]='\0';
     /*clear number*/
     stu[num].score_start_year = -1;
     stu[num].score_start_MD = -1;
     stu[num].score_end_year = -1;          
     stu[num].socre_end_MD = -1;
     num--;
     system("cls");
     return 0;
}

/***Display data 顯示資料***/	
int Display_data(int che){
    int i=0; //參數 
    int k;  //將使用者輸入的ASCII的數字轉換成一般數字並存入 
	char ctr;//輸入的指令 
	system("cls");
    /*顯示*/
    if(num==0){ //判斷資料庫內是否有資料 
	printf("\n\t目前資料庫中沒有資料\n\n\n");
	system("pause");
    }else{ 
	       do{
	          system("cls"); //清除畫面
	          if(che == 1)
                 printf("\t\t請請輸入欲更改資料之號碼?\n"); 
	          do{
                 if(che==1)
	               show(stu[i],i%3,-2);
	             else
	               show(stu[i],i,-2);
	             printf("\n");
	             i++;//  ↓顯示3筆,若不足3比則全部顯示
                 }while(((i<num)&&((i%3)!=0)));	
           /*判斷上一頁,下一頁,離開*/ 
	       if((i<=3)&&(i==num)){ //只有一頁 
              do{
                 printf("＊－。－。－。－。－。－。－。－。－－。－。－。－。－。－＊\n");
	             printf("\t\t\t離開請按'q'\n\t\t\t   ",i);
		         scanf("%c",&ctr);
		         fflush(stdin);
		         if(che == 1)
                    if(ctr<='3'&&ctr>='1'){
                       k = ctr-'0';
                       return k;
                    }
              }while(ctr!='q'&&ctr!='Q');
           }else if((i<=3)&&(i!=num)){ //位於第一頁 
              do{
                 printf("＊－。－。－。－。－。－。－。－。－－。－。－。－。－。－＊\n");
			     printf("\t\t下一頁請按\"n\"，離開請按\"q\"\n\t\t\t\t");
			     scanf("%c",&ctr);
			     fflush(stdin);
			     if(che == 1)
                    if(ctr<='3'&&ctr>='1'){
                       k=ctr-'0';
                       return k;
                    }
              }while((ctr!='q')&&(ctr!='n')&&(ctr!='Q')&&(ctr!='N'));
           }else if((i>3)&&(i!=num)){ //位於中間頁 
              do{
                 printf("＊－。－。－。－。－。－。－。－。－－。－。－。－。－。－＊\n");
			     printf("\t前一頁請按\"p\"，下一頁請按按\"n\"，離開請按\"q\"\n\t\t\t\t");
			     scanf("%c",&ctr);
			     fflush(stdin);
			     if(che == 1)
                    if(ctr<='3'&&ctr>='1'){
                       k = ctr -'0';
                       return i-4+k+1;
                    }
			     if(ctr=='p'||ctr=='P')
				    i-=((i-1)%3)+4;	
              }while((ctr!='q')&&(ctr!='p')&&(ctr!='n')&&(ctr!='Q')&&(ctr!='P')&&(ctr!='N'));
           }else{  //最後一頁 
              do{
                 printf("＊－。－。－。－。－。－。－。－。－－。－。－。－。－。－＊\n");
			     printf("\t\t前一頁請按\"p\"，離開請按\"q\"\n\t\t\t\t");
			     scanf("%c",&ctr);
			     fflush(stdin);
			     if(che == 1)
                    if(ctr<='3'&&ctr>='1'){
                       k = ctr -'0';
                       if(i%3==0)
                           return i-4+k+1;
                       else
                           return i-(i%3+1)+k+1;  
                    }
			     if(ctr=='p'||ctr=='P')
				    i-=((i-1)%3)+4;
              }while((ctr!='q')&&(ctr!='p')&&(ctr!='Q')&&(ctr!='P'));
           }
	       }while((ctr!='q')&&(ctr!='Q'));
          }
          return -1;
}

/***quit 離開***/ 
void quit(void){
     system("cls");
     printf("感謝您的使用\n");
     printf("\n╭╮＿＿　＼｜／　＿＿╭╮");
     printf("\n│　　　　　　　　　　　│");
     printf("\n│　　　　　　　　　　　│");
     printf("\n│　≧　╭───╮　≦　│");
     printf("\n│／／／│０　０│／／／│");
     printf("\n│　　　╰───╯　　　│");
     printf("\n╰──┬Ｏ────┬─Ｏ╯");
     printf("\n　　　│　　　　　│");
     printf("\n　　●│ │");
     printf("\n　　╰│　　Ｏ　　│");
     printf("\n　　　╰｜｜－｜｜╯ ");
     printf("\tｂｙｅ　ｂｙｅ　...\n");	
     system("pause");
}

/***Inquiry data 查詢資料***/ 
void Inquiry_data(void){
     char ch;
     int j,page=0,n,temp; //迴圈用變數 
     system("cls");
     if(num<=0){
     printf("\n\n\t但是目前沒有資料˙_˙\n\n\n");
     printf("�歈��� ╭──────────────╮\n");
     printf("�攭茶� │□□□□□□□□□□□□□□｜\n");
     printf("�羉翐齰y█▇▆▅▄▃ 幸 福 ▃▅▆▇█｜　\n");
     printf("�齯輟齰y＊＊＊◤ 快點上車吧!◢＊＊＊ ｜\n");
     printf("�屭涇齰y����  大家都一定要快樂唷���� ｜╴\n");
     printf("�裺��憓y □□□□□□□□□□□□□□｜　∼∼\n");
     printf("　�齱@ ╰───◎◎─────◎◎──╯▔\n");
     system("pause");
     }else{
           do{
              system("cls");
              printf("\t\t    ╭─︿＿＿︿─╮╭─︿＿＿︿─╮\n");
              printf("\t\t＊　│〈=－︽－=〉││〈=－︽－=〉│　＊\n");
              printf("\t\t：╭�灨歈������灨歈��灨歈������灨歈灨﹛G\n");
              printf("\t\t＊�齱砥@1.∼　＊　＊　＊　　＊　　＊�齱珮n");
              printf("\t\t：�齱@　　＊　鍵入式查詢∼＊　　　　�齱G\n");
              printf("\t\t＊�齱@  2.∼　　＊　　＊　　　　＊　�齱珮n");
              printf("\t\t：�齱@　　＊　列表式查詢∼＊　　　　�齱G\n");
              printf("\t\t＊�齱@  3.∼　　＊　　＊　　　　＊　�齱珮n");
              printf("\t\t：�齱@　　＊　離開  查詢∼＊　　　　�齱G\n");
              printf("\t\t＊╰�������������������������������灨ㄐ珮n\t\t\t\t   ");
              scanf("%c",&ch);
              fflush(stdin);
           }while(ch!='1'&&ch!='2'&&ch!='3');
           //因為只有輸入正確的數字才會離開迴圈
           //所以離開迴圈後ch不是1、2那就一定是3 
            /*鑑入式查詢*/ 
           if(ch=='1'){
               keyin();
           /*列表示查詢*/
           }else if(ch=='2'){
               list();
            //顯示把值回傳 
           } 
     }
}
/*key in 鍵入式*/
void keyin(void){
     char a[1000],ch2; //a儲存使用者鍵入欲查詢之名稱 ,ch存儲使用者的決定 
     int quit=0,i=0,k;//quit=1代表有查到資料,quit=0代表沒有查到資料
               printf("\t\t\t  請輸入欲查詢名稱:\n\n \t\t\t\t");
               scanf("%[^\n]",&a);
               fflush(stdin);
               do{/*比較字串的值若相等代表名稱相同,若查到相同則顯示*/
                  if(strcmp(a,stu[i-1].name)==0){
                  system("cls");
                  printf("\n\n");
                  show(stu[i-1],-2,-2); //單筆顯示,int -2 為單筆顯示控制值 
                  printf("\n\n");
                  quit=1;
                  }
                  i++;
               }while(i<=num&&quit==0); //直到沒資料or查到資料為止 ... 
               if(quit==0)
                  printf("查無資料\n");
               system("pause");
}
/***show 顯示***/
void show(STU data,int j,int k){
     int i;
     char compare[38]="1234567890123456789012345678"; //用以比較 
       printf("。……。…。…。…。…。…。…。…。…。 …。…。…。…。…。\n");
       if(j!=-2)
          printf("第%02d筆 :\t名稱 : %-17s第%d世紀心理學家\n",j+1,data.name,data.Century);
       else
          printf("\t\t名稱 : %-17s第%d世紀心理學家\n",data.name,data.Century);
       if(k==-2){
          printf("\t\t生於 : 西元%d %05.2f\t",data.score_start_year,data.score_start_MD);
          printf("卒於 : 西元%d %05.2f\n",data.score_end_year,data.socre_end_MD);
          printf("\t\t國籍 : %-15s",data.Nationality);
          printf("\t學派 : %-15s\n",data.school);
          printf("\t\t身份 : ");
          if(strcmp(data.status,compare)>0){
            for(i=0;i<34;i++)
              printf("%c",data.status[i]);
            printf(" ...\n");
          }else
            printf("%-15s\n",data.status);
       }
       printf("。……。…。…。…。…。…。…。…。…。 …。…。…。…。…。\n");
}

/***revise data  更改資料***/
void revise_data(void){
     char ch0,ch2;
     int n1,n2;
     system("cls");
     if(num==0){
          printf("\n\n\t目前沒有資料\n\t  無從更新\n\n\n");
     }else{
            n1 = Display_data(1);        
          if(n1>=0&&n1<=9){
          do{
             system("cls");
             show(stu[n1-1],-2,-2);
             printf("\t┌。•。•。•。•。•。•。•。•。•。┐\n\n");
             printf("\t•\t請選擇您想更改的資料項目\t•\n\n");
             printf("\t┌。•。•。•。•。•。•。•。•。•。┐\n\n");
             printf("\t。\t\t1.修改 : 名稱\t\t。\n");
             printf("\t•\t\t2.修改 : 生卒年\t\t•\n");
             printf("\t。\t\t3.修改 : 國籍\t\t。\n");
             printf("\t•\t\t4.修改 : 學派\t\t•\n");
             printf("\t。\t\t5.修改 : 身份\t\t。\n");
             printf("\t•\t\t6.離開\t\t\t•\n\n");
             printf("\t└。•。•。•。•。•。•。•。•。•。┘\n\n");
             printf("\t•\t\t請輸入 1 ~ 6\t\t•\n\n");
             printf("\t└。•。•。•。•。•。•。•。•。•。┘\n\t\t\t\t");
             scanf("%c",&ch2);
             fflush(stdin);
             n2=ch2-'0';
              switch(n2){
                 case 1:  /*修改名稱*/
                          ch0 = Inname(n1,1); 
                      break;
                 case 2:  /*修改生卒年*/ 
                          ch0 = InBD(n1,1);
                      break;
                 case 3:  /*修改國籍*/
                          ch0 = InNationality(n1,1);
                      break;
                 case 4:  /*修改學派*/
                          ch0 = Inschool(n1,1);
                      break;
                 case 5:  /*修改身份*/
                          ch0 = Instatus(n1,1); 
                      break;
                 case 6:  /*離開*/ 
                          ch0 = 'e';
                      break;
                 default :  printf("輸入錯誤");
                            system("pause");
                            ch0='n';
              }
          }while(ch0!='e');  //e = esc  哈哈XD 
          }
     }
}

/* Insert name */
char Inname(int n,const int swi){
     char ch,ch2;
     char name[100];//暫存 name 的空間 
     int k;  //迴圈判斷之使用函數 
     system("cls");
     printf("請輸入學者名稱:\n");
     scanf("%[^\n]",name);
     fflush(stdin); // 清理 儲存位置
     if(swi==0){
         strcpy(stu[n-1].name,name);
     }else{
         system("cls");
         printf("請問確定更做以下更改嗎?[Y/N]\n\n");
         printf("原本:\n\t%s\n",stu[n-1].name);
         printf("更改為:\n\t%s\n",name);
         scanf("%c",&ch);
         fflush(stdin); // 清理 儲存位置
         do{
            if(ch=='y'){
               strcpy(stu[n-1].name,name);
               printf(" 此資料更新完畢\n");
               k=1;
            }else if(ch=='n'){
               printf("此資料將不予更新 \n");
               k=1;
            }else{
               printf("輸入錯誤\n");
               k=0;
            }
         }while(k!=1);
         do{
            system("cls");             
            printf("\n要回主畫面請按'Q'\n\n");
            printf("若繼續操作請按'n'\n\n");
            scanf("%c",&ch2);
            fflush(stdin); // 清理 儲存位置
            if(ch2=='q'||ch2=='Q')
                return 'e';
            else if(ch2=='n'||ch2=='N')
                return 'n';
            else{
                printf("輸入錯誤\n\n");
                k=0;
            }
         }while(k!=1);
     }
     return 'e';
}

/* Insert Birth and Death  => 插入生卒年 */
char InBD(int n,const int swi){
     int start_year;     //暫存 start_year 的空間
     double start_MD;    //暫存 start_MD 的空間
     int end_year;       //暫存 end_year 的空間
     int cent;        //暫存 Century 的空間            
     double end_MD;      //暫存 end_MD 的空間
     char ch,ch2;
     int k;              //迴圈判斷之使用函數 
     do{
         system("cls");
         printf("請輸入該學者為哪個生於世紀[10~22]:\n");
         while(scanf("%d",&cent)==0){//判斷使用者是否輸入數字 
         fflush(stdin); // 清理 儲存位置
         printf("輸入的不是數字\n請從新輸入");
         }
     }while(cent>22||cent<10);  //若輸入不是該範圍則持續詢問
     system("cls");
     
     do{
        system("cls");
        printf("請輸入學者出生的年代(西元年):\n");
        while(scanf("%d",&start_year)==0){ 
        fflush(stdin); // 清理 儲存位置
        printf("輸入的不是數字\n請從新輸入");
        }
     }while(start_year<(cent-1)*100+1||start_year>cent*100);//判斷是否為該世紀 
     printf("請輸入");
     while(1){    
          printf("學者出生的月份.日期\n");
          while(scanf("%lf",&start_MD)==0){
          fflush(stdin); // 清理 儲存位置
          printf("輸入的不是數字\n請從新輸入");
          printf("學者出生的月份.日期\n");
          }
          //==判斷月份/日期========= 
          if((start_MD >= 1.0)&&(start_MD <=1.31)
          ||(start_MD >= 2.0)&&(start_MD <=2.29)
          ||(start_MD >= 3.0)&&(start_MD <=3.31)
          ||(start_MD >= 4.0)&&(start_MD <=4.30)
          ||(start_MD >= 5.0)&&(start_MD <=5.31)
          ||(start_MD >= 6.0)&&(start_MD <=6.30)
          ||(start_MD >= 7.0)&&(start_MD <=7.31)
          ||(start_MD >= 8.0)&&(start_MD <=8.31)
          ||(start_MD >= 9.0)&&(start_MD <=9.30)
          ||(start_MD >= 10.0)&&(start_MD <=10.31)
          ||(start_MD >= 11.0)&&(start_MD <=11.30)
          ||(start_MD >= 12.0)&&(start_MD <=12.31))
            break;
          else
            printf("輸入錯誤!\n請從新輸入");    
     }
     printf("請輸入");
     while(1){ 
       printf("學者逝世的年代(西元年)\n");
       while(scanf("%d",&end_year)==0){
       fflush(stdin); // 清理 儲存位置
       printf("輸入的不是數字\n請從新輸入");
       printf("學者逝世的年代(西元年)\n");
       }
       if(end_year<=start_year)
          printf("逝世年代不合理!\n請從新輸入");
       else
          break;    //正確後跳出回圈繼續執行      
     }
     printf("請輸入");
     while(1){ 
       printf("學者逝世的月份.日期\n");
       while(scanf("%lf",&end_MD)==0){
       fflush(stdin); // 清理 儲存位置
       printf("輸入的不是數字\n請從新輸入");
       printf("學者逝世的月份.日期\n");
       }
       //==判斷月份/日期========== 
       if((end_MD >= 1.0)&&(end_MD <=1.31)
       ||(end_MD >= 2.0)&&(end_MD <=2.29)
       ||(end_MD >= 3.0)&&(end_MD <=3.31)
       ||(end_MD >= 4.0)&&(end_MD <=4.30)
       ||(end_MD >= 5.0)&&(end_MD <=5.31)
       ||(end_MD >= 6.0)&&(end_MD <=6.30)
       ||(end_MD >= 7.0)&&(end_MD <=7.31)
       ||(end_MD >= 8.0)&&(end_MD <=8.31)
       ||(end_MD >= 9.0)&&(end_MD <=9.30)
       ||(end_MD >= 10.0)&&(end_MD <=10.31)
       ||(end_MD >= 11.0)&&(end_MD <=11.30)
       ||(end_MD >= 12.0)&&(end_MD <=12.31))
          break; //正確後跳出迴圈繼續執行 
       else
          printf("輸入錯誤!\n請從新輸入");    
     }
     if(swi==0){  //swi=0為Insert之功能 
        /*為新增內的功能*/
        stu[n-1].score_start_year = start_year;
        stu[n-1].score_start_MD=start_MD;
        stu[n-1].score_end_year=end_year;              
        stu[n-1].socre_end_MD=end_MD;
        /*判斷為此學者生於幾世紀*/
        stu[n-1].Century = cent;
     }else{     //swi!=0為更新之功能 
         system("cls");
         printf("請問確定更做以下更改嗎?[Y/N]\n\n");
         printf("原本:\n\t%d世紀的學者",stu[n-1].Century);
         printf("\n\t生於年%4d ",stu[n-1].score_start_year);
         printf("%04.2lf\n",stu[n-1].score_start_MD);
         printf("\t卒於年%4d ",stu[n-1].score_end_year);
         printf("%04.2lf\n\n",stu[n-1].socre_end_MD);
         printf("更改為:\n\t%d世紀的學者",cent);
         printf("\n\t生於年%4d ",start_year);
         printf("%04.2lf\n",start_MD);
         printf("\t卒於年%4d ",end_year);
         printf("%04.2lf\n",end_MD);
         scanf("%c",&ch);
         fflush(stdin); // 清理 儲存位置
         do{
            system("cls");
            if(ch=='y'||ch=='Y'){  //將資料存入 
               stu[n-1].score_start_year = start_year;
               stu[n-1].score_start_MD=start_MD;
               stu[n-1].score_end_year=end_year;              
               stu[n-1].socre_end_MD=end_MD;
               stu[n-1].Century = cent;
               /*判斷為此學者生於幾世紀*/
               printf(" 此資料更新完畢\n");
               k=1;
            }else if(ch=='n'||ch=='N'){
               printf("此資料將不予更新 \n");
               k=1;
            }else{
               printf("輸入錯誤\n");
               k=0;
            }
         }while(k!=1);
         do{
            system("cls");
            printf("要回主畫面請按'Q'\n\n");
            printf("若繼續操作請按'n'\n\n");
            scanf("%c",&ch2);
            fflush(stdin); // 清理 儲存位置
            if(ch2=='q'||ch2=='Q')
               return 'e';
            else if(ch2=='n'||ch2=='N')
               return 'n';
            else{
               printf("輸入錯誤\n");
               k=0;
            }
         }while(k!=1);
     }
     return 'e';
}

/* Insert Nationality */
char InNationality(int n,const int swi){
     char ch,ch2;
     char Nationality[30];   //暫存 Nationality 的空間
     int k;                  //迴圈判斷之使用函數 
     system("cls");
     printf("請輸入學者的國籍:\n");
     scanf("%[^\n]",Nationality);
     fflush(stdin); // 清理 儲存位置
     if(swi==0){
         strcpy(stu[n-1].Nationality,Nationality);
     }else{
        do{
           system("cls");
           printf("請問確定更做以下更改嗎?[Y/N]\n\n");
           printf("原本:\n\t%s\n",stu[n-1].Nationality);
           printf("更改為:\n\t%s\n",Nationality);
           scanf("%c",&ch);
           fflush(stdin); // 清理 儲存位置
           if(ch=='y'){
              strcpy(stu[n-1].Nationality,Nationality);
              printf("此資料更新完畢\n");
              k=1;
           }else if(ch=='n'){
              printf("此資料將不予更新\n");
              k=1;
           }else{
              printf("輸入錯誤\n");
              k=0;
           }
        }while(k!=1);
        do{
           system("cls");
           printf("\n要回主畫面請按'Q'\n");
           printf("若繼續操作請按'n'\n");
           scanf("%c",&ch2);
           fflush(stdin); // 清理 儲存位置
           if(ch2=='q'||ch2=='Q')
              return 'e';
           else if(ch2=='n'||ch2=='N')
              return 'n';
           else{
              printf("輸入錯誤\n"); 
              k=0;
           }
        }while(k=!1);
         system("pause");
     } 
     return 'e';
}

/* Insert school */
char Inschool(int n,const int swi){
     char ch,ch2;
     char school[50];   //暫存 school 的空間
     int k;             //迴圈判斷之使用函數 
     system("cls");
     printf("請輸入學者的學派:\n");
     scanf("%[^\n]",school);
     fflush(stdin); // 清理 儲存位置 
     if(swi==0){
         strcpy(stu[n-1].school,school);
     }else{
         printf("請問確定更做以下更改嗎?[Y/N]\n\n");
         printf("原本:\n\t%s\n",stu[n-1].school);
         printf("更改為:\n\t%s\n",school);
         scanf("%c",&ch);
         fflush(stdin); // 清理 儲存位置
         do{
            if(ch=='y'){
               strcpy(stu[n-1].school,school);
               printf("此資料更新完畢\n");
               k=1;
            }else if(ch=='n'){
               printf("此資料將不予更新\n");
               k=1;
            }else{
               printf("輸入錯誤\n");
               k=0;
            }
         }while(k=!1);
         do{
            system("cls");
            printf("要回主畫面請按'Q'\n");
            printf("若繼續操作請按'n'\n");
            scanf("%c",&ch2);
            fflush(stdin); // 清理 儲存位置
            if(ch2=='q'||ch2=='Q')
               return 'e';
            else if(ch2=='n'||ch2=='N')
               return 'n';
            else{
               printf("輸入錯誤\n"); 
               k=0;
            }
         }while(k=!1);
     } 
     return 'n';
}
/* Insert status */
char Instatus(int n,const int swi){
     char ch,ch2;
     char status[100];  //暫存 status 的空間
     int k;             //迴圈判斷之使用函數 
     system("cls");
     printf("請輸入學者的身份:\n");
     scanf("%[^\n]",status);
     fflush(stdin); // 清理 儲存位置
     if(swi==0){
         strcpy(stu[n-1].status,status);
     }else{
         do{
            system("cls");
            printf("請問確定更做以下更改嗎[Y/N]?\n\n");
            printf("原本:\n\t%s\n",stu[n-1].status);
            printf("更改為:\n\t%s\n",status);
            scanf("%c",&ch);
            fflush(stdin); // 清理 儲存位置
            if(ch=='y'){
               strcpy(stu[n-1].status,status);
               printf("此資料更新完畢\n");
               k=1;
            }else if(ch=='n'){
               printf("此資料將不予更新\n");
               k=1;
            }else{
               printf("輸入錯誤\n");
               k=0;
            }
         }while(k!=1);
         do{
            system("cls");
            printf("要回主畫面請按'Q'\n");
            printf("若繼續操作請按'n'\n");
            scanf("%c",&ch2);
            fflush(stdin); // 清理 儲存位置
            if(ch2=='q'||ch2=='Q')
               return 'e';
            else if(ch2=='n'||ch2=='N')
               return 'n';
            else{
               printf("輸入錯誤\n");
               k=0;
            }
         }while(k!=1); 
     } 
     return 'e';
}

/*sequence 排序 */
void sequence(void){
     /*將較早出生的學者排到前面*/     
     int i;
     if(num>=2)
       for(i=0;i<(num-1);i++){ 
         if(stu[i].score_start_year!=stu[i+1].score_start_year){
         //如果下一個的出生年跟上一個不同 
           if(stu[i].score_start_year>stu[i+1].score_start_year){
           //且,上一個的出生年代大於下一個的出生年代,則將兩筆資料交換
              change(i,i+1);
           }
         }else{//如果上一個與下一個出生年代相同
           if(stu[i].score_start_MD<stu[i+1].score_start_MD){
           //且上一個的出生月份比較早(小),則將兩筆資料交換
              change(i,i+1); 
          } 
       }
     }                   
}

/*change 交換*/
void change(int a,int b){
     char name[100];
     char Nationality[30];
     char school[50];
     char status[500];
     int Century;
     int start_year;
     double start_MD;
     int end_year;              
     double end_MD;
     /*將a的值放至暫存空間*/
     strcpy(name,stu[a].name);
     strcpy(Nationality,stu[a].Nationality);
     strcpy(school,stu[a].school);
     strcpy(status,stu[a].status);
     Century     = stu[a].Century;
     start_year  = stu[a].score_start_year;
     start_MD    = stu[a].score_start_MD;
     end_year    = stu[a].score_end_year;
     end_MD      = stu[a].socre_end_MD;
     /*將b存到a*/
     strcpy(stu[a].name,stu[b].name);
     strcpy(stu[a].Nationality,stu[b].Nationality);
     strcpy(stu[a].school,stu[b].school);
     strcpy(stu[a].status,stu[b].status);
     stu[a].Century           = stu[b].Century;
     stu[a].score_start_year  = stu[b].score_start_year;
     stu[a].score_start_MD    = stu[b].score_start_MD;
     stu[a].score_end_year    = stu[b].score_end_year;
     stu[a].socre_end_MD      = stu[b].socre_end_MD;
     /*將暫存存到b*/
     strcpy(stu[b].name,name);
     strcpy(stu[b].Nationality,Nationality);
     strcpy(stu[b].school,school);
     strcpy(stu[b].status,status);
     stu[b].Century           = Century;
     stu[b].score_start_year  = start_year;
     stu[b].score_start_MD    = start_MD;
     stu[b].score_end_year    = end_year;
     stu[b].socre_end_MD      = end_MD;  
}

/* open data 開啟並讀取.txt內的資料*/
void opendata(const char swi){
    int temp;
	char msg[30];
	system("cls");
	printf("開啟中 ...\n");
	/*開啟檔案*/
    if(swi=='r')//如果控制參數為'r'則使用rb+打開資料 
	   if((fp=fopen(filename,"rb+"))==NULL){
		  printf("\n\n無法開啟檔案 : %s\n\n",filename);
		  exit(0);
	   }
    if(swi=='w')//如果控制參數為'w'則使用wb+打開資料 
       if((fp=fopen(filename,"wb+"))==NULL){
		  printf("\n\n無法開啟檔案 : %s\n\n",filename);
		  exit(0);
	   }
	fseek(fp,0,SEEK_SET);
	/*讀取資料*/ 
	while(fscanf(fp,"%s",stu[num].name)!=EOF){
		fscanf(fp,"%s",stu[num].Nationality); //讀國籍
        fscanf(fp,"%s",stu[num].school);      //讀學派 
        fscanf(fp,"%s",stu[num].status);      //讀身分
        fscanf(fp,"%s",msg);
        stu[num].Century=atoi(msg);           //讀世紀 
        fscanf(fp,"%s",msg);
		stu[num].score_start_year=atoi(msg);  //讀生於年 
		fscanf(fp,"%s",msg);
		stu[num].score_start_MD=atof(msg);    //讀生月.日 
		fscanf(fp,"%s",msg);
		stu[num].score_end_year=atoi(msg);    //讀卒於年 
		fscanf(fp,"%s",msg);
		stu[num].socre_end_MD=atof(msg);      //讀卒月.日
        num++;
	}
}

/* closr data 關閉並儲存資料進入database.txt */
void closedata(){
	int i;
	/*利用迴圈將陣列的資料存回檔案裡*/
	fseek(fp,0,SEEK_SET);
	system("cls");
	printf("關閉中 ...\n");
	for(i=0;i<num;i++){
        fprintf(fp,"%s\n",stu[i].name);
	    fprintf(fp,"%s\n",stu[i].Nationality);
		fprintf(fp,"%s\n",stu[i].school);
        fprintf(fp,"%s\n",stu[i].status);
        fprintf(fp,"%d\n",stu[i].Century);
		fprintf(fp,"%d\n",stu[i].score_start_year);
		fprintf(fp,"%lf\n",stu[i].score_start_MD);
		fprintf(fp,"%d\n",stu[i].score_end_year);
		fprintf(fp,"%lf\n",stu[i].socre_end_MD);
		}
	/*關閉檔案*/
	if(fclose(fp)!=0){
		printf("無法關閉檔案 : %s\n",filename);
		exit(0);
		} 
	} 
