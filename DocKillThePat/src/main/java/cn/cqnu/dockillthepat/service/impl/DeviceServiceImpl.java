package cn.cqnu.dockillthepat.service.impl;

import cn.cqnu.dockillthepat.commen.ReturnInfo;
import cn.cqnu.dockillthepat.mapper.DeviceMapper;
import cn.cqnu.dockillthepat.mapper.DeviceMapper;
import cn.cqnu.dockillthepat.pojo.Device;
import cn.cqnu.dockillthepat.pojo.Device;
import cn.cqnu.dockillthepat.service.DeviceService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 刘良杰
 */
@Service
public class DeviceServiceImpl implements DeviceService {
    
    @Resource
    private DeviceMapper deviceMapper;
    @Resource
    private ReturnInfo returnInfo;
    @Resource
    private Device device;
    
    @Override
    public ReturnInfo devComeToDevice() {
        return null;
    }

    @Override
    public ReturnInfo devReNewDevice(Device device) {
        try {
            int result = deviceMapper.updateById(device);
            if (result != 1) {
                returnInfo.message = "failed";
                returnInfo.data = device;
            } else {
                this.device = deviceMapper.selectById(device.getId());
                returnInfo.data = this.device;
                returnInfo.message = "success";
            }
            return returnInfo;
        }catch (Exception e) {
            e.printStackTrace();
            returnInfo.data = null;
            returnInfo.message = "DBERROR";
            return returnInfo;
        }
    }

    @Override
    public ReturnInfo devAddDevice(Device device) {
        try {
            int ret = deviceMapper.insert(device);
            if (ret != 1) {
                returnInfo.message = "RegisterFailed";
                returnInfo.data = null;
            } else {
                returnInfo.message = "RegisterSuccess";
                returnInfo.data = device;
            }
            return returnInfo;
        }catch (Exception e) {
            returnInfo.message = "ERROR";
            returnInfo.data = null;
            e.printStackTrace();
            return returnInfo;
        }
    }

    @Override
    public ReturnInfo devDeleteDevice(Integer id) {
        try {
            deviceMapper.deleteById(id);
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
    public ReturnInfo devSelectOne(Integer id) {
        try {
            Device device = deviceMapper.selectById(id);
            if (device != null) {
                returnInfo.message = "RegisterFailed";
                returnInfo.data = device;
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
    public ReturnInfo devSelectAll() {
        try {
            List<Device> deviceList = deviceMapper.selectList(null);
            if (deviceList.size() != 0) {
                returnInfo.message = "RegisterFailed";
                returnInfo.data = deviceList;
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
}
