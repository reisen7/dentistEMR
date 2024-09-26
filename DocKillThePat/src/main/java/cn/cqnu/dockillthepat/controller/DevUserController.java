package cn.cqnu.dockillthepat.controller;

import cn.cqnu.dockillthepat.commen.ReturnInfo;
import cn.cqnu.dockillthepat.pojo.Device;
import cn.cqnu.dockillthepat.service.DeviceService;
import cn.cqnu.dockillthepat.service.DoctorService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/dev")
public class DevUserController {

    @Resource
    private DeviceService deviceService;
    @Resource
    private ReturnInfo returnInfo;

    @PostMapping("/insert")
    public ReturnInfo devInsert(@RequestBody Device device){
        returnInfo = deviceService.devAddDevice(device);
        return returnInfo;
    }

    @PostMapping("/delete")
    public ReturnInfo devDelete(@RequestParam("id")Integer id){
        returnInfo = deviceService.devDeleteDevice(id);
        return returnInfo;
    }
    @PostMapping("/update")
    public ReturnInfo devUpdate(@RequestBody Device device){
        System.out.println(device);
        returnInfo = deviceService.devReNewDevice(device);
        return returnInfo;
    }

    @PostMapping("/getOne")
    public ReturnInfo devGetOne(@RequestParam("id")Integer id){
        returnInfo = deviceService.devSelectOne(id);
        return returnInfo;
    }

    @PostMapping("/get")
    public ReturnInfo devGet(){
        returnInfo = deviceService.devSelectAll();
        return returnInfo;
    }

}
