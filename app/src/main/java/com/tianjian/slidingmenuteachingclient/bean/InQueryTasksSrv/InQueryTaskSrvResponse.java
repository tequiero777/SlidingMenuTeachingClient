
package com.tianjian.slidingmenuteachingclient.bean.InQueryTasksSrv;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;


/**
 * <p>InQueryTaskSrvResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="InQueryTaskSrvResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ErrorFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ErrorMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="COUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="InQueryTaskSrvOutputCollection" type="{http://tj.teach.com/InQueryTaskSrv}InQueryTaskSrvOutputCollection"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class InQueryTaskSrvResponse implements KvmSerializable {

    protected String errorFlag;
    protected String errorMessage;
    protected String count;
    protected InQueryTaskSrvOutputCollection inQueryTaskSrvOutputCollection;

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
     * ��ȡinQueryTaskSrvOutputCollection���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link InQueryTaskSrvOutputCollection }
     *     
     */
    public InQueryTaskSrvOutputCollection getInQueryTaskSrvOutputCollection() {
        return inQueryTaskSrvOutputCollection;
    }

    /**
     * ����inQueryTaskSrvOutputCollection���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link InQueryTaskSrvOutputCollection }
     *     
     */
    public void setInQueryTaskSrvOutputCollection(InQueryTaskSrvOutputCollection value) {
        this.inQueryTaskSrvOutputCollection = value;
    }

    @Override
   	public Object getProperty(int arg0) {
   		switch (arg0) {
   		case 0:return errorFlag;
   		case 1:return errorMessage;
   		case 2:return count;
   		case 3:return inQueryTaskSrvOutputCollection;
   		default:
   			break;
   		}
   		return null;
   	}

   	@Override
   	public int getPropertyCount() {
   		return 4;
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
   			 info.name="InQueryTaskSrvOutputCollection";info.type=InQueryTaskSrvOutputCollection.class;break;
   			
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
   		case 3:
   			SoapObject soapObject = (SoapObject) arg1;
   			inQueryTaskSrvOutputCollection = new InQueryTaskSrvOutputCollection();
   			for(int i =0 ;i< soapObject.getPropertyCount();i++){
   				inQueryTaskSrvOutputCollection.setProperty(0, soapObject.getProperty(i));
   			}break;
   		default:
   			break;
   		}
   		
   	}
}
