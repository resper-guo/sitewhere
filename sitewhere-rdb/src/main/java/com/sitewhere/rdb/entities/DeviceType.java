package com.sitewhere.rdb.entities;

import com.sitewhere.spi.device.DeviceContainerPolicy;
import com.sitewhere.spi.device.IDeviceType;
import com.sitewhere.spi.device.element.IDeviceElementSchema;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "device_type")
public class DeviceType implements IDeviceType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /** Name */
    private String name;

    /** Decription */
    private String description;

    /** Device container policy */
    @Enumerated(EnumType.STRING)
    private DeviceContainerPolicy containerPolicy = DeviceContainerPolicy.Standalone;

    /** Schema that specifies allowable locations of nested devices */
    private DeviceElementSchema deviceElementSchema;

    private String imageUrl;

    private String backgroundColor;

    private String foregroundColor;

    private String borderColor;

    private String icon;

    private String token;

    private Date createdDate;

    private String createdBy;

    private Date updatedDate;

    private String updatedBy;

    @ElementCollection
    @CollectionTable(name="device_type_metadata")
    @MapKeyColumn(name="propKey")
    @Column(name="propValue")
    private Map<String, String> metadata = new HashMap<>();

    @Override
    public DeviceContainerPolicy getContainerPolicy() {
        return containerPolicy;
    }

    @Override
    public IDeviceElementSchema getDeviceElementSchema() {
        return deviceElementSchema;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public String getForegroundColor() {
        return foregroundColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public Date getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public String getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContainerPolicy(DeviceContainerPolicy containerPolicy) {
        this.containerPolicy = containerPolicy;
    }

    public void setDeviceElementSchema(DeviceElementSchema deviceElementSchema) {
        this.deviceElementSchema = deviceElementSchema;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setForegroundColor(String foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

}