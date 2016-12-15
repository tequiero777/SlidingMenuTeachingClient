
package com.tianjian.slidingmenuteachingclient.bean.InQueryTasksSrv;

import java.math.BigDecimal;

import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>MsgHeader complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="MsgHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SOURCESYSTEMID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SOURCESYSTEMNAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="USERID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="USERNAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="USERPWD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SUBMITDATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="PAGE_SIZE" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="CURRENT_PAGE" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="TOTAL_RECORD" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="PROVINCE_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ENVIRONMENT_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class MsgHeader {

    protected String sourcesystemid;
    protected String sourcesystemname;
    protected String userid;
    protected String username;
    protected String userpwd;
    protected XMLGregorianCalendar submitdate;
    protected BigDecimal pagesize;
    protected BigDecimal currentpage;
    protected BigDecimal totalrecord;
    protected String provincecode;
    protected String environmentname;

    /**
     * ��ȡsourcesystemid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSOURCESYSTEMID() {
        return sourcesystemid;
    }

    /**
     * ����sourcesystemid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSOURCESYSTEMID(String value) {
        this.sourcesystemid = value;
    }

    /**
     * ��ȡsourcesystemname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSOURCESYSTEMNAME() {
        return sourcesystemname;
    }

    /**
     * ����sourcesystemname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSOURCESYSTEMNAME(String value) {
        this.sourcesystemname = value;
    }

    /**
     * ��ȡuserid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSERID() {
        return userid;
    }

    /**
     * ����userid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSERID(String value) {
        this.userid = value;
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
     * ��ȡuserpwd���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSERPWD() {
        return userpwd;
    }

    /**
     * ����userpwd���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSERPWD(String value) {
        this.userpwd = value;
    }

    /**
     * ��ȡsubmitdate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSUBMITDATE() {
        return submitdate;
    }

    /**
     * ����submitdate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSUBMITDATE(XMLGregorianCalendar value) {
        this.submitdate = value;
    }

    /**
     * ��ȡpagesize���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPAGESIZE() {
        return pagesize;
    }

    /**
     * ����pagesize���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPAGESIZE(BigDecimal value) {
        this.pagesize = value;
    }

    /**
     * ��ȡcurrentpage���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCURRENTPAGE() {
        return currentpage;
    }

    /**
     * ����currentpage���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCURRENTPAGE(BigDecimal value) {
        this.currentpage = value;
    }

    /**
     * ��ȡtotalrecord���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTOTALRECORD() {
        return totalrecord;
    }

    /**
     * ����totalrecord���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTOTALRECORD(BigDecimal value) {
        this.totalrecord = value;
    }

    /**
     * ��ȡprovincecode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPROVINCECODE() {
        return provincecode;
    }

    /**
     * ����provincecode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPROVINCECODE(String value) {
        this.provincecode = value;
    }

    /**
     * ��ȡenvironmentname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getENVIRONMENTNAME() {
        return environmentname;
    }

    /**
     * ����environmentname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setENVIRONMENTNAME(String value) {
        this.environmentname = value;
    }

}
