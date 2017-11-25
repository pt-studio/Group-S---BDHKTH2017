#include <ESP8266WiFi.h>
#include <DNSServer.h>
#include <ESP8266WebServer.h>
#include <WiFiManager.h> 
#include <ESP8266HTTPClient.h>
#include <ArduinoJson.h>

String token;

void setup() {
  Serial.begin(115200);
  WiFiManager wifiManager;
  if(!wifiManager.autoConnect("Smart-Pot")) {
    Serial.println("failed to connect and hit timeout");
    //reset and try again, or maybe put it to deep sleep
    ESP.reset();
    delay(2000);
  }
  else {
    HTTPClient http;  //Declare an object of class HTTPClient
    http.begin("http://1a502337.ngrok.io/api/v1/token/?codename=leminhson-sp-2");
    http.addHeader("Content-Type", "application/json");                             
    
    while(http.GET() != 200) {
        Serial.println("Cannot get token");
        delay(2000);
    }
    token = http.getString();
    Serial.println("Connect successfully");
    http.end();
  }
}

void loop() {
  HTTPClient http;  //Declare an object of class HTTPClient
  http.begin("http://1a502337.ngrok.io/api/v1/user_device/3/");  //Specify request destination
  int humidity_value = analogRead(A0);
  StaticJsonBuffer<200> MainJsonBuffer, SubJsonBuffer;
  JsonObject& data = MainJsonBuffer.createObject();
  JsonObject& attribute = SubJsonBuffer.createObject();
  data["code_name"] = "leminhson-sp-2";
  data["status"] = "working";
  attribute["safe_value"] = 0;
  attribute["humidity_value"] = humidity_value;
  data["attribute"] = attribute;
  data.printTo(Serial);
  char buffer[200];
  data.printTo(buffer, sizeof(buffer));
  http.addHeader("Content-Type", "application/json");
  http.addHeader("Authorization", "Token token");
  int httpCode = http.POST(buffer);                                                                  //Send the request

  if (httpCode > 0) { //Check the returning code

    String payload = http.getString();   //Get the request response payload
    Serial.println(payload);                     //Print the response payload

 }

  http.end();   //Close connection
  delay(3000);

}
