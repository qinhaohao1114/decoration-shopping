package com.decoration.manage.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "tb_item")
@Data
public class Item extends BasePojo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String sellPoint;

	private Long price;

	private Integer num;

	private String barcode;

	private String image;

	private Long cid;

	private Integer status;

}
