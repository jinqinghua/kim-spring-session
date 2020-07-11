package kim.demo.springsession.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    FindByIndexNameSessionRepository<? extends Session> findByIndexNameSessionRepository;

    @GetMapping("")
    public Map<String, Object> list() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "spring-session-jdbc");
        return map;
    }

    @RequestMapping("/{username}")
    @ResponseBody
    public Map<String, ? extends Session> findByUsername(@PathVariable String username) {
        return findByIndexNameSessionRepository.findByPrincipalName(username);
    }
}
