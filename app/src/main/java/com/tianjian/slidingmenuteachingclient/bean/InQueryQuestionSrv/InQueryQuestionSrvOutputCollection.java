
package com.tianjian.slidingmenuteachingclient.bean.InQueryQuestionSrv;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>InQueryQuestionSrvOutputCollection complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="InQueryQuestionSrvOutputCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InQueryQuestionSrvOutputItem" type="{http://tj.teach.com/InQueryQuestionSrv}InQueryQuestionSrvOutputItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class InQueryQuestionSrvOutputCollection {

    protected List<InQueryQuestionSrvOutputItem> inQueryQuestionSrvOutputItem;

    /**
     * Gets the value of the inQueryQuestionSrvOutputItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inQueryQuestionSrvOutputItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInQueryQuestionSrvOutputItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InQueryQuestionSrvOutputItem }
     * 
     * 
     */
    public List<InQueryQuestionSrvOutputItem> getInQueryQuestionSrvOutputItem() {
        if (inQueryQuestionSrvOutputItem == null) {
            inQueryQuestionSrvOutputItem = new ArrayList<InQueryQuestionSrvOutputItem>();
        }
        return this.inQueryQuestionSrvOutputItem;
    }

}
