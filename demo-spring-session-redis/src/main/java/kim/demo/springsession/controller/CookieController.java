package kim.demo.springsession.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class CookieController {

    @GetMapping("/test/cookie")
    public String cookie(@RequestParam("browser") String browser, HttpServletRequest request, HttpSession session) {
        // 取出 session 中的 browser
        Object sessionBrowser = session.getAttribute("browser");
        if (sessionBrowser == null) {
            log.info("不存在 session，设置 browser={}", browser);
            session.setAttribute("browser", browser);
        } else {
            log.info("存在 session，browser={}", sessionBrowser.toString());
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                log.info(cookie.getName() + ":" + cookie.getValue());
            }
        }
        return "OK";
    }
}
