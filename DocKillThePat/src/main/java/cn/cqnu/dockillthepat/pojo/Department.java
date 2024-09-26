package cn.cqnu.dockillthepat.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 刘良杰
 */
@Data
@Component
@TableName("department")
public class Department {
    private Integer id;
    private String name;
    private String introduction;
    private Long tel;
    @TableField(exist = false)
    private List<Doctor> doctorList;
}
