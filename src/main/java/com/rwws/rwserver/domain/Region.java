package com.rwws.rwserver.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 国家地区实体
 * @Author keyi
 * @Date 2023/3/8 21:38
 * @Version 1.0
 */
@Data
public class Region implements Serializable {

    /** 主键 */
    private Integer id;

    /** 中文名称 */
    private String chnName;

    /** 英文名称或拼音 */
    private String engName;

    /** 备注 */
    private String remark;


}
