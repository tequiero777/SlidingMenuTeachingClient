
package com.tianjian.slidingmenuteachingclient.bean.InQueryCountSrv;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;


/**
 * <p>InQueryCountSrvResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="InQueryCountSrvResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ErrorFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ErrorMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="InQueryCountSrvOutputCollection" type="{http://tj.teach.com/InQueryCountSrv}InQueryCountSrvOutputCollection"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class InQueryCountSrvResponse implements KvmSerializable {

    protected String errorFlag;
    protected String errorMessage;
    protected InQueryCountSrvOutputCollection inQueryCountSrvOutputCollection;

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
     * ��ȡinQueryCountSrvOutputCollection���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link InQueryCountSrvOutputCollection }
     *     
     */
    public InQueryCountSrvOutputCollection getInQueryCountSrvOutputCollection() {
        return inQueryCountSrvOutputCollection;
    }

    /**
     * ����inQueryCountSrvOutputCollection���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link InQueryCountSrvOutputCollection }
     *     
     */
    public void setInQueryCountSrvOutputCollection(InQueryCountSrvOutputCollection value) {
        this.inQueryCountSrvOutputCollection = value;
    }

    @Override
   	public Object getProperty(int arg0) {
   		switch (arg0) {
   		case 0:return errorFlag;
   		case 1:return errorMessage;
   		case 2:return inQueryCountSrvOutputCollection;
   		default:
   			break;
   		}
   		return null;
   	}

   	@Override
   	public int getPropertyCount() {
   		return 3;
   	}

   	@Override
   	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo info) {
   		switch (arg0) {
   		case 0:
   			 info.name="ErrorFlag";info.type= PropertyInfo.STRING_CLASS;break;
   		case 1:
   			 info.name="ErrorMessage";info.type= PropertyInfo.STRING_CLASS;break;
   		case 2:
   			 info.name="InQueryCountSrvOutputCollection";info.type=InQueryCountSrvOutputCollection.class;break;
   			
   		default:
   			break;
   		}
   		
   	}

   	@Override
   	public void setProperty(int arg0, Object arg1) {
   		switch (arg0) {
   		case 0:errorFlag = arg1==null?"":arg1+"";break;
   		case 1:errorMessage = arg1==null?"":arg1+"";break;
   		case 2:
   			SoapObject soapObject = (SoapObject) arg1;
   			inQueryCountSrvOutputCollection = new InQueryCountSrvOutputCollection();
   			for(int i =0 ;i< soapObject.getPropertyCount();i++){
   				inQueryCountSrvOutputCollection.setProperty(0, soapObject.getProperty(i));
   			}break;
   		default:
   			break;
   		}
   		
   	}
}
