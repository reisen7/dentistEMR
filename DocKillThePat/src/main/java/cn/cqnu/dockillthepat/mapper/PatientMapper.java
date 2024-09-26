package cn.cqnu.dockillthepat.mapper;

import cn.cqnu.dockillthepat.pojo.Patient;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author 刘良杰
 */
@Mapper
public interface PatientMapper extends BaseMapper<Patient> {

    @Select("select * from patient where id=#{id}")
    Patient selectPatient(int id);
}
