
package com.tianjian.slidingmenuteachingclient.bean.InQueryQuestionSrv;



/**
 * <p>InQueryQuestionAnswersDetailSrvOutputItem complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="InQueryQuestionAnswersDetailSrvOutputItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ANSWER_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="USERNAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="QUESTION_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ANSWER_CONTENT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TIME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RE_ANSWER_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ANSWERS_RESOURCES_DETAIL_LINE" type="{http://tj.teach.com/InQueryQuestionSrv}InQueryQuestionAnswersResourcesDetailSrvOutputCollection"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class InQueryQuestionAnswersDetailSrvOutputItem {

    protected String answerid;
    protected String username;
    protected String name;
    protected String type;
    protected String questionid;
    protected String answercontent;
    protected String time;
    protected String reanswerid;
    protected InQueryQuestionAnswersResourcesDetailSrvOutputCollection answersresourcesdetailline;

    /**
     * ��ȡanswerid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getANSWERID() {
        return answerid;
    }

    /**
     * ����answerid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setANSWERID(String value) {
        this.answerid = value;
    }

    /**
     * ��ȡusername���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSERNAME() {
        return username;
    }

    /**
     * ����username���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSERNAME(String value) {
        this.username = value;
    }

    /**
     * ��ȡname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNAME() {
        return name;
    }

    /**
     * ����name���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNAME(String value) {
        this.name = value;
    }

    /**
     * ��ȡtype���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTYPE() {
        return type;
    }

    /**
     * ����type���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTYPE(String value) {
        this.type = value;
    }

    /**
     * ��ȡquestionid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQUESTIONID() {
        return questionid;
    }

    /**
     * ����questionid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQUESTIONID(String value) {
        this.questionid = value;
    }

    /**
     * ��ȡanswercontent���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getANSWERCONTENT() {
        return answercontent;
    }

    /**
     * ����answercontent���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setANSWERCONTENT(String value) {
        this.answercontent = value;
    }

    /**
     * ��ȡtime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIME() {
        return time;
    }

    /**
     * ����time���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIME(String value) {
        this.time = value;
    }

    /**
     * ��ȡreanswerid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREANSWERID() {
        return reanswerid;
    }

    /**
     * ����reanswerid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREANSWERID(String value) {
        this.reanswerid = value;
    }

    /**
     * ��ȡanswersresourcesdetailline���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link InQueryQuestionAnswersResourcesDetailSrvOutputCollection }
     *     
     */
    public InQueryQuestionAnswersResourcesDetailSrvOutputCollection getANSWERSRESOURCESDETAILLINE() {
        return answersresourcesdetailline;
    }

    /**
     * ����answersresourcesdetailline���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link InQueryQuestionAnswersResourcesDetailSrvOutputCollection }
     *     
     */
    public void setANSWERSRESOURCESDETAILLINE(InQueryQuestionAnswersResourcesDetailSrvOutputCollection value) {
        this.answersresourcesdetailline = value;
    }

}
