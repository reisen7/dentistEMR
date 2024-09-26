package cn.cqnu.dockillthepat.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author 刘良杰
 */
@Data
@Component
@TableName("device")

public class Device {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer departid;
    private Integer docid;
    private String illustrate;
    private String name;
    private Long cost;
    private String chargmethod;
    private String standard;

}
