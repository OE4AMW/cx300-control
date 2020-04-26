# Polycom CX300 Control

This project is a demo application for controlling a Polycom CX300 USB phone using the USB HID protocol. It can intercept keypresses, audio state and set the display text. 

Detailed protocol specifications can be found at my blog post: [Reverse engineering the Polycom CX300 USB phone](https://bsmulders.com/2018/04/polycom-cx300/)

## Changes
This fork has following changes:

* adds support for international (non-ASCII) characters on the display


## Usage

To start the project, use the spring-boot-maven-plugin in the terminal:

    mvn spring-boot:run

The application will start and display a clock on the plugged-in Polycom CX300 phone. Keypresses and audio state are written to the console.
