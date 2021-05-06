import axios from "axios";

const LANGUAGE_PATH = "/api/language/search";
const LANGAUGE_CODE = "KO";

// // ========== (Common) ========== //
// const COMMON_ID = "COMMON_ID"; // 아이디
// const COMMON_DESCRIPTION = "COMMON_DESCRIPTION"; // 설명
// const COMMON_RESET = "COMMON_RESET"; // 초기화
// const COMMON_ADD = "COMMON_ADD"; // 추가
// const COMMON_MODIFY = "COMMON_MODIFY"; // 수정
// const COMMON_DELETE = "COMMON_DELETE"; // 삭제
// const COMMON_KEYWORD = "COMMON_KEYWORD"; // 키워드
// const COMMON_CODE = "COMMON_CODE"; // 코드
// const COMMON_TEXT = "COMMON_TEXT"; // 문자열
// const COMMON_SEARCH = "COMMON_SEARCH"; // 조회
// // ============================== //

// // ========== (Authority.vue) ========== //
// const AUTHORITY_GROUP_LIST = "AUTHORITY_GROUP_LIST"; // 모든 권한 그룹
// const AUTHORITY_GROUP_INFO = "AUTHORITY_GROUP_INFO"; // 권한 그룹 정보
// const AUTHORITY_GROUP_ID = "AUTHORITY_GROUP_ID"; // 권한 그룹 아이디
// const AUTHORITY_GROUP_DESCRIPTION = "AUTHORITY_GROUP_DESCRIPTION"; // 권한 그룹 설명
// const AUTHORITY_GROUP_FOOTER = "AUTHORITY_GROUP_FOOTER"; // 권한 그룹 관리 메뉴 화면 입니다.
// // ===================================== //

// // ========== (Groups.vue) ========== //
// const USER_GROUP_LIST = "USER_GROUP_LIST"; // 모든 사용자 그룹
// const USER_GROUP_INFO = "USER_GROUP_INFO"; // 사용자 그룹 정보
// const USER_GROUP_ID = "USER_GROUP_ID"; // 사용자 그룹 아이디
// const USER_GROUP_DESCRIPTION = "USER_GROUP_DESCRIPTION"; // 사용자 그룹 설명
// const USER_GROUP_FOOTER = "USER_GROUP_FOOTER"; // 사용자 그룹 관리 메뉴 화면 입니다.
// // ===================================== //

// let langText = new Object();

// const language = (keyword) => {
//     axios
//         .get(LANGUAGE_PATH, {
//             params: {
//                 keyword: keyword,
//                 code: LANGAUGE_CODE,
//             }
//         })
//         .then(
//             (response) => (
//                 (langText["keyword"] = keyword),
//                 (langText[langText["keyword"]] = response.data)
//             )
//         )
//         .catch((error) => alert(keyword + "/" + code + "-" + error));
// }

// const multiLanguageSet = () => {
//     language(COMMON_ID); // 아이디
//     language(COMMON_DESCRIPTION); // 설명
//     language(COMMON_RESET); // 초기화
//     language(COMMON_ADD); // 추가
//     language(COMMON_MODIFY); // 수정
//     language(COMMON_DELETE); // 삭제
//     language(COMMON_KEYWORD); // 키워드
//     language(COMMON_CODE); // 코드
//     language(COMMON_TEXT); // 문자열
//     language(COMMON_SEARCH); // 조회

//     language(AUTHORITY_GROUP_LIST); // 모든 권한 그룹
//     language(AUTHORITY_GROUP_INFO); // 권한 그룹 정보
//     language(AUTHORITY_GROUP_ID); // 권한 그룹 아이디
//     language(AUTHORITY_GROUP_DESCRIPTION); // 권한 그룹 설명
//     language(AUTHORITY_GROUP_FOOTER);

//     language(USER_GROUP_LIST); // 모든 사용자 그룹
//     language(USER_GROUP_INFO); // 사용자 그룹 정보
//     language(USER_GROUP_ID); // 사용자 그룹 아이디
//     language(USER_GROUP_DESCRIPTION); // 사용자 그룹 설명
//     language(USER_GROUP_FOOTER);
// }

// const getLangText = (keyword) => {
//     console.log(langText[keyword]);
//     return langText[keyword];
// }

// export { multiLanguageSet, getLangText }

