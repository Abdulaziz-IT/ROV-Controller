#include <Servo.h>

//On standard servos a parameter value of 1000 is fully counter-clockwise, 2000 is fully clockwiseو  and 1500 is in the middle. (https://www.arduino.cc/en/Reference/ServoWriteMicroseconds)

//Servos definitions
Servo servo2;
Servo backRight;
Servo servo4;
Servo servo3;
Servo servo6;
Servo servo5;

//Commands to be read
String command;

//Moving state and direction
bool moving = false;
String state = "stop";

/*
Pins:
back left	=	3
back right	=	2
mid left	=	7
mid right	= 	5
front left 	= 	6
front right = 	4
*/

/* Moves:
Up: 3+4 clockwise
Down: 3+4 counter-clockwise
Front: 1+2+5+6 clockwise
Back: 1+2+5+6 counter-clockwise
Rotate right: 2+6 clockwise, 1+5 Counter-clockwise
Rotate left: 1+5 clockwise, 2+6 Counter-clockwise
Right: 1+6 clockwise, 2+5 counter-clockwise
Left: 5+2 clockwise, 1+6 counter-clockwise
Vertical Right = 4 clockwise, 3 counter-clockwise
Vertical Left = 3 clockwise, 4 counter-clockwise

Combinations:
Front+up
Front+down

Back+up
Back+down

Right+up
Right+down

Left+up
Left+down
*/

void setup() {
	Serial.begin(9600);
	
	//Attaching pins to servos
	servo2.attach(3); //Back-left servo
	servo1.attach(2); //Back-right servo
	servo4.attach(7); //Mid-left servo
	servo3.attach(5); //Mid-right servo
	servo6.attach(6); //Front-left servo
	servo5.attach(4); //Front-right servo
		
}

void loop() {
	
	//Read the command from serial
	command = Serial.readString();
	
	if (command == "test") {
		Serial.println("Test passed");
	} else if (command == "front" && !moving) {
		moveFront();
		moving = true;
		state = command;
	} else if (command == "back" && !moving) {
		moveBack();
		moving = true;
		state = command;
	} else if (command == "rotate left" && !moving) {
		rotateLeft();
		moving = true;
		state = command;
	} else if (command == "rotate right" && !moving) {
		rotateRight();
		moving = true;
		state = command;
	} else if (command == "up" && !moving) {
		moveUp();
		moving = true;
		state = command;
	} else if (command == "down" && !moving) {
		moveDown();
		moving = true;
		state = command;
	} else if (command == "stop" && moving) {
		stopMoving();
		moving = false;
		state = command;
	}
	
}

void moveFront() {
		servo2.writeMicroseconds(1650);
		servo1.writeMicroseconds(1650);
		servo6.writeMicroseconds(1650);
		servo5.writeMicroseconds(1650);
		Serial.println("ROV is moving front");
}

void moveBack() {
		servo2.writeMicroseconds(1350);
		servo1.writeMicroseconds(1350);
		servo6.writeMicroseconds(1350);
		servo5.writeMicroseconds(1350);
		Serial.println("ROV is moving back");
}

void rotateLeft() {
		servo2.writeMicroseconds(1350);
		servo6.writeMicroseconds(1350);
		servo1.writeMicroseconds(1650);
		servo5.writeMicroseconds(1650);	
		Serial.println("ROV is rotating to left");
}

void rotateRight() {
		servo2.writeMicroseconds(1650);
		servo6.writeMicroseconds(1650);
		servo1.writeMicroseconds(1350);
		servo5.writeMicroseconds(1350);
		Serial.println("ROV is rotating to right");
}

void moveUp() {
		servo4.writeMicroseconds(1650);
		servo3.writeMicroseconds(1650);
		Serial.println("ROV is moving up");
}

void moveDown() {
		servo4.writeMicroseconds(1350);
		servo3.writeMicroseconds(1350);
		Serial.println("ROV is moving down");
}

void moveLeft() {
		servo1.writeMicroseconds(1650);
		servo6.writeMicroseconds(1650);
		servo2.writeMicroseconds(1350);
		servo5.writeMicroseconds(1350);
		Serial.println("ROV is moving left");
}

void moveRight() {
		servo1.writeMicroseconds(1650);
		servo6.writeMicroseconds(1650);
		servo2.writeMicroseconds(1350);
		servo5.writeMicroseconds(1350);
		Serial.println("ROV is moving right");
}

void stopMoving() {
	if (state == "front" || state == "back" || state == "left" || state == "right") {
		servo2.writeMicroseconds(1500);
		servo1.writeMicroseconds(1500);		
		servo6.writeMicroseconds(1500);
		servo5.writeMicroseconds(1500);
	} else if (state == "up" || state == "down") {
		servo4.writeMicroseconds(1500);
		servo3.writeMicroseconds(1500);
	}
		Serial.println("ROV HAS STOPPED");
}
