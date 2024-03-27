package pojo;

public class GetCourse {
	
	private String instructor;
	private String url;
	private String services;
	private String expertise;
	private Courses course;
	private String linkedIn;
	
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
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
		return course;
	}
	public void setCourses(Courses course) {
		this.course = course;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
	// THIS is JSON format that is converted into POJO CLASS AND THROUGH SERIALIZATION AND DE_SERIALIZATION WE CAN ACHIEVE
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
}
*/
	
	

}
