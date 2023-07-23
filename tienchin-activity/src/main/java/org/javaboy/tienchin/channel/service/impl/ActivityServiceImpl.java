package org.javaboy.tienchin.channel.service.impl;

import org.javaboy.tienchin.channel.entity.Activity;
import org.javaboy.tienchin.channel.mapper.ActivityMapper;
import org.javaboy.tienchin.channel.service.IActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Odin
 * @since 2023-07-23
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

}
