package org.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.ApiOperation;
import org.example.exception.AppException;
import org.example.exception.AppExceptionCodeMsg;
import org.example.query.BaseQuery;
import org.example.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BaseController<T, W> {

    @Autowired
    IService<T> baseService;

    @ApiOperation(value = "保存数据，有id就修改，没id就添加" )
    @PostMapping      //  请求方式 + 请求路径   post  /模块路径[根据继承类型的模块路径来确定]
    public ResultBean save(@RequestBody T t){
        System.out.println("----BaseController-----save--------");
        System.out.println(t);
        boolean update = baseService.saveOrUpdate(t);
        if (!update){
            //抛异常
            throw new AppException(AppExceptionCodeMsg.SAVE_FAIL);
        }
        return ResultBean.SUCCESS();
    }

    @ApiOperation(value = "通过id删除" )
    //  delete   /xxx/1003
    @DeleteMapping("/{id}")   // http://localhost:8080/xxx/1003
    public ResultBean del(@PathVariable("id") Integer id){
        System.out.println("-------BaseController--------del-------------");
        System.out.println(id);
        boolean b = baseService.removeById(id);
        if (!b){
            //抛异常
            throw new AppException(AppExceptionCodeMsg.DELETE_FAIL);
        }
        return ResultBean.SUCCESS();
    }

    @ApiOperation(value = "通过id查询" )
    //  get   /xxx/1004
    @GetMapping("/{id}")   // http://localhost:8080/xxx/1003
    public ResultBean getById(@PathVariable("id") Integer id){
        System.out.println("-------BaseController--------getById-------------");
        System.out.println(id);
        T t = baseService.getById(id);
        if (t==null){
            //throw
            throw new AppException(AppExceptionCodeMsg.DATA_NOT_FOUND);
        }
        return ResultBean.SUCCESS(t);
    }

    @ApiOperation(value = "分页条件搜索" )
    @PostMapping("/page")    //  post  /xxx/all
    public <W extends BaseQuery> ResultBean getPage(@RequestBody W baseQuery){
        System.out.println("-------BaseController-----getPage--------");
        IPage<T> page = new Page<>(baseQuery.getCurrent(),baseQuery.getSize());
        List list = baseService.list(page, baseQuery.getQueryWrapper());
        page.setRecords(list);
        return ResultBean.SUCCESS(page);
    }

    @ApiOperation(value = "批量删除" )
    @PostMapping("/batch/")   // post + /xxx/batch
    public ResultBean batchDel(@RequestBody List<Integer> ids){
        System.out.println("----------BaseController---------batchDel---------------");
        boolean b = baseService.removeBatchByIds(ids);
        if (!b){
            //抛异常
            throw new AppException(AppExceptionCodeMsg.DELETE_FAIL);
        }
        return ResultBean.SUCCESS();
    }

    @ApiOperation(value = "查询所有")
    @GetMapping("/all")
    public ResultBean all(){
        System.out.println("BaseController---------all");
        List<T> list = baseService.list();
        return ResultBean.SUCCESS(list);
    }

}
