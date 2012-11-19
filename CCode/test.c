#include <stdio.h>
#include "graphics.h"

int main(void) {

  setSize(2000,1500);
  drawImage(150,0,700,500,"file:background.png"); 
  //Text
  setColourGradient("white","yellow");
  drawString(200,470,"H a p p y...W i n t e r");
  //SnowMan Body
  setColourGradient("gray","white");
  fillOval(440.0,160.0,65.0,60.0);
  fillOval(430.0,220.0,90.0,80.0);
  fillOval(400.0,300.0,150.0,160.0);
  //Facial Features
  setColourGradient("black","green");
  fillOval(455.0,175.0,10.0,10.0);
  fillOval(480.0,175.0,10.0,10.0);
  setColourGradient("red","red");
  drawArc(92, 50, 116, 116, 0, 160);
  drawArc(470,200,20,50,30,40);
  //Hands
  setColour(white); 
  drawLine(440,240,395,200);  
  drawLine(520,240,565,200);
  //Accessories
  drawOval(472,235,10,10);
  drawOval(472,255,10,10);
  drawOval(472,275,10,10);
  //Shining Star
  setColourGradient("yellow","white");
  setPosition(750,100);
  moveForward(50);
  // drawRect(400,160,450,140);
  //setPosition(450,250);
  //turnLeft(45);
  //moveForward(100);
  //setPosition(460,200);
  //turnRight(50);
  //moveForward(50);
  //turnLeft(10);
  //moveForward(10);
  //turnRight(45); 

  return 0;
}


