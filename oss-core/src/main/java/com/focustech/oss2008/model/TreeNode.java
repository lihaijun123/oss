package com.focustech.oss2008.model;

import java.util.List;

public class TreeNode implements Comparable<TreeNode> {
    /** 葉子節點 */
    public static final String LEAF_NODE = "file";
    /** 中間節點 */
    public static final String MIDDLE_NODE = "folder";
    /** 銷售功能根菜單編號 */
    public static final String SAL_ROOT_MENU = "101";
    /** 權限功能根菜單編號 */
    public static final String AUT_ROOT_MENU = "100";
    private String id;
    private String text;
    private boolean leaf;
    private String cls;
    private String link = "";
    private String href = "";
    private String hrefTarget = "centerPage";
    private List<TreeNode> children;
    private long order;

    public int compareTo(TreeNode o) {
        if (order < o.order)
            return -1;
        else if (order == o.order)
            return 0;
        else
            return 1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getHrefTarget() {
        return hrefTarget;
    }

    public void setHrefTarget(String hrefTarget) {
        this.hrefTarget = hrefTarget;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }
}
