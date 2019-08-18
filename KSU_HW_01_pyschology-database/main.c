/********4*0*1*0*E*1*0*5**½²**Às**¦ö****¤ß**²z**¾Ç**®a**¸ê**®Æ**®w********/
 
/*¨ç¥«®w«Å§i*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "ture_c.h"  //©I¥s¦Û¤v©w¸qªºµ²ºc 
/*³]©w¥þ°ìÅÜ¼Æ*/
FILE *fp; //ÀÉ®×ªº«ü¼Ð 
STU stu[99];
int num=0;

/*«Å§i°Æ¨ç¦¡*/ 
char User_Interface(const int); //¿ï³æ int=¨Ï¥ÎªÌ¿ù»~¦¸¼Æ,char¦^¶Ç¨Ï¥ÎªÌ¿ï¾Ü¤§«ü¥O 
void opendata(const char);    //¶}±Ò¨ÃÅª¨úpsychologydata.txtªº¸ê®Æ,char:'r'=rb+,'w'=wb+
void closedata(void);   //Ãö³¬¨ÃÀx¦s¸ê®Æ¦Üpsychologydata.txt
void Insert_data(void);      //·s¼W Insret
void Inquiry_data(void);     //¬d¸ß inquury 
void delete_data(void);      //§R°£ delete
void revise_data(void);      //§ó§ï revise
void data_amount(void);      //¸ê®Æ¼Æ¶q amount  
void quit(void);             //Â÷¶} quit
int Display_data(const int);//Åã¥Ü,int=0¬°¥Dµ{¦¡Åã¥Ü¨Ï¥Î,
//int=1«h¬°§ó·s¤§Åã¥Ü¥\¯à,int=2«h¬°§R°£,int=3«h¬°¬d¸ß¤§¦Cªí¬dªk 
void show(STU data,int,int);     //Åã¥Ü³æµ§¸ê®Æ,­Yint = -2 «h³æ¯ÂÅã¥Ü¸Ó¸ê®Æ 
int clear(int);              //clear data
char Inname(int,const int);  //Insert¦WºÙint=¦ì¸m,int=0«h¬°Insert¤§¥\¯à 
char InBD(int,const int);    //Insert Birth and Death  => Insert¥Í¨ò¦~
char InNationality(int,const int); //Insert°êÄyint=¦ì¸m,int=0«h¬°Insert¤§¥\¯à 
char Inschool(int,const int);//Insert¾Ç¬£int=¦ì¸m,int=0«h¬°Insert¤§¥\¯à 
char Instatus(int,const int);//Insert¨­¥÷int=¦ì¸m,int=0«h¬°Insert¤§¥\¯à 
void keyin(void);            //Áä¤J¦¡
void list(void);             //¦CÁ|¦¡ 
void sequence(void);         //±Æ§Ç
void change(int,int);            //±N¨âµ§¸ê®Æ¥æ´« 
/*** main ¥Dµ{¦¡°õ¦æ ***/ 
int main(){
    char bee='\07'; //¿é¤J¿ù»~®ÉBee¤@Án 
    int k=0,quit_m=0;//K­pºâ¿ù»~¦¸¼Æ,quit_m=1¬°Â÷¶},quit_m=0¬°¿ù»~¦Ó¹L¦h¸õ¥X 
    opendata('r'); //¶}±Ò.txtÀÉ =>¥Îrb+ 
    closedata(); 
    do{
       sequence(); //±Æ¶¶§Ç  ¡ö¨C°µ¤@­Ó°Ê§@³£·|±Æ¤@¦¸ 
       system("cls");
       switch(User_Interface(k)){
          case 'I':  //·s¼W¸ê®Æ_OK 
          case 'i':  k=0;
                     Insert_data(); 
                break;
          case 'D':  //§R°£¸ê®Æ_OK 
          case 'd':  k=0;
                     delete_data();
               break;
          case 'Q':  //¬d¸ß¸ê®Æ_OK 
          case 'q':  k=0;
                     Inquiry_data();
               break;
          case 'M':  //Åã¥Ü¸ê®Æ_ok
          case 'm':  k=0;
                     Display_data(0);
               break;
          case 'E':  //Â÷¶}_OK 
          case 'e':  quit();
                     quit_m=1;
               break;
          case 'K':  //Åã¥Ü¸ê®Æ¼Æ¶q_OK 
          case 'k':  k=0;
                     data_amount();
               break;
          case 'R' : //§ó·s_OK 
          case 'r' : k=0;
         	         revise_data(); 
               break;
          default :system("cls");
                   printf("±z¿é¤J¿ù»~ªº«ü¥O!½Ð±q·s¿é¤J\n");
                   printf("%c",bee); 
                   system("pause");
                   k++;
       }    
    }while(k<5&&quit_m==0);
    system("cls");
    if(quit==0){
       printf("¿é¤J¿ù»~¶W¹L5¦¸");
       system("pause");
    }
    opendata('w'); //¶}±Ò.txtÀÉ =>¥Îw+ 
    closedata(); //Ãö³¬ .txtÀÉ 
    return 0;
    }
    
