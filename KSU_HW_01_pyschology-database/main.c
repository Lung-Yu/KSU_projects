/********4*0*1*0*E*1*0*5**��**�s**��****��**�z**��**�a**��**��**�w********/
 
/*�祫�w�ŧi*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "ture_c.h"  //�I�s�ۤv�w�q�����c 
/*�]�w�����ܼ�*/
FILE *fp; //�ɮת����� 
STU stu[99];
int num=0;

/*�ŧi�ƨ禡*/ 
char User_Interface(const int); //��� int=�ϥΪ̿��~����,char�^�ǨϥΪ̿�ܤ����O 
void opendata(const char);    //�}�Ҩ�Ū��psychologydata.txt�����,char:'r'=rb+,'w'=wb+
void closedata(void);   //�������x�s��Ʀ�psychologydata.txt
void Insert_data(void);      //�s�W Insret
void Inquiry_data(void);     //�d�� inquury 
void delete_data(void);      //�R�� delete
void revise_data(void);      //��� revise
void data_amount(void);      //��Ƽƶq amount  
void quit(void);             //���} quit
int Display_data(const int);//���,int=0���D�{����ܨϥ�,
//int=1�h����s����ܥ\��,int=2�h���R��,int=3�h���d�ߤ��C��d�k 
void show(STU data,int,int);     //��ܳ浧���,�Yint = -2 �h�����ܸӸ�� 
int clear(int);              //clear data
char Inname(int,const int);  //Insert�W��int=��m,int=0�h��Insert���\�� 
char InBD(int,const int);    //Insert Birth and Death  => Insert�ͨ�~
char InNationality(int,const int); //Insert���yint=��m,int=0�h��Insert���\�� 
char Inschool(int,const int);//Insert�Ǭ�int=��m,int=0�h��Insert���\�� 
char Instatus(int,const int);//Insert����int=��m,int=0�h��Insert���\�� 
void keyin(void);            //��J��
void list(void);             //�C�|�� 
void sequence(void);         //�Ƨ�
void change(int,int);            //�N�ⵧ��ƥ洫 
/*** main �D�{������ ***/ 
int main(){
    char bee='\07'; //��J���~��Bee�@�n 
    int k=0,quit_m=0;//K�p����~����,quit_m=1�����},quit_m=0�����~�ӹL�h���X 
    opendata('r'); //�}��.txt�� =>��rb+ 
    closedata(); 
    do{
       sequence(); //�ƶ���  ���C���@�Ӱʧ@���|�Ƥ@�� 
       system("cls");
       switch(User_Interface(k)){
          case 'I':  //�s�W���_OK 
          case 'i':  k=0;
                     Insert_data(); 
                break;
          case 'D':  //�R�����_OK 
          case 'd':  k=0;
                     delete_data();
               break;
          case 'Q':  //�d�߸��_OK 
          case 'q':  k=0;
                     Inquiry_data();
               break;
          case 'M':  //��ܸ��_ok
          case 'm':  k=0;
                     Display_data(0);
               break;
          case 'E':  //���}_OK 
          case 'e':  quit();
                     quit_m=1;
               break;
          case 'K':  //��ܸ�Ƽƶq_OK 
          case 'k':  k=0;
                     data_amount();
               break;
          case 'R' : //��s_OK 
          case 'r' : k=0;
         	         revise_data(); 
               break;
          default :system("cls");
                   printf("�z��J���~�����O!�бq�s��J\n");
                   printf("%c",bee); 
                   system("pause");
                   k++;
       }    
    }while(k<5&&quit_m==0);
    system("cls");
    if(quit==0){
       printf("��J���~�W�L5��");
       system("pause");
    }
    opendata('w'); //�}��.txt�� =>��w+ 
    closedata(); //���� .txt�� 
    return 0;
    }
    
