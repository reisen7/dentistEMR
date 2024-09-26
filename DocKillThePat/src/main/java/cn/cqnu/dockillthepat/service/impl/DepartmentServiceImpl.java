package cn.cqnu.dockillthepat.service.impl;

import cn.cqnu.dockillthepat.commen.ReturnInfo;
import cn.cqnu.dockillthepat.mapper.DepartmentMapper;
import cn.cqnu.dockillthepat.pojo.AdminUser;
import cn.cqnu.dockillthepat.pojo.Department;
import cn.cqnu.dockillthepat.service.DepartmentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 刘良杰
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private ReturnInfo returnInfo;
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private Department department;
    @Override
    public ReturnInfo adminComeToDepart() {
        try{
            List<Department> departments = departmentMapper.selectList(null);
            if (departments.size()==0){
                returnInfo.data=null;
                returnInfo.message="noData";
            }else {
                returnInfo.data=departments;
                returnInfo.message="success";
            }
            return returnInfo;
        }       catch (Exception e){
            e.printStackTrace();
            returnInfo.message="DBERROR";
            returnInfo.data=null;
            return returnInfo;
        }

    }

    @Override
    public ReturnInfo adminNewDepart(Department department) {
        try{
            int a= departmentMapper.insert(department);
            if(a!=1){
                returnInfo.message="failed";
                returnInfo.data=null;
            }
            else {
                returnInfo.message="success";
                returnInfo.data=department;
            }
            return returnInfo;

        }catch (Exception e){
            e.printStackTrace();
            returnInfo.data=null;
            returnInfo.message="DBERROR";
            return returnInfo;
        }
    }

    @Override
    public ReturnInfo adminDeleteDepart(Integer id) {
        try{
            int bo=departmentMapper.deleteById(id);
            returnInfo.data=null;
            if(bo!=1){
                returnInfo.message="failed";
                }else {
                returnInfo.message="success";
                }
            return returnInfo;
        }catch ( Exception e) {
            e.printStackTrace();
            returnInfo.data = null;
            returnInfo.message = "DBERROR";
            return returnInfo;
        }
    }

    @Override
    public ReturnInfo adminRenewDepart(Department department) {
        try{
            int result=departmentMapper.updateById(department);
            if(result!=1){
                returnInfo.message="failed";
                returnInfo.data=department;
            }
            else {
                this.department=departmentMapper.selectById(department.getId());
                returnInfo.data= this.department;
                returnInfo.message="success";
            }return returnInfo;
        }catch (Exception e){
            e.printStackTrace();
            returnInfo.data= null;
            returnInfo.message="DBERROR";
            return returnInfo;
        }
    }

    @Override
    public ReturnInfo adminUseDepartList() {
        try{
            QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("name");
            List<String>  departNames= departmentMapper.selectList(queryWrapper).stream().map(Department::getName).toList();
            returnInfo.message="success";
            returnInfo.data=departNames;
            return returnInfo;
        }catch (Exception e){
            e.printStackTrace();
            returnInfo.message="DBERROR";
            returnInfo.data=null;
            return returnInfo;
        }
    }

}
