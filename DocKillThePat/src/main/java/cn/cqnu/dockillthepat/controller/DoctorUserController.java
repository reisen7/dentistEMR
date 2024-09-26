package cn.cqnu.dockillthepat.controller;

import cn.cqnu.dockillthepat.commen.ReturnInfo;
import cn.cqnu.dockillthepat.pojo.Doctor;
import cn.cqnu.dockillthepat.pojo.Patient;
import cn.cqnu.dockillthepat.service.DoctorService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author 刘良杰
 */
@RestController
@CrossOrigin
@RequestMapping("/Doctor")
public class DoctorUserController {

    @Resource
    private DoctorService doctorService;
    @Resource
    private ReturnInfo returnInfo;
    @PostMapping("/Login")
    public ReturnInfo patientLogin(@RequestBody Doctor doctor){
        System.out.println(doctor);
        returnInfo=doctorService.doctorLogin(doctor);
        return returnInfo;
    }
}
