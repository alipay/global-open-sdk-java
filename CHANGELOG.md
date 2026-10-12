# Changelog

## 3.1.1 (planned; unreleased)

- Tighten gateway response signature verification: an unsigned response with resultStatus S is now rejected, and a response that carries only one of the signature and response-time headers is now rejected. Merchants using mock gateways or proxies that strip custom response headers may observe new verification errors.
- Add post-authorization risk review fields authReviewStatus, authReviewSource, and popRiskDecisionResultInfo to the payment inquiry response and the payment result notification.
- Add splitDetails to the refund request and refund inquiry response for Shopify ISV split reversal, with the new SplitDetail model.
- Align Credit Grant and Meter models with the official Billing contract: availableAmount now uses the Amount model, the AvailableAmount model is removed, and enum values and field descriptions are updated.

## 3.1.0 - 2026-09-18

- Add an independent API Key client using a gateway and API Key, with existing business request/response models.
- Keep existing RSA APIs compatible; retain Session HTTP/2 and notification verification behavior.
- Add a runnable Restricted TEST createPaymentSession example and configuration documentation.
- Preserve safe exception causes while redacting API Key material.