/***User Interface ¨Ï¥ÎªÌ¤¶­±***/
char User_Interface(const int a){
       char ch;
       system("cls");
           printf("¡ï¢X¡O¡¸¢X¡D¡O¢X¡O¢X¡E¢X¡ï¢X¡E¢X¡³¡C+¢Ý¡E¢Xo¡E¢Xo¡D¡O¡E¢X¡ï¢X¡D¡O¡E¢X");
           printf("\n¢¨¢i¢i¢i¢i¢i¢i¢i¢i¢i¢i¢i¢i¢i¢i¢i¢i¢i¢i¢i¢i¢i¢©");
           printf("\n¡@¡@¡@¢i¡@¡@¡@¡@¡@ ¡¸½Ð¿ï¾Ü±z±ý¤u§@ªº¶µ¥Ø¡¸   ¡@¡@¢i");
           printf("\n¡@¢i¢©¢i¢¨¢i¡@¡@          (I)  ¥[¤J·s¸ê®Æ       ¡@¢i");
           printf("\n¢ª¡ÅÅw¢iªï¡Å¢«¡@¡@¡@¡@¡@¡@(D)  §R°£¸ê®Æ   ¡@¡@¡@¡@¢i");
           printf("\n¢¨¢i¢¨¢i¢©¢i¢©¡@¡@¡@¡@¡@¡@(Q)  ¬d¸ß¸ê®Æ ¡@¡@¡@¡@¡@¢i");
           printf("\n¡@¡@¡@¢i¡@¡@¡@¡@¡@¡@¡@¡@¡@(M)  Åã¥Ü¸ê®Æ     ¡@¡@¡@¢i");
           printf("\n¡@¡@¡@¢i¡@¡@¡@¡@¡@¡@¡@¡@¡@(R)  §ó§ï¸ê®Æ           ¢i");
           printf("\n¡@¡@¡@¢i¡@¡@¡@¡@¡@¡@¡@¡@  (K)  Åã¥Ü¸ê®Æ¼Æ¥Ø   ¡@¡@¢i");
           printf("\n¡@¡@¡@¢i¡@¢¨¢i¢©¢¨¢i¢©¡@¡@(E)   Â÷   ¶}       ¡@¡@¢i¡@");
           printf("\n¡@¡@¡@¢i¡@¢i¡@¡´¡´¡@¢i¡@¡¸¡@¡@¡¸¡@¡@¡¸¡@¡@¡¸¡@¡@¡@¢i");
           printf("\n¡@¡@¡@¢i¢¨¢i¢i¢«¢ª¢i¢i¢©¡@¡@¡@¡@¡@¡@¡@¡@¡@¡@¡@¢i¢©¢i¢¨¢i");
           if(a==0)
           printf("\n¡@¡@¡@¢i¢i¡³¢i¢i¢i¢i¡³¢i                    ¢ª¡Å¨Ï¢i¥Î¡Å¢«");
           else
           printf("\n¡@¡@¡@¢i¢i¡³¢i¢i¢i¢i¡³¢i     ¿é¤J¿ù»~%d¦¸    ¢ª¡Å¨Ï¢i¥Î¡Å¢«",a);
           printf("\n¡@¡@¡@¢i¢ª¢i¢i¢©¢¨¢i¢i¢«¡@¡@¡@¡@¡@¡@¡@¡@¡@¡@¢¨¢i¢¨¢i¢©¢i¢©");
           printf("\n¡@¡@¡@¢i¢¨¢i¢i¢i¢i¢i¢i¢©¡@¡@¡¸¡@¡@¡¸¡@¡@¡¸¡@¡@¡@¡@¢i");
           printf("\n¡@¡@¡@¢ª¢i¢i¢i¢i¢i¢i¢i¢i¢i¢i¢i¢i¢i\n");
           printf("¡ï¢X¡O¡¸¢X¡D¡O¢X¡O¢X¡E¢X¡ï¢X¡E¢X¡³¡C+¢Ý¡E¢Xo¡E¢Xo¡D¡O¡E¢X¡ï¢X¡D¡O¡E¢X\n\n");
        printf("\t½Ð  ¿é  ¤J  : \t");
        scanf("%c",&ch);
        fflush(stdin); // ²M²z Àx¦s¦ì¸m 
        return ch;
}

