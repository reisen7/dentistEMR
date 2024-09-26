package cn.cqnu.dockillthepat.mapper;

import cn.cqnu.dockillthepat.pojo.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 刘良杰
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

    @Select("select * from department where id = #{id}")
    Department loadDepartmentById(Integer id);

    @Select("select * from department")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "introduction",property = "introduction"),
            @Result(column = "tel",property = "tel"),
            @Result(column = "id",property = "doctor",javaType = List.class,
                    many = @Many(select = "cn.cqnu.dockillthepat.mapper.DoctorMapper.loadDoctorByDepartment")
            )

    })
    List<Department> loadAllDepartment();

//    @Select("select ")
//    public

}
