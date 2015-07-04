# FabLab - Manager

#Description

Fablab-manager is a fully open-source web application that allows you to easily manage a Fablab (or any makerspace). 
This is the extension of the G.Collaud FabLab-Manager application. 

You can manage :

 * Users (with membership type, group, rights on appli)
 * Machines (with machine type, state, status)
 * Machines failures (ticket) and revisions
 * Trainings over machine with certification, level, prerequisites and control of uses
 * Supplies with purchase and stock motion
 * Events with modules, user list of participant and organizer
 * Reservations
 * Calendar (display a google calendar in the reservation module)
 * Price of machines (linked to membership type and machine type)
 * Payment (when members pay you, or when the fablab has to pay)
 * Usage of machines (when member pay for your services)
 * Subscription (price depend on membership type and subscription duration is editable)
 * Audit of all action done on the platform
 * See accounting informations, user account and general journal with csv exports (All cashflow of the webApp is saved)


## Technologies
* Java 8
* Apache Tomcat 8
* MySQL
* Spring 4
* Spring-data
* Spring-security
* AngularJS

## Installation

* Install MySQL and Tomcat
* Compile from source (I will make releases eventually)
* Put fablab-config.properties in tomcat configuration directory
* Edit fablab-config.properties
* Create database schema (use Mysql workbench)
* Deploy the WAR file previously compiled

##Version
v2.0