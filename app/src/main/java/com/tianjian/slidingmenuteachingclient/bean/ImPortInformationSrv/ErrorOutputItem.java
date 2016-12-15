
package com.tianjian.slidingmenuteachingclient.bean.ImPortInformationSrv;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;


/**
 * <p>ErrorOutputItem complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ErrorOutputItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ErrorFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ErrorMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class ErrorOutputItem implements KvmSerializable {

    protected String errorFlag;
    protected String errorMessage;

    public String getErrorFlag() {
        return errorFlag;
    }

    public void setErrorFlag(String value) {
        this.errorFlag = value;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String value) {
        this.errorMessage = value;
    }

    @Override
   	public int getPropertyCount() {
   		return 2;
   	}

   	@Override
   	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo info) {
   		switch (arg0) {
   		case 0:info.name="errorFlag"; info.type = PropertyInfo.STRING_CLASS;break;
   		case 1:info.name="errorMessage"; info.type = PropertyInfo.STRING_CLASS;break;
   		default:
   			break;
   		}
   		 
   	}

   	@Override
   	public void setProperty(int arg0, Object arg1) {
   		switch (arg0) {
   		case 0: errorFlag = toStringValue(arg1);break;
   		case 1: errorMessage = toStringValue(arg1);break;
   		
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
   		case 0: return errorFlag;
   		case 1: return errorMessage;
   		
   		default:
   			break;
   		}
   		return null;
   		
   	}
}
