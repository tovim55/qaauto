exports.getInactivityTimeout = function(currentfilename) {
    //The amount of time (in seconds) the application will wait for user input
    conf = JSON.parse(ARGV.cp_confOwner);
    ARGV.timeout = conf.cp_inactivityTimeoutPeriod;
    ARGV.currentfilename = currentfilename;
    //for locaization specify suffix string from catalog file i.e *.ctlg file.
    ARGV.lang = "en"
    lang.setLanguage(ARGV.lang);
}

