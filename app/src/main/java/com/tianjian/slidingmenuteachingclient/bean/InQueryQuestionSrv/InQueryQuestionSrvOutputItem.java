
package com.tianjian.slidingmenuteachingclient.bean.InQueryQuestionSrv;


/**
 * <p>InQueryQuestionSrvOutputItem complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="InQueryQuestionSrvOutputItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="QUESTION_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TITLE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CLASS_TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SECRET_TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="USERNAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TIME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CONTENT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ANSWER_STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EFFECTIVE_STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="QUESTION_QUALITY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SCORE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IMAGE" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="RESOURCES_DETAIL_LINE" type="{http://tj.teach.com/InQueryQuestionSrv}InQueryQuestionResourcesDetailSrvOutputCollection"/>
 *         &lt;element name="ANSWERS_DETAIL_LINE" type="{http://tj.teach.com/InQueryQuestionSrv}InQueryQuestionAnswersDetailSrvOutputCollection"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class InQueryQuestionSrvOutputItem {

    protected String questionid;
    protected String title;
    protected String classtype;
    protected String secrettype;
    protected String username;
    protected String name;
    protected String time;
    protected String content;
    protected String answerstatus;
    protected String effectivestatus;
    protected String questionquality;
    protected String score;
    protected byte[] image;
    protected InQueryQuestionResourcesDetailSrvOutputCollection resourcesdetailline;
    protected InQueryQuestionAnswersDetailSrvOutputCollection answersdetailline;

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
     * ��ȡtitle���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTITLE() {
        return title;
    }

    /**
     * ����title���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTITLE(String value) {
        this.title = value;
    }

    /**
     * ��ȡclasstype���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCLASSTYPE() {
        return classtype;
    }

    /**
     * ����classtype���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCLASSTYPE(String value) {
        this.classtype = value;
    }

    /**
     * ��ȡsecrettype���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSECRETTYPE() {
        return secrettype;
    }

    /**
     * ����secrettype���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSECRETTYPE(String value) {
        this.secrettype = value;
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
     * ��ȡcontent���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCONTENT() {
        return content;
    }

    /**
     * ����content���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCONTENT(String value) {
        this.content = value;
    }

    /**
     * ��ȡanswerstatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getANSWERSTATUS() {
        return answerstatus;
    }

    /**
     * ����answerstatus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setANSWERSTATUS(String value) {
        this.answerstatus = value;
    }

    /**
     * ��ȡeffectivestatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEFFECTIVESTATUS() {
        return effectivestatus;
    }

    /**
     * ����effectivestatus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEFFECTIVESTATUS(String value) {
        this.effectivestatus = value;
    }

    /**
     * ��ȡquestionquality���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQUESTIONQUALITY() {
        return questionquality;
    }

    /**
     * ����questionquality���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQUESTIONQUALITY(String value) {
        this.questionquality = value;
    }

    /**
     * ��ȡscore���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSCORE() {
        return score;
    }

    /**
     * ����score���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSCORE(String value) {
        this.score = value;
    }

    /**
     * ��ȡimage���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getIMAGE() {
        return image;
    }

    /**
     * ����image���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setIMAGE(byte[] value) {
        this.image = value;
    }

    /**
     * ��ȡresourcesdetailline���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link InQueryQuestionResourcesDetailSrvOutputCollection }
     *     
     */
    public InQueryQuestionResourcesDetailSrvOutputCollection getRESOURCESDETAILLINE() {
        return resourcesdetailline;
    }

    /**
     * ����resourcesdetailline���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link InQueryQuestionResourcesDetailSrvOutputCollection }
     *     
     */
    public void setRESOURCESDETAILLINE(InQueryQuestionResourcesDetailSrvOutputCollection value) {
        this.resourcesdetailline = value;
    }

    /**
     * ��ȡanswersdetailline���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link InQueryQuestionAnswersDetailSrvOutputCollection }
     *     
     */
    public InQueryQuestionAnswersDetailSrvOutputCollection getANSWERSDETAILLINE() {
        return answersdetailline;
    }

    /**
     * ����answersdetailline���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link InQueryQuestionAnswersDetailSrvOutputCollection }
     *     
     */
    public void setANSWERSDETAILLINE(InQueryQuestionAnswersDetailSrvOutputCollection value) {
        this.answersdetailline = value;
    }

}
