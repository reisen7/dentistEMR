package cn.cqnu.dockillthepat.service;


import cn.cqnu.dockillthepat.commen.ReturnInfo;
import cn.cqnu.dockillthepat.pojo.Diagnose;
import cn.cqnu.dockillthepat.pojo.Patient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 刘良杰
 */
@Service
public interface PatientService {
    //病人注册
     ReturnInfo patientRegister( Long account,  String password, String name, Integer age, String sex,  String address,String classify, String anamnesis, MultipartFile profile);
    //病人登录
     ReturnInfo patientLogin(Patient patient);

     //病人信息查询
    ReturnInfo PatientSelect(Patient patient);
    //病人信息查询
    ReturnInfo PatientSelectOne(Integer id);
    //病人信息查询
    ReturnInfo PatientSelectAll();

     //病人信息修改
    ReturnInfo patientUpdate(Patient patient);

    //病历信息查询
    ReturnInfo patientCaseSelect(Patient patient);

    //病历信息修改
    ReturnInfo patientCaseUpdate(Diagnose diagnose);

    //病历信息删除
    ReturnInfo patientCaseInfoDelete(Integer id);

    //病历信息新增
    ReturnInfo patientCaseInfoInsert(Diagnose diagnose);

    //病人删除
    ReturnInfo patientCaseDelete(Integer id);
}
