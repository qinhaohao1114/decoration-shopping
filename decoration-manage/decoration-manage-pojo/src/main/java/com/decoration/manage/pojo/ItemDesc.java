package com.decoration.manage.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "tb_item_desc")
@Data
public class ItemDesc extends BasePojo{
    
    @Id//对应tb_item中的id
    private Long itemId;
    
    private String itemDesc;
}
