package com.decoration.manage.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Table(name = "tb_item_param")
@Data
public class ItemParam extends BasePojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long itemCatId;

    private String paramData;

    @Transient
    private String itemCatName;
    

}
