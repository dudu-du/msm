package com.welsee.extentity;

import com.welsee.entity.ClassCircle;
import com.welsee.entity.ClassCirclePraise;

import java.util.List;

public class ExtClassCircle extends ClassCircle {

    private String userName;

    private List<ExtClassCircleComment> commentList;

    private List<ClassCirclePraise> praiseList;

    public List<ClassCirclePraise> getPraiseList() {
        return praiseList;
    }

    public void setPraiseList(List<ClassCirclePraise> praiseList) {
        this.praiseList = praiseList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ExtClassCircleComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<ExtClassCircleComment> commentList) {
        this.commentList = commentList;
    }
}
