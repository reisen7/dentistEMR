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
@TableName("tell")
public class Tell {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer docid;
    private String content;
    private String name;  //病症名称
    private String advice;
    private String druginfo;
    @TableField(exist = false)
    private String doctor;
    @TableField(exist = false)
    private String department;
}
