package cn.cqnu.dockillthepat.service.impl;

import cn.cqnu.dockillthepat.commen.ReturnInfo;
import cn.cqnu.dockillthepat.mapper.*;
import cn.cqnu.dockillthepat.pojo.*;
import cn.cqnu.dockillthepat.service.CaseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author 刘良杰
 */
@Service
public class CaseServiceImpl implements CaseService {

    @Resource
    private TellMapper tellMapper;
    @Resource
    private DoctorMapper doctorMapper;
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private DrugMapper drugMapper;
    @Resource
    private PatientMapper patientMapper;
    @Resource
    private DiagnoseMapper diagnoseMapper;
    @Resource
    private ReturnInfo returnInfo;


    @Override
    public ReturnInfo patientDiagSelect(Diagnose diagnose) {
        QueryWrapper<Diagnose> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("patid", diagnose.getPatid());
        List<Diagnose> diagnoseList = diagnoseMapper.selectList(queryWrapper);
        if(diagnoseList.isEmpty()){
            returnInfo.data=null;
            returnInfo.message="Failure";
        }else{
            for(Diagnose diagnose1:diagnoseList){
                QueryWrapper<Doctor> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("id", diagnose1.getDocid());
                Doctor doctor1 = doctorMapper.selectOne(queryWrapper1);
                QueryWrapper<Department> queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.eq("id", doctor1.getDepartmentid());
                Department department1 = departmentMapper.selectOne(queryWrapper2);
                diagnose1.setDepartment(department1.getName());
                if(diagnose1.getType().equals("门诊")){
                    diagnose1.setTypeTag("");
                }else if(diagnose1.getType().equals("急诊")){
                    diagnose1.setTypeTag("success");
                }else{
                    diagnose1.setTypeTag("warning");
                }
            }
            returnInfo.data=diagnoseList;
            returnInfo.message="Success";
        }
        return returnInfo;
    }

    @Override
    public ReturnInfo patientDiagDetail(Diagnose diagnose) {
        QueryWrapper<Diagnose> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("id", diagnose.getId());
        Diagnose diagnose1 = diagnoseMapper.selectOne(queryWrapper1);
        QueryWrapper<Patient> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("id", diagnose1.getPatid());
        Patient patient1 = patientMapper.selectOne(queryWrapper2);
        QueryWrapper<Doctor> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("id", diagnose1.getDocid());
        Doctor doctor1 = doctorMapper.selectOne(queryWrapper3);
        QueryWrapper<Department> queryWrapper4 = new QueryWrapper<>();
        queryWrapper4.eq("id", doctor1.getDepartmentid());
        Department department1 = departmentMapper.selectOne(queryWrapper4);

        diagnose1.setPatName(patient1.getName());
        diagnose1.setPatSex(patient1.getSex());
        diagnose1.setPatAge(patient1.getAge());
        diagnose1.setDoctor(doctor1.getName());
        diagnose1.setDepartment(department1.getName());

        //查药品
        List<Drug> drugs = new ArrayList<>();
        String[] drugIds = diagnose1.getDrug().split("-");
        for (String drugId : drugIds) {
            QueryWrapper<Drug> queryWrapper5 = new QueryWrapper<>();
            queryWrapper5.eq("id", drugId);
            Drug drug = drugMapper.selectOne(queryWrapper5);
            drugs.add(drug);
        }

        List<Object> objectList = new ArrayList<>();
        objectList.add(diagnose1);
        objectList.add(drugs);
        returnInfo.message="Success";
        returnInfo.data=objectList;
        return returnInfo;
    }
}
