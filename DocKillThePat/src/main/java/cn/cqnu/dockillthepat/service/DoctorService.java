package cn.cqnu.dockillthepat.service;

import cn.cqnu.dockillthepat.commen.ReturnInfo;
import cn.cqnu.dockillthepat.pojo.Doctor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 刘良杰
 */
@Service
public interface DoctorService {

    //登录
     ReturnInfo doctorLogin(Doctor doctor);

    ReturnInfo docComeToDoctor();
    ReturnInfo docReNewDoctor(Doctor doctor);
    ReturnInfo docAddDoctor(MultipartFile file, String department, String name, Integer age, String sex, Long account, String password, String authority, String introduction, String title);
    ReturnInfo docDeleteDoctor(Integer id);
}
