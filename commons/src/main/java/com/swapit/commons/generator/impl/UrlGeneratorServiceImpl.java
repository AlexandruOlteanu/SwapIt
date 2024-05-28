package com.swapit.commons.generator.impl;

import com.swapit.commons.generator.UrlGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlGeneratorServiceImpl implements UrlGeneratorService {

    public enum UrlIdentifier {
        USER_LOGIN, USER_REGISTER, CREATE_PRODUCT, SEND_PRIVATE_MESSAGE, GET_USER_DETAILS,
        GET_CONVERSATIONS_PREVIEW, SPECIFIC_USERS_DETAILS, GET_CONVERSATION, UPDATE_BASIC_USER_DETAILS,
        UPDATE_PROTECTED_USER_DETAILS, ADD_NEW_PRODUCT_CATEGORY, GET_PRODUCT_CATEGORY_ID, ADD_PRODUCT_IN_SEARCH_DICTIONARY,
        SEARCH_PRODUCTS, GET_PRODUCT_BY_ID, GET_CATEGORY_TREE, SEARCH_PRODUCTS_BY_CATEGORY, USER_OAUTH2_LOGIN,
        UPDATE_PRODUCT, UPDATE_PRODUCT_IN_SEARCH_DICTIONARY, CHANGE_PRODUCT_LIKE_STATUS, GET_PRODUCT_LIKE_STATUS,
        GET_PRODUCTS_BY_USER, GET_LIKED_PRODUCTS_BY_USER, GET_RECOMMENDED_PRODUCTS, MANUAL_SECURITY_CODES_EXPIRE,
        DELETE_PRODUCT, DELETE_PRODUCT_FROM_SEARCH_DICTIONARY, BAN_USER, REMOVE_USER_BAN, MANUAL_REMOVE_USERS_BAN,
        GET_USER_DETAILS_BY_USERNAME, FORGOTTEN_PASSWORD_RESET, PASSWORD_RESET, USERNAME_RESET, EMAIL_RESET, DELETE_PRODUCT_ADMIN,
        GET_USER_ACCOUNT_STATUS, POST_USER_ACTION, GET_USER_ACTIONS
    }

    // USER URI
    @Value("${user.login.route}")
    private String userLoginUri;
    @Value("${user.oauth2login.route}")
    private String userOauth2LoginUri;
    @Value("${user.register.route}")
    private String userRegisterUri;
    @Value("${user.getUserDetails.route}")
    private String getUserDetailsUri;
    @Value("${user.getUserDetailsByUsername.route}")
    private String getUserDetailsByUsernameUri;
    @Value("${user.banUser.route}")
    private String banUserUri;
    @Value("${user.removeUserBan.route}")
    private String removeUserBanUri;
    @Value("${user.updateBasicUserDetails.route}")
    private String updateBasicUserDetailsUri;
    @Value("${user.updateProtectedUserDetails.route}")
    private String updateProtectedUserDetailsUri;
    @Value("${user.getSpecificUsersDetails.route}")
    private String getSpecificUsersDetailsUri;
    @Value("${user.forgottenPasswordReset.route}")
    private String forgottenPasswordResetUri;
    @Value("${user.postUserAction.route}")
    private String postUserActionUri;
    @Value("${user.getUserActions.route}")
    private String getUserActionsUri;
    @Value("${user.passwordReset.route}")
    private String passwordResetUri;
    @Value("${user.usernameReset.route}")
    private String usernameResetUri;
    @Value("${user.emailReset.route}")
    private String emailResetUri;
    @Value("${user.manualSecurityCodesExpire.route}")
    private String manualSecurityCodesExpireUri;
    @Value("${user.manualRemoveUsersBan.route}")
    private String manualRemoveUsersBanUri;
    @Value("${user.getUserAccountStatus.route}")
    private String getUserAccountStatusUri;

    // PRODUCT URI
    @Value("${product.createProduct.route}")
    private String createProductUri;
    @Value("${product.updateProduct.route}")
    private String updateProductUri;
    @Value("${product.deleteProduct.route}")
    private String deleteProductUri;
    @Value("${product.deleteProductAdmin.route}")
    private String deleteProductAdminUri;
    @Value("${product.getProductById.route}")
    private String getProductByIdUri;
    @Value("${product.getProductsByUser.route}")
    private String getProductsByUserUri;
    @Value("${product.changeProductLikeStatus.route}")
    private String changeProductLikeStatusUri;
    @Value("${product.getProductLikeStatus.route}")
    private String getProductLikeStatusUri;
    @Value("${product.getLikedProductsByUser.route}")
    private String getLikedProductsByUserUri;
    @Value("${product.getRecommendedProducts.route}")
    private String getRecommendedProductsUri;

    // CHAT URI
    @Value("${chat.sendPrivateMessage.route}")
    private String sendPrivateMessageUri;
    @Value("${chat.getConversationsPreview.route}")
    private String getConversationsPreviewUri;
    @Value("${chat.getConversation.route}")
    private String getConversationUri;

    // SEARCH ENGINE URI
    @Value("${searchEngine.addNewProductCategory.route}")
    private String addNewProductCategoryUri;
    @Value("${searchEngine.getProductCategoryId.route}")
    private String getProductCategoryIdUri;
    @Value("${searchEngine.addProductInSearchDictionary.route}")
    private String addProductInSearchDictionaryUri;
    @Value("${searchEngine.updateProductInSearchDictionary.route}")
    private String updateProductInSearchDictionaryUri;
    @Value("${searchEngine.deleteProductFromSearchDictionary.route}")
    private String deleteProductFromSearchDictionaryUri;
    @Value("${searchEngine.searchProducts.route}")
    private String searchProductsUri;
    @Value("${searchEngine.getCategoryTree.route}")
    private String getCategoryTreeUri;
    @Value("${searchEngine.searchProductsByCategory.route}")
    private String searchProductsByCategoryUri;

    @Override
    public String getServiceURL(UrlIdentifier api) {
        return switch (api) {
            case USER_LOGIN -> userLoginUri;
            case USER_REGISTER -> userRegisterUri;
            case CREATE_PRODUCT -> createProductUri;
            case SEND_PRIVATE_MESSAGE -> sendPrivateMessageUri;
            case GET_USER_DETAILS -> getUserDetailsUri;
            case GET_CONVERSATIONS_PREVIEW -> getConversationsPreviewUri;
            case SPECIFIC_USERS_DETAILS -> getSpecificUsersDetailsUri;
            case GET_CONVERSATION -> getConversationUri;
            case UPDATE_BASIC_USER_DETAILS -> updateBasicUserDetailsUri;
            case UPDATE_PROTECTED_USER_DETAILS -> updateProtectedUserDetailsUri;
            case ADD_NEW_PRODUCT_CATEGORY -> addNewProductCategoryUri;
            case GET_PRODUCT_CATEGORY_ID -> getProductCategoryIdUri;
            case ADD_PRODUCT_IN_SEARCH_DICTIONARY -> addProductInSearchDictionaryUri;
            case SEARCH_PRODUCTS -> searchProductsUri;
            case GET_PRODUCT_BY_ID -> getProductByIdUri;
            case GET_CATEGORY_TREE -> getCategoryTreeUri;
            case SEARCH_PRODUCTS_BY_CATEGORY -> searchProductsByCategoryUri;
            case USER_OAUTH2_LOGIN -> userOauth2LoginUri;
            case UPDATE_PRODUCT -> updateProductUri;
            case UPDATE_PRODUCT_IN_SEARCH_DICTIONARY -> updateProductInSearchDictionaryUri;
            case CHANGE_PRODUCT_LIKE_STATUS -> changeProductLikeStatusUri;
            case GET_PRODUCT_LIKE_STATUS -> getProductLikeStatusUri;
            case GET_PRODUCTS_BY_USER -> getProductsByUserUri;
            case GET_LIKED_PRODUCTS_BY_USER -> getLikedProductsByUserUri;
            case GET_RECOMMENDED_PRODUCTS -> getRecommendedProductsUri;
            case MANUAL_SECURITY_CODES_EXPIRE -> manualSecurityCodesExpireUri;
            case DELETE_PRODUCT -> deleteProductUri;
            case DELETE_PRODUCT_FROM_SEARCH_DICTIONARY -> deleteProductFromSearchDictionaryUri;
            case BAN_USER -> banUserUri;
            case REMOVE_USER_BAN -> removeUserBanUri;
            case MANUAL_REMOVE_USERS_BAN -> manualRemoveUsersBanUri;
            case GET_USER_DETAILS_BY_USERNAME -> getUserDetailsByUsernameUri;
            case FORGOTTEN_PASSWORD_RESET -> forgottenPasswordResetUri;
            case PASSWORD_RESET -> passwordResetUri;
            case USERNAME_RESET -> usernameResetUri;
            case EMAIL_RESET -> emailResetUri;
            case DELETE_PRODUCT_ADMIN -> deleteProductAdminUri;
            case GET_USER_ACCOUNT_STATUS -> getUserAccountStatusUri;
            case POST_USER_ACTION -> postUserActionUri;
            case GET_USER_ACTIONS -> getUserActionsUri;
        };
    }
}
