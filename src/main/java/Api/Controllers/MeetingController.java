package Api.Controllers;

import Api.Entity.Meeting;
import Api.Service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MeetingController {

    private final MeetingService meetingService;

    @Autowired
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/saveMeeting")
    public ResponseEntity SaveMeeting(@Valid @RequestBody Meeting meeting)
    {
        try {
            meetingService.createOrUpdateMeeting(meeting);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/deleteMeeting")
    public ResponseEntity DeleteMeeting(@Valid @RequestBody Meeting meeting)
    {
        try {
            meetingService.deleteMeeting(meeting);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
