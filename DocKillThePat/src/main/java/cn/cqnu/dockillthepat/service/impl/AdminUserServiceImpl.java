package cn.cqnu.dockillthepat.service.impl;

import cn.cqnu.dockillthepat.commen.ReturnInfo;
import cn.cqnu.dockillthepat.mapper.AdminUserMapper;
import cn.cqnu.dockillthepat.mapper.DoctorMapper;
import cn.cqnu.dockillthepat.pojo.AdminUser;
import cn.cqnu.dockillthepat.pojo.Doctor;
import cn.cqnu.dockillthepat.service.AdminUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘良杰
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Resource
    private AdminUserMapper adminUserMapper;
    @Resource
    private ReturnInfo returnInfo;
    @Override
    public ReturnInfo adminLogin(AdminUser adminUser) {
        try {
            QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("account", adminUser.getAccount());
            queryWrapper.eq("password", adminUser.getPassword());
            List<AdminUser> adminUsers = adminUserMapper.selectList(queryWrapper);
            System.out.println(adminUsers.size());
            if (adminUsers.size() != 1) {
                returnInfo.data = null;
                returnInfo.message = "LoginError";
            } else {
                returnInfo.data = adminUsers.get(0);
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
    public ReturnInfo adminFaceLogin(Integer id) {
        AdminUser adminUser=adminUserMapper.selectById(id);
        if (adminUser==null){
            returnInfo.data=null;
            returnInfo.message="failed";
            return returnInfo;
        }
        returnInfo.data=adminUser.getId();
        returnInfo.message="Success";
        return returnInfo;
    }
}
