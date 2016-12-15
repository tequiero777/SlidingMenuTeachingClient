
package com.tianjian.slidingmenuteachingclient.bean.InQueryQuestionSrv;

import java.util.ArrayList;
import java.util.List;

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
