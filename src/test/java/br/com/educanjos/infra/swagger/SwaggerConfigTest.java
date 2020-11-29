package br.com.educanjos.infra.swagger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class SwaggerConfigTest {

    private SwaggerConfig swaggerConfig = new SwaggerConfig();

    @Test
    public void deveTestarSwagger(){
        assertNotNull(swaggerConfig);
        assertNotNull(swaggerConfig.api());
    }
}
