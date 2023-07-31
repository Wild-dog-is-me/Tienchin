package org.javaboy.tienchin.channel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.javaboy.tienchin.channel.domain.Channel;
import org.javaboy.tienchin.channel.domain.vo.ChannelVO;
import org.javaboy.tienchin.channel.mapper.ChannelMapper;
import org.javaboy.tienchin.channel.service.IChannelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.javaboy.tienchin.common.core.domain.AjaxResult;
import org.javaboy.tienchin.common.core.domain.model.PieData;
import org.javaboy.tienchin.common.utils.SecurityUtils;
import org.javaboy.tienchin.common.utils.bean.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Odin
 * @since 2023-07-22
 */
@Service
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, Channel> implements IChannelService {

    @Resource
    private ChannelMapper channelMapper;

    @Override
    public List<Channel> selectChannelList(ChannelVO channelVO) {
        return channelMapper.selectChannelList(channelVO);
    }

    @Override
    public AjaxResult addChannel(ChannelVO channelVO) {
        QueryWrapper<Channel> qw = new QueryWrapper();
        qw.lambda().eq(Channel::getChannelName, channelVO.getChannelName());
        Channel channelDB = getOne(qw);
        if (channelDB != null) {
            return AjaxResult.error("存在同名渠道，添加失败");
        }
        Channel channel = new Channel();
        BeanUtils.copyProperties(channelVO, channel);
        channel.setCreateBy(SecurityUtils.getUsername());
        channel.setCreateTime(LocalDateTime.now());
        channel.setDelFlag(0);
        return save(channel) ? AjaxResult.success("添加成功") : AjaxResult.error("添加失败");
    }

    @Override
    public AjaxResult updateChannel(ChannelVO channelVO) {
        Channel channel = new Channel();
        BeanUtils.copyProperties(channelVO, channel);
        channel.setUpdateBy(SecurityUtils.getUsername());
        channel.setUpdateTime(LocalDateTime.now());
        channel.setCreateTime(null);
        channel.setCreateBy(null);
        channel.setDelFlag(null);
        return updateById(channel) ? AjaxResult.success("更新成功") : AjaxResult.error("更新失败");
    }

    @Override
    public Boolean deleteChannelByIds(Long[] channelIds) {
        UpdateWrapper<Channel> uw = new UpdateWrapper<>();
        uw.lambda().set(Channel::getDelFlag, 1).in(Channel::getChannelId, channelIds);
        return update(uw);
    }

    @Override
    public Boolean importChannel(List<Channel> list, boolean updateSupport) {
        if (updateSupport) {
            List<Channel> channels = list.stream().map(c -> {
                c.setUpdateTime(LocalDateTime.now());
                c.setUpdateBy(SecurityUtils.getUsername());
                return c;
            }).collect(Collectors.toList());
            return saveBatch(channels);
        } else {
            List<Channel> channels = list.stream().map(c -> {
                c.setCreateBy(SecurityUtils.getUsername());
                c.setCreateTime(LocalDateTime.now());
                c.setChannelId(null);
                return c;
            }).collect(Collectors.toList());
            return saveBatch(channels);
        }
    }

    @Override
    public AjaxResult channelAnalysisData(ChannelVO channelVO) {
        if (channelVO.getParams().get("beginTime")==null||channelVO.getParams().get("endTime")==null) {
            channelVO.getParams().put("beginTime", LocalDateTime.now().minusWeeks(1));
            channelVO.getParams().put("endTime", LocalDateTime.now().plusWeeks(1));
        }
        List<PieData> list = channelMapper.channelAnalysisData(channelVO);
        return AjaxResult.success(list);
    }

}
