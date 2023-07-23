package org.javaboy.tienchin.clue.service.impl;

import org.javaboy.tienchin.clue.domain.Clue;
import org.javaboy.tienchin.clue.mapper.ClueMapper;
import org.javaboy.tienchin.clue.service.IClueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.javaboy.tienchin.common.core.domain.AjaxResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Odin
 * @since 2023-07-23
 */
@Service
public class ClueServiceImpl extends ServiceImpl<ClueMapper, Clue> implements IClueService {

    @Resource
    ClueMapper clueMapper;

    @Override
    public AjaxResult addClue(Clue clue) {

        return null;
    }
}
