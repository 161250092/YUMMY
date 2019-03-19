package cn.yummy.mapper;

import cn.yummy.entity.yummy.SystemMail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 尝试用MyBAITS重构数据层
 */
@Mapper
public interface SystemMailMapper {
    List<SystemMail> getSystemMail();

}
