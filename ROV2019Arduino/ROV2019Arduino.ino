#include <Servo.h>

//On standard servos a parameter value of 1000 is fully counter-clockwise, 2000 is fully clockwiseو  and 1500 is in the middle. (https://www.arduino.cc/en/Reference/ServoWriteMicroseconds)

//Servos definitions
Servo backLeft;
Servo backRight;
Servo midLeft;
Servo midRight;
Servo frontLeft;
Servo frontRight;

//Commands to be read
String command;

//Moving state and direction
bool moving = false;
String state = "stop";

//Pins:
/*
back left	=	3
back right	=	2
mid left	=	7
mid right	= 	5
front left 	= 	6
front right = 	4
*/

void setup() {
	Serial.begin(9600);
	
	//Attaching pins to servos
	backLeft.attach(3);
	backRight.attach(2);
	midLeft.attach(7);
	midRight.attach(5);
	frontLeft.attach(6);
	frontRight.attach(4);	
		
}

void loop() {
	
	//Read the command from joystick
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
	} else if (command == "left" && !moving) {
		moveLeft();
		moving = true;
		state = command;
	} else if (command == "right" && !moving) {
		moveRight();
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
		backLeft.writeMicroseconds(1650);
		backRight.writeMicroseconds(1650);
		frontLeft.writeMicroseconds(1650);
		frontRight.writeMicroseconds(1650);
		Serial.println("ROV is moving front");
}

void moveBack() {
		backLeft.writeMicroseconds(1350);
		backRight.writeMicroseconds(1350);
		frontLeft.writeMicroseconds(1350);
		frontRight.writeMicroseconds(1350);
		Serial.println("ROV is moving back");
}

void moveLeft() {
		backLeft.writeMicroseconds(1650);
		frontLeft.writeMicroseconds(1650);
		backRight.writeMicroseconds(1350);
		frontRight.writeMicroseconds(1350);
		Serial.println("ROV is moving left");
}

void moveRight() {
		backLeft.writeMicroseconds(1350);
		frontLeft.writeMicroseconds(1350);
		backRight.writeMicroseconds(1650);
		frontRight.writeMicroseconds(1650);	
		Serial.println("ROV is moving right");
}

void moveUp() {
		midLeft.writeMicroseconds(1650);
		midRight.writeMicroseconds(1650);
		Serial.println("ROV is moving up");
}

void moveDown() {
		midLeft.writeMicroseconds(1350);
		midRight.writeMicroseconds(1350);
		Serial.println("ROV is moving down");
}

void stopMoving() {
	if (state == "front" || state == "back" || state == "left" || state == "right") {
		backLeft.writeMicroseconds(1500);
		backRight.writeMicroseconds(1500);		
		frontLeft.writeMicroseconds(1500);
		frontRight.writeMicroseconds(1500);
	} else if (state == "up" || state == "down") {
		midLeft.writeMicroseconds(1500);
		midRight.writeMicroseconds(1500);
	}
		Serial.println("ROV HAS STOPPED");
}