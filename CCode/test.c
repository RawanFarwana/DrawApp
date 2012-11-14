#include <stdio.h>
#include "graphics.h"

int main(void) {
  drawRect(50,100,150,25);
  drawLine(150,25,250,100);
  fillRect(130, 190, 40, 60);
  drawRect(70, 195, 40, 30);
  drawImage(500,500,50,50,"pic.gif"); 
  setColourGradient("green", "blue");
  drawRect(190, 195, 40, 30);
  fillOval(400.0, 200.0, 30.0, 40.0);

  return 0;
}


