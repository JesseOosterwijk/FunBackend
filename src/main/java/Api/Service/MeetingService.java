package Api.Service;

import Api.Entity.Meeting;
import Api.JpaRepository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;

    @Autowired
    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public void createOrUpdateMeeting(Meeting meeting)
    {
        try {
            meetingRepository.save(meeting);
        } catch(Exception e) {
            throw e;
        }
    }

    public void deleteMeeting(Meeting meeting) {
        try {
            meetingRepository.delete(meeting);
        } catch(Exception e) {
            throw e;
        }
    }

    public Meeting findMeetingById(int id) {
        try {
            return meetingRepository.findMeetingById(id);
        } catch(Exception e) {
            throw e;
        }
    }
}
