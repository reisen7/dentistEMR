package cn.cqnu.dockillthepat.service.impl;

import cn.cqnu.dockillthepat.commen.ReturnInfo;
import cn.cqnu.dockillthepat.mapper.DrugMapper;
import cn.cqnu.dockillthepat.pojo.Drug;
import cn.cqnu.dockillthepat.service.DrugService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘良杰
 */
@Service
public class DrugServiceImpl implements DrugService {
    @Resource
    private DrugMapper drugMapper;
    @Resource
    private ReturnInfo returnInfo;
    @Resource
    private Drug drug;
    
    @Override
    public ReturnInfo drugComeToDrug() {
        return null;
    }

    @Override
    public ReturnInfo drugReNewDrug(Drug drug) {
        try {
            int result = drugMapper.updateById(drug);
            if (result != 1) {
                returnInfo.message = "failed";
                returnInfo.data = drug;
            } else {
                this.drug = drugMapper.selectById(drug.getId());
                returnInfo.data = this.drug;
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
    public ReturnInfo drugAddDrug(Drug drug) {
        try {
            int ret = drugMapper.insert(drug);
            if (ret != 1) {
                returnInfo.message = "RegisterFailed";
                returnInfo.data = null;
            } else {
                returnInfo.message = "RegisterSuccess";
                returnInfo.data = drug;
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
    public ReturnInfo drugDeleteDrug(Integer id) {
        try {
            drugMapper.deleteById(id);
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
    public ReturnInfo drugSelectOne(Integer id) {
        try {
            Drug drug = drugMapper.selectById(id);
            if (drug != null) {
                returnInfo.message = "RegisterFailed";
                returnInfo.data = drug;
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
    public ReturnInfo drugSelectAll() {
        try {
            List<Drug> drugList = drugMapper.selectList(null);
            if (drug != null) {
                returnInfo.message = "RegisterFailed";
                returnInfo.data = drugList;
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
