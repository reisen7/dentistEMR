package cn.cqnu.dockillthepat.service;

import cn.cqnu.dockillthepat.commen.ReturnInfo;
import cn.cqnu.dockillthepat.pojo.AdminUser;
import org.springframework.stereotype.Service;

/**
 * @author 刘良杰
 */
@Service
public interface AdminUserService {
    ReturnInfo adminLogin(AdminUser adminUser);
    ReturnInfo adminFaceLogin(Integer id);
}
