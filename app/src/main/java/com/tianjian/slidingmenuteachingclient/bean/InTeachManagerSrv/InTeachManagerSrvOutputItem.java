
package com.tianjian.slidingmenuteachingclient.bean.InTeachManagerSrv;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;


/**
 * <p>InTeachManagerSrvOutputItem complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="InTeachManagerSrvOutputItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="USERID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PASSWORD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SEX" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HOSPITAL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MAIL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IMAGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class InTeachManagerSrvOutputItem  implements KvmSerializable {

    protected String userid;
    protected String password;
    protected String sex;
    protected String type;
    protected String status;
    protected String hospital;
    protected String mail;
    protected String image;

    /**
     * ��ȡuserid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSERID() {
        return userid;
    }

    /**
     * ����userid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSERID(String value) {
        this.userid = value;
    }

    /**
     * ��ȡpassword���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPASSWORD() {
        return password;
    }

    /**
     * ����password���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPASSWORD(String value) {
        this.password = value;
    }

    /**
     * ��ȡsex���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSEX() {
        return sex;
    }

    /**
     * ����sex���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSEX(String value) {
        this.sex = value;
    }

    /**
     * ��ȡtype���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTYPE() {
        return type;
    }

    /**
     * ����type���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTYPE(String value) {
        this.type = value;
    }

    /**
     * ��ȡstatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTATUS() {
        return status;
    }

    /**
     * ����status���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTATUS(String value) {
        this.status = value;
    }

    /**
     * ��ȡhospital���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHOSPITAL() {
        return hospital;
    }

    /**
     * ����hospital���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHOSPITAL(String value) {
        this.hospital = value;
    }

    /**
     * ��ȡmail���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMAIL() {
        return mail;
    }

    /**
     * ����mail���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMAIL(String value) {
        this.mail = value;
    }

    /**
     * ��ȡimage���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIMAGE() {
        return image;
    }

    /**
     * ����image���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIMAGE(String value) {
        this.image = value;
    }

    @Override
	public int getPropertyCount() {
		return 8;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo info) {
		switch (arg0) {
		case 0:info.name="userid"; info.type = PropertyInfo.STRING_CLASS;break;
		case 1:info.name="password"; info.type = PropertyInfo.STRING_CLASS;break;
		case 2:info.name="sex"; info.type = PropertyInfo.STRING_CLASS;break;
		case 3:info.name="type"; info.type = PropertyInfo.STRING_CLASS;break;
		case 4:info.name="status"; info.type = PropertyInfo.STRING_CLASS;break;
		case 5:info.name="hospital"; info.type = PropertyInfo.STRING_CLASS;break;
		case 6:info.name="mail"; info.type = PropertyInfo.STRING_CLASS;break;
		case 7:info.name="image"; info.type = PropertyInfo.STRING_CLASS;break;
		default:
			break;
		}
		 
	}

	@Override
	public void setProperty(int arg0, Object arg1) {
		switch (arg0) {
		case 0: userid = toStringValue(arg1);break;
		case 1: password = toStringValue(arg1);break;
		case 2: sex = toStringValue(arg1);break;
		case 3: type = toStringValue(arg1);break;
		case 4: status = toStringValue(arg1);break;
		case 5: hospital = toStringValue(arg1);break;
		case 6: mail = toStringValue(arg1);break;
		case 7: image = toStringValue(arg1);break;
		
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
		case 0: return userid;
		case 1: return password;
		case 2: return sex;
		case 3: return type;
		case 4: return status;
		case 5: return hospital;
		case 6: return mail;
		case 7: return image;
		
		default:
			break;
		}
		return null;
		
	}
}
