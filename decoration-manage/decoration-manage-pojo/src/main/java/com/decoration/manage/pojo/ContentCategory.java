package com.decoration.manage.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "tb_content_category")
@Data
public class ContentCategory extends BasePojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parentId;

    private String name;

    private Integer status;

    private Integer sortOrder;

    @Column(name = "is_parent")
    private Boolean isParent;

    // 扩展字段，用于EasyUI中tree结构
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