/***Insert data´¡¤J(¸ê®Æ)***/
void Insert_data(void){
    char yorn;
     system("cls");
     num++;        //num++ ±N·sªºªÅ¶¡ªÅ¥X¨Ó
  if(num>=99){     //§PÂ_¸ê®Æ¶q¬O§_¤wº¡ 
     printf("¸ê®Æ®w¤wº¡\n");
  }else{
        Inname(num,0); // num,int=0«h¬°Insert¤§¥\¯à 
        InBD(num,0);   //Insert Birth and Death  => ´¡¤J¥Í¨ò¦~
        InNationality(num,0);
        Inschool(num,0);
        Instatus(num,0);
        /*½T»{­è­è¿é¤Jªº¸ê®Æ*/
       system("cls"); //²M°£µe­± 
       printf("½Ð½T»{¿é¤J¸ê®Æ¬O§_µL»~\n");
       show(stu[num-1],-2,-2);
       printf("¬O§_¦s¤J?(Y/N) : \n");
       printf("ùùùùùùùùùùùù¡äùùùùùùùùùù\n");
       printf("¡@¡@¡@¡@¡@¢~ùá¢¡¡@¡@¡@¡@¡@¡@¡@¡@¡@ ¢¨\n");
       printf("¢b¢~¢f¢g¢h¡¼¡¼¢i¢h¢g¢f¢e¢d¢c¢b(¢®)¢i¢¡\n");
       printf("¡@¢¢ùù¢d__¢b¢b¢b¢b¢b¡çùùùùùùùù¢v¢v¢v¡@\n");
       printf("¡@¡@¡@¡@ùõO ¡@ ùõO\n");
       do{              // do...while ³Ì¤Ö°õ¦æ¤@¦¸ 
          scanf("%c",&yorn);
          fflush(stdin); // ²M²z Àx¦s¦ì¸m
          if((yorn=='y')||(yorn=='Y')) //yes ±N¸ê®ÆÀx¦s
                 printf("¤w±N¸ê®ÆÀx¦s,ÁÂÁÂ±z¨Ï¥Î\n");
          else if((yorn=='n')||(yorn=='N')){ //no ±N¸ê®Æ®ø°£ 
                printf("¦¹µ§¸ê®Æ±N¤£·|³Q¦s¤J\n");
                printf("ÁÂÁÂ¨Ï¥Î\n");
                num--;  //±N­è­è·s¼Wªº¸ê®ÆªÅ¶¡ÂkÁÙ,¨Ã¥B¤£¤¹¤©Åã¥Ü 
          }else{
                yorn = 'r';   //¥O§PÂ_ yorn ='r' ¨Ï°j°éÄ~Äò°õ¦æ  
                printf("¿é¤J¿ù»~\n"); 
                printf("½Ð¿é¤J (Y/N) : ");
          } 
       }while(yorn=='r'); //yorn == 'r' ¬°°õ¦æ±ø¥ó
 }
 system("pause");
}
/*list ¦Cªí¦¡¬d¸ß*/
void list(void){
     int n=0,k,temp;
     char ch,ch2='\0'; //ch2 ³]©wµL¬ÛÃö­È¬°ªì©l­È,¥H§K°j°é¿ù»~ 
          do{
             ch2='\0'; //¨C¦¸³£­n±N­Èµ¹²MªÅ§_«h°õ¦æ¹L¤@¦¸´N¤£·|¦A§PÂ_Àx¦s 
             system("cls");
             do{ 
                 show(stu[n],n%7,-1);
                 n++;
             }while(n<num&&(n%7!=0));
             printf("\t\t\t¿é¤J±ý¬d¬Ý½s¸¹\n");
             printf("\t¤W¤@­¶½Ð¿é¤J\"p\"¤U¤@­¶½Ð¿é¤J\"n\"Â÷¶}½Ð¿é¤J\"q\"\n");
             scanf("%c",&ch);
             fflush(stdin);
             if(ch=='n'||ch=='N'){  //¤U¤@­¶
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
             }else if(ch=='p'||ch=='P'){ //¤W¤@­¶
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
             }else if(ch>='1'&&ch<='5'){ //§PÂ_¿é¤J¬O§_¬°1~5ªº¼Æ¦r 
                k=ch -'0';  //±NASC||½Xªº¼Æ¦r´«¦¨¤@¯ë¼Æ¦r 
                temp = n/7; //­pºâ­¶¼Æ 
                system("cls");
                printf("\t±z¬d¸ßªº¸ê®Æ¬° ....\n\n");
                if(n<=7) //­Y¥u¦³¤@­¶ 
                show(stu[k-1],-2,-2); //±N¼Æ¦r±a¤J·íµ§ - Åã¥Ü¨ç¦¡ 
                else  //­Y¤£¥u¤@­¶ 
                show(stu[((temp-1)*7)+k-1],-2,-2);//­¶¼Æ*¤@­¶5µ§+¨Ï¥ÎªÌ¿ï¾Üªº¶µ¥Ø¼Æ¦r 
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
/***data amount ¸ê®Æ¶q***/
void data_amount(){
     system("cls");
     printf("     ¡O¡¸¢X¡O¡¸¡D¡O¡¸¢X¡O¡¸¡D¡O¡¸¢X\n");
     printf("ùÝùäùùùùùùùßùÝùùùùùùùùùßùÝùùùùùùùùùßùÝùùùùùùùùùß\n");
     if(num==0)  //¨S¦³¸ê®Æ«hÅã¥Ü¨S¦³ 
        printf("ùø¥Ø«e¡ã¡ãùàùâ¸ê®Æ®w¡ãùàùâ¡ã¨S¦³¡ãùàùâ¸ê®Æ¡ã¡ãùà\n");
     else        //§_«hÅã¥Ü¸ê®Æªº¼Æ¶q                                     
        printf("ùø¥Ø«e¡ã¡ãùàùâ¤w¸g¦³¡ãùàùâ%d¡ãµ§¡ãùàùâ¸ê®Æ¡ã¡ãùà\n",num);
     printf("ùã¡·ùùùù¡·ùåùã¡·ùùùù¡·ùåùã¡·ùùùù¡·ùåùã¡·ùùùù¡·ùå\n");
     printf("¢X¡O¡¸¢X¡D¡O¡E¢X¡ï¢X¡O¢X¡D¡O¡E¢X¡ï¢X.¡O¡E¢X¡ï¢X¡O¡¸\n\n");
     system("pause"); 
}

/***delete data §R°£¸ê®Æ***/
void delete_data(void){
     int n=0,k,temp;
     char ch,ch2='\0'; //ch2 ³]©wµL¬ÛÃö­È¬°ªì©l­È,¥H§K°j°é¿ù»~ 
     system("cls"); 
     if(num==0){
       printf("\n\n\t¸ê®Æ®w¤ºµL¸ê®Æ\n\n");
       system("pause");
     }else{
          do{
            system("cls");
            ch2='\0'; //µ¹©wªì©l­È 
             do{ 
                 show(stu[n],n%5,-1);
                 n++;
             }while(n<num&&(n%5!=0));
             printf("\t\t\t¿é¤J±ý§R°£½s¸¹\n");
             printf("\t¤W¤@­¶½Ð¿é¤J\"p\"¤U¤@­¶½Ð¿é¤J\"n\"Â÷¶}½Ð¿é¤J\"q\"\n");
             scanf("%c",&ch);
             fflush(stdin);
             if(ch=='n'||ch=='N'){  //¤U¤@­¶
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
             }else if(ch=='p'||ch=='P'){ //¤W¤@­¶
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
             }else if(ch>='1'&&ch<='5'){ //§PÂ_¿é¤J¬O§_¬°1~5ªº¼Æ¦r 
                k=ch -'0';  //±NASC||½Xªº¼Æ¦r´«¦¨¤@¯ë¼Æ¦r 
                temp = n/5; //­pºâ­¶¼Æ 
                system("cls");
                printf("½Ð°Ý¬O§_§R°£¦¹µ§¸ê®Æ\n");
                if(n<=5) //­Y¥u¦³¤@­¶ 
                show(stu[k-1],-2,-2); //±N¼Æ¦r±a¤J·íµ§ - Åã¥Ü¨ç¦¡ 
                else  //­Y¤£¥u¤@­¶ 
                show(stu[((temp-1)*5)+k-1],-2,-2);//­¶¼Æ*¤@­¶5µ§+¨Ï¥ÎªÌ¿ï¾Üªº¶µ¥Ø¼Æ¦r 
                while(ch2!='n'&&ch2!='N'&&ch2!='Y'&&ch2!='y'){    
                printf("\n½Ð¿é¤J(Y/N):\t");     
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
                      printf("±z¿é¤J¿ù»~\n");
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

/***clear data ²M°£¸ê®Æ***/     
int clear(int n){
     int i;
     printf("§R°£¤¤ ....\n");
     /*±N«á­±ªº¸ê®Æ©¹«e¸É ... */ 
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
     /*±N³Ì«á¤@µ§¸ê®Æ§R°£*/
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

/***Display data Åã¥Ü¸ê®Æ***/	
int Display_data(int che){
    int i=0; //°Ñ¼Æ 
    int k;  //±N¨Ï¥ÎªÌ¿é¤JªºASCIIªº¼Æ¦rÂà´«¦¨¤@¯ë¼Æ¦r¨Ã¦s¤J 
	char ctr;//¿é¤Jªº«ü¥O 
	system("cls");
    /*Åã¥Ü*/
    if(num==0){ //§PÂ_¸ê®Æ®w¤º¬O§_¦³¸ê®Æ 
	printf("\n\t¥Ø«e¸ê®Æ®w¤¤¨S¦³¸ê®Æ\n\n\n");
	system("pause");
    }else{ 
	       do{
	          system("cls"); //²M°£µe­±
	          if(che == 1)
                 printf("\t\t½Ð½Ð¿é¤J±ý§ó§ï¸ê®Æ¤§¸¹½X?\n"); 
	          do{
                 if(che==1)
	               show(stu[i],i%3,-2);
	             else
	               show(stu[i],i,-2);
	             printf("\n");
	             i++;//  ¡õÅã¥Ü3µ§,­Y¤£¨¬3¤ñ«h¥þ³¡Åã¥Ü
                 }while(((i<num)&&((i%3)!=0)));	
           /*§PÂ_¤W¤@­¶,¤U¤@­¶,Â÷¶}*/ 
	       if((i<=3)&&(i==num)){ //¥u¦³¤@­¶ 
              do{
                 printf("¡¯¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡¯\n");
	             printf("\t\t\tÂ÷¶}½Ð«ö'q'\n\t\t\t   ",i);
		         scanf("%c",&ctr);
		         fflush(stdin);
		         if(che == 1)
                    if(ctr<='3'&&ctr>='1'){
                       k = ctr-'0';
                       return k;
                    }
              }while(ctr!='q'&&ctr!='Q');
           }else if((i<=3)&&(i!=num)){ //¦ì©ó²Ä¤@­¶ 
              do{
                 printf("¡¯¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡¯\n");
			     printf("\t\t¤U¤@­¶½Ð«ö\"n\"¡AÂ÷¶}½Ð«ö\"q\"\n\t\t\t\t");
			     scanf("%c",&ctr);
			     fflush(stdin);
			     if(che == 1)
                    if(ctr<='3'&&ctr>='1'){
                       k=ctr-'0';
                       return k;
                    }
              }while((ctr!='q')&&(ctr!='n')&&(ctr!='Q')&&(ctr!='N'));
           }else if((i>3)&&(i!=num)){ //¦ì©ó¤¤¶¡­¶ 
              do{
                 printf("¡¯¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡¯\n");
			     printf("\t«e¤@­¶½Ð«ö\"p\"¡A¤U¤@­¶½Ð«ö«ö\"n\"¡AÂ÷¶}½Ð«ö\"q\"\n\t\t\t\t");
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
           }else{  //³Ì«á¤@­¶ 
              do{
                 printf("¡¯¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡C¡Ð¡¯\n");
			     printf("\t\t«e¤@­¶½Ð«ö\"p\"¡AÂ÷¶}½Ð«ö\"q\"\n\t\t\t\t");
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

/***quit Â÷¶}***/ 
void quit(void){
     system("cls");
     printf("·PÁÂ±zªº¨Ï¥Î\n");
     printf("\n¢~¢¡¡Ä¡Ä¡@¢@¡U¡þ¡@¡Ä¡Ä¢~¢¡");
     printf("\n¢x¡@¡@¡@¡@¡@¡@¡@¡@¡@¡@¡@¢x");
     printf("\n¢x¡@¡@¡@¡@¡@¡@¡@¡@¡@¡@¡@¢x");
     printf("\n¢x¡@¡Ù¡@¢~¢w¢w¢w¢¡¡@¡Ø¡@¢x");
     printf("\n¢x¡þ¡þ¡þ¢x¢¯¡@¢¯¢x¡þ¡þ¡þ¢x");
     printf("\n¢x¡@¡@¡@¢¢¢w¢w¢w¢£¡@¡@¡@¢x");
     printf("\n¢¢¢w¢w¢s¢Ý¢w¢w¢w¢w¢s¢w¢Ý¢£");
     printf("\n¡@¡@¡@¢x¡@¡@¡@¡@¡@¢x");
     printf("\n¡@¡@¡´¢x ¢x");
     printf("\n¡@¡@¢¢¢x¡@¡@¢Ý¡@¡@¢x");
     printf("\n¡@¡@¡@¢¢¡U¡U¡Ð¡U¡U¢£ ");
     printf("\t¢ê£B¢í¡@¢ê£B¢í¡@...\n");	
     system("pause");
}

/***Inquiry data ¬d¸ß¸ê®Æ***/ 
void Inquiry_data(void){
     char ch;
     int j,page=0,n,temp; //°j°é¥ÎÅÜ¼Æ 
     system("cls");
     if(num<=0){
     printf("\n\n\t¦ý¬O¥Ø«e¨S¦³¸ê®Æ£»_£»\n\n\n");
     printf("ùÝùùùß ¢~¢w¢w¢w¢w¢w¢w¢w¢w¢w¢w¢w¢w¢w¢w¢¡\n");
     printf("ùø©¯ùø ¢x¡¼¡¼¡¼¡¼¡¼¡¼¡¼¡¼¡¼¡¼¡¼¡¼¡¼¡¼¡U\n");
     printf("ùøºÖùø¢y¢i¢h¢g¢f¢e¢d ©¯ ºÖ ¢d¢f¢g¢h¢i¡U¡@\n");
     printf("ùø¤½ùø¢y¡¯¡¯¡¯¢« §ÖÂI¤W¨®§a!¢¨¡¯¡¯¡¯ ¡U\n");
     printf("ùø¨®ùø¢yÝÝ  ¤j®a³£¤@©w­n§Ö¼Ö­òÝÝ ¡U¡Z\n");
     printf("ùãùùùå¢y ¡¼¡¼¡¼¡¼¡¼¡¼¡¼¡¼¡¼¡¼¡¼¡¼¡¼¡¼¡U¡@¡ã¡ã\n");
     printf("¡@ùø¡@ ¢¢¢w¢w¢w¡·¡·¢w¢w¢w¢w¢w¡·¡·¢w¢w¢£¢v\n");
     system("pause");
     }else{
           do{
              system("cls");
              printf("\t\t    ¢~¢w¡s¡Ä¡Ä¡s¢w¢¡¢~¢w¡s¡Ä¡Ä¡s¢w¢¡\n");
              printf("\t\t¡¯¡@¢x¡q=¡Ð¡o¡Ð=¡r¢x¢x¡q=¡Ð¡o¡Ð=¡r¢x¡@¡¯\n");
              printf("\t\t¡G¢~ùù¢Ýùùùùùùùù¢Ýùùùù¢Ýùùùùùùùù¢Ýùù¢¡¡G\n");
              printf("\t\t¡¯ùø¡¯¡@1.¡ã¡@¡¯¡@¡¯¡@¡¯¡@¡@¡¯¡@¡@¡¯ùø¡¯\n");
              printf("\t\t¡Gùø¡@¡@¡@¡¯¡@Áä¤J¦¡¬d¸ß¡ã¡¯¡@¡@¡@¡@ùø¡G\n");
              printf("\t\t¡¯ùø¡@  2.¡ã¡@¡@¡¯¡@¡@¡¯¡@¡@¡@¡@¡¯¡@ùø¡¯\n");
              printf("\t\t¡Gùø¡@¡@¡@¡¯¡@¦Cªí¦¡¬d¸ß¡ã¡¯¡@¡@¡@¡@ùø¡G\n");
              printf("\t\t¡¯ùø¡@  3.¡ã¡@¡@¡¯¡@¡@¡¯¡@¡@¡@¡@¡¯¡@ùø¡¯\n");
              printf("\t\t¡Gùø¡@¡@¡@¡¯¡@Â÷¶}  ¬d¸ß¡ã¡¯¡@¡@¡@¡@ùø¡G\n");
              printf("\t\t¡¯¢¢ùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùù¢£¡¯\n\t\t\t\t   ");
              scanf("%c",&ch);
              fflush(stdin);
           }while(ch!='1'&&ch!='2'&&ch!='3');
           //¦]¬°¥u¦³¿é¤J¥¿½Tªº¼Æ¦r¤~·|Â÷¶}°j°é
           //©Ò¥HÂ÷¶}°j°é«ách¤£¬O1¡B2¨º´N¤@©w¬O3 
            /*Å²¤J¦¡¬d¸ß*/ 
           if(ch=='1'){
               keyin();
           /*¦Cªí¥Ü¬d¸ß*/
           }else if(ch=='2'){
               list();
            //Åã¥Ü§â­È¦^¶Ç 
           } 
     }
}
/*key in Áä¤J¦¡*/
void keyin(void){
     char a[1000],ch2; //aÀx¦s¨Ï¥ÎªÌÁä¤J±ý¬d¸ß¤§¦WºÙ ,ch¦sÀx¨Ï¥ÎªÌªº¨M©w 
     int quit=0,i=0,k;//quit=1¥Nªí¦³¬d¨ì¸ê®Æ,quit=0¥Nªí¨S¦³¬d¨ì¸ê®Æ
               printf("\t\t\t  ½Ð¿é¤J±ý¬d¸ß¦WºÙ:\n\n \t\t\t\t");
               scanf("%[^\n]",&a);
               fflush(stdin);
               do{/*¤ñ¸û¦r¦êªº­È­Y¬Ûµ¥¥Nªí¦WºÙ¬Û¦P,­Y¬d¨ì¬Û¦P«hÅã¥Ü*/
                  if(strcmp(a,stu[i-1].name)==0){
                  system("cls");
                  printf("\n\n");
                  show(stu[i-1],-2,-2); //³æµ§Åã¥Ü,int -2 ¬°³æµ§Åã¥Ü±±¨î­È 
                  printf("\n\n");
                  quit=1;
                  }
                  i++;
               }while(i<=num&&quit==0); //ª½¨ì¨S¸ê®Æor¬d¨ì¸ê®Æ¬°¤î ... 
               if(quit==0)
                  printf("¬dµL¸ê®Æ\n");
               system("pause");
}
/***show Åã¥Ü***/
void show(STU data,int j,int k){
     int i;
     char compare[38]="1234567890123456789012345678"; //¥Î¥H¤ñ¸û 
       printf("¡C¡K¡K¡C¡K¡C¡K¡C¡K¡C¡K¡C¡K¡C¡K¡C¡K¡C¡K¡C ¡K¡C¡K¡C¡K¡C¡K¡C¡K¡C\n");
       if(j!=-2)
          printf("²Ä%02dµ§ :\t¦WºÙ : %-17s²Ä%d¥@¬ö¤ß²z¾Ç®a\n",j+1,data.name,data.Century);
       else
          printf("\t\t¦WºÙ : %-17s²Ä%d¥@¬ö¤ß²z¾Ç®a\n",data.name,data.Century);
       if(k==-2){
          printf("\t\t¥Í©ó : ¦è¤¸%d %05.2f\t",data.score_start_year,data.score_start_MD);
          printf("¨ò©ó : ¦è¤¸%d %05.2f\n",data.score_end_year,data.socre_end_MD);
          printf("\t\t°êÄy : %-15s",data.Nationality);
          printf("\t¾Ç¬£ : %-15s\n",data.school);
          printf("\t\t¨­¥÷ : ");
          if(strcmp(data.status,compare)>0){
            for(i=0;i<34;i++)
              printf("%c",data.status[i]);
            printf(" ...\n");
          }else
            printf("%-15s\n",data.status);
       }
       printf("¡C¡K¡K¡C¡K¡C¡K¡C¡K¡C¡K¡C¡K¡C¡K¡C¡K¡C¡K¡C ¡K¡C¡K¡C¡K¡C¡K¡C¡K¡C\n");
}

/***revise data  §ó§ï¸ê®Æ***/
void revise_data(void){
     char ch0,ch2;
     int n1,n2;
     system("cls");
     if(num==0){
          printf("\n\n\t¥Ø«e¨S¦³¸ê®Æ\n\t  µL±q§ó·s\n\n\n");
     }else{
            n1 = Display_data(1);        
          if(n1>=0&&n1<=9){
          do{
             system("cls");
             show(stu[n1-1],-2,-2);
             printf("\t¢z¡C¡E¡C¡E¡C¡E¡C¡E¡C¡E¡C¡E¡C¡E¡C¡E¡C¡E¡C¢{\n\n");
             printf("\t¡E\t½Ð¿ï¾Ü±z·Q§ó§ïªº¸ê®Æ¶µ¥Ø\t¡E\n\n");
             printf("\t¢z¡C¡E¡C¡E¡C¡E¡C¡E¡C¡E¡C¡E¡C¡E¡C¡E¡C¡E¡C¢{\n\n");
             printf("\t¡C\t\t1.­×§ï : ¦WºÙ\t\t¡C\n");
             printf("\t¡E\t\t2.­×§ï : ¥Í¨ò¦~\t\t¡E\n");
             printf("\t¡C\t\t3.­×§ï : °êÄy\t\t¡C\n");
             printf("\t¡E\t\t4.­×§ï : ¾Ç¬£\t\t¡E\n");
             printf("\t¡C\t\t5.­×§ï : ¨­¥÷\t\t¡C\n");
             printf("\t¡E\t\t6.Â÷¶}\t\t\t¡E\n\n");
             printf("\t¢|¡C¡E¡C¡E¡C¡E¡C¡E¡C¡E¡C¡E¡C¡E¡C¡E¡C¡E¡C¢}\n\n");
             printf("\t¡E\t\t½Ð¿é¤J 1 ~ 6\t\t¡E\n\n");
             printf("\t¢|¡C¡E¡C¡E¡C¡E¡C¡E¡C¡E¡C¡E¡C¡E¡C¡E¡C¡E¡C¢}\n\t\t\t\t");
             scanf("%c",&ch2);
             fflush(stdin);
             n2=ch2-'0';
              switch(n2){
                 case 1:  /*­×§ï¦WºÙ*/
                          ch0 = Inname(n1,1); 
                      break;
                 case 2:  /*­×§ï¥Í¨ò¦~*/ 
                          ch0 = InBD(n1,1);
                      break;
                 case 3:  /*­×§ï°êÄy*/
                          ch0 = InNationality(n1,1);
                      break;
                 case 4:  /*­×§ï¾Ç¬£*/
                          ch0 = Inschool(n1,1);
                      break;
                 case 5:  /*­×§ï¨­¥÷*/
                          ch0 = Instatus(n1,1); 
                      break;
                 case 6:  /*Â÷¶}*/ 
                          ch0 = 'e';
                      break;
                 default :  printf("¿é¤J¿ù»~");
                            system("pause");
                            ch0='n';
              }
          }while(ch0!='e');  //e = esc  «¢«¢XD 
          }
     }
}

/* Insert name */
char Inname(int n,const int swi){
     char ch,ch2;
     char name[100];//¼È¦s name ªºªÅ¶¡ 
     int k;  //°j°é§PÂ_¤§¨Ï¥Î¨ç¼Æ 
     system("cls");
     printf("½Ð¿é¤J¾ÇªÌ¦WºÙ:\n");
     scanf("%[^\n]",name);
     fflush(stdin); // ²M²z Àx¦s¦ì¸m
     if(swi==0){
         strcpy(stu[n-1].name,name);
     }else{
         system("cls");
         printf("½Ð°Ý½T©w§ó°µ¥H¤U§ó§ï¶Ü?[Y/N]\n\n");
         printf("­ì¥»:\n\t%s\n",stu[n-1].name);
         printf("§ó§ï¬°:\n\t%s\n",name);
         scanf("%c",&ch);
         fflush(stdin); // ²M²z Àx¦s¦ì¸m
         do{
            if(ch=='y'){
               strcpy(stu[n-1].name,name);
               printf(" ¦¹¸ê®Æ§ó·s§¹²¦\n");
               k=1;
            }else if(ch=='n'){
               printf("¦¹¸ê®Æ±N¤£¤©§ó·s \n");
               k=1;
            }else{
               printf("¿é¤J¿ù»~\n");
               k=0;
            }
         }while(k!=1);
         do{
            system("cls");             
            printf("\n­n¦^¥Dµe­±½Ð«ö'Q'\n\n");
            printf("­YÄ~Äò¾Þ§@½Ð«ö'n'\n\n");
            scanf("%c",&ch2);
            fflush(stdin); // ²M²z Àx¦s¦ì¸m
            if(ch2=='q'||ch2=='Q')
                return 'e';
            else if(ch2=='n'||ch2=='N')
                return 'n';
            else{
                printf("¿é¤J¿ù»~\n\n");
                k=0;
            }
         }while(k!=1);
     }
     return 'e';
}

/* Insert Birth and Death  => ´¡¤J¥Í¨ò¦~ */
char InBD(int n,const int swi){
     int start_year;     //¼È¦s start_year ªºªÅ¶¡
     double start_MD;    //¼È¦s start_MD ªºªÅ¶¡
     int end_year;       //¼È¦s end_year ªºªÅ¶¡
     int cent;        //¼È¦s Century ªºªÅ¶¡            
     double end_MD;      //¼È¦s end_MD ªºªÅ¶¡
     char ch,ch2;
     int k;              //°j°é§PÂ_¤§¨Ï¥Î¨ç¼Æ 
     do{
         system("cls");
         printf("½Ð¿é¤J¸Ó¾ÇªÌ¬°­þ­Ó¥Í©ó¥@¬ö[10~22]:\n");
         while(scanf("%d",&cent)==0){//§PÂ_¨Ï¥ÎªÌ¬O§_¿é¤J¼Æ¦r 
         fflush(stdin); // ²M²z Àx¦s¦ì¸m
         printf("¿é¤Jªº¤£¬O¼Æ¦r\n½Ð±q·s¿é¤J");
         }
     }while(cent>22||cent<10);  //­Y¿é¤J¤£¬O¸Ó½d³ò«h«ùÄò¸ß°Ý
     system("cls");
     
     do{
        system("cls");
        printf("½Ð¿é¤J¾ÇªÌ¥X¥Íªº¦~¥N(¦è¤¸¦~):\n");
        while(scanf("%d",&start_year)==0){ 
        fflush(stdin); // ²M²z Àx¦s¦ì¸m
        printf("¿é¤Jªº¤£¬O¼Æ¦r\n½Ð±q·s¿é¤J");
        }
     }while(start_year<(cent-1)*100+1||start_year>cent*100);//§PÂ_¬O§_¬°¸Ó¥@¬ö 
     printf("½Ð¿é¤J");
     while(1){    
          printf("¾ÇªÌ¥X¥Íªº¤ë¥÷.¤é´Á\n");
          while(scanf("%lf",&start_MD)==0){
          fflush(stdin); // ²M²z Àx¦s¦ì¸m
          printf("¿é¤Jªº¤£¬O¼Æ¦r\n½Ð±q·s¿é¤J");
          printf("¾ÇªÌ¥X¥Íªº¤ë¥÷.¤é´Á\n");
          }
          //==§PÂ_¤ë¥÷/¤é´Á========= 
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
            printf("¿é¤J¿ù»~!\n½Ð±q·s¿é¤J");    
     }
     printf("½Ð¿é¤J");
     while(1){ 
       printf("¾ÇªÌ³u¥@ªº¦~¥N(¦è¤¸¦~)\n");
       while(scanf("%d",&end_year)==0){
       fflush(stdin); // ²M²z Àx¦s¦ì¸m
       printf("¿é¤Jªº¤£¬O¼Æ¦r\n½Ð±q·s¿é¤J");
       printf("¾ÇªÌ³u¥@ªº¦~¥N(¦è¤¸¦~)\n");
       }
       if(end_year<=start_year)
          printf("³u¥@¦~¥N¤£¦X²z!\n½Ð±q·s¿é¤J");
       else
          break;    //¥¿½T«á¸õ¥X¦^°éÄ~Äò°õ¦æ      
     }
     printf("½Ð¿é¤J");
     while(1){ 
       printf("¾ÇªÌ³u¥@ªº¤ë¥÷.¤é´Á\n");
       while(scanf("%lf",&end_MD)==0){
       fflush(stdin); // ²M²z Àx¦s¦ì¸m
       printf("¿é¤Jªº¤£¬O¼Æ¦r\n½Ð±q·s¿é¤J");
       printf("¾ÇªÌ³u¥@ªº¤ë¥÷.¤é´Á\n");
       }
       //==§PÂ_¤ë¥÷/¤é´Á========== 
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
          break; //¥¿½T«á¸õ¥X°j°éÄ~Äò°õ¦æ 
       else
          printf("¿é¤J¿ù»~!\n½Ð±q·s¿é¤J");    
     }
     if(swi==0){  //swi=0¬°Insert¤§¥\¯à 
        /*¬°·s¼W¤ºªº¥\¯à*/
        stu[n-1].score_start_year = start_year;
        stu[n-1].score_start_MD=start_MD;
        stu[n-1].score_end_year=end_year;              
        stu[n-1].socre_end_MD=end_MD;
        /*§PÂ_¬°¦¹¾ÇªÌ¥Í©ó´X¥@¬ö*/
        stu[n-1].Century = cent;
     }else{     //swi!=0¬°§ó·s¤§¥\¯à 
         system("cls");
         printf("½Ð°Ý½T©w§ó°µ¥H¤U§ó§ï¶Ü?[Y/N]\n\n");
         printf("­ì¥»:\n\t%d¥@¬öªº¾ÇªÌ",stu[n-1].Century);
         printf("\n\t¥Í©ó¦~%4d ",stu[n-1].score_start_year);
         printf("%04.2lf\n",stu[n-1].score_start_MD);
         printf("\t¨ò©ó¦~%4d ",stu[n-1].score_end_year);
         printf("%04.2lf\n\n",stu[n-1].socre_end_MD);
         printf("§ó§ï¬°:\n\t%d¥@¬öªº¾ÇªÌ",cent);
         printf("\n\t¥Í©ó¦~%4d ",start_year);
         printf("%04.2lf\n",start_MD);
         printf("\t¨ò©ó¦~%4d ",end_year);
         printf("%04.2lf\n",end_MD);
         scanf("%c",&ch);
         fflush(stdin); // ²M²z Àx¦s¦ì¸m
         do{
            system("cls");
            if(ch=='y'||ch=='Y'){  //±N¸ê®Æ¦s¤J 
               stu[n-1].score_start_year = start_year;
               stu[n-1].score_start_MD=start_MD;
               stu[n-1].score_end_year=end_year;              
               stu[n-1].socre_end_MD=end_MD;
               stu[n-1].Century = cent;
               /*§PÂ_¬°¦¹¾ÇªÌ¥Í©ó´X¥@¬ö*/
               printf(" ¦¹¸ê®Æ§ó·s§¹²¦\n");
               k=1;
            }else if(ch=='n'||ch=='N'){
               printf("¦¹¸ê®Æ±N¤£¤©§ó·s \n");
               k=1;
            }else{
               printf("¿é¤J¿ù»~\n");
               k=0;
            }
         }while(k!=1);
         do{
            system("cls");
            printf("­n¦^¥Dµe­±½Ð«ö'Q'\n\n");
            printf("­YÄ~Äò¾Þ§@½Ð«ö'n'\n\n");
            scanf("%c",&ch2);
            fflush(stdin); // ²M²z Àx¦s¦ì¸m
            if(ch2=='q'||ch2=='Q')
               return 'e';
            else if(ch2=='n'||ch2=='N')
               return 'n';
            else{
               printf("¿é¤J¿ù»~\n");
               k=0;
            }
         }while(k!=1);
     }
     return 'e';
}

/* Insert Nationality */
char InNationality(int n,const int swi){
     char ch,ch2;
     char Nationality[30];   //¼È¦s Nationality ªºªÅ¶¡
     int k;                  //°j°é§PÂ_¤§¨Ï¥Î¨ç¼Æ 
     system("cls");
     printf("½Ð¿é¤J¾ÇªÌªº°êÄy:\n");
     scanf("%[^\n]",Nationality);
     fflush(stdin); // ²M²z Àx¦s¦ì¸m
     if(swi==0){
         strcpy(stu[n-1].Nationality,Nationality);
     }else{
        do{
           system("cls");
           printf("½Ð°Ý½T©w§ó°µ¥H¤U§ó§ï¶Ü?[Y/N]\n\n");
           printf("­ì¥»:\n\t%s\n",stu[n-1].Nationality);
           printf("§ó§ï¬°:\n\t%s\n",Nationality);
           scanf("%c",&ch);
           fflush(stdin); // ²M²z Àx¦s¦ì¸m
           if(ch=='y'){
              strcpy(stu[n-1].Nationality,Nationality);
              printf("¦¹¸ê®Æ§ó·s§¹²¦\n");
              k=1;
           }else if(ch=='n'){
              printf("¦¹¸ê®Æ±N¤£¤©§ó·s\n");
              k=1;
           }else{
              printf("¿é¤J¿ù»~\n");
              k=0;
           }
        }while(k!=1);
        do{
           system("cls");
           printf("\n­n¦^¥Dµe­±½Ð«ö'Q'\n");
           printf("­YÄ~Äò¾Þ§@½Ð«ö'n'\n");
           scanf("%c",&ch2);
           fflush(stdin); // ²M²z Àx¦s¦ì¸m
           if(ch2=='q'||ch2=='Q')
              return 'e';
           else if(ch2=='n'||ch2=='N')
              return 'n';
           else{
              printf("¿é¤J¿ù»~\n"); 
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
     char school[50];   //¼È¦s school ªºªÅ¶¡
     int k;             //°j°é§PÂ_¤§¨Ï¥Î¨ç¼Æ 
     system("cls");
     printf("½Ð¿é¤J¾ÇªÌªº¾Ç¬£:\n");
     scanf("%[^\n]",school);
     fflush(stdin); // ²M²z Àx¦s¦ì¸m 
     if(swi==0){
         strcpy(stu[n-1].school,school);
     }else{
         printf("½Ð°Ý½T©w§ó°µ¥H¤U§ó§ï¶Ü?[Y/N]\n\n");
         printf("­ì¥»:\n\t%s\n",stu[n-1].school);
         printf("§ó§ï¬°:\n\t%s\n",school);
         scanf("%c",&ch);
         fflush(stdin); // ²M²z Àx¦s¦ì¸m
         do{
            if(ch=='y'){
               strcpy(stu[n-1].school,school);
               printf("¦¹¸ê®Æ§ó·s§¹²¦\n");
               k=1;
            }else if(ch=='n'){
               printf("¦¹¸ê®Æ±N¤£¤©§ó·s\n");
               k=1;
            }else{
               printf("¿é¤J¿ù»~\n");
               k=0;
            }
         }while(k=!1);
         do{
            system("cls");
            printf("­n¦^¥Dµe­±½Ð«ö'Q'\n");
            printf("­YÄ~Äò¾Þ§@½Ð«ö'n'\n");
            scanf("%c",&ch2);
            fflush(stdin); // ²M²z Àx¦s¦ì¸m
            if(ch2=='q'||ch2=='Q')
               return 'e';
            else if(ch2=='n'||ch2=='N')
               return 'n';
            else{
               printf("¿é¤J¿ù»~\n"); 
               k=0;
            }
         }while(k=!1);
     } 
     return 'n';
}
/* Insert status */
char Instatus(int n,const int swi){
     char ch,ch2;
     char status[100];  //¼È¦s status ªºªÅ¶¡
     int k;             //°j°é§PÂ_¤§¨Ï¥Î¨ç¼Æ 
     system("cls");
     printf("½Ð¿é¤J¾ÇªÌªº¨­¥÷:\n");
     scanf("%[^\n]",status);
     fflush(stdin); // ²M²z Àx¦s¦ì¸m
     if(swi==0){
         strcpy(stu[n-1].status,status);
     }else{
         do{
            system("cls");
            printf("½Ð°Ý½T©w§ó°µ¥H¤U§ó§ï¶Ü[Y/N]?\n\n");
            printf("­ì¥»:\n\t%s\n",stu[n-1].status);
            printf("§ó§ï¬°:\n\t%s\n",status);
            scanf("%c",&ch);
            fflush(stdin); // ²M²z Àx¦s¦ì¸m
            if(ch=='y'){
               strcpy(stu[n-1].status,status);
               printf("¦¹¸ê®Æ§ó·s§¹²¦\n");
               k=1;
            }else if(ch=='n'){
               printf("¦¹¸ê®Æ±N¤£¤©§ó·s\n");
               k=1;
            }else{
               printf("¿é¤J¿ù»~\n");
               k=0;
            }
         }while(k!=1);
         do{
            system("cls");
            printf("­n¦^¥Dµe­±½Ð«ö'Q'\n");
            printf("­YÄ~Äò¾Þ§@½Ð«ö'n'\n");
            scanf("%c",&ch2);
            fflush(stdin); // ²M²z Àx¦s¦ì¸m
            if(ch2=='q'||ch2=='Q')
               return 'e';
            else if(ch2=='n'||ch2=='N')
               return 'n';
            else{
               printf("¿é¤J¿ù»~\n");
               k=0;
            }
         }while(k!=1); 
     } 
     return 'e';
}

/*sequence ±Æ§Ç */
void sequence(void){
     /*±N¸û¦­¥X¥Íªº¾ÇªÌ±Æ¨ì«e­±*/     
     int i;
     if(num>=2)
       for(i=0;i<(num-1);i++){ 
         if(stu[i].score_start_year!=stu[i+1].score_start_year){
         //¦pªG¤U¤@­Óªº¥X¥Í¦~¸ò¤W¤@­Ó¤£¦P 
           if(stu[i].score_start_year>stu[i+1].score_start_year){
           //¥B,¤W¤@­Óªº¥X¥Í¦~¥N¤j©ó¤U¤@­Óªº¥X¥Í¦~¥N,«h±N¨âµ§¸ê®Æ¥æ´«
              change(i,i+1);
           }
         }else{//¦pªG¤W¤@­Ó»P¤U¤@­Ó¥X¥Í¦~¥N¬Û¦P
           if(stu[i].score_start_MD<stu[i+1].score_start_MD){
           //¥B¤W¤@­Óªº¥X¥Í¤ë¥÷¤ñ¸û¦­(¤p),«h±N¨âµ§¸ê®Æ¥æ´«
              change(i,i+1); 
          } 
       }
     }                   
}

/*change ¥æ´«*/
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
     /*±Naªº­È©ñ¦Ü¼È¦sªÅ¶¡*/
     strcpy(name,stu[a].name);
     strcpy(Nationality,stu[a].Nationality);
     strcpy(school,stu[a].school);
     strcpy(status,stu[a].status);
     Century     = stu[a].Century;
     start_year  = stu[a].score_start_year;
     start_MD    = stu[a].score_start_MD;
     end_year    = stu[a].score_end_year;
     end_MD      = stu[a].socre_end_MD;
     /*±Nb¦s¨ìa*/
     strcpy(stu[a].name,stu[b].name);
     strcpy(stu[a].Nationality,stu[b].Nationality);
     strcpy(stu[a].school,stu[b].school);
     strcpy(stu[a].status,stu[b].status);
     stu[a].Century           = stu[b].Century;
     stu[a].score_start_year  = stu[b].score_start_year;
     stu[a].score_start_MD    = stu[b].score_start_MD;
     stu[a].score_end_year    = stu[b].score_end_year;
     stu[a].socre_end_MD      = stu[b].socre_end_MD;
     /*±N¼È¦s¦s¨ìb*/
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

/* open data ¶}±Ò¨ÃÅª¨ú.txt¤ºªº¸ê®Æ*/
void opendata(const char swi){
    int temp;
	char msg[30];
	system("cls");
	printf("¶}±Ò¤¤ ...\n");
	/*¶}±ÒÀÉ®×*/
    if(swi=='r')//¦pªG±±¨î°Ñ¼Æ¬°'r'«h¨Ï¥Îrb+¥´¶}¸ê®Æ 
	   if((fp=fopen(filename,"rb+"))==NULL){
		  printf("\n\nµLªk¶}±ÒÀÉ®× : %s\n\n",filename);
		  exit(0);
	   }
    if(swi=='w')//¦pªG±±¨î°Ñ¼Æ¬°'w'«h¨Ï¥Îwb+¥´¶}¸ê®Æ 
       if((fp=fopen(filename,"wb+"))==NULL){
		  printf("\n\nµLªk¶}±ÒÀÉ®× : %s\n\n",filename);
		  exit(0);
	   }
	fseek(fp,0,SEEK_SET);
	/*Åª¨ú¸ê®Æ*/ 
	while(fscanf(fp,"%s",stu[num].name)!=EOF){
		fscanf(fp,"%s",stu[num].Nationality); //Åª°êÄy
        fscanf(fp,"%s",stu[num].school);      //Åª¾Ç¬£ 
        fscanf(fp,"%s",stu[num].status);      //Åª¨­¤À
        fscanf(fp,"%s",msg);
        stu[num].Century=atoi(msg);           //Åª¥@¬ö 
        fscanf(fp,"%s",msg);
		stu[num].score_start_year=atoi(msg);  //Åª¥Í©ó¦~ 
		fscanf(fp,"%s",msg);
		stu[num].score_start_MD=atof(msg);    //Åª¥Í¤ë.¤é 
		fscanf(fp,"%s",msg);
		stu[num].score_end_year=atoi(msg);    //Åª¨ò©ó¦~ 
		fscanf(fp,"%s",msg);
		stu[num].socre_end_MD=atof(msg);      //Åª¨ò¤ë.¤é
        num++;
	}
}

/* closr data Ãö³¬¨ÃÀx¦s¸ê®Æ¶i¤Jdatabase.txt */
void closedata(){
	int i;
	/*§Q¥Î°j°é±N°}¦Cªº¸ê®Æ¦s¦^ÀÉ®×¸Ì*/
	fseek(fp,0,SEEK_SET);
	system("cls");
	printf("Ãö³¬¤¤ ...\n");
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
	/*Ãö³¬ÀÉ®×*/
	if(fclose(fp)!=0){
		printf("µLªkÃö³¬ÀÉ®× : %s\n",filename);
		exit(0);
		} 
	} 
