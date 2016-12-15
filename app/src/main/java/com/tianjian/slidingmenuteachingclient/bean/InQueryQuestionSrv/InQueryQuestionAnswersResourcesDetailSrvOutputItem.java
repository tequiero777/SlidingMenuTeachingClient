
package com.tianjian.slidingmenuteachingclient.bean.InQueryQuestionSrv;



/**
 * <p>InQueryQuestionAnswersResourcesDetailSrvOutputItem complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="InQueryQuestionAnswersResourcesDetailSrvOutputItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RESOURCES_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RESOURCES_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RESOURCES_URL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UPLOAD_USERNAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UPLOAD_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UPLOAD_TIME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TASK_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="QUESTION_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ANSWER_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RESOURCES_TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IMAGE" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class InQueryQuestionAnswersResourcesDetailSrvOutputItem {

    protected String resourcesid;
    protected String resourcesname;
    protected String resourcesurl;
    protected String uploadusername;
    protected String uploadname;
    protected String uploadtime;
    protected String taskid;
    protected String questionid;
    protected String answerid;
    protected String resourcestype;
    protected byte[] image;

    /**
     * ��ȡresourcesid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRESOURCESID() {
        return resourcesid;
    }

    /**
     * ����resourcesid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRESOURCESID(String value) {
        this.resourcesid = value;
    }

    /**
     * ��ȡresourcesname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRESOURCESNAME() {
        return resourcesname;
    }

    /**
     * ����resourcesname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRESOURCESNAME(String value) {
        this.resourcesname = value;
    }

    /**
     * ��ȡresourcesurl���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRESOURCESURL() {
        return resourcesurl;
    }

    /**
     * ����resourcesurl���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRESOURCESURL(String value) {
        this.resourcesurl = value;
    }

    /**
     * ��ȡuploadusername���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUPLOADUSERNAME() {
        return uploadusername;
    }

    /**
     * ����uploadusername���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUPLOADUSERNAME(String value) {
        this.uploadusername = value;
    }

    /**
     * ��ȡuploadname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUPLOADNAME() {
        return uploadname;
    }

    /**
     * ����uploadname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUPLOADNAME(String value) {
        this.uploadname = value;
    }

    /**
     * ��ȡuploadtime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUPLOADTIME() {
        return uploadtime;
    }

    /**
     * ����uploadtime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUPLOADTIME(String value) {
        this.uploadtime = value;
    }

    /**
     * ��ȡtaskid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTASKID() {
        return taskid;
    }

    /**
     * ����taskid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTASKID(String value) {
        this.taskid = value;
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
     * ��ȡresourcestype���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRESOURCESTYPE() {
        return resourcestype;
    }

    /**
     * ����resourcestype���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRESOURCESTYPE(String value) {
        this.resourcestype = value;
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

}
