# -*- coding: utf-8 -*-

import cv2
import numpy as np
import sys

def Update_Pointer(pointer, y):
    return (pointer[0], pointer[1] + y)

def show_recognized(lis, path):
    #vars
    height = 512
    width = 512
    padx = 120
    pady = 70
    draw_pointer = (int(width/2), 70)
    font = cv2.FONT_HERSHEY_SCRIPT_COMPLEX
    
    img = np.zeros((height, width, 3), np.uint8)
    # background
    cv2.rectangle(img, (0, 0), (width, height), (255, 255, 255), cv2.FILLED)
    
    #draw Circle
    cv2.putText(img, str(lis[3]), (draw_pointer[0] - padx, draw_pointer[1]), font, 2, (0, 0, 255), 5)
    cv2.circle(img, draw_pointer, 50, (0, 0, 255), cv2.FILLED)
    ## update pointer
    draw_pointer = Update_Pointer(draw_pointer, pady)
    draw_pointer = Update_Pointer(draw_pointer, int(pady/2))
    # draw triangle
    cv2.putText(img, str(lis[0]), (draw_pointer[0] - padx, draw_pointer[1] + 50), font, 2, (0, 0, 255), 5)
    points = np.array([draw_pointer, (draw_pointer[0] - 50, draw_pointer[1] + pady), (draw_pointer[0] + 50, draw_pointer[1] + pady)])
    cv2.drawContours(img, [points], 0, (0, 0, 255), -1)
    # update
    draw_pointer = Update_Pointer(draw_pointer, pady + int(pady/2))
    # draw line
    cv2.putText(img, str(lis[2]), (draw_pointer[0] - padx, draw_pointer[1] + 50), font, 2, (0, 0, 255), 5)
    cv2.line(img, (draw_pointer[0] - 50, draw_pointer[1] + 40), (draw_pointer[0] + 50, draw_pointer[1] + 40), (0, 0, 255), 10)
    # update
    draw_pointer = Update_Pointer(draw_pointer, pady +int(pady/2))
    # draw Poly
    cv2.rectangle(img, (draw_pointer[0] - 50, draw_pointer[1]), (draw_pointer[0] + 50, draw_pointer[1] + 80), (0, 0, 255), -1)
    cv2.putText(img, str(lis[1]), (draw_pointer[0] - padx, draw_pointer[1] + 50), font, 2, (0, 0, 255), 5)
    #cv2.imshow('test', img)
    cv2.imwrite(str(path[0 : -4] + '-recognized.jpg'), img)
    
    
#C:\\Users\\HEMOU\\Desktop\\Image-Recognition\\X.jpg
#path = input()
path = sys.argv[1]
print (path)
img = cv2.imread(path, cv2.IMREAD_GRAYSCALE)
_, threshold = cv2.threshold(img, 80, 255, cv2.THRESH_BINARY)
contours, _ = cv2.findContours(threshold, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
 
font = cv2.FONT_HERSHEY_COMPLEX
##place holder for numbers of shapes in the image
lis = [0, 0, 0, 0]
for cnt in contours:
    approx = cv2.approxPolyDP(cnt, 0.02*cv2.arcLength(cnt, True), True)
    #cv2.drawContours(img, [approx], 0, (0), 1)
    x = approx.ravel()[0]
    y = approx.ravel()[1]
    if len(approx) < 3:
        lis[2] +=1
    elif len(approx) == 3:
        lis[0] += 1
    elif len(approx) == 4:
        if x == 0:
            continue
        x2 = approx.ravel()[2]
        if (abs(x - y) < 10 or abs(x - x2) < 10):
            lis[2] += 1
        else:
            lis[1] += 1
    else:
       # cv2.putText(img, str(abs(x - y)), (x, y), font, 1 , (0))
        lis[3] += 1
      
#cv2.imshow("shapes", img)
show_recognized(lis, path)
##cv2.imshow("thre", threshold)
print ("Triangles, Rectangles, Lines, Circles")
print (lis[0], lis[1], lis[2], lis[3])


#cv2.waitKey(0)
#cv2.destroyAllWindows()
