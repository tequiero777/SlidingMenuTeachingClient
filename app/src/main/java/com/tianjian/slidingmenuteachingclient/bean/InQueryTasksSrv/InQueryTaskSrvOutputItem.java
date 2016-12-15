
package com.tianjian.slidingmenuteachingclient.bean.InQueryTasksSrv;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;


/**
 * <p>InQueryTaskSrvOutputItem complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="InQueryTaskSrvOutputItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TASK_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CONTENT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MENTOR_USERNAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MENTOR_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TIME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EFFECTIVE_STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RECEIVE_USERNAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RECEIVE_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RESOURCES_DETAIL_LINE" type="{http://tj.teach.com/InQueryTaskSrv}InQueryTaskResourcesDetailSrvOutputCollection"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class InQueryTaskSrvOutputItem implements KvmSerializable {

    protected String taskid;
    protected String name;
    protected String content;
    protected String mentorusername;
    protected String mentorname;
    protected String time;
    protected String status;
    protected String effectivestatus;
    protected String receiveusername;
    protected String receivename;
    protected InQueryTaskResourcesDetailSrvOutputCollection resourcesdetailline;

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
     * ��ȡcontent���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCONTENT() {
        return content;
    }

    /**
     * ����content���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCONTENT(String value) {
        this.content = value;
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

    /**
     * ��ȡtime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIME() {
        return time;
    }

    /**
     * ����time���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIME(String value) {
        this.time = value;
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
     * ��ȡeffectivestatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEFFECTIVESTATUS() {
        return effectivestatus;
    }

    /**
     * ����effectivestatus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEFFECTIVESTATUS(String value) {
        this.effectivestatus = value;
    }

    /**
     * ��ȡreceiveusername���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRECEIVEUSERNAME() {
        return receiveusername;
    }

    /**
     * ����receiveusername���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRECEIVEUSERNAME(String value) {
        this.receiveusername = value;
    }

    /**
     * ��ȡreceivename���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRECEIVENAME() {
        return receivename;
    }

    /**
     * ����receivename���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRECEIVENAME(String value) {
        this.receivename = value;
    }

    /**
     * ��ȡresourcesdetailline���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link InQueryTaskResourcesDetailSrvOutputCollection }
     *     
     */
    public InQueryTaskResourcesDetailSrvOutputCollection getRESOURCESDETAILLINE() {
        return resourcesdetailline;
    }

    /**
     * ����resourcesdetailline���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link InQueryTaskResourcesDetailSrvOutputCollection }
     *     
     */
    public void setRESOURCESDETAILLINE(InQueryTaskResourcesDetailSrvOutputCollection value) {
        this.resourcesdetailline = value;
    }

    @Override
	public int getPropertyCount() {
		return 11;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo info) {
		switch (arg0) {
		case 0:info.name="taskid"; info.type = PropertyInfo.STRING_CLASS;break;
		case 1:info.name="name"; info.type = PropertyInfo.STRING_CLASS;break;
		case 2:info.name="content"; info.type = PropertyInfo.STRING_CLASS;break;
		case 3:info.name="mentorusername"; info.type = PropertyInfo.STRING_CLASS;break;
		case 4:info.name="mentorname"; info.type = PropertyInfo.STRING_CLASS;break;
		case 5:info.name="time"; info.type = PropertyInfo.STRING_CLASS;break;
		case 6:info.name="status"; info.type = PropertyInfo.STRING_CLASS;break;
		case 7:info.name="effectivestatus"; info.type = PropertyInfo.STRING_CLASS;break;
		case 8:info.name="receiveusername"; info.type = PropertyInfo.STRING_CLASS;break;
		case 9:info.name="receivename"; info.type = PropertyInfo.STRING_CLASS;break;
		case 10:info.name="resourcesdetailline"; info.type = InQueryTaskResourcesDetailSrvOutputCollection.class;break;
		default:
			break;
		}
		 
	}

	@Override
	public void setProperty(int arg0, Object arg1) {
		switch (arg0) {
		case 0: taskid = toStringValue(arg1);break;
		case 1: name = toStringValue(arg1);break;
		case 2: content = toStringValue(arg1);break;
		case 3: mentorusername = toStringValue(arg1);break;
		case 4: mentorname = toStringValue(arg1);break;
		case 5: time = toStringValue(arg1);break;
		case 6: status = toStringValue(arg1);break;
		case 7: effectivestatus = toStringValue(arg1);break;
		case 8: receiveusername = toStringValue(arg1);break;
		case 9: receivename = toStringValue(arg1);break;
		case 10: SoapObject soapObject = (SoapObject) arg1;
		resourcesdetailline = new InQueryTaskResourcesDetailSrvOutputCollection();
			for(int i =0 ;i< soapObject.getPropertyCount();i++){
				resourcesdetailline.setProperty(0, soapObject.getProperty(i));
			}break;
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
		case 0: return taskid;
		case 1: return name;
		case 2: return content;
		case 3: return mentorusername;
		case 4: return mentorname;
		case 5: return time;
		case 6: return status;
		case 7: return effectivestatus;
		case 8: return receiveusername;
		case 9: return receivename;
		case 10: return resourcesdetailline;
		
		default:
			break;
		}
		return null;
		
	}
}
