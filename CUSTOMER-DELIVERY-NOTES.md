# Antom Java SDK 2.1.15-jdk6 交付说明

## 这是什么

基于官方 global-open-sdk-java **2.1.15** 改造的 **JDK 1.6 专用版本**。功能与官方 2.1.15 完全一致（支付/查询/退款/订阅/绑卡/风控/ABA 等全部接口），仅调整了编译目标和依赖版本以兼容 Java 6。所有 class 均已逐一验证为 Java 6 字节码。

**单 jar 即用**：所有运行时依赖（fastjson 1.2.83_noneautotype、commons-lang3、commons-codec、bouncycastle、jackson-annotations）已全部打进 jar，直接放入 classpath 即可，无需再引入任何其他依赖。

```
<dependency>
    <groupId>com.alipay.global.sdk</groupId>
    <artifactId>global-open-sdk-java</artifactId>
    <version>2.1.15-jdk6</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/lib/global-open-sdk-java-2.1.15-jdk6.jar</systemPath>
</dependency>
```
（无 Maven 的项目直接将 jar 加入 classpath 即可。）

## 使用前必须确认的两件事

**1. 您的 JDK 6 小版本号**（`java -version` 输出）：

- 需 **1.6.0_111 及以上**。更低版本不支持 TLS 1.2，无法连接 Antom 网关（无解，需先升级 JDK 补丁版本）
- 即使是 6u111+，也需在 JVM 启动参数中显式开启：
  ```
  -Dhttps.protocols=TLSv1.2
  ```

**2. jar 内已嵌入依赖，请勿重复引入**：如果您的工程 classpath 中已存在 fastjson、commons-lang3 等同名库的不同版本，会产生类冲突（`NoSuchMethodError` / `NoClassDefFoundError`）。如有此情况请联系我们协商改为瘦 jar 交付。

## 落地前必须做的验证（按顺序）

1. **网络连通验证（最重要，先于一切开发）**：在您的生产/测试 JVM（JDK 1.6 环境 + 上述 JVM 参数）中运行任一 HTTPS 请求（如用 SDK 的 inquiryPayment 查询一个不存在的订单号），确认：
   - 不报 `SSLHandshakeException` / `Unsupported protocol`（说明 TLS 版本 OK）
   - 能收到 Antom 网关返回的 JSON 报文（哪怕业务错误码如 ORDER_NOT_EXIST，也说明网络全通）
2. **验签验证**：SDK 默认对每个响应做签名校验（用 Antom 公钥），如果第 1 步能正常返回业务报文且无签名异常，即证明密钥配置正确。
3. **冒烟用例**：跑一笔沙箱支付（pay → 拿到跳转链接）+ 一次查询（inquiryPayment → SUCCESS），确认签名、序列化、网络三条链路均正常。

## 与官方版本的行为差异

**无**。JSON 序列化使用 fastjson 1.2.83_noneautotype（官方认可的 fastjson 1.2.83 反序列化漏洞修复版，官方 SDK 2.2.1 起同款，与 1.2.83 行为逐字节一致）；被替换的 jackson-databind 在官方版本中也仅作为注解使用（SDK 从未调用其运行时 API），替换为同版本注解包后行为完全一致。

> 安全扫描提示：部分 SCA 工具可能因版本号中含 "1.2.83" 对 fastjson 告警。此为已知误报——noneautotype 版本已在编译期移除 autoType 机制（漏洞根因），如需佐证可联系我们提供该版本与官方 1.2.83 的字节码差异分析。

## 已知限制

- JDK 6 不支持 SNI（服务器名称指示）。如 Antom 网关未来启用强制 SNI 校验，JDK 6 将无法连接——届时需升级 JDK（Java 7+），任何 SDK 版本都无法规避
- 本版本不经 Maven Central 分发，如需升级请直接联系我们获取新 jar
