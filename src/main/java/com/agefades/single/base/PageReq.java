package com.agefades.single.base;

import com.agefades.single.constants.CommonConstant;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页请求Vo
 *
 * @author DuChao
 * @date 2020/10/20 5:36 下午
 */
@Data
public class PageReq<T> {

    @ApiModelProperty("当前页")
    private int curPage;

    @ApiModelProperty("当前页条数")
    private int size;

    public Page<T> page() {
        if (curPage <= 0 || size <= 0) {
            curPage = 1;
            size = 10;
        }

        Page<T> page = new Page<>(curPage, size);
        page.addOrder(OrderItem.desc(CommonConstant.DEFAULT_SORT_FIELD));
        return page;
    }

}
