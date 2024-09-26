package cn.cqnu.dockillthepat;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.cqnu.dockillthepat.mapper")
@SpringBootApplication
public class DocKillThePatApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocKillThePatApplication.class, args);
    }

}
