import axios from "axios";

const LANGUAGE_PATH = "/api/language/search";
const LANGAUGE_CODE = "CN";

// ========== (Common) ========== //
const COMMON_ID = "COMMON_ID"; // 아이디
const COMMON_DESCRIPTION = "COMMON_DESCRIPTION"; // 설명
const COMMON_RESET = "COMMON_RESET"; // 초기화
const COMMON_ADD = "COMMON_ADD"; // 추가
const COMMON_MODIFY = "COMMON_MODIFY"; // 수정
const COMMON_DELETE = "COMMON_DELETE"; // 삭제
const COMMON_KEYWORD = "COMMON_KEYWORD"; // 키워드
const COMMON_CODE = "COMMON_CODE"; // 코드
const COMMON_TEXT = "COMMON_TEXT"; // 문자열
// ============================== //

// ========== (Authority.vue) ========== //
const AUTHORITY_GROUP_LIST = "AUTHORITY_GROUP_LIST"; // 모든 권한 그룹
const AUTHORITY_GROUP_INFO = "AUTHORITY_GROUP_INFO"; // 권한 그룹 정보
// ===================================== //

let langText = new Object();

// const language = (keyword) => {
//     axios
//         .post(LANGUAGE_PATH, {
//             keyword: keyword,
//             code: LANGAUGE_CODE,
//         })
//         .then(
//             (response) => (
//                 (langText["keyword"] = keyword),
//                 (langText[langText["keyword"]] = response.data)
//             )
//         )
//         .catch((error) => alert(keyword + "/" + code + "-" + error));
// }

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
    // 아이디
    language(COMMON_ID);

    // 설명
    language(COMMON_DESCRIPTION);

    // 초기화
    language(COMMON_RESET);

    // 추가
    language(COMMON_ADD);

    // 수정
    language(COMMON_MODIFY);

    // 삭제
    language(COMMON_DELETE);

    // 키워드
    language(COMMON_KEYWORD);

    // 코드
    language(COMMON_CODE);

    // 문자열
    language(COMMON_TEXT);

    // 모든 권한 그룹
    language(AUTHORITY_GROUP_LIST);

    // 권한 그룹 정보
    language(AUTHORITY_GROUP_INFO);
}

const getLangText = (keyword) => {
    // console.log("langText[keyword] : " + langText[keyword])
    return langText[keyword];
}

export { multiLanguageSet, getLangText }