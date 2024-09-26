package cn.cqnu.dockillthepat.service;

import cn.cqnu.dockillthepat.commen.ReturnInfo;
import cn.cqnu.dockillthepat.pojo.Patient;
import cn.cqnu.dockillthepat.pojo.Tell;
import org.springframework.stereotype.Service;

/**
 * @author 刘良杰
 */
@Service
public interface TellService {


    //医嘱信息查询
    ReturnInfo patientTellSelect(Tell tell);

    //医嘱信息详情
    ReturnInfo patientTellDetail(Tell tell);
}
