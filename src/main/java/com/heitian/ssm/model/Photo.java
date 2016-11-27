package com.heitian.ssm.model;

import java.io.File;

/**
 * Created by oasis on 11/27/16.
 */
public class Photo {
    private Long id;
    private File path;
    private String hash;
    private Product productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public File getPath() {
        return path;
    }

    public void setPath(File path) {
        this.path = path;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }
}
