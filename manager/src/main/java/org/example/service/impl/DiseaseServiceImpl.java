package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.DiseaseMapper;
import org.example.pojo.Disease;
import org.example.service.IDiseaseService;
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
public class DiseaseServiceImpl extends ServiceImpl<DiseaseMapper, Disease> implements IDiseaseService {

}
