

/**
 * 往左边滑动选项卡。
 */
function left_slideCard() {
    //判断当前选项卡个数，超过7个方可触发该方法
    var card = $("#ul-block_for_page li");
    var li_length = $("#ul-block_for_page li").length;
    if (li_length > 7) {
        for (var i = li_length - 1; i >= 0; i--) {
            if (card[i].style.display != "none") {
                $("#ul-block_for_page li")[i-7].style.display = "inline";
                $("#ul-block_for_page li")[i].style.display = "none";
                break;
            }
        }
    }
}

/**
 * 往右边滑动选项卡。
 */
function right_slideCard() {
    //判断当前选项卡个数，超过7个方可触发该方法
    var card = $("#ul-block_for_page li");
    var li_length = $("#ul-block_for_page li").length;
    if (li_length > 7) {
        for (var i = li_length - 1; i >= 0; i--) {
            if (card[i].style.display != "none" && i < (li_length - 1)) {
                $("#ul-block_for_page li")[i+1].style.display = "inline";
                $("#ul-block_for_page li")[i-6].style.display = "none";
                break;
            }
        }
    }
}