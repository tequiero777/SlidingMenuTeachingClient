
package com.tianjian.slidingmenuteachingclient.bean.ImPortInformationSrv;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


/**
 * <p>ErrorCollection complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ErrorCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ErrorOutputItem" type="{http://tj.teach.com/ImPortInformationSrv}ErrorOutputItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class ErrorCollection implements KvmSerializable {

    protected List<ErrorOutputItem> errorOutputItem;

    /**
     * Gets the value of the errorOutputItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errorOutputItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrorOutputItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ErrorOutputItem }
     * 
     * 
     */
    public List<ErrorOutputItem> getErrorOutputItem() {
        if (errorOutputItem == null) {
            errorOutputItem = new ArrayList<ErrorOutputItem>();
        }
        return this.errorOutputItem;
    }

    @Override
   	public Object getProperty(int arg0) {
   		switch (arg0) {
   		case 0:
   			return errorOutputItem;

   		default:
   			break;
   		}
   		return null;
   	}

   	@Override
   	public int getPropertyCount() {
   		return 1;
   	}

   	@Override
   	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
   		switch (arg0) {
   		case 0:
   			arg2.name ="ErrorOutputItem";arg2.type = getErrorOutputItem().getClass();
   			break;

   		default:
   			break;
   		}
   		
   	}

   	@Override
   	public void setProperty(int arg0, Object arg1) {
   		switch (arg0) {
   		case 0:
   			SoapObject soapObject = (SoapObject) arg1;
   			errorOutputItem = getErrorOutputItem();
   			ErrorOutputItem item = new ErrorOutputItem(); 
   			for(int i =0 ;i< soapObject.getPropertyCount();i++){
   				item.setProperty(i, soapObject.getProperty(i));
   			}
   			errorOutputItem.add(item);
   			break;

   		default:
   			break;
   		}
   	}
}
