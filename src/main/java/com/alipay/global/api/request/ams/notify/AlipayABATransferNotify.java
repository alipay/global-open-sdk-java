package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.Result;
import com.alipay.global.api.model.ams.TransferFromDetail;
import com.alipay.global.api.model.ams.TransferToDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayABATransferNotify extends AlipayNotify {

  /**
   * The unique ID assigned by the merchant to identify a transfer request.
   * Maximum length: 64 characters
   */
  private String transferRequestId;

  /**
   * The unique ID assigned by Antom to identify a transfer.
   * Maximum length: 64 characters
   */
  private String transferId;

  /** The result of the transfer request. */
  private Result transferResult;

  /** The finishing time of a transfer. */
  private String transferFinishTime;

  /** The transfer from detail information. */
  private TransferFromDetail transferFromDetail;

  /** The transfer to detail information. */
  private TransferToDetail transferToDetail;
}