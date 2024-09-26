package cn.cqnu.dockillthepat.service.impl;

import cn.cqnu.dockillthepat.commen.ReturnInfo;
import cn.cqnu.dockillthepat.mapper.*;
import cn.cqnu.dockillthepat.pojo.*;
import cn.cqnu.dockillthepat.service.TellService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;


/**
 * @author 刘良杰
 */
@Service
public class TellServiceImpl implements TellService {

    @Resource
    private TellMapper tellMapper;
    @Resource
    private DoctorMapper doctorMapper;
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private DrugMapper drugMapper;
    @Resource
    private ReturnInfo returnInfo;
    @Override
    public ReturnInfo patientTellSelect(Tell tell) {
        if(tell==null || tell.getName()==null || tell.getName().equals("")){
            System.out.println("tell==null");
            List<Tell> tellList = tellMapper.selectList(null);
            if(tellList.isEmpty()){
                returnInfo.message = "Failure";
                returnInfo.data = null;
            }else{
                for(Tell tell1: tellList){
                    QueryWrapper<Doctor> queryWrapper1 = new QueryWrapper<>();
                    queryWrapper1.eq("id", tell1.getDocid());
                    Doctor doctor1 = doctorMapper.selectOne(queryWrapper1);
                    tell1.setDoctor(doctor1.getName());
                }
                returnInfo.message = "Success";
                returnInfo.data = tellList;
            }
        }else{
            System.out.println("tell!=null");
            QueryWrapper<Tell> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("name", tell.getName());
            List<Tell> tellList = tellMapper.selectList(queryWrapper);
            if(tellList.isEmpty()){
                returnInfo.message = "Failure";
                returnInfo.data = null;
            }else{
                for(Tell tell1: tellList){
                    QueryWrapper<Doctor> queryWrapper1 = new QueryWrapper<>();
                    queryWrapper1.eq("id", tell1.getDocid());
                    Doctor doctor1 = doctorMapper.selectOne(queryWrapper1);
                    tell1.setDoctor(doctor1.getName());
                }
                returnInfo.message = "Success";
                returnInfo.data = tellList;
            }
        }
        return returnInfo;
    }

    @Override
    public ReturnInfo patientTellDetail(Tell tell) {
        //查医嘱
        QueryWrapper<Tell> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("id", tell.getId());
        Tell tell1 = tellMapper.selectOne(queryWrapper1);
        //查医生
        QueryWrapper<Doctor> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("id", tell1.getDocid());
        Doctor doctor1 = doctorMapper.selectOne(queryWrapper2);
        tell1.setDoctor(doctor1.getName());
        //查科室
        QueryWrapper<Department> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("id", doctor1.getDepartmentid());
        Department department1 = departmentMapper.selectOne(queryWrapper3);
        tell1.setDepartment(department1.getName());
        //查药品
        List<Drug> drugs = new ArrayList<>();
        String[] drugIds = tell1.getDruginfo().split("-");
        for (String drugId : drugIds) {
            QueryWrapper<Drug> queryWrapper4 = new QueryWrapper<>();
            queryWrapper4.eq("id", drugId);
            Drug drug = drugMapper.selectOne(queryWrapper4);
            drugs.add(drug);
        }
        List<Object> objectList = new ArrayList<>();
        objectList.add(tell1);
        objectList.add(drugs);
        returnInfo.message="Success";
        returnInfo.data=objectList;
        return returnInfo;
    }
}
