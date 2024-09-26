package cn.cqnu.dockillthepat.service.impl;

import cn.cqnu.dockillthepat.commen.ReturnInfo;
import cn.cqnu.dockillthepat.mapper.DiagnoseMapper;
import cn.cqnu.dockillthepat.mapper.PatientMapper;
import cn.cqnu.dockillthepat.pojo.Device;
import cn.cqnu.dockillthepat.pojo.Diagnose;
import cn.cqnu.dockillthepat.pojo.Patient;
import cn.cqnu.dockillthepat.service.PatientService;
import cn.cqnu.dockillthepat.utils.FileUploadUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘良杰
 */
@Service
public class PatientServiceImpl implements PatientService {
    @Resource
    private PatientMapper patientMapper;
    @Resource
    private DiagnoseMapper diagnoseMapper;
    @Resource
    private ReturnInfo returnInfo;
    @Resource
    private Patient patient;
    @Resource
    private FileUploadUtil fileUploadUtil;

    @Override
    public ReturnInfo patientRegister(Long account, String password, String name, Integer age, String sex, String address, String classify, String anamnesis, MultipartFile profile) {
        try {
            patient.setAccount(account);
            patient.setPassword(password);
            patient.setName(name);
            patient.setAge(age);
            patient.setSex(sex);
            patient.setClassify(classify);
            patient.setAnamnesis(anamnesis);
            patient.setAddress(address);
            QueryWrapper<Patient> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("account", patient.getAccount());
            List<Patient> patients = patientMapper.selectList(queryWrapper);
            if (patients.size() != 0) {
                returnInfo.message = "hasRegisterAccount";
                returnInfo.data = null;
                return returnInfo;
            }
            if (profile != null) {
                String file = fileUploadUtil.docProfileUpload(profile);
                patient.setProfile(file);
            }
            int ret = patientMapper.insert(patient);
            System.out.println(ret);
            if (ret != 1) {
                returnInfo.message = "RegisterFailed";
                returnInfo.data = null;
            } else {
                returnInfo.message = "RegisterSuccess";
                returnInfo.data = patient;
            }
            return returnInfo;
        } catch (Exception e) {
            returnInfo.message = "ERROR";
            returnInfo.data = null;
            e.printStackTrace();
            return returnInfo;
        }

    }

    @Override
    public ReturnInfo patientLogin(Patient patient) {
        try {
            QueryWrapper<Patient> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("account", patient.getAccount());
            queryWrapper.eq("password", patient.getPassword());
            List<Patient> patients = patientMapper.selectList(queryWrapper);
            System.out.println(patients.size());
            if (patients.size() != 1) {
                returnInfo.data = null;
                returnInfo.message = "LoginError";
            } else {
                returnInfo.data = patients.get(0);
                returnInfo.message = "LoginSuccess";
            }
            return returnInfo;
        } catch (Exception e) {
            returnInfo.message = "ERROR";
            returnInfo.data = null;
            e.printStackTrace();
            return returnInfo;
        }
    }

    @Override
    public ReturnInfo PatientSelect(Patient patient) {
        QueryWrapper<Patient> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", patient.getId());
        Patient patient1 = patientMapper.selectOne(queryWrapper);
        if(patient1!=null) {
            returnInfo.data=patient1;
            returnInfo.message="Success";
        }else{
            returnInfo.data=null;
            returnInfo.message="Failure";
        }
        return returnInfo;
    }

    @Override
    public ReturnInfo PatientSelectOne(Integer id) {

        Patient patient = patientMapper.selectPatient(id);
        if(patient!=null) {
            returnInfo.data=patient;
            returnInfo.message="Success";
        }else{
            returnInfo.data=null;
            returnInfo.message="Failure";
        }
        return returnInfo;
    }

