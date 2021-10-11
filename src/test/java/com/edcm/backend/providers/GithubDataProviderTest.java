package com.edcm.backend.providers;

import com.edcm.backend.BaseTest;
import com.edcm.backend.core.tools.GithubDataProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class GithubDataProviderTest extends BaseTest {
    @Autowired
    private GithubDataProvider dataProvider;

    @Test
    public void sendRequest(){
        dataProvider.getCommodities();
    }
}
