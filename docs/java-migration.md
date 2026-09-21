# Migrating from Java SDK 2.x to 3.x

The SDK now uses Jackson for ordinary JSON requests, multipart JSON parts, and
Meter HTTP/2 bodies. Its POM imports Jackson BOM `2.18.10`. An application's BOM
can override these dependencies; check the effective dependency tree and keep
Jackson modules aligned. Jackson 3 is not supported.

Existing Client calls, model field types, and Java enum constants are retained.
Generated AMS string enums write API wire values and accept both wire values and
historical Java constant names on input; unknown input returns null, including
direct calls to `fromValue`. In particular, `TEMPLATE`/`FILE` write
`DISPUTE_EVIDENCE_TEMPLATE`/`DISPUTE_EVIDENCE_FILE`, and
`PAYMENTCODE`/`ORDERCODE`/`ENTRYCODE` write `PaymentCode`/`OrderCode`/`EntryCode`.
Authentication responses read both `isPassed` and historical `passed`, but write
only `isPassed`.

For custom JSON handling, use `com.alipay.global.api.tools.JsonUtil.toJson` and
`JsonUtil.fromJson`. Object field order and complete JSON strings may change;
assert JSON structure instead of string snapshots. The SDK no longer supplies
fastjson transitively, so applications that still use it must manage that
dependency themselves. The SDK's mapper is private and does not reuse an
application's Spring ObjectMapper.

Sign exactly the JSON body you send. For notifications, call
`WebhookTool.checkSignature` with the original HTTP body **before** parsing it
with `JsonUtil.fromJson`; do not reserialize a notification before verification.
Meter HTTP/2 continues to use only `X-Session-Id`, as described in the [Meter guide](meter-event-upload.md).
