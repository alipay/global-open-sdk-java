package integration;

import static org.junit.Assert.assertEquals;

import com.alipay.global.api.tools.AmountUtil;
import org.junit.Test;

public class AmountUtilPublicApiTest {
  @Test
  public void publicApiIsCallableOutsideItsPackage() {
    String value = AmountUtil.toAmount("10.25", "USD");
    assertEquals("1025", value);
    assertEquals("10.25", AmountUtil.fromAmount(value, "USD"));
  }
}
