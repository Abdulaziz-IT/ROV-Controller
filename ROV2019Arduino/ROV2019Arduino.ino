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
	} else if (command == "front") {
		backLeft.writeMicroseconds(1650);
		backRight.writeMicroseconds(1650); 
		frontLeft.writeMicroseconds(1650); 
		frontRight.writeMicroseconds(1650); 
		
		Serial.println("ROV is moving front");
	} else if (command == "back") {
		backLeft.writeMicroseconds(1350); 
		backRight.writeMicroseconds(1350); 
		frontLeft.writeMicroseconds(1350); 
		frontRight.writeMicroseconds(1350); 
		
		Serial.println("ROV is moving back");
	} else if (command == "left") {
		backLeft.writeMicroseconds(1650);
		frontLeft.writeMicroseconds(1650);
		backRight.writeMicroseconds(1350); 
		frontRight.writeMicroseconds(1350); 
		
		Serial.println("ROV is moving left");
	} else if (command == "right") {
		backLeft.writeMicroseconds(1350);
		frontLeft.writeMicroseconds(1350);
		backRight.writeMicroseconds(1650); 
		frontRight.writeMicroseconds(1650); 
		
		Serial.println("ROV is moving right");
	} else if (command == "up") {
		midLeft.writeMicroseconds(1650); 
		midRight.writeMicroseconds(1650); 

		Serial.println("ROV is moving up");
	} else if (command == "down") {
		midLeft.writeMicroseconds(1350); 
		midRight.writeMicroseconds(1350); 

		Serial.println("ROV is moving down");
	} else if (command == "stop") {
		backLeft.writeMicroseconds(1500); 
		backRight.writeMicroseconds(1500); 
		midLeft.writeMicroseconds(1500); 
		midRight.writeMicroseconds(1500); 
		frontLeft.writeMicroseconds(1500); 
		frontRight.writeMicroseconds(1500); 
		
		Serial.println("ROV STOPPED");
	}
	
}