#!/usr/bin/python

import numpy as np
import cv2
import imutils

def test():
    while True:
        ret, img = camera.read()

        yellowLower = (20, 60, 0)
        yellowUpper = (60, 255, 255)

        img = imutils.resize(img, width=600)
        blurred = cv2.GaussianBlur(img, (11, 11), 0)
        hsv = cv2.cvtColor(blurred, cv2.COLOR_BGR2HSV)
        gray = cv2.cvtColor(blurred, cv2.COLOR_BGR2GRAY)

        mask = cv2.inRange(hsv, yellowLower, yellowUpper)
        mask = cv2.erode(mask, None, iterations=2)
        mask = cv2.dilate(mask, None, iterations=2)

        output = img.copy()
        circles = cv2.HoughCircles(gray, cv2.HOUGH_GRADIENT, 1.2, 10)
        if circles is not None:
            circles = np.round(circles[0, :]).astype("int")
            for (x, y, r) in circles:
                cv2.circle(output, (x, y), r, (0, 0, 255), 4)
                cv2.rectangle(output, (x - 5, y - 5), (x + 5, y + 5), (0, 128, 255), -1)

        cv2.imshow("test", output)

        if cv2.waitKey(1) & 0xFF == ord('q'):
            break

camera = cv2.VideoCapture("/dev/video2")
if __name__ == "__main__":
    test()
