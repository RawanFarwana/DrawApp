enum colour {black,blue,cyan,darkgray,gray,green,lightgray,magenta,orange,pink,red,white,yellow};
typedef enum colour colour;

void drawLine(int,int,int,int);
void drawRect(int,int,int,int);
void drawOval(int,int,int,int);
void drawArc(int,int,int,int,int,int);
void fillRect(int,int,int,int);
void drawString(char*,int,int);
void drawImage(int, int,int,int,char*);
void fillOval(double, double, double, double);

void setColour(colour);
void getColour(colour);
void setColourGradient(char*, char*);
