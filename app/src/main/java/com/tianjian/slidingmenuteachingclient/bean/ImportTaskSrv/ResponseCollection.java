
package com.tianjian.slidingmenuteachingclient.bean.ImportTaskSrv;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


/**
 * <p>ResponseCollection complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ResponseCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResponseOutputItem" type="{http://tj.teach.com/ImPortTaskSrv}ResponseOutputItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class ResponseCollection  implements KvmSerializable {

    protected List<ResponseOutputItem> responseOutputItem;

    /**
     * Gets the value of the responseOutputItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the responseOutputItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResponseOutputItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResponseOutputItem }
     * 
     * 
     */
    public List<ResponseOutputItem> getResponseOutputItem() {
        if (responseOutputItem == null) {
            responseOutputItem = new ArrayList<ResponseOutputItem>();
        }
        return this.responseOutputItem;
    }

    @Override
   	public Object getProperty(int arg0) {
   		switch (arg0) {
   		case 0:
   			return responseOutputItem;

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
   			arg2.name ="ResponseCollection";arg2.type = getResponseOutputItem().getClass();
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
   			responseOutputItem = getResponseOutputItem();
   			ResponseOutputItem item = new ResponseOutputItem(); 
   			for(int i =0 ;i< soapObject.getPropertyCount();i++){
   				item.setProperty(i, soapObject.getProperty(i));
   			}
   			responseOutputItem.add(item);
   			break;

   		default:
   			break;
   		}
   	}
}
