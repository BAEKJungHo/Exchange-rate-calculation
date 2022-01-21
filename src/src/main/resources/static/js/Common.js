function isBlank(target) {
    if(target === null || target === '' || typeof target === 'undefined') {
        return true;
    }
    return false;
}
