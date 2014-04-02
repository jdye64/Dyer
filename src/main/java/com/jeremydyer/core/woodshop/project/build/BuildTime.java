package com.jeremydyer.core.woodshop.project.build;

import com.makeandbuild.persistence.jdbc.SaveWhen;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Represents a single time entry against a particular project build step. "Pausing" a timer will result in just
 * creating a new entry in the database.
 *
 * User: Jeremy Dyer
 * Date: 4/2/14
 * Time: 9:40 AM
 */
@Table(name = "project_build_time")
public class BuildTime
    implements Serializable {

    @Id
    @Column(name = "project_build_time_id")
    @SaveWhen(insert = true, update = false)
    private Long projectBuildId;

    @Column(name = "project_step_id")
    @SaveWhen(insert = true, update = true)
    private Long projectId;

    @Column(name = "start_timestamp")
    @SaveWhen(insert = true, update = true)
    private Long builderUserId;

    @Column(name = "end_timestamp")
    @SaveWhen(insert = true, update = true)
    private Date buildDate;

    public Long getProjectBuildId() {
        return projectBuildId;
    }

    public void setProjectBuildId(Long projectBuildId) {
        this.projectBuildId = projectBuildId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getBuilderUserId() {
        return builderUserId;
    }

    public void setBuilderUserId(Long builderUserId) {
        this.builderUserId = builderUserId;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }
}
