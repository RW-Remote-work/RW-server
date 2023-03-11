package com.rwws.rwserver.controller.request;

import com.rwws.rwserver.common.constant.RegexConstant;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 添加远程工作
 */
@Data
public class AddRwJobRequest {

    /**
     * 岗位名称
     */
    @NotEmpty(message = "岗位名称不能为空")
    private String jobName;

    /**
     * 职位分类ID
     */
    @NotNull(message = "职位分类不能为空")
    private Long jobCategoryId;

    /**
     * 工作类型（1:全职远程/2:兼职远程）
     */
    @NotNull(message = "工作类型不能为空")
    private Integer jobType;

    /**
     * 国家/地区ID
     */
    @NotNull(message = "国家/地区不能为空")
    private Long regionId;

    /**
     * 投递邮箱
     */
    @NotEmpty(message = "邮箱不能为空")
    @Email(regexp = RegexConstant.EMAIL)
    private String deliverEmail;

    /**
     * 投递微信号
     */
    private String deliverWechat;

    /**
     * 投递telegram
     */
    private String deliverTelegram;

    /**
     * 投递网址
     */
    private String deliverWebsite;

    /**
     * 薪资表示类型（1:月薪，2:年薪）
     */
    private Integer salaryType;

    /**
     * 薪资币种
     */
    private Long salaryCurrency;

    /**
     * min薪资
     */
    private Long salaryMin;

    /**
     * max薪资
     */
    private Long salaryMax;

    /**
     * 岗位描述
     */
    private String jobDescription;

    /**
     * 岗位职责
     */
    @NotEmpty(message = "岗位职责不能为空")
    private String jobDuty;

    /**
     * 岗位要求
     */
    @NotEmpty(message = "岗位要求不能为空")
    private String jobRequirement;

    /**
     * 公司/团队介绍
     */
    private String companyInfo;

    /**
     * 岗位标签
     */
    private String jobLabel;

}
