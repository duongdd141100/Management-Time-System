function RenderPager(id, pageIndex, totalPage, gap, url) {
    var divRender = document.getElementById(id);
    if (pageIndex > gap + 1) {
        divRender.innerHTML += "<a href='"+url+"?page=1'>First</a>"
    }
    for (var i = pageIndex - gap; i < pageIndex + gap + 1; i++) {
        if (i > 0 && i <= totalPage) {
            if (i == pageIndex) {
                divRender.innerHTML += "<span>" + pageIndex + "</span>"
            } else {
                divRender.innerHTML += "<a href='"+url+"?page=" + i + "'>" + i + "</a>"
            }
        }
    }
    if (pageIndex + gap < totalPage) {
        divRender.innerHTML += "<a href='"+url+"?page=" + totalPage + "'>Last</a>"
    }
}
function RenderPagerWithUser(id, pageIndex, totalPage, gap, url, username) {
    var divRender = document.getElementById(id);
    if (pageIndex > gap + 1) {
        divRender.innerHTML += "<a href='"+url+"?username="+username+"&page=1'>First</a>"
    }
    for (var i = pageIndex - gap; i < pageIndex + gap + 1; i++) {
        if (i > 0 && i <= totalPage) {
            if (i == pageIndex) {
                divRender.innerHTML += "<span>" + pageIndex + "</span>"
            } else {
                divRender.innerHTML += "<a href='"+url+"?username="+username+"&page=" + i + "'>" + i + "</a>"
            }
        }
    }
    if (pageIndex + gap < totalPage) {
        divRender.innerHTML += "<a href='"+url+"?username="+username+"&page=" + totalPage + "'>Last</a>"
    }
}