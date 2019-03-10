package cn.yummy.controller.merchantController;


import cn.yummy.entity.primitiveType.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class UploadController {
    private static final String TASK_IMAGE_FILE_ROOT = "./Image/";

    @RequestMapping("/upload")
    public Result upload(@RequestParam("picture") MultipartFile picture, HttpServletRequest request){
        //创建根目录
        if(!Files.isDirectory(Paths.get(TASK_IMAGE_FILE_ROOT))){
            try {
                Files.createDirectory(Paths.get(TASK_IMAGE_FILE_ROOT));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String idCode =(String)attributes.getRequest().getSession().getAttribute("account");
        String filePath = TASK_IMAGE_FILE_ROOT + idCode;
        //创建商家图片文件夹
        if(!Files.isDirectory(Paths.get(filePath))){
            try {
                Files.createDirectory(Paths.get(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String originalFileName = picture.getOriginalFilename();
//        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
//        System.out.println("文件类型：" + type);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        //设置文件新名称: 当前时间+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date + name + "." + type;
//        System.out.println("新文件名称：" + fileName);

        try {
            Files.copy(picture.getInputStream(), Paths.get(filePath, fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(true,"/image/dish/"+fileName);

    }


    @RequestMapping("/image/dish/{pictureName}")
    public void getImage(@PathVariable(name="pictureName") String pictureName, HttpServletResponse response) throws IOException {
//        System.out.println(pictureName);
        response.getOutputStream().write(getDishImageData(pictureName));
    }

    private static byte[]  getDishImageData(String pictureName){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String idCode =(String)attributes.getRequest().getSession().getAttribute("account");

        String visitMerchantIdCode = (String)attributes.getRequest().getSession().getAttribute("idCode");
        if (visitMerchantIdCode!=null) {
            idCode = visitMerchantIdCode;
        }

        File file =  new File(Paths.get("Image/"+idCode+"/"+pictureName).toString());
        return getImageData(file);
    }

    private static  byte[]  getImageData(File file){
        FileInputStream inputStream;

        try {
            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            inputStream.close();
            return bytes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    @RequestMapping("/image/public/{pictureName}")
    public void getPublicImage(@PathVariable(name="pictureName") String pictureName, HttpServletResponse response) throws IOException {
//        System.out.println(pictureName);
        response.getOutputStream().write(getPublicImageData(pictureName));
    }

    private static byte[]  getPublicImageData(String pictureName){

        File file =  new File(Paths.get("Image/public/"+pictureName).toString());
        return getImageData(file);
    }

}
