package cn.cqnu.dockillthepat.controller;

import cn.cqnu.dockillthepat.commen.ReturnInfo;
import cn.cqnu.dockillthepat.pojo.AdminUser;
import cn.cqnu.dockillthepat.pojo.Department;
import cn.cqnu.dockillthepat.pojo.Doctor;
import cn.cqnu.dockillthepat.service.AdminUserService;
import cn.cqnu.dockillthepat.service.DepartmentService;
import cn.cqnu.dockillthepat.service.DoctorService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author 刘良杰
 */
@RestController
@CrossOrigin
@RequestMapping("/Admin")
public class AdminUserController {

    @Resource
    private AdminUserService adminUserService;
    @Resource
    private ReturnInfo returnInfo;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private DoctorService doctorService;

    @PostMapping("/Login")
    public ReturnInfo adminLogin(@RequestBody AdminUser adminUser){
        System.out.println(adminUser);
        returnInfo=adminUserService.adminLogin(adminUser);
        return returnInfo;
    }

    @PostMapping("gotoOperDepart")
    public ReturnInfo adminGotoOperaDepart(){
        returnInfo=departmentService.adminComeToDepart();
        System.out.println(returnInfo.data);
        return  returnInfo;
    }

    @PostMapping("newDepart")
    public ReturnInfo adminNewDepart(@RequestBody Department department){
        System.out.println(department);
        returnInfo=departmentService.adminNewDepart(department);
        return returnInfo;
    }

    @PostMapping("deleteDepart")
    public ReturnInfo adminDeleteDepart(@RequestParam("id") Integer id){
        System.out.println(id);
        returnInfo= departmentService.adminDeleteDepart(id);
        return returnInfo;
    }

    @PostMapping("reNewDepart")
    public ReturnInfo adminReNewDepart(@RequestBody Department department){
        System.out.println(department);
        returnInfo= departmentService.adminRenewDepart(department);
        return returnInfo;
    }

    @PostMapping("gotoOperDoctor")
    public ReturnInfo adminGotoOperaDoctor(){
        returnInfo=doctorService.docComeToDoctor();
        System.out.println(returnInfo.data);
        return  returnInfo;
    }
    @PostMapping("gotoEditDoctor")
    public ReturnInfo adminGotoEditDoctor(){
        returnInfo=departmentService.adminUseDepartList();
        System.out.println(returnInfo.data);
        return  returnInfo;
    }
    @PostMapping("reNewDoctor")
    public ReturnInfo adminReNewDoctor(@RequestBody Doctor doctor){
        returnInfo=doctorService.docReNewDoctor(doctor);
        System.out.println(returnInfo.data);
        return  returnInfo;
    }
    @PostMapping("addDoctor")
    public ReturnInfo adminAddDoctor(@RequestParam("img")MultipartFile file,@RequestParam("department")String department,@RequestParam("name")String name,@RequestParam("age") Integer age,@RequestParam("sex")String sex,@RequestParam("account")Long account,@RequestParam("password")String password,@RequestParam("authority")String authority,@RequestParam("introduction")String introduction,@RequestParam("title")String title){
        returnInfo=doctorService.docAddDoctor(file,department,name,age,sex,account,password,authority,introduction,title);
        System.out.println(returnInfo.data);
        return  returnInfo;
    }

    @PostMapping("deleteDoctor")
    public ReturnInfo adminDeleteDoctor(@RequestParam("id") Integer id){
        returnInfo=doctorService.docDeleteDoctor(id);
        System.out.println(returnInfo.data);
        return  returnInfo;
    }

    @PostMapping( "faceLogin")
    public ReturnInfo validateFace(@RequestBody AdminUser adminUser) {
        try{
            String path ="D:\\home\\Document\\code\\课程设计\\电子病历\\imgIdentifypy\\shibie.py";
            String[] args1 = new String[]{"python", path};
            Process proc = null;
            proc = Runtime.getRuntime().exec(args1);
            proc.waitFor();
            proc.waitFor();
            String result = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            if ((result = in.readLine()) != null) {
                returnInfo= adminUserService.adminFaceLogin(Integer.parseInt(result.trim()));
                if( returnInfo.data==adminUser.getId()){
                     returnInfo.message="Success";
                    return returnInfo;
                }
                returnInfo.message="failed";
                return returnInfo;
            }
        }catch (Exception e) {
            e.printStackTrace();
            returnInfo.message="failed";
            return returnInfo;
        }
        return returnInfo;
    }





}
