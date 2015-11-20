@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')
import wslite.soap.*

def ans
def temp
def type

def client = new SOAPClient('http://www.w3schools.com/webservices/tempconvert.asmx')

BufferedReader inputtype = new BufferedReader(new InputStreamReader(System.in))

for(;;){
  
  println "Type of Converter\n[1]Celsius to Farenheit\n[2]Farenheit to Celsius\n or exit "
  println "Type of converter: "
  type = inputtype.readLine()
  
if(type == "1"){
  
  println "Input Celsius: "
  temp = inputtype.readLine()
  try{
  def response=client.send(SOAPAction:'http://www.w3schools.com/webservices/CelsiusToFahrenheit'){
    body{
      CelsiusToFahrenheit('xmlns':'http://www.w3schools.com/webservices/'){
        Celsius(temp)
      }
    }
  }
  println "Fahrenheit: " + response.CelsiusToFahrenheitResponse.CelsiusToFahrenheitResult
  }
  catch (Exception e){
    println e
  }
}
else if(type == "2"){
  println "Input Farenheight: "
  temp = inputtype.readLine()
  try{
  def response=client.send(SOAPAction:'http://www.w3schools.com/webservices/FahrenheitToCelsius'){
    body{
      FahrenheitToCelsius('xmlns':'http://www.w3schools.com/webservices/'){
        Fahrenheit(temp)
      }
    }
  }
  println "Celsius: " + response.FahrenheitToCelsiusResponse.FahrenheitToCelsiusResult
  }
  catch(Exception e){
      println e
    }
  }
else if(type == "exit"){
  break
}

else{
  println "Invalid input try again\n Yes or No"
  ans = inputtype.readLine()
  if(ans=="no")
  break

}

}

//assert response
//assert 200 == response.httpResponse.statusCode
class tria {

}
