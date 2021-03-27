# USAGE
# python ball_tracking.py --video ball_tracking_example.mp4
# python ball_tracking.py

# import the necessary packages
from imutils.video import VideoStream
import numpy as np
import argparse
import cv2
import imutils
import time
import math
from networktables import NetworkTables

# construct the argument parse and parse the arguments
ap = argparse.ArgumentParser()
ap.add_argument("-v", "--video",
	help="path to the (optional) video file")
ap.add_argument("-b", "--buffer", type=int, default=64,
	help="max buffer size")
args = vars(ap.parse_args())

# networktables interface, using blank path variable for now
ip = "roborio-7503-FRC.local"
NetworkTables.initialize(server=ip)
datatable = NetworkTables.getTable("galacticsearch")

# define the lower and upper boundaries of the "yellow"
# ball in the HSV color space, then initialize the
# list of tracked points
# v4l2-ctl -d /dev/video2 -c exposure_auto=1 -c exposure_absolute=20
# exposure values: 1, 20

yellowLower = (20, 100, 100)
yellowUpper = (30, 255, 255)

# if a video path was not supplied, grab the reference
# to the webcam
if not args.get("video", False):
	#vs = VideoStream(src=1).start()
	vs = VideoStream('http://roboRIO-7503-frc.local:1181/stream.mjpg').start()

# otherwise, grab a reference to the video file
else:
	vs = cv2.VideoCapture(args["video"])

# allow the camera or video file to warm up
time.sleep(0)

