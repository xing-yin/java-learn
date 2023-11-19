package org.example.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>FIXME: 简要描述本文件的功能</p>
 *
 * @author yinxing
 * @since 2023/11/16
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode//用于后期的去重使用
public class Book {

  //id
  private Long id;
  //书名
  private String name;
  //分类
  private String category; //例如"哲学,小说"
  //评分
  private Integer score;
  //简介
  private String intro;

}
