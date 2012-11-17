#include <stdio.h>
#include "graphics.h"

int main(void) {

  setSize(1200,700);
  drawRect(50,100,150,25);
  drawLine(150,25,250,100);
  fillRect(130, 190, 40, 60);
  drawRect(70, 195, 40, 30);
  drawImage(100,100,50,50,"file:camera.png"); 
  setColourGradient("green", "blue");
  drawRect(190, 195, 40, 30);
  fillOval(400.0, 200.0, 30.0, 40.0);
  setPosition(450,250);
  turnLeft(45);
  moveForward(100);
  setPosition(460,200);
  turnRight(50);
  moveForward(50);
  //turnLeft(10);
  //moveForward(10);
  //turnRight(45); 

  return 0;
}


