/**
 *  Polling App
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
    name: "Polling App",
    namespace: "",
    author: "Phil Bianco",
    description: "This is testing polling devices. ",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")


preferences {
	section("Polling Group") {
            input "group", "capability.polling", title:"Select devices to be polled", multiple:true, required:false
            input "refresh", "capability.refresh", title:"Select devices to be refreshed", multiple:true, required:false
            input "interval", "number", title:"Set polling interval (in minutes)", defaultValue:5
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
// This is a test for Git. 
// This is another test for Git ... Making changes in Git instead of Smartthings IDE
