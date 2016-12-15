
package com.tianjian.slidingmenuteachingclient.bean.InLoginSrv;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.IOException;
import java.util.Hashtable;

import Decoder.BASE64Decoder;


/**
 * <p>InLoginSrvOutputItem complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="InLoginSrvOutputItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="USERNAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PASSWORD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SEX" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HOSPITAL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IMAGE" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="MENTOR_USERNAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MENTOR_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class InLoginSrvOutputItem implements KvmSerializable {

    protected String username;
    protected String password;
    protected String name;
    protected String sex;
    protected String type;
    protected String status;
    protected String hospital;
    protected byte[] image;
    protected String mentorusername;
    protected String mentorname;

    /**
     * ��ȡusername���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSERNAME() {
        return username;
    }

    /**
     * ����username���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSERNAME(String value) {
        this.username = value;
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
     * ��ȡname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNAME() {
        return name;
    }

    /**
     * ����name���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNAME(String value) {
        this.name = value;
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
     * ��ȡmentorusername���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMENTORUSERNAME() {
        return mentorusername;
    }

    /**
     * ����mentorusername���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMENTORUSERNAME(String value) {
        this.mentorusername = value;
    }

    /**
     * ��ȡmentorname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMENTORNAME() {
        return mentorname;
    }

    /**
     * ����mentorname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMENTORNAME(String value) {
        this.mentorname = value;
    }

    @Override
	public int getPropertyCount() {
		return 10;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo info) {
		switch (arg0) {
		case 0:info.name="username"; info.type = PropertyInfo.STRING_CLASS;break;
		case 1:info.name="password"; info.type = PropertyInfo.STRING_CLASS;break;
		case 2:info.name="name"; info.type = PropertyInfo.STRING_CLASS;break;
		case 3:info.name="sex"; info.type = PropertyInfo.STRING_CLASS;break;
		case 4:info.name="type"; info.type = PropertyInfo.STRING_CLASS;break;
		case 5:info.name="status"; info.type = PropertyInfo.STRING_CLASS;break;
		case 6:info.name="hospital"; info.type = PropertyInfo.STRING_CLASS;break;
		case 7:info.name="image"; info.type = PropertyInfo.STRING_CLASS;break;
		case 8:info.name="mentorusername"; info.type = PropertyInfo.STRING_CLASS;break;
		case 9:info.name="mentorname"; info.type = PropertyInfo.STRING_CLASS;break;
		default:
			break;
		}
		 
	}

	@Override
	public void setProperty(int arg0, Object arg1) {
		switch (arg0) {
		case 0: username = toStringValue(arg1);break;
		case 1: password = toStringValue(arg1);break;
		case 2: name = toStringValue(arg1);break;
		case 3: sex = toStringValue(arg1);break;
		case 4: type = toStringValue(arg1);break;
		case 5: status = toStringValue(arg1);break;
		case 6: hospital = toStringValue(arg1);break;
		case 7: image = toByteValue(arg1);break;
		case 8: mentorusername = toStringValue(arg1);break;
		case 9: mentorname = toStringValue(arg1);break;
		
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
		case 0: return username;
		case 1: return password;
		case 2: return name;
		case 3: return sex;
		case 4: return type;
		case 5: return status;
		case 6: return hospital;
		case 7: return image;
		case 8: return mentorusername;
		case 9: return mentorname;
		
		default:
			break;
		}
		return null;
		
	}
}
