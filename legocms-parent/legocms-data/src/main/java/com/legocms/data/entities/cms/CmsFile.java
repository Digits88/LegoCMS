package com.legocms.data.entities.cms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.legocms.data.base.BaseEntity;
import com.legocms.data.entities.cms.simpletype.CmsFileType;
import com.legocms.data.entities.sys.SysSite;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "cms_file")
@EqualsAndHashCode(callSuper = true)
@ToString
public class CmsFile extends BaseEntity {

    private long size;
    private String path;
    private Date updateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private CmsFileType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private CmsFile parent;

    @OneToMany(cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private List<CmsFile> childrens;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id", referencedColumnName = "id")
    private SysSite site;

    protected CmsFile() { }

    public CmsFile(String code) {
        super(code);
    }

    public List<CmsFile> getAllChildren() {
        List<CmsFile> list = new ArrayList<CmsFile>();
        list.add(this);
        if (childrens.isEmpty()) {
            return list;
        }
        for (CmsFile children : childrens) {
            list.addAll(children.getAllChildren());
        }
        return list;
    }
}