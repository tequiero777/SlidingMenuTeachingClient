
package com.tianjian.slidingmenuteachingclient.bean.ImPortInformationSrv;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;


/**
 * <p>ImPortInformationSrvResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ImPortInformationSrvResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ErrorFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ErrorMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ErrorCollection" type="{http://tj.teach.com/ImPortInformationSrv}ErrorCollection"/>
 *         &lt;element name="ResponseCollection" type="{http://tj.teach.com/ImPortInformationSrv}ResponseCollection"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class ImPortInformationSrvResponse implements KvmSerializable {

    protected String errorFlag;
    protected String errorMessage;
    protected ErrorCollection errorCollection;
    protected ResponseCollection responseCollection;

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
     * ��ȡerrorCollection���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ErrorCollection }
     *     
     */
    public ErrorCollection getErrorCollection() {
        return errorCollection;
    }

    /**
     * ����errorCollection���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorCollection }
     *     
     */
    public void setErrorCollection(ErrorCollection value) {
        this.errorCollection = value;
    }

    /**
     * ��ȡresponseCollection���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ResponseCollection }
     *     
     */
    public ResponseCollection getResponseCollection() {
        return responseCollection;
    }

    /**
     * ����responseCollection���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseCollection }
     *     
     */
    public void setResponseCollection(ResponseCollection value) {
        this.responseCollection = value;
    }

    @Override
   	public Object getProperty(int arg0) {
   		switch (arg0) {
   		case 0:return errorFlag;
   		case 1:return errorMessage;
   		case 2:return errorCollection;
   		case 3:return responseCollection;
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
   			 info.name="ErrorCollection";info.type=ErrorCollection.class;break;
   		case 3:
  			 info.name="ResponseCollection";info.type=ResponseCollection.class;break;
  			 
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
   			errorCollection = new ErrorCollection();
   			for(int i =0 ;i< soapObject.getPropertyCount();i++){
   				errorCollection.setProperty(0, soapObject.getProperty(i));
   			}break;
   		case 3:
   			SoapObject soapObject1 = (SoapObject) arg1;
   			responseCollection = new ResponseCollection();
   			for(int i =0 ;i< soapObject1.getPropertyCount();i++){
   				responseCollection.setProperty(0, soapObject1.getProperty(i));
   			}break;
   		default:
   			break;
   		}
   		
   	}
}
