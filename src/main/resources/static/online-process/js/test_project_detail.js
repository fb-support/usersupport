$(function() {

    $(".project_detail_child_menu").click(function() {
        $(".child_menu_selected").attr("class", "project_detail_child_menu");
        $(this).attr("class", "child_menu_selected");
        //跳转页面
        //隐藏所有页面后，打开指定页面，
        $(".project_content_1").hide();
        switch ($(this).attr("value")) {
            case "1":
                $(".page_project_record").show();
                // 重加载项目操作记录页面
                search();
                break;
            case "2":
                $(".page_test_form").show();
                break;
            case "3":
                $(".page_launch_form").show();
                break;
            default:
                break;
        }
    });
});