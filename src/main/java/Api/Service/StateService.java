package Api.Service;

import Api.Entity.State;
import Api.JpaRepository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateService {

    private final StateRepository stateRepository;

    @Autowired
    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public void createOrUpdateState(State state) {
        try {
            stateRepository.save(state);
        } catch(Exception e) {
            throw e;
        }
    }

    public void deleteState(State state) {
        try {
            stateRepository.delete(state);
        } catch(Exception e) {
            throw e;
        }
    }
}
