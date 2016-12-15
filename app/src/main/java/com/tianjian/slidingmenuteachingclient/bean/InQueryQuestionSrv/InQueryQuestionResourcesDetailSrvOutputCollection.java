
package com.tianjian.slidingmenuteachingclient.bean.InQueryQuestionSrv;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>InQueryQuestionResourcesDetailSrvOutputCollection complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="InQueryQuestionResourcesDetailSrvOutputCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InQueryQuestionResourcesDetailSrvOutputItem" type="{http://tj.teach.com/InQueryQuestionSrv}InQueryQuestionResourcesDetailSrvOutputItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class InQueryQuestionResourcesDetailSrvOutputCollection {

    protected List<InQueryQuestionResourcesDetailSrvOutputItem> inQueryQuestionResourcesDetailSrvOutputItem;

    /**
     * Gets the value of the inQueryQuestionResourcesDetailSrvOutputItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inQueryQuestionResourcesDetailSrvOutputItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInQueryQuestionResourcesDetailSrvOutputItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InQueryQuestionResourcesDetailSrvOutputItem }
     * 
     * 
     */
    public List<InQueryQuestionResourcesDetailSrvOutputItem> getInQueryQuestionResourcesDetailSrvOutputItem() {
        if (inQueryQuestionResourcesDetailSrvOutputItem == null) {
            inQueryQuestionResourcesDetailSrvOutputItem = new ArrayList<InQueryQuestionResourcesDetailSrvOutputItem>();
        }
        return this.inQueryQuestionResourcesDetailSrvOutputItem;
    }

}