# keep looping
while True:
	# grab the current frame
	frame = vs.read()

	# handle the frame from VideoCapture or VideoStream
	frame = frame[1] if args.get("video", False) else frame

	# if we are viewing a video and we did not grab a frame,
	# then we have reached the end of the video
	if frame is None:
		break

	# resize the frame, blur it, and convert it to the HSV
	# color space
	frame = imutils.resize(frame, width=600)
	blurred = cv2.GaussianBlur(frame, (11, 11), 0)
	hsv = cv2.cvtColor(blurred, cv2.COLOR_BGR2HSV)

	# construct a mask for the color "yellow", then perform
	# a series of dilations and erosions to remove any small
	# blobs left in the mask
	mask = cv2.inRange(hsv, yellowLower, yellowUpper)
	mask = cv2.erode(mask, None, iterations=2)
	mask = cv2.dilate(mask, None, iterations=2)

	# find contours in the mask and initialize the current
	# (x, y) center of the ball
	cnts = cv2.findContours(mask.copy(), cv2.RETR_EXTERNAL,
		cv2.CHAIN_APPROX_NONE)
	cnts = imutils.grab_contours(cnts)
	center = None

	pts = []

	# run for first target
	if len(cnts) > 0:
		# find the largest contour in the mask, then use
		# it to compute the minimum enclosing circle and
		# centroid

		c = None
		# check if its actually a circle
		c_found = False
		cnts.sort(key=cv2.contourArea)
		while not c_found and len(cnts) > 0:
			distances = []
			c = cnts.pop(0)
			((x, y), radius) = cv2.minEnclosingCircle(c)
			if radius > 7:
				for p in c:
					cv2.circle(frame, (p[0][0], p[0][1]), 5, (255, 0, 0))
					distance_from_center = math.sqrt((x-p[0][0])**2 + (y-p[0][1])**2)
					distances.append(abs(radius - distance_from_center))
				distances = np.array(distances)
				if distances.std() < 5 and distances.mean() < 5:
					c_found = True
				#print("std")
				#print(distances.std())
				#print("mean")
				#print(np.array(distances).mean())

		if c_found:
			((x, y), radius) = cv2.minEnclosingCircle(c)

			M = cv2.moments(c)
			center = (int(M["m10"] / M["m00"]), int(M["m01"] / M["m00"]))

			# only proceed if the radius meets a minimum size
			if radius > 7:
				# draw the circle and centroid on the frame,
				# then update the list of tracked points
				cv2.circle(frame, (int(x), int(y)), int(radius),
					(0, 255, 255), 2)
				cv2.circle(frame, center, 5, (0, 0, 255), -1)

				if center is not None and radius is not None:
					pts.append((center, radius))


	# run for second target
	if len(cnts) > 0:
		# find the largest contour in the mask, then use
		# it to compute the minimum enclosing circle and
		# centroid

		c = None
		# check if its actually a circle
		c_found = False
		cnts.sort(key=cv2.contourArea)
		while not c_found and len(cnts) > 0:
			distances = []
			c = cnts.pop(0)
			((x, y), radius) = cv2.minEnclosingCircle(c)
			if radius > 10:
				for p in c:
					cv2.circle(frame, (p[0][0], p[0][1]), 5, (255, 0, 0))
					distance_from_center = math.sqrt((x-p[0][0])**2 + (y-p[0][1])**2)
					distances.append(abs(radius - distance_from_center))
				distances = np.array(distances)
				if distances.std() < 5 and distances.mean() < 5:
					c_found = True
				#print("std")
				#print(distances.std())
				#print("mean")
				#print(np.array(distances).mean())

		if c_found:
			((x, y), radius) = cv2.minEnclosingCircle(c)

			M = cv2.moments(c)
			center = (int(M["m10"] / M["m00"]), int(M["m01"] / M["m00"]))

			# only proceed if the radius meets a minimum size
			if radius > 10:
				# draw the circle and centroid on the frame,
				# then update the list of tracked points
				cv2.circle(frame, (int(x), int(y)), int(radius),
					(0, 255, 255), 2)
				cv2.circle(frame, center, 5, (0, 0, 255), -1)
	
				if center is not None and radius is not None:
					pts.append((center, radius))

	# run for third target
	if len(cnts) > 0:
		# find the largest contour in the mask, then use
		# it to compute the minimum enclosing circle and
		# centroid

		c = None
		# check if its actually a circle
		c_found = False
		cnts.sort(key=cv2.contourArea)
		while not c_found and len(cnts) > 0:
			distances = []
			c = cnts.pop(0)
			((x, y), radius) = cv2.minEnclosingCircle(c)
			if radius > 10:
				for p in c:
					cv2.circle(frame, (p[0][0], p[0][1]), 5, (255, 0, 0))
					distance_from_center = math.sqrt((x-p[0][0])**2 + (y-p[0][1])**2)
					distances.append(abs(radius - distance_from_center))
				distances = np.array(distances)
				if distances.std() < 5 and distances.mean() < 5:
					c_found = True
				#print("std")
				#print(distances.std())
				#print("mean")
				#print(np.array(distances).mean())

		if c_found:
			((x, y), radius) = cv2.minEnclosingCircle(c)

			M = cv2.moments(c)
			center = (int(M["m10"] / M["m00"]), int(M["m01"] / M["m00"]))

			# only proceed if the radius meets a minimum size
			if radius > 10:
				# draw the circle and centroid on the frame,
				# then update the list of tracked points
				cv2.circle(frame, (int(x), int(y)), int(radius),
					(0, 255, 255), 2)
				cv2.circle(frame, center, 5, (0, 0, 255), -1)
	
				if center is not None and radius is not None:
					pts.append((center, radius))

	path = None
	color = None
	ball_in_center = None
	# if ball in front of us, path A
	for x in pts:
		if abs(320 - x[0][0]) < 60:
			path = "A"
			ball_in_center = x
	# if ball to the left or right, path B
	if path == None:
		path = "B"

	if path is not None:
		datatable.putString("path", path)
		print("Path: " + path)

	# path a
	if ball_in_center is not None and path == "A":
		# middle ball far away, blue
		if ball_in_center[1] < 12:
			color = "blue"

		# middle ball close by, red
		else:
			color = "red"

	# path b
	if path == "B": 
		num_balls_on_left = 0
		num_balls_on_right = 0
		# 2 balls to the left, red
		for x in pts:
			if abs(320 - x[0][0]) > 20:
				if x[0][0] < 320:
					num_balls_on_left += 1
				elif x[0][0] > 320:
					num_balls_on_right += 1
		if num_balls_on_left == 2:
			color = "red"
		# 2 balls to the right, blue
		elif num_balls_on_right == 2:
			color = "blue"

	if color is not None:
		datatable.putString("color", color)
		print("Color: " + color)

	# show the frame to our screen
	cv2.imshow("Frame", frame)
	key = cv2.waitKey(1) & 0xFF

	# if the 'q' key is pressed, stop the loop
	if key == ord("q"):
		break

# if we are not using a video file, stop the camera video stream
if not args.get("video", False):
	vs.stop()

# otherwise, release the camera
else:
	vs.release()

# close all windows
cv2.destroyAllWindows()