#include <stdio.h>
#include "graphics.h"

void setPosition(int x, int y)
{
  printf("TP %i %i\n", x,y);
}

void moveForward(int distance)
{
  printf("TM %i\n", distance);
}

void turnLeft(int angle)
{
  printf("TL %i\n", angle);
}

void turnRight(int angle)
{
  printf("TR %i\n", angle);
}

void setSize(int width, int height)
{
  printf("SS %i %i\n", width, height);
}

void drawLine(int x1, int x2, int x3, int x4)
{
  printf("DL %i %i %i %i\n", x1, x2, x3, x4);
}

void drawRect(int x1, int x2, int x3, int x4)
{
  printf("DR %i %i %i %i\n", x1, x2, x3, x4);
}

void drawOval(int x, int y, int width, int height)
{
  printf("DO %i %i %i %i\n",x,y,width,height);
}

void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)
{
  printf("DA %i %i %i %i %i %i\n",x,y,width,height, startAngle, arcAngle);
}

void fillRect(int x1, int x2, int x3, int x4)
{
  printf("FR %i %i %i %i\n", x1, x2, x3, x4);
}

void drawString(char* s, int x, int y)
{
  printf("DS %i %i @%s\n",x,y,s);
}

void drawImage(int x, int y, int width, int height, char* pathWay)
{
  printf("DI %i %i %i %i @%s\n", x,y,width,height,pathWay);
}

void fillOval(double x, double y, double width, double height)
{
  printf("FO %f %f %f %f\n", x,y,width,height);
}

void setColourGradient(char* colour1, char* colour2)
{
  printf("CG %s %s\n", colour1, colour2);
}

void setColour(colour c)
{
  char* colourName;
  switch(c)
  {
    case black : colourName = "black"; break;
    case blue : colourName = "blue"; break;
    case cyan : colourName = "cyan"; break;
    case darkgray : colourName = "darkgray"; break;
    case gray : colourName = "gray"; break;
    case green : colourName = "green"; break;
    case lightgray : colourName = "lightgray"; break;
    case magenta : colourName = "magenta"; break;
    case orange : colourName = "orange"; break;
    case pink : colourName = "pink"; break;
    case red : colourName = "red"; break;
    case white : colourName = "white"; break;
    case yellow : colourName = "yellow"; break;
  }
  printf("SC %s\n", colourName);
}
