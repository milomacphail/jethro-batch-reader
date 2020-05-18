package com.bigDataFileReader.models;

public class TransactionRecord {
    private int step;
    private String type;
    private float amount;
    private String nameOrig;
    private float oldBalanceOrig;
    private float newBalanceOrig;
    private String nameDest;
    private float oldBalanceDest;
    private float newBalanceDest;
    private int isFraud;
    private int isFlaggedFraud;

    public TransactionRecord() {
    }

    public TransactionRecord(int step, String type, float amount, String nameOrig, float oldBalanceOrig, float newBalanceOrig, String nameDest, float oldBalanceDest, float newBalanceDest, int isFraud, int isFlaggedFraud) {
        this.step = step;
        this.type = type;
        this.amount = amount;
        this.nameOrig = nameOrig;
        this.oldBalanceOrig = oldBalanceOrig;
        this.newBalanceOrig = newBalanceOrig;
        this.nameDest = nameDest;
        this.oldBalanceDest = oldBalanceDest;
        this.newBalanceDest = newBalanceDest;
        this.isFraud = isFraud;
        this.isFlaggedFraud = isFlaggedFraud;
    }
    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getNameOrig() {
        return nameOrig;
    }

    public void setNameOrig(String nameOrig) {
        this.nameOrig = nameOrig;
    }

    public float getOldBalanceOrig() {
        return oldBalanceOrig;
    }

    public void setOldBalanceOrig(float oldBalanceOrig) {
        this.oldBalanceOrig = oldBalanceOrig;
    }

    public float getNewBalanceOrig() {
        return newBalanceOrig;
    }

    public void setNewBalanceOrig(float newBalanceOrig) {
        this.newBalanceOrig = newBalanceOrig;
    }

    public String getNameDest() {
        return nameDest;
    }

    public void setNameDest(String nameDest) {
        this.nameDest = nameDest;
    }

    public float getOldBalanceDest() {
        return oldBalanceDest;
    }

    public void setOldBalanceDest(float oldBalanceDest) {
        this.oldBalanceDest = oldBalanceDest;
    }

    public float getNewBalanceDest() {
        return newBalanceDest;
    }

    public void setNewBalanceDest(float newBalanceDest) {
        this.newBalanceDest = newBalanceDest;
    }

    public int getIsFraud() {
        return isFraud;
    }

    public void setIsFraud(int isFraud) {
        this.isFraud = isFraud;
    }

    public int getIsFlaggedFraud() {
        return isFlaggedFraud;
    }

    public void setIsFlaggedFraud(int isFlaggedFraud) {
        this.isFlaggedFraud = isFlaggedFraud;
    }

    @Override
    public String toString() {
        return String.format("Transaction Record[step='%d', type='%s', amount='%f', nameOrig='%s', oldBalanceOrg='%f', newBalanceOrg='%f', nameDest='%s', oldBalanceDest='%f', newBalanceDest='%f', isFraud='%d', isFlaggedFraud='%d']", step, type, amount, nameOrig, oldBalanceOrig, newBalanceOrig, nameDest, oldBalanceDest, newBalanceDest, isFraud, isFlaggedFraud);
    }
}
