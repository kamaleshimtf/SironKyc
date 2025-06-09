package com.imtf.siron.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "WSSTATUS", schema = "CLNT0001")
public class WebServiceStatusEntity {

    @Id
    @Column(name = "WS_REQUEST_UUID", columnDefinition = "NVARCHAR (36)")
    public String wsRequestUuid;

    @Column(name = "WS_METHOD_NAME", columnDefinition = "NVARCHAR (100)")
    public String wsMethodName;

    @Column(name = "REQUEST_TIME_START", columnDefinition = "NCHAR (17)")
    public String requestTimeStart;

    @Column(name = "REQUEST_TIME_END", columnDefinition = "NCHAR (17)")
    public String requestTimeEnd;

    @Column(name = "RETURNCODE", columnDefinition = "INT")
    public Integer returnCode;

    @Column(name = "SCORING_TIME_START", columnDefinition = "NCHAR (17)")
    public String scoringTimeStart;

    @Column(name = "SCORING_TIME_END", columnDefinition = "NCHAR (17)")
    public String scoringTimeEnd;

    @Column(name = "RELATING_CUSTOMERS", columnDefinition = "NVARCHAR (2000)")
    public String relatingCustomers;

    @Column(name = "ASYNC_REQUEST_ID", columnDefinition = "NVARCHAR (256)")
    public String asyncRequestId;

    @Column(name = "TBQ_FORMID", columnDefinition = "INT")
    public Integer tbqFormId;

    @Column(name = "SERVERNAME", columnDefinition = "NVARCHAR (255)")
    public String serverName;


    public String getWsRequestUuid() {
        return wsRequestUuid;
    }

    public void setWsRequestUuid(String wsRequestUuid) {
        this.wsRequestUuid = wsRequestUuid;
    }

    public String getWsMethodName() {
        return wsMethodName;
    }

    public void setWsMethodName(String wsMethodName) {
        this.wsMethodName = wsMethodName;
    }

    public String getRequestTimeStart() {
        return requestTimeStart;
    }

    public void setRequestTimeStart(String requestTimeStart) {
        this.requestTimeStart = requestTimeStart;
    }

    public String getRequestTimeEnd() {
        return requestTimeEnd;
    }

    public void setRequestTimeEnd(String requestTimeEnd) {
        this.requestTimeEnd = requestTimeEnd;
    }

    public Integer getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public String getScoringTimeStart() {
        return scoringTimeStart;
    }

    public void setScoringTimeStart(String scoringTimeStart) {
        this.scoringTimeStart = scoringTimeStart;
    }

    public String getScoringTimeEnd() {
        return scoringTimeEnd;
    }

    public void setScoringTimeEnd(String scoringTimeEnd) {
        this.scoringTimeEnd = scoringTimeEnd;
    }

    public String getRelatingCustomers() {
        return relatingCustomers;
    }

    public void setRelatingCustomers(String relatingCustomers) {
        this.relatingCustomers = relatingCustomers;
    }

    public String getAsyncRequestId() {
        return asyncRequestId;
    }

    public void setAsyncRequestId(String asyncRequestId) {
        this.asyncRequestId = asyncRequestId;
    }

    public Integer getTbqFormId() {
        return tbqFormId;
    }

    public void setTbqFormId(Integer tbqFormId) {
        this.tbqFormId = tbqFormId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public WebServiceStatusEntity(String wsRequestUuid,
                                  String wsMethodName,
                                  String requestTimeStart,
                                  String requestTimeEnd,
                                  Integer returnCode,
                                  String scoringTimeStart,
                                  String scoringTimeEnd,
                                  String relatingCustomers,
                                  String asyncRequestId,
                                  Integer tbqFormId,
                                  String serverName) {
        this.wsRequestUuid = wsRequestUuid;
        this.wsMethodName = wsMethodName;
        this.requestTimeStart = requestTimeStart;
        this.requestTimeEnd = requestTimeEnd;
        this.returnCode = returnCode;
        this.scoringTimeStart = scoringTimeStart;
        this.scoringTimeEnd = scoringTimeEnd;
        this.relatingCustomers = relatingCustomers;
        this.asyncRequestId = asyncRequestId;
        this.tbqFormId = tbqFormId;
        this.serverName = serverName;
    }

    public WebServiceStatusEntity() {

    }

}
