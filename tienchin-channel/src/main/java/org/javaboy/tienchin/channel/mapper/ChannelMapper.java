package org.javaboy.tienchin.channel.mapper;

import org.javaboy.tienchin.channel.domain.Channel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Odin
 * @since 2023-07-22
 */
public interface ChannelMapper extends BaseMapper<Channel> {

    List<Channel> selectChannelList();
}
