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
    public static final Target create = constructTarget("create");
    public static final Target select2User = constructTarget("2_user_be_selected");
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
    public static final Target contactNotSaved = constructTarget("contact_not_save");
    public static final Target contactEntry = constructTarget("contact_entry");
    public static final Target contactGroupEntry = constructTarget("contact_group_entry");
    public static final Target groupMemberSummary = constructTarget("group_member_summary");
    private static final String RESOURCE_PATH_FORMAT = "/target/%s.png";

    private static Target constructTarget(final String resourceName) {
        URL imageURL = App.class.getResource(String.format(RESOURCE_PATH_FORMAT, resourceName));
        return new ImageTarget(imageURL);
    }

}