/***User Interface �ϥΪ̤���***/
char User_Interface(const int a){
       char ch;
       system("cls");
           printf("��X�O���X�D�O�X�O�X�E�X��X�E�X���C+�ݡE�Xo�E�Xo�D�O�E�X��X�D�O�E�X");
           printf("\n���i�i�i�i�i�i�i�i�i�i�i�i�i�i�i�i�i�i�i�i�i��");
           printf("\n�@�@�@�i�@�@�@�@�@ ���п�ܱz���u�@�����ء�   �@�@�i");
           printf("\n�@�i���i���i�@�@          (I)  �[�J�s���       �@�i");
           printf("\n�����w�i��Ţ��@�@�@�@�@�@(D)  �R�����   �@�@�@�@�i");
           printf("\n���i���i���i���@�@�@�@�@�@(Q)  �d�߸�� �@�@�@�@�@�i");
           printf("\n�@�@�@�i�@�@�@�@�@�@�@�@�@(M)  ��ܸ��     �@�@�@�i");
           printf("\n�@�@�@�i�@�@�@�@�@�@�@�@�@(R)  �����           �i");
           printf("\n�@�@�@�i�@�@�@�@�@�@�@�@  (K)  ��ܸ�Ƽƥ�   �@�@�i");
           printf("\n�@�@�@�i�@���i�����i���@�@(E)   ��   �}       �@�@�i�@");
           printf("\n�@�@�@�i�@�i�@�����@�i�@���@�@���@�@���@�@���@�@�@�i");
           printf("\n�@�@�@�i���i�i�����i�i���@�@�@�@�@�@�@�@�@�@�@�i���i���i");
           if(a==0)
           printf("\n�@�@�@�i�i���i�i�i�i���i                    ���ŨϢi�ΡŢ�");
           else
           printf("\n�@�@�@�i�i���i�i�i�i���i     ��J���~%d��    ���ŨϢi�ΡŢ�",a);
           printf("\n�@�@�@�i���i�i�����i�i���@�@�@�@�@�@�@�@�@�@���i���i���i��");
           printf("\n�@�@�@�i���i�i�i�i�i�i���@�@���@�@���@�@���@�@�@�@�i");
           printf("\n�@�@�@���i�i�i�i�i�i�i�i�i�i�i�i�i\n");
           printf("��X�O���X�D�O�X�O�X�E�X��X�E�X���C+�ݡE�Xo�E�Xo�D�O�E�X��X�D�O�E�X\n\n");
        printf("\t��  ��  �J  : \t");
        scanf("%c",&ch);
        fflush(stdin); // �M�z �x�s��m 
        return ch;
}

