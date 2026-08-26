package com.poketome.marketpricingservice.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class GradingSpread {

    private GradeMetric ungraded;
    private GradeMetric grade7;
    private GradeMetric grade8;
    private GradeMetric grade9;
    private GradeMetric grade95;
    private GradeMetric psa10;

    public GradingSpread() {
    }

    public GradeMetric getUngraded() {
        return ungraded;
    }

    public void setUngraded(GradeMetric ungraded) {
        this.ungraded = ungraded;
    }

    public GradeMetric getGrade7() {
        return grade7;
    }

    public void setGrade7(GradeMetric grade7) {
        this.grade7 = grade7;
    }

    public GradeMetric getGrade8() {
        return grade8;
    }

    public void setGrade8(GradeMetric grade8) {
        this.grade8 = grade8;
    }

    public GradeMetric getGrade9() {
        return grade9;
    }

    public void setGrade9(GradeMetric grade9) {
        this.grade9 = grade9;
    }

    public GradeMetric getGrade95() {
        return grade95;
    }

    public void setGrade95(GradeMetric grade95) {
        this.grade95 = grade95;
    }

    public GradeMetric getPsa10() {
        return psa10;
    }

    public void setPsa10(GradeMetric psa10) {
        this.psa10 = psa10;
    }

}
