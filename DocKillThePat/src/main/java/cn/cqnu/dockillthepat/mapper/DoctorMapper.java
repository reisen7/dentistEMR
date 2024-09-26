package cn.cqnu.dockillthepat.mapper;

import cn.cqnu.dockillthepat.pojo.Department;
import cn.cqnu.dockillthepat.pojo.Doctor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 刘良杰
 */
@Mapper
public interface DoctorMapper extends BaseMapper<Doctor> {


    //BaseMapper中的selectById无法自动映射多表操作（查询）
    @Select("select * from doctor where id = #{id}")
    @Results(
            {
                    @Result(column = "id",property = "id"),
                    @Result(column = "name",property = "name"),
                    @Result(column = "sex",property = "sex"),
                    @Result(column = "age",property = "age"),
                    @Result(column = "title",property = "title"),
                    @Result(column = "departmentid",property = "departmentid"),
                    @Result(column = "introduction",property = "introduction"),
                    @Result(column = "img",property = "img"),
                    @Result(column = "account",property = "account"),
                    @Result(column = "password",property = "password"),
                    @Result(column = "authority",property = "authority"),
                    @Result(column = "id",property = "department",javaType = Department.class,
                            one = @One(select = "cn.cqnu.dockillthepat.mapper.DepartmentMapper.loadDepartmentById")
                    )
            }
    )
    public Doctor selectDoctorById(Integer id);
    @Select("select * from tb_job where id = #{id}")
    public List<Doctor> loadDoctorByDepartment(Integer id);

    @Select("SELECT d.*, dept.name as department FROM doctor d LEFT JOIN department dept ON d.departmentid = dept.id")
    List<Doctor> selectDoctorsWithDepartment();
}
