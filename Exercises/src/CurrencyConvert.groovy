@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')
import wslite.soap.*

def from
def to
def response

def client = new SOAPClient('http://www.webservicex.net/CurrencyConvertor.asmx')

BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))

for(;;) {
	println "\nCURRENCY CONVERSION "
	
	print "From:\t"
	from = userInput.readLine()
	print "To:\t"
	to = userInput.readLine()
	println ""
	
	try {
		response = client.send(SOAPAction: "http://www.webserviceX.NET/ConversionRate"){
			body {
				ConversionRate("xmlns":"http://www.webserviceX.NET/"){
					FromCurrency(from.toUpperCase())
					ToCurrency(to.toUpperCase())
				}
			}
		}
		
		println "\t" + response.ConversionRateResponse.ConversionRateResult + "\n"
		
	} catch(Exception e) {
		println e
	}
}