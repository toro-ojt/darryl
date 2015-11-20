@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')
import wslite.soap.*

def choice
def ipAdd
def response

def client = new SOAPClient('http://www.webservicex.net/geoipservice.asmx')

BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))

for(;;) {
	println "\n[1] - Get IP Location \n[2] - Get My IP Location\n[3] - Exit"
	print "Choose option: "
	choice = userInput.readLine()
	
	if(choice == "1") {
		print "Input IP Address: "
		ipAdd = userInput.readLine()
		
		try{
			response = client.send(SOAPAction: "http://www.webservicex.net/GetGeoIP"){
				body {
					GetGeoIP("xmlns":"http://www.webservicex.net/"){
						IPAddress(ipAdd)
					}
				}
			}
			
			if(response.GetGeoIPResponse.GetGeoIPResult.ReturnCode == 1) {
				println "\n\tCountry Name: "+ response.GetGeoIPResponse.GetGeoIPResult.CountryName +" \n\tCountry Code: "+ response.GetGeoIPResponse.GetGeoIPResult.CountryCode+"\n"
			} else {
				println "Invalid IP Address!"
			}
			
			
		} catch(Exception e) {
			println e
		}
	} else if (choice == "2") {
		try{
			response = client.send(SOAPAction: "http://www.webservicex.net/GetGeoIPContext"){
				body {
					GetGeoIPContext("xmlns":"http://www.webservicex.net/"){
						
					}
				}
			}
			
			println "\n\tIP Address: " + response.GetGeoIPContextResponse.GetGeoIPContextResult.IP
			println "\tCountry Name: " + response.GetGeoIPContextResponse.GetGeoIPContextResult.CountryName
			println "\tCountry Code: " + response.GetGeoIPContextResponse.GetGeoIPContextResult.CountryCode
			println ""
			
		} catch(Exception e) {
			
		}
	} else if(choice == "3") {
		break
	} else {
		println "Invalid input!"
	}
}