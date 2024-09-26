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
@TableName("patient")
public class Patient {
    private Integer id;
    private Long account;
    private String password;
    private String name;
    private Integer age;
    private String sex;
    private String address;
    private String classify;
    private String anamnesis; //既往病史
    private String profile;
}
