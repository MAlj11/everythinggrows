package cn.everythinggrows.controller.account;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class indexController {
  private Logger logger = LoggerFactory.getLogger(indexController.class);

    @RequestMapping(value = "/sindex.html",method = RequestMethod.GET)
    public String getIndex(){
        logger.info("__________________________________________进入sindex");
        return "sindex";
    }

    @RequestMapping(value = "/index.html",method = RequestMethod.GET)
    public String getInsexx(){
        return "lw-index";
    }
}
