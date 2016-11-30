package com.heitian.ssm.model;

/**
 * Created by oasis on 11/30/16.
 */
public class OwnerPhoto {
    private Long id;
    private String path;
    private Long ownerId;

    public OwnerPhoto() {}
    public OwnerPhoto(String path) {
        this.path = path;
    }

    public OwnerPhoto(String path, Long ownerId) {
        this.path = path;
        this.ownerId = ownerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
