import axios from "axios";

const MULTI_LANGUAGE_SEARCH = "/api/language/search";

const LANG_CODE = {
    // ========== (Common) ========== //
    COMMON_SEARCH: "@COMMON_SEARCH", // 조회
    COMMON_RESET: "@COMMON_RESET", // 초기화
    COMMON_ADD: "@COMMON_ADD", // 추가
    COMMON_MODIFY: "@COMMON_MODIFY", // 수정
    COMMON_DELETE: "@COMMON_DELETE", // 삭제
    COMMON_KEYWORD: "@COMMON_KEYWORD", // 키워드
    COMMON_CODE: "@COMMON_CODE", // 코드
    COMMON_TEXT: "@COMMON_TEXT", // 문자열
    COMMON_INQUIRY: "@COMMON_INQUIRY", // 조회
    // ============================== //

    // ========== (Authority.vue) ========== //
    AUTHORITY_GROUP_LIST: "@AUTHORITY_GROUP_LIST", // 모든 권한 그룹
    AUTHORITY_GROUP_INFO: "@AUTHORITY_GROUP_INFO", // 권한 그룹 정보
    AUTHORITY_GROUP_ID: "@AUTHORITY_GROUP_ID", // 권한 그룹 아이디
    AUTHORITY_GROUP_DESCRIPTION: "@AUTHORITY_GROUP_DESCRIPTION", // 권한 그룹 설명
    AUTHORITY_GROUP_FOOTER: "@AUTHORITY_GROUP_FOOTER", // 권한 그룹 관리 메뉴 화면 입니다.
    // ===================================== //

    // ========== (Groups.vue) ========== //
    USER_GROUP_LIST: "@USER_GROUP_LIST", // 모든 사용자 그룹
    USER_GROUP_INFO: "@USER_GROUP_INFO", // 사용자 그룹 정보
    USER_GROUP_ID: "@USER_GROUP_ID", // 사용자 그룹 아이디
    USER_GROUP_DESCRIPTION: "@USER_GROUP_DESCRIPTION", // 사용자 그룹 설명
    USER_GROUP_FOOTER: "@USER_GROUP_FOOTER", // 사용자 그룹 관리 메뉴 화면 입니다.
    // ===================================== //

    // ========== (MultiLang.vue) ========== //
    MULTI_LANG_LIST: "@MULTI_LANG_LIST", // 모든 다국어 정보
    MULTI_LANG_KOREAN: "@MULTI_LANG_KOREAN", // 한국어
    MULTI_LANG_ENGLISH: "@MULTI_LANG_ENGLISH", // 영어
    MULTI_LANG_CHINESE: "@MULTI_LANG_CHINESE", // 중국어
    MULTI_LANG_VIETNAMES: "@MULTI_LANG_VIETNAMES", // 베트남어
    MULTI_LANG_INFO: "@MULTI_LANG_INFO", // 다국어 관리 정보
    MULTI_LANG_FOOTER: "@MULTI_LANG_FOOTER", // 다국어 정보 관리 메뉴 화면 입니다.
    // ===================================== //
}

let langData = new Object();

const language = (langCode, langtyp) => {
    axios
        .get(MULTI_LANGUAGE_SEARCH, {
            params: {
                langCode: langCode,
                langTyp: langtyp,
            }
        })
        .then(
            (response) => (
                (langData["langCode"] = langCode),
                (langData[langData["langCode"]] = response.data["langData"])
            )
        )
        .catch((error) => alert(error))
}

const multiLanguageSet = (langtyp) => {
    language(LANG_CODE.COMMON_SEARCH, langtyp); // 검색
    language(LANG_CODE.COMMON_RESET, langtyp); // 초기화
    language(LANG_CODE.COMMON_ADD, langtyp); // 추가
    language(LANG_CODE.COMMON_MODIFY, langtyp); // 수정
    language(LANG_CODE.COMMON_DELETE, langtyp); // 삭제
    language(LANG_CODE.COMMON_KEYWORD, langtyp); // 키워드
    language(LANG_CODE.COMMON_CODE, langtyp); // 코드
    language(LANG_CODE.COMMON_TEXT, langtyp); // 문자열
    language(LANG_CODE.COMMON_INQUIRY, langtyp); // 조회

    language(LANG_CODE.AUTHORITY_GROUP_LIST, langtyp); // 모든 권한 그룹
    language(LANG_CODE.AUTHORITY_GROUP_INFO, langtyp); // 권한 그룹 정보
    language(LANG_CODE.AUTHORITY_GROUP_ID, langtyp); // 권한 그룹 아이디
    language(LANG_CODE.AUTHORITY_GROUP_DESCRIPTION, langtyp); // 권한 그룹 설명
    language(LANG_CODE.AUTHORITY_GROUP_FOOTER, langtyp); // 권한 그룹 관리 메뉴 화면 입니다.

    language(LANG_CODE.USER_GROUP_LIST, langtyp); // 모든 사용자 그룹
    language(LANG_CODE.USER_GROUP_INFO, langtyp); // 사용자 그룹 정보
    language(LANG_CODE.USER_GROUP_ID, langtyp); // 사용자 그룹 아이디
    language(LANG_CODE.USER_GROUP_DESCRIPTION, langtyp); // 사용자 그룹 설명
    language(LANG_CODE.USER_GROUP_FOOTER, langtyp); // 사용자 그룹 관리 메뉴 화면 입니다.

    language(LANG_CODE.MULTI_LANG_LIST, langtyp); // 모든 다국어 정보
    language(LANG_CODE.MULTI_LANG_KOREAN, langtyp); // 한국어
    language(LANG_CODE.MULTI_LANG_ENGLISH, langtyp); // 영어
    language(LANG_CODE.MULTI_LANG_CHINESE, langtyp); // 중국어
    language(LANG_CODE.MULTI_LANG_VIETNAMES, langtyp); // 베트남어
    language(LANG_CODE.MULTI_LANG_INFO, langtyp); // 모든 다국어 정보
    language(LANG_CODE.MULTI_LANG_FOOTER, langtyp); // 다국어 정보 관리 메뉴 화면 입니다.
}

const getLangText = (langCode) => {
    console.log(langData[langCode]);
    return langData[langCode];
}

export { multiLanguageSet, getLangText, LANG_CODE }