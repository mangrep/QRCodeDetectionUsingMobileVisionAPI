package com.qrcode;

import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;
import com.qrcode.ui.camera.GraphicOverlay;


/**
 * Factory for creating a tracker and associated graphic to be associated with a new barcode.  The
 * multi-processor uses this factory to create barcode trackers as needed -- one for each barcode.
 */
class QRCodeTrackerFactory implements MultiProcessor.Factory<Barcode> {
    private GraphicOverlay<QRCodeGraphic> mGraphicOverlay;
    private QRCodeDetector callBackQRCodeDetector;

    QRCodeTrackerFactory(QRCodeDetector qrCodeDetector, GraphicOverlay<QRCodeGraphic> barcodeGraphicOverlay) {
        mGraphicOverlay = barcodeGraphicOverlay;
        callBackQRCodeDetector = qrCodeDetector;
    }

    @Override
    public Tracker<Barcode> create(Barcode barcode) {
        QRCodeGraphic graphic = new QRCodeGraphic(callBackQRCodeDetector, mGraphicOverlay);
        return new QRCodeGraphicTracker(mGraphicOverlay, graphic);
    }

}

