package ding.opencloudisk.bean;

import ding.opencloudisk.OwnConfig;

import java.io.File;

public class Fileinfo {

    private String path;
    private String[][] info;

    /*Constructor*/
    public Fileinfo(){}

    public Fileinfo(String path){
        this.path = path;
        File file = new File(OwnConfig.rootPath + path);
        if(file.isDirectory()){
            File[] temp = file.listFiles();
            info = new String[temp.length][4];

            for(int i = 0;i < temp.length;i++){
                info[i][0] = temp[i].getName();
                if(temp[i].isDirectory()){
                    info[i][1] = "D";
                }else if (temp[i].isFile()){
                    info[i][1] = "F";
                }
                info[i][2] = String.valueOf(temp[i].length());
                info[i][3] = "";
            }
        }
    }

    /*Getter*/
    public String getPath() {
        return path;
    }

    public String[][] getInfo() {
        return info;
    }

    /*Setter*/
    public void setPath(String path) {
        this.path = path;
    }

    public void setInfo(String[][] info) {
        this.info = info;
    }
}