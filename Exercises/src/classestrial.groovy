
class classestrial {
	def userid = "105505"
	def name
	def age 
	def gender
	def civilstatus
	
	
}

class derived {
	static void main(String... args) {
	def ct = new classestrial()
	
	def user = ct.userid.toString
	print "$user"
	
	}
}
