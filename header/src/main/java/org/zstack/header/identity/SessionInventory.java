package org.zstack.header.identity;

import org.zstack.header.configuration.PythonClassInventory;
import org.zstack.header.rest.APINoSee;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@PythonClassInventory
public class SessionInventory implements Serializable {
    private String uuid;
    @APINoSee
    private String accountUuid;
    @APINoSee
    private String userUuid;
    @APINoSee
    private Timestamp expiredDate;
    @APINoSee
    private Timestamp createDate;
    
    public static SessionInventory valueOf(SessionVO vo) {
        SessionInventory inv = new SessionInventory();
        inv.setAccountUuid(vo.getAccountUuid());
        inv.setCreateDate(vo.getCreateDate());
        inv.setExpiredDate(vo.getExpiredDate());
        inv.setUserUuid(vo.getUserUuid());
        inv.setUuid(vo.getUuid());
        return inv;
    }
    
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getAccountUuid() {
        return accountUuid;
    }
    public void setAccountUuid(String accountUuid) {
        this.accountUuid = accountUuid;
    }
    public String getUserUuid() {
        return userUuid;
    }
    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public Timestamp getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Timestamp expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
