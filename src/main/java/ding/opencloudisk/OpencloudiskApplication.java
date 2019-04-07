package ding.opencloudisk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpencloudiskApplication{

    public static void main(String[] args) {
        for(String i : args){
            String[] temp = i.split("=");
            if(temp[0].equals("-rootpath")){
                OwnConfig.rootPath = temp[1];
            }
        }
        SpringApplication.run(OpencloudiskApplication.class, args);
    }
}
