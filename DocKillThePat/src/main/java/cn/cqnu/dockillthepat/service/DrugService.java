package cn.cqnu.dockillthepat.service;

import cn.cqnu.dockillthepat.commen.ReturnInfo;
import cn.cqnu.dockillthepat.pojo.Drug;
import org.springframework.stereotype.Service;

/**
 * @author 刘良杰
 */
@Service
public interface DrugService {

    ReturnInfo drugComeToDrug();
    ReturnInfo drugReNewDrug(Drug Drug);
    ReturnInfo drugAddDrug(Drug Drug);
    ReturnInfo drugDeleteDrug(Integer id);
    ReturnInfo drugSelectOne(Integer id);
    ReturnInfo drugSelectAll();
    
}
