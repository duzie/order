function generateBarcode() {
    const count = document.getElementById('count').value;
    const format = document.getElementById('format').value;
    const barcodeContainer = document.getElementById('barcodeContainer');
    barcodeContainer.innerHTML = '';

	//JsBarcode("#barcode1", "sdfsdfsdfs!")

    for (let i = 1; i < count; i++) {
        JsBarcode("#barcode"+i, `${Math.random().toString(36).substr(2, 9)}`, {
            width: 2,
            height: 100,
        });
    }
}
