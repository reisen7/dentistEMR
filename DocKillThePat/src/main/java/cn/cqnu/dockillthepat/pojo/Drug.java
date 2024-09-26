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
@TableName("drug")

public class Drug {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String type;
    private String standards;
    private String producter;
    private String indications;
    private String usefor; //用法
    private String taboo;//禁忌
    private String comcon;
    private String price;
    private String proplace; //产地
}
