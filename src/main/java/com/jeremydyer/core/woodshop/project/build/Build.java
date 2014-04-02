package com.jeremydyer.core.woodshop.project.build;

import com.makeandbuild.persistence.jdbc.SaveWhen;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Holds a particular build instance for a project.
 *
 * User: Jeremy Dyer
 * Date: 4/2/14
 * Time: 9:26 AM
 */
@Table(name = "project_build")
public class Build
    implements Serializable {

    @Id
    @Column(name = "project_build_id")
    @SaveWhen(insert = true, update = false)
    private Long projectBuildId;

    @Column(name = "project_id")
    @SaveWhen(insert = true, update = true)
    private Long projectId;

    @Column(name = "builder_user_id")
    @SaveWhen(insert = true, update = true)
    private Long builderUserId;

    @Column(name = "build_start_date")
    @SaveWhen(insert = true, update = true)
    private Date buildStateDate;

    @Column(name = "build_end_date")
    @SaveWhen(insert = true, update = true)
    private Date buildEndDate;

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

    public Date getBuildStateDate() {
        return buildStateDate;
    }

    public void setBuildStateDate(Date buildStateDate) {
        this.buildStateDate = buildStateDate;
    }

    public Date getBuildEndDate() {
        return buildEndDate;
    }

    public void setBuildEndDate(Date buildEndDate) {
        this.buildEndDate = buildEndDate;
    }
}
