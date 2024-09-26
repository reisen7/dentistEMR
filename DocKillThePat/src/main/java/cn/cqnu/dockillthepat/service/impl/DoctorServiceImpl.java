package cn.cqnu.dockillthepat.service.impl;

import cn.cqnu.dockillthepat.commen.ReturnInfo;
import cn.cqnu.dockillthepat.mapper.DepartmentMapper;
import cn.cqnu.dockillthepat.mapper.DoctorMapper;
import cn.cqnu.dockillthepat.pojo.Department;
import cn.cqnu.dockillthepat.pojo.Doctor;
import cn.cqnu.dockillthepat.pojo.Patient;
import cn.cqnu.dockillthepat.service.DoctorService;
import cn.cqnu.dockillthepat.utils.FileUploadUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 刘良杰
 */
@Service
public class DoctorServiceImpl implements DoctorService {

    @Resource
    private DoctorMapper doctorMapper;
    @Resource
    private ReturnInfo returnInfo;
    @Resource
    private Doctor doctor;
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private FileUploadUtil fileUploadUtil;

    @Override
    public ReturnInfo doctorLogin(Doctor doctor) {
        try {
            QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("account", doctor.getAccount());
            queryWrapper.eq("password", doctor.getPassword());
            List<Doctor> doctors = doctorMapper.selectList(queryWrapper);
            System.out.println(doctors.size());
            if (doctors.size() != 1) {
                returnInfo.data = null;
                returnInfo.message = "LoginError";
            } else {
                returnInfo.data = doctors.get(0);
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
    public ReturnInfo docComeToDoctor() {
        try {
            List<Doctor> doctors = doctorMapper.selectDoctorsWithDepartment();
            if (doctors.size() == 0) {
                returnInfo.data = null;
                returnInfo.message = "noData";
            } else {
                returnInfo.data = doctors;
                returnInfo.message = "success";
            }
            return returnInfo;
        } catch (Exception e) {
            e.printStackTrace();
            returnInfo.message = "DBERROR";
            returnInfo.data = null;
            return returnInfo;
        }

    }

    @Override
    public ReturnInfo docReNewDoctor(Doctor doctor) {
        try {
            QueryWrapper<Department> wrapper = new QueryWrapper<>();
            wrapper.eq("name", doctor.getDepartment());
            Department department = departmentMapper.selectOne(wrapper);
            doctor.setDepartmentid(department.getId());
            int result = doctorMapper.updateById(doctor);
            if (result != 1) {
                returnInfo.message = "failed";
                returnInfo.data = doctor;
            } else {
                this.doctor = doctorMapper.selectById(doctor.getId());
                returnInfo.data = this.doctor;
                returnInfo.message = "success";
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
    public ReturnInfo docAddDoctor(MultipartFile file, String department, String name, Integer age, String sex, Long account, String password, String authority, String introduction, String title) {
        Doctor doctor = new Doctor();
        try {
            doctor.setAccount(account);
            doctor.setPassword(password);
            doctor.setName(name);
            doctor.setAge(age);
            doctor.setSex(sex);
            doctor.setAuthority(authority);
            doctor.setIntroduction(introduction);
            doctor.setTitle(title);

            QueryWrapper<Department> wrapper = new QueryWrapper<>();
            wrapper.eq("name", department);
            Department department1 = departmentMapper.selectOne(wrapper);
            doctor.setDepartmentid(department1.getId());
            QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("account", doctor.getAccount());
            List<Doctor> doctors = doctorMapper.selectList(queryWrapper);
            if (doctors.size() != 0) {
                returnInfo.message = "hasRegisterAccount";
                returnInfo.data = null;
                return returnInfo;
            }
            if (file != null) {
                String img = fileUploadUtil.docProfileUpload(file);
                doctor.setImg(img);
            }
            int ret = doctorMapper.insert(doctor);
            if (ret != 1) {
                returnInfo.message = "RegisterFailed";
                returnInfo.data = null;
            } else {
                returnInfo.message = "RegisterSuccess";
                returnInfo.data = doctor;
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
    public ReturnInfo docDeleteDoctor(Integer id) {
        try {
            doctorMapper.deleteById(id);
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
