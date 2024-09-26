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
@TableName("diagnose")
public class Diagnose {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer patid;
    private Integer docid;
    private String date;
    private String physicalcheck;
    private String auxiliarycheck;
    private String diagnosticinfo;
    private String  therapyinfo;
    private Double cost;
    private String drug; //药品费用
    private String device;  //器械费用
    private String type;
    @TableField(exist = false)
    private String department;
    @TableField(exist = false)
    private String typeTag;

    @TableField(exist = false)
    private String patName;
    @TableField(exist = false)
    private String patSex;
    @TableField(exist = false)
    private Integer patAge;
    @TableField(exist = false)
    private String doctor;
}
