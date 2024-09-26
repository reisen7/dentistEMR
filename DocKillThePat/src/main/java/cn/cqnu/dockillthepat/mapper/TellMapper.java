package cn.cqnu.dockillthepat.mapper;

import cn.cqnu.dockillthepat.pojo.Tell;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 刘良杰
 */
@Mapper
public interface TellMapper extends BaseMapper<Tell> {

}
