package ding.opencloudisk.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @RequestMapping(value = "/")
    public String root(){
        return "<script>document.location = '/index.html'</script>";
    }

    @RequestMapping(value = "/about")
    public String about()
    {
        return "<script>document.location = '/about.html'</script>";
    }
}
