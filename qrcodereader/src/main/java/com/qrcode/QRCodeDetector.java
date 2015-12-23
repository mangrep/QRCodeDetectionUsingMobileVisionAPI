package com.qrcode;

import com.google.android.gms.vision.barcode.Barcode;

/**
 * Created by turing on 22/12/15.
 */
public interface QRCodeDetector {
    void barCodeDetected(Barcode barcode);
}
