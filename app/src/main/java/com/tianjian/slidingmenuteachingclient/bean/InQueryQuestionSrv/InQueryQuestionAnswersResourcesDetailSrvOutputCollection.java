
package com.tianjian.slidingmenuteachingclient.bean.InQueryQuestionSrv;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>InQueryQuestionAnswersResourcesDetailSrvOutputCollection complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="InQueryQuestionAnswersResourcesDetailSrvOutputCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InQueryQuestionAnswersResourcesDetailSrvOutputItem" type="{http://tj.teach.com/InQueryQuestionSrv}InQueryQuestionAnswersResourcesDetailSrvOutputItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class InQueryQuestionAnswersResourcesDetailSrvOutputCollection {

    protected List<InQueryQuestionAnswersResourcesDetailSrvOutputItem> inQueryQuestionAnswersResourcesDetailSrvOutputItem;

    /**
     * Gets the value of the inQueryQuestionAnswersResourcesDetailSrvOutputItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inQueryQuestionAnswersResourcesDetailSrvOutputItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInQueryQuestionAnswersResourcesDetailSrvOutputItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InQueryQuestionAnswersResourcesDetailSrvOutputItem }
     * 
     * 
     */
    public List<InQueryQuestionAnswersResourcesDetailSrvOutputItem> getInQueryQuestionAnswersResourcesDetailSrvOutputItem() {
        if (inQueryQuestionAnswersResourcesDetailSrvOutputItem == null) {
            inQueryQuestionAnswersResourcesDetailSrvOutputItem = new ArrayList<InQueryQuestionAnswersResourcesDetailSrvOutputItem>();
        }
        return this.inQueryQuestionAnswersResourcesDetailSrvOutputItem;
    }

}
