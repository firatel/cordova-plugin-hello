# TPS320 Cordova Plugin - USB THERMAL PRINTER

    /*
        Alignment
        public static final int ALGIN_LEFT = 0;
        public static final int ALGIN_MIDDLE = 1;
        public static final int ALGIN_RIGHT = 2;
        hello.alignment(12, success, failure);
    */

    var success = function(message) {
        alert(message);
    }

    var failure = function() {
        alert("Error calling Hello Plugin");
    }


    hello.startPrinter(null, success, failure);
    hello.resetPrinter(null, success, failure);
    hello.alignment(1, success, failure);
    hello.leftIndent(0, success, failure);
    hello.lineSpace(5, success, failure);
    hello.fontSize(60, success, failure);
    hello.addString("Firatel Print", success, failure);
    hello.printString(null, success, failure);
    hello.walkPaper(10, success, failure);
