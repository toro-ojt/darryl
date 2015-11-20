@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')
import wslite.soap.*

def tempType
def temp
def response
def answer

def client = new SOAPClient('http://www.w3schools.com/webservices/tempconvert.asmx')

BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))

for(;;) {
	println "\t [1] - Celcius to Farenheit\n\t [2] - Farenheit to Celcius"
	
	println "Choose a conversion: "
	tempType = userInput.readLine()
	
	if(tempType == "1") {
		println "Input Celsius: "
		temp = userInput.readLine()
		try{
		response = client.send(SOAPAction:'http://www.w3schools.com/webservices/CelsiusToFahrenheit'){
			body{
				CelsiusToFahrenheit('xmlns':'http://www.w3schools.com/webservices/'){
					Celsius(temp)
					}
				}
			}
			answer = response.CelsiusToFahrenheitResponse.CelsiusToFahrenheitResult
			println "Fahrenheit: " + answer + "\n"
		} catch (Exception e){
			println e
		}
		
	} else if(tempType == "2") {
		println "Input Farenheit: "
		temp = userInput.readLine()
		try{
		response = client.send(SOAPAction: "http://www.w3schools.com/webservices/FahrenheitToCelsius"){
			body{
				FahrenheitToCelsius('xmlns':'http://www.w3schools.com/webservices/'){
					Fahrenheit(temp)
					}
				}
			}
			answer = response.FahrenheitToCelsiusResponse.FahrenheitToCelsiusResult
			println "Celcius: " + answer +"\n"
		} catch (Exception e){
			println e
		}
		
	} else {
		println "Invalid input"
	}
}	