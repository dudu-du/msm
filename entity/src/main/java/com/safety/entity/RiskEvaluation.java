package com.safety.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * <p>
 * 安全风险动态评估
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public class RiskEvaluation extends Model<RiskEvaluation> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 状态 0 初始可编辑 1 生效不可编辑
     */
    private Integer state;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 填写人ID
     */
    private String createPersonFk;

    /**
     * 所属机构ID
     */
    private String orgFk;

    private List<RiskEvaluationList> riskEvaluationList;
    
    
    public List<RiskEvaluationList> getRiskEvaluationList() {
		return riskEvaluationList;
	}

	public void setRiskEvaluationList(List<RiskEvaluationList> riskEvaluationList) {
		this.riskEvaluationList = riskEvaluationList;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }
    public String getCreatePersonFk() {
        return createPersonFk;
    }

    public void setCreatePersonFk(String createPersonFk) {
        this.createPersonFk = createPersonFk;
    }
    public String getOrgFk() {
        return orgFk;
    }

    public void setOrgFk(String orgFk) {
        this.orgFk = orgFk;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RiskEvaluation{" +
        "id=" + id +
        ", state=" + state +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        ", createPersonFk=" + createPersonFk +
        ", orgFk=" + orgFk +
        "}";
    }
}
