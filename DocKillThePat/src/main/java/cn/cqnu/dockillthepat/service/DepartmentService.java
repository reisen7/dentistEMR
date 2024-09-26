package cn.cqnu.dockillthepat.service;

import cn.cqnu.dockillthepat.commen.ReturnInfo;
import cn.cqnu.dockillthepat.pojo.Department;
import org.springframework.stereotype.Service;

/**
 * @author 刘良杰
 */@Service
public interface DepartmentService {
     ReturnInfo adminComeToDepart();
     ReturnInfo adminNewDepart(Department department);
     ReturnInfo adminDeleteDepart(Integer id);
     ReturnInfo adminRenewDepart(Department department);

     ReturnInfo adminUseDepartList();
}
