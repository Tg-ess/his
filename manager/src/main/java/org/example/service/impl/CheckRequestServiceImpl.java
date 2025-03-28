package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.CheckRequestMapper;
import org.example.pojo.CheckRequest;
import org.example.service.ICheckRequestService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cyx
 * @since 2024-12-18
 */
@Service
public class CheckRequestServiceImpl extends ServiceImpl<CheckRequestMapper, CheckRequest> implements ICheckRequestService {

}
