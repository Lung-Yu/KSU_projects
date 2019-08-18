#include <SoftwareSerial.h>

int bluetoothTx = 45;
int bluetoothRx = 47;

SoftwareSerial bluetooth(bluetoothTx, bluetoothRx);
int dir1PinA=13;
int dir2PinA=12;
int speedPinA=10;

int dir1PinB=11;
int dir2PinB=8;
int speedPinB=9;

int dir1PinC=7;
int dir2PinC=6;
int speedPinC=5;

int portPinX=0;   //類比A0
int portPinY=1;   //類比A1
int rotPin=3;



unsigned long time;
int speedA=900;
int speedB=900;
int speedC=900;
int dirA=0;
int dirB=0;
int dirC=0;

int valX=0;
int valY=0;
int absX=0;
int absY=0;
void setup()
{
    //set Motor PinMode
  pinMode(dir1PinA,OUTPUT);
  pinMode(dir2PinA,OUTPUT);
  pinMode(speedPinA,OUTPUT);
  pinMode(dir1PinB,OUTPUT);
  pinMode(dir2PinB,OUTPUT);
  pinMode(speedPinB,OUTPUT);
  pinMode(dir1PinC,OUTPUT);
  pinMode(dir2PinC,OUTPUT);
  pinMode(speedPinC,OUTPUT); 
  pinMode(rotPin,INPUT); 
  time=millis();

  pinMode(A8, OUTPUT);
  pinMode(A9, OUTPUT);

  //attachInterrupt(4,doEncoderA,CHANGE);
  //attachInterrupt(4,doEncoderB,CHANGE);
  //attachInterrupt(4,doEncoderC,CHANGE);
  //pinMode(45, OUTPUT);
  //pinMode(47, INPUT);
  pinMode(53, OUTPUT);
  pinMode(51,OUTPUT);
  pinMode(49,OUTPUT);
  digitalWrite(51,LOW);
  //Setup usb serial connection to computer
  Serial.begin(57600);

  //Setup Bluetooth serial connection to android
//  bluetooth.begin(115200);
//  bluetooth.print("$$$");
  bluetooth.begin(9600);
}

