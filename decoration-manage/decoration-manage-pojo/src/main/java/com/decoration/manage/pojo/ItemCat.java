package com.decoration.manage.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "tb_item_cat")
@Data
public class ItemCat extends BasePojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parentId;

    private String name;

    private Integer status;

    private Integer sortOrder;

    private Boolean isParent;

    // 扩展EasyUI的tree属性
    public String getText() {
        return getName();
    }

    private String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public String getState() {
        return getIsParent() ? "closed" : "open";
    }

	private boolean getIsParent() {
		// TODO Auto-generated method stub
		return isParent;
	}

}
