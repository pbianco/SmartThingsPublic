/**
 *  Smart Lighting Control (Dynamic Page)
 *
 *  Copyright 2015 Phil Bianco
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
    name: "Smart Lighting Control (Dynamic Page)",
    namespace: "pbianco",
    author: "Phil Bianco",
    description: "App to control lights / dimmers based on lux sensor and motion detectors",
    category: "Convenience",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")


preferences {
   page(name: "pageOne", title: "Select Sensors, Switches and Dimmers", nextPage: "pageTwo", uninstall: true)
   page(name: "pageTwo", title: "Configuration Options / Settings", uninstall: true, install: true)
   		
}

def pageOne() {
	dynamicPage(name: "pageOne"){
	section {            
            input(name: "motionSensor", type: "capability.motionSensor",
            	title: "Motion sensor(s) used to active lights", description: null, multiple: true,
                required: false)            
            input(name: "switch", type: "capability.switch", title: "Switches to turn on/off",
            	description: null, multiple: true, required: false)
            input(name: "dimmer", type: "capability.switchLevel", title: "Dimmers to turn on/off",
            	description: null, multiple: true, required: false)
            input(name: "luxAnswer", type: "enum", title: "Lux Sensor (Yes/No)?",
            	options: ["No","Yes"])
            input(name: "delayAnswer", type: "enum", title: "Set off delay when motion stops (Yes/No)?",
            	options: ["No","Yes"])
        }
    }
}

def pageTwo() {
	dynamicPage(name: "pageTwo") {
    
    	if (luxAnswer == "Yes") { 
        section ("Lux Settings"){
        	input(name: "luxSensor", type: "capability.illuminanceMeasurement", title: "Lux Sensor")
            input(name: "luxLevel", type: "number", defaultValue: 50, title: "Lux Level to turn lights on",
            	range: "10..100")
        }
        }
        if (delayAnswer == "Yes"){
        section ("Motion Settings") { 
        	input(name: "offDelay", type: "number", title: "Delay after motion stops", defaultValue: 1)
        }
        }
        if (dimmer) {
        section("Dimmer Settings") {
            input(name: "dimmerLevel", type: "number", title: "Dimmer level you would like dimmer(s) to turn on", 
            	description: "Range 5 - 100 ... Default 50", defaultValue: 50, range: "5..100")
        }
        }
    }
}

def installed() {
	log.debug "Installed with settings: ${settings}"

	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"

	unsubscribe()
	initialize()
}

def initialize() {
	// TODO: subscribe to attributes, devices, locations, etc.
}

// TODO: implement event handlers