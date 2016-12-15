
package com.tianjian.slidingmenuteachingclient.bean.InQueryQuestionSrv;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;


/**
 * <p>InQueryQuestionSrvResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="InQueryQuestionSrvResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ErrorFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ErrorMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="COUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="JSON" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="InQueryQuestionSrvOutputCollection" type="{http://tj.teach.com/InQueryQuestionSrv}InQueryQuestionSrvOutputCollection"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class InQueryQuestionSrvResponse implements KvmSerializable {

    protected String errorFlag;
    protected String errorMessage;
    protected String count;
    protected String json;
    protected InQueryQuestionSrvOutputCollection inQueryQuestionSrvOutputCollection;

    /**
     * ��ȡerrorFlag���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorFlag() {
        return errorFlag;
    }

    /**
     * ����errorFlag���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorFlag(String value) {
        this.errorFlag = value;
    }

    /**
     * ��ȡerrorMessage���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * ����errorMessage���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorMessage(String value) {
        this.errorMessage = value;
    }

    /**
     * ��ȡcount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOUNT() {
        return count;
    }

    /**
     * ����count���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOUNT(String value) {
        this.count = value;
    }

    /**
     * ��ȡjson���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJSON() {
        return json;
    }

    /**
     * ����json���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJSON(String value) {
        this.json = value;
    }

    /**
     * ��ȡinQueryQuestionSrvOutputCollection���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link InQueryQuestionSrvOutputCollection }
     *     
     */
    public InQueryQuestionSrvOutputCollection getInQueryQuestionSrvOutputCollection() {
        return inQueryQuestionSrvOutputCollection;
    }

    /**
     * ����inQueryQuestionSrvOutputCollection���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link InQueryQuestionSrvOutputCollection }
     *     
     */
    public void setInQueryQuestionSrvOutputCollection(InQueryQuestionSrvOutputCollection value) {
        this.inQueryQuestionSrvOutputCollection = value;
    }

    @Override
   	public Object getProperty(int arg0) {
   		switch (arg0) {
   		case 0:return errorFlag;
   		case 1:return errorMessage;
   		case 2:return count;
   		case 3:return json;
   		case 4:return inQueryQuestionSrvOutputCollection;
   		default:
   			break;
   		}
   		return null;
   	}

   	@Override
   	public int getPropertyCount() {
   		return 5;
   	}

   	@Override
   	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo info) {
   		switch (arg0) {
   		case 0:
   			 info.name="ErrorFlag";info.type= PropertyInfo.STRING_CLASS;break;
   		case 1:
   			 info.name="ErrorMessage";info.type= PropertyInfo.STRING_CLASS;break;
   		case 2:
  			 info.name="COUNT";info.type= PropertyInfo.STRING_CLASS;break;
   		case 3:
  			 info.name="JSON";info.type= PropertyInfo.STRING_CLASS;break;
   		case 4:
   			 info.name="InQueryQuestionSrvOutputCollection";info.type=InQueryQuestionSrvOutputCollection.class;break;
   			
   		default:
   			break;
   		}
   		
   	}

   	@Override
   	public void setProperty(int arg0, Object arg1) {
   		switch (arg0) {
   		case 0:errorFlag = arg1==null?"":arg1+"";break;
   		case 1:errorMessage = arg1==null?"":arg1+"";break;
   		case 2:count = arg1==null?"":arg1+"";break;
   		case 3:json = arg1==null?"":arg1+"";break;
   		case 4:inQueryQuestionSrvOutputCollection = null;break;
   		default:
   			break;
   		}
   		
   	}
}