    @Override
    public ReturnInfo PatientSelectAll() {
        try {
            List<Patient> patients = patientMapper.selectList(null);
            if (patients.size() != 0) {
                returnInfo.message = "RegisterFailed";
                returnInfo.data = patients;
            } else {
                returnInfo.message = "RegisterSuccess";
                returnInfo.data = null;
            }
            return returnInfo;
        } catch (Exception e) {
            e.printStackTrace();
            returnInfo.data = null;
            returnInfo.message = "DBERROR";
            return returnInfo;
        }
    }

    @Override
    public ReturnInfo patientUpdate(Patient patient) {
        UpdateWrapper<Patient> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", patient.getId()); // 匹配条件：id=1
        Patient patient1 = patientMapper.selectOne(wrapper);
        patient.setAccount(patient1.getAccount());
        patient.setAnamnesis(patient1.getAnamnesis());
        // 调用 update 方法更新数据
        int rows = patientMapper.update(patient, wrapper);
        if(rows!=0){
            returnInfo.data = patient.getId();
            returnInfo.message="Success";
        }
        return returnInfo;
    }

    @Override
    public ReturnInfo patientCaseSelect(Patient patient) {
        QueryWrapper<Patient> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("id", patient.getId());
        Patient patient1 = patientMapper.selectOne(queryWrapper1);
        if (patient1 != null) {
            QueryWrapper<Diagnose> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("patid", patient.getId());
            List<Diagnose> diagnoseList = diagnoseMapper.selectList(queryWrapper2);
            List<Object> objectList = new ArrayList<>();
            objectList.add(patient1);
            objectList.add(diagnoseList);
            returnInfo.data = objectList;
            returnInfo.message = "Success";
        } else {
            returnInfo.data = null;
            returnInfo.message="Failure";
        }
        return returnInfo;
    }

    @Override
    public ReturnInfo patientCaseUpdate(Diagnose diagnose) {
        Diagnose diagnose1 = diagnoseMapper.selectById(diagnose.getId());
        diagnose.setPatid(diagnose1.getPatid());
        diagnose.setDocid(diagnose1.getDocid());
        diagnose.setDate(diagnose1.getDate());
        diagnose.setCost(diagnose1.getCost());
        diagnose.setDrug(diagnose1.getDrug());
        diagnose.setDevice(diagnose1.getDevice());
        diagnose.setType(diagnose1.getType());
        try {
            int ret = diagnoseMapper.updateById(diagnose);
            if (ret > 0){
                returnInfo.data = null;
                returnInfo.message = "success";
                return returnInfo;
            }else{
                returnInfo.data = null;
                returnInfo.message = "Failure";
                return returnInfo;
            }

        } catch (Exception e) {
            e.printStackTrace();
            returnInfo.data = null;
            returnInfo.message = "DBERROR";
            return returnInfo;
        }
    }

    @Override
    public ReturnInfo patientCaseInfoDelete(Integer id) {
        try {
            diagnoseMapper.deleteById(id);
            returnInfo.data = null;
            returnInfo.message = "success";
            return returnInfo;
        } catch (Exception e) {
            e.printStackTrace();
            returnInfo.data = null;
            returnInfo.message = "DBERROR";
            return returnInfo;
        }
    }

    @Override
    public ReturnInfo patientCaseInfoInsert(Diagnose diagnose) {
        try {
            int ret = diagnoseMapper.insert(diagnose);
            if (ret > 0){
                returnInfo.data = null;
                returnInfo.message = "success";
                return returnInfo;
            }else{
                returnInfo.data = null;
                returnInfo.message = "Failure";
                return returnInfo;
            }

        } catch (Exception e) {
            e.printStackTrace();
            returnInfo.data = null;
            returnInfo.message = "DBERROR";
            return returnInfo;
        }    }

    @Override
    public ReturnInfo patientCaseDelete(Integer id) {
        try {
            patientMapper.deleteById(id);
            returnInfo.data = null;
            returnInfo.message = "success";
            return returnInfo;
        } catch (Exception e) {
            e.printStackTrace();
            returnInfo.data = null;
            returnInfo.message = "DBERROR";
            return returnInfo;
        }
    }
}
