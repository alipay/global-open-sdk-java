package com.alipay.global.api.model.ams;

import lombok.Data;

@Data
public class DisputeDefenseFile {
  /** Name of the generated defense material. */
  private String fileName;

  /** Material format: PDF or WORD. */
  private String fileFormat;

  /** MD5 of the material body as 32 lowercase hexadecimal characters. */
  private String fileMd5;
}
