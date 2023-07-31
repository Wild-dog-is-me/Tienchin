package org.javaboy.tienchin.channel.service;

import org.javaboy.tienchin.channel.domain.Channel;
import com.baomidou.mybatisplus.extension.service.IService;
import org.javaboy.tienchin.channel.domain.vo.ChannelVO;
import org.javaboy.tienchin.common.core.domain.AjaxResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Odin
 * @since 2023-07-22
 */
public interface IChannelService extends IService<Channel> {

    List<Channel> selectChannelList(ChannelVO channelVO);

    AjaxResult addChannel(ChannelVO channelVO);

    AjaxResult updateChannel(ChannelVO channelVO);

    Boolean deleteChannelByIds(Long[] channelIds);

    Boolean importChannel(List<Channel> list, boolean updateSupport);

    AjaxResult channelAnalysisData(ChannelVO channelVO);
}
