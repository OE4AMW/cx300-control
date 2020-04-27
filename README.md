# Polycom CX300 Control

This project is a demo application for controlling a Polycom CX300 USB phone using the USB HID protocol. It can intercept keypresses, audio state and set the display text. 

Detailed protocol specifications can be found at bsmulders' blog post: [Reverse engineering the Polycom CX300 USB phone](https://bsmulders.com/2018/04/polycom-cx300/)

My CX300 with Firmware 01.10.6.03 (used with Ubuntu 18.4) showed some differences in the protocol, which I tried to tackle in this fork (see 'Changes'). All changes should be backwards-compatible, but I can't test this.

## Changes
This fork has following changes:

* compiles on ubuntu with java 11
* adds support for international (non-ASCII) characters on the display (it seems that the phone supports UTF16)
* recognizes keys also when handset is lifted
* handles message when mic-input is selected (maybe a speciality of linux, or firmware version)
* tried to add robustness against unknown messages and device errors
* adds voicemail-LED
* adds additional colors to the status-LEDs
* simulates a keyboard! (requires linux and xdotool)


## Usage

To start the project, use the spring-boot-maven-plugin in the terminal:

    mvn spring-boot:run

The application will start and display a clock on the plugged-in Polycom CX300 phone. Keypresses and audio state are written to the console.
