
package com.tianjian.slidingmenuteachingclient.bean.InQueryCountSrv;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;


/**
 * <p>InQueryCountSrvOutputItem complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="InQueryCountSrvOutputItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TASK_COUT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PRIVATE_COUT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PULIC_COUT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CONSULTATION_COUT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SEND_COUT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GET_COUT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="REPLY_COUT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="REPLY_CONSULTATION_COUT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class InQueryCountSrvOutputItem implements KvmSerializable {

    protected String taskcout;
    protected String privatecout;
    protected String puliccout;
    protected String consultationcout;
    protected String sendcout;
    protected String getcout;
    protected String replycout;
    protected String replyconsultationcout;

    /**
     * ��ȡtaskcout���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTASKCOUT() {
        return taskcout;
    }

    /**
     * ����taskcout���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTASKCOUT(String value) {
        this.taskcout = value;
    }

    /**
     * ��ȡprivatecout���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRIVATECOUT() {
        return privatecout;
    }

    /**
     * ����privatecout���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRIVATECOUT(String value) {
        this.privatecout = value;
    }

    /**
     * ��ȡpuliccout���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPULICCOUT() {
        return puliccout;
    }

    /**
     * ����puliccout���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPULICCOUT(String value) {
        this.puliccout = value;
    }

    /**
     * ��ȡconsultationcout���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCONSULTATIONCOUT() {
        return consultationcout;
    }

    /**
     * ����consultationcout���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCONSULTATIONCOUT(String value) {
        this.consultationcout = value;
    }

    /**
     * ��ȡsendcout���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSENDCOUT() {
        return sendcout;
    }

    /**
     * ����sendcout���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSENDCOUT(String value) {
        this.sendcout = value;
    }

    /**
     * ��ȡgetcout���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGETCOUT() {
        return getcout;
    }

    /**
     * ����getcout���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGETCOUT(String value) {
        this.getcout = value;
    }

    /**
     * ��ȡreplycout���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREPLYCOUT() {
        return replycout;
    }

    /**
     * ����replycout���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREPLYCOUT(String value) {
        this.replycout = value;
    }

    /**
     * ��ȡreplyconsultationcout���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREPLYCONSULTATIONCOUT() {
        return replyconsultationcout;
    }

    /**
     * ����replyconsultationcout���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREPLYCONSULTATIONCOUT(String value) {
        this.replyconsultationcout = value;
    }

    @Override
	public int getPropertyCount() {
		return 8;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo info) {
		switch (arg0) {
		case 0:info.name="taskcout"; info.type = PropertyInfo.STRING_CLASS;break;
		case 1:info.name="privatecout"; info.type = PropertyInfo.STRING_CLASS;break;
		case 2:info.name="puliccout"; info.type = PropertyInfo.STRING_CLASS;break;
		case 3:info.name="consultationcout"; info.type = PropertyInfo.STRING_CLASS;break;
		case 4:info.name="sendcout"; info.type = PropertyInfo.STRING_CLASS;break;
		case 5:info.name="getcout"; info.type = PropertyInfo.STRING_CLASS;break;
		case 6:info.name="replycout"; info.type = PropertyInfo.STRING_CLASS;break;
		case 7:info.name="replyconsultationcout"; info.type = PropertyInfo.STRING_CLASS;break;
		default:
			break;
		}
		 
	}

	@Override
	public void setProperty(int arg0, Object arg1) {
		switch (arg0) {
		case 0: taskcout = toStringValue(arg1);break;
		case 1: privatecout = toStringValue(arg1);break;
		case 2: puliccout = toStringValue(arg1);break;
		case 3: consultationcout = toStringValue(arg1);break;
		case 4: sendcout = toStringValue(arg1);break;
		case 5: getcout = toStringValue(arg1);break;
		case 6: replycout = toStringValue(arg1);break;
		case 7: replyconsultationcout = toStringValue(arg1);break;
		
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
		case 0: return taskcout;
		case 1: return privatecout;
		case 2: return puliccout;
		case 3: return consultationcout;
		case 4: return sendcout;
		case 5: return getcout;
		case 6: return replycout;
		case 7: return replyconsultationcout;
		
		default:
			break;
		}
		return null;
		
	}
}
