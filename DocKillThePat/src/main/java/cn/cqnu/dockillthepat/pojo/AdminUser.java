package cn.cqnu.dockillthepat.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author 刘良杰
 */

@Data
@Component
@TableName("adminuser")
public class AdminUser {
    private Integer id;
    private Long account;
    private String password;
}
