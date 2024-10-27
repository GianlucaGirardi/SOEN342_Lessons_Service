package AccountManager;

import lessonServices.LessonCatalog;
import lessonServices.OfferingCatalog;

public class Administrator {
	private static Administrator admin;
	private OfferingCatalog offeringCatalog;
	private LessonCatalog lessonCatalog;
	private ClientCatalog clientCatalog;
	private InstructorCatalog instructorCatalog;
	
	private Administrator() {}
	
	private Administrator(OfferingCatalog offeringCatalog, LessonCatalog lessonCatalog, ClientCatalog clientCatalog, InstructorCatalog instructorCatalog) {
		this.offeringCatalog = offeringCatalog;
		this.lessonCatalog = lessonCatalog;
		this.clientCatalog = clientCatalog;
		this.instructorCatalog = instructorCatalog;
	}
	
	public static Administrator getAdministrator(OfferingCatalog offeringCatalog, LessonCatalog lessonCatalog, ClientCatalog clientCatalog, InstructorCatalog instructorCatalog) {
		if(admin == null) {
			admin = new Administrator(offeringCatalog, lessonCatalog, clientCatalog, instructorCatalog);
		}
		return admin;
	}
	
	public OfferingCatalog getOfferingCatalog() {
        return offeringCatalog;
    }

    public LessonCatalog getLessonCatalog() {
        return lessonCatalog;
    }

    public ClientCatalog getClientCatalog() {
        return clientCatalog;
    }

    public InstructorCatalog getInstructorCatalog() {
        return instructorCatalog;
    } 
}
