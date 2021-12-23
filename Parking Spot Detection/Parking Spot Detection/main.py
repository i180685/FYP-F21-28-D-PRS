#REFERENCES:

#https://towardsdatascience.com/image-data-labelling-and-annotation-everything-you-need-to-know-86ede6c684b1
#https://theailearner.com/2018/10/15/extracting-and-saving-video-frames-using-opencv-python/
#https://stackabuse.com/reading-and-writing-yaml-to-a-file-in-python/
#https://www.pyimagesearch.com/2015/05/25/basic-motion-detection-and-tracking-with-python-and-opencv/


import argparse
import yaml
import logging
from coordinates_generator import CoordinatesGenerator
from motion_detector import MotionDetector
from colors import *


def main():
    logging.basicConfig(level=logging.INFO)

    image_file = "images/Frame2.png"
    data_file = "data/coordinates_1.yml"
    start_frame = 1
    video_file = "videos/FastParking.mp4"

    if image_file is not None:
        with open(data_file, "w+") as points:
            generator = CoordinatesGenerator(image_file, points, COLOR_RED)
            generator.generate()

    with open(data_file, "r") as data:
        points = yaml.full_load(data)
        detector = MotionDetector(video_file, points, int(start_frame))
        detector.detect_motion()


def parse_args():
    parser = argparse.ArgumentParser(description='Generates Coordinates File')

    parser.add_argument("--image",
                        dest="image_file",
                        required=False,
                        help="Image file to generate coordinates on")

    parser.add_argument("--video",
                        dest="video_file",
                        required=True,
                        help="Video file to detect motion on")

    parser.add_argument("--data",
                        dest="data_file",
                        required=True,
                        help="Data file to be used with OpenCV")

    parser.add_argument("--start-frame",
                        dest="start_frame",
                        required=False,
                        default=1,
                        help="Starting frame on the video")

    return parser.parse_args()


if __name__ == '__main__':
    main()
