package cn.cqnu.dockillthepat.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author 刘良杰
 */
@Component
public class FileUploadUtil {
    /**
     * @param file 文件
     * @author刘良杰
     */

    public String docProfileUpload(MultipartFile file) throws Exception {
        String originalFileName = file.getOriginalFilename();
        System.out.println(originalFileName);
        //文件写入目录
        String mdir = System.getProperty("user.dir");
        String filePath = mdir + "/src/main/resources/static/file/";
        File directory = new File(filePath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File writeFile = new File(directory +"/"+ originalFileName);
        file.transferTo(writeFile);
        return "http://localhost:8909/proFile/" +originalFileName;
    }
}
