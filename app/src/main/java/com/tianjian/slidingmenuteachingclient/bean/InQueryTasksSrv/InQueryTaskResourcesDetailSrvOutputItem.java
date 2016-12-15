
package com.tianjian.slidingmenuteachingclient.bean.InQueryTasksSrv;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.IOException;
import java.util.Hashtable;

import Decoder.BASE64Decoder;


/**
 * <p>InQueryTaskResourcesDetailSrvOutputItem complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="InQueryTaskResourcesDetailSrvOutputItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RESOURCES_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RESOURCES_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IMAGE" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="RESOURCES_URL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UPLOAD_USERNAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UPLOAD_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UPLOAD_TIME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TASK_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RESOURCES_TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class InQueryTaskResourcesDetailSrvOutputItem implements KvmSerializable {

    protected String resourcesid;
    protected String resourcesname;
    protected byte[] image;
    protected String resourcesurl;
    protected String uploadusername;
    protected String uploadname;
    protected String uploadtime;
    protected String taskid;
    protected String resourcestype;

    /**
     * ��ȡresourcesid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRESOURCESID() {
        return resourcesid;
    }

    /**
     * ����resourcesid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRESOURCESID(String value) {
        this.resourcesid = value;
    }

    /**
     * ��ȡresourcesname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRESOURCESNAME() {
        return resourcesname;
    }

    /**
     * ����resourcesname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRESOURCESNAME(String value) {
        this.resourcesname = value;
    }

    /**
     * ��ȡimage���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getIMAGE() {
        return image;
    }

    /**
     * ����image���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setIMAGE(byte[] value) {
        this.image = value;
    }

    /**
     * ��ȡresourcesurl���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRESOURCESURL() {
        return resourcesurl;
    }

    /**
     * ����resourcesurl���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRESOURCESURL(String value) {
        this.resourcesurl = value;
    }

    /**
     * ��ȡuploadusername���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUPLOADUSERNAME() {
        return uploadusername;
    }

    /**
     * ����uploadusername���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUPLOADUSERNAME(String value) {
        this.uploadusername = value;
    }

    /**
     * ��ȡuploadname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUPLOADNAME() {
        return uploadname;
    }

    /**
     * ����uploadname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUPLOADNAME(String value) {
        this.uploadname = value;
    }

    /**
     * ��ȡuploadtime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUPLOADTIME() {
        return uploadtime;
    }

    /**
     * ����uploadtime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUPLOADTIME(String value) {
        this.uploadtime = value;
    }

    /**
     * ��ȡtaskid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTASKID() {
        return taskid;
    }

    /**
     * ����taskid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTASKID(String value) {
        this.taskid = value;
    }

    /**
     * ��ȡresourcestype���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRESOURCESTYPE() {
        return resourcestype;
    }

    /**
     * ����resourcestype���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRESOURCESTYPE(String value) {
        this.resourcestype = value;
    }

    @Override
	public int getPropertyCount() {
		return 9;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo info) {
		switch (arg0) {
		case 0:info.name="resourcesid"; info.type = PropertyInfo.STRING_CLASS;break;
		case 1:info.name="resourcesname"; info.type = PropertyInfo.STRING_CLASS;break;
		case 2:info.name="image"; info.type = PropertyInfo.STRING_CLASS;break;
		case 3:info.name="resourcesurl"; info.type = PropertyInfo.STRING_CLASS;break;
		case 4:info.name="uploadusername"; info.type = PropertyInfo.STRING_CLASS;break;
		case 5:info.name="uploadname"; info.type = PropertyInfo.STRING_CLASS;break;
		case 6:info.name="uploadtime"; info.type = PropertyInfo.STRING_CLASS;break;
		case 7:info.name="taskid"; info.type = PropertyInfo.STRING_CLASS;break;
		case 8:info.name="resourcestype"; info.type = PropertyInfo.STRING_CLASS;break;
		default:
			break;
		}
	}

	@Override
	public void setProperty(int arg0, Object arg1) {
		switch (arg0) {
		case 0: resourcesid = toStringValue(arg1);break;
		case 1: resourcesname = toStringValue(arg1);break;
		case 2: image = toByteValue(arg1);break;
		case 3: resourcesurl = toStringValue(arg1);break;
		case 4: uploadusername = toStringValue(arg1);break;
		case 5: uploadname = toStringValue(arg1);break;
		case 6: uploadtime = toStringValue(arg1);break;
		case 7: taskid = toStringValue(arg1);break;
		case 8: resourcestype = toStringValue(arg1);break;

		default:
			break;
		}
		
	}
	private String toStringValue(Object obj){
		if(obj!=null){
			return obj+"";
		}
		return "";
	}
	
	private byte[] toByteValue(Object obj){
		if(obj!=null){
			BASE64Decoder decode = new BASE64Decoder();
	        byte[] bytes;
			try {
				bytes = decode.decodeBuffer(obj+"");
				return bytes;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}else{
			return null;
		}
	}
	
	@Override
	public Object getProperty(int arg0) {
		switch (arg0) {
		case 0: return resourcesid;
		case 1: return resourcesname;
		case 2: return image;
		case 3: return resourcesurl;
		case 4: return uploadusername;
		case 5: return uploadname;
		case 6: return uploadtime;
		case 7: return taskid;
		case 8: return resourcestype;
		
		default:
			break;
		}
		return null;
		
	}
}
