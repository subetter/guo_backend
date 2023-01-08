package com.guo_backend.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * @TableName course_resourse
 */
@TableName(value ="course_resourse")
@Data
@Builder
public class CourseResourse implements Serializable {
    /**
     * 
     */
    @TableId
    private String resourceId;

    /**
     * 
     */
    private String pdf;

    /**
     * 
     */
    private String video;

    /**
     * 
     */
    private String chapterId;

    private Integer isFirst;

    private Integer count;
    private String courseId;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CourseResourse other = (CourseResourse) that;
        return (this.getResourceId() == null ? other.getResourceId() == null : this.getResourceId().equals(other.getResourceId()))
            && (this.getPdf() == null ? other.getPdf() == null : this.getPdf().equals(other.getPdf()))
            && (this.getVideo() == null ? other.getVideo() == null : this.getVideo().equals(other.getVideo()))
            && (this.getChapterId() == null ? other.getChapterId() == null : this.getChapterId().equals(other.getChapterId()))
            && (this.getCount() == null ? other.getCount()== null : this.getCount().equals(other.getCount()))
            && (this.getCourseId() == null ? other.getCourseId()== null : this.getCourseId().equals(other.getCourseId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getResourceId() == null) ? 0 : getResourceId().hashCode());
        result = prime * result + ((getPdf() == null) ? 0 : getPdf().hashCode());
        result = prime * result + ((getVideo() == null) ? 0 : getVideo().hashCode());
        result = prime * result + ((getCount() == null) ? 0 : getCount().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", resourceId=").append(resourceId);
        sb.append(", pdf=").append(pdf);
        sb.append(", video=").append(video);
        sb.append(", count=").append(count);
        sb.append(", chapterId=").append(chapterId);
        sb.append(", courseId=").append(courseId);
        sb.append(", isFirst=").append(isFirst);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}