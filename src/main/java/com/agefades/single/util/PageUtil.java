package com.agefades.single.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * MyBatis Plus 分页包装类
 *
 * @author DuChao
 * @date 2021/1/12 4:39 下午
 */
@Data
public class PageUtil<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("总记录数")
    private int totalCount;

    @ApiModelProperty("每页记录数")
    private int pageSize;

    @ApiModelProperty("总页数")
    private int totalPage;

    @ApiModelProperty("当前页数")
    private int currPage;

    @ApiModelProperty("列表数据")
    private List<T> list;

    public PageUtil(IPage<T> page) {
        this.list = page.getRecords();
        this.totalCount = (int) page.getTotal();
        this.pageSize = (int) page.getSize();
        this.currPage = (int) page.getCurrent();
        this.totalPage = (int) page.getPages();
    }

}
