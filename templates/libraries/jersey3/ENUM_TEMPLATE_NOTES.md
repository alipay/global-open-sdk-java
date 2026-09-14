# Enum Template Ownership

`modelEnum.mustache` and `modelInnerEnum.mustache` originate from the Java templates
in OpenAPI Generator 6.0.1, matching the existing Automation toolchain.

The local override preserves enum names, values, annotations and non-string enum
behavior. String enums accept both API wire values and Java constant names on
input, return null for unknown input, and serialize only their `@JsonValue` value.
Maintain both standalone and inner-enum templates when changing this contract.

Do not edit generated enums directly. Regenerate using the existing Automation
tasks and the same formatting step used by the GitHub workflow. Reproduction and
compatibility checks are maintained in integration-loop.
