package Pojo;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class GetCourseRespPojo {

	//Get Course details is response of Get from OAuth Authorization API Server
	//Deserialization & Serialization involves POJO Classes, Java Object, Rest Assured, Json Body Req/Resp
	//Serialization - Java Object ---> Request Body(PayLoad)
	//Deserialization - Response Body ---> Java Object
	//PayLoad can be JSON/XML
	//And also used Jackson Library for Ser&Deser
	
	private String url;
	private String services;
	private String expertise;
	private Courses courses;
	private String instructor;
	private String linkedIn;
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public Courses getCourses() {
		return courses;
	}
	public void setCourses(Courses courses) {
		this.courses = courses;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getLinkedin() {
		return linkedIn;
	}
	public void setLinkedin(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
	
	//Alt Shift S for generating getters setters shortcut in eclipse
	
}

//Json Response of Get method from OAuth 
//Complex Json
/*{
    "instructor": "RahulShetty",
    "url": "rahulshettycademy.com",
    "services": "projectSupport",
    "expertise": "Automation",
    "courses": {
        "webAutomation": [
            {
                "courseTitle": "Selenium Webdriver Java",
                "price": "50"
            },
            {
                "courseTitle": "Cypress",
                "price": "40"
            },
            {
                "courseTitle": "Protractor",
                "price": "40"
            }
        ],
        "api": [
            {
                "courseTitle": "Rest Assured Automation using Java",
                "price": "50"
            },
            {
                "courseTitle": "SoapUI Webservices testing",
                "price": "40"
            }
        ],
        "mobile": [
            {
                "courseTitle": "Appium-Mobile Automation using Java",
                "price": "50"
            }
        ]
    },
    "linkedIn": "https://www.linkedin.com/in/rahul-shetty-trainer/"
}*/




































