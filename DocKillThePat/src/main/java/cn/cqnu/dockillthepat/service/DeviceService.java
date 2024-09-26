package cn.cqnu.dockillthepat.service;

import cn.cqnu.dockillthepat.commen.ReturnInfo;
import cn.cqnu.dockillthepat.pojo.Device;
import cn.cqnu.dockillthepat.pojo.Device;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 刘良杰
 */
@Service
public interface DeviceService {


    ReturnInfo devComeToDevice();
    ReturnInfo devReNewDevice(Device device);
    ReturnInfo devAddDevice(Device device);
    ReturnInfo devDeleteDevice(Integer id);
    ReturnInfo devSelectOne(Integer id);
    ReturnInfo devSelectAll();
}
