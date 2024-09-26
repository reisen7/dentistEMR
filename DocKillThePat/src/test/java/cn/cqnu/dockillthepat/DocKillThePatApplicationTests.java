package cn.cqnu.dockillthepat;

import cn.cqnu.dockillthepat.commen.ReturnInfo;
import cn.cqnu.dockillthepat.mapper.AdminUserMapper;
import cn.cqnu.dockillthepat.mapper.DiagnoseMapper;
import cn.cqnu.dockillthepat.mapper.DrugMapper;
import cn.cqnu.dockillthepat.mapper.PatientMapper;
import cn.cqnu.dockillthepat.pojo.AdminUser;
import cn.cqnu.dockillthepat.pojo.Diagnose;
import cn.cqnu.dockillthepat.pojo.Drug;
import cn.cqnu.dockillthepat.pojo.Patient;
import cn.cqnu.dockillthepat.service.AdminUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@SpringBootTest
class DocKillThePatApplicationTests {

    @Autowired
    PatientMapper patientMapper;

    @Autowired
    DiagnoseMapper diagnoseMapper;

    @Autowired
    DrugMapper drugMapper;
    @Autowired
    AdminUser adminUser;
    @Autowired
    AdminUserService adminUserService;
    @Autowired
    ReturnInfo returnInfo;

    @Test
    public void test01(){
        QueryWrapper<Patient> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "病");
        List<Patient> patientList = patientMapper.selectList(null);
        patientList.forEach(System.out::println);
    }

    @Test
    public void test02(){
        QueryWrapper<Drug> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("id", 1);
        Drug drug1 = drugMapper.selectOne(queryWrapper2);
        System.out.println(drug1);
//        List<Case> caseList = caseMapper.selectList(queryWrapper2);
//        caseList.forEach(System.out::println);
    }

    @Test
    public  void  test03(){
        try{
            String path ="D:\\Python_WorkSpace\\imgIdentify\\shibie.py";
            String[] args1 = new String[]{"python", path};
            Process proc = null;
            proc = Runtime.getRuntime().exec(args1);
            proc.waitFor();
            String result = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            if ((result = in.readLine()) != null) {
                returnInfo= adminUserService.adminFaceLogin(Integer.parseInt(result.trim()));
                System.out.println(returnInfo.message);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(returnInfo.message);
    }


}
