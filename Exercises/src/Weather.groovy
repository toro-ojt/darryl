@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')
import wslite.soap.*

def choice
def userZip
def zip
def response

def client = new SOAPClient('http://wsf.cdyne.com/WeatherWS/Weather.asmx')

BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))

for(;;) {
	
	println "\n[1] - Get City Forecast\n[2] - Get City Weather\n[3] - Get Weather Information"
	print "Choose option: "
	choice = userInput.readLine()
	
	if(choice == "1") {
		print "Input ZIP code: "
		zip = userInput.readLine()
		
	
		try{
			response = client.send(SOAPAction: "http://ws.cdyne.com/WeatherWS/GetCityForecastByZIP"){
				body {
					GetCityForecastByZIP("xmlns":"http://ws.cdyne.com/WeatherWS/"){
						ZIP(zip)
					}					
				}
			}
			
			if(response.GetCityForecastByZIPResponse.GetCityForecastByZIPResult.Success == 'true') {
			
				println "State: " + response.GetCityForecastByZIPResponse.GetCityForecastByZIPResult.State
				println "City: " + response.GetCityForecastByZIPResponse.GetCityForecastByZIPResult.City
				println "Weather Station CIty: " + response.GetCityForecastByZIPResponse.GetCityForecastByZIPResult.WeatherStationCity
				
				response.GetCityForecastByZIPResponse.GetCityForecastByZIPResult.ForecastResult.Forecast.each {
					println "Date: $it.Date"
					println "Weather ID: $it.WeatherID"
					println "Description: $it.Desciption"
					println "Temperatures: $it.Temperatures.MorningLow"
					println "Datetime High:  $it.Temperatures.DaytimeHigh"
					println "Probability Percipitation: \n \tNighttime: $it.ProbabilityOfPrecipiation.Nighttime\n \tDaytime: $it.ProbabilityOfPrecipiation.Daytime"
				}
			} else {
				println "City could not be found in our weather data. Please contact CDYNE for more Details."
			}
			
		} catch(Exception e) {
			println e
		}
	} else if (choice == "2") {
		print "Input ZIP code: "
		zip = userInput.readLine()
	
		try{
			response = client.send(SOAPAction: "http://ws.cdyne.com/WeatherWS/GetCityWeatherByZIP"){
				body {
					GetCityWeatherByZIP("xmlns":"http://ws.cdyne.com/WeatherWS/"){
						ZIP(zip)
					}
				}
			}
			
			if(response.GetCityWeatherByZIPResponse.GetCityWeatherByZIPResult.Success == true) {
				println "\n\tState: " + response.GetCityWeatherByZIPResponse.GetCityWeatherByZIPResult.state
				println "\tCity: " + response.GetCityWeatherByZIPResponse.GetCityWeatherByZIPResult.WeatherStationCity
				println "\tWeather ID: " + response.GetCityWeatherByZIPResponse.GetCityWeatherByZIPResult.WeatherID
				println "\tDescription: " + response.GetCityWeatherByZIPResponse.GetCityWeatherByZIPResult.Description
				println "\tTemperature: " + response.GetCityWeatherByZIPResponse.GetCityWeatherByZIPResult.Temperature
				println "\tRelative Humidity: " + response.GetCityWeatherByZIPResponse.GetCityWeatherByZIPResult.RelativeHumidity
				println "\tWind: " + response.GetCityWeatherByZIPResponse.GetCityWeatherByZIPResult.Wind
				println "\tPreassure: " + response.GetCityWeatherByZIPResponse.GetCityWeatherByZIPResult.Preassure
				println "\tVisibility: " + response.GetCityWeatherByZIPResponse.GetCityWeatherByZIPResult.Visibility
				println "\tWind Chill: " + response.GetCityWeatherByZIPResponse.GetCityWeatherByZIPResult.Wind Chill
				println "\tRemarks: " + response.GetCityWeatherByZIPResponse.GetCityWeatherByZIPResult.Remarks
			} else {
				println "\n\tInvalid ZIP!"
			}
			
		} catch(Exception e) {
		
		}
	} else if (choice == "3") {                                                                                                            
		
		try{
			response = client.send(SOAPAction: "http://ws.cdyne.com/WeatherWS/GetWeatherInformation"){
				body {
					GetWeatherInformation("xmlns":"http://ws.cdyne.com/WeatherWS/"){
					
					}
				}
			}
			
		def result = response.GetWeatherInformationResponse.GetWeatherInformationResult.WeatherDescription
		
		result.each {
				println "\n\tWeather ID: " + it.WeatherID
				println "\tDescription: " + it.Description
				println "\tPictureURL: " + it.PictureURL + "\n"
			}
			
		} catch(Exception e) {
			println e
		}
	
	} else {
		println "\n\tInvalid input!"
	}
}
	
	
//zip 90019