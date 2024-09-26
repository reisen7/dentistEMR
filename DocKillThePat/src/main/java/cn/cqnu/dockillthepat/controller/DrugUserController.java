package cn.cqnu.dockillthepat.controller;

import cn.cqnu.dockillthepat.commen.ReturnInfo;
import cn.cqnu.dockillthepat.pojo.Drug;
import cn.cqnu.dockillthepat.service.DrugService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/drug")
public class DrugUserController {

    @Resource
    private DrugService drugService;
    @Resource
    private ReturnInfo returnInfo;

    @PostMapping("/insert")
    public ReturnInfo drugInsert(@RequestBody Drug drug){
        returnInfo = drugService.drugAddDrug(drug);
        return returnInfo;
    }

    @PostMapping("/delete")
    public ReturnInfo drugDelete(@RequestParam("id")Integer id){
        returnInfo = drugService.drugDeleteDrug(id);
        return returnInfo;
    }
    @PostMapping("/update")
    public ReturnInfo drugUpdate(@RequestBody Drug drug){
        System.out.println(drug);
        returnInfo = drugService.drugReNewDrug(drug);
        return returnInfo;
    }

    @PostMapping("/getOne")
    public ReturnInfo drugGetOne(@RequestParam("id")Integer id){
        returnInfo = drugService.drugSelectOne(id);
        return returnInfo;
    }

    @PostMapping("/get")
    public ReturnInfo drugGet(){
        returnInfo = drugService.drugSelectAll();
        return returnInfo;
    }
    
}
