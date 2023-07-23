package org.javaboy.tienchin.assignment.service.impl;

import org.javaboy.tienchin.assignment.domain.Assignment;
import org.javaboy.tienchin.assignment.mapper.AssignmentMapper;
import org.javaboy.tienchin.assignment.service.IAssignmentService;
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
public class AssignmentServiceImpl extends ServiceImpl<AssignmentMapper, Assignment> implements IAssignmentService {

}
