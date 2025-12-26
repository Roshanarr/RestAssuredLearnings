package postRequestBodyApproaches.UsingPOJOClass;

public class StudentsPOJODemo {
	//variables
	String name;
	String location;
	String phone;
	String courses[];
	
	//for each variable we need to create 2 methods
		//a)getter method(get the data from the variable)
		//b)setter method(set the data into variable)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String[] getCourses() {
		return courses;
	}
	public void setCourses(String[] courses) {
		this.courses = courses;
	}
	
	
	
	
		
	

}
