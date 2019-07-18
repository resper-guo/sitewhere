package com.sitewhere.rdb.entities;

import com.sitewhere.spi.area.IZone;
import com.sitewhere.spi.common.ILocation;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "zone")
public class Zone implements IZone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /** Id for associated area */
    private UUID areaId;

    /** Displayed name */
    private String name;

    /** Zone bounds */
    @OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
    private List<Location> bounds = new ArrayList<Location>();

    /** Border color */
    private String borderColor;

    /** Fill color */
    private String fillColor;

    /** Opacity */
    private Double opacity;

    private String token;

    private Date createdDate;

    private String createdBy;

    private Date updatedDate;

    private String updatedBy;

    @ElementCollection
    @CollectionTable(name="zone_metadata")
    @MapKeyColumn(name="propKey")
    @Column(name="propValue")
    private Map<String, String> metadata = new HashMap<>();

    @Override
    public UUID getAreaId() {
        return areaId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public Double getOpacity() {
        return opacity;
    }

    @Override
    public List<? extends ILocation> getBounds() {
        return bounds;
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

    public void setAreaId(UUID areaId) {
        this.areaId = areaId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBounds(List<Location> bounds) {
        this.bounds = bounds;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public void setOpacity(Double opacity) {
        this.opacity = opacity;
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
