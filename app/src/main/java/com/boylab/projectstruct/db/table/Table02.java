package com.boylab.projectstruct.db.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Table02  extends BaseTable{

    @Id(autoincrement = true)
    private Long id;
    private String A;
    private int i;
    private long l;
    private float f;
    private double d;
    private byte b;
    @Generated(hash = 1590806350)
    public Table02(Long id, String A, int i, long l, float f, double d, byte b) {
        this.id = id;
        this.A = A;
        this.i = i;
        this.l = l;
        this.f = f;
        this.d = d;
        this.b = b;
    }
    @Generated(hash = 1736879593)
    public Table02() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getA() {
        return this.A;
    }
    public void setA(String A) {
        this.A = A;
    }
    public int getI() {
        return this.i;
    }
    public void setI(int i) {
        this.i = i;
    }
    public long getL() {
        return this.l;
    }
    public void setL(long l) {
        this.l = l;
    }
    public float getF() {
        return this.f;
    }
    public void setF(float f) {
        this.f = f;
    }
    public double getD() {
        return this.d;
    }
    public void setD(double d) {
        this.d = d;
    }
    public byte getB() {
        return this.b;
    }
    public void setB(byte b) {
        this.b = b;
    }

}
