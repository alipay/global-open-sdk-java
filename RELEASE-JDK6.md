# global-open-sdk-java 2.1.15-jdk6

基于官方 **2.1.15**（发布于 2026-03-17，对应提交 84cf3b46）改造的 **JDK 1.6 兼容版本**。

## 与官方 2.1.15 的差异

代码零改动（核心代码），仅以下调整：

| 变更项 | 官方 2.1.15 | 本版本 | 原因 |
|--------|------------|--------|------|
| maven-compiler source/target | 8 | **1.6** | JDK 6 字节码 |
| jackson-databind | 2.12.7.2 | **jackson-annotations 2.12.7** | SDK 仅使用 @JsonValue/@JsonCreator（48 处枚举），无 ObjectMapper 运行时调用；annotations 包为 Java 6 字节码，注解语义与 databind 捆绑版完全一致；JSON 序列化始终由 fastjson 承担，行为零漂移 |
| commons-lang3 | 3.9 | **3.5** | 3.9 为 Java 8 字节码（3.7 起 Java 7）；3.5 全量 260 个 class 均为 Java 6 字节码（已逐一核验）；SDK 仅用 isBlank/isEmpty/DateFormatUtils |
| validation-api | 2.0.1.Final | **删除** | 全代码库零引用（死依赖），且为 Java 8 字节码 |
| fastjson | 1.2.83 | **1.2.83_noneautotype** | 官方认可的反序列化 RCE 止血版（同坐标同包名，编译期移除 autoType，官方 2.2.1+ 同款）；与 1.2.83 行为零漂移（270/273 class 逐字节一致，差异仅为 autoType 挂钩点），major 49 兼容 Java 6。注意：fastjson 1.2.84 为 Java 8 字节码（major 52），JDK 6 场景不可用 |
| example 演示代码 | `new HashMap<>()` | `new HashMap<String, String>()` | 3 处 diamond 运算符不兼容 -source 1.6；example 不进 jar |
| 打包 | 普通 jar | **shade fat jar** | 嵌入全部运行时依赖，客户零依赖引入 |
| GPG / central-publishing 插件 | 有 | 移除 | 本地交付构建，不发布 Maven Central |

保留不动：commons-codec 1.10（major 50）、bcprov-jdk15on 1.61（major 49）、lombok 1.18.30（provided，仅编译期）。

## 构建方法

```bash
# 需要 JDK 8 作为编译机（产出 Java 6 字节码），Maven 3.x
export JAVA_HOME=<jdk8-home>
mvn clean package -Dmaven.javadoc.skip=true -Dmaven.test.skip=true
# 产物: target/global-open-sdk-java-2.1.15-jdk6.jar (fat jar, ~6.5MB)
```

## 验收记录（2026-08-27）

1. **全量字节码审计**：解包 fat jar，4907 个 class（SDK 659 + 嵌入依赖）逐一检查 class 文件 major version，**全部 ≤ 50（Java 6）**，零超标；无 META-INF/versions 多版本目录残留。
2. **嵌入 fastjson 版本身份验证**：`ParserConfig.class` sha 与官方 `fastjson-1.2.83_noneautotype.jar` 完全一致（edc461dd5417）；autoType 入口类 `JSONObject$SecureObjectInputStream` 确认已移除。
3. **运行时冒烟（JDK 8 跑 Java 6 字节码）**：DefaultAlipayClient 构造、pay/subscription 请求 fastjson 序列化+反序列化 round-trip、RSA-SHA256 签名 + Base64、lang3 3.5 StringUtils、jackson-annotations 枚举注解加载——全部通过。
4. **源码级验证（此前完成）**：477 个源文件 `-source 1.6 -target 1.6` 全量编译零错误；常量池扫描 24 项 JDK 7/8 API 特征零引用。

## 已知运行时约束（非 SDK 可控，详见客户交付说明）

- JDK 6 需 **6u111+** 才支持 TLS 1.2，且默认关闭，需 JVM 参数 `-Dhttps.protocols=TLSv1.2`
- JDK 6 JSSE 不发送 SNI 扩展（Java 7+ 特性），若网关强制 SNI 则握手失败且无 SDK 层解法
- fat jar 已嵌入 fastjson/lang3/codec/bcprov/jackson-annotations，客户 classpath 若存在同库不同版本会有类冲突风险

## 分支说明

- 分支：`release/2.1.15-jdk6`（基于 84cf3b46）
- 不合入 master，不发布 Maven Central，仅用于 JDK 1.6 商户交付
- 客户交付说明：见工作区 `jdk6-release/CUSTOMER-DELIVERY-NOTES.md`
