package cn.cqnu.dockillthepat.controller;

import cn.cqnu.dockillthepat.commen.ReturnInfo;
import cn.cqnu.dockillthepat.mapper.DoctorMapper;
import cn.cqnu.dockillthepat.pojo.Diagnose;
import cn.cqnu.dockillthepat.pojo.Doctor;
import cn.cqnu.dockillthepat.pojo.Patient;
import cn.cqnu.dockillthepat.pojo.Tell;
import cn.cqnu.dockillthepat.service.CaseService;
import cn.cqnu.dockillthepat.service.DoctorService;
import cn.cqnu.dockillthepat.service.PatientService;
import cn.cqnu.dockillthepat.service.TellService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 刘良杰
 */
@RestController
@CrossOrigin
@RequestMapping("/Patient")
public class PatientUseController {
    @Resource
    private ReturnInfo returnInfo;
    @Resource
    private PatientService patientService;
    @Resource
    private TellService tellService;
    @Resource
    private DoctorMapper doctorMapper;
    @Resource
    private CaseService caseService;

    @PostMapping("/Register")
    public ReturnInfo patientRegister(@RequestParam("account") Long account, @RequestParam("password") String password, @RequestParam("name") String name, @RequestParam("age") Integer age, @RequestParam("sex") String sex, @RequestParam("address") String address, @RequestParam("classify") String classify, @RequestParam("anamnesis") String anamnesis, @RequestParam("profile") MultipartFile profile) {
        System.out.println(account);
        System.out.println(password);
        System.out.println(name);
        System.out.println(age);
        System.out.println(sex);
        System.out.println(address);
        System.out.println(classify);
        System.out.println(anamnesis);
        System.out.println(profile);
        returnInfo = patientService.patientRegister(account, password, name, age, sex, address, classify, anamnesis, profile);
        return returnInfo;
    }

    @PostMapping("/Login")
    public ReturnInfo patientLogin(@RequestBody Patient patient) {
        System.out.println(patient);
        returnInfo = patientService.patientLogin(patient);
        return returnInfo;
    }

    //病人信息查询
    @PostMapping("/PatientSelect")
    public ReturnInfo PatientSelect(@RequestBody Patient patient) {
        System.out.println(patient);
        returnInfo = patientService.PatientSelect(patient);
        return returnInfo;
    }

    //病人信息查询单独查询
    @PostMapping("/PatientGetOne")
    public ReturnInfo PatientSelectOne(Integer id) {
        System.out.println(id);
        returnInfo = patientService.PatientSelectOne(id);
        return returnInfo;
    }

    //病人信息查询
    @PostMapping("/PatientGet")
    public ReturnInfo PatientSelectAll() {
        returnInfo = patientService.PatientSelectAll();
        return returnInfo;
    }

    //病人信息修改
    @PostMapping("/PatientUpdate")
    public ReturnInfo patientUpdate(@RequestBody Patient patient) {
        System.out.println(patient);
        returnInfo = patientService.patientUpdate(patient);
        return returnInfo;
    }

    //病人信息修改
    @PostMapping("/PatientDelete")
    public ReturnInfo patientDelete(Integer id) {
        returnInfo = patientService.patientCaseDelete(id);
        return returnInfo;
    }

    //病历信息查询
    @PostMapping("/PatientCaseSelect")
    public ReturnInfo patientCaseSelect(@RequestBody Patient patient) {
        System.out.println(patient);
        returnInfo = patientService.patientCaseSelect(patient);
        return returnInfo;
    }

    //病历信息修改
    @PostMapping("/PatientCaseUpdate")
    public ReturnInfo patientCaseUpdate(@RequestBody Diagnose diagnose) {
        System.out.println(diagnose);
        returnInfo = patientService.patientCaseUpdate(diagnose);
        return returnInfo;
    }

    //病历信息删除
    @PostMapping("/PatientCaseDelete")
    public ReturnInfo patientCaseDelete(Integer id) {
        System.out.println(id);
        returnInfo = patientService.patientCaseInfoDelete(id);
        return returnInfo;
    }

    //病历信息新增
    @PostMapping("/PatientCaseInsert")
    public ReturnInfo patientCaseInsert(@RequestBody Diagnose diagnose) {
        System.out.println(diagnose);
        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", diagnose.getDoctor());
        Doctor doctor = doctorMapper.selectOne(queryWrapper);
        if (doctor != null && !doctor.equals("")){
            diagnose.setDocid(doctor.getId());
        }else{
            returnInfo.data = null;
            returnInfo.message = "Failure";
            return returnInfo;
        }
        Date date = new Date();
        // 定义一个日期格式
        String dateFormat = "yyyy/MM/dd";
        // 使用SimpleDateFormat格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        diagnose.setDate(sdf.format(date));
        returnInfo = patientService.patientCaseInfoInsert(diagnose);
        return returnInfo;
    }

    //医嘱信息查询
    @PostMapping("/PatientTellSelect")
    public ReturnInfo patientTellSelect(@RequestBody Tell tell) {
        System.out.println(tell);
        returnInfo = tellService.patientTellSelect(tell);
        return returnInfo;
    }

    //医嘱信息详情
    @PostMapping("/PatientTellDetail")
    public ReturnInfo patientTellDetail(@RequestBody Tell tell) {
        System.out.println(tell);
        returnInfo = tellService.patientTellDetail(tell);
        return returnInfo;
    }

    //诊断信息查询
    @PostMapping("/PatientDiagSelect")
    public ReturnInfo patientDiagSelect(@RequestBody Diagnose diagnose) {
        System.out.println(diagnose);
        returnInfo = caseService.patientDiagSelect(diagnose);
        return returnInfo;
    }

    //诊断信息详情
    @PostMapping("/PatientDiagDetail")
    public ReturnInfo patientDiagDetail(@RequestBody Diagnose diagnose) {
        System.out.println(diagnose);
        returnInfo = caseService.patientDiagDetail(diagnose);
        return returnInfo;
    }
}
