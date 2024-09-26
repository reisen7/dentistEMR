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
@TableName("reservation")

public class Reservation {
  @TableId(type = IdType.AUTO)
    private Integer id;
  private Integer patid;
  private Integer depatid;
  private Integer docid;
  private String datt;
  private String time;
  private String reason;
  private String way; //预约方式
    private String status;

}
