
package com.tianjian.slidingmenuteachingclient.bean.InQueryAppUpdateSrv;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


/**
 * <p>DOC_INV_HIS_InQueryAppUpdateSrvOutputCollection complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="DOC_INV_HIS_InQueryAppUpdateSrvOutputCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DOC_INV_HIS_InQueryAppUpdateSrvOutputItem" type="{http://tj.his.com/DOC_INV_HIS_InQueryAppUpdateSrv}DOC_INV_HIS_InQueryAppUpdateSrvOutputItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class DOCINVHISInQueryAppUpdateSrvOutputCollection implements KvmSerializable {

    protected List<DOCINVHISInQueryAppUpdateSrvOutputItem> docinvhisInQueryAppUpdateSrvOutputItem;

    /**
     * Gets the value of the docinvhisInQueryAppUpdateSrvOutputItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the docinvhisInQueryAppUpdateSrvOutputItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDOCINVHISInQueryAppUpdateSrvOutputItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DOCINVHISInQueryAppUpdateSrvOutputItem }
     * 
     * 
     */
    public List<DOCINVHISInQueryAppUpdateSrvOutputItem> getDOCINVHISInQueryAppUpdateSrvOutputItem() {
        if (docinvhisInQueryAppUpdateSrvOutputItem == null) {
            docinvhisInQueryAppUpdateSrvOutputItem = new ArrayList<DOCINVHISInQueryAppUpdateSrvOutputItem>();
        }
        return this.docinvhisInQueryAppUpdateSrvOutputItem;
    }

    
    @Override
	public Object getProperty(int arg0) {
		switch (arg0) {
		case 0:
			return docinvhisInQueryAppUpdateSrvOutputItem;

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
			arg2.name ="DOC_INV_HIS_InQueryDeptSrvOutputItem";arg2.type = getDOCINVHISInQueryAppUpdateSrvOutputItem().getClass();
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
			docinvhisInQueryAppUpdateSrvOutputItem = getDOCINVHISInQueryAppUpdateSrvOutputItem();
			DOCINVHISInQueryAppUpdateSrvOutputItem item = new DOCINVHISInQueryAppUpdateSrvOutputItem(); 
			for(int i =0 ;i< soapObject.getPropertyCount();i++){
				item.setProperty(i, soapObject.getProperty(i));
			}
			docinvhisInQueryAppUpdateSrvOutputItem.add(item);
			break;

		default:
			break;
		}
		
		
	}
}
