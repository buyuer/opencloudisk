package ding.opencloudisk.controller;

import ding.opencloudisk.OwnConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;

@Controller
public class FileController {

    @RequestMapping(value = "/filedown",method = RequestMethod.GET)
    public String filedown( HttpServletRequest request,
                            HttpServletResponse response,
                            @RequestParam(value = "path",required = true)String path){


        String journal = "[\n" +
                "  method: " + request.getMethod() + "\n" +
                "  url   : " + request.getRequestURI() + "\n" +
                "  path  : " + path + "\n" +
                "  ip    : " + request.getRemoteAddr() + "\n" +
                "  host  : " + request.getRemoteHost() + "\n" +
                "  date  : " + new Date().toString() + "\n"
                ;


        File file = new File(OwnConfig.rootPath + path);

        if(file.exists() && file.isFile()){
            long filesize = file.length();
            long start = 0,end = filesize;
            long downsize = filesize - start;

            response.setHeader("content-type","application/octer-stream");try{
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(),"UTF-8"));}catch (Exception e){}
            response.setHeader("Accept-Ranges", "bytes");

            if(request.getHeader("Range") == null){
                response.setHeader("Content-Length",String.valueOf(downsize));

                byte[] buffer = new byte[OwnConfig.bufferSize];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    journal += "  status: no range & filedown successfully\n";
                }
                catch (Exception e) {
                    journal +=  "  status: no range & filedown failed\n" +
                            "  except: " + e.getMessage() + "\n";
                }
                finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            else {
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

                String[] ranges =   request.getHeader("Range").
                        replaceAll("bytes=","").
                        split("-");
                start = Long.parseLong(ranges[0]);
                if(ranges.length > 1){
                    end = Long.parseLong(ranges[1]);
                    downsize = end - start;
                }else {
                    downsize = filesize - start;
                }
                response.setHeader("Content-Length",String.valueOf(downsize));

                RandomAccessFile raf = null;
                OutputStream os = null;
                try{
                    raf = new RandomAccessFile(file,"r");
                    os = response.getOutputStream();
                    raf.seek(start);

                    int num = 0;
                    long count = 0;
                    byte[] buffer = new byte[OwnConfig.bufferSize];
                    while((num = raf.read(buffer)) != -1){
                        os.write(buffer,0,num);
                        count += num;
                        if(downsize - count < OwnConfig.bufferSize){
                            int buffersize = (int)(downsize - count);
                            if(buffersize <= 0){
                                break;
                            }else {
                                buffer = new byte[buffersize];
                            }
                        }
                    }
                    response.flushBuffer();

                    journal += "  status: filedown successfully  " + request.getHeader("Range") + "\n";

                }catch (Exception e){
                    journal +=  "  status: filedown failed  " + request.getHeader("Range") + "\n" +
                            "  except: " + e.getMessage() + "\n";
                }finally {
                    if(raf != null){
                        try{
                            raf.close();
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    if(os != null){
                        try{
                            os.close();
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }

            System.out.println(journal + "]\n\n");
        }
        else{
            journal += "  status: file is not existed\n";
            System.out.println(journal + "]\n");
        }

        return null;
    }

    @RequestMapping(value = "/fileup",method = RequestMethod.POST)
    public String fileup(MultipartFile[] files,@RequestParam(value = "path",required = true)String path){
        if(files.length == 0){
            return null;
        }
        else{

        }

        return null;
    }
}