/***Insert data���J(���)***/
void Insert_data(void){
    char yorn;
     system("cls");
     num++;        //num++ �N�s���Ŷ��ťX��
  if(num>=99){     //�P�_��ƶq�O�_�w�� 
     printf("��Ʈw�w��\n");
  }else{
        Inname(num,0); // num,int=0�h��Insert���\�� 
        InBD(num,0);   //Insert Birth and Death  => ���J�ͨ�~
        InNationality(num,0);
        Inschool(num,0);
        Instatus(num,0);
        /*�T�{����J�����*/
       system("cls"); //�M���e�� 
       printf("�нT�{��J��ƬO�_�L�~\n");
       show(stu[num-1],-2,-2);
       printf("�O�_�s�J?(Y/N) : \n");
       printf("������������������������\n");
       printf("�@�@�@�@�@�~�ᢡ�@�@�@�@�@�@�@�@�@ ��\n");
       printf("�b�~�f�g�h�����i�h�g�f�e�d�c�b(��)�i��\n");
       printf("�@�����d__�b�b�b�b�b�����������v�v�v�@\n");
       printf("�@�@�@�@��O �@ ��O\n");
       do{              // do...while �ְ̤���@�� 
          scanf("%c",&yorn);
          fflush(stdin); // �M�z �x�s��m
          if((yorn=='y')||(yorn=='Y')) //yes �N����x�s
                 printf("�w�N����x�s,���±z�ϥ�\n");
          else if((yorn=='n')||(yorn=='N')){ //no �N��Ʈ��� 
                printf("������ƱN���|�Q�s�J\n");
                printf("���¨ϥ�\n");
                num--;  //�N���s�W����ƪŶ��k��,�åB��������� 
          }else{
                yorn = 'r';   //�O�P�_ yorn ='r' �ϰj���~�����  
                printf("��J���~\n"); 
                printf("�п�J (Y/N) : ");
          } 
       }while(yorn=='r'); //yorn == 'r' ���������
 }
 system("pause");
}
/*list �C���d��*/
void list(void){
     int n=0,k,temp;
     char ch,ch2='\0'; //ch2 �]�w�L�����Ȭ���l��,�H�K�j����~ 
          do{
             ch2='\0'; //�C�����n�N�ȵ��M�ŧ_�h����L�@���N���|�A�P�_�x�s 
             system("cls");
             do{ 
                 show(stu[n],n%7,-1);
                 n++;
             }while(n<num&&(n%7!=0));
             printf("\t\t\t��J���d�ݽs��\n");
             printf("\t�W�@���п�J\"p\"�U�@���п�J\"n\"���}�п�J\"q\"\n");
             scanf("%c",&ch);
             fflush(stdin);
             if(ch=='n'||ch=='N'){  //�U�@��
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
             }else if(ch=='p'||ch=='P'){ //�W�@��
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
             }else if(ch>='1'&&ch<='5'){ //�P�_��J�O�_��1~5���Ʀr 
                k=ch -'0';  //�NASC||�X���Ʀr�����@��Ʀr 
                temp = n/7; //�p�⭶�� 
                system("cls");
                printf("\t�z�d�ߪ���Ƭ� ....\n\n");
                if(n<=7) //�Y�u���@�� 
                show(stu[k-1],-2,-2); //�N�Ʀr�a�J�� - ��ܨ禡 
                else  //�Y���u�@�� 
                show(stu[((temp-1)*7)+k-1],-2,-2);//����*�@��5��+�ϥΪ̿�ܪ����ؼƦr 
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
/***data amount ��ƶq***/
void data_amount(){
     system("cls");
     printf("     �O���X�O���D�O���X�O���D�O���X\n");
     printf("������������������������������������������������\n");
     if(num==0)  //�S����ƫh��ܨS�� 
        printf("���ثe��������Ʈw�������S���������ơ����\n");
     else        //�_�h��ܸ�ƪ��ƶq                                     
        printf("���ثe�������w�g��������%d�㵧�������ơ����\n",num);
     printf("�㡷���������㡷���������㡷���������㡷��������\n");
     printf("�X�O���X�D�O�E�X��X�O�X�D�O�E�X��X.�O�E�X��X�O��\n\n");
     system("pause"); 
}

/***delete data �R�����***/
void delete_data(void){
     int n=0,k,temp;
     char ch,ch2='\0'; //ch2 �]�w�L�����Ȭ���l��,�H�K�j����~ 
     system("cls"); 
     if(num==0){
       printf("\n\n\t��Ʈw���L���\n\n");
       system("pause");
     }else{
          do{
            system("cls");
            ch2='\0'; //���w��l�� 
             do{ 
                 show(stu[n],n%5,-1);
                 n++;
             }while(n<num&&(n%5!=0));
             printf("\t\t\t��J���R���s��\n");
             printf("\t�W�@���п�J\"p\"�U�@���п�J\"n\"���}�п�J\"q\"\n");
             scanf("%c",&ch);
             fflush(stdin);
             if(ch=='n'||ch=='N'){  //�U�@��
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
             }else if(ch=='p'||ch=='P'){ //�W�@��
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
             }else if(ch>='1'&&ch<='5'){ //�P�_��J�O�_��1~5���Ʀr 
                k=ch -'0';  //�NASC||�X���Ʀr�����@��Ʀr 
                temp = n/5; //�p�⭶�� 
                system("cls");
                printf("�аݬO�_�R���������\n");
                if(n<=5) //�Y�u���@�� 
                show(stu[k-1],-2,-2); //�N�Ʀr�a�J�� - ��ܨ禡 
                else  //�Y���u�@�� 
                show(stu[((temp-1)*5)+k-1],-2,-2);//����*�@��5��+�ϥΪ̿�ܪ����ؼƦr 
                while(ch2!='n'&&ch2!='N'&&ch2!='Y'&&ch2!='y'){    
                printf("\n�п�J(Y/N):\t");     
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
                      printf("�z��J���~\n");
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

/***clear data �M�����***/     
int clear(int n){
     int i;
     printf("�R���� ....\n");
     /*�N�᭱����Ʃ��e�� ... */ 
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
     /*�N�̫�@����ƧR��*/
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

/***Display data ��ܸ��***/	
int Display_data(int che){
    int i=0; //�Ѽ� 
    int k;  //�N�ϥΪ̿�J��ASCII���Ʀr�ഫ���@��Ʀr�æs�J 
	char ctr;//��J�����O 
	system("cls");
    /*���*/
    if(num==0){ //�P�_��Ʈw���O�_����� 
	printf("\n\t�ثe��Ʈw���S�����\n\n\n");
	system("pause");
    }else{ 
	       do{
	          system("cls"); //�M���e��
	          if(che == 1)
                 printf("\t\t�нп�J������Ƥ����X?\n"); 
	          do{
                 if(che==1)
	               show(stu[i],i%3,-2);
	             else
	               show(stu[i],i,-2);
	             printf("\n");
	             i++;//  �����3��,�Y����3��h�������
                 }while(((i<num)&&((i%3)!=0)));	
           /*�P�_�W�@��,�U�@��,���}*/ 
	       if((i<=3)&&(i==num)){ //�u���@�� 
              do{
                 printf("���СC�СC�СC�СC�СC�СC�СC�СC�ССC�СC�СC�СC�СC�С�\n");
	             printf("\t\t\t���}�Ы�'q'\n\t\t\t   ",i);
		         scanf("%c",&ctr);
		         fflush(stdin);
		         if(che == 1)
                    if(ctr<='3'&&ctr>='1'){
                       k = ctr-'0';
                       return k;
                    }
              }while(ctr!='q'&&ctr!='Q');
           }else if((i<=3)&&(i!=num)){ //���Ĥ@�� 
              do{
                 printf("���СC�СC�СC�СC�СC�СC�СC�СC�ССC�СC�СC�СC�СC�С�\n");
			     printf("\t\t�U�@���Ы�\"n\"�A���}�Ы�\"q\"\n\t\t\t\t");
			     scanf("%c",&ctr);
			     fflush(stdin);
			     if(che == 1)
                    if(ctr<='3'&&ctr>='1'){
                       k=ctr-'0';
                       return k;
                    }
              }while((ctr!='q')&&(ctr!='n')&&(ctr!='Q')&&(ctr!='N'));
           }else if((i>3)&&(i!=num)){ //��󤤶��� 
              do{
                 printf("���СC�СC�СC�СC�СC�СC�СC�СC�ССC�СC�СC�СC�СC�С�\n");
			     printf("\t�e�@���Ы�\"p\"�A�U�@���Ы���\"n\"�A���}�Ы�\"q\"\n\t\t\t\t");
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
           }else{  //�̫�@�� 
              do{
                 printf("���СC�СC�СC�СC�СC�СC�СC�СC�ССC�СC�СC�СC�СC�С�\n");
			     printf("\t\t�e�@���Ы�\"p\"�A���}�Ы�\"q\"\n\t\t\t\t");
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

/***quit ���}***/ 
void quit(void){
     system("cls");
     printf("�P�±z���ϥ�\n");
     printf("\n�~���ġġ@�@�U���@�ġĢ~��");
     printf("\n�x�@�@�@�@�@�@�@�@�@�@�@�x");
     printf("\n�x�@�@�@�@�@�@�@�@�@�@�@�x");
     printf("\n�x�@�١@�~�w�w�w���@�ء@�x");
     printf("\n�x�������x���@���x�������x");
     printf("\n�x�@�@�@���w�w�w���@�@�@�x");
     printf("\n���w�w�s�ݢw�w�w�w�s�w�ݢ�");
     printf("\n�@�@�@�x�@�@�@�@�@�x");
     printf("\n�@�@���x �x");
     printf("\n�@�@���x�@�@�ݡ@�@�x");
     printf("\n�@�@�@���U�U�СU�U�� ");
     printf("\t��B��@��B��@...\n");	
     system("pause");
}

/***Inquiry data �d�߸��***/ 
void Inquiry_data(void){
     char ch;
     int j,page=0,n,temp; //�j����ܼ� 
     system("cls");
     if(num<=0){
     printf("\n\n\t���O�ثe�S����ƣ�_��\n\n\n");
     printf("������ �~�w�w�w�w�w�w�w�w�w�w�w�w�w�w��\n");
     printf("������ �x�����������������������������U\n");
     printf("�������y�i�h�g�f�e�d �� �� �d�f�g�h�i�U�@\n");
     printf("�������y�������� ���I�W���a!�������� �U\n");
     printf("�������y�ݝ�  �j�a���@�w�n�ּ֭�ݝ� �U�Z\n");
     printf("������y �����������������������������U�@���\n");
     printf("�@���@ ���w�w�w�����w�w�w�w�w�����w�w���v\n");
     system("pause");
     }else{
           do{
              system("cls");
              printf("\t\t    �~�w�s�ġġs�w���~�w�s�ġġs�w��\n");
              printf("\t\t���@�x�q=�Сo��=�r�x�x�q=�Сo��=�r�x�@��\n");
              printf("\t\t�G�~�����������������������������������G\n");
              printf("\t\t�������@1.��@���@���@���@�@���@�@������\n");
              printf("\t\t�G���@�@�@���@��J���d�ߡ㡯�@�@�@�@���G\n");
              printf("\t\t�����@  2.��@�@���@�@���@�@�@�@���@����\n");
              printf("\t\t�G���@�@�@���@�C���d�ߡ㡯�@�@�@�@���G\n");
              printf("\t\t�����@  3.��@�@���@�@���@�@�@�@���@����\n");
              printf("\t\t�G���@�@�@���@���}  �d�ߡ㡯�@�@�@�@���G\n");
              printf("\t\t����������������������������������������\n\t\t\t\t   ");
              scanf("%c",&ch);
              fflush(stdin);
           }while(ch!='1'&&ch!='2'&&ch!='3');
           //�]���u����J���T���Ʀr�~�|���}�j��
           //�ҥH���}�j���ch���O1�B2���N�@�w�O3 
            /*Ų�J���d��*/ 
           if(ch=='1'){
               keyin();
           /*�C��ܬd��*/
           }else if(ch=='2'){
               list();
            //��ܧ�Ȧ^�� 
           } 
     }
}
/*key in ��J��*/
void keyin(void){
     char a[1000],ch2; //a�x�s�ϥΪ���J���d�ߤ��W�� ,ch�s�x�ϥΪ̪��M�w 
     int quit=0,i=0,k;//quit=1�N���d����,quit=0�N��S���d����
               printf("\t\t\t  �п�J���d�ߦW��:\n\n \t\t\t\t");
               scanf("%[^\n]",&a);
               fflush(stdin);
               do{/*����r�ꪺ�ȭY�۵��N��W�٬ۦP,�Y�d��ۦP�h���*/
                  if(strcmp(a,stu[i-1].name)==0){
                  system("cls");
                  printf("\n\n");
                  show(stu[i-1],-2,-2); //�浧���,int -2 ���浧��ܱ���� 
                  printf("\n\n");
                  quit=1;
                  }
                  i++;
               }while(i<=num&&quit==0); //����S���or�d���Ƭ��� ... 
               if(quit==0)
                  printf("�d�L���\n");
               system("pause");
}
/***show ���***/
void show(STU data,int j,int k){
     int i;
     char compare[38]="1234567890123456789012345678"; //�ΥH��� 
       printf("�C�K�K�C�K�C�K�C�K�C�K�C�K�C�K�C�K�C�K�C �K�C�K�C�K�C�K�C�K�C\n");
       if(j!=-2)
          printf("��%02d�� :\t�W�� : %-17s��%d�@���߲z�Ǯa\n",j+1,data.name,data.Century);
       else
          printf("\t\t�W�� : %-17s��%d�@���߲z�Ǯa\n",data.name,data.Century);
       if(k==-2){
          printf("\t\t�ͩ� : �褸%d %05.2f\t",data.score_start_year,data.score_start_MD);
          printf("��� : �褸%d %05.2f\n",data.score_end_year,data.socre_end_MD);
          printf("\t\t���y : %-15s",data.Nationality);
          printf("\t�Ǭ� : %-15s\n",data.school);
          printf("\t\t���� : ");
          if(strcmp(data.status,compare)>0){
            for(i=0;i<34;i++)
              printf("%c",data.status[i]);
            printf(" ...\n");
          }else
            printf("%-15s\n",data.status);
       }
       printf("�C�K�K�C�K�C�K�C�K�C�K�C�K�C�K�C�K�C�K�C �K�C�K�C�K�C�K�C�K�C\n");
}

/***revise data  �����***/
void revise_data(void){
     char ch0,ch2;
     int n1,n2;
     system("cls");
     if(num==0){
          printf("\n\n\t�ثe�S�����\n\t  �L�q��s\n\n\n");
     }else{
            n1 = Display_data(1);        
          if(n1>=0&&n1<=9){
          do{
             system("cls");
             show(stu[n1-1],-2,-2);
             printf("\t�z�C�E�C�E�C�E�C�E�C�E�C�E�C�E�C�E�C�E�C�{\n\n");
             printf("\t�E\t�п�ܱz�Q��諸��ƶ���\t�E\n\n");
             printf("\t�z�C�E�C�E�C�E�C�E�C�E�C�E�C�E�C�E�C�E�C�{\n\n");
             printf("\t�C\t\t1.�ק� : �W��\t\t�C\n");
             printf("\t�E\t\t2.�ק� : �ͨ�~\t\t�E\n");
             printf("\t�C\t\t3.�ק� : ���y\t\t�C\n");
             printf("\t�E\t\t4.�ק� : �Ǭ�\t\t�E\n");
             printf("\t�C\t\t5.�ק� : ����\t\t�C\n");
             printf("\t�E\t\t6.���}\t\t\t�E\n\n");
             printf("\t�|�C�E�C�E�C�E�C�E�C�E�C�E�C�E�C�E�C�E�C�}\n\n");
             printf("\t�E\t\t�п�J 1 ~ 6\t\t�E\n\n");
             printf("\t�|�C�E�C�E�C�E�C�E�C�E�C�E�C�E�C�E�C�E�C�}\n\t\t\t\t");
             scanf("%c",&ch2);
             fflush(stdin);
             n2=ch2-'0';
              switch(n2){
                 case 1:  /*�ק�W��*/
                          ch0 = Inname(n1,1); 
                      break;
                 case 2:  /*�ק�ͨ�~*/ 
                          ch0 = InBD(n1,1);
                      break;
                 case 3:  /*�ק���y*/
                          ch0 = InNationality(n1,1);
                      break;
                 case 4:  /*�ק�Ǭ�*/
                          ch0 = Inschool(n1,1);
                      break;
                 case 5:  /*�ק鶴��*/
                          ch0 = Instatus(n1,1); 
                      break;
                 case 6:  /*���}*/ 
                          ch0 = 'e';
                      break;
                 default :  printf("��J���~");
                            system("pause");
                            ch0='n';
              }
          }while(ch0!='e');  //e = esc  ����XD 
          }
     }
}

/* Insert name */
char Inname(int n,const int swi){
     char ch,ch2;
     char name[100];//�Ȧs name ���Ŷ� 
     int k;  //�j��P�_���ϥΨ�� 
     system("cls");
     printf("�п�J�Ǫ̦W��:\n");
     scanf("%[^\n]",name);
     fflush(stdin); // �M�z �x�s��m
     if(swi==0){
         strcpy(stu[n-1].name,name);
     }else{
         system("cls");
         printf("�аݽT�w�󰵥H�U����?[Y/N]\n\n");
         printf("�쥻:\n\t%s\n",stu[n-1].name);
         printf("��אּ:\n\t%s\n",name);
         scanf("%c",&ch);
         fflush(stdin); // �M�z �x�s��m
         do{
            if(ch=='y'){
               strcpy(stu[n-1].name,name);
               printf(" ����Ƨ�s����\n");
               k=1;
            }else if(ch=='n'){
               printf("����ƱN������s \n");
               k=1;
            }else{
               printf("��J���~\n");
               k=0;
            }
         }while(k!=1);
         do{
            system("cls");             
            printf("\n�n�^�D�e���Ы�'Q'\n\n");
            printf("�Y�~��ާ@�Ы�'n'\n\n");
            scanf("%c",&ch2);
            fflush(stdin); // �M�z �x�s��m
            if(ch2=='q'||ch2=='Q')
                return 'e';
            else if(ch2=='n'||ch2=='N')
                return 'n';
            else{
                printf("��J���~\n\n");
                k=0;
            }
         }while(k!=1);
     }
     return 'e';
}

/* Insert Birth and Death  => ���J�ͨ�~ */
char InBD(int n,const int swi){
     int start_year;     //�Ȧs start_year ���Ŷ�
     double start_MD;    //�Ȧs start_MD ���Ŷ�
     int end_year;       //�Ȧs end_year ���Ŷ�
     int cent;        //�Ȧs Century ���Ŷ�            
     double end_MD;      //�Ȧs end_MD ���Ŷ�
     char ch,ch2;
     int k;              //�j��P�_���ϥΨ�� 
     do{
         system("cls");
         printf("�п�J�ӾǪ̬����ӥͩ�@��[10~22]:\n");
         while(scanf("%d",&cent)==0){//�P�_�ϥΪ̬O�_��J�Ʀr 
         fflush(stdin); // �M�z �x�s��m
         printf("��J�����O�Ʀr\n�бq�s��J");
         }
     }while(cent>22||cent<10);  //�Y��J���O�ӽd��h����߰�
     system("cls");
     
     do{
        system("cls");
        printf("�п�J�Ǫ̥X�ͪ��~�N(�褸�~):\n");
        while(scanf("%d",&start_year)==0){ 
        fflush(stdin); // �M�z �x�s��m
        printf("��J�����O�Ʀr\n�бq�s��J");
        }
     }while(start_year<(cent-1)*100+1||start_year>cent*100);//�P�_�O�_���ӥ@�� 
     printf("�п�J");
     while(1){    
          printf("�Ǫ̥X�ͪ����.���\n");
          while(scanf("%lf",&start_MD)==0){
          fflush(stdin); // �M�z �x�s��m
          printf("��J�����O�Ʀr\n�бq�s��J");
          printf("�Ǫ̥X�ͪ����.���\n");
          }
          //==�P�_���/���========= 
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
            printf("��J���~!\n�бq�s��J");    
     }
     printf("�п�J");
     while(1){ 
       printf("�Ǫ̳u�@���~�N(�褸�~)\n");
       while(scanf("%d",&end_year)==0){
       fflush(stdin); // �M�z �x�s��m
       printf("��J�����O�Ʀr\n�бq�s��J");
       printf("�Ǫ̳u�@���~�N(�褸�~)\n");
       }
       if(end_year<=start_year)
          printf("�u�@�~�N���X�z!\n�бq�s��J");
       else
          break;    //���T����X�^���~�����      
     }
     printf("�п�J");
     while(1){ 
       printf("�Ǫ̳u�@�����.���\n");
       while(scanf("%lf",&end_MD)==0){
       fflush(stdin); // �M�z �x�s��m
       printf("��J�����O�Ʀr\n�бq�s��J");
       printf("�Ǫ̳u�@�����.���\n");
       }
       //==�P�_���/���========== 
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
          break; //���T����X�j���~����� 
       else
          printf("��J���~!\n�бq�s��J");    
     }
     if(swi==0){  //swi=0��Insert���\�� 
        /*���s�W�����\��*/
        stu[n-1].score_start_year = start_year;
        stu[n-1].score_start_MD=start_MD;
        stu[n-1].score_end_year=end_year;              
        stu[n-1].socre_end_MD=end_MD;
        /*�P�_�����Ǫ̥ͩ�X�@��*/
        stu[n-1].Century = cent;
     }else{     //swi!=0����s���\�� 
         system("cls");
         printf("�аݽT�w�󰵥H�U����?[Y/N]\n\n");
         printf("�쥻:\n\t%d�@�����Ǫ�",stu[n-1].Century);
         printf("\n\t�ͩ�~%4d ",stu[n-1].score_start_year);
         printf("%04.2lf\n",stu[n-1].score_start_MD);
         printf("\t���~%4d ",stu[n-1].score_end_year);
         printf("%04.2lf\n\n",stu[n-1].socre_end_MD);
         printf("��אּ:\n\t%d�@�����Ǫ�",cent);
         printf("\n\t�ͩ�~%4d ",start_year);
         printf("%04.2lf\n",start_MD);
         printf("\t���~%4d ",end_year);
         printf("%04.2lf\n",end_MD);
         scanf("%c",&ch);
         fflush(stdin); // �M�z �x�s��m
         do{
            system("cls");
            if(ch=='y'||ch=='Y'){  //�N��Ʀs�J 
               stu[n-1].score_start_year = start_year;
               stu[n-1].score_start_MD=start_MD;
               stu[n-1].score_end_year=end_year;              
               stu[n-1].socre_end_MD=end_MD;
               stu[n-1].Century = cent;
               /*�P�_�����Ǫ̥ͩ�X�@��*/
               printf(" ����Ƨ�s����\n");
               k=1;
            }else if(ch=='n'||ch=='N'){
               printf("����ƱN������s \n");
               k=1;
            }else{
               printf("��J���~\n");
               k=0;
            }
         }while(k!=1);
         do{
            system("cls");
            printf("�n�^�D�e���Ы�'Q'\n\n");
            printf("�Y�~��ާ@�Ы�'n'\n\n");
            scanf("%c",&ch2);
            fflush(stdin); // �M�z �x�s��m
            if(ch2=='q'||ch2=='Q')
               return 'e';
            else if(ch2=='n'||ch2=='N')
               return 'n';
            else{
               printf("��J���~\n");
               k=0;
            }
         }while(k!=1);
     }
     return 'e';
}

/* Insert Nationality */
char InNationality(int n,const int swi){
     char ch,ch2;
     char Nationality[30];   //�Ȧs Nationality ���Ŷ�
     int k;                  //�j��P�_���ϥΨ�� 
     system("cls");
     printf("�п�J�Ǫ̪����y:\n");
     scanf("%[^\n]",Nationality);
     fflush(stdin); // �M�z �x�s��m
     if(swi==0){
         strcpy(stu[n-1].Nationality,Nationality);
     }else{
        do{
           system("cls");
           printf("�аݽT�w�󰵥H�U����?[Y/N]\n\n");
           printf("�쥻:\n\t%s\n",stu[n-1].Nationality);
           printf("��אּ:\n\t%s\n",Nationality);
           scanf("%c",&ch);
           fflush(stdin); // �M�z �x�s��m
           if(ch=='y'){
              strcpy(stu[n-1].Nationality,Nationality);
              printf("����Ƨ�s����\n");
              k=1;
           }else if(ch=='n'){
              printf("����ƱN������s\n");
              k=1;
           }else{
              printf("��J���~\n");
              k=0;
           }
        }while(k!=1);
        do{
           system("cls");
           printf("\n�n�^�D�e���Ы�'Q'\n");
           printf("�Y�~��ާ@�Ы�'n'\n");
           scanf("%c",&ch2);
           fflush(stdin); // �M�z �x�s��m
           if(ch2=='q'||ch2=='Q')
              return 'e';
           else if(ch2=='n'||ch2=='N')
              return 'n';
           else{
              printf("��J���~\n"); 
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
     char school[50];   //�Ȧs school ���Ŷ�
     int k;             //�j��P�_���ϥΨ�� 
     system("cls");
     printf("�п�J�Ǫ̪��Ǭ�:\n");
     scanf("%[^\n]",school);
     fflush(stdin); // �M�z �x�s��m 
     if(swi==0){
         strcpy(stu[n-1].school,school);
     }else{
         printf("�аݽT�w�󰵥H�U����?[Y/N]\n\n");
         printf("�쥻:\n\t%s\n",stu[n-1].school);
         printf("��אּ:\n\t%s\n",school);
         scanf("%c",&ch);
         fflush(stdin); // �M�z �x�s��m
         do{
            if(ch=='y'){
               strcpy(stu[n-1].school,school);
               printf("����Ƨ�s����\n");
               k=1;
            }else if(ch=='n'){
               printf("����ƱN������s\n");
               k=1;
            }else{
               printf("��J���~\n");
               k=0;
            }
         }while(k=!1);
         do{
            system("cls");
            printf("�n�^�D�e���Ы�'Q'\n");
            printf("�Y�~��ާ@�Ы�'n'\n");
            scanf("%c",&ch2);
            fflush(stdin); // �M�z �x�s��m
            if(ch2=='q'||ch2=='Q')
               return 'e';
            else if(ch2=='n'||ch2=='N')
               return 'n';
            else{
               printf("��J���~\n"); 
               k=0;
            }
         }while(k=!1);
     } 
     return 'n';
}
/* Insert status */
char Instatus(int n,const int swi){
     char ch,ch2;
     char status[100];  //�Ȧs status ���Ŷ�
     int k;             //�j��P�_���ϥΨ�� 
     system("cls");
     printf("�п�J�Ǫ̪�����:\n");
     scanf("%[^\n]",status);
     fflush(stdin); // �M�z �x�s��m
     if(swi==0){
         strcpy(stu[n-1].status,status);
     }else{
         do{
            system("cls");
            printf("�аݽT�w�󰵥H�U����[Y/N]?\n\n");
            printf("�쥻:\n\t%s\n",stu[n-1].status);
            printf("��אּ:\n\t%s\n",status);
            scanf("%c",&ch);
            fflush(stdin); // �M�z �x�s��m
            if(ch=='y'){
               strcpy(stu[n-1].status,status);
               printf("����Ƨ�s����\n");
               k=1;
            }else if(ch=='n'){
               printf("����ƱN������s\n");
               k=1;
            }else{
               printf("��J���~\n");
               k=0;
            }
         }while(k!=1);
         do{
            system("cls");
            printf("�n�^�D�e���Ы�'Q'\n");
            printf("�Y�~��ާ@�Ы�'n'\n");
            scanf("%c",&ch2);
            fflush(stdin); // �M�z �x�s��m
            if(ch2=='q'||ch2=='Q')
               return 'e';
            else if(ch2=='n'||ch2=='N')
               return 'n';
            else{
               printf("��J���~\n");
               k=0;
            }
         }while(k!=1); 
     } 
     return 'e';
}

/*sequence �Ƨ� */
void sequence(void){
     /*�N�����X�ͪ��Ǫ̱ƨ�e��*/     
     int i;
     if(num>=2)
       for(i=0;i<(num-1);i++){ 
         if(stu[i].score_start_year!=stu[i+1].score_start_year){
         //�p�G�U�@�Ӫ��X�ͦ~��W�@�Ӥ��P 
           if(stu[i].score_start_year>stu[i+1].score_start_year){
           //�B,�W�@�Ӫ��X�ͦ~�N�j��U�@�Ӫ��X�ͦ~�N,�h�N�ⵧ��ƥ洫
              change(i,i+1);
           }
         }else{//�p�G�W�@�ӻP�U�@�ӥX�ͦ~�N�ۦP
           if(stu[i].score_start_MD<stu[i+1].score_start_MD){
           //�B�W�@�Ӫ��X�ͤ�������(�p),�h�N�ⵧ��ƥ洫
              change(i,i+1); 
          } 
       }
     }                   
}

/*change �洫*/
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
     /*�Na���ȩ�ܼȦs�Ŷ�*/
     strcpy(name,stu[a].name);
     strcpy(Nationality,stu[a].Nationality);
     strcpy(school,stu[a].school);
     strcpy(status,stu[a].status);
     Century     = stu[a].Century;
     start_year  = stu[a].score_start_year;
     start_MD    = stu[a].score_start_MD;
     end_year    = stu[a].score_end_year;
     end_MD      = stu[a].socre_end_MD;
     /*�Nb�s��a*/
     strcpy(stu[a].name,stu[b].name);
     strcpy(stu[a].Nationality,stu[b].Nationality);
     strcpy(stu[a].school,stu[b].school);
     strcpy(stu[a].status,stu[b].status);
     stu[a].Century           = stu[b].Century;
     stu[a].score_start_year  = stu[b].score_start_year;
     stu[a].score_start_MD    = stu[b].score_start_MD;
     stu[a].score_end_year    = stu[b].score_end_year;
     stu[a].socre_end_MD      = stu[b].socre_end_MD;
     /*�N�Ȧs�s��b*/
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

/* open data �}�Ҩ�Ū��.txt�������*/
void opendata(const char swi){
    int temp;
	char msg[30];
	system("cls");
	printf("�}�Ҥ� ...\n");
	/*�}���ɮ�*/
    if(swi=='r')//�p�G����ѼƬ�'r'�h�ϥ�rb+���}��� 
	   if((fp=fopen(filename,"rb+"))==NULL){
		  printf("\n\n�L�k�}���ɮ� : %s\n\n",filename);
		  exit(0);
	   }
    if(swi=='w')//�p�G����ѼƬ�'w'�h�ϥ�wb+���}��� 
       if((fp=fopen(filename,"wb+"))==NULL){
		  printf("\n\n�L�k�}���ɮ� : %s\n\n",filename);
		  exit(0);
	   }
	fseek(fp,0,SEEK_SET);
	/*Ū�����*/ 
	while(fscanf(fp,"%s",stu[num].name)!=EOF){
		fscanf(fp,"%s",stu[num].Nationality); //Ū���y
        fscanf(fp,"%s",stu[num].school);      //Ū�Ǭ� 
        fscanf(fp,"%s",stu[num].status);      //Ū����
        fscanf(fp,"%s",msg);
        stu[num].Century=atoi(msg);           //Ū�@�� 
        fscanf(fp,"%s",msg);
		stu[num].score_start_year=atoi(msg);  //Ū�ͩ�~ 
		fscanf(fp,"%s",msg);
		stu[num].score_start_MD=atof(msg);    //Ū�ͤ�.�� 
		fscanf(fp,"%s",msg);
		stu[num].score_end_year=atoi(msg);    //Ū���~ 
		fscanf(fp,"%s",msg);
		stu[num].socre_end_MD=atof(msg);      //Ū���.��
        num++;
	}
}

/* closr data �������x�s��ƶi�Jdatabase.txt */
void closedata(){
	int i;
	/*�Q�ΰj��N�}�C����Ʀs�^�ɮ׸�*/
	fseek(fp,0,SEEK_SET);
	system("cls");
	printf("������ ...\n");
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
	/*�����ɮ�*/
	if(fclose(fp)!=0){
		printf("�L�k�����ɮ� : %s\n",filename);
		exit(0);
		} 
	} 
