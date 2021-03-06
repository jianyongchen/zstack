package org.zstack.test.core.config;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.zstack.core.componentloader.ComponentLoader;
import org.zstack.core.config.*;
import org.zstack.test.Api;
import org.zstack.test.ApiSenderException;
import org.zstack.test.BeanConstructor;
import org.zstack.test.DBUtil;

public class TestGlobalConfigValidateExtension2 {
	GlobalConfigFacade gcf;
	ComponentLoader loader;
	Api api;
    boolean success = true;
	
	@Before
	public void setUp() throws Exception {
	    DBUtil.reDeployDB();
        BeanConstructor con = new BeanConstructor();
        loader = con.addXml("PortalForUnitTest.xml").addXml("AccountManager.xml").build();
		gcf = loader.getComponent(GlobalConfigFacade.class);
		api = new Api();
		api.startServer();

        GlobalConfigForTest.TEST4.installValidateExtension(new GlobalConfigValidatorExtensionPoint() {
            @Override
            public void validateGlobalConfig(String category, String name, String oldValue, String value) throws GlobalConfigException {
                if (!success) {
                    throw new GlobalConfigException("on purpose");
                }
            }
        });
	}

    @Test
    public void test() throws InterruptedException, ApiSenderException {
        GlobalConfigInventory target = null;
        for (GlobalConfigInventory inv : api.listGlobalConfig(null)) {
            if ("Test4".equals(inv.getName())) {
                target = inv;
                break;
            }
        }
        success = true;
        target.setValue("hello");
        api.updateGlobalConfig(target);
        Assert.assertEquals("hello", GlobalConfigForTest.TEST4.value());
    }
}
