package cn.cqnu.dockillthepat.service;

import cn.cqnu.dockillthepat.commen.ReturnInfo;
import cn.cqnu.dockillthepat.pojo.Diagnose;
import cn.cqnu.dockillthepat.pojo.Tell;
import org.springframework.stereotype.Service;

/**
 * @author 刘良杰
 */
@Service
public interface CaseService {

    //医嘱信息查询
    ReturnInfo patientDiagSelect(Diagnose diagnose);

    //医嘱信息详情
    ReturnInfo patientDiagDetail(Diagnose diagnose);
}
