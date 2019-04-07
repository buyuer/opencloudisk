package ding.opencloudisk.controller;

import ding.opencloudisk.bean.Fileinfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class FileinfoController {

    @RequestMapping(value = "/fileinfo",method = RequestMethod.GET)
    public Fileinfo fileinfo(@RequestParam(value = "path",required = true)String path, HttpServletRequest request) {
        String journal = "[\n" +
                "  method: " + request.getMethod() + "\n" +
                "  url   : " + request.getRequestURI() + "\n" +
                "  path  : " + path + "\n" +
                "  ip    : " + request.getRemoteAddr() + "\n" +
                "  host  : " + request.getRemoteHost() + "\n" +
                "  date  : " + new Date().toString() + "\n";
        Fileinfo temp = new Fileinfo(path);
        if(temp.getInfo() == null){
            journal += "  status: dir is not existed\n";
        }
        else{
            journal += "  status: successfully\n";
        }

        System.out.println(journal + "]\n");

        return temp;
    }
}