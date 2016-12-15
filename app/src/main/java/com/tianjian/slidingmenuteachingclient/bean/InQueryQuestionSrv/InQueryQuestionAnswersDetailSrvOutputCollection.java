
package com.tianjian.slidingmenuteachingclient.bean.InQueryQuestionSrv;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>InQueryQuestionAnswersDetailSrvOutputCollection complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="InQueryQuestionAnswersDetailSrvOutputCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InQueryQuestionAnswersDetailSrvOutputItem" type="{http://tj.teach.com/InQueryQuestionSrv}InQueryQuestionAnswersDetailSrvOutputItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class InQueryQuestionAnswersDetailSrvOutputCollection {

    protected List<InQueryQuestionAnswersDetailSrvOutputItem> inQueryQuestionAnswersDetailSrvOutputItem;

    /**
     * Gets the value of the inQueryQuestionAnswersDetailSrvOutputItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inQueryQuestionAnswersDetailSrvOutputItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInQueryQuestionAnswersDetailSrvOutputItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InQueryQuestionAnswersDetailSrvOutputItem }
     * 
     * 
     */
    public List<InQueryQuestionAnswersDetailSrvOutputItem> getInQueryQuestionAnswersDetailSrvOutputItem() {
        if (inQueryQuestionAnswersDetailSrvOutputItem == null) {
            inQueryQuestionAnswersDetailSrvOutputItem = new ArrayList<InQueryQuestionAnswersDetailSrvOutputItem>();
        }
        return this.inQueryQuestionAnswersDetailSrvOutputItem;
    }

}
