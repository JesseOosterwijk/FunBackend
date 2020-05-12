package Api.Controllers;

import Api.Entity.State;
import Api.Service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class StateController {

    private final StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/saveState")
    public ResponseEntity SaveState(@Valid @RequestBody State state)
    {
        try {
            stateService.createOrUpdateState(state);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/deleteState")
    public ResponseEntity DeleteState(@Valid @RequestBody State state)
    {
        try {
            stateService.deleteState(state);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
