package com.focustech.focus3d.utils;

import org.springframework.core.io.Resource;
/**
 * 资源
 * *
 * @author lihaijun
 *
 */
public class CiefResource {
	/**图册生成路径*/
	private Resource atlasresource;
	/**公司图片生成路径*/
	private Resource companypicresource;
    /**生成x3dxml路径 */
    private Resource x3dxmlresource;
    /**图片编辑器文件临时文件路径*/
    private Resource imageEidtresource;
    /** 场景父world文件固定路径 */
    private Resource x3dParentResource;

    public Resource getImageEidtresource() {
		return imageEidtresource;
	}

	public void setImageEidtresource(Resource imageEidtresource) {
		this.imageEidtresource = imageEidtresource;
	}

	public Resource getX3dxmlresource() {
        return x3dxmlresource;
    }

    public void setX3dxmlresource(Resource x3dxmlresource) {
        this.x3dxmlresource = x3dxmlresource;
    }

    public Resource getCompanypicresource() {
		return companypicresource;
	}

	public void setCompanypicresource(Resource companypicresource) {
		this.companypicresource = companypicresource;
	}

	public Resource getAtlasresource() {
		return atlasresource;
	}

	public void setAtlasresource(Resource atlasresource) {
		this.atlasresource = atlasresource;
	}

    public Resource getX3dParentResource() {
        return x3dParentResource;
    }

    public void setX3dParentResource(Resource x3dParentResource) {
        this.x3dParentResource = x3dParentResource;
    }

}
