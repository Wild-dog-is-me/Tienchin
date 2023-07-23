package org.javaboy.tienchin.clue.service;

import org.javaboy.tienchin.clue.domain.Clue;
import com.baomidou.mybatisplus.extension.service.IService;
import org.javaboy.tienchin.common.core.domain.AjaxResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Odin
 * @since 2023-07-23
 */
public interface IClueService extends IService<Clue> {

    AjaxResult addClue(Clue clue);
}
