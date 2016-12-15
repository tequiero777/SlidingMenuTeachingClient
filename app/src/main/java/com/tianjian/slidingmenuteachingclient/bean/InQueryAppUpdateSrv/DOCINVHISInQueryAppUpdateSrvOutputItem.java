
package com.tianjian.slidingmenuteachingclient.bean.InQueryAppUpdateSrv;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;


/**
 * <p>DOC_INV_HIS_InQueryAppUpdateSrvOutputItem complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="DOC_INV_HIS_InQueryAppUpdateSrvOutputItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VERSION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UPDATE_URL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UPDATE_FLAG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CREATE_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CREATE_USER_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CREATE_USER_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TENANT_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class DOCINVHISInQueryAppUpdateSrvOutputItem implements KvmSerializable {

    protected String id;
    protected String version;
    protected String updateurl;
    protected String updateflag;
    protected String createdate;
    protected String createuserid;
    protected String createusername;
    protected String tenantid;

    /**
     * ��ȡid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * ����id���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * ��ȡversion���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVERSION() {
        return version;
    }

    /**
     * ����version���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVERSION(String value) {
        this.version = value;
    }

    /**
     * ��ȡupdateurl���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUPDATEURL() {
        return updateurl;
    }

    /**
     * ����updateurl���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUPDATEURL(String value) {
        this.updateurl = value;
    }

    /**
     * ��ȡupdateflag���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUPDATEFLAG() {
        return updateflag;
    }

    /**
     * ����updateflag���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUPDATEFLAG(String value) {
        this.updateflag = value;
    }

    /**
     * ��ȡcreatedate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCREATEDATE() {
        return createdate;
    }

    /**
     * ����createdate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCREATEDATE(String value) {
        this.createdate = value;
    }

    /**
     * ��ȡcreateuserid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCREATEUSERID() {
        return createuserid;
    }

    /**
     * ����createuserid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCREATEUSERID(String value) {
        this.createuserid = value;
    }

    /**
     * ��ȡcreateusername���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCREATEUSERNAME() {
        return createusername;
    }

    /**
     * ����createusername���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCREATEUSERNAME(String value) {
        this.createusername = value;
    }

    /**
     * ��ȡtenantid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTENANTID() {
        return tenantid;
    }

    /**
     * ����tenantid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTENANTID(String value) {
        this.tenantid = value;
    }

    
    @Override
	public int getPropertyCount() {
		return 8;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo info) {
		switch (arg0) {
		case 0:info.name="id"; info.type = PropertyInfo.STRING_CLASS;break;
		case 1:info.name="version"; info.type = PropertyInfo.STRING_CLASS;break;
		case 2:info.name="updateurl"; info.type = PropertyInfo.STRING_CLASS;break;
		case 3:info.name="updateflag"; info.type = PropertyInfo.STRING_CLASS;break;
		case 4:info.name="createdate"; info.type = PropertyInfo.STRING_CLASS;break;
		case 5:info.name="createuserid"; info.type = PropertyInfo.STRING_CLASS;break;
		case 6:info.name="createusername"; info.type = PropertyInfo.STRING_CLASS;break;
		case 7:info.name="tenantid"; info.type = PropertyInfo.STRING_CLASS;break;
		default:
			break;
		}
		 
	}

	@Override
	public void setProperty(int arg0, Object arg1) {
		switch (arg0) {
		case 0:  id = toStringValue(arg1);break;
		case 1: version = toStringValue(arg1);break;
		case 2:   updateurl = toStringValue(arg1);break;
		case 3:  updateflag = toStringValue(arg1);break;
		case 4:  createdate = toStringValue(arg1);break;
		case 5:  createuserid = toStringValue(arg1);break;
		case 6:  createusername = toStringValue(arg1);break;
		case 7:  tenantid = toStringValue(arg1);break;
		
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

	@Override
	public Object getProperty(int arg0) {
		switch (arg0) {
		case 0: return id;
		case 1: return version;
		case 2: return updateurl;
		case 3: return updateflag;
		case 4: return createdate;
		case 5: return createuserid;
		case 6: return createusername;
		case 7: return tenantid;
		
		default:
			break;
		}
		return null;
		
	}
    
}
