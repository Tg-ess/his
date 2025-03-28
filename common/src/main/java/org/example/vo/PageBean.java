package org.example.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageBean<T> {

    private Long totalRows;

    private List<T> data;

}