void loop()
{
  //Read from usb serial to bluetooth

  int ref=30,i,obj,kp,err,lightSpeedA,lightSpeedB,speed_m,val;
  if(digitalRead(2)==0){
      kp=2.0;
      obj=light();
      err=(ref-obj)*kp;
      if(err>=50)
        err=50;
      if(err<=-50)
        err=-50;
      lightSpeedA=400-err;
      lightSpeedB=400+err;
       if(err >=25){
        motor_moveA(2,lightSpeedB);
        motor_moveB(2,lightSpeedB);
      }else if(err < 0){
        motor_moveA(1,lightSpeedB);
        motor_moveB(1,lightSpeedB);
       }else{
        motor_moveA(1,lightSpeedA);
        motor_moveB(2,lightSpeedB);
      }
  }else{
    String str = "";
    byte bBuf[512];
    while(Serial.available()){
      str += (char) Serial.read();
      delay(2);
    }
  if(str.length()>0){
        str.getBytes(bBuf,512);
        if(bBuf[4]==0){
                    double _dif = 1.40;
                    double x = (bBuf[0]==1)?(bBuf[1]):(bBuf[1]*(-1));
                    double y = (bBuf[2]==1)?(bBuf[3]):(bBuf[3]*(-1));
                   
                    x *= _dif;
                    y *= _dif;
                    //處理
                    double x_max = 160;
                    double y_max = 160;
                    if(x==0 || y==0){
                      if(x==0&&y==0){
                        dirA=0;
                        dirB=0;
                        dirC=0;
                      }else if(x==0 && y> 0 ){
                      speedA=220 * y/y_max;speedB=220 *y/y_max;speedC=0;dirA=1;dirB=2;dirC=0;
                      }else if(x==0 && y<0){
                        speedA=220*y/y_max;speedB=220*y/y_max;speedC=0;dirA=2;dirB=1;dirC=0;
                      }else if(x>0 && y==0){
                        speedA=88*x/x_max;speedB=174*x/x_max;speedC=174*x/x_max;dirA=2;dirB=2;dirC=1;
                      }else{
                        speedA=174*x/x_max;speedB=88*x/x_max;speedC=145*x/x_max;dirA=1;dirB=1;dirC=2;
                      }
                    }else if(x>0 && y>0){  //I
                      Serial.println("I");
                      speedA = ( 174 * (y / y_max) ) + 88*(x / x_max);
                      speedB = ( 174 * (y / y_max) ) + 174*(x / x_max);
                      speedC = ( 0 * (y / y_max) ) + 174*(x / x_max);
                      dirA = 1;
                      dirB = 2;
                      dirC = 1;
                    }else if(x<0 && y>0){  //II
                      speedA = ( 174 * (y / y_max) ) + 174 * (x / x_max);
                      speedB = ( 174 * (y / y_max) ) + 88 * (x / x_max);
                      speedC = ( 0 * (y / y_max) ) + 145 * (x / x_max);
                      dirA = 1;
                      dirB = 2;
                      dirC = 2;
                    }else if(x<0 && y<0){  //III
                      speedA = ( 174 * (y / y_max) ) + 174 * (x / x_max);
                      speedB = ( 174 * (y / y_max) ) + 88 * (x / x_max);
                      speedC = ( 0 * (y / y_max) ) + 145 * (x / x_max);
                      dirA = 2;
                      dirB = 1;
                      dirC = 2;
                    }else if(x>0 && y<0){  //IV
                      speedA = ( 174 * (y / y_max) ) + 88 * (x / x_max);
                      speedB = ( 174 * (y / y_max) ) + 174 * (x / x_max);
                      speedC = ( 0 * (y / y_max) ) + 174 * (x / x_max);
                      dirA = 2;
                      dirB = 1;
                      dirC = 1;
                    }else{
                      motor_moveA(dirA,speedA);
                      motor_moveB(dirB,speedB);
                      motor_moveC(dirC,speedC); 
                    }
        }else if(bBuf[4]==1){
                  dirA  = dirB = dirC = 1;
                  speedA = speedB =speedC = 150;
        }else if(bBuf[4]==2){
                dirA = dirB = dirC =2;
                speedA = speedB = speedC = 150;
        }
       
      
  }else{
    motor_moveA(dirA,speedA);
    motor_moveB(dirB,speedB);
    motor_moveC(dirC,speedC); 
  }
   /*if(Serial.available()){
     byte toSend=Serial.read();
     delay(5);
     dirA = (int)toSend[0];
     speedA = (int)toSend[1];
     dirB = (int)toSend[2];
     speedB = (int)toSend[3];
     dirC = 1;
     speedC = 100;
   }else{
     Serial.print('Hello\n');
     motor_moveA(dirA,speedA);
     motor_moveB(dirB,speedB);
     motor_moveC(dirC,speedC);
   }*/
  }
}
void char2byteArray(char ch){
  byte result[8];
  result[0] = (byte)((ch >> 56) & 0xff);
  result[1] = (byte)((ch >> 48) & 0xff);
  result[2] = (byte)((ch >> 40) & 0xff);
  result[3] = (byte)((ch >> 32) & 0xff);
  result[4] = (byte)((ch >> 24) & 0xff);
  result[5] = (byte)((ch >> 16) & 0xff);
  result[6] = (byte)((ch >> 8 ) & 0xff);
  result[7] = (byte)((ch >> 0) & 0xff);
       dirA = int(result[0]);
     dirB = int(result[2]);
     dirC = int(result[4]);
     speedA = int(result[1]);
     speedB = int(result[3]);
     speedC = int(result[5]);
  return;
}
byte* int2byteArray(int num) {
  byte result[4];
  result[0] = (byte) (num >> 24);// 取最高8位放到0下标
  result[1] = (byte) (num >> 16);// 取次高8为放到1下标
  result[2] = (byte) (num >> 8); // 取次低8位放到2下标
  result[3] = (byte) (num); // 取最低8位放到3下标
  return result;
}
int byteArray2int(byte b[]){
  int a;
  a = b[0] & 0xff;
  a |= (b[1]<<8) & 0xff;
  a |= (b[2]<<16)& 0xff;
  a |= (b[3]<<24)&& 0xff;
  return a;
}
void Stop(){speedA=0;speedB=0;speedC=0;dirA=0;dirB=0;dirC=0;}
void Forward(){speedA=174;speedB=174;speedC=0;dirA=1;dirB=2;dirC=0;}
void Back(){speedA=174;speedB=174;speedC=0;dirA=2;dirB=1;dirC=0;}
void Left(){speedA=174;speedB=88;speedC=145;dirA=1;dirB=1;dirC=2;}
void Right(){speedA=88;speedB=174;speedC=174;dirA=2;dirB=2;dirC=1;}
void LeftFront(){speedA=246;speedB=61;speedC=124;dirA=1;dirB=2;dirC=2;}
void RightFront(){speedA=61;speedB=246;speedC=124;dirA=1;dirB=2;dirC=1;}
void LeftBack(){speedA=65;speedB=246;speedC=124 ;dirA=2;dirB=1;dirC=2;}
void RightBack(){speedA=246;speedB=61;speedC=124;dirA=2;dirB=2;dirC=1;}
void RightRotation(){speedA=174;speedB=174;speedC=174;dirA=2;dirB=2;dirC=2;}
void LeftRotation(){speedA=174;speedB=174;speedC=174;dirA=1;dirB=1;dirC=1;}
void motor_moveA(int motor_dir,int motor_speed){
    analogWrite(10,motor_speed);
    switch(motor_dir){
      case 0:digitalWrite(13,LOW);digitalWrite(12,LOW);break;
      case 1:digitalWrite(13,HIGH);digitalWrite(12,LOW);break;
      case 2:digitalWrite(13,LOW);digitalWrite(12,HIGH);break;
      default:break;
    }
}
void motor_moveB(int motor_dir,int motor_speed){
    analogWrite(9,motor_speed);
    switch(motor_dir){
      case 0:digitalWrite(11,LOW);digitalWrite(8,LOW);break;
      case 1:digitalWrite(11,HIGH);digitalWrite(8,LOW);break;
      case 2:;digitalWrite(11,LOW);digitalWrite(8,HIGH);break;
      default:break;
    }
}
void motor_moveC(int motor_dir,int motor_speed){
    analogWrite(5,motor_speed);
    switch(motor_dir){
      case 0:digitalWrite(7,LOW);digitalWrite(6,LOW);break;
      case 1:digitalWrite(7,HIGH);digitalWrite(6,LOW);break;
      case 2:digitalWrite(7,LOW);digitalWrite(6,HIGH);break;
      default:break;
    }
}


/*循跡感測器*/
int light(){
   int sum=0,i,no=0;
   if(analogRead(5)>=500){
   sum += 10;
   no++;  
   }
   if(analogRead(4)>=500){
   sum += 20;
   no++;  
   }
   if(analogRead(3)>=500){
   sum += 30;
   no++;  
   }
   if(analogRead(2)>=500){
   sum += 40;
   no++;  
   }
   if(analogRead(1)>=500){
   sum += 50;
   no++;  
   }
   
   if(no==0)
     return sum;
    else
      return (sum/no);
}
