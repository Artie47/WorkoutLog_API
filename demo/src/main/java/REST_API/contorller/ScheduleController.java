package REST_API.contorller;


import REST_API.model.Schedule;
import REST_API.model.User;
import REST_API.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @GetMapping("/schedule/getLessons")
    public List<Schedule> getLessons(@RequestBody User user){ return scheduleRepository.getLessonsOnMonth(user);}
}
