package com.jeremydyer.core.woodshop.project;

import com.makeandbuild.persistence.jdbc.SaveWhen;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Represents a single Project build step.
 *
 * User: Jeremy Dyer
 * Date: 4/2/14
 * Time: 9:40 AM
 */
@Table(name = "project_step")
public class ProjectStep
    implements Serializable {

    @Id
    @Column(name = "project_step_id")
    @SaveWhen(insert = true, update = false)
    private Long projectStepId;

    @Column(name = "project_id")
    @SaveWhen(insert = true, update = true)
    private int projectId;

    @Column(name = "step_order")
    @SaveWhen(insert = true, update = true)
    private int stepOrder;

    @Column(name = "step_estimated_time_minutes")
    @SaveWhen(insert = true, update = true)
    private Long estimatedTimeInMinutes;

    @Column(name = "step_description")
    @SaveWhen(insert = true, update = true)
    private String stepDescription;

    public Long getProjectStepId() {
        return projectStepId;
    }

    public void setProjectStepId(Long projectStepId) {
        this.projectStepId = projectStepId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(int stepOrder) {
        this.stepOrder = stepOrder;
    }

    public Long getEstimatedTimeInMinutes() {
        return estimatedTimeInMinutes;
    }

    public void setEstimatedTimeInMinutes(Long estimatedTimeInMinutes) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }
}
