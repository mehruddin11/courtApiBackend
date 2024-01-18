package com.nagarro.ProductSearchApi.dto;

import com.nagarro.ProductSearchApi.model.FeedBack;
import com.nagarro.ProductSearchApi.model.User;

public class FeedbackDTO {
    private FeedBack feedback;
    private User user;

    public FeedbackDTO(FeedBack feedback, User user) {
        this.feedback = feedback;
        this.user = user;
    }

    // Getters and setters

    public FeedBack getFeedback() {
        return feedback;
    }

    public void setFeedback(FeedBack feedback) {
        this.feedback = feedback;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
