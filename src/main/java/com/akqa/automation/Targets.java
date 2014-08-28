package com.akqa.automation;

import org.sikuli.api.ImageTarget;
import org.sikuli.api.Target;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 8/11/14
 * Time: 10:45 AM
 */
public final class Targets {
    public static final Target home = constructTarget("home");
    public static final Target create = constructTarget("create");
    public static final Target select2User = constructTarget("2_user_be_selected");
    public static final Target firstSelect = constructTarget("first_select");
    public static final Target confirmGroupCreation = constructTarget("confirm_group_creation");
    public static final Target createGroup = constructTarget("create_group");
    public static final Target groupEntry = constructTarget("group_entry");
    public static final Target qrCodeCorner = constructTarget("qr_code_corner");
    public static final Target groupQrCodeEntry = constructTarget("group_qr_code_entry");
    public static final Target user1Snapshot = constructTarget("user1");
    public static final Target user2Snapshot = constructTarget("user2");
    public static final Target wechatNav = constructTarget("wechat_nav");
    public static final Target chatTab = constructTarget("chat_tab");
    public static final Target back = constructTarget("back");
    public static final Target contactNotSaved = constructTarget("contact_not_save1");
    public static final Target contactEntry = constructTarget("contact_entry");
    public static final Target contactGroupEntry = constructTarget("contact_group_entry");
    public static final Target groupMemberSummary = constructTarget("group_member_summary");
    public static final Target notRefreshFlag = constructTarget("not_refresh_flag");
    public static final Target robot = constructTarget("robot3");
    public static final Target robot2 = constructTarget("robot2");
    public static final Target createGroupFlag = constructTarget("create_group_flag1");
    public static final Target notResponseError = constructTarget("not_response_error");
    public static final Target chooseAGroup = constructTarget("choose_a_group");
    public static final Target startAGroupConvesation = constructTarget("start_a_group_convesation");
    public static final Target groupContactList = constructTarget("group_contact_list");
    public static final Target wait = constructTarget("wait");
    public static final Target enterGroup = constructTarget("enter_group");
    public static final Target groupDetail = constructTarget("group_detail");
    public static final Target chatInformation = constructTarget("chat_information");
    public static final Target mainScreen = constructTarget("main_screen");
    private static final String RESOURCE_PATH_FORMAT = "/target/%s.png";
    private static final String GENY_MOTION_RESOURCE_PATH_FORMAT = "/target/genymotion/%s.png";

    private static Target constructTarget(final String resourceName) {
        URL imageURL = App.class.getResource(String.format(RESOURCE_PATH_FORMAT, resourceName));
        ImageTarget imageTarget = new ImageTarget(imageURL);
        imageTarget.setMinScore(0.88);
        return imageTarget;
    }

    public static final Target confirm_group_contact_list = constructGenyTarget("confirm_group_contact_list");
    public static final Target confirm_group_contact_snapshot = constructGenyTarget("confirm_group_contact_snapshot");
    public static final Target confirm_group_detail = constructGenyTarget("confirm_group_detail");
    public static final Target confirm_qr_code_detail = constructGenyTarget("confirm_qr_code_detail");
    public static final Target enter_group_button = constructGenyTarget("enter_group_button");
    public static final Target group_contact_entry = constructGenyTarget("group_contact_entry");
    public static final Target group_detail_entry_button = constructGenyTarget("group_detail_entry_button");
    public static final Target main_nav = constructGenyTarget("main_nav");
    public static final Target qr_code_bar = constructGenyTarget("qr_code_bar");
    public static final Target qr_code_corner = constructGenyTarget("qr_code_corner");
    public static final Target save_to_contact_bar = constructGenyTarget("save_to_contact_bar");
    public static final Target wechat_nav = constructGenyTarget("wechat_nav");

    private static Target constructGenyTarget(final String resourceName) {
        URL imageURL = App.class.getResource(String.format(GENY_MOTION_RESOURCE_PATH_FORMAT, resourceName));
        ImageTarget imageTarget = new ImageTarget(imageURL);
        imageTarget.setMinScore(0.88);
        return imageTarget;
    }



}
