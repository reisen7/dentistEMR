package cn.cqnu.dockillthepat.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author 刘良杰
 */
@Data
@Component
@TableName("doctor")

public class Doctor {
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private String title;
    private Integer departmentid;
    private String introduction;
    private String img;
    private Long account;
    private String password;
    private String authority;
    @TableField(exist = false)
    private String department;

}