const LANG_KEYWORD = {
    // ========== (Common) ========== //
    COMMON_SEARCH: "COMMON_SEARCH", // 조회
    COMMON_RESET: "COMMON_RESET", // 초기화
    COMMON_ADD: "COMMON_ADD", // 추가
    COMMON_MODIFY: "COMMON_MODIFY", // 수정
    COMMON_DELETE: "COMMON_DELETE", // 삭제
    COMMON_KEYWORD: "COMMON_KEYWORD", // 키워드
    COMMON_CODE: "COMMON_CODE", // 코드
    COMMON_TEXT: "COMMON_TEXT", // 문자열
    COMMON_INQUIRY: "COMMON_INQUIRY", // 조회
    // ============================== //

    // ========== (Authority.vue) ========== //
    AUTHORITY_GROUP_LIST: "AUTHORITY_GROUP_LIST", // 모든 권한 그룹
    AUTHORITY_GROUP_INFO: "AUTHORITY_GROUP_INFO", // 권한 그룹 정보
    AUTHORITY_GROUP_ID: "AUTHORITY_GROUP_ID", // 권한 그룹 아이디
    AUTHORITY_GROUP_DESCRIPTION: "AUTHORITY_GROUP_DESCRIPTION", // 권한 그룹 설명
    AUTHORITY_GROUP_FOOTER: "AUTHORITY_GROUP_FOOTER", // 권한 그룹 관리 메뉴 화면 입니다.
    // ===================================== //

    // ========== (Groups.vue) ========== //
    USER_GROUP_LIST: "USER_GROUP_LIST", // 모든 사용자 그룹
    USER_GROUP_INFO: "USER_GROUP_INFO", // 사용자 그룹 정보
    USER_GROUP_ID: "USER_GROUP_ID", // 사용자 그룹 아이디
    USER_GROUP_DESCRIPTION: "USER_GROUP_DESCRIPTION", // 사용자 그룹 설명
    USER_GROUP_FOOTER: "USER_GROUP_FOOTER", // 사용자 그룹 관리 메뉴 화면 입니다.
    // ===================================== //

    // ========== (MultiLang.vue) ========== //
    MULTI_LANG_LIST: "MULTI_LANG_LIST", // 모든 다국어 정보
    MULTI_LANG_FOOTER: "MULTI_LANG_FOOTER", // 다국어 정보 관리 메뉴 화면 입니다.
    // ===================================== //
}

let langText = new Object();

const language = (keyword) => {
    axios
        .get(LANGUAGE_PATH, {
            params: {
                keyword: keyword,
                code: LANGAUGE_CODE,
            }
        })
        .then(
            (response) => (
                (langText["keyword"] = keyword),
                (langText[langText["keyword"]] = response.data)
            )
        )
        .catch((error) => alert(keyword + "/" + code + "-" + error));
}

const multiLanguageSet = () => {
    language(LANG_KEYWORD.COMMON_SEARCH); // 검색
    language(LANG_KEYWORD.COMMON_RESET); // 초기화
    language(LANG_KEYWORD.COMMON_ADD); // 추가
    language(LANG_KEYWORD.COMMON_MODIFY); // 수정
    language(LANG_KEYWORD.COMMON_DELETE); // 삭제
    language(LANG_KEYWORD.COMMON_KEYWORD); // 키워드
    language(LANG_KEYWORD.COMMON_CODE); // 코드
    language(LANG_KEYWORD.COMMON_TEXT); // 문자열
    language(LANG_KEYWORD.COMMON_INQUIRY); // 조회

    language(LANG_KEYWORD.AUTHORITY_GROUP_LIST); // 모든 권한 그룹
    language(LANG_KEYWORD.AUTHORITY_GROUP_INFO); // 권한 그룹 정보
    language(LANG_KEYWORD.AUTHORITY_GROUP_ID); // 권한 그룹 아이디
    language(LANG_KEYWORD.AUTHORITY_GROUP_DESCRIPTION); // 권한 그룹 설명
    language(LANG_KEYWORD.AUTHORITY_GROUP_FOOTER); // 권한 그룹 관리 메뉴 화면 입니다.

    language(LANG_KEYWORD.USER_GROUP_LIST); // 모든 사용자 그룹
    language(LANG_KEYWORD.USER_GROUP_INFO); // 사용자 그룹 정보
    language(LANG_KEYWORD.USER_GROUP_ID); // 사용자 그룹 아이디
    language(LANG_KEYWORD.USER_GROUP_DESCRIPTION); // 사용자 그룹 설명
    language(LANG_KEYWORD.USER_GROUP_FOOTER); // 사용자 그룹 관리 메뉴 화면 입니다.

    language(LANG_KEYWORD.MULTI_LANG_LIST); // 모든 다국어 정보
    language(LANG_KEYWORD.MULTI_LANG_FOOTER); // 다국어 정보 관리 메뉴 화면 입니다.
}

const getLangText = (keyword) => {
    console.log(langText[keyword]);
    return langText[keyword];
}

export { multiLanguageSet, getLangText, LANG_KEYWORD }

// let langText;

// const language = (keyword) => {
//     axios
//         .get(LANGUAGE_PATH, {
//             params: {
//                 keyword: keyword,
//                 code: LANGAUGE_CODE,
//             }
//         })
//         .then(
//             (response) => getTempLangText(response.data)
//         )
//         .catch((error) => alert(keyword + "/" + code + "-" + error));

//     console.log("langText : " + langText)

//     return langText;
// }

// const getTempLangText = (text) => {
//     langText = text;
// }

// const setTempLangText = () => {
//     return langText;
// }

// const getLangText = (keyword) => {
//     language(keyword);
//     return setTempLangText();
// }

// export { getLangText }