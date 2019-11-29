# SimpleMessagingAPI

Simple Messaging API Server and Client

### Tech

SimpleMessagingAPI uses a number of open source projects to work properly :

* [Java Version 8]
* [Spring Boot Starter 2] 
* [Twilio] 
* [Javascript] 
* [HTML5] 
* [jQuery] 

For complete uses check out the pom.xml

### Installation

Before start make sure you have installed Java Version 8 (Java 1.8) in your PC
Clone this repository, Install the dependencies and run from your PC with IDE like :

* [Intellij] Recomended
* [Visual Studio Code]
* [Eclipse]

If you use IDE to run the app you have to make a change in the SMSService.java code because Twilio Credentials cannot be publish in public (you can email me if you want to try), or you can use the artifact (jar files) and running in the terminal  (better ways to do it with this)
```sh
$ cd inside-directory
```
example :
```sh
$ cd Desktop/SimpleMessagingAPI
```

check the file inside must contains :
- lib folder
- app.jar files
- logback-spring.xml files

if the file is complete then do
```sh
$ java -cp lib/*:app.jar com.fariz.rest.MainApp
```

if everything goes clean and soft open your browser (Recommeded with Chrome / Firefox) and typing this address
```sh
http://localhost:8080/
```

if you want to make a REST request from curl or from postman make sure use method POST and Content-type app/json.
the request json will look like this.
- Send SMS REST
```sh
{
  "action" : "SMS",
  "subAction : "sendSMS",
  "message" : "text message you want to send"
}
```

- History SMS REST
```sh
{
  "action" : "SMS",
  "subAction : "getHistorySMS",
  "limit" : number
}
```

if theres an error or something you wanna ask feel free to catching up with me at farizardiansyah60@gmail.com


