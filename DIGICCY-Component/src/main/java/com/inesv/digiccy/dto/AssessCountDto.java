package com.inesv.digiccy.dto;

/**
 * Created by JimJim on 2016/12/2 0002.
 */
public class AssessCountDto {

    private String coin;

    private Double assess;
    /** 技术评分 */
    private Double skill_assess;
    /** 应用评分 */
    private Double apply_assess;
    /** 前景评分 */
    private Double vista_assess;

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public Double getAssess() {
        return assess;
    }

    public void setAssess(Double assess) {
        this.assess = assess;
    }

    public Double getSkill_assess() {
        return skill_assess;
    }

    public void setSkill_assess(Double skill_assess) {
        this.skill_assess = skill_assess;
    }

    public Double getApply_assess() {
        return apply_assess;
    }

    public void setApply_assess(Double apply_assess) {
        this.apply_assess = apply_assess;
    }

    public Double getVista_assess() {
        return vista_assess;
    }

    public void setVista_assess(Double vista_assess) {
        this.vista_assess = vista_assess;
    }
}
