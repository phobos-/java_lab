package pwr.databases;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class IndexController {

    @RequestMapping(value = "/", method = GET)
    public String index() {
        return "<b>Hello fdfdsfds</b>";
    }
}
