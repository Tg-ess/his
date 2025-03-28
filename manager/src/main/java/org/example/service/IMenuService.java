package org.example.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.Menu;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cyx
 * @since 2024-12-19
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 查询树结构菜单
     */
    List<Menu> tree(Wrapper wrapper);

}
